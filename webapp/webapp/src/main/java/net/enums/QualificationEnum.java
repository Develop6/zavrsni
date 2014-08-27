package net.enums;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public enum QualificationEnum {

	NKV			(1, "NKV"),
	PKV			(2, "PKV"),
	KV			(3, "KV"),
	VKV			(4, "VKV"),
	SSS			(5, "Srednja stručna sprema"),
	VSH			(6, "Viša stručna sprema"),
	BAC			(7, "Stručni prvostupnik"),
	UNIVBACC	(8, "Sveučilišni prvostupnik"),
	VSS			(9, "Visoka stručna sprema"),
	MBA			(10, "MBA"),
	MAG			(11, "Magisterij"),
	MAGS		(12, "Magistar struke"),
	DOC			(13, "Doktorat");
	
	private final int id;
	private final String name;
	
	private static BidiMap jobTypeMap;
	
	QualificationEnum (int id, String name) {
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
        for (QualificationEnum ce : values()) {
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
