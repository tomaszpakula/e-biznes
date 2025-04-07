import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

export default function Product({ product }) {
  const [quantity, setQuantity] = useState(0);
  const addToCart = (productId) => {
    axios("http://localhost:9000/cart", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: {
        productId: productId,
        quantity: quantity,
      },
    }).then((response) => {
      return response;
    });
  };
  return (
    <div
      style={{
        border: "1px solid #555",
        borderRadius: "0.6rem",
        padding: "1rem",
      }}
    >
      <h2>{product.name}</h2>
      <p>${product.price}</p>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          gap: "1rem",
        }}
      >
        <button
          disabled={quantity === 0}
          onClick={() => {
            setQuantity((prev) => prev - 1);
          }}
        >
          -
        </button>
        <div
          style={{
            maxWidth: "50%",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            border: "1px solid #333",
            borderTadius: "0.5rem",
            minWidth: "3rem",
          }}
        >
          {quantity}
        </div>
        <button
          onClick={() => {
            setQuantity((prev) => prev + 1);
          }}
        >
          +
        </button>
        <FontAwesomeIcon
          icon={faCartShopping}
          className="cart"
          onClick={() => {
            addToCart(product.id);
            setQuantity(0);
          }}
        />
      </div>
    </div>
  );
}
