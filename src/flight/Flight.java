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
	private int nSeats;
	private int avaliableSeats;
	private Client [][] seats;
	private double value;
	private String status;
	
	public static final Map<Character, Integer> dict = new HashMap<>();
	static {
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
	
    @Override
    public boolean equals(Object obj) {
    	boolean operation = false;
		if (obj instanceof Flight) {
			Flight f = (Flight) obj;
			operation = this.code.equals(f.getCode());
		}
		
		return operation;
    }
	
	public Flight (String origin, String destiny, String date,
			String hour, double value) {
		
		Client[][] seats = new Client [6][6];
		
		setOrigin(origin);
		setDestiny(destiny);
		setDate(date, hour);
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
		if (getStatus().equals("Ativo")) {
			setStatus("Efetivado");
		}
		else {
			setStatus("Ativo");
		}
	}
	
	public void print() {
		System.out.println("=============================");
		System.out.print("Código:");
		System.out.println(getCode());
		System.out.print("Origem:");
		System.out.println(getOrigin());
		System.out.print("Destino:");
		System.out.println(getDestiny());
		System.out.print("Data e horário:");
		System.out.println(getDate().toLocaleString());
		//System.out.print("Horário:");
		//System.out.println(String.format("%02d", getDate().getHours())
		//		 + ":" + String.format("%02d" ,getDate().getMinutes()));
		System.out.print("Assentos disponíveis:");
		System.out.println(getNseats());
		System.out.print("Valor:");
		System.out.println(getValue());
		System.out.println("Assentos:");
		printSeats();
		System.out.print("Status:");
		System.out.println(getStatus());
		System.out.println("=============================");
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
	public void setDate(String date, String hour) {
		if (Utils.isValidDate(date, hour)) {
			this.date = Utils.parseDate(date, hour);
		}
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
