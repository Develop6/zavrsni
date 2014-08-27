package net.dao.hbm;

import net.dao.JobDao;
import net.domain.Job;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JobDaoHbm implements JobDao{

	/**
	 * SessionFactory.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void saveJob(Job job) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.save(job);
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void deleteJob (Job job) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.delete(job);
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Transactional
	public Job getJobById(int id) {

		Session session = sessionFactory.getCurrentSession();	
		Job job = (Job)session.get(Job.class, id);
		return job;
	}

}
