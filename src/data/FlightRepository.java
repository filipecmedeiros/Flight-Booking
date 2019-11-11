package data;

import java.io.Serializable;

import flight.Flight;

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
}
