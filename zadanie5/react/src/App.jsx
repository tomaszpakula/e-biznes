import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Products from "./Products";
import Payments from "./Payments";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Products />
      <Payments />
    </>
  );
}

export default App;
