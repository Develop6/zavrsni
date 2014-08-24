package net.enums;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public enum JobTypeEnum {

	FULL_TIME				(1,"full_time", "Stalni radni odnos"),
	CONCTRACT				(2,"contract", "Na određeno vrijeme"),
	PART_TIME				(3,"part_time", "Honorarno"),
	STUDENT					(4,"student", "Studentski ugovor"),
	INTERNSHIP				(5,"internship", "Praksa"),
	VOLUNTEERING			(6,"volunteering", "Volontiranje"),
	SEASONAL				(7,"seasonal", "Sezonski"),
	PROFESSIONAL_TRAINING	(8,"professional_training", "Stručno osposobljavanje");
	
	private final int id;
	private final String label;
	private final String name;
	
	private static BidiMap jobTypeMap;
	
	JobTypeEnum (int id, String label, String name) {
		this.id = id;
		this.label = label;
		this.name = name;
	}
	
    public static String getName(Integer id) {
        if (jobTypeMap == null) {
            initMapping();
        }
        return jobTypeMap.get(id).toString();
    }
    
    public static int getId(String name) {
        if (jobTypeMap == null) {
            initMapping();
        }
        return (int)jobTypeMap.getKey(name);
    }    
 
    private static void initMapping() {
    	jobTypeMap = new TreeBidiMap();
        for (JobTypeEnum ce : values()) {
        	jobTypeMap.put(ce.id, ce.name);
        }
    }
    
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}    

	public String getLabel() {
		return label;
	}	
	
}
