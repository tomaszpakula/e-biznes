package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Product

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{

    private var products = List(
        Product(1, "Laptop", 3000.00, 1),
        Product(2, "Telefon", 1500.00, 1)
    )

    def getProducts = Action {
        Ok(Json.toJson(products))
    }

    //Pobierz produkt po id
    def getProduct(id: Int) = Action {
        products.find(_.id == id) match {
            case Some(product) => Ok(Json.toJson(product))
            case None => NotFound(Json.obj("error" -> s"Produkt o id $id nie istnieje"))
        }
    }

    //Dodawanie produktu
    def createProduct = Action(parse.json) { request =>
        request.body.validate[Product] match {
            case JsSuccess(newProduct, _) =>{
                products = products :+ newProduct
                Created(Json.toJson(newProduct))
            }
            case JsError(errors) => BadRequest(Json.obj("error" -> "Nieprawidłowy format JSON"))
        }
    }

    //Aktualizacja produktu
    def updateProduct(id: Int) = Action(parse.json) { request =>
        request.body.validate[Product] match {
            case JsSuccess(updatedProduct, _) =>{
                products = products.map(p => if (p.id == id) updatedProduct else p)
                Ok(Json.toJson(updatedProduct))
            }
            case JsError(_) => BadRequest(Json.obj("error"->"Nieprawidłowy format JSON"))
        }
    }

    //Usunięcie produktu
    def deleteProduct(id: Int) = Action {
        products.find(_.id == id) match {
            case Some(_) =>{
                products = products.filterNot(_.id == id)
                NoContent
            }
            case None => NotFound(Json.obj("error" -> "Produkt nie istnieje"))
        }
    }


}