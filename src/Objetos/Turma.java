package Objetos;

public class Turma {

	private static final int MAX_ALUNOS = 30;
	
	private int codigo;
	private String disciplina;
	private Professor professor;	
	private Aluno[] alunos;	
	
	private int qtdAlunos;
	
	public Turma() {
		this.alunos = new Aluno[MAX_ALUNOS];
	}
	
	public Turma(int codigo, String disciplina, Professor professor, int qtdAlunos) {
		this.codigo = codigo;
		this.disciplina = disciplina;
		this.professor = professor;
		this.qtdAlunos = qtdAlunos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public int getQtdAlunos() {
		return qtdAlunos;
	}
	
}
