package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 18: Mapa de distancias (camino más corto por propagación)
 *
 * ENUNCIADO:  Desde la posición (5, 0), calcula cuántos pasos hacen falta para llegar
 *             a cada casilla transitable moviéndose en 4 direcciones (esquivando
 *             agua, montañas, árboles y edificios). Pide un destino y muestra la
 *             distancia mínima, o avisa si no se puede llegar.
 * SIRVE PARA: el algoritmo de "ola": se avanza por capas, como una onda en el agua.
 * CONCEPTO CLAVE:
 *   - Matriz distancia con SIN_VISITAR = -1 en todo; el origen vale 0.
 *   - Paso k: todas las casillas con distancia k "contagian" a sus vecinos
 *     transitables aún sin visitar, que reciben k + 1.
 *   - Se repite mientras el paso anterior haya marcado alguna casilla nueva.
 *   - El método marcar() comprueba borde, terreno y que no esté visitada, y dice
 *     si ha marcado algo.
 *   - La primera vez que una casilla recibe valor, ESE es su camino más corto.
 * ERROR TIPICO: permitir revisitar casillas (bucle infinito o distancias erróneas).
 * PRUEBA:     destino (0, 9) -> distancia 14.   Destino (2, 2) (un edificio) ->
 *             "El destino no es transitable".
 */
public class Matriz18MapaDistancias {

    static final int SIN_VISITAR = -1;

    static boolean marcar(int[][] mundo, boolean[] transitable, int[][] distancia, int fila, int columna, int valor) {
        boolean marcada = false;
        boolean dentroDelMapa = fila >= 0 && fila < mundo.length && columna >= 0 && columna < mundo[0].length;
        if (dentroDelMapa && transitable[mundo[fila][columna]] && distancia[fila][columna] == SIN_VISITAR) {
            distancia[fila][columna] = valor;
            marcada = true;
        }
        return marcada;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };
        final int ORIGEN_FILA = 5;
        final int ORIGEN_COLUMNA = 0;

        int[][] mundo = {
                { 0, 0, 0, 3, 3, 0, 0, 7, 7, 9 },
                { 0, 2, 2, 2, 3, 0, 0, 0, 7, 0 },
                { 0, 2, 1, 2, 3, 3, 0, 0, 0, 0 },
                { 0, 2, 2, 2, 0, 3, 8, 8, 0, 0 },
                { 0, 0, 2, 0, 0, 0, 8, 9, 0, 0 },
                { 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }
        };

        int destinoFila;
        do {
            System.out.println("Fila de destino (0-" + (mundo.length - 1) + "): ");
            destinoFila = scanner.nextInt();
        } while (destinoFila < 0 || destinoFila >= mundo.length);

        int destinoColumna;
        do {
            System.out.println("Columna de destino (0-" + (mundo[0].length - 1) + "): ");
            destinoColumna = scanner.nextInt();
        } while (destinoColumna < 0 || destinoColumna >= mundo[0].length);

        int[][] distancia = new int[mundo.length][mundo[0].length];
        for (int fila = 0; fila < distancia.length; fila++) {
            for (int columna = 0; columna < distancia[fila].length; columna++) {
                distancia[fila][columna] = SIN_VISITAR;
            }
        }
        distancia[ORIGEN_FILA][ORIGEN_COLUMNA] = 0;

        int paso = 0;
        boolean hayAvance;
        do {
            hayAvance = false;
            for (int fila = 0; fila < mundo.length; fila++) {
                for (int columna = 0; columna < mundo[fila].length; columna++) {
                    if (distancia[fila][columna] == paso) {
                        if (marcar(mundo, TRANSITABLE, distancia, fila - 1, columna, paso + 1)) {
                            hayAvance = true;
                        }
                        if (marcar(mundo, TRANSITABLE, distancia, fila + 1, columna, paso + 1)) {
                            hayAvance = true;
                        }
                        if (marcar(mundo, TRANSITABLE, distancia, fila, columna - 1, paso + 1)) {
                            hayAvance = true;
                        }
                        if (marcar(mundo, TRANSITABLE, distancia, fila, columna + 1, paso + 1)) {
                            hayAvance = true;
                        }
                    }
                }
            }
            paso++;
        } while (hayAvance);

        System.out.println("--- Mapa de distancias desde (" + ORIGEN_FILA + ", " + ORIGEN_COLUMNA + ") ---");
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                if (!TRANSITABLE[mundo[fila][columna]]) {
                    System.out.print("### ");
                } else if (distancia[fila][columna] == SIN_VISITAR) {
                    System.out.print("??? ");
                } else {
                    System.out.printf("%3d ", distancia[fila][columna]);
                }
            }
            System.out.println();
        }

        if (!TRANSITABLE[mundo[destinoFila][destinoColumna]]) {
            System.out.println("El destino no es transitable.");
        } else if (distancia[destinoFila][destinoColumna] == SIN_VISITAR) {
            System.out.println("No hay camino hasta el destino.");
        } else {
            System.out.println("Distancia minima: " + distancia[destinoFila][destinoColumna] + " pasos");
        }

        scanner.close();
    }
}
