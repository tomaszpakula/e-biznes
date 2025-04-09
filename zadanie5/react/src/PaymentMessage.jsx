import React from "react";
import { Link, useLocation } from "react-router-dom";

export default function PaymentMessage() {
  const location = useLocation();
  const message = location.state?.message || "Something went wrong ...";
  return (
    <>
      <div>{message}</div>
      <Link to="/">Back to shop</Link>
    </>
  );
}
