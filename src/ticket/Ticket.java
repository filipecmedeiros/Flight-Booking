package ticket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import client.Client;
import flight.Flight;

public class Ticket implements Serializable{
	
	private String code;
	private Client client;
	private Flight flight;
	private int lineSeat;
	private int colSeat;
	private String status;
	
	public static final Map<String, Integer> dict = new HashMap<>();
	static {
		dict.put("A", 0);
		dict.put("B", 1);
		dict.put("C", 2);
		dict.put("D", 3);
		dict.put("E", 4);
		dict.put("F", 5);
	}
	
	public static final Map<Integer, String> inv = invert(dict);
	
	public Ticket (Client client, Flight flight, int lineSeat, 
			char colSeat, String status) {
		this.code = flight.getCode() + "_" + client.getCpf();
		this.client = client;
		this.flight = flight;
		this.colSeat = colSeat;
		this.lineSeat = lineSeat;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
	
	@Override
    public boolean equals(Object obj) {
    	boolean operation = false;
		if (obj instanceof Ticket) {
			Ticket f = (Ticket) obj;
			operation = this.code.equals(f.getCode());
		}
		
		return operation;
    }

	public Ticket (Client client, Flight flight, Scanner input) {
		this.client = client;
		this.flight = flight;
		boolean validInput;
		
		if (flight.getNseats()>0) {
			do {
				validInput = false;
				flight.printSeats();
				System.out.println("Por favor, informe a poltrona que deseja reservar (coluna):");
				try {
					String var = input.next();
					this.colSeat = dict.get(var.toUpperCase());
					if (this.colSeat>=0 && this.colSeat<6) {
						validInput = true;
					}
				}catch (Exception e) { 
					System.out.println("Entrada inválida");
				}
			}while(!validInput);
			
			do {
				System.out.println("Por favor, informe a poltrona que deseja reservar (linha):");
				try {
					this.lineSeat = Integer.parseInt(input.next());
					if (this.lineSeat>=0 && this.lineSeat<6) {
						validInput = true;
					}
				}catch (Exception e) { 
					System.out.println("Entrada inválida");
				}
			}while(!validInput);
		
			if (flight.getSeats()[this.lineSeat][this.colSeat] == null) {
				flight.getSeats()[this.lineSeat][this.colSeat] = client;
				flight.setNseats(flight.getNseats()-1);
				this.code = flight.getCode()+this.colSeat+this.lineSeat+client.getCpf();
				this.status = "Ativa";
			}
			else {
				System.out.println("Assento não disponível");
			}
		}
		else {
			System.out.println("Não há assentos disponíveis nesse vôo");
		}
	}
	
	public void print() {
		System.out.print("\nCódigo da passagem: ");
		System.out.println(getCode());
		System.out.print("Cliente: ");
		System.out.println(getClient());
		System.out.print("Vôo: ");
		System.out.println(getFlight());
		System.out.print("Assento: ");
		System.out.println(getSeat());
		System.out.print("Status: ");
		System.out.println(getStatus());
		if (getStatus().equals("Cancelada")) {
			System.out.print("Valor do crédito: ");
			System.out.println(flight.getValue()/2);
		}
		else {
			System.out.print("Valor da passagem: ");
			System.out.println(flight.getValue());
		}
		System.out.println();
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getLineSeat() {
		return lineSeat;
	}

	public void setLineSeat(int lineSeat) {
		this.lineSeat = lineSeat;
	}

	public int getColSeat() {
		return colSeat;
	}

	public void setColSeat(char colSeat) {
		this.colSeat = colSeat;
	}

	public String getSeat() {
		return inv.get(getColSeat()) + getLineSeat();
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	private static <V, K> Map<V, K> invert(Map<K, V> map) {

	    Map<V, K> inv = new HashMap<V, K>();

	    for (Entry<K, V> entry : map.entrySet())
	        inv.put(entry.getValue(), entry.getKey());

	    return inv;
	}
}
