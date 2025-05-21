import { useState } from "react";
import "./App.css";
import ChatHistory from "./ChatHistory";
import ChatInput from "./ChatInput";

function App() {
  const [messages, setMessages] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  return (
    <div
      style={{
        margin: "0 auto",
        minHeight: "100vh",
        width: "80%",
        padding: "2rem",
        boxSizing: "border-box",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <ChatHistory messages={messages} loading={loading} error={error}/>
      <ChatInput setMessages={setMessages} setLoading={setLoading} setError={setError} />
    </div>
  );
}

export default App;
