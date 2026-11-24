package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 11: Registrar las casillas visitadas
 *
 * ENUNCIADO:  Mientras el personaje se mueve, deja una huella " * " en cada casilla
 *             por la que pasó. Muestra el porcentaje de casillas transitables
 *             que ya ha explorado.
 * SIRVE PARA: usar una SEGUNDA matriz del mismo tamaño que guarda el estado de
 *             cada casilla (visitada o no), separado del terreno.
 * CONCEPTO CLAVE:
 *   - boolean[][] visitadas = new boolean[filas][columnas] (empieza todo en false).
 *   - Matriz de datos + matriz de estado: mismas coordenadas, distinta información.
 *   - Contador de visitadas: solo aumenta la PRIMERA vez que se pisa una casilla.
 *   - El porcentaje usa 100.0 para no hacer una división entera.
 * ERROR TIPICO: contar la casilla cada vez que se pisa (supera el 100 %) o dividir
 *             enteros (siempre sale 0 %).
 * PRUEBA:     47 casillas transitables. Tras pulsar "d" dos veces desde (5, 0)
 *             hay 3 visitadas = 6,4 %.   Volver sobre una visitada no suma.
 */
public class Matriz11CasillasVisitadas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String HUELLA = " * ";
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };
        final String TECLA_ARRIBA = "w";
        final String TECLA_IZQUIERDA = "a";
        final String TECLA_ABAJO = "s";
        final String TECLA_DERECHA = "d";
        final String TECLA_SALIR = "q";
        final int FILA_INICIAL = 5;
        final int COLUMNA_INICIAL = 0;

        int[][] mundo = {
                { 0, 0, 0, 3, 3, 0, 0, 7, 7, 9 },
                { 0, 2, 2, 2, 3, 0, 0, 0, 7, 0 },
                { 0, 2, 1, 2, 3, 3, 0, 0, 0, 0 },
                { 0, 2, 2, 2, 0, 3, 8, 8, 0, 0 },
                { 0, 0, 2, 0, 0, 0, 8, 9, 0, 0 },
                { 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }
        };

        boolean[][] visitadas = new boolean[mundo.length][mundo[0].length];

        int casillasTransitables = 0;
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                if (TRANSITABLE[mundo[fila][columna]]) {
                    casillasTransitables++;
                }
            }
        }

        int[] posicionPersonaje = { FILA_INICIAL, COLUMNA_INICIAL };
        visitadas[FILA_INICIAL][COLUMNA_INICIAL] = true;
        int casillasVisitadas = 1;
        boolean estaJugando = true;

        do {
            for (int fila = 0; fila < mundo.length; fila++) {
                for (int columna = 0; columna < mundo[fila].length; columna++) {
                    if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                        System.out.print(PERSONAJE);
                    } else if (visitadas[fila][columna]) {
                        System.out.print(HUELLA);
                    } else {
                        System.out.print(MOSAICOS[mundo[fila][columna]]);
                    }
                }
                System.out.println();
            }
            System.out.println("-".repeat(mundo[0].length * MOSAICOS[0].length()));
            double porcentaje = casillasVisitadas * 100.0 / casillasTransitables;
            System.out.printf("Explorado: %d de %d casillas (%.1f%%)%n", casillasVisitadas, casillasTransitables, porcentaje);
            System.out.print("WASD (Q para salir) -> ");
            String tecla = scanner.nextLine();

            int nuevaFila = posicionPersonaje[0];
            int nuevaColumna = posicionPersonaje[1];
            boolean intentaMover = true;

            if (tecla.equalsIgnoreCase(TECLA_ARRIBA)) {
                nuevaFila--;
            } else if (tecla.equalsIgnoreCase(TECLA_ABAJO)) {
                nuevaFila++;
            } else if (tecla.equalsIgnoreCase(TECLA_IZQUIERDA)) {
                nuevaColumna--;
            } else if (tecla.equalsIgnoreCase(TECLA_DERECHA)) {
                nuevaColumna++;
            } else {
                intentaMover = false;
                if (tecla.equalsIgnoreCase(TECLA_SALIR)) {
                    estaJugando = false;
                } else {
                    System.out.println("Tecla no valida.");
                }
            }

            if (intentaMover) {
                boolean dentroDelMapa = nuevaFila >= 0 && nuevaFila < mundo.length
                        && nuevaColumna >= 0 && nuevaColumna < mundo[0].length;
                if (!dentroDelMapa) {
                    System.out.println("No puedes salir del mapa.");
                } else if (!TRANSITABLE[mundo[nuevaFila][nuevaColumna]]) {
                    System.out.println("Hay algo que bloquea el paso.");
                } else {
                    posicionPersonaje[0] = nuevaFila;
                    posicionPersonaje[1] = nuevaColumna;
                    if (!visitadas[nuevaFila][nuevaColumna]) {
                        visitadas[nuevaFila][nuevaColumna] = true;
                        casillasVisitadas++;
                    }
                }
            }
        } while (estaJugando);

        scanner.close();
    }
}
