package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Category

@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
    implicit val productFormat: OFormat[Category] = Json.format[Category]
    private var categories = List(
        Category(1, "technology"),
        Category(2, "health")
    )

    def getCategories = Action {
        Ok(Json.toJson(categories))
    }

    def getCategoryById(id:Int) = Action {
        categories.find(_.id == id) match {
            case Some(category) => Ok(Json.toJson(category))
            case None => NotFound(Json.obj("error" -> s"Category with id $id doesn't exist"))
        }
    }

    def createCategory = Action(parse.json) { request =>
        request.body.validate[Category] match{
            case JsSuccess(newCategory, _) => {
                categories = categories :+ newCategory
                Created(Json.toJson(newCategory))
            }
            case JsError(_) => BadRequest(Json.obj("error" -> "Bad request" )) 
        }
    }

    def updateCategory(id:Int) = Action(parse.json) { request =>
        request.body.validate[Category] match{
            case JsSuccess(updatedProduct, _) => {
                categories = categories.map(cat => if(cat.id == id) updatedProduct else cat)
                Ok(Json.toJson(updatedProduct))
            }
            case JsError(_) => BadRequest(Json.obj("error" -> "Bad request"))
        }
    }

    def deleteCategory(id:Int) = Action {
        categories.find(_.id == id) match{
            case Some(_) => {
                categories = categories.filterNot(_.id == id)
                NoContent
            }
            case None => NotFound(Json.obj("error" -> s"Category with id $id not found"))
        }
    }
    


}

