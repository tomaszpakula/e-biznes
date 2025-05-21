import React, { useState } from "react";


export default function ChatInput({ setMessages, setLoading, setError}) {
  const [message, setMessage] = useState("");
  return (
    <>
      <textarea
        placeholder="Type your prompt"
        value={message}
        onChange={(e) => {
          setMessage(e.target.value);
        }}
        style={{
          margin: "2rem auto 1rem auto",
          width: "50%",
          padding: "1rem",
          fontSize: "1.1rem",
          minHeight: "3rem",
          border: "2px solid white",
          borderRadius: "1.5rem",
        }}
      />
      <button
        style={{ marginBottom: "1rem" }}
        onClick={async () => {
          setMessage("");
          await setMessages((prev) => [...prev, { message, from: "user" }]);
          try {
            setLoading(true);
            setError(null);
            const response = await fetch("http://localhost:8000/chat", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({ text: message }),
            });

            const data = await response.json();

            setMessages((prev) => [
              ...prev,
              { from: "bot", message: data.reply },
            ]);
          } catch (error) {
            console.error("Błąd połączenia:", error);
            setError("Could not connect with bot: "+error);
          }
          setLoading(false);
        }}
      >
        Send
      </button>
    </>
  );
}
