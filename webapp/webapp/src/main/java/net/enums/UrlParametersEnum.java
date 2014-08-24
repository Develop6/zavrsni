package net.enums;

public enum UrlParametersEnum {

	PUBLISHED		("published"),
	CATEGORY		("category"),
	AREA			("area"),
	JOB_TYPE		("job_type"),
	QUALIFICATION	("professional_qualification");
	
	private String parameter;
	
	UrlParametersEnum (String parameter) {
		this.parameter = parameter;
	}

	public String getParameter() {
		return parameter;
	}
}
