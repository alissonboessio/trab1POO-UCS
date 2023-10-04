import java.util.Scanner;

import Objetos.Aluno;
import Objetos.Curso;
import Objetos.Professor;
import Objetos.Turma;
import Util.Util;

public class Menu {
	
	private static final int MAX_TURMAS = 1;
	private static final int MAX_CURSOS = 4;
	private static final int MAX_PROFESSORES = MAX_TURMAS;

	protected int qtdTurmas = 0;
	protected int qtdProfessores = 0;

	private static final Curso[] cursos = new Curso[MAX_CURSOS];
	private static final Turma[] turmas = new Turma[MAX_TURMAS];
	private static final Professor[] professores = new Professor[MAX_PROFESSORES];
	
	public void initCursos() {
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
			
			opc = Util.leEntradaInt("");

            switch (opc) {
                case 1 -> printTurmas();
                case 2 -> adicionaTurma();
                case 3 -> {
                	if(qtdTurmas > 0) {
                		printTurmasCodigo();                    	
                    	do {
                    		if(consultarTurmaPorCodigo(Util.leEntradaString("Digite o código da turma: "))){;
                    			break;
                    		}
                    		
                    	}while(true);                        
                        
                	}else {
                		System.out.println("Ainda nao ha turmas cadastradas!");
                	}
                	Util.esperaEntrada();
                	
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
			turma.setCodigo(Util.leEntradaString("Codigo da Turma: "));
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
			System.out.println();
			int alunoOption = 0;
			do{
				alunoOption = Util.leEntradaInt("O que você deseja fazer?\n1- Atribuir um novo aluno à turma. 2- Ver alunos já cadastrados na turma. 3- Sair: ");
				if(alunoOption == 1){
					if(turma.getQtdAlunosRestante() > 0) {
						if(!turma.addAluno(novoAluno(turma.getQtdAlunos()))) {
						break;
					} 
					}else{
						System.out.println("Limite de alunos atingido!");
					}
					
				}
				else if(alunoOption == 2){
					System.out.println(turma.printAlunos());					
				}
				
			}while(alunoOption != 3);

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
				System.out.println(professores[i]);
			}
			
		}
	}

	private void printCursos(){
		System.out.println();
		for(Curso curso : cursos){
			System.out.println(curso);
		}
	}

	private void printTurmas(){ // OPÇÃO 1
		if (!isTurmasEmpty()){
			System.out.println();
			for(int i=0;i<qtdTurmas;i++){
				System.out.println(turmas[i].printAlunosSemTurma());
			}
		}
		else{
			System.out.println("Ainda não há turmas cadastradas.");
		}
	}	

	private void printTurmasCodigo(){
		if (!isTurmasEmpty()){
			System.out.println();
			for(int i=0;i<qtdTurmas;i++){
				System.out.println("Turma: " + turmas[i].getCodigo() + " - " + turmas[i].getDisciplina());
			}
		}
		else{
			System.out.println("Ainda não há turmas cadastradas.");
		}
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

		
		do {
			printCursos();
			int codigoCurso = Util.leEntradaInt("Qual o código do curso do aluno? ");
			if(codigoCurso < 1 || codigoCurso > 4) {
				System.out.println("Curso inexistente!");
				continue;
			}
			aluno.setCurso(cursos[codigoCurso - 1]);
			break;
		}while(true);
		
		return aluno;
	}

	private boolean consultarTurmaPorCodigo(String codigo){ // OPÇÃO 3
		for (int i = 0; i < turmas.length; i++) {
			if(turmas[i].getCodigo().equalsIgnoreCase(codigo)) {
				System.out.println(turmas[i]);
				return true;
			}
		}
		return false;
	}

	private void printDetalhesGerais(){ // OPÇÃO 4
		// para cada turma
		int qteMatriculadosInstituicao = 0;
		double percentualAprovacaoTurmas = 0;
		for(int i=0;i<qtdTurmas;i++){
			System.out.println(turmas[i].printAlunosSemTurma());
			
			if(turmas[i].getQtdAlunos() <= 0){ // para não gerar erro de /zero
				continue;
			}			

			percentualAprovacaoTurmas += (double) turmas[i].getQtdAprovados() /turmas[i].getQtdAlunos() * 100;
		
			System.out.println("Quantidade de aprovados: " + turmas[i].getQtdAprovados());
			System.out.println("Porcentagem de aprovação: " + turmas[i].getQtdAprovados()/turmas[i].getQtdAlunos() * 100 + "%\n");
					
			qteMatriculadosInstituicao += turmas[i].getQtdAlunos();
		}

		// Dados gerais da instituição
		System.out.println("-- Dados gerais da instituição --");
		System.out.println("Quantidade de alunos matriculados na instituição: " + qteMatriculadosInstituicao);
		if(qtdTurmas > 0){ // para não gerar erro de /zero
			System.out.println("Média de aprovação nas turmas da instituição: " + percentualAprovacaoTurmas/qtdTurmas + "%");
		}
		Util.esperaEntrada();
	}
}
