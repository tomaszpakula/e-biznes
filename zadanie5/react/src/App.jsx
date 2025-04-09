import { useState } from "react";
import "./App.css";
import Products from "./Products";
import Payments from "./Payments";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
import { BrowserRouter, Link, Route, Router, Routes } from "react-router-dom";
import Cart from "./Cart";
import { ProductProvider } from "./ProductContext";
import PaymentMessage from "./PaymentMessage";

function App() {
  const [count, setCount] = useState(0);

  return (
    <ProductProvider>
      <BrowserRouter>
        <Link to="/cart">
          <FontAwesomeIcon icon={faCartShopping} className="cart" id="cart" />
        </Link>

        <Routes>
          <Route path="/" element={<Products />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/payments" element={<Payments />} />
          <Route path="/message" element={<PaymentMessage />} />
        </Routes>
      </BrowserRouter>
    </ProductProvider>
  );
}
export default App;
