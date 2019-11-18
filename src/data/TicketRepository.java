package data;

import java.io.Serializable;

import client.Client;
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
}
