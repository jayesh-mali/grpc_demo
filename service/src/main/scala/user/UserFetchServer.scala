package user

import com.protobuftest.models.v1.UserServiceGrpc
import io.grpc.Server
import io.grpc.netty.NettyServerBuilder

import java.util.logging.Logger
import scala.concurrent.ExecutionContext

object UserFetchServer {
  private val logger = Logger.getLogger(classOf[UserFetchServer].getName)

  def main(args: Array[String]): Unit = {
    val server = new UserFetchServer(ExecutionContext.global)
    server.start()
    server.blockUntilShutdown()
  }

  private val port = 9081
}

class UserFetchServer(executionContext: ExecutionContext) { self =>
  private[this] var server: Server = null

  private def start(): Unit = {
    server = NettyServerBuilder
      .forPort(UserFetchServer.port)
      .addService(UserServiceGrpc.bindService(new UserFetchService, executionContext))
      .build
      .start
    UserFetchServer.logger.info(
      "Server started, listening on " + UserFetchServer.port
    )
    sys.addShutdownHook {
      System.err.println(
        "*** shutting down gRPC server since JVM is shutting down"
      )
      self.stop()
      System.err.println("*** server shut down")
    }
  }

  private def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  private def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }
}
