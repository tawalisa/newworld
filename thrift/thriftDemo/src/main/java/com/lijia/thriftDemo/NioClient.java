package com.lijia.thriftDemo;

import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import tutorial.Calculator;

public class NioClient {
	

	public static void main(String[] args) {
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket("localhost", 9092);
			TProtocolFactory protocol = new TBinaryProtocol.Factory();
			Calculator.AsyncClient asyncClient = new Calculator.AsyncClient(protocol, clientManager, transport);
			System.out.println("Client calls .....");
			AsyncMethodCallback<Integer> resultHandler = new AsyncMethodCallback<Integer>() {

				@Override
				public void onComplete(Integer response) {
					System.out.println("return:" + response);
				}

				@Override
				public void onError(Exception exception) {
					exception.printStackTrace();
				}

			};
			asyncClient.add(1, 2, resultHandler);
			
//			AsyncMethodCallback<Void> callBack = new AsyncMethodCallback<Void>() {
//
//				@Override
//				public void onComplete(Void response) {
//					System.out.println("return: ping" );
//				}
//
//				@Override
//				public void onError(Exception exception) {
//					exception.printStackTrace();
//					
//				}
//				
//			};
//			asyncClient.ping(callBack );
			
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
