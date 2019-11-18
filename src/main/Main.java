package main;

import java.io.File;
import java.util.Scanner;

import client.Client;
import client.ClientRepository;
import data.FlightRepository;
import data.TicketRepository;
import flight.Flight;
import flight.Locations;
import ticket.Ticket;
import utils.Utils;


public class Main {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		ClientRepository cr;
		File clientsFile = new File("./clients");
		FlightRepository fr;
		File flightsFile = new File("./flights");
		TicketRepository tr;
		File ticketFile = new File("./tickets");
		
		if (clientsFile.exists())
			cr = ClientRepository.decode(clientsFile.getName());
		else
			cr = new ClientRepository();
		
		if (flightsFile.exists())
			fr = (FlightRepository) FlightRepository.decode(flightsFile.getName());
		else
			fr = new FlightRepository();
		
		if (ticketFile.exists())
			tr = (TicketRepository) TicketRepository.decode(ticketFile.getName());
		else
			tr = new TicketRepository();
		
		fr.verifyFlights();
				
		int mainMenu = 0;
		boolean exit = false;
		String var1, var2, var3, var4, var5;
		Locations location;
		double value;
		Client c;
		Flight fa;
		String messageMainMenu = "Menu:\n"
				+ "1. Clientes\n"
				+ "2. Voôs\n"
				+ "3. Passagens\n"
				+ "4. Sair\n";
		int menu;
		String messageClient = "Clientes:\n"
				+ "1. Cadastrar novo cliente\n"
				+ "2. Buscar cliente por CPF\n"
				+ "3. Editar telefone\n"
				+ "4. Editar e-mail\n"
				+ "5. Deletar cliente\n"
				+ "6. Exibir todos os clientes\n"
				+ "7. Voltar\n";
		String messageFlight = "Vôos:\n"
				+ "1. Cadastrar novo vôo\n"
				+ "2. Buscar vôo por local\n"
				+ "3. Editar valor do vôo\n"
				+ "4. Remover vôo\n"
				+ "5. Consultar vôo\n"
				+ "6. Exibir todos os vôos\n"
				+ "7. Voltar\n";
		String messageTicket = "Passagens:\n"
				+ "1. Vender passagem\n"
				+ "2. Consultar passagem de um cliente\n"
				+ "3. Consultar histórico de cliente\n"
				+ "4. Cancelar passagem\n"
				/*+ "5. Consultar passageiros de um vôo\n"
				+ "6. Exibir todos os vôos\n"*/
				+ "7. Voltar\n";
				
		do {
			System.out.println(messageMainMenu);
			boolean validInput = false;
			exit = false;
			do{
				try {
					mainMenu = Integer.parseInt(input.next());
					validInput = true;
				}catch (Exception e) { 
					System.out.println("Entrada inválida");
					System.out.println(messageMainMenu);
				}
			}while (!validInput);
			
			switch(mainMenu) {
				case 1:
					do{
						do{
							System.out.println(messageClient);
							validInput = false;
							exit = false;
							menu = 0;
							try {
								menu = Integer.parseInt(input.next());
								validInput = true;
							}catch (Exception e) { 
								System.out.println("Entrada inválida");
							}
						}while (!validInput);
						
						
						switch(menu) {
							case 1:System.out.println("Nome:");
								var1 = input.next();
								System.out.println("CPF:");
								var2 = input.next();
								c = new Client(var1, var2);
								try {
									cr.create(c);
									cr.save(clientsFile.getName());
									System.out.println("Cliente cadastrado com sucesso!");
								} catch (Exception e) {
									System.out.println("Não foi possível criar o cliente.");
									System.out.println("Os dados não são válidos.");
								}
									
								break;
							case 2: System.out.println("Informe o CPF:");
									var1 = input.next();
									cr.printClient(var1);
								break;
							case 3: System.out.println("Informe o CPF do cliente:");
									var1 = input.next();
									System.out.println("Informe o telefone:");
									var2 = input.next();
									cr.updatePhone(var1, var2);
									cr.save(clientsFile.getName());
									System.out.println("Cliente atualizado com sucesso!");
									break;
							case 4: System.out.println("Informe o CPF do cliente:");
									var1 = input.next();
									System.out.println("Informe o e-mail:");
									var2 = input.next();
									cr.updateEmail(var1, var2);
									cr.updatePhone(var1, var2);
									System.out.println("Cliente atualizado com sucesso!");
									break;
							case 5: System.out.println("Informe o CPF do cliente:");
									var1 = input.next();
									cr.delete(var1);
									cr.save(clientsFile.getName());
									System.out.println("Cliente removido com sucesso!");
									break;
							case 6: System.out.println(cr);
									break;
							case 7:
								exit = true;
								break;
						}
					}while((menu < 0 || menu > 8) && !exit);
					exit = false;
					break;
				case 2:
					do{
						do{
							System.out.println(messageFlight);
							validInput = false;
							exit = false;
							menu = 0;
							try {
								menu = Integer.parseInt(input.next());
								validInput = true;
							}catch (Exception e) { 
								System.out.println("Entrada inválida");
							}
						}while (!validInput);
						switch(menu) {
							case 1:
								Locations.print();
								System.out.println("Origem:");
								var1 = input.next();
								var1 = var1.toUpperCase();
								try {
									location = Locations.valueOf(var1);
								} catch (Exception e){
									System.out.println("Local inválido");
									System.out.println("\n\n");
									exit = true;
									break;
								}
								Locations.print();
								System.out.println("Destino:");
								var2 = input.next();
								var2 = var2.toUpperCase();
								try {
									location = Locations.valueOf(var2);
								} catch (Exception e) {
									System.out.println("Local inválido");
									System.out.println("\n\n");
									exit = true;
									break;
								}
								System.out.println("Data (no formato dd/mm/yyyy):");
								var3 = input.next();
								System.out.println("Hora (no formato hh:mm):");
								var4 = input.next();
								if (!Utils.isValidDate(var3, var4)) {
									System.out.println("Data inválida");
									System.out.println("\n\n");
									exit = true;
									break;
								}
								System.out.println("Valor:");
								var5 = input.next();
								try {
									value = Double.parseDouble(var5);
									if (value < 0.0) {
										throw new Exception();
									}
								} catch (Exception e) {
									System.out.println("Valor inválido\n\n");
									exit = true;
									break;
								}
								Flight f = new Flight(
										var1, var2, var3, var4, value);
								try {
									fr.create(f);
									fr.save(flightsFile.getName());
									System.out.println("Vôo cadastrado com sucesso!");
								} catch (Exception e) {
									System.out.println("Não foi possível criar o vôo.");
									System.out.println("Os dados não são válidos.");
								}
									
								break;
							case 2: System.out.println("Origem:");
									var1 = input.next();
									System.out.println("Destino:");
									var2 = input.next();
									System.out.println("Data (no formato dd/mm/yyyy):");
									var3 = input.next();
									fr.search(var1, var2, var3);
								break;
							case 3: System.out.println("Informe o código do vôo:");
									var1 = input.next();
									fa = fr.get(var1);
									if (fa != null) {
										System.out.println("Valor:");
										var2 = input.next();
										try {
											value = Double.parseDouble(var2);
											if (value < 0.0) {
												throw new Exception();
											}
											else {
												fa.setValue(value);
												fr.save(flightsFile.getName());
												System.out.println("Valor atualizado com sucesso!");
											}
										} catch (Exception e) {
											System.out.println("Valor inválido\n\n");
											exit = true;
											break;
										}
									}
									else {
										System.out.println("Vôo não encontrado");
									}
									break;
							case 4: 
									System.out.println("Informe o código do vôo:");
									var1 = input.next();
									fa = fr.get(var1);
									if (fa != null) {
										if (fa.getStatus().equals("Ativo") &&
												fa.getNseats()==36) {
											fr.delete(fa);
											fr.save(flightsFile.getName());
											System.out.println("Vôo removido com sucesso!");
										}
										else {
											System.out.println("Não é possível remover este vôo");
										}
									}
									else {
										System.out.println("Vôo não encontrado!");
									}
									break;
							case 5: 
									System.out.println("Informe o código do vôo:");
									var1 = input.next();
									fa = fr.get(var1);
									if (fa != null) {
										fa.print();
									}
									else {
										System.out.println("Vôo não encontrado!");
									}
									break;
							case 6: System.out.println(fr);
									break;
							case 7:
								exit = true;
								break;
						}
					}while((menu < 0 || menu > 8) && !exit);
					exit = false;
					break;
				case 3: 
					do{
						do{
							System.out.println(messageTicket);
							validInput = false;
							exit = false;
							menu = 0;
							try {
								menu = Integer.parseInt(input.next());
								validInput = true;
							}catch (Exception e) { 
								System.out.println("Entrada inválida");
							}
						}while (!validInput);
						switch(menu) {
						case 1:
							System.out.println("Informe o CPF do cliente:");
							var1 = input.next();
							c = cr.read(var1);
							System.out.println("Informe o código do vôo:");
							var2 = input.next();
							Flight f = fr.get(var2);
							
							if (c!= null && f != null) {
								
								if (f.clientHasTicket(c)) {
									System.out.println("Este cliente já tem uma passagem neste vôo.");
								}
								else {
									Ticket t = new Ticket(c, f, input);							
									
									if (t.getCode() != null) {
										tr.create(t);
										tr.save(ticketFile.getName());
										fr.save(flightsFile.getName());
										System.out.println("Passagem reservada com sucesso!");
										f.printSeats();
										t.print();
									}
								
								}
							}
							else {
								System.out.println("Os valores informados não estão corretos");
							}
							break;
						case 2:
							System.out.println("Informe o CPF do cliente:");
							var1 = input.next();
							c = cr.read(var1);
							if (c!=null) {
								tr.clientTickets(c);
							}
							else {
								System.out.println("Cliente não encontrado.");
							}
							break;
						case 3:
							System.out.println("Informe o CPF do cliente:");
							var1 = input.next();
							c = cr.read(var1);
							if (c!=null) {
								tr.clientTicketsAll(c);
							}
							else {
								System.out.println("Cliente não encontrado.");
							}
							break;
						case 4:
							break;
						}
					}while((menu < 0 || menu > 8) && !exit);
					exit = false;
					break;					
				case 4: exit = true; 
					break;
			}
		}while((mainMenu > 0 || mainMenu <5) && !exit);
		input.close();
		
		cr.save(clientsFile.getName());
		fr.save(flightsFile.getName());
		tr.save(ticketFile.getName());
		/* CPFs de teste
		 * 72782091026
		 * 96914177074
		 * */
		
		
		
	}

}
