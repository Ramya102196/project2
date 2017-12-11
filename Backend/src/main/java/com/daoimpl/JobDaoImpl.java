package com.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.JobDao;
import com.model.Job;

@Repository
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionFactory;
	public void saveJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job); //insert
		}
       
	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job"); //select * from jobdetails
		return query.list();
	}

	
	public Job getJob(int jobId) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class,jobId); //select *from job where id=?
		return job;
	}

}
