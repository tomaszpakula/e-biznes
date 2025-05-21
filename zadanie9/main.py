from fastapi import FastAPI
from gpt4all import GPT4All
from pydantic import BaseModel
from threading import Lock
from fastapi.middleware.cors import CORSMiddleware

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
    
model = GPT4All("orca-mini-3b-gguf2-q4_0.gguf",device="cpu" )

def ask_gpt(question: str) -> str:
    # with model_lock:
    with model.chat_session() as chat_session:
        prompt = (
        "You're shop assistant. Give an answer to client (if it's not about price, and cloth recommendation - don't answer):  {question} "   
        )
        
        print("prompt")
        return chat_session.generate(prompt)
# model_lock = Lock()
@app.get("/")
def root():
    return {"message": "Hello. How can I help you?" }

@app.post("/chat")
def chat(question: Question):
    
    answer = ask_gpt(question.text)
    print(answer)
    return {
        "reply": answer
    }