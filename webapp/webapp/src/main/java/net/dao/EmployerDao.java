package net.dao;

import net.domain.Employer;

public interface EmployerDao {

	public Employer getEmployerByName (String name);
	public Employer getEmployerById (int id);
	public void saveEmployer(Employer employer);
}
