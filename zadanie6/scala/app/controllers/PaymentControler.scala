package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models._

@Singleton
class PaymentController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
    
    def validate = Action(parse.json){ request =>
        request.body.validate[Payment] match{
            case JsSuccess(payment,_) => {
                if(payment.cardNumber.length == 4 && payment.cvv.length == 3){
                    Ok(Json.obj("status" -> "valid"))
                } else {
                    BadRequest(Json.obj("error" -> "Invalid card number or CVV"))
                }
            }
            case JsError(_) => BadRequest(Json.obj("error" -> "Payment Failed"))
        }
    }


}