import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import CartItem from "../CartItem";
import React from "react";

describe("CartItem", () => {
  it("renders  the product name, quantity, and buttons", () => {
    const mockContext = {
      setItems: vi.fn(),
      setCartChange: vi.fn(),
    };
    render(<CartItem name="Laptop" item={{ quantity: 3 }} />);
    expect(screen.getByText("Laptop")).toBeInTheDocument();
    expect(screen.getByText("3")).toBeInTheDocument();
  });
});
