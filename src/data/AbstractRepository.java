package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public abstract class AbstractRepository <T> implements Serializable {
	private LinkedList <T> rep;
			
	public AbstractRepository(){
		this.rep = new LinkedList<T>();
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
	
	public LinkedList <T> getRep() {
		return rep;
	}

	public void setRep(LinkedList <T> rep) {
		this.rep = rep;
	}

	
	public void create(T object) {
		if (getRep().size() == 0) {
			getRep().add(object);
		}
		else if (read(object) == null) {
			int i=0; 
			while (i < getRep().size()) {
				if (object.equals(getRep().get(i)) == false) {
					i++;
				}
				else {
					break;
				}
			}
			getRep().add(i, object);
		}
		else {
			System.out.println(object + " já está cadastrado");
		}
		
	}
	
	public T read (T object) {
		boolean notFound = true;
		T aux = null;
		
		for (int i=0; i<getRep().size() && notFound; i++) {
			if (getRep().get(i).equals(object) == true) {
				aux = (T) getRep().get(i);
				notFound=false;
			}
		}
		return aux;
	}
	
	
	public void update (T object) {
		T aux = read(object);
		
		if (aux != null) {
			delete(aux);
			create(object);
		}
	}
	
	
	public void delete (T object) {
		T aux = read(object);
		
		if (aux != null){
			getRep().remove(aux);
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
    
	@SuppressWarnings("rawtypes")
	public static AbstractRepository decode (String directory) {
		AbstractRepository r = null;
		
		try {	
			FileInputStream fin = new FileInputStream(directory);
			ObjectInputStream ois = new ObjectInputStream(fin);
			
			if (fin.available() != 0){
				r = (AbstractRepository) ois.readObject();
			}
			
			fin.close();
			ois.close();
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return r;
		
	}

}
