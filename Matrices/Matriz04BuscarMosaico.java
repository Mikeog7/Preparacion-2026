package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 04: Buscar un tipo de casilla
 *
 * ENUNCIADO:  Pide un tipo de casilla (0 a 9). Muestra las coordenadas de TODAS
 *             sus apariciones y, aparte, busca solo la PRIMERA deteniéndose en
 *             cuanto la encuentre (sin usar break).
 * SIRVE PARA: dominar la búsqueda en dos dimensiones.
 * CONCEPTO CLAVE:
 *   - Recorrido completo: dos for anidados, mostrando (fila, columna).
 *   - Recorrido con parada: DOS while, y la bandera "hallado" en AMBAS condiciones.
 *     El índice solo avanza si todavía no se ha encontrado, para que las
 *     coordenadas guardadas sigan siendo las correctas.
 *   - NO_ENCONTRADO = -1: el 0 es una coordenada válida.
 * ERROR TIPICO: avanzar el índice aunque se haya encontrado (se guarda una posición
 *             de más) o comprobar la bandera solo en el bucle interior.
 * PRUEBA:     buscar 9 -> (0, 9) y (4, 7), primera (0, 9).   buscar 4 -> ninguna.
 */
public class Matriz04BuscarMosaico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int TIPO_MIN = 0;
        final int TIPO_MAX = 9;
        final int NO_ENCONTRADO = -1;

        int[][] mundo = {
                { 0, 0, 0, 3, 3, 0, 0, 7, 7, 9 },
                { 0, 2, 2, 2, 3, 0, 0, 0, 7, 0 },
                { 0, 2, 1, 2, 3, 3, 0, 0, 0, 0 },
                { 0, 2, 2, 2, 0, 3, 8, 8, 0, 0 },
                { 0, 0, 2, 0, 0, 0, 8, 9, 0, 0 },
                { 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }
        };

        int buscado;
        do {
            System.out.println("Tipo de casilla a buscar (0-9): ");
            buscado = scanner.nextInt();
        } while (buscado < TIPO_MIN || buscado > TIPO_MAX);

        // 1) Todas las apariciones.
        int veces = 0;
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                if (mundo[fila][columna] == buscado) {
                    veces++;
                    System.out.println("Aparece en (" + fila + ", " + columna + ")");
                }
            }
        }
        System.out.println("Total de apariciones: " + veces);

        // 2) Solo la primera, sin break.
        int filaHallada = NO_ENCONTRADO;
        int columnaHallada = NO_ENCONTRADO;
        boolean hallado = false;
        int fila = 0;
        while (fila < mundo.length && !hallado) {
            int columna = 0;
            while (columna < mundo[fila].length && !hallado) {
                if (mundo[fila][columna] == buscado) {
                    hallado = true;
                    filaHallada = fila;
                    columnaHallada = columna;
                } else {
                    columna++;
                }
            }
            if (!hallado) {
                fila++;
            }
        }

        if (hallado) {
            System.out.println("Primera aparicion: (" + filaHallada + ", " + columnaHallada + ")");
        } else {
            System.out.println("Ese tipo de casilla no aparece en el mapa");
        }

        scanner.close();
    }
}
