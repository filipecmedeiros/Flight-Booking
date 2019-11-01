package main;

import java.io.File;
import java.util.Scanner;

import client.Client;
import client.ClientRepository;
import flight.Flight;

public class Main {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		ClientRepository cr;
		File clientsFile = new File("./clients");
		
		if (clientsFile.exists())
			cr = ClientRepository.decode(clientsFile.getName());
		else
			cr = new ClientRepository();
		
		
		Flight f = new Flight("Recife", "Salvador", "09/11/2019", "08:00", 100.00);
		System.out.println(f);
		Flight g = new Flight("Recife", "Salvador", "09/11/2019", "08:00", 100.00);
		System.out.println(g);
		Flight h = new Flight("Recife", "Salvador", "09/11/2019", "08:00", 100.00);
		System.out.println(h);
		System.out.println(f.getDate());
		f.printSeats();
		
		int mainMenu;
		boolean exit = false;
		String var1, var2;
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
		
		
		do {
			System.out.println(messageMainMenu);
			mainMenu = input.nextInt();
			
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
				case 2: break;
				case 3: break;					
				case 4: exit = true; 
					break;
				default: break;
			}
		}while(mainMenu > 0 && mainMenu <5 && !exit);
		input.close();
		
		cr.save(clientsFile.getName());
		
		/* CPFs de teste
		 * 72782091026
		 * 96914177074
		 * */
		
		
		
	}

}
