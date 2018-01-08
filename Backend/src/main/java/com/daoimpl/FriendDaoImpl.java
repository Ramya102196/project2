package com.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.FriendDao;
import com.model.Friend;
import com.model.User;
@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<User> suggestedUsersList(String username) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("(select * from userdetails where username in "
				+ " (select username from userdetails where username!=? "
				+ " minus "
				+ "(select fromId from frienddetails where toId=? "
				+ " union select toId from  frienddetails where fromId=? ))) ");
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		return suggestedUsers;
	}

	
	public void addFriendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.save(friend); //insert into friend values(fromId,status,toId)
		
	}

public List<Friend> pendingRequests(String username) {
		
Session session=sessionFactory.getCurrentSession();
//select * from friend where toId='uma' and status='P'
		Query query=session.createQuery("from Friend where toId=? and status=?");
				query.setString(0, username);
				query.setCharacter(1, 'P');
				List<Friend> pendingRequests=query.list();
				return pendingRequests;
		}
	//friend->friend.status='A' for accept,friend.status='D' for delete
	public void updatePendingRequest(Friend friend)
	{
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
				session.update(friend); //update friend set status='A' where id=?
	
	else
		session.delete(friend); //delete friend where id=?
	}

	public List<User> listOfFriends(String username) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query1=session.createSQLQuery("select * from userdetails where username in "
				+ "(select toId from frienddetails where fromId=? and status='A' "
				+ "union "
				+ "select fromId from frienddetails where toId=? and status='A') ");
		query1.setString(0, username);
		query1.setString(1, username);
		query1.addEntity(User.class);
		List<User> friends=query1.list();
		return friends;
	}
		

	
}
