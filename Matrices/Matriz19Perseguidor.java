package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 19: Un enemigo que persigue al personaje
 *
 * ENUNCIADO:  Un enemigo (" X ") se mueve una casilla hacia el personaje después de
 *             cada movimiento válido del jugador. Si lo alcanza, el jugador pierde una
 *             vida (de 3) y el enemigo vuelve a su casilla inicial. Se gana
 *             sobreviviendo 15 turnos; se pierde al quedarse sin vidas.
 * SIRVE PARA: manejar DOS posiciones en la misma matriz y reglas de interacción.
 * CONCEPTO CLAVE:
 *   - Dos arrays de posición: posicionPersonaje y posicionEnemigo.
 *   - Perseguir: se mira la diferencia de filas y de columnas; se mueve en el eje
 *     con MAYOR distancia (Math.abs) un paso hacia el personaje.
 *   - El enemigo también respeta el terreno (si el paso está bloqueado, se queda).
 *   - Una colisión es "misma fila Y misma columna".
 *   - Solo cuentan como turno los movimientos válidos del jugador.
 * ERROR TIPICO: mover al enemigo antes de comprobar la colisión del jugador, o
 *             comparar solo la fila.
 * PRUEBA:     el enemigo empieza en (0, 0). Si te quedas cerca de él, pierdes vidas;
 *             si te alejas por el camino de abajo, sobrevives 15 turnos.
 */
public class Matriz19Perseguidor {

    static boolean hayColision(int[] primero, int[] segundo) {
        return primero[0] == segundo[0] && primero[1] == segundo[1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String ENEMIGO = " X ";
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };
        final int VIDAS_INICIALES = 3;
        final int TURNOS_PARA_GANAR = 15;
        final int ENEMIGO_FILA_INICIAL = 0;
        final int ENEMIGO_COLUMNA_INICIAL = 0;
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

        int[] posicionPersonaje = { 5, 0 };
        int[] posicionEnemigo = { ENEMIGO_FILA_INICIAL, ENEMIGO_COLUMNA_INICIAL };
        int vidas = VIDAS_INICIALES;
        int turnos = 0;
        boolean estaJugando = true;

        do {
            for (int fila = 0; fila < mundo.length; fila++) {
                for (int columna = 0; columna < mundo[fila].length; columna++) {
                    if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                        System.out.print(PERSONAJE);
                    } else if (fila == posicionEnemigo[0] && columna == posicionEnemigo[1]) {
                        System.out.print(ENEMIGO);
                    } else {
                        System.out.print(MOSAICOS[mundo[fila][columna]]);
                    }
                }
                System.out.println();
            }
            System.out.println("-".repeat(mundo[0].length * MOSAICOS[0].length()));
            System.out.println("Vidas: " + vidas + " | Turnos: " + turnos + "/" + TURNOS_PARA_GANAR);
            System.out.print("WASD (Q para salir) -> ");
            String tecla = scanner.nextLine();

            int nuevaFila = posicionPersonaje[0];
            int nuevaColumna = posicionPersonaje[1];
            boolean intentaMover = true;
            boolean turnoValido = false;

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
                    turnoValido = true;
                }
            }

            if (turnoValido) {
                turnos++;

                // Colision por que el jugador camina hacia el enemigo.
                if (hayColision(posicionPersonaje, posicionEnemigo)) {
                    vidas--;
                    posicionEnemigo[0] = ENEMIGO_FILA_INICIAL;
                    posicionEnemigo[1] = ENEMIGO_COLUMNA_INICIAL;
                    System.out.println("El enemigo te ha alcanzado! Pierdes una vida.");
                }

                // El enemigo da un paso hacia el personaje, en el eje de mayor distancia.
                int diferenciaFila = posicionPersonaje[0] - posicionEnemigo[0];
                int diferenciaColumna = posicionPersonaje[1] - posicionEnemigo[1];
                int enemigoNuevaFila = posicionEnemigo[0];
                int enemigoNuevaColumna = posicionEnemigo[1];

                if (Math.abs(diferenciaFila) >= Math.abs(diferenciaColumna)) {
                    if (diferenciaFila > 0) {
                        enemigoNuevaFila++;
                    } else if (diferenciaFila < 0) {
                        enemigoNuevaFila--;
                    }
                } else {
                    if (diferenciaColumna > 0) {
                        enemigoNuevaColumna++;
                    } else if (diferenciaColumna < 0) {
                        enemigoNuevaColumna--;
                    }
                }

                if (TRANSITABLE[mundo[enemigoNuevaFila][enemigoNuevaColumna]]) {
                    posicionEnemigo[0] = enemigoNuevaFila;
                    posicionEnemigo[1] = enemigoNuevaColumna;
                }

                // Colision por que el enemigo camina hacia el jugador.
                if (hayColision(posicionPersonaje, posicionEnemigo)) {
                    vidas--;
                    posicionEnemigo[0] = ENEMIGO_FILA_INICIAL;
                    posicionEnemigo[1] = ENEMIGO_COLUMNA_INICIAL;
                    System.out.println("El enemigo te ha alcanzado! Pierdes una vida.");
                }
            }

            if (vidas == 0) {
                System.out.println("Te has quedado sin vidas. Fin del juego.");
                estaJugando = false;
            } else if (turnos >= TURNOS_PARA_GANAR) {
                System.out.println("Has sobrevivido " + TURNOS_PARA_GANAR + " turnos. Victoria!");
                estaJugando = false;
            }
        } while (estaJugando);

        scanner.close();
    }
}
