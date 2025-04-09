package main

import (
	"github.com/labstack/echo/v4"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
	"zadanie4/controllers"
	"zadanie4/models"
)

type App struct {
	DB *gorm.DB
}

func main() {
	db, err := gorm.Open(sqlite.Open("data.db"), &gorm.Config{})

	if err != nil {
		panic("failed to connect database")
	}

	e := echo.New()
	db.AutoMigrate(&models.Product{})

	productController := &controllers.ProductController{DB: db}
	cartController := &controllers.CartController{DB: db}

	e.POST("/products", productController.CreateProduct)
	e.GET("/products", productController.GetProducts)
	e.GET("/products/:id", productController.GetProduct)
	e.PUT("/products/:id", productController.UpdateProduct)
	e.DELETE("/products/:id", productController.DeleteProduct)

	e.POST("/cart", cartController.AddToCart)
	e.GET("/cart", cartController.GetAllProducts)
	e.DELETE("/cart/:id", cartController.RemoveProduct)
	e.Logger.Fatal(e.Start(":8080"))

}
