import axios from "axios";
import React, { createContext, useEffect, useState } from "react";

export const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
  const [products, setProducts] = useState([]);
  const [items, setItems] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:9000/products", {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => setProducts(response.data));
  }, [products]);

  useEffect(() => {
    axios("http://localhost:9000/cart", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }).then((response) => {
      setItems(response.data);
    });
  }, [items]);

  return (
    <ProductContext.Provider value={{ products, setProducts, items, setItems }}>
      {children}
    </ProductContext.Provider>
  );
};
