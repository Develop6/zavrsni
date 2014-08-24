package net.dao.hbm;

import java.util.List;

import net.dao.EmployerDao;
import net.domain.Employer;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployerDaoHbm implements EmployerDao{
	
	/**
	 * SessionFactory.
	 */
	@Autowired
	private SessionFactory sessionFactory;	

	public Employer getEmployerByName (String name) {
		
		Session session = sessionFactory.getCurrentSession();
		List<Employer> employers = null;
		try {
			Criteria searchCriteria = session.createCriteria(Employer.class)
					.setResultTransformer(
							CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			searchCriteria.add(Restrictions.eq("name", name));
			employers = searchCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(employers.size() == 0) {
			return null;
		}
		return employers.get(0);
	}
	
	public Employer getEmployerById (int id) {
		
		Session session = sessionFactory.getCurrentSession();
		return (Employer) session.get(Employer.class, id);
	}	
	
	public void saveEmployer(Employer employer) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.save(employer);
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
