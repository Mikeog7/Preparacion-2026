package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 13: Cámara (ventana que sigue al personaje)
 *
 * ENUNCIADO:  Con el mapa grande del juego de referencia (20 x 25), dibuja solo una
 *             ventana de 7 filas x 11 columnas centrada en el personaje. Cuando
 *             el personaje se acerca a un borde, la ventana se detiene (no muestra
 *             casillas que no existen).
 * SIRVE PARA: dibujar una PARTE de la matriz (subrango de filas y columnas).
 * CONCEPTO CLAVE:
 *   - Esquina superior izquierda de la ventana: posición - (tamaño de ventana / 2).
 *   - Ajuste ("clamp"): si el inicio es menor que 0, se pone en 0; si el inicio es
 *     mayor que (tamaño del mundo - tamaño de ventana), se pone en ese máximo.
 *   - Los bucles de dibujo van de "inicio" a "inicio + tamaño de ventana".
 *   - El personaje se compara con las coordenadas ABSOLUTAS (las del mundo).
 * ERROR TIPICO: no ajustar los bordes (la ventana pide filas/columnas fuera de la matriz).
 * PRUEBA:     empieza en (11, 6): ventana filas 8-14, columnas 1-11.
 *             En (0, 0) la ventana es filas 0-6, columnas 0-10 (el personaje arriba a la izquierda).
 */
public class Matriz13Camara {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final int ALTO_VISTA = 7;
        final int ANCHO_VISTA = 11;
        final int MITAD = 2;
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };
        final String TECLA_ARRIBA = "w";
        final String TECLA_IZQUIERDA = "a";
        final String TECLA_ABAJO = "s";
        final String TECLA_DERECHA = "d";
        final String TECLA_SALIR = "q";

        int[][] mundo = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 },
                { 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 },
                { 0, 7, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 9, 3, 3, 3, 3, 3, 3, 3, 3 },
                { 7, 0, 0, 0, 0, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 7, 3, 3, 3, 3 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 2, 2, 3, 3 },
                { 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 7, 7, 2, 2, 3 },
                { 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 0, 0, 2, 0, 0, 0, 8, 7, 7, 2, 3 },
                { 5, 5, 5, 5, 5, 0, 2, 0, 0, 5, 5, 5, 5, 3, 0, 0, 2, 0, 0, 0, 0, 5, 7, 2, 2 },
                { 5, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 5, 3, 0, 0, 2, 0, 0, 0, 0, 8, 7, 7, 2 },
                { 5, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 5, 3, 0, 0, 2, 0, 0, 0, 0, 0, 5, 7, 7 },
                { 5, 0, 1, 1, 1, 0, 2, 0, 1, 1, 1, 0, 5, 3, 8, 8, 2, 8, 8, 8, 8, 5, 5, 7, 7 },
                { 5, 0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0, 5, 3, 6, 6, 2, 6, 6, 6, 5, 5, 5, 7, 7 },
                { 5, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 3, 6, 6, 2, 6, 5, 5, 5, 5, 5, 7, 7 },
                { 5, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 3, 6, 6, 2, 5, 5, 5, 8, 8, 8, 5, 7 },
                { 5, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 3, 6, 5, 2, 5, 6, 6, 6, 8, 8, 5, 7 },
                { 5, 0, 1, 1, 9, 1, 1, 1, 9, 1, 1, 0, 5, 3, 5, 5, 2, 6, 6, 8, 8, 8, 8, 8, 7 },
                { 5, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 5, 3, 5, 6, 2, 6, 6, 6, 8, 1, 1, 1, 1 },
                { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 3, 4, 6, 2, 2, 2, 2, 2, 9, 0, 0, 0 },
                { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 4, 6, 6, 8, 8, 8, 8, 1, 0, 0, 0 },
                { 7, 7, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 6, 6, 8, 8, 8, 8, 1, 0, 5, 0 },
        };

        int[] posicionPersonaje = { 11, 6 };
        boolean estaJugando = true;

        do {
            // Esquina superior izquierda de la ventana, ajustada a los bordes.
            int filaInicio = posicionPersonaje[0] - ALTO_VISTA / MITAD;
            if (filaInicio < 0) {
                filaInicio = 0;
            }
            if (filaInicio > mundo.length - ALTO_VISTA) {
                filaInicio = mundo.length - ALTO_VISTA;
            }
            int columnaInicio = posicionPersonaje[1] - ANCHO_VISTA / MITAD;
            if (columnaInicio < 0) {
                columnaInicio = 0;
            }
            if (columnaInicio > mundo[0].length - ANCHO_VISTA) {
                columnaInicio = mundo[0].length - ANCHO_VISTA;
            }

            for (int fila = filaInicio; fila < filaInicio + ALTO_VISTA; fila++) {
                for (int columna = columnaInicio; columna < columnaInicio + ANCHO_VISTA; columna++) {
                    if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                        System.out.print(PERSONAJE);
                    } else {
                        System.out.print(MOSAICOS[mundo[fila][columna]]);
                    }
                }
                System.out.println();
            }
            System.out.println("-".repeat(ANCHO_VISTA * MOSAICOS[0].length()));
            System.out.println("Posicion: fila " + posicionPersonaje[0] + ", columna " + posicionPersonaje[1]
                    + " | Ventana desde (" + filaInicio + ", " + columnaInicio + ")");
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
                }
            }
        } while (estaJugando);

        scanner.close();
    }
}
