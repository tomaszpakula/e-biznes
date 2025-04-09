package controllers

import (
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
	"net/http"
	"zadanie4/models"
)

type ProductController struct {
	DB *gorm.DB
}

func (p *ProductController) CreateProduct(c echo.Context) error {
	var product models.Product
	if err := c.Bind(&product); err != nil {
		return err
	}
	if result := p.DB.Create(&product); result.Error != nil {
		return result.Error
	}
	return c.JSON(http.StatusCreated, product)
}

func (p *ProductController) GetProducts(c echo.Context) error {
	var products []models.Product
	result := p.DB.Find(&products)
	if result.Error != nil {
		return result.Error
	}
	return c.JSON(http.StatusOK, products)
}

func (p *ProductController) GetProduct(c echo.Context) error {
	id := c.Param("id")
	var product models.Product
	if err := p.DB.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, err)
	}
	return c.JSON(http.StatusOK, product)
}

func (p *ProductController) UpdateProduct(c echo.Context) error {
	id := c.Param("id")
	var product models.Product

	if err := p.DB.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, err)
	}

	if err := c.Bind(&product); err != nil {
		return err
	}

	p.DB.Save(&product)
	return c.JSON(http.StatusOK, product)
}

func (p *ProductController) DeleteProduct(c echo.Context) error {
	id := c.Param("id")
	if err := p.DB.Delete(&models.Product{}, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, err)
	}
	return c.JSON(http.StatusOK, "Product deleted")
}
