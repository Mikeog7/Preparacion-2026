package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 12: Niebla de guerra
 *
 * ENUNCIADO:  El mapa está oculto ("???"). Al moverse, el personaje descubre las
 *             casillas a una distancia de 1 (el cuadrado de 3 x 3 a su alrededor).
 *             Lo descubierto se queda visible. Muestra cuánto del mapa se ha descubierto.
 * SIRVE PARA: trabajar con DISTANCIAS entre coordenadas y con matrices de estado.
 * CONCEPTO CLAVE:
 *   - boolean[][] descubierta: qué casillas ya se han visto.
 *   - Una casilla (fila, columna) está a distancia <= radio del personaje si
 *     Math.abs(fila - filaPersonaje) <= radio Y Math.abs(columna - columnaPersonaje) <= radio.
 *   - Se recorre TODA la matriz y se marcan las que cumplen la condición (así no
 *     hay que preocuparse por salirse de los bordes).
 *   - Dibujar: personaje, o mosaico si está descubierta, o "???" si no.
 * ERROR TIPICO: restar sin Math.abs (solo descubre en una dirección).
 * PRUEBA:     al empezar en (5, 0) con radio 1 se descubren 4 casillas (filas 4-5,
 *             columnas 0-1). Tras "d" se descubre también la columna 2.
 */
public class Matriz12NieblaDeGuerra {

    static void descubrir(boolean[][] descubierta, int[] posicionPersonaje, int radio) {
        for (int fila = 0; fila < descubierta.length; fila++) {
            for (int columna = 0; columna < descubierta[fila].length; columna++) {
                boolean cercaEnFilas = Math.abs(fila - posicionPersonaje[0]) <= radio;
                boolean cercaEnColumnas = Math.abs(columna - posicionPersonaje[1]) <= radio;
                if (cercaEnFilas && cercaEnColumnas) {
                    descubierta[fila][columna] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String NIEBLA = "???";
        final int RADIO_VISION = 1;
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };
        final String TECLA_ARRIBA = "w";
        final String TECLA_IZQUIERDA = "a";
        final String TECLA_ABAJO = "s";
        final String TECLA_DERECHA = "d";
        final String TECLA_SALIR = "q";

        int[][] mundo = {
                { 0, 0, 0, 3, 3, 0, 0, 7, 7, 9 },
                { 0, 2, 2, 2, 3, 0, 0, 0, 7, 0 },
                { 0, 2, 1, 2, 3, 3, 0, 0, 0, 0 },
                { 0, 2, 2, 2, 0, 3, 8, 8, 0, 0 },
                { 0, 0, 2, 0, 0, 0, 8, 9, 0, 0 },
                { 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }
        };

        boolean[][] descubierta = new boolean[mundo.length][mundo[0].length];
        int[] posicionPersonaje = { 5, 0 };
        descubrir(descubierta, posicionPersonaje, RADIO_VISION);
        boolean estaJugando = true;

        do {
            int casillasDescubiertas = 0;
            for (int fila = 0; fila < mundo.length; fila++) {
                for (int columna = 0; columna < mundo[fila].length; columna++) {
                    if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                        System.out.print(PERSONAJE);
                    } else if (descubierta[fila][columna]) {
                        System.out.print(MOSAICOS[mundo[fila][columna]]);
                    } else {
                        System.out.print(NIEBLA);
                    }
                    if (descubierta[fila][columna]) {
                        casillasDescubiertas++;
                    }
                }
                System.out.println();
            }
            System.out.println("-".repeat(mundo[0].length * MOSAICOS[0].length()));
            double porcentaje = casillasDescubiertas * 100.0 / (mundo.length * mundo[0].length);
            System.out.printf("Descubierto: %d casillas (%.1f%%)%n", casillasDescubiertas, porcentaje);
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
                    descubrir(descubierta, posicionPersonaje, RADIO_VISION);
                }
            }
        } while (estaJugando);

        scanner.close();
    }
}
