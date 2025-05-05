package controllers

import (
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
	"net/http"
	"strconv"
	"zadanie4/models"
)

type CartController struct {
	DB *gorm.DB
}

func (cc *CartController) AddToCart(c echo.Context) error {
	var cartProduct models.Cart

	if err := c.Bind(&cartProduct); err != nil {
		return c.JSON(http.StatusBadRequest, echo.Map{"message": "Invalid input"})
	}
	var existingProduct models.Cart
	if err := cc.DB.Where("product_id = ?", cartProduct.ProductID).First(&existingProduct).Error; err == nil {
		existingProduct.Quantity += cartProduct.Quantity
		cc.DB.Save(&existingProduct)
		return c.JSON(http.StatusOK, existingProduct)
	}
	if err := cc.DB.Create(&cartProduct).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, echo.Map{"message": "Failed to add product"})
	}

	return c.JSON(http.StatusCreated, cartProduct)
}

func (cc *CartController) GetAllProducts(c echo.Context) error {
	var cartProducts []models.Cart
	relation := c.QueryParam("relation")
	quantity := c.QueryParam("quantity")

	results := cc.DB.Model(&models.Cart{})

	if quantity, err := strconv.Atoi(quantity); err == nil {
		results = results.Scopes(models.FilterByQuantity(relation, quantity))
	}

	results = results.Find(&cartProducts)

	if results.Error != nil {
		return c.JSON(http.StatusInternalServerError, echo.Map{"message": "Failed to get all products"})
	}

	return c.JSON(http.StatusOK, cartProducts)
}

func (cc *CartController) RemoveProduct(c echo.Context) error {
	productID := c.Param("id")
	var cartProduct models.Cart

	if err := cc.DB.Where("product_id = ?", productID).Delete(&cartProduct).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"message": "Product not found"})
	}

	return c.JSON(http.StatusOK, echo.Map{"message": "Product removed"})
}
