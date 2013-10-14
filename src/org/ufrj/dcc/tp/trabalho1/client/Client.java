package org.ufrj.dcc.tp.trabalho1.client;

public class Client {
	private Integer id;

	public Client(Integer id) {
		super();
		this.id = id;
	}
	
	@Override
	public String toString() {		
		return id.equals(Message.PUBLIC_MESSAGE) ? "Todos" : "<ID:"+id+">";
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
}
