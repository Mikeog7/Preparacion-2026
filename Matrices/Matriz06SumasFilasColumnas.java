package Matrices;
/**
 * MATRIZ 06: Sumas por filas, por columnas y diagonal
 *
 * ENUNCIADO:  Dada una matriz de recursos por zona (4 filas x 5 columnas), calcula
 *             la suma de cada fila, de cada columna, el total, la fila y la
 *             columna con más recursos, el valor máximo con su posición y la
 *             suma de la diagonal principal.
 * SIRVE PARA: el recorrido por filas frente al recorrido por columnas.
 * CONCEPTO CLAVE:
 *   - Por filas: bucle exterior = filas, interior = columnas.
 *   - Por columnas: se INTERCAMBIAN los bucles (exterior = columnas).
 *   - La suma parcial se declara DENTRO del bucle exterior para reiniciarse.
 *   - Diagonal principal: matriz[i][i], con i menor que el menor de filas y columnas
 *     (la matriz no es cuadrada).
 * ERROR TIPICO: declarar la suma fuera del bucle exterior (la fila 2 arrastra la 1)
 *             o mezclar los índices de fila y columna.
 * PRUEBA:     filas 19, 19, 22, 23;  columnas 18, 12, 20, 15, 18;  total 83;
 *             fila mayor = 3, columna mayor = 2 (empezando en 0);
 *             maximo 9 en (2, 0);  diagonal 3 + 0 + 3 + 0 = 6.
 */
public class Matriz06SumasFilasColumnas {

    public static void main(String[] args) {
        int[][] recursos = {
                { 3, 5, 2, 8, 1 },
                { 4, 0, 7, 2, 6 },
                { 9, 1, 3, 5, 4 },
                { 2, 6, 8, 0, 7 }
        };

        int filas = recursos.length;
        int columnas = recursos[0].length;

        int total = 0;
        int filaMayor = 0;
        int mayorSumaFila = 0;
        System.out.println("--- Suma por filas ---");
        for (int fila = 0; fila < filas; fila++) {
            int sumaFila = 0;                              // se reinicia en cada fila
            for (int columna = 0; columna < columnas; columna++) {
                sumaFila = sumaFila + recursos[fila][columna];
            }
            System.out.println("Fila " + fila + ": " + sumaFila);
            total = total + sumaFila;
            if (fila == 0 || sumaFila > mayorSumaFila) {
                mayorSumaFila = sumaFila;
                filaMayor = fila;
            }
        }

        int columnaMayor = 0;
        int mayorSumaColumna = 0;
        System.out.println("--- Suma por columnas ---");
        for (int columna = 0; columna < columnas; columna++) {
            int sumaColumna = 0;                           // se reinicia en cada columna
            for (int fila = 0; fila < filas; fila++) {
                sumaColumna = sumaColumna + recursos[fila][columna];
            }
            System.out.println("Columna " + columna + ": " + sumaColumna);
            if (columna == 0 || sumaColumna > mayorSumaColumna) {
                mayorSumaColumna = sumaColumna;
                columnaMayor = columna;
            }
        }

        int valorMaximo = recursos[0][0];
        int filaMaximo = 0;
        int columnaMaximo = 0;
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (recursos[fila][columna] > valorMaximo) {
                    valorMaximo = recursos[fila][columna];
                    filaMaximo = fila;
                    columnaMaximo = columna;
                }
            }
        }

        int limiteDiagonal = filas < columnas ? filas : columnas;
        int diagonal = 0;
        for (int indice = 0; indice < limiteDiagonal; indice++) {
            diagonal = diagonal + recursos[indice][indice];
        }

        System.out.println("Total: " + total);
        System.out.println("Fila con mas recursos: " + filaMayor + " (" + mayorSumaFila + ")");
        System.out.println("Columna con mas recursos: " + columnaMayor + " (" + mayorSumaColumna + ")");
        System.out.println("Valor maximo: " + valorMaximo + " en (" + filaMaximo + ", " + columnaMaximo + ")");
        System.out.println("Diagonal principal: " + diagonal);
    }
}
