package Arrays;
import java.util.Scanner;

/**
 * ARRAY 17: Matriz cuadrada (diagonales, transpuesta y simetría)
 *
 * ENUNCIADO:  Lee una matriz 3x3 de enteros. Muestra la matriz, la suma de cada
 *             diagonal, la matriz transpuesta y si es simétrica.
 * SIRVE PARA: operar con las relaciones entre índices de fila y columna.
 * CONCEPTO CLAVE:
 *   - Diagonal principal: fila == columna          -> matriz[i][i]
 *   - Diagonal secundaria: columna = TAMANO - 1 - fila -> matriz[i][TAMANO - 1 - i]
 *   - Transpuesta: transpuesta[columna][fila] = matriz[fila][columna]
 *   - Simétrica: matriz[f][c] == matriz[c][f] para TODOS los pares.
 *     Se usa una bandera que solo pasa a false (y se corta el bucle con ella).
 * ERROR TIPICO: transponer sobre la misma matriz (se pisan los valores): hace
 *             falta una matriz nueva.
 * PRUEBA:     1 2 3 / 4 5 6 / 7 8 9 -> diagonales 15 y 15, no simétrica.
 *             1 2 3 / 2 5 6 / 3 6 9 -> simétrica.
 */
public class Array17MatrizCuadrada {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int TAMANO = 3;

        int[][] matriz = new int[TAMANO][TAMANO];
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                System.out.println("Valor [" + fila + "][" + columna + "]: ");
                matriz[fila][columna] = scanner.nextInt();
            }
        }

        System.out.println("--- Matriz ---");
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                System.out.print(matriz[fila][columna] + "\t");
            }
            System.out.println();
        }

        int diagonalPrincipal = 0;
        int diagonalSecundaria = 0;
        for (int indice = 0; indice < TAMANO; indice++) {
            diagonalPrincipal = diagonalPrincipal + matriz[indice][indice];
            diagonalSecundaria = diagonalSecundaria + matriz[indice][TAMANO - 1 - indice];
        }
        System.out.println("Diagonal principal: " + diagonalPrincipal);
        System.out.println("Diagonal secundaria: " + diagonalSecundaria);

        int[][] transpuesta = new int[TAMANO][TAMANO];
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                transpuesta[columna][fila] = matriz[fila][columna];
            }
        }
        System.out.println("--- Transpuesta ---");
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                System.out.print(transpuesta[fila][columna] + "\t");
            }
            System.out.println();
        }

        boolean esSimetrica = true;
        for (int fila = 0; fila < TAMANO && esSimetrica; fila++) {
            for (int columna = 0; columna < TAMANO && esSimetrica; columna++) {
                if (matriz[fila][columna] != matriz[columna][fila]) {
                    esSimetrica = false;
                }
            }
        }
        System.out.println(esSimetrica ? "La matriz es simetrica" : "La matriz NO es simetrica");

        scanner.close();
    }
}
