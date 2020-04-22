package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import NuevaPartida.NuevaPartida;

public class Interfaz_del_menu {
	static int seleccion;
	static Scanner tec = new Scanner(System.in);

	public static void main(String[] args) {
		// Tendremos diversos menus, el primer menu dará opcion a empezar
		// partida, escogiendo los nombres del usuario y el tamaño de la matriz.
		// El segundo menú nos dará la opción de contiuar con otra partida,
		// mostrar resultados o salir del juego, finalizando el programa.

		int opcion = 0;
		do {

			opcion = seleccionMenu();
			switch (opcion) {
			case 1:
				NuevaPartida.nuevaPartida();
				break;
			case 2:
				NuevaPartida.resultados();
				break;
			case 3:
				System.out.println("Fin del programa");
				break;
			}

		} while (opcion != 3); // Cuando es 3 termina el bucle

	}

	public static int seleccionMenu() {

		// representación del menú por pantalla

		System.out.println("(̅_̅_̅(̲̲̲̲̲̅̅̅̅̅̅(̅_̅_̅_̅_̅_̅̅ _̅_̅_̅_̅_̅̅_(̅_̅_̅(̅_̅_̅_̅_̅_̅_̅_̅̅()ڪ ");
		System.out.println("(̅_̅_̅(̲̲̲̲̲̅̅̅̅̅̅(̅_̅_̲̲̲̅̅̅l̲̅u̲̅c̲̅k̲̅y̲̲̅̅̅_̅_ ̅_̅() ڪے");
		System.out.println("(̅_̅_̅(̲̲̲̲̲̅̅̅̅̅̅(̅_̅_̲̅м̲̅a̲̅я̲̅l̲̅b̲̅o̲̅r̲̅o̲̅̅ _̅_̅_̅()ڪے\n");

		System.out.println("|----------------------------------|");
		System.out.println("|1.- Nueva partida.                |");
		System.out.println("|2.- Resultados (entre partidas).  |");
		System.out.println("|3.- Salir del juego               |");
		System.out.println("|----------------------------------|");
		System.out.println(" ");
		System.out.println("Escriba aqui su eleccion: ");

		// selección del menú
		boolean ok = false;
		do {
			try {
				seleccion = tec.nextInt();
				tec.nextLine();
				if (seleccion == 1) {
					System.out.println("------------------------------------");
					System.out.println("Usted ha escogido -Nueva partida-");
					System.out.println("------------------------------------");
					ok = true;
				} else if (seleccion == 2) {
					System.out.println("------------------------------------");
					System.out.println("Usted ha escogido -Resultados (entre partidas)-");
					System.out.println("------------------------------------");
					ok = true;
				} else if (seleccion == 3) {
					System.out.println("------------------------------------");
					System.out.println("Usted ha escogido -Salir del juego-");
					System.out.println("------------------------------------");
					ok = true;

				} else {
					System.out.println("No ha escogido una de las opciones del menu, vuelva a escoger: ");
				}

			} catch (InputMismatchException e) {
				System.out.println("No ha escogido una de las opciones del menu, vuelva a escoger: ");
				tec.nextLine();
			}

		} while (!ok);
		return seleccion;
	}
}
