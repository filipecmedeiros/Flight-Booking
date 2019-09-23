package main;

import client.Client;
import client.ClientRepository;

public class Main {

	public static void main(String[] args) {
		Client c1, c2;
		c1 = new Client ("Abigail", "000.000.000-00");
		c2 = new Client ("Bianca", "111.111.111-11");
		
		ClientRepository cr = new ClientRepository();
		
		cr.create(c2);
		cr.create(c1);
	
		System.out.println(cr);
	}

}
