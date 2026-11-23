package Arrays;
import java.util.Scanner;

/**
 * ARRAY 14: Quitar duplicados (valores únicos)
 *
 * ENUNCIADO:  Lee N enteros y construye un segundo array con cada valor una sola
 *             vez, respetando el orden de aparición. Muestra cuántos duplicados
 *             se eliminaron.
 * SIRVE PARA: combinar un array "parcialmente lleno" (cantidadUnicos) con una
 *             búsqueda dentro de él antes de añadir.
 * CONCEPTO CLAVE:
 *   - Para cada dato: buscar si ya está entre los únicos ya guardados
 *     (bandera yaEsta + while, sin break).
 *   - Si no está, se añade en la posición cantidadUnicos y esta aumenta.
 *   - El array de únicos se crea del tamaño del original (caso peor: todos distintos).
 * ERROR TIPICO: recorrer todo el array de únicos (con ceros sin usar) en vez de
 *             solo hasta cantidadUnicos.
 * PRUEBA:     4 7 4 2 7 9 -> únicos 4 7 2 9 (se eliminaron 2).
 */
public class Array14Unicos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CANTIDAD_MIN = 1;
        final int CANTIDAD_MAX = 15;

        int cantidad;
        do {
            System.out.println("Cuantos numeros (1-15): ");
            cantidad = scanner.nextInt();
        } while (cantidad < CANTIDAD_MIN || cantidad > CANTIDAD_MAX);

        int[] datos = new int[cantidad];
        for (int posicion = 0; posicion < datos.length; posicion++) {
            System.out.println("Numero " + (posicion + 1) + ": ");
            datos[posicion] = scanner.nextInt();
        }

        int[] unicos = new int[cantidad];
        int cantidadUnicos = 0;

        for (int posicion = 0; posicion < datos.length; posicion++) {
            boolean yaEsta = false;
            int indice = 0;
            while (indice < cantidadUnicos && !yaEsta) {
                if (unicos[indice] == datos[posicion]) {
                    yaEsta = true;
                }
                indice++;
            }
            if (!yaEsta) {
                unicos[cantidadUnicos] = datos[posicion];
                cantidadUnicos++;
            }
        }

        System.out.print("Valores unicos: ");
        for (int posicion = 0; posicion < cantidadUnicos; posicion++) {
            System.out.print(unicos[posicion] + " ");
        }
        System.out.println();
        System.out.println("Duplicados eliminados: " + (cantidad - cantidadUnicos));

        scanner.close();
    }
}
