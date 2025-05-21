from fastapi import FastAPI
from gpt4all import GPT4All
from pydantic import BaseModel
from threading import Lock

app = FastAPI()

class Question(BaseModel):
    text: str   
    
model = GPT4All("orca-mini-3b-gguf2-q4_0.gguf",device="cpu" )

def ask_gpt(question: str) -> str:
    # with model_lock:
    with model.chat_session():
        return model.generate(
            question,
            temp=0.7,
            top_k=40,
            top_p=0.95)
# model_lock = Lock()
@app.get("/")
def root():
    return {"message": "Hello. How can I help you?" }

@app.post("/chat")
def chat(question: Question):
    
    answer = ask_gpt(question.text)
    return {
        "reply": answer
    }