package user

import scala.util.Random
import faker._

object UserStorageService {

  private val users: Map[Int, User] =
    List.from(1 to 5).map(id => id -> generateUser(id)).toMap

  def getUser(id: Int): Option[User] ={
    users.get(id)
  }

  private def generateUser(id: Int): User = {
    User(
      id = id,
      firstName = Name.first_name,
      lastName = Name.last_name,
      age = Random.between(10, 50),
      address = Address("India", "Karnataka", "Bangalore")
    )
  }

}

case class User(
    id: Int,
    firstName: String,
    lastName: String,
    age: Int,
    address: Address
)

case class Address(
    country: String,
    region: String,
    city: String
)
