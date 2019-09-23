package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;


public class ClientRepository implements Serializable {
	private LinkedList <Client> rep;
	
	
	public ClientRepository(){
		this.rep = new LinkedList<Client>();
	}
	
	@Override
	public String toString() {
		String s = "[";
		for (int i=0; i<getRep().size(); i++) {
			s += getRep().get(i);
			if (i+1 != getRep().size()) {
				s+= ", ";
			}
		}
		s+= "]";
		return s;
	}
	
	public LinkedList<Client> getRep() {
		return rep;
	}

	public void setRep(LinkedList<Client> rep) {
		this.rep = rep;
	}

	
	public void create(Client client) {
		if (getRep().size() == 0) {
			getRep().add(client);
		}
		else if (read(client.getCpf()) == null) {
			int i=0; 
			while (i < getRep().size()) {
				if (client.getName().compareTo(getRep().get(i).getName())>0) {
					i++;
				}
				else {
					break;
				}
			}
			getRep().add(i, client);
		}
		else {
			System.out.println("This client already exists!");
		}
		
	}
	
	public Client read(String cpf) {
		boolean notFound = true;
		Client c = null;
		
		for (int i=0; i<getRep().size() && notFound; i++) {
			if (getRep().get(i).getCpf().compareTo(cpf) == 0) {
				c = getRep().get(i);
				notFound=false;
			}
		}
		return c;
	}
	
	
	public void printClient(String cpf) {
		Client c = read (cpf);
		
		if (c!=null) {
			System.out.println("Name: " + c.getName());
			System.out.println("CPF: "+ c.getCpf());
			System.out.println("Phone number: " + c.getPhone());
			System.out.println("Email: " + c.getEmail());
		}
		else {
			System.out.println("Client not found!");
		}
	}
	
	public void updatePhone (String cpf, String phone) {
		Client c = read(cpf);
		
		if (c != null) {
			c.setPhone(phone);
		}
	}
	
	public void updateEmail (String cpf, String email) {
		Client c = read(cpf);
		
		if (c != null) {
			c.setEmail(email);
		}
	}
	
	public void delete (String cpf) {
		Client client = read(cpf);
		
		if (client != null){
			getRep().remove(client);
		}
	}
	
	
    public void save(String directory) {
		File file = new File(directory);
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			
			fout.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public static ClientRepository decode (String directory) {
		ClientRepository cr = null;
		
		try {	
			FileInputStream fin = new FileInputStream(directory);
			ObjectInputStream ois = new ObjectInputStream(fin);
			
			if (fin.available() != 0){
				cr = (ClientRepository) ois.readObject();
			}
			
			fin.close();
			ois.close();
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cr;
		
	}
	
	
	
	
}
