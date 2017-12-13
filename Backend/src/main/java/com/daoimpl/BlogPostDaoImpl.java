package com.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BlogPostDao;
import com.model.BlogPost;
@Repository
@Transactional

public class BlogPostDaoImpl implements BlogPostDao {
@Autowired
	private SessionFactory sessionFactory;

public void saveBlogPost(BlogPost blogPost)
{
	Session session=sessionFactory.getCurrentSession();
	session.save(blogPost);
}

public List<BlogPost> getBlogs(int approved) {
	Session session=sessionFactory.getCurrentSession();
	Query query=session.createQuery("from BlogPost where approved=" + approved);
    return query.list();
}

}
