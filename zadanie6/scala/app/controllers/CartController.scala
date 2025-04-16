package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models._

@Singleton
class CartController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
    
    private var cart = List.empty[CartItem] 

    def getCart = Action{
        Ok(Json.toJson(cart))
    }

    def addToCart = Action(parse.json) { request =>
    request.body.validate[CartItem] match {
    case JsSuccess(newCartItem, _) =>
      cart.find(_.productId == newCartItem.productId) match {
        case Some(existingItem) =>
          cart = cart.map {
            case item if item.productId == existingItem.productId =>
              item.copy(quantity = item.quantity + newCartItem.quantity)
            case item => item
          }
          Ok(Json.toJson(existingItem)) 

        case None =>
          cart = cart :+ newCartItem
          Created(Json.toJson(newCartItem)) 
      }

    case JsError(_) =>
      BadRequest(Json.obj("error" -> "Bad request"))
  }
}


    def updateQuantity(productId: Int) = Action(parse.json){ request =>
        request.body.validate[Int] match{
            case JsSuccess(updatedQuantity, _) if updatedQuantity > 0 => {
                cart.find(_.productId == productId) match {
                    case Some(item) => {
                        cart = cart.map(i => if(i.productId == productId) i.copy(quantity = updatedQuantity) else i)
                        Ok(Json.obj("message" -> s"Updated quantity of item $productId from ${item.quantity} to $updatedQuantity"))
                    }
                    case None => NotFound(Json.obj("error" -> s"Product with id: $productId not found"))
                }
            }

            case JsSuccess(_,_) => BadRequest(Json.obj("error" -> "Value have to be greater than 0"))
            case JsError(_) => BadRequest(Json.obj("error" -> "Incorrect format of body"))
        }
    }

    def removeFromCart(productId: Int) = Action{
        cart.find(_.productId == productId) match {
            case Some(_) => {
                cart = cart.filterNot(_.productId == productId)
                NoContent
            }
            case None => NotFound(Json.obj("error" -> "Not found"))
        }
    }

    def clearCart = Action{
        cart = List.empty[CartItem]
        NoContent
    }

}