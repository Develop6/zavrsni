package net.dao;

import net.domain.Job;

import org.springframework.stereotype.Repository;

@Repository
public interface JobDao {
	
	public void saveJob(Job job);
	public void deleteJob(Job job);
	public Job getJobById(int id);
}
