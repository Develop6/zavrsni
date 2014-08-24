package net.service;

import net.dao.JobDao;
import net.domain.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobDaoManager {

	@Autowired
	JobDao jobDao;
	
	public void saveJob(Job job) {
		jobDao.saveJob(job);
	}
	
	public void deleteJob(Job job) {
		jobDao.deleteJob(job);
	}
	
	public Job getJobById(int id) {
		return jobDao.getJobById(id);
	}
}
