import React from "react";
import "./App.css";
import useCarts from "./useCarts";

export default function CartItem({ name, item }) {
  const { removeItem } = useCarts();
  return (
    <div className="cartItem" data-testid="cart-item">
      <h2>{name}</h2>
      <p>quantity: {item.quantity}</p>
      <button
        onClick={() => removeItem(item.productId)}
        data-testid="remove-item"
      >
        Remove
      </button>
    </div>
  );
}
