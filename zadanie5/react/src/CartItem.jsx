import React, { useContext } from "react";
import "./App.css";
import axios from "axios";
import { ProductContext } from "./ProductContext";

export default function CartItem({ name, item }) {
  const { setItems } = useContext(ProductContext);
  const removeItem = (id) => {
    axios(`http://localhost:9000/cart/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => {
      setItems((prev) => prev.filter((item) => item.productId != id));
    });
  };

  return (
    <div className="cartItem">
      <h2>{name}</h2>
      <p>quantity: {item.quantity}</p>
      <button onClick={() => removeItem(item.productId)}>Remove</button>
    </div>
  );
}
