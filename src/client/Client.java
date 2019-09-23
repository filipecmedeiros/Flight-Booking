package client;

import java.io.Serializable;

import utils.Utils;

public class Client implements Serializable {

    private String name;
    private String cpf;
    private String phone;
    private String email;


    public Client(String name, String cpf, String phone, String email) {
        setName(name);
        setCpf(cpf);
        setPhone(phone);
        setEmail(email);
    }

    public Client (String name, String cpf) {
    	setName(name);
    	setCpf(cpf);
    }
    
	@Override
    public String toString () {
    	return this.name;
    }
    
    @Override
    public boolean equals(Object obj) {
    	boolean operation = false;
		if (obj instanceof Client) {
			Client client = (Client) obj;
			operation = this.cpf.equals(client.cpf);
		}
		
		return operation;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (Utils.isValidName(name)) {
			this.name = name;
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (Utils.isValidCPF(cpf)) {
			this.cpf = cpf;
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (Utils.isValidPhone(phone)) {
			this.phone = phone;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (Utils.isValidEmail(email)) {
			this.email = email;
		}
	}
    
    
}