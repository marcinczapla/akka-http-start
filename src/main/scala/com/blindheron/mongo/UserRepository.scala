package com.blindheron.mongo

import com.blindheron.User
import org.mongodb.scala._
import org.mongodb.scala.bson.ObjectId

import scala.concurrent.{ExecutionContext, Future}

class UserRepository(collection: MongoCollection[User])(implicit ec: ExecutionContext) {

  def all: Future[Seq[User]] =
    collection.find().limit(5).collect().head()

  def findById(id: String): Future[Option[User]] =
    collection
      .find(Document("_id" -> new ObjectId(id)))
      .first
      .head
      .map(Option(_))

  def save(user: User): Future[String] =
    collection
      .insertOne(user)
      .head
      .map { _ => user._id.toHexString }
}
