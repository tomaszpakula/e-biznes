from fastapi import FastAPI
from gpt4all import GPT4All
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware
from random import choice
import gensim.downloader as api
import numpy as np

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class Question(BaseModel):
    text: str   
    
model = GPT4All("Meta-Llama-3-8B-Instruct.Q4_0.gguf",device="cpu" )

word_vectors = api.load("glove-wiki-gigaword-50")

shop_keywords = ["clothes", "shopping", "price", "outfit", "dress", "shirt", "pants", "store", "fashion", "sale"]

def sentence_vector(sentence: str) -> np.ndarray:
    """Wylicz średni wektor dla zdania na podstawie word vectors"""
    words = sentence.lower().split()
    valid_vectors = [word_vectors[word] for word in words if word in word_vectors]
    if not valid_vectors:
        return np.zeros(word_vectors.vector_size)
    return np.mean(valid_vectors, axis=0)

def is_on_topic(question: str, threshold=0.5) -> bool:
    """Sprawdź czy pytanie jest w temacie sklepu przez podobieństwo kosinusowe"""
    question_vec = sentence_vector(question)
    topic_vec = sentence_vector(" ".join(shop_keywords))
    dot = np.dot(question_vec, topic_vec)
    norm_q = np.linalg.norm(question_vec)
    norm_t = np.linalg.norm(topic_vec)
    if norm_q == 0 or norm_t == 0:
        return False
    similarity = dot / (norm_q * norm_t)
    return similarity >= threshold

openings = [
    "Hello! How can I assist you today?",
    "Hi there! Looking for something specific?",
    "Welcome to our store! What are you shopping for today?",
    "Good to see you! How may I help you?",
    "Hey! Need help finding the perfect outfit?"
]

closings = [
    "Let me know if you need anything else.",
    "I'm here if you have more questions!",
    "Thanks for visiting – happy shopping!",
    "Feel free to ask if something else comes to mind!",
    "Take care and enjoy your day!"
]

def ask_gpt(question: str) -> str:
    # with model_lock:
    with model.chat_session() as chat_session:
        prompt = (
        f"You're shop assistant. Give an answer to client (if it's not about price, and cloth recommendation - don't answer. If someone ask you something about not related with clothes, shopping etc, tell him that you cant answer.):  Hi i'm client {question}. End the conversation using one of following closures: {closings} "   
        )
        
        print(prompt)
        return chat_session.generate(prompt)
# model_lock = Lock()
@app.get("/")
def root():
    return {"reply": choice(openings) }


@app.post("/chat")
def chat(question: Question):
    if not is_on_topic(question.text):
        return {"reply": "Sorry, I can only help with questions about clothes and shopping."}
    answer = ask_gpt(question.text)
    return {
        "reply": answer
    }