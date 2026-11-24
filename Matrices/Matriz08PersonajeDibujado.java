package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 08: Colocar y dibujar al personaje
 *
 * ENUNCIADO:  Pide la fila y la columna donde colocar al personaje (validadas),
 *             dibuja el mapa con el personaje encima e indica sobre qué tipo de
 *             terreno está.
 * SIRVE PARA: representar la posición con un array de dos valores, como en el
 *             juego de referencia: posicionPersonaje = { fila, columna }.
 * CONCEPTO CLAVE:
 *   - Al dibujar, en cada casilla se pregunta: ¿es la del personaje? Si sí, se
 *     dibuja el personaje; si no, el mosaico.
 *   - El personaje NO está dentro de la matriz: es un dato aparte (la matriz
 *     conserva el terreno que hay debajo).
 *   - Arrays paralelos: MOSAICOS y NOMBRES comparten índice (el ID del terreno).
 *   - Validar con do-while que la posición esté dentro del mapa.
 * ERROR TIPICO: comparar fila con la columna del personaje (o al revés).
 * PRUEBA:     fila 4, columna 7 -> el personaje cubre el tesoro; "Estas sobre: tesoro".
 *             fila 6 -> pide otra vez (el mapa solo tiene filas 0 a 5).
 */
public class Matriz08PersonajeDibujado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final String[] NOMBRES = { "hierba", "edificio", "camino", "agua", "arena", "piedra",
                "arbusto", "montana", "arbol", "tesoro" };

        int[][] mundo = {
                { 0, 0, 0, 3, 3, 0, 0, 7, 7, 9 },
                { 0, 2, 2, 2, 3, 0, 0, 0, 7, 0 },
                { 0, 2, 1, 2, 3, 3, 0, 0, 0, 0 },
                { 0, 2, 2, 2, 0, 3, 8, 8, 0, 0 },
                { 0, 0, 2, 0, 0, 0, 8, 9, 0, 0 },
                { 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }
        };

        int[] posicionPersonaje = new int[2];

        do {
            System.out.println("Fila del personaje (0-" + (mundo.length - 1) + "): ");
            posicionPersonaje[0] = scanner.nextInt();
        } while (posicionPersonaje[0] < 0 || posicionPersonaje[0] >= mundo.length);

        do {
            System.out.println("Columna del personaje (0-" + (mundo[0].length - 1) + "): ");
            posicionPersonaje[1] = scanner.nextInt();
        } while (posicionPersonaje[1] < 0 || posicionPersonaje[1] >= mundo[0].length);

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

        int terreno = mundo[posicionPersonaje[0]][posicionPersonaje[1]];
        System.out.println("Estas sobre: " + NOMBRES[terreno]);

        scanner.close();
    }
}
