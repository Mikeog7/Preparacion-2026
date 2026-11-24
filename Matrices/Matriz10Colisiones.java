package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 10: Terrenos que bloquean el paso (colisiones)
 *
 * ENUNCIADO:  Igual que el ejercicio 9, pero el personaje no puede entrar en agua,
 *             edificios, montañas ni árboles. Si lo intenta, se muestra qué le
 *             bloquea el camino. Cuenta pasos y choques.
 * SIRVE PARA: guardar las REGLAS del mundo en datos, no en una cadena de if.
 * CONCEPTO CLAVE:
 *   - boolean[] TRANSITABLE indexado por el ID del terreno: transitable[id] dice si
 *     se puede pisar. Añadir un terreno nuevo = añadir una posición, no un if.
 *   - Orden de las comprobaciones: PRIMERO que esté dentro del mapa, DESPUÉS el
 *     terreno (si no, se leería una casilla que no existe).
 *   - El && y el else if cortan: si no está dentro, ni se mira el terreno.
 * ERROR TIPICO: mirar mundo[nuevaFila][nuevaColumna] antes de comprobar el límite
 *             (ArrayIndexOutOfBoundsException).
 * PRUEBA:     desde (5, 0): w w w w w sube a (0, 0); d d llega a (0, 2); otra d
 *             choca con el agua ("Te bloquea: agua"). Resultado: 7 pasos, 1 choque.
 */
public class Matriz10Colisiones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final String[] NOMBRES = { "hierba", "edificio", "camino", "agua", "arena", "piedra",
                "arbusto", "montana", "arbol", "tesoro" };
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

        int[] posicionPersonaje = { 5, 0 };
        int pasos = 0;
        int choques = 0;
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
            System.out.println("Pasos: " + pasos + " | Choques: " + choques);
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
                    System.out.println("Te bloquea: " + NOMBRES[mundo[nuevaFila][nuevaColumna]]);
                    choques++;
                } else {
                    posicionPersonaje[0] = nuevaFila;
                    posicionPersonaje[1] = nuevaColumna;
                    pasos++;
                }
            }
        } while (estaJugando);

        System.out.println("Pasos: " + pasos + " | Choques: " + choques);
        scanner.close();
    }
}
