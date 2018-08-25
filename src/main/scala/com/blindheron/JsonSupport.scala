package com.blindheron

import com.blindheron.UserRegistryActor.ActionPerformed
import org.bson.types.ObjectId
import spray.json.{JsString, JsValue, JsonFormat}

//#json-support
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit object ObjectIdJsonFormat extends JsonFormat[ObjectId] {
    override def read(json: JsValue): ObjectId = {
      new ObjectId(json.convertTo[String])
    }

    override def write(obj: ObjectId): JsValue = {
      JsString(obj.toString)
    }
  }

  implicit val userJsonFormat = jsonFormat4(User)
  implicit val usersJsonFormat = jsonFormat1(Users)

  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
//#json-support
