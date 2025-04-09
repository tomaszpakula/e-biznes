package models

type Cart struct {
	ProductID uint64 `json:"product_id"`
	Quantity  uint64 `json:"quantity"`
}
