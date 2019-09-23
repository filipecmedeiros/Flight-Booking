package main;

import java.io.File;


import client.Client;
import client.ClientRepository;

public class Main {

	public static void main(String[] args) {
		ClientRepository cr;
		File clientsFile = new File("./clients");
		
		if (clientsFile.exists())
			cr = ClientRepository.decode(clientsFile.getName());
		else
			cr = new ClientRepository();
		
		if (cr.getRep().size() == 0) {	
			Client c1, c2;
			c1 = new Client ("Abigail", "72782091026");
			c2 = new Client ("Bianca", "96914177074");
		
			cr.create(c2);
			cr.create(c1);
		
			System.out.println(cr);
			
			System.out.println(cr.read("72782091026"));
			
			System.out.println(cr.read("222.222.222-22"));
			
			
			cr.updatePhone("72782091026", "81999999999");
			cr.updateEmail("72782091026", "teste@teste.com");
		}
		
		cr.printClient("72782091026");
		
		cr.save(clientsFile.getName());
	}

}
