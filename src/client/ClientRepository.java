package client;

import java.util.LinkedList;

public class ClientRepository {
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
		else {
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
		
	}
	
	
	
}
