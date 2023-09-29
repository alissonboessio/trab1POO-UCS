import java.util.Scanner;

import Objetos.Aluno;
import Objetos.Curso;
import Objetos.Professor;
import Objetos.Turma;
import Util.Util;

public class Menu {
	
	private static final int MAX_TURMAS = 10;
	private static final int MAX_CURSOS = 4;
	private static final int MAX_PROFESSORES = 100;

	protected int qtdTurmas = 0;
	protected int qtdProfessores = 0;

	private static final Curso[] cursos = new Curso[MAX_CURSOS];
	private static final Turma[] turmas = new Turma[MAX_TURMAS];
	private static final Professor[] professores = new Professor[MAX_PROFESSORES];
	
	public void setCursos() {
		cursos[0] = new Curso(1, "Ciência da Computação");
		cursos[1] = new Curso(2, "Engenharia de Software");
		cursos[2] = new Curso(3, "Engenharia de COmputação");
		cursos[3] = new Curso(4, "Análise e Desenvolvimento de Sistemas");
	}

	private boolean isTurmasEmpty() {
		return qtdTurmas == 0;
	}

	private boolean isProfessoresEmpty() {
		return qtdProfessores == 0;
	}

	public void mostrar() {
		Scanner in = new Scanner(System.in);
		int opc;
		do {
			System.out.println("------------------------------------------------------------");
			System.out.println("Instituição TI");
			System.out.println("------------------------------------------------------------");
			System.out.println("Escolha uma das opções a seguir:");
			System.out.println("1) Listar todas as turmas");
			System.out.println("2) Informar dados de uma turma");
			System.out.println("3) Consultar os dados de uma turma");
			System.out.println("4) Consultar estatísticas gerais");
			System.out.println("5) Sair do sistema");
			System.out.println("------------------------------------------------------------");
			
			opc = in.nextInt();

            switch (opc) {
                case 1 -> printTurmas();
                case 2 -> adicionaTurma();
                case 3 -> {
                    int codigo = Util.leEntradaInt("Digite o código da turma: ");
                    consultarTurmaPorCodigo(codigo - 1);
                }
                case 4 -> printDetalhesGerais();
                case 5 -> {
                }
            }
		} while(opc != 5);
		in.close();
	}
	
	private void adicionaTurma() { // OPÇÃO 2
		if(this.qtdTurmas < MAX_TURMAS) {
			System.out.println(this.qtdTurmas);
			Turma turma = new Turma();
			
			System.out.println("Dados da turma:");
			if(qtdTurmas == 0){
				turma.setCodigo(qtdTurmas);
			}
			else{
				turma.setCodigo(qtdTurmas++);
			}
			turma.setDisciplina(Util.leEntradaString("Nome da Disciplina: "));

			// PROFESSOR
			System.out.println();
			int profOption = Util.leEntradaInt("Deseja atribuir a esta turma um professor novo ou um já existente?\n1- Já existente. 2- Novo: ");
			while (profOption != 1 && profOption != 2) {
				profOption = Util.leEntradaInt("Deseja atribuir a esta turma um professor novo ou um já existente?\n1- Já existente. 2- Novo: ");
			}

			if(profOption == 1){
				printProfessores(); // printa as opções de professores cadastrados
				int codigoProfessor = Util.leEntradaInt("Digite o código do professor desejado: "); // pedir para escolher o código do professor existente
				turma.setProfessor(professores[codigoProfessor + 1]);// adicionar o professor existente à turma através do código escolhido
			}
			else { // será 2 de qualquer forma
				atribuirNovoProfessorATurma(turma);
			}

			// ALUNOS
			Aluno[] alunos = new Aluno[0]; // atribuído como vetor da turma

			System.out.println();

			int alunoOption = 0;
			while(alunoOption != 3 && alunos.length <= 30){
				alunoOption = Util.leEntradaInt("O que você deseja fazer?\n1- Atribuir um novo aluno à turma. 2- Ver alunos já cadastrados na turma. 3- Sair.");
				if(alunoOption == 1){
					atribuirNovoAlunoATurma(alunos);
				}
				else if(alunoOption == 2){
					printAlunos(alunos);
				}
				else if(alunoOption == 3){
					break;
				}
			}

			turma.setAlunos(alunos);

			// INCREMENTANDO
			turmas[qtdTurmas] = turma;
			qtdTurmas++;
		}
		else{
			System.out.println("Limite de turmas atingido!");
		}
	}

	private void printProfessores(){
		if(isProfessoresEmpty()){
			System.out.println("Ainda não há professores cadastrados.");
		}
		else{
			System.out.println();
			for(Professor professor : professores){
				System.out.println("Professor #" + professor.getCodigo() +
						" Nome: " + professor.getNome() +
						" Email: " + professor.getEmail() +
						" Universidade de formação: " + professor.getUnivFormacao() + "\n");
			}
		}
	}

	private void printAlunos(Aluno[] alunos){
		if(alunos.length == 0){
			System.out.println("Ainda não há alunos cadastrados.");
		}
		else{
			System.out.println();
			for(Aluno aluno : alunos){
				System.out.println("Aluno #" + aluno.getCodigo() +
						" Nome: " + aluno.getNome() +
						" Email: " + aluno.getEmail() +
						" N1: " + aluno.getN1() +
						" N2: " + aluno.getN2() +
						" N3: " + aluno.getN3() +
						" Média: " + aluno.calculaMedia() +
						(aluno.calculaMedia() >= 7.0 ? " Situação: Aprovado." : " Situação: Reprovado.\n"));
			}
		}
	}

	private void printCursos(){
		System.out.println();
		for(Curso curso : cursos){
			System.out.println("Curso #" + curso.getCodigo() +
					" Nome: " + curso.getNome() + "\n");
		}
	}

	private void printTurmas(){ // OPÇÃO 1
		if (isTurmasEmpty()){
			System.out.println();
			for(Turma turma : turmas){
				printTurmaSemAlunos(turma);
			}
		}
		else{
			System.out.println("Ainda não há turmas cadastradas.");
		}
	}

	private void printTurma(Turma turma){
		System.out.println("Turma #" + turma.getCodigo() +
				" Disciplina: " + turma.getDisciplina() +
				" Professor: " + turma.getProfessor().getNome() +
				" Quantidade de alunos: " + turma.getQtdAlunos());
		System.out.println("\nAlunos:");
		printAlunos(turma.getAlunos());
	}

	private void printTurmaSemAlunos(Turma turma){
		System.out.println("Turma #" + turma.getCodigo() +
				" Disciplina: " + turma.getDisciplina() +
				" Professor: " + turma.getProfessor().getNome() +
				" Quantidade de alunos matriculados: " + turma.getQtdAlunos());
	}

	private void atribuirNovoProfessorATurma(Turma turma){
		Professor professorNovo = new Professor();
		professorNovo.setCodigo(professores.length + 1);
		professorNovo.setNome(Util.leEntradaString("Nome do professor: "));
		professorNovo.setEmail(Util.leEntradaString("Email do professor: "));
		professorNovo.setUnivFormacao(Util.leEntradaString("Universidade de formação do professor: "));
		turma.setProfessor(professorNovo);
	}

	private void atribuirNovoAlunoATurma(Aluno[] alunos){
		Aluno aluno = new Aluno();
		aluno.setCodigo(alunos.length + 1);
		aluno.setNome(Util.leEntradaString("Nome do aluno: "));
		aluno.setEmail(Util.leEntradaString("Email do aluno: "));
		aluno.setN1(Util.leEntradaDouble("Nota N1: "));
		aluno.setN2(Util.leEntradaDouble("Nota N2: "));
		aluno.setN3(Util.leEntradaDouble("Nota N3: "));

		printCursos();
		int codigoCurso = Util.leEntradaInt("Qual o código do curso do aluno? ");
		aluno.setCurso(cursos[codigoCurso-1]);

		///// !!!! aqui está dando problema, na hora de adicionar o aluno ao vetor de alunos, que posteriormente será atribuído à turma
		if(alunos.length == 0){
			alunos[alunos.length] = aluno;
		}
		else{
			alunos[alunos.length + 1] = aluno;
		}
	}

	private void consultarTurmaPorCodigo(int codigo){ // OPÇÃO 3
		Turma turma = turmas[codigo];
		printTurma(turma);
	}

	private void printDetalhesGerais(){ // OPÇÃO 4
		// para cada turma
		int qteMatriculadosInstituicao = 0;
		double percentualAprovacaoTurmas = 0;
		for(Turma turma : turmas){
			printTurmaSemAlunos(turma);
			int qteAprovados = 0;
			for(Aluno aluno : turma.getAlunos()){
				qteMatriculadosInstituicao++;
				if(aluno.calculaMedia() >= 7){
					qteAprovados++;
				}
			}
			System.out.println("\nQuantidade de aprovados: " + qteAprovados);
			System.out.println("\nPorcentagem de aprovação: " + qteAprovados/turma.getAlunos().length * 100 + "%");
			percentualAprovacaoTurmas += (double) qteAprovados /turma.getAlunos().length * 100;
		}
		// Dados gerais da instituição
		System.out.println("\nDados gerais da instituição\n");
		System.out.println("\nQuantidade de alunos matriculados na instituição: " + qteMatriculadosInstituicao);
		System.out.println("\nMédia de aprovação nas turmas da instituição: " + percentualAprovacaoTurmas/turmas.length + "%");
	}
}
