from fastapi import FastAPI
from gpt4all import GPT4All
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware
from random import choice

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
    
    answer = ask_gpt(question.text)
    print(answer)
    return {
        "reply": answer
    }