package com.dao;

import java.util.List;

import com.model.Friend;
import com.model.User;

public interface FriendDao {
	List<User> suggestedUsersList(String username);

	void addFriendRequest(Friend friend);
	//select * from friend where toid=? and status='P'
	List<Friend> pendingRequests(String username); //value for toid-> login id

void updatePendingRequest(Friend friend); //update/delete
	List<User> listOfFriends(String username); //username is login id
 
}
