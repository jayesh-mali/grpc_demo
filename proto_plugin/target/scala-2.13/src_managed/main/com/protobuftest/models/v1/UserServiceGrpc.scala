package com.protobuftest.models.v1

object UserServiceGrpc {
  val METHOD_FETCH_USER: _root_.io.grpc.MethodDescriptor[com.protobuftest.models.v1.UserRequest, com.protobuftest.models.v1.UserResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "FetchUser"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[com.protobuftest.models.v1.UserRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[com.protobuftest.models.v1.UserResponse])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(com.protobuftest.models.v1.UserProto.javaDescriptor.getServices().get(0).getMethods().get(0)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("UserService")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(com.protobuftest.models.v1.UserProto.javaDescriptor))
      .addMethod(METHOD_FETCH_USER)
      .build()
  
  trait UserService extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = UserService
    def fetchUser(request: com.protobuftest.models.v1.UserRequest): scala.concurrent.Future[com.protobuftest.models.v1.UserResponse]
  }
  
  object UserService extends _root_.scalapb.grpc.ServiceCompanion[UserService] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[UserService] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.protobuftest.models.v1.UserProto.javaDescriptor.getServices().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = com.protobuftest.models.v1.UserProto.scalaDescriptor.services(0)
    def bindService(serviceImpl: UserService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_FETCH_USER,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.protobuftest.models.v1.UserRequest, com.protobuftest.models.v1.UserResponse] {
          override def invoke(request: com.protobuftest.models.v1.UserRequest, observer: _root_.io.grpc.stub.StreamObserver[com.protobuftest.models.v1.UserResponse]): _root_.scala.Unit =
            serviceImpl.fetchUser(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  trait UserServiceBlockingClient {
    def serviceCompanion = UserService
    def fetchUser(request: com.protobuftest.models.v1.UserRequest): com.protobuftest.models.v1.UserResponse
  }
  
  class UserServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[UserServiceBlockingStub](channel, options) with UserServiceBlockingClient {
    override def fetchUser(request: com.protobuftest.models.v1.UserRequest): com.protobuftest.models.v1.UserResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_FETCH_USER, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): UserServiceBlockingStub = new UserServiceBlockingStub(channel, options)
  }
  
  class UserServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[UserServiceStub](channel, options) with UserService {
    override def fetchUser(request: com.protobuftest.models.v1.UserRequest): scala.concurrent.Future[com.protobuftest.models.v1.UserResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_FETCH_USER, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): UserServiceStub = new UserServiceStub(channel, options)
  }
  
  def bindService(serviceImpl: UserService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = UserService.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): UserServiceBlockingStub = new UserServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): UserServiceStub = new UserServiceStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.protobuftest.models.v1.UserProto.javaDescriptor.getServices().get(0)
  
}