import java.util.Scanner;

import Objetos.Aluno;
import Objetos.Curso;
import Objetos.Professor;
import Objetos.Turma;
import Util.Util;

public class Menu {
	
	private static final int MAX_TURMAS = 10;
	private static final int MAX_CURSOS = 4;
	
	protected int qtdTurmas = 0;

	private static final Curso[] cursos = new Curso[MAX_CURSOS];
	private static final Turma[] turmas = new Turma[MAX_TURMAS];
	private static final Professor[] professores = new Professor[100];
	
	public void setCursos() {
		cursos[0] = new Curso(1, "Ciência da Computação");
		cursos[1] = new Curso(2, "Engenharia de Software");
		cursos[2] = new Curso(3, "Engenharia de COmputação");
		cursos[3] = new Curso(4, "Análise e Desenvolvimento de Sistemas");
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
                case 4 -> {
                    int valor = Util.leEntradaInt("O que você deseja consultar?\n" +
                            "1- Todos os professores\n" +
                            "2- Todos os cursos\n" +
                            ""); // ADICIONAR MAIS OPÇÕES, CRIAR MÉTODOS PERSONALIZADOS, ALGO ASSIM
                    if (valor == 1) {
                        printProfessores();
                    } else if (valor == 2) {
                        printCursos();
                    }
                }
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
			//while(profOption != 1 || profOption != 2){
				//profOption = Util.leEntradaInt("Deseja atribuir a esta turma um professor novo ou um já existente?\n1- Já existente. 2- Novo: ");
			//}

			if(profOption == 1){
				printProfessores(); // printa as opções de professores cadastrados
				int codigoProfessor = Util.leEntradaInt("Digite o código do professor desejado: "); // pedir para escolher o código do professor existente
				turma.setProfessor(professores[codigoProfessor + 1]);// adicionar o professor existente à turma através do código escolhido
			}
			else if(profOption == 2){
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
		if(professores.length == 0){
			System.out.println("Ainda não há professores cadastrados.");
		}
		else{
			System.out.println();
			for(Professor professor : professores){
				System.out.println("Professor #" + professor.getCodigo() + " " +
						"Nome: " + professor.getNome() + " " +
						"Email: " + professor.getEmail() + " " +
						"Universidade de formação: " + professor.getUnivFormacao() + "\n");
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
				System.out.println("Aluno #" + aluno.getCodigo() + " " +
						"Nome: " + aluno.getNome() + " " +
						"Email: " + aluno.getEmail() + "\n");
			}
		}
	}

	private void printCursos(){
		System.out.println();
		for(Curso curso : cursos){
			System.out.println("Curso #" + curso.getCodigo() + " " +
					"Nome: " + curso.getNome() + "\n");
		}
	}

	private void printTurmas(){ // OPÇÃO 1
		System.out.println();
		for(Turma turma : turmas){
			printTurma(turma);
		}
	}

	private void printTurma(Turma turma){
		System.out.println("Turma #" + turma.getCodigo() + " " +
				"Disciplina: " + turma.getDisciplina() + " " +
				"Professor: " + turma.getProfessor().getNome() + " " +
				"Quantidade de alunos: " + turma.getQtdAlunos());
		System.out.println("\nAlunos:");
		printAlunos(turma.getAlunos());
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
}
