package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 09: Mover al personaje sin salirse del mapa
 *
 * ENUNCIADO:  Mueve al personaje con W, A, S, D (Q para salir). Si el movimiento
 *             lo sacaría del mapa, no se realiza y se avisa. Cuenta los pasos.
 * SIRVE PARA: corregir el fallo del juego de referencia: allí no se comprueban los
 *             límites y el personaje sale del mapa y DESAPARECE (el dibujo solo
 *             compara coordenadas, así que no da error, pero el jugador queda perdido).
 * CONCEPTO CLAVE:
 *   - Se calcula la posición NUEVA en variables aparte (nuevaFila, nuevaColumna).
 *   - Se comprueba que esté dentro: 0 <= fila < mundo.length y
 *     0 <= columna < mundo[0].length. Solo entonces se aplica.
 *   - Una tecla no válida NO debe contar como paso (bandera intentaMover).
 *   - Bucle do-while con una variable de estado: estaJugando = false para salir.
 *   - Teclas en constantes: nada de "w" suelto por el código.
 * ERROR TIPICO: modificar la posición real antes de comprobar (ya no se puede deshacer).
 * PRUEBA:     empieza en (5, 0): pulsa "a" -> "No puedes salir del mapa", pasos 0.
 *             "w" -> sube y pasos 1.   "x" -> tecla no válida, pasos sin cambio.
 */
public class Matriz09MovimientoLimites {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
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
        int pasos = 0;
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
            System.out.println("Pasos: " + pasos);
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
                if (dentroDelMapa) {
                    posicionPersonaje[0] = nuevaFila;
                    posicionPersonaje[1] = nuevaColumna;
                    pasos++;
                } else {
                    System.out.println("No puedes salir del mapa.");
                }
            }
        } while (estaJugando);

        System.out.println("Has dado " + pasos + " pasos. Hasta luego.");
        scanner.close();
    }
}
