package client;


public class Client {

    private String name;
    private String cpf;
    private String phone;
    private String email;


    public Client(String name, String cpf, String phone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
    }

    public Client (String name, String cpf) {
    	this.name = name;
    	this.cpf = cpf;
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
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}