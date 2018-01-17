package com.configuration;

/*import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker //enable broker based stomp messaging
//autodecting annoatation @MessageMapping,@SubscribeMapping
@ComponentScan(basePackages="com")

public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//enablesimplebroker destination prefixes - by spring controller to send message to client 
		//to send data from server to client
		//topic - to notify the newly joined username
		//queue - to send chat message
		// communication direction- from server to client
		//server users destination /queue,/topic to send messages to the client
		//client will receive the message by subscribing $scope.subscribe("/topic/join",...)
		registry.enableSimpleBroker("/queue/" , "/topic/"); //server to client
		//client to server - destionation prefix as  /app
		//in client side .. send("/app/join", ....)
		registry.setApplicationDestinationPrefixes("/app"); //client to server
	}

	
	public void registerStompEndpoints(StompEndpointRegistry registry) {
//js stomp.over("../chatmodule")
		registry.addEndpoint("/chatmodule").withSockJS();
		
	}

}
*/


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;




@Configuration
@EnableWebSocketMessageBroker  // enable broker based stomp messaging
//autodecting annoatation @MessageMapping,@SubscribeMapping
@ComponentScan(basePackages="com")
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {


	

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("REGISTER STOMP ENDPOINTS...");
		registry.addEndpoint("/chatmodule").withSockJS();
	}

	
	public void configureMessageBroker(MessageBrokerRegistry  configurer) {
		System.out.println("CONFIGURE MESSAGE BROKER REGISTRY");
		//enablesimplebroker destination prefixes - by spring controller to send message to client 
				//to send data from server to client
				//topic - to notify the newly joined username
				//queue - to send chat message
				// communication direction- from server to client
				//server users destination /queue,/topic to send messages to the client
				//client will receive the message by subscribing $scope.subscribe("/topic/join",...)
		configurer.enableSimpleBroker("/queue/", "/topic/");
		//client to server - destionation prefix as  /app
				//in client side .. send("/app/join", ....)
		configurer.setApplicationDestinationPrefixes("/app");
	}

	
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}


	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}


}