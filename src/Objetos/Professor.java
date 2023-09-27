package Objetos;

public class Professor extends Pessoa{

	private String univFormacao;

	public Professor(int codigo, String nome, String email, String univFormacao) {
		super(codigo, nome, email);
		this.univFormacao = univFormacao;
	}

	public String getUnivFormacao() {
		return univFormacao;
	}

	public void setUnivFormacao(String univFormacao) {
		this.univFormacao = univFormacao;
	}
	
	
	
}
