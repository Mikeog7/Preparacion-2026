package Arrays;
import java.util.Scanner;

/**
 * ARRAY 11: Búsqueda binaria
 *
 * ENUNCIADO:  Dado un array YA ORDENADO de 11 enteros, pide un valor y búscalo
 *             descartando la mitad en cada paso. Muestra cuántas comparaciones hizo.
 * SIRVE PARA: ver por qué ordenar un array vale la pena: se busca mucho más rápido.
 * CONCEPTO CLAVE:
 *   - Tres variables: bajo, alto y medio.
 *   - Si datos[medio] es menor que lo buscado, se descarta la mitad izquierda
 *     (bajo = medio + 1); si es mayor, la derecha (alto = medio - 1).
 *   - Condición del while: bajo <= alto y todavía no encontrado (bandera, sin break).
 *   - SOLO funciona si el array está ordenado.
 * ERROR TIPICO: bajo = medio (en vez de medio + 1): puede quedarse en bucle infinito.
 * PRUEBA:     buscar 23 -> posición 5 en 1 comparación.   buscar 91 -> posición 10.
 *             buscar 7 -> no encontrado.   Una búsqueda lineal necesitaría hasta 11.
 */
public class Array11BusquedaBinaria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NO_ENCONTRADO = -1;
        final int DIVISOR_MITAD = 2;

        int[] datos = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};

        System.out.println("Valor a buscar: ");
        int buscado = scanner.nextInt();

        int bajo = 0;
        int alto = datos.length - 1;
        int posicion = NO_ENCONTRADO;
        int comparaciones = 0;

        while (bajo <= alto && posicion == NO_ENCONTRADO) {
            int medio = bajo + (alto - bajo) / DIVISOR_MITAD;
            comparaciones++;
            if (datos[medio] == buscado) {
                posicion = medio;
            } else if (datos[medio] < buscado) {
                bajo = medio + 1;
            } else {
                alto = medio - 1;
            }
        }

        if (posicion == NO_ENCONTRADO) {
            System.out.println("No esta en el array (" + comparaciones + " comparaciones)");
        } else {
            System.out.println("Encontrado en la posicion " + posicion + " (" + comparaciones + " comparaciones)");
        }

        scanner.close();
    }
}
