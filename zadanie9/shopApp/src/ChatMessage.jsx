import React from "react";

export default function ChatMessage({ from, message }) {
  return (
    <div
        className={"chat-message-"+from}
      style={{
        width: "60%",
        border: "2px solid ",
        borderRadius: "1.1rem",
        minHeight: "3rem",
        display: "flex",
        alignItems: "center",
        padding: "1rem",
        textAlign: "left",
        marginBottom: "1.5rem"
      }}
    >
      {message}
    </div>
  );
}
