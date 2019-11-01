package flight;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import client.Client;
import utils.Utils;

public class Flight implements Serializable{
	
	private String code;
	private String origin;
	private String destiny;
	private Date date;
	private String hour;
	private int nSeats;
	private int avaliableSeats;
	private Client [][] seats;
	private double value;
	private String status;
	private static String counter;
	
	public static final Map<Character, Integer> dict = new HashMap<>();
	static {
		counter="AAA0000";
		dict.put('A', 0);
		dict.put('B', 1);
		dict.put('C', 2);
		dict.put('D', 3);
		dict.put('E', 4);
		dict.put('F', 5);
	}
	
	@Override
	public String toString() {
		return this.code;
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
	
	private static String generateCode() {
		String code = counter;
		counter = moveCounter(counter, 6);
		return code;
	}
	
	public Flight (String origin, String destiny, String date,
			String hour, double value) {
		
		Client[][] seats = new Client [6][6];
		
		setCode(generateCode());
		setOrigin(origin);
		setDestiny(destiny);
		setDate(date);
		setHour(hour);
		setNseats(36);
		setAvaliableSeats(36);
		setSeats(seats);
		setValue(value);
		setStatus("Ativo");
	}
	
	public void printSeats() {
		int i, j;
		System.out.println(" [A][B]|  |[C][D]|  |[E][F]");
		for (i=0; i<6; i++) {
			System.out.print(i);
			for (j=0; j<6; ) {
				for (int k=0; k<2; k++, j++) {
					if (getSeats()[i][j] != null) {
						System.out.print("[C]");
					}
					else {
						System.out.print("[ ]");
					}
				}
				if (j<5) {
					System.out.print("|  |");
				}
			}
			System.out.println();
		}
	}
	
	public void takeSeat(Client client, char column, int line) {
		getSeats()[dict.get(column)][line] = client;
	}
	
	public void chageStatus() {
		if (getStatus()=="Ativo") {
			setStatus("Efetivado");
		}
		else {
			setStatus("Ativo");
		}
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestiny() {
		return destiny;
	}
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String date) {
		if (Utils.isValidDate(date)) {
			this.date = Utils.parseDate(date);
		}
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public int getNseats() {
		return nSeats;
	}
	public void setNseats(int nSeats) {
		this.nSeats = nSeats;
	}
	public int getAvaliableSeats() {
		return avaliableSeats;
	}
	public void setAvaliableSeats(int avaliableSeats) {
		this.avaliableSeats = avaliableSeats;
	}
	public Client[][] getSeats() {
		return seats;
	}
	public void setSeats(Client[][] seats) {
		this.seats = seats;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
