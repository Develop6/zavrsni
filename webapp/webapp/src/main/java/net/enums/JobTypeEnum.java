package net.enums;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public enum JobTypeEnum {

	FULL_TIME				(1, "Stalni radni odnos"),
	CONCTRACT				(2, "Na određeno vrijeme"),
	PART_TIME				(3, "Honorarno"),
	STUDENT					(4, "Studentski ugovor"),
	INTERNSHIP				(5, "Praksa"),
	VOLUNTEERING			(6, "Volontiranje"),
	SEASONAL				(7, "Sezonski"),
	PROFESSIONAL_TRAINING	(8, "Stručno osposobljavanje");
	
	private final int id;
	private final String name;
	
	private static BidiMap jobTypeMap;
	
	JobTypeEnum (int id, String name) {
		this.id = id;
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
	
}
