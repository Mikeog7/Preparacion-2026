package Matrices;
/**
 * MATRIZ 01: Declarar, crear y acceder a una matriz
 *
 * ENUNCIADO:  Crea matrices de enteros, mira sus valores por defecto, lee y
 *             modifica casillas, y provoca (controlado) un acceso fuera de rango.
 * SIRVE PARA: entender qué ES una matriz: un array de arrays. El mundo del juego
 *             de referencia (int[][] mundo) es exactamente eso.
 * CONCEPTO CLAVE:
 *   - new int[FILAS][COLUMNAS]  crea una tabla con todo a 0.
 *   - matriz[fila][columna]     SIEMPRE primero la fila, luego la columna.
 *   - matriz.length             nº de FILAS.
 *   - matriz[0].length          nº de COLUMNAS (longitud de la primera fila).
 *   - Una fila es un array: matriz[fila].length puede ser distinto en cada fila
 *     (matriz irregular). Por eso el bucle interior usa matriz[fila].length.
 * ERROR TIPICO: escribir [columna][fila] (índices al revés) o usar un índice igual
 *             a length (el último válido es length - 1).
 * PRUEBA:     3 filas y 4 columnas; mundo[2][0] vale 1; la última casilla vale 0.
 */
public class Matriz01Declaracion {

    public static void main(String[] args) {
        final int FILAS = 3;
        final int COLUMNAS = 4;

        int[][] vacia = new int[FILAS][COLUMNAS];
        int[][] mundo = {
                { 0, 0, 3, 3 },
                { 0, 2, 2, 0 },
                { 1, 1, 0, 0 }
        };

        System.out.println("Filas de mundo: " + mundo.length);
        System.out.println("Columnas de mundo: " + mundo[0].length);

        System.out.println("--- Matriz vacia (valores por defecto) ---");
        for (int fila = 0; fila < vacia.length; fila++) {
            for (int columna = 0; columna < vacia[fila].length; columna++) {
                System.out.print(vacia[fila][columna] + " ");
            }
            System.out.println();
        }

        vacia[1][2] = 7;                                   // fila 1, columna 2
        System.out.println("vacia[1][2] = " + vacia[1][2]);

        System.out.println("mundo[2][0] = " + mundo[2][0]);   // fila 2, columna 0 -> 1
        System.out.println("mundo[0][2] = " + mundo[0][2]);   // fila 0, columna 2 -> 3
        System.out.println("Ultima casilla = " + mundo[mundo.length - 1][mundo[0].length - 1]);

        System.out.println("--- Matriz irregular ---");
        int[][] irregular = new int[3][];
        irregular[0] = new int[2];
        irregular[1] = new int[4];
        irregular[2] = new int[1];
        for (int fila = 0; fila < irregular.length; fila++) {
            System.out.println("La fila " + fila + " tiene " + irregular[fila].length + " columnas");
        }

        System.out.println("--- Fuera de rango ---");
        try {
            System.out.println(mundo[FILAS][0]);           // la fila 3 no existe
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Error de fila: " + error.getMessage());
        }
        try {
            System.out.println(mundo[0][COLUMNAS]);        // la columna 4 no existe
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Error de columna: " + error.getMessage());
        }
    }
}
