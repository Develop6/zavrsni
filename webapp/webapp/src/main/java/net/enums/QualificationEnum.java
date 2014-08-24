package net.enums;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public enum QualificationEnum {

	NKV			(1,"NKV", "NKV"),
	PKV			(2,"PKV", "PKV"),
	KV			(3,"KV", "KV"),
	VKV			(4,"VKV", "VKV"),
	SSS			(5,"SSS", "Srednja stručna sprema"),
	VSH			(6,"VSH", "Viša stručna sprema"),
	BAC			(7,"BAC", "Stručni prvostupnik"),
	UNIVBACC	(8,"UNIVBACC", "Sveučilišni prvostupnik"),
	VSS			(9,"VSS", "Visoka stručna sprema"),
	MBA			(10,"MBA", "MBA"),
	MAG			(11,"MAG", "Magisterij"),
	MAGS		(12,"MAGS", "Magistar struke"),
	DOC			(13,"DOC", "Doktorat");
	
	private final int id;
	private final String label;
	private final String name;
	
	private static BidiMap jobTypeMap;
	
	QualificationEnum (int id, String label, String name) {
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
    
	public String getLabel() {
		return label;
	}		
	
}
