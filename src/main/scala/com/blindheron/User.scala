package com.blindheron

import io.circe._
import io.circe.syntax._
import org.bson.types.ObjectId

case class FindByIdRequest(id: String) {
  require(ObjectId.isValid(id), "the informed id is not a representation of a valid hex string")
}

case class User(_id: ObjectId, name: String, age: Int, countryOfResidence: String) {
  require(name != null, "username not informed")
  require(name.nonEmpty, "username cannot be empty")
  require(age > 0, "age cannot be lower than 1")
}

case class Message(message: String)

object Message {
  implicit val encoder: Encoder[Message] = m => Json.obj("message" -> m.message.asJson)
}
