package Objetos;

public abstract class Pessoa {

	protected int codigo;
	protected String nome;
	protected String email;
	
	public Pessoa(int codigo, String nome, String email) {
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
	}		

	public Pessoa() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
