package user

import com.protobuftest.models.v1.UserServiceGrpc.UserServiceBlockingStub
import com.protobuftest.models.v1.{UserRequest, UserServiceGrpc}
import io.grpc.netty.NettyChannelBuilder
import io.grpc.{ManagedChannel, StatusRuntimeException}

import java.util.concurrent.TimeUnit
import java.util.logging.{Level, Logger}

object UserFetchClient {
  def apply(host: String, port: Int): UserFetchClient = {
    val channel =
      NettyChannelBuilder.forAddress(host, port).usePlaintext().build
    val blockingStub = UserServiceGrpc.blockingStub(channel)
    new UserFetchClient(channel, blockingStub)
  }

  def main(args: Array[String]): Unit = {
    val client = UserFetchClient("localhost", 9081)
    try {
      val user = 1
      client.fetch(user)
    } finally {
      client.shutdown()
    }
  }
}

class UserFetchClient private (
    private val channel: ManagedChannel,
    private val blockingStub: UserServiceBlockingStub
) {
  private[this] val logger = Logger.getLogger(classOf[UserFetchClient].getName)

  def shutdown(): Unit = {
    channel.shutdown.awaitTermination(5, TimeUnit.SECONDS)
  }

  /** Fetch user from server. */
  def fetch(id: Int): Unit = {
    logger.info("Will try to greet " + id + " ...")
    val request = UserRequest(id)
    try {
      val response = blockingStub.fetchUser(request)
      println(response)
      logger.info("Greeting: " + response.firstName + " " + response.lastName)
    } catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }
}
