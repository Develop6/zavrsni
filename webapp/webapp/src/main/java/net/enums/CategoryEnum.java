package net.enums;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public enum CategoryEnum {
	
	ADMINISTRATIVNA_ZANIMANJA					(1,"Administrativna zanimanja"),
	ARHITEKTURA									(2,"Arhitektura"),
	BANKARSTVO									(3,"Bankarstvo"),
	BRIGA_O_LJEPOTI_SPORT						(4,"Briga o ljepoti, sport"),
	DIZAJN_I_UMJETNOST							(5,"Dizajn i umjetnost"),
	ELEKTROTEHNIKA								(6,"Elektrotehnika"),
	FARMACEUTIKA_I_BIOTEHNOLOGIJA				(7,"Farmaceutika i biotehnologija"),
	EKONOMIJA_FINANCIJE_I_RACUNOVODSTVO			(8,"Ekonomija, financije i računovodstvo"),
	GRADITELJSTVO_GEODEZIJA_GEOOGIJA			(9,"Graditeljstvo, geodezija, geologija"),
	INSTALACIJE_ODRAVANJE_I_POPRAVCI			(10,"Instalacije, održavanje i popravci"),
	IT_TELEKOMUNIKACIJE							(11,"IT, telekomunikacije"),
	LJUDSKI_RESURSI								(12,"Ljudski resursi"),
	MARKETING_PR_I_MEDIJI						(13,"Marketing, PR i mediji"),
	OBRAZOVANJE_I_ZNANOST						(14,"Obrazovanje i znanost"),
	OSTALO										(15,"Ostalo"),
	POLJOPRIVREDA_SUMARSTVO_RIBARSTVO			(16,"Poljoprivreda, šumarstvo, ribarstvo"),
	PRAVO										(17,"Pravo"),
	PRODAJA_TRGOVINA							(18,"Prodaja (Trgovina)"),
	PROIZVODNJA_I_ZANATSKE_USLUGE				(19,"Proizvodnja i zanatske usluge"),
	PROMET_TRANSPORT_POMORSTVO					(20,"Promet, transport, pomorstvo"),
	SIGURNOST_I_ZASTITA							(21,"Sigurnost i zaštita"),
	SKLADISTENJE_I_LOGISTIKA					(22,"Skladištenje i logistika"),
	SKRB_O_DJECI_I_STARIJIMA					(23,"Skrb (o djeci, starijima...)"),
	STROJARSTVO_I_BRODOGRADNJA					(24,"Strojarstvo i brodogradnja"),
	TURIZAM_I_UGOSTITELJSTVO					(25,"Turizam i ugostiteljstvo"),
	ZDRAVSTVO_SOCIJALNI_RAD						(26,"Zdravstvo, socijalni rad"),
	MANAGEMENT									(27,"Management"),
	DRŽAVNA_SLUŽBA_I_NEPROFITNE_ORGANIZACIJE	(28,"Državna služba i neprofitne organizacije");	
	
	private final int id;
	private final String name;
	
	private static BidiMap categoryMap;
	
	CategoryEnum (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
    public static String getName(Integer id) {
        if (categoryMap == null) {
            initMapping();
        }
        return categoryMap.get(id).toString();
    }
    
    public static int getId(String name) {
        if (categoryMap == null) {
            initMapping();
        }
        return (int)categoryMap.getKey(name);
    }    
 
    private static void initMapping() {
    	categoryMap = new TreeBidiMap();
        for (CategoryEnum ce : values()) {
        	categoryMap.put(ce.id, ce.name);
        }
    }
    
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}    
}
