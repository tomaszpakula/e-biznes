package models
import play.api.libs.json._

case class Payment(cardNumber: String, cardHolder: String, expirationDate: String, cvv: String)

object Payment {
    implicit val PayementFormat: OFormat[Payment] = Json.format[Payment]
}
