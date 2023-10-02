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
		cursos[2] = new Curso(3, "Engenharia de Computação");
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
                    consultarTurmaPorCodigo(codigo);
                }
                case 4 -> printDetalhesGerais();
                case 5 -> {
					String resposta = Util.leEntradaString("Deseja realmente sair? Digite S para sim ou N para não: ");
					if(resposta.equalsIgnoreCase("N")){
						opc = 0;
                    }
					else{
						System.out.println("Programa encerrado.");
					}
                }
            }
		} while(opc != 5);
		in.close();
	}
	
	private void adicionaTurma() { // OPÇÃO 2
		if(this.qtdTurmas < MAX_TURMAS) {
			Turma turma = new Turma();
			System.out.println("Dados da turma:");
			turma.setCodigo(qtdTurmas);
			turma.setDisciplina(Util.leEntradaString("Nome da Disciplina: "));

			// PROFESSOR
			System.out.println();
			int profOption = Util.leEntradaInt("Deseja atribuir a esta turma um professor novo ou um já existente?\n1- Já existente. 2- Novo: ");
			while (profOption != 1 && profOption != 2) {
				profOption = Util.leEntradaInt("Deseja atribuir a esta turma um professor novo ou um já existente?\n1- Já existente. 2- Novo: ");
			}

			if(profOption == 1){
				if(qtdProfessores > 0){
					System.out.println("Professores cadastrados:");
					printProfessores(); // printa as opções de professores cadastrados
					int codigoProfessor = Util.leEntradaInt("Digite o código do professor desejado: "); // pedir para escolher o código do professor existente
					turma.setProfessor(professores[codigoProfessor]);// adicionar o professor existente à turma através do código escolhido
				}
				else{
					System.out.println("Ainda não há professores cadastrados. Você será redirecionado para a seção de cadastro de um novo professor.");
					atribuirNovoProfessorATurma(turma);
				}
			}
			else { // será 2 de qualquer forma
				atribuirNovoProfessorATurma(turma);
			}

			// ALUNOS
			Aluno[] alunos = new Aluno[30];
			System.out.println();
			int alunoOption = 0;
			int numAlunos = 0;
			while(numAlunos <= 30){
				alunoOption = Util.leEntradaInt("O que você deseja fazer?\n1- Atribuir um novo aluno à turma. 2- Ver alunos já cadastrados na turma. 3- Sair: ");
				if(alunoOption == 1){
					if(numAlunos < 30) {
						Aluno aluno = novoAluno(numAlunos);
						alunos[numAlunos] = aluno;
						numAlunos++;
					} else {
						System.out.println("A turma já atingiu o limite de 30 alunos.");
					}
				}
				else if(alunoOption == 2){
					printAlunos(alunos, numAlunos);
				}
				else if(alunoOption == 3){
					break;
				}
			}
			turma.setAlunos(alunos);
			turma.setQtdAlunos(numAlunos);

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
			for(int i=0; i<qtdProfessores; i++){
				System.out.println("Professor código: " + professores[i].getCodigo() +
						" Nome: " + professores[i].getNome() +
						" Email: " + professores[i].getEmail() +
						" Universidade de formação: " + professores[i].getUnivFormacao() + "\n");
			}
		}
	}

	private void printAlunos(Aluno[] alunos, int numAlunos){
		if(numAlunos == 0){
			System.out.println("Ainda não há alunos cadastrados.");
		}
		else{
			System.out.println();
			for(int i = 0; i<numAlunos; i++){
				System.out.println("Aluno código: " + alunos[i].getCodigo() +
						" Nome: " + alunos[i].getNome() +
						" Email: " + alunos[i].getEmail() +
						" N1: " + alunos[i].getN1() +
						" N2: " + alunos[i].getN2() +
						" N3: " + alunos[i].getN3() +
						" Média: " + alunos[i].calculaMedia() +
						(alunos[i].calculaMedia() >= 7.0 ? " Situação: Aprovado." : " Situação: Reprovado.\n"));
			}
		}
	}

	private void printCursos(){
		System.out.println();
		for(Curso curso : cursos){
			System.out.println("Curso código: " + curso.getCodigo() +
					" Nome: " + curso.getNome());
		}
	}

	private void printTurmas(){ // OPÇÃO 1
		if (!isTurmasEmpty()){
			System.out.println();
			for(int i=0;i<qtdTurmas;i++){
				printTurmaSemAlunos(turmas[i]);
			}
		}
		else{
			System.out.println("Ainda não há turmas cadastradas.");
		}
	}

	private void printTurma(Turma turma){
		System.out.println("Turma código: " + turma.getCodigo() +
				"\nDisciplina: " + turma.getDisciplina() +
				"\nProfessor: " + turma.getProfessor().getNome() +
				"\nQuantidade de alunos: " + turma.getQtdAlunos());
		System.out.println("\nAlunos:");
		printAlunos(turma.getAlunos(), turma.getQtdAlunos());
	}

	private void printTurmaSemAlunos(Turma turma){
		System.out.println("Turma código: " + turma.getCodigo() +
				"\nDisciplina: " + turma.getDisciplina() +
				"\nProfessor: " + turma.getProfessor().getNome() +
				"\nQuantidade de alunos matriculados: " + turma.getQtdAlunos());
	}

	private void atribuirNovoProfessorATurma(Turma turma){
		Professor professorNovo = new Professor();
		professorNovo.setCodigo(qtdProfessores);
		professorNovo.setNome(Util.leEntradaString("Nome do professor: "));
		professorNovo.setEmail(Util.leEntradaString("Email do professor: "));
		professorNovo.setUnivFormacao(Util.leEntradaString("Universidade de formação do professor: "));
		turma.setProfessor(professorNovo);

		professores[qtdProfessores] = professorNovo;
		qtdProfessores++;
	}

	private Aluno novoAluno(int codigo) {
		Aluno aluno = new Aluno();
		aluno.setCodigo(codigo);
		aluno.setNome(Util.leEntradaString("Nome do aluno: "));
		aluno.setEmail(Util.leEntradaString("Email do aluno: "));
		aluno.setN1(Util.leEntradaDouble("Nota N1: ")); // atribuir valor double com , e não .
		aluno.setN2(Util.leEntradaDouble("Nota N2: "));
		aluno.setN3(Util.leEntradaDouble("Nota N3: "));

		printCursos();
		int codigoCurso = Util.leEntradaInt("Qual o código do curso do aluno? ");
		aluno.setCurso(cursos[codigoCurso - 1]);
		return aluno;
	}

	private void consultarTurmaPorCodigo(int codigo){ // OPÇÃO 3
		Turma turma = turmas[codigo];
		printTurma(turma);
	}

	private void printDetalhesGerais(){ // OPÇÃO 4
		// para cada turma
		int qteMatriculadosInstituicao = 0;
		double percentualAprovacaoTurmas = 0;
		for(int i=0;i<qtdTurmas;i++){
			printTurmaSemAlunos(turmas[i]);
			int qteAprovados = 0;
			for(int j=0; j<turmas[i].getQtdAlunos(); j++){
				qteMatriculadosInstituicao++;
				if(turmas[i].getAlunos()[j].calculaMedia() >= 7){
					qteAprovados++;
				}
			}
			System.out.println("Quantidade de aprovados: " + qteAprovados);
			System.out.println("Porcentagem de aprovação: " + qteAprovados/turmas[i].getQtdAlunos() * 100 + "%\n");
			if(turmas[i].getQtdAlunos() > 0){ // para não gerar erro de /zero
				percentualAprovacaoTurmas += (double) qteAprovados /turmas[i].getQtdAlunos() * 100;
			}
		}

		// Dados gerais da instituição
		System.out.println("-- Dados gerais da instituição --");
		System.out.println("Quantidade de alunos matriculados na instituição: " + qteMatriculadosInstituicao);
		if(qtdTurmas > 0){ // para não gerar erro de /zero
			System.out.println("Média de aprovação nas turmas da instituição: " + percentualAprovacaoTurmas/qtdTurmas + "%");
		}
	}
}
