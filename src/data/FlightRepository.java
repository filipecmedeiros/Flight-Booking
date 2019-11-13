package data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import flight.Flight;
import utils.Utils;

public class FlightRepository extends AbstractRepository <Flight> implements Serializable{

	private String counter;
	
	public FlightRepository () {
		super();
		setCounter("AAA0000");
	}
	
	public String getCounter() {
		return counter;
	}
	
	public void setCounter(String newCounter) {
		counter = newCounter;
	}
	
	private static String moveCounter(String counter, int position) {
		char [] aux = counter.toCharArray();
		if ((aux[position] >='0' && aux[position]<='8') || 
				(aux[position]>='A' && aux[position]<='Y')) {
			aux[position] = (char) (aux[position]+1);
		}
		else if (aux[position]=='9') {
			counter = moveCounter(counter, position-1);
			aux = counter.toCharArray();
			aux[position]='0';
		}
		else if (aux[position]=='Z') {
			aux[position]='A';
		}
		return  String.valueOf(aux);
	}
	
	private String generateCode() {
		String code = moveCounter(getCounter(), 6); //6 is the last position
		return code;
	}
	
	@Override
	public void create(Flight f) {
		f.setCode(getCounter());
		setCounter(generateCode());
		super.create(f);
	}
	
	public void search(String origin, String destiny, String date) {
		Date parsedDate = null;
		origin = origin.toUpperCase();
		destiny = destiny.toUpperCase();
		if (Utils.isValidDate(date, "00:00")) {
			parsedDate = Utils.parseDate(date, "00:00");
			
			System.out.println("Vôos encontrados:");
			for (int i=0; i<getRep().size(); i++) {
				Flight f = getRep().get(i); 
				if (f.getOrigin().equals(origin) && f.getDestiny().equals(destiny) 
						&& Utils.sameDay(f.getDate(), parsedDate) &&
						f.getNseats()>0 && f.getStatus().equals("Ativo")) {
					System.out.println("=============================");
					System.out.print("Código:");
					System.out.println(f);
					System.out.print("Horário:");
					System.out.println(String.format("%02d", f.getDate().getHours())
							 + ":" + String.format("%02d" ,f.getDate().getMinutes()));
					System.out.print("Assentos disponíveis:");
					System.out.println(f.getNseats());
					System.out.print("Valor:");
					System.out.println(f.getValue());
					System.out.println("=============================");
				}
			}
			System.out.println();
		}
	}
	
	public void verifyFlights () {
		Flight f = null;
		Date today = Calendar.getInstance().getTime();
		if (getRep() != null) {
			for (int i=0; i<getRep().size();i++) {
				f = getRep().get(i);
				if (f.getDate().before(today) && f.getStatus().equals("Ativo")) {
					f.chageStatus();
				}
			}
		}
	}
}
