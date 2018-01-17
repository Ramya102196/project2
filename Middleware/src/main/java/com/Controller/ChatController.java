/*package com.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.model.Chat;

@Controller
public class ChatController {
	//send data to the client
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	//list of username who has joined the chat room
	private List<String> users=new ArrayList<String>();
	//$stompClient.subscribeMapping("/app/join/tom")
	@SubscribeMapping("/join/{username}")
	public List<String> join(@DestinationVariable String username)
{
if(!users.contains(username))
	users.add(username);
    messagingTemplate.convertAndSend("/topic/join",username);//all the other who already joined the chat room
return users; //newly joined user
}
	@MessageMapping("/chat")
	public void chatMessage(Chat chat){ //to,from,message 
		//group chat
		if(chat.getTo().equals("all")){
			messagingTemplate.convertAndSend("/queue/chats",chat);
		}
		//from:uma to:tom
		else
		{
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getTo(),chat);
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getFrom(),chat);
		}
		
		}
	
}*/

package com.Controller;



import java.util.ArrayList;

import java.util.List;


import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.handler.annotation.DestinationVariable;


import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;


import org.springframework.messaging.simp.annotation.SubscribeMapping;



import org.springframework.stereotype.Controller;

import com.model.Chat;


@Controller
public class ChatController {
	//send data to the client

	private static final Log logger = LogFactory.getLog(ChatController.class);

	private final SimpMessagingTemplate messagingTemplate;
	//list of username who has joined the chat room

	private List<String> users = new ArrayList<String>();


	@Autowired

	public ChatController(SimpMessagingTemplate messagingTemplate) {

		this.messagingTemplate = messagingTemplate;

	}

	@SubscribeMapping("/join/{username}")

	public List<String> join(@DestinationVariable("username") String username) {
        

		 System.out.println("username in sockcontroller" + username);
		 
		 if(!users.contains(username)) {
				users.add(username);
			}


		System.out.println("====JOIN==== " + username);

		// notify all subscribers of new user

		messagingTemplate.convertAndSend("/topic/join", username);
		//all the other who already joined the chat room

		return users;//newly joined user

	}

	@MessageMapping(value = "/chat")

	//group chat
	public void chatReveived(Chat chat) {


		if ("all".equals(chat.getTo())) {

			System.out.println("IN CHAT REVEIVED " + chat.getMessage() + " " + chat.getFrom() + " to " + chat.getTo());

			messagingTemplate.convertAndSend("/queue/chats", chat);

		}

		else {

			System.out.println("CHAT TO " + chat.getTo() + " From " + chat.getFrom() + " Message " + chat.getMessage());

			messagingTemplate.convertAndSend("/queue/chats/" + chat.getTo(), chat);

			messagingTemplate.convertAndSend("/queue/chats/" + chat.getFrom(), chat);

		}

	}

}