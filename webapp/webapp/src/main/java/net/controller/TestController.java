package net.controller;

import java.io.IOException;
import java.util.Date;

import net.domain.Employer;
import net.domain.Job;
import net.http.HttpRequestHandling;
import net.parser.JobParser;
import net.service.EmployerDaoManager;
import net.service.JobDaoManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="/test")
public class TestController {
	
	private Logger log = Logger.getLogger(ParserController.class);
	
	@Autowired
	JobDaoManager jobDaoManager;
	
	@Autowired
	EmployerDaoManager employerDaoManager;	

	@RequestMapping(method = RequestMethod.GET)	
	public @ResponseBody void test(Model model) {
		
		String response = null;
		try {
			response = HttpRequestHandling.sendGet("http://www.moj-posao.net/Posao/244713/Dipl-ing-brodogradnje-mz/", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JobParser jp = new JobParser(response);
		
		Date date = new Date();
		
		Job job = new Job();
		
		job.setTitle(jp.getTitle());
		job.setDeadline(jp.getApplicationDeadline());
		job.setDescription(jp.getDescription());
		job.setConditions(jp.getConditions());
		job.setQualifications(jp.getQualification());
		job.setYearsOfExperience(jp.getYearsOfExperience());
		job.setLanguages(jp.getLanguages());
		job.setSkills(jp.getSkills());
		job.setDrivingLicence(jp.getDrivingLicense());
		job.setEmployerOffer(jp.getEmployeeOffer());
		job.setJobTypes(jp.getJobTypes());
		job.setCategories(jp.getCategories());
		job.setCounties(jp.getCounties());
		job.setPublishDate(date);			
	}

	@RequestMapping(method = RequestMethod.GET, value="/testSaveEmp")
	public @ResponseBody void testSaveEmp(Model model) {
		
		Employer employer = new Employer();	
		
		employer.setAddress("feswfwef");
		employer.setId(1000000000);
		employer.setLink("vwvwevwv");
		employer.setName("Ime");
		
		employerDaoManager.saveEmployer(employer);
	}	

}
