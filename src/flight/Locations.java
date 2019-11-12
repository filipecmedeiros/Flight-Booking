package flight;

import java.util.EnumSet;

public enum Locations {
	RECIFE, MACEIÓ, JOÃO_PESSOA, 
	SALVADOR, ARACAJU, FORTALEZA,
	TERESINA, SÃO_LUÍS,  NATAL;

	
	public static void print() {
		System.out.println("====================================");
		EnumSet.allOf(Locations.class).forEach(location -> System.out.println(location));
		System.out.println("====================================");
	}
	
	@Override
	public String toString() {
        switch(this){
	        case RECIFE:
	            return "RECIFE";
	        case MACEIÓ:
	            return "MACEIÓ";
	        case JOÃO_PESSOA:
	            return "JOÃO PESSOA";
	        case SALVADOR:
	        	return "SALVADOR";
	        case ARACAJU:
	        	return "ARACAJU";
	        case FORTALEZA:
	        	return "FORTALEZA";
	        case TERESINA:
	        	return "TERESINA";
	        case SÃO_LUÍS:
	        	return "SÃO LUÍS";
	        case NATAL:
	        	return "NATAL";
        }
        return null;
	}

}
