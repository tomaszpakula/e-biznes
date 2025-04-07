import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import axios from "axios";
import React, { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { ProductContext } from "./ProductContext";
import CartItem from "./CartItem";
import "./App.css";

export default function Cart() {
  const { items, setItems } = useContext(ProductContext);
  const { products } = useContext(ProductContext);

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

  return (
    <div id="cartItems">
      <h1>Cart Items</h1>
      <Link to="/">
        <FontAwesomeIcon icon={faCartShopping} className="cart" id="cart" />
      </Link>
      {items.map((item, index) => {
        const product = products.find((p) => p.id === item.productId);
        return (
          <>
            {product ? (
              <CartItem name={product.name} item={item} key={`item${index}`} />
            ) : (
              ""
            )}
          </>
        );
      })}
      <button
        disabled={items.length === 0}
        onClick={() => {
          clearCart();
        }}
      >
        Clear Cart
      </button>
    </div>
  );
}
