package user

import scala.concurrent.Future
import com.protobuftest.models.v1.{UserRequest, UserResponse}
import com.protobuftest.models.v1.UserServiceGrpc.UserService

class UserFetchService extends UserService {
  override def fetchUser(request: UserRequest): Future[UserResponse] = {
    val user = UserStorageService.getUser(request.id).get
    Future.successful(generateResponse(user))
  }

  private def generateResponse(user: User): UserResponse = {
    UserResponse(
      id = user.id,
      firstName = user.firstName,
      lastName = user.lastName,
      age = user.age,
      address = Some(generateAddress(user.address))
    )
  }

  private def generateAddress(address: Address): UserResponse.Address = {
    UserResponse.Address(
      country = address.country,
      region = address.region,
      city = address.city
    )
  }

}
