package net.enums;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public enum CountyEnum {

	ZAGREB_I_ZAGREBACKA					(2,"Zagreb i zagrebačka"),
	KRAPINSKO_ZAGORSKA					(3,"Krapinsko-zagorska"),
	SISACKO_MOSLAVACKA					(4,"Sisačko-moslavačka"),
	KARLOVACKA							(5,"Karlovačka"),
	VARAZDINSKA							(6,"Varaždinska"),
	KOPRIVNICKO_KRIZEVACKA				(7,"Koprivničko-križevačka"),
	BJELOVARSKO_BILOGORSKA				(8,"Bjelovarsko-bilogorska"),
	PRIMORSKO_GORANSKA					(9,"Primorsko-goranska"),
	LICKO_SENJSKA						(10,"Ličko-senjska"),
	VIROVITICKO_PODRAVSKA				(11,"Virovitičko-podravska"),
	POZESKO_SLAVONSKA					(12,"Požeško-slavonska"),
	BRODSKO_POSAVSKA					(13,"Brodsko-posavska"),
	ZADARSKA							(14,"Zadarska"),
	OSJECKO_BARANJSKA					(15,"Osječko-baranjska"),
	SIBENSKO_KNINSKA					(16,"Šibensko-kninska"),
	VUKOVARSKO_SRIJEMSKA				(17,"Vukovarsko-srijemska"),
	SPLITSKO_DALMATINSKA				(18,"Splitsko-dalmatinska"),
	ISTARSKA							(19,"Istarska"),
	DUBROVACKO_NERETVANSKA				(20,"Dubrovačko-neretvanska"),
	MEDJIMURSKA							(21,"Međimurska"),
	INOZEMSTVO							(22,"Inozemstvo");
	
	private final int id;
	private final String name;
	
	private static BidiMap countyMap;
	
	CountyEnum (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
    public static String getName(Integer id) {
        if (countyMap == null) {
            initMapping();
        }
        return countyMap.get(id).toString();
    }
    
    public static int getId(String name) {
        if (countyMap == null) {
            initMapping();
        }
        return (int)countyMap.getKey(name);
    }    
 
    private static void initMapping() {
    	countyMap = new TreeBidiMap();
        for (CountyEnum ce : values()) {
        	countyMap.put(ce.id, ce.name);
        }
    }
    
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}    
}
