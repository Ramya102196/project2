package com.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.BlogPostDao;
import com.dao.UserDao;
import com.model.BlogPost;
import com.model.ErrorClazz;
import com.model.User;

@RestController
public class BlogPostContoller {
	@Autowired
	private BlogPostDao blogPostDao;
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="/saveblog",method=RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
	{
		String username=(String) session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //401
			}
		User user=userDao.getUserByUsername(username); //select * from user when usernmae="uma"
				blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(user); //FK column posted_by username ['ruba']
		try{
			blogPostDao.saveBlogPost(blogPost);
		}catch(Exception e)
		{
			ErrorClazz error=new ErrorClazz(6,"Unable to insert blog details" +e.getMessage());
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR); //500
		}
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
@RequestMapping(value="/getblogs/{approved}",method=RequestMethod.GET)
public ResponseEntity<?> getBlogs(@PathVariable int approved,HttpSession session)
{
	String username=(String) session.getAttribute("username");
	if(username==null)
	{
		ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED); //401
		}
	
	if(approved==0) //list of blog waiting for approval
	{
		User user=userDao.getUserByUsername(username);
	if(!user.getRole().equals("ADMIN")){
			ErrorClazz error=new ErrorClazz(7,"acess denied");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
}
			List<BlogPost> blogPosts=blogPostDao.getBlogs(approved);
	return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
		
}
}
