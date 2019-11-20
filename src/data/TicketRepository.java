package data;

import java.io.Serializable;

import client.Client;
import flight.Flight;
import ticket.Ticket;

public class TicketRepository extends AbstractRepository <Ticket> implements Serializable{

	
	
	public void clientTickets(Client client) {
		if (client != null) {
			System.out.println("Passagens do(a) cliente " + client.getName() + ":");
			if (getRep().size()>0) {
				for (int i=0; i<getRep().size(); i++) {
					if (getRep().get(i).getClient().equals(client) && 
							getRep().get(i).getStatus().equals("Ativa")) {
						System.out.println("========================================");
						getRep().get(i).print();
						System.out.println("========================================");
						System.out.println();
					}
				}
			}
			else {
				System.out.println("Não há passagens reservadas");
			}
		}
		else {
			System.out.println("Cliente inválido");
		}
	}
	
	public void clientTicketsAll(Client client) {
		if (client != null) {
			System.out.println("Passagens do(a) cliente " + client.getName() + ":");
			if (getRep().size()>0) {
				for (int i=0; i<getRep().size(); i++) {
					if (getRep().get(i).getClient().equals(client)) {
						System.out.println("========================================");
						getRep().get(i).print();
						System.out.println("========================================");
						System.out.println();
					}
				}
			}
			else {
				System.out.println("Não há passagens reservadas");
			}
		}
		else {
			System.out.println("Cliente inválido");
		}
	}
	
	public void cancell(Ticket ticket) {
		ticket.getFlight().getSeats()[ticket.getColSeat()][ticket.getLineSeat()] = null;
		ticket.getFlight().setNseats(ticket.getFlight().getNseats()+1);
		ticket.setStatus("Cancelada");
	}
	
	public void clientsPerFlight(Flight flight) {
		
		System.out.println("=====================================");
		for (int i=0; i<getRep().size(); i++) {
			if(getRep().get(i).getFlight().equals(flight) && !getRep().get(i).getStatus().equals("Cancelada")) {
				System.out.print("Cliente: ");
				System.out.println(getRep().get(i).getClient().getName());
				System.out.print("CPF: ");
				System.out.println(getRep().get(i).getClient().getCpf());
				System.out.print("Assento: ");
				System.out.println(getRep().get(i).getSeat());
				System.out.println();
			}
		}
		System.out.println("=====================================");
		
	}
}
