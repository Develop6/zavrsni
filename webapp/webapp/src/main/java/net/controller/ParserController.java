package net.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.domain.Employer;
import net.domain.Job;
import net.http.HttpRequestHandling;
import net.parser.JobParser;
import net.service.EmployerDaoManager;
import net.service.JobDaoManager;
import net.util.Utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/welcome")
public class ParserController {
	
	private Logger log = Logger.getLogger(ParserController.class);
	
	@Autowired
	JobDaoManager jobDaoManager;
	
	@Autowired
	EmployerDaoManager employerDaoManager;	

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody void printWelcome(Model model) {
		
//		String na = System.getProperty("file.encoding"); 
		
		Job jobes = jobDaoManager.getJobById(243302);
		
		log.info("Executing sql: MESSAGE");
		log.debug("Executing sql: MESSAGE");
		log.error("Executing sql: MESSAGE");
		log.fatal("Executing sql: MESSAGE");
		
//		Hibernate.initialize(jobes.getCategories());
//		Iterator<Category> itr = jobes.getCategories().iterator();
//			
//		while(itr.hasNext()) {
//			Category cat = itr.next();
//			System.out.println(cat.getId());
//			System.out.println(cat.getName());
//			System.out.println();
//		}	
		
//		jobDaoManager.deleteJob(jobes);
		
		List<Job> allJobs = new ArrayList<Job>();	
		boolean isLastPage = false;
		int pageCount = 1;

		// goes through pages and collects job and employers ids and links
		while(!isLastPage) {
		
			String urlParameters = null;
			
			try {
				urlParameters = "?published=" + URLEncoder.encode("1", "UTF-8") +
						"&page=" + URLEncoder.encode(String.valueOf(pageCount), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	
			//pojedini poslovi
	//		String response = HttpRequestHandling.sendGet("http://m.moj-posao.net/Posao/243890/Java-Web-Developer-mz/Invitation/307677/", urlParameters);
	//		String response = HttpRequestHandling.sendGet("http://m.moj-posao.net/Posao/243855/Suradnik-u-veleprodaji-mz/", urlParameters);
	//		String response = HttpRequestHandling.sendGet("http://m.moj-posao.net/Posao/242927/Tehnicar-elektro-struke-mz/", urlParameters);
	//		String response = HttpRequestHandling.sendGet("http://m.moj-posao.net/Posao/243949/Financijski-knjigovodja-mz/", urlParameters);
	//		String response = HttpRequestHandling.sendGet("http://m.moj-posao.net/Posao/244079/Monter-ALU-i-PVC-stolarije-mz/", urlParameters);
			//http://www.moj-posao.net/Posao/243949/Financijski-knjigovodja-mz/
			
			// svi poslovi
			String response = null;
			try {
				response = HttpRequestHandling.sendGet("http://www.moj-posao.net/Pretraga-Poslova/", urlParameters);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JobParser jp = new JobParser(response);			
			
			if(jp.featuredJobsExist()) {
				allJobs = jp.getFeaturedJobIdAndLink(allJobs);	
			}

			isLastPage = jp.checkIfLastPage();
			
			if(isLastPage) {
				if (jp.regionalJobFirst()) {
					break;
				} else {
					allJobs = jp.getJobIdAndLink(allJobs);
				}
			}
			else {
				allJobs = jp.getJobIdAndLink(allJobs);
			}
			
			pageCount++;
		}
		
		JobParser jp = new JobParser("");
		String response = null;
		
		// iterates through jobs and saves them into database if they doesn't exist
		int count = 0;
		for(Job job : allJobs) {
			
			// if job doesn't exist in database
			if(jobDaoManager.getJobById(job.getId()) == null) {

				Employer employer = null;
				
				// if employer id is undefined on page
				if(job.getEmployer().getId() == 0 || job.getEmployer().getId() >= 10000000) {
					employer = employerDaoManager.getEmployerByName(job.getEmployer().getName());

					// if employer exists in database
					if(employer != null) {
						job.setEmployer(employer);
					}
					else {
						// employer doesn't exist in database
						do {
							int employerId = Utils.generateRandomNumber(10000000, 99999999);
							employer = employerDaoManager.getEmployerById(employerId);
							if(employer == null) {
								job.getEmployer().setId(employerId);
								employerDaoManager.saveEmployer(job.getEmployer());
								break;
							}							
						} while (employer != null);
					}
				}
				else {
					
					employer = employerDaoManager.getEmployerById(job.getEmployer().getId());
					
					// if employer exists in database
					if(employer != null) {
						job.setEmployer(employer);
					}
					else {
						try {
							response = HttpRequestHandling.sendGet(job.getEmployer().getLink(), "");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						jp = new JobParser(response);						
						String address = jp.getEmployerAddress();
						
						job.getEmployer().setAddress(address);
						employerDaoManager.saveEmployer(job.getEmployer());
					}
				}
			
				try {
					response = HttpRequestHandling.sendGet(job.getLink(), "");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jp = new JobParser(response);
				
				String title = jp.getTitle(); 
				if(title == null || title.equals("")) {
					continue;
				}
				
				Date deadline = jp.getApplicationDeadline();
				if (deadline == null) {
					continue;
				}
				
				job.setTitle(title);
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
				
				jobDaoManager.saveJob(job);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				count++;				
			}
		}
		
		System.out.println();
		
//		Employer employer = new Employer();
//		employer.setId(1);
//		
//		Category category = new Category();
//		category.setId(CategoryEnum.PRAVO.getId());
//		Set<Category> categories = new HashSet<Category>();
//		categories.add(category);
//		
//		County county = new County();
//		county.setId(CountyEnum.getId("Međimurska"));
//		Set<County> counties = new HashSet<County>();
//		counties.add(county);
//		
//		JobType jobType = new JobType();
//		jobType.setId(JobTypeConstants.PART_TIME_ID);
//		Set<JobType> jobTypes = new HashSet<JobType>();
//		jobTypes.add(jobType);
//		
//		Qualification qualification = new Qualification();
//		qualification.setId(JobQualificationConstants.MBA_ID);
//		Set<Qualification> qualifications = new HashSet<Qualification>();
//		qualifications.add(qualification);
//		
//		Date date = new Date();
//		
//		Job job = new Job();
//		job.setEmployer(employer);
//		job.setName("poslic");
//		job.setExpirationDate(date);
//		job.setId(4);
//		job.setCategories(categories);
//		job.setCounties(counties);
//		job.setJobTypes(jobTypes);
//		job.setQualifications(qualifications);
//		
//		
//		testDaoManager.testMethod(job);
//		
//		return null;
	}
}
