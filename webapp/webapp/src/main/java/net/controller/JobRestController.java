package net.controller;

import java.util.List;

import net.domain.Job;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/jobRest")
public class JobRestController {

	@RequestMapping(method = RequestMethod.GET, value = "/jobs")
	public @ResponseBody
	List<Job> getJobs(@RequestParam String qualification,
			@RequestParam String startDate, @RequestParam(required=false) String endDate) {

		System.out.println("usao je");
		return null;
	}
}
