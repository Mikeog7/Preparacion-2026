package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 14: Recoger tesoros (modificar la matriz durante el juego)
 *
 * ENUNCIADO:  El personaje debe recoger todos los tesoros (casillas tipo 9). Al
 *             pisar uno suma 100 puntos y la casilla pasa a ser hierba. Se gana al
 *             recoger el último. El número de tesoros se cuenta recorriendo el mapa.
 * SIRVE PARA: entender que la matriz es el ESTADO del juego y puede cambiar.
 * CONCEPTO CLAVE:
 *   - Contar los tesoros recorriendo la matriz (no escribir "2" a mano): si cambia
 *     el mapa, el programa sigue funcionando.
 *   - Al recogerlo: mundo[fila][columna] = HIERBA (la casilla se "gasta").
 *   - Condición de victoria comprobada DESPUÉS de cada movimiento.
 *   - Se sale del bucle con la bandera estaJugando, no con break.
 * ERROR TIPICO: no cambiar la casilla y recoger el mismo tesoro una y otra vez.
 * PRUEBA:     desde (5, 0): d x7 -> (5, 7); w -> (4, 7) primer tesoro (100 puntos);
 *             s, d, d -> (5, 9); w x5 -> (0, 9) segundo tesoro: victoria con 200 puntos.
 */
public class Matriz14Tesoros {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };
        final int HIERBA = 0;
        final int TESORO = 9;
        final int PUNTOS_TESORO = 100;
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

        int tesorosRestantes = 0;
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                if (mundo[fila][columna] == TESORO) {
                    tesorosRestantes++;
                }
            }
        }

        int[] posicionPersonaje = { 5, 0 };
        int puntuacion = 0;
        boolean estaJugando = true;

        do {
            for (int fila = 0; fila < mundo.length; fila++) {
                for (int columna = 0; columna < mundo[fila].length; columna++) {
                    if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                        System.out.print(PERSONAJE);
                    } else {
                        System.out.print(MOSAICOS[mundo[fila][columna]]);
                    }
                }
                System.out.println();
            }
            System.out.println("-".repeat(mundo[0].length * MOSAICOS[0].length()));
            System.out.println("Puntuacion: " + puntuacion + " | Tesoros restantes: " + tesorosRestantes);
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
                    if (mundo[nuevaFila][nuevaColumna] == TESORO) {
                        puntuacion = puntuacion + PUNTOS_TESORO;
                        tesorosRestantes--;
                        mundo[nuevaFila][nuevaColumna] = HIERBA;
                        System.out.println("Has encontrado un tesoro!");
                    }
                }
            }

            if (tesorosRestantes == 0 && estaJugando) {
                System.out.println("Has recogido todos los tesoros. Victoria con " + puntuacion + " puntos!");
                estaJugando = false;
            }
        } while (estaJugando);

        scanner.close();
    }
}
