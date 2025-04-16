import React, { useState } from "react";
import "./App.css";
import Products from "./Products";
import Payments from "./Payments";
import { BrowserRouter, Link, Route, Router, Routes } from "react-router-dom";
import Cart from "./Cart";
import { ProductProvider } from "./ProductContext";
import PaymentMessage from "./PaymentMessage";
import CartIcon from "./CartIcon";

function App() {
  const [count, setCount] = useState(0);

  return (
    <ProductProvider>
      <BrowserRouter>
        <Routes>
          <Route
            path="/"
            element={
              <>
                <Products />
                <CartIcon to="/cart" />
              </>
            }
          />
          <Route
            path="/cart"
            element={
              <>
                <Cart />
                <CartIcon to="/" />
              </>
            }
          />
          <Route path="/payments" element={<Payments />} />
          <Route path="/message" element={<PaymentMessage />} />
        </Routes>
      </BrowserRouter>
    </ProductProvider>
  );
}
export default App;
