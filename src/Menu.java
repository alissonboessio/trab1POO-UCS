import java.util.Scanner;

import Objetos.Curso;
import Objetos.Turma;
import Util.Util;

public class Menu {
	
	private static final int MAX_TURMAS = 10;
	private static final int MAX_CURSOS = 4;
	
	protected int qtdTurmas = 0;

	private static final Curso[] cursos = new Curso[MAX_CURSOS];
	private static final Turma[] turmas = new Turma[MAX_TURMAS];
	
	public void setCursos() {
		cursos[0] = new Curso(1, "Ciência da Computação");
		cursos[1] = new Curso(2, "Engenharia de Software");
		cursos[2] = new Curso(3, "Engenharia de COmputação");
		cursos[3] = new Curso(4, "Análise e Desenvolvimento de Sistemas");		
		
	}

	public void mostrar() {
		
		Scanner in = new Scanner(System.in);
		
		int opc = 5;
				
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

			case 1:
				break;
			case 2:
				adicionaTurma();
				break;
			case 3:
				break;
			case 4:
				break;
			
			}
			
			
		} while(opc != 5);
		
		in.close();
	}
	
	private void adicionaTurma() {			
		
		if(this.qtdTurmas < this.MAX_TURMAS) {
			System.out.println(this.qtdTurmas);
			Turma turma = new Turma();
			
			System.out.println("Dados da turma:");	
			turma.setCodigo(Util.leEntradaInt("Codigo: "));
			turma.setDisciplina(Util.leEntradaString("Nome da Disciplina: "));

			//TODO resto da funcao
			
		}else {
			System.out.println("Limite de turmas atingido!");
			
		}		
		
	}
	
	
}
