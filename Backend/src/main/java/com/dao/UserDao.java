package com.dao;

import com.model.User;

public interface UserDao {
	void registerUser(User user);
	boolean isEmailidValid(String emailid);
	boolean isUsernameValid(String username);
	User login(User user);
	void updateUser(User user);
	User getUserByUsername(String username);
	
	

}
