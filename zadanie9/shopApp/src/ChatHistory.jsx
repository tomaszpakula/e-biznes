import React, { useState } from "react";
import ChatMessage from "./ChatMessage";


export default function ChatHistory({ messages, loading, error }) {
  return (
    <div style={{ minHeight: "70%", width: "100%" }}>
      {messages.map((message, idx) => (
        <ChatMessage key={idx} from={message.from} message={message.message} />
      ))}
       {loading && <ChatMessage from={"bot-loading"} message={"..."} />}
       {error && <ChatMessage from={"bot-error"} message={error} />}
    </div>
  );
}
