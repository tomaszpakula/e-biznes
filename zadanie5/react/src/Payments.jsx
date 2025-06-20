import React from "react";
import axios from "axios";
import "./App.css";
import { Navigate, useNavigate } from "react-router-dom";

export default function Payments() {
  const cardNumber = React.useRef(null);
  const cardHolder = React.useRef(null);
  const expirationDate = React.useRef(null);
  const cvv = React.useRef(null);
  const navigate = useNavigate();
  const sendPayment = () => {
    axios("http://localhost:9000/validate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: {
        cardNumber: cardNumber.current.value,
        cardHolder: cardHolder.current.value,
        expirationDate: expirationDate.current.value,
        cvv: cvv.current.value,
      },
    })
      .then((response) => {
        navigate("/message", { state: { message: "Payment successful!" } });
        return response;
      })
      .catch((error) => {
        navigate("/message", { state: { message: "Something went wrong!" } });
      });
  };

  return (
    <div>
      <h1>Payments</h1>
      <form
        style={{
          display: "flex",
          flexDirection: "column",
          height: "70vh",
          justifyContent: "space-around",
          minWidth: "25vw",
        }}
        onSubmit={(e) => {
          e.preventDefault();
          sendPayment();
        }}
      >
        <input
          type="text"
          maxLength={4}
          minLength={4}
          placeholder="Card Number (4 digits)"
          ref={cardNumber}
          required
        />
        <input
          type="text"
          placeholder="Card Holder"
          ref={cardHolder}
          required
        />
        <input
          type="text"
          placeholder="Expiration Date"
          ref={expirationDate}
          pattern="\d{2}/\d{2}"
          title="Format: MM/YY"
          required
        />
        <input
          type="text"
          placeholder="CVV (3 digits)"
          ref={cvv}
          required
          minLength={3}
          maxLength={3}
        />
        <button>Pay</button>
      </form>
    </div>
  );
}
