package org.ufrj.dcc.tp.trabalho1.client;

public class Client {
	private Integer id;
	private String name;

	public Client(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {		
		return id.equals(Message.PUBLIC_MESSAGE) ? "Todos" : "<"+name+">";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean equals(Client obj) {
		return this.id.equals(obj.getId());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
