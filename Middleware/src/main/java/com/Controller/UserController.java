package com.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserDao;
import com.model.ErrorClazz;
import com.model.User;

@Controller

public class UserController {
	@Autowired
private UserDao userDao;
	
	public UserController()
	{
		System.out.println("UserController is Executed Successfully");
	}
//client - Angular JS client
//User - in JSON
//convert JSON to Java Object
//? any type, for Success type is User , for error type is ErrorClazz


@RequestMapping(value="/registeruser",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody User user)
{
try{
	if(!userDao.isUsernameValid(user.getUsername()))//duplicate Username
	{
		ErrorClazz error=new ErrorClazz(2,"Username already exists..please try different username");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
	}
	if(!userDao.isEmailidValid(user.getEmailid()))
	{
		ErrorClazz error=new ErrorClazz(3,"Emailid is already exists..try different emailid");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
	}
	userDao.registerUser(user);
}
catch(Exception e)
{
	ErrorClazz error=new ErrorClazz(1,"Unable to register user details");
	return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	//failure - response.data=error ,response.status=500
	
}

return new ResponseEntity<User>(user,HttpStatus.OK);
}
@RequestMapping(value="/login",method=RequestMethod.POST)
public ResponseEntity<?> login(@RequestBody User user,HttpSession session)
{
	User validUser=userDao.login(user);
	if(validUser==null){
		ErrorClazz errorclazz=new ErrorClazz(4,"invalid username/password");
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED); //ErrorClazz,401
	}
	
	else
	{
		validUser.setOnline(true);
		session.setAttribute("username",validUser.getUsername());
		userDao.updateUser(validUser);
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
	}
	
	
}
@RequestMapping(value="/logout",method=RequestMethod.GET)
public ResponseEntity<?> logout(HttpSession session)
{
String username=(String)session.getAttribute("username");
if(username==null)//without login
{
	ErrorClazz error=new ErrorClazz(5,"Unauthorized acess");
	return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //401-> login.html
}
User user=userDao.getUserByUsername(username); //select * from user where username=?
user.setOnline(false);
userDao.updateUser(user);  //update User set online=false where username=?
session.removeAttribute("username");
session.invalidate();
return new ResponseEntity<Void>(HttpStatus.OK);
		
	
}
@RequestMapping(value="/getuser",method=RequestMethod.GET)
public ResponseEntity<?> getUser(HttpSession session)
{
	String username=(String)session.getAttribute("username");
if(username==null)	//without login
{
	ErrorClazz error=new ErrorClazz(5,"Unauthorized acess");
	return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
}
 User user=userDao.getUserByUsername(username); //select from User where username=?
 return new ResponseEntity<User>(user,HttpStatus.OK);
}
@RequestMapping(value="/edituserprofile",method=RequestMethod.PUT)
public ResponseEntity<?> editUserProfile(@RequestBody User user,HttpSession session)
{
	String username=(String)session.getAttribute("username");
	if(username==null)	//without login
	{
		ErrorClazz error=new ErrorClazz(5,"Unauthorized acess");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	try{
	userDao.updateUser(user);
	}
	catch(Exception e)
	{
		ErrorClazz error=new ErrorClazz(6,e.getMessage());
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<User>(user,HttpStatus.OK);
	
}
}
