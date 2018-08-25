package com.blindheron

//#user-registry-actor
import akka.actor.{Actor, ActorLogging, Props}
import com.blindheron.mongo.UserRepository

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

//#user-case-classes
final case class Users(users: Seq[User])
//#user-case-classes

object UserRegistryActor {
  final case class ActionPerformed(description: String)
  final case object GetUsers
  final case class CreateUser(user: User)
  final case class GetUser(name: String)
  final case class DeleteUser(name: String)

  implicit val ec: ExecutionContext = ExecutionContext.global

  def props: Props = Props(classOf[UserRegistryActor], new UserRepository(Mongo.userCollection))
}

class UserRegistryActor(repository: UserRepository) extends Actor with ActorLogging {
  import UserRegistryActor._

  var users = Set.empty[User]

  def receive: Receive = {
    case GetUsers =>
      sender() ! Users(Await.result(repository.all, 1.second))
    case CreateUser(user) =>
      repository.save(user)
      sender() ! ActionPerformed(s"User ${user.name} created.")
    case GetUser(name) =>
      sender() ! users.find(_.name == name)
    case DeleteUser(name) =>
      users.find(_.name == name) foreach { user => users -= user }
      sender() ! ActionPerformed(s"User ${name} deleted.")
  }
}
//#user-registry-actor