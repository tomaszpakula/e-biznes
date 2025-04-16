import React, { useContext } from "react";
import "./App.css";
import { ProductContext } from "./ProductContext";
import useCarts from "./useCarts";

export default function CartItem({ name, item }) {
  const { setItems } = useContext(ProductContext);
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
