package Objetos;

public class Turma {

	private static final int MAX_ALUNOS = 2;
	
	private String codigo;
	private String disciplina;
	private Professor professor;
	private Aluno[] alunos;

	private int qtdAlunos = 0;
	
	public Turma() {
		this.alunos = new Aluno[MAX_ALUNOS];
	}
	
	public Turma(String codigo, String disciplina, Professor professor, int qtdAlunos) {
		this.codigo = codigo;
		this.disciplina = disciplina;
		this.professor = professor;
		this.qtdAlunos = qtdAlunos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
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

	public void setQtdAlunos(int qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	public Aluno[] getAlunos() {
		return alunos;
	}

	public void setAlunos(Aluno[] alunos) {
		this.alunos = alunos;
	}
	
	public int getQtdAlunosRestante() {
		return MAX_ALUNOS - this.qtdAlunos;
	}
	
	public boolean addAluno(Aluno aluno) {
		if(this.qtdAlunos < MAX_ALUNOS) {
			this.alunos[this.qtdAlunos] = aluno;
			this.qtdAlunos++;
			return true;
		}else {
			System.out.println("Maximo de "+ MAX_ALUNOS +" da turma atingido!");			
			return false;
			
		}		
	}
	
	public String printAlunos() {
		if(this.qtdAlunos == 0){
			return "Ainda não há alunos cadastrados.";
		}
		else{
			String alunosStr = "";
			for(int i = 0; i<this.qtdAlunos; i++){
				alunosStr += alunos[i].printAlunoAprRepr();
			}
			return alunosStr;
		}
	}
	
	public int getQtdAprovados() {
		int qtd = 0;
		for (int i = 0; i < this.qtdAlunos; i++) {
			if (alunos[i].calculaMedia() >= 7) {
				qtd++;
			}
		}
		return qtd;
	}
	
	@Override
	public String toString() {
		return ("Turma código: " + this.getCodigo() +
				"\nDisciplina: " + this.getDisciplina() +
				"\nProfessor: " + this.getProfessor().getNome() +
				"\nQuantidade de alunos: " + this.getQtdAlunos() +
				"\n Alunos: \n" +
				this.printAlunos());
	}
	// usado para pritnar a turma sem os alunos (opcao 1)
	public String printAlunosSemTurma() {
		return "Turma código: " + this.getCodigo() +
			"\nDisciplina: " + this.getDisciplina() +
			"\nProfessor: " + this.getProfessor().getNome() +
			"\nQuantidade de alunos matriculados: " + this.getQtdAlunos();
	}
	
	
}
