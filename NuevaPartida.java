package NuevaPartida;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NuevaPartida {
	static int[] coorArray;
	static int[] posiciones;
	static int ByH;
	static int ganadas1 = 0, ganadas2 = 0;
	static Scanner tec = new Scanner(System.in);

	public static void nuevaPartida() {
		System.out.println("El primer jugador utilizará la ficha 1");
		System.out.println("El segundo jugador utlilizará la ficha 2");
		System.out.println();
		System.out.println();
		// --------------------------------------------------------------------------------------
		// TAMAÑO MATRIZ
		System.out.println(
				"Introduce el tamaño de  casillas de la longitud del juego (sera la misma para la altura) con un maximo de 9 casillas:");
		int ByH = 0;
		boolean ok = false;
		do {
			try {
				do {
					ByH = tec.nextInt();
					tec.nextLine();
					if (2 > ByH || ByH > 9) {
						System.out.println("Número de casillas incorrectas, escoja otro numero: ");
					}
				} while (2 > ByH || ByH > 9);
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Debe introducir un número: ");
				tec.nextLine();
			}
		} while (!ok);

		// --------------------------------------------------------------------------------------------
		// Coordenadas del tablero
		int[][] tablero = new int[ByH][ByH];
		mostrarTablero(tablero);
		// movimientos jugadores
		boolean ganador1 = false;
		boolean ganador2 = false;

		do {

			System.out.println("Escriba las coordenadas j1: y,x  ");
			// J1

			String coord1 = tec.next();
			int[] coorArray = jugador1(coord1);

			System.out.println("Coordenadas J1: " + Arrays.toString(coorArray));
			for (int i = 0; i <= tablero.length; i++) {
				if (i == coorArray[0]) {
					for (int j = 0; j <= tablero.length; j++) {
						if (j == coorArray[1])
							tablero[i - 1][j - 1] = 1;
					}
				}
			}
			mostrarTablero(tablero);

			// Comprobación
			if (ganador(tablero) == 1) {
				System.out.println("Jugador 1 gana ");
				ganador1 = true;
				ganadas1++;

				System.out.println("Fin de la partida ");
				System.out.println();
				System.out.println();
				System.out.println();
			}

			// -------------------------------------------------------------------------------
			if (ganador1 == false) {
				// J2
				System.out.println("Escriba las coordenadas j2: y,x  ");
				String coord2 = tec.next();
				int[] coorArray2 = jugador2(coord2);

				System.out.println("Coordenadas J2: " + Arrays.toString(coorArray2));
				for (int i = 0; i <= tablero.length; i++) {
					if (i == coorArray2[0]) {
						for (int j = 0; j <= tablero.length; j++) {
							if (j == coorArray2[1])
								tablero[i - 1][j - 1] = 2;
						}
					}
				}
				mostrarTablero(tablero);
				if (ganador(tablero) == 2) {
					System.out.println("Jugador 2 gana ");
					ganador2 = true;
					ganadas2++;
					System.out.println("Fin de la partida ");
					System.out.println();
					System.out.println();
					System.out.println();
				}

			}
		} while (ganador2 == false && ganador1 == false);
	}

	// ----------------------------------------------------------------------------------------------
	// ahora el usuario 1 debe colocar su ficha
	public static int[] jugador1(String coord1) {
		// Jugador1
		boolean ok = true;
		do {
			try {
				tec.nextLine();
				int posComa = coord1.indexOf(',');

				int numX = Integer.parseInt(coord1.substring(0, posComa));
				int numY = Integer.parseInt(coord1.substring(posComa + 1));
				posiciones = new int[2];
				posiciones[0] = numX;
				posiciones[1] = numY;

			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("Vuelva a escribir la posicion correctamente: ");
				ok = false;
			}
		} while (ok == false);

		return posiciones;
	}

	// -----------------------------------------------------------------------------------------------
	// ahora el usuario 2 debe colocar su ficha
	public static int[] jugador2(String coord2) {
		// Jugador2
		int posComa = coord2.indexOf(',');
		int numX = Integer.parseInt(coord2.substring(0, posComa));
		int numY = Integer.parseInt(coord2.substring(posComa + 1));

		int[] posiciones = new int[2];
		posiciones[0] = numX;
		posiciones[1] = numY;
		return posiciones;

	}

	// creación del tablero
	public static void mostrarTablero(int[][] tablero) {
		// Fila con los numeros de columna
		System.out.print("  1");
		for (int numcas = 1; numcas < tablero.length; numcas++) {
			System.out.print(" " + (numcas + 1));
		}
		System.out.print(" X");
		System.out.println();
		// --
		for (int i = 0; i < tablero.length; i++) {
			// Encabezado de la fila
			// System.out.println();
			System.out.print((i + 1) + " ");
			// --
			for (int j = 0; j < tablero[0].length; j++) {
				System.out.print(tablero[i][j] + " ");

			}

			System.out.println();
		}
		System.out.println("y");
		System.out.println("============================");
	}

	// comprobación de las lineas
	public static int ganador(int[][] tablero) {
		int gana = 0;

		// Comprobar filas
		for (int i = 0; i < tablero.length && gana == 0; i++) {
			int ceros = 0;
			int unos = 0;
			int doses = 0;
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] == 0) {
					ceros++;
				} else if (tablero[i][j] == 1) {
					unos++;
				} else if (tablero[i][j] == 2) {
					doses++;
				}
			}
			if (unos == tablero[i].length) {
				gana = 1;
			} else if (doses == tablero[i].length) {
				gana = 2;
			}
		}

		if (gana == 0) {
			// Comprobar columnas
			for (int j = 0; j < tablero[0].length && gana == 0; j++) {
				int ceros = 0;
				int unos = 0;
				int doses = 0;
				for (int i = 0; i < tablero.length; i++) {
					if (tablero[i][j] == 0) {
						ceros++;
					} else if (tablero[i][j] == 1) {
						unos++;
					} else if (tablero[i][j] == 2) {
						doses++;
					}
				}
				if (unos == tablero.length) {
					gana = 1;
				} else if (doses == tablero.length) {
					gana = 2;
				}
			}

		}
		// Comprobar diagonal superior
		if (gana == 0) {
			int ceros = 0;
			int unos = 0;
			int doses = 0;
			for (int i = 0; i < tablero.length && gana == 0; i++) {
				if (i == i) {
					if (tablero[i][i] == 0) {
						ceros++;
					} else if (tablero[i][i] == 1) {
						unos++;
					} else if (tablero[i][i] == 2) {
						doses++;
					}
					if (unos == tablero[i].length) {
						gana = 1;
					} else if (doses == tablero[i].length) {
						gana = 2;
					}
				}
			}
		}
		// Comprobar diagonal inferior
		if (gana == 0) {
			int ceros = 0;
			int unos = 0;
			int doses = 0;
			int aux = 0;
			for (int i = tablero.length - 1; i >= 0 && gana == 0; i--) {
				if (tablero[i][aux] == 0) {
					ceros++;
				} else if (tablero[i][aux] == 1) {
					unos++;
				} else if (tablero[i][aux] == 2) {
					doses++;
				}
				aux++;
				if (unos == tablero[i].length) {
					gana = 1;
				} else if (doses == tablero[i].length) {
					gana = 2;
				}
			}

		}
		return gana;
	}

	public static void resultados() {
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("El jugador 1 ha ganado " + ganadas1 + " partidas. ");
		System.out.println("El jugador 2 ha ganado " + ganadas2 + " partidas. ");

		if (ganadas1 > ganadas2) {
			System.out.println(
					"FELICIDADES JUGADOR 1, VAS GANANDO CON " + (ganadas1 - ganadas2) + " PUNTOS DE DIFERENCIA.");
		} else if (ganadas1 < ganadas2) {
			System.out.println(
					"FELICIDADES JUGADOR 2, VAS GANANDO CON " + (ganadas2 - ganadas1) + " PUNTOS DE DIFERENCIA.");
		} else {
			System.out.println();
			System.out.println("VAIS EMPATE");
		}

		System.out.println();
		System.out.println();
		System.out.println();

	}
}