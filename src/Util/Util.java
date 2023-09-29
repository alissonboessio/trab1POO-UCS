package Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Util {

	public static int leEntradaInt(String pergunta) {
		Scanner in = new Scanner(System.in);
		int var = 0;
		boolean leu = false;
		do {
			try {
				System.out.print(pergunta);
				var = in.nextInt();
				leu = true;
			}catch(InputMismatchException e) {
			}
			in.nextLine();
		}while(!leu);
		return var;
	}

	public static String leEntradaString(String pergunta) {
		Scanner in = new Scanner(System.in);
		String var = "";
		boolean leu = false;
		do {
			try {
				System.out.print(pergunta);
				var = in.nextLine();
				leu = true;
			}catch(InputMismatchException e) {
			}
			
		}while(!leu);
		return var;
	}

	public static double leEntradaDouble(String pergunta) {
		Scanner in = new Scanner(System.in);
		double var = 0;
		boolean leu = false;

		do {
			try {
				System.out.print(pergunta);
				var = in.nextDouble();
				leu = true;
			}catch(InputMismatchException e) {

			}
			in.nextLine();

		}while(!leu);
		return var;
	}
}
