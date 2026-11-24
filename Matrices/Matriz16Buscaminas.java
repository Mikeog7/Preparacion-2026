package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 16: Buscaminas (contar minas vecinas)
 *
 * ENUNCIADO:  Dado un campo de minas (1 = mina), calcula para cada casilla sin mina
 *             cuántas minas hay en sus 8 casillas vecinas. Dibuja el campo
 *             resultante y permite consultar una casilla.
 * SIRVE PARA: el recorrido de VECINOS (8 direcciones) con control de bordes:
 *             un clásico de examen.
 * CONCEPTO CLAVE:
 *   - Dos bucles de desplazamiento (-1, 0, 1) para recorrer los vecinos.
 *   - Se descarta la propia casilla (desplazamiento 0, 0).
 *   - Se descartan los vecinos fuera del mapa ANTES de leerlos.
 *   - Se guarda el resultado en otra matriz (-1 = mina) sin tocar la original.
 * ERROR TIPICO: contar la propia casilla como vecina, o leer un vecino fuera del mapa.
 * PRUEBA:     la casilla (0, 2) tiene 2 minas vecinas; la (3, 1) tiene 2; la (5, 0) tiene 0.
 *             Consultar (0, 3) -> mina.
 */
public class Matriz16Buscaminas {

    static final int MINA = 1;
    static final int DESPLAZAMIENTO_MIN = -1;
    static final int DESPLAZAMIENTO_MAX = 1;

    static int contarMinasVecinas(int[][] minas, int fila, int columna) {
        int vecinas = 0;
        for (int desplazamientoFila = DESPLAZAMIENTO_MIN; desplazamientoFila <= DESPLAZAMIENTO_MAX; desplazamientoFila++) {
            for (int desplazamientoColumna = DESPLAZAMIENTO_MIN; desplazamientoColumna <= DESPLAZAMIENTO_MAX; desplazamientoColumna++) {
                int filaVecina = fila + desplazamientoFila;
                int columnaVecina = columna + desplazamientoColumna;
                boolean esLaMisma = desplazamientoFila == 0 && desplazamientoColumna == 0;
                boolean dentroDelCampo = filaVecina >= 0 && filaVecina < minas.length
                        && columnaVecina >= 0 && columnaVecina < minas[0].length;
                if (!esLaMisma && dentroDelCampo && minas[filaVecina][columnaVecina] == MINA) {
                    vecinas++;
                }
            }
        }
        return vecinas;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CASILLA_MINA = -1;

        int[][] minas = {
                { 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        int[][] campo = new int[minas.length][minas[0].length];
        for (int fila = 0; fila < minas.length; fila++) {
            for (int columna = 0; columna < minas[fila].length; columna++) {
                if (minas[fila][columna] == MINA) {
                    campo[fila][columna] = CASILLA_MINA;
                } else {
                    campo[fila][columna] = contarMinasVecinas(minas, fila, columna);
                }
            }
        }

        System.out.println("--- Campo resuelto ---");
        for (int fila = 0; fila < campo.length; fila++) {
            for (int columna = 0; columna < campo[fila].length; columna++) {
                if (campo[fila][columna] == CASILLA_MINA) {
                    System.out.print(" * ");
                } else if (campo[fila][columna] == 0) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + campo[fila][columna] + " ");
                }
            }
            System.out.println();
        }

        int filaConsulta;
        do {
            System.out.println("Fila a consultar (0-" + (campo.length - 1) + "): ");
            filaConsulta = scanner.nextInt();
        } while (filaConsulta < 0 || filaConsulta >= campo.length);

        int columnaConsulta;
        do {
            System.out.println("Columna a consultar (0-" + (campo[0].length - 1) + "): ");
            columnaConsulta = scanner.nextInt();
        } while (columnaConsulta < 0 || columnaConsulta >= campo[0].length);

        if (campo[filaConsulta][columnaConsulta] == CASILLA_MINA) {
            System.out.println("BOOM! Ahi habia una mina.");
        } else {
            System.out.println("Hay " + campo[filaConsulta][columnaConsulta] + " minas alrededor.");
        }

        scanner.close();
    }
}
