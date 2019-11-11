package main;

import java.io.File;
import java.util.Scanner;

import client.Client;
import client.ClientRepository;
import data.FlightRepository;
import flight.Flight;

public class Main {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		ClientRepository cr;
		File clientsFile = new File("./clients");
		FlightRepository fr;
		File flightsFile = new File("./flights");
		
		if (clientsFile.exists())
			cr = ClientRepository.decode(clientsFile.getName());
		else
			cr = new ClientRepository();
		
		if (flightsFile.exists())
			fr = (FlightRepository) FlightRepository.decode(flightsFile.getName());
		else
			fr = new FlightRepository();
			
		int mainMenu = 0;
		boolean exit = false;
		String var1, var2, var3, var4, var5;
		String messageMainMenu = "Menu:\n"
				+ "1. Clientes\n"
				+ "2. Voôs\n"
				+ "3. Compra de passagens\n"
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
				//+ "2. Buscar cliente por CPF\n"
				//+ "3. Editar telefone\n"
				//+ "4. Editar e-mail\n"
				//+ "5. Deletar cliente\n"
				+ "6. Exibir todos os vôos\n"
				+ "7. Voltar\n";
		
		do {
			System.out.println(messageMainMenu);
			boolean validInput = false;
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
						System.out.println(messageClient);
						menu = input.nextInt();
						switch(menu) {
							case 1:System.out.println("Nome:");
								var1 = input.next();
								System.out.println("CPF:");
								var2 = input.next();
								Client c = new Client(var1, var2);
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
						System.out.println(messageFlight);
						menu = input.nextInt();
						switch(menu) {
							case 1:System.out.println("Origem:");
								var1 = input.next();
								System.out.println("Destino:");
								var2 = input.next();
								System.out.println("Data (no formato dd/mm/yyy):");
								var3 = input.next();
								System.out.println("Hora (no formato hh:mm):");
								var4 = input.next();
								System.out.println("Valor:");
								var5 = input.next();
								Flight f = new Flight(
										var1, var2, var3, var4, Double.parseDouble(var5));
								try {
									fr.create(f);
									fr.save(flightsFile.getName());
									System.out.println("Vôo cadastrado com sucesso!");
								} catch (Exception e) {
									System.out.println("Não foi possível criar o vôo.");
									System.out.println("Os dados não são válidos.");
								}
									
								break;
							/*case 2: System.out.println("Informe o CPF:");
									var1 = input.next();
									cr.printClient(var1);
								break;
							case 3: System.out.println("Informe o CPF do cliente:");
									var1 = input.next();
									System.out.println("Informe o telefone:");
									var2 = input.next();
									cr.updatePhone(var1, var2);
									break;
							case 4: System.out.println("Informe o CPF do cliente:");
									var1 = input.next();
									System.out.println("Informe o e-mail:");
									var2 = input.next();
									cr.updateEmail(var1, var2);
									break;
							case 5: System.out.println("Informe o CPF do cliente:");
									var1 = input.next();
									cr.delete(var1);
									break;*/
							case 6: System.out.println(fr);
									break;
							case 7:
								exit = true;
								break;
						}
					}while((menu < 0 || menu > 8) && !exit);
					exit = false;
					break;
				case 3: break;					
				case 4: exit = true; 
					break;
				default: break;
			}
		}while(mainMenu > 0 && mainMenu <5 && !exit);
		input.close();
		
		cr.save(clientsFile.getName());
		fr.save(flightsFile.getName());
		
		/* CPFs de teste
		 * 72782091026
		 * 96914177074
		 * */
		
		
		
	}

}
