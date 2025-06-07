import { render, screen } from '@testing-library/react'
import Product from '../Product'
import React from 'react'
import { ProductContext } from '../ProductContext'
import userEvent from '@testing-library/user-event'
import * as cartsHook from '../useCarts'

describe('Product component', () => {
  const product = {
    id: 1,
    name: "Laptop",
    price: 999
  }

  const mockContext = {
    items: [],
    setItems: vi.fn(),
    setCartChange: vi.fn(),
  }

  const addToCartMock = vi.fn()

  beforeEach(() => {
    vi.clearAllMocks()
    vi.spyOn(cartsHook, 'default').mockReturnValue({
      addToCart: addToCartMock,
      clearCart: vi.fn(),
      removeItem: vi.fn(),
    })
  })

  it('renders product name, price and initial quantity', () => {
    render(
      <ProductContext.Provider value={mockContext}>
        <Product product={product} />
      </ProductContext.Provider>
    )

    expect(screen.getByText('Laptop')).toBeInTheDocument()
    expect(screen.getByText('$999')).toBeInTheDocument()
    expect(screen.getByTestId('add-to-cart')).toBeInTheDocument()
    expect(screen.getByTestId('quantity')).toHaveTextContent('0')
  })

  it('increase button increments quantity', async () => {
    render(
      <ProductContext.Provider value={mockContext}>
        <Product product={product} />
      </ProductContext.Provider>
    )

    const increaseBtn = screen.getByTestId('increase')
    await userEvent.click(increaseBtn)
    expect(screen.getByTestId('quantity')).toHaveTextContent('1')
  })

  it('decrease button decrements quantity but not below 0', async () => {
    render(
      <ProductContext.Provider value={mockContext}>
        <Product product={product} />
      </ProductContext.Provider>
    )

    const increaseBtn = screen.getByTestId('increase')
    const decreaseBtn = screen.getByTestId('decrease')

    expect(decreaseBtn).toBeDisabled()

    await userEvent.click(increaseBtn)
    expect(screen.getByTestId('quantity')).toHaveTextContent('1')
    expect(decreaseBtn).not.toBeDisabled()

    await userEvent.click(decreaseBtn)
    expect(screen.getByTestId('quantity')).toHaveTextContent('0')
    expect(decreaseBtn).toBeDisabled()
  })

  it('clicking add-to-cart calls addToCart and resets quantity', async () => {
    render(
      <ProductContext.Provider value={mockContext}>
        <Product product={product} />
      </ProductContext.Provider>
    )

    const increaseBtn = screen.getByTestId('increase')
    const addToCartBtn = screen.getByTestId('add-to-cart')

    await userEvent.click(increaseBtn)
    await userEvent.click(increaseBtn)

    expect(screen.getByTestId('quantity')).toHaveTextContent('2')

    await userEvent.click(addToCartBtn)

    expect(addToCartMock).toHaveBeenCalledWith(product.id, 2)
    expect(screen.getByTestId('quantity')).toHaveTextContent('0')
  })
})
