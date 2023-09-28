package Objetos;

public class Aluno extends Pessoa{

	protected Curso curso;
	private double n1;
	private double n2;
	private double n3;

	public Aluno(){}

	public Aluno(int codigo, String nome, String email, Curso curso, double n1, double n2, double n3) {
		super(codigo, nome, email);
		this.curso = curso;
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
	}

	public Aluno(int codigo, String nome, String email) {
		super(codigo, nome, email);
	}


	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public double getN1() {
		return n1;
	}

	public void setN1(double n1) {
		this.n1 = n1;
	}

	public double getN2() {
		return n2;
	}

	public void setN2(double n2) {
		this.n2 = n2;
	}

	public double getN3() {
		return n3;
	}

	public void setN3(double n3) {
		this.n3 = n3;
	}
	
	public double calculaMedia() {	
		return (n1 + n2 + n3) / 3;
	}
}
