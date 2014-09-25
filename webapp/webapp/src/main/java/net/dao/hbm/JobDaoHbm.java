package net.dao.hbm;

import net.dao.JobDao;
import net.domain.Job;

import org.apache.log4j.Logger;
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
	
	private Logger log = Logger.getLogger(JobDaoHbm.class);
	
	@Transactional
	public void saveJob(Job job) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.save(job);
		}
		catch (HibernateException e) {
			log.error("HibernateException. Method: saveJob: " + e.toString()
					+ ". Parameters: id = " + job.getId() + ", employer_id = "
					+ job.getEmployer().getId() + ", deadline = " + job.getDeadline()
					+ ", link = " + job.getLink());
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
			log.error("HibernateException. Method: deleteJob: " + e.toString()
					+ ". Parameters: id = " + job.getId() + ", employer_id = "
					+ job.getEmployer().getId() + ", deadline = " + job.getDeadline()
					+ ", link = " + job.getLink());			
			e.printStackTrace();
		}catch (Exception e) {
			log.error("Exception. Method: deleteJob: " + e.toString()
					+ ". Parameters: id = " + job.getId() + ", employer_id = "
					+ job.getEmployer().getId() + ", deadline = " + job.getDeadline()
					+ ", link = " + job.getLink());			
			e.printStackTrace();
		}		
	}
	
	@Transactional
	public Job getJobById(int id) {

		Session session = sessionFactory.getCurrentSession();	
		Job job = null;
		try {
			job = (Job)session.get(Job.class, id);
		}
		catch (Exception e) {
			log.error("Exception. Method: getJobById: " + e.toString()
					+ ". Parameters: id = " + id);	
			job = null;
		}
		return job;
	}

}
