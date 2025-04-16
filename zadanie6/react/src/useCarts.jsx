import axios from "axios";
import React, { useContext } from "react";
import { ProductContext } from "./ProductContext";

export default function useCarts() {
  const { setItems } = useContext(ProductContext);
  const { setCartChange } = useContext(ProductContext);
  const clearCart = () => {
    axios(`http://localhost:9000/cart`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => {
      setItems([]);
    });
  };

  const addToCart = (productId, quantity) => {
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
      setCartChange((prev) => !prev);
      return response;
    });
  };
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

  return { clearCart, removeItem, addToCart };
}
