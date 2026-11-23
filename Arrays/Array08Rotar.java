package Arrays;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ARRAY 08: Rotar un array
 *
 * ENUNCIADO:  Dado {1, 2, 3, 4, 5, 6}, pide cuántas posiciones rotar a la
 *             izquierda y muestra el resultado. Muestra también una rotación de
 *             una posición a la derecha.
 * SIRVE PARA: desplazar elementos sin perder ninguno.
 * CONCEPTO CLAVE:
 *   - Izquierda: guardar el primero, mover cada elemento a la posición anterior
 *     (de IZQUIERDA a DERECHA), poner el primero al final.
 *   - Derecha: guardar el último, mover cada elemento a la posición siguiente
 *     (de DERECHA a IZQUIERDA), poner el último al principio.
 *   - El sentido del recorrido importa: al revés, se pisan valores aún no movidos.
 *   - Rotar k posiciones = repetir k veces la rotación de una posición.
 * ERROR TIPICO: recorrer hasta length y leer datos[i + 1] fuera de rango.
 * PRUEBA:     2 a la izquierda -> 3 4 5 6 1 2.   1 a la derecha -> 6 1 2 3 4 5.
 */
public class Array08Rotar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int POSICIONES_MIN = 0;

        int[] original = {1, 2, 3, 4, 5, 6};

        int posiciones;
        do {
            System.out.println("Posiciones a rotar a la izquierda (0-" + original.length + "): ");
            posiciones = scanner.nextInt();
        } while (posiciones < POSICIONES_MIN || posiciones > original.length);

        int[] izquierda = Arrays.copyOf(original, original.length);
        for (int vuelta = 1; vuelta <= posiciones; vuelta++) {
            int primero = izquierda[0];
            for (int indice = 0; indice < izquierda.length - 1; indice++) {
                izquierda[indice] = izquierda[indice + 1];
            }
            izquierda[izquierda.length - 1] = primero;
        }

        int[] derecha = Arrays.copyOf(original, original.length);
        int ultimo = derecha[derecha.length - 1];
        for (int indice = derecha.length - 1; indice > 0; indice--) {
            derecha[indice] = derecha[indice - 1];
        }
        derecha[0] = ultimo;

        System.out.println("Original:  " + Arrays.toString(original));
        System.out.println("Izquierda: " + Arrays.toString(izquierda));
        System.out.println("Derecha (1 posicion): " + Arrays.toString(derecha));

        scanner.close();
    }
}
