package net.service;

import net.dao.EmployerDao;
import net.domain.Employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerDaoManager {

	@Autowired
	EmployerDao employerDao;
	
	public Employer getEmployerByName(String name) {
		return employerDao.getEmployerByName(name);
	}
	
	public Employer getEmployerById(int id) {
		return employerDao.getEmployerById(id);
	}	
	
	public void saveEmployer(Employer employer) {
		employerDao.saveEmployer(employer);
	}
}
