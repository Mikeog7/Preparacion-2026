package Arrays;
import java.util.Scanner;

/**
 * ARRAY 04: Búsqueda lineal
 *
 * ENUNCIADO:  Dado un array de enteros, pide un valor y muestra la primera
 *             posición donde aparece, la última y cuántas veces aparece.
 * SIRVE PARA: el patrón de búsqueda: recorrer y recordar dónde ocurre algo.
 * CONCEPTO CLAVE:
 *   - NO_ENCONTRADO = -1: el 0 es una posición válida, no sirve como "no está".
 *   - Primera aparición: while con la bandera en la condición (se detiene al
 *     encontrarlo, sin break).
 *   - Última aparición y recuento: for completo, sobrescribiendo la posición.
 * ERROR TIPICO: imprimir "no encontrado" dentro del bucle (saldría por cada
 *             elemento distinto). El mensaje va DESPUÉS del bucle.
 * PRUEBA:     datos {4, 9, 2, 9, 7, 9, 1}: buscar 9 -> primera 2, última 6, 3 veces.
 *             buscar 5 -> no encontrado.
 */
public class Array04Buscar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NO_ENCONTRADO = -1;

        int[] datos = {4, 9, 2, 9, 7, 9, 1};

        System.out.println("Valor a buscar: ");
        int buscado = scanner.nextInt();

        int primera = NO_ENCONTRADO;
        int posicion = 0;
        while (posicion < datos.length && primera == NO_ENCONTRADO) {
            if (datos[posicion] == buscado) {
                primera = posicion;
            }
            posicion++;
        }

        int ultima = NO_ENCONTRADO;
        int veces = 0;
        for (int indice = 0; indice < datos.length; indice++) {
            if (datos[indice] == buscado) {
                ultima = indice;
                veces++;
            }
        }

        if (primera == NO_ENCONTRADO) {
            System.out.println("El valor " + buscado + " no esta en el array");
        } else {
            System.out.println("Primera posicion: " + primera);
            System.out.println("Ultima posicion: " + ultima);
            System.out.println("Veces que aparece: " + veces);
        }

        scanner.close();
    }
}
