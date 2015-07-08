import akka.typed._
import akka.typed.ScalaDSL._
import akka.typed.AskPattern._

import scala.concurrent.Future
import scala.concurrent.Await

import scala.concurrent.duration._

object HelloWorld {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String)

  val greeter = Static[Greet] { msg =>
    println(s"Hello ${msg.whom}!")
    msg.replyTo ! Greeted(msg.whom)
  }
}



object Main extends App {
  import scala.concurrent.ExecutionContext.Implicits.global
  import akka.util.Timeout
  import HelloWorld._

  val system: ActorSystem[Greet] = ActorSystem("hello", Props(greeter))

  implicit val timeout = Timeout(5 seconds)
  val future: Future[Greeted] = system ? (Greet("world", _))

  for {
    greeting <- future.recover { case ex => ex.getMessage }
    done     <- { println(s"result: $greeting"); system.terminate() }
  } println("system terminated")

}