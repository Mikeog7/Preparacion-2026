package Arrays;
import java.util.Scanner;

/**
 * ARRAY 10: Ordenación por burbuja (ascendente o descendente)
 *
 * ENUNCIADO:  Lee N enteros y ordénalos de menor a mayor ('a') o de mayor a
 *             menor ('d') según elija el usuario. Muestra cuántas pasadas hicieron falta.
 * SIRVE PARA: el algoritmo de ordenación más típico de examen.
 * CONCEPTO CLAVE:
 *   - Se comparan parejas vecinas (i, i + 1) y se intercambian si están desordenadas.
 *   - Tras cada pasada, el elemento más "pesado" queda al final: el límite
 *     interior se reduce (length - 1 - pasadas).
 *   - Optimización: si una pasada no intercambia nada, ya está ordenado
 *     (bandera huboIntercambio).
 *   - Cambiar el sentido del orden = cambiar > por <.
 * ERROR TIPICO: límite interior length (datos[i + 1] fuera de rango) o intercambiar
 *             sin variable auxiliar.
 * PRUEBA:     5 1 4 2 8 -> ascendente 1 2 4 5 8; descendente 8 5 4 2 1.
 *             Ya ordenado (1 2 3) -> 1 pasada.
 */
public class Array10Burbuja {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CANTIDAD_MIN = 2;
        final int CANTIDAD_MAX = 10;
        final char ASCENDENTE = 'a';

        int cantidad;
        do {
            System.out.println("Cuantos numeros (2-10): ");
            cantidad = scanner.nextInt();
        } while (cantidad < CANTIDAD_MIN || cantidad > CANTIDAD_MAX);

        int[] datos = new int[cantidad];
        for (int posicion = 0; posicion < datos.length; posicion++) {
            System.out.println("Numero " + (posicion + 1) + ": ");
            datos[posicion] = scanner.nextInt();
        }

        System.out.println("Orden ascendente (a) o descendente (d)? ");
        char orden = scanner.next().charAt(0);
        boolean ascendente = orden == ASCENDENTE;

        boolean huboIntercambio;
        int pasadas = 0;
        do {
            huboIntercambio = false;
            for (int posicion = 0; posicion < datos.length - 1 - pasadas; posicion++) {
                boolean fueraDeOrden = ascendente
                        ? datos[posicion] > datos[posicion + 1]
                        : datos[posicion] < datos[posicion + 1];
                if (fueraDeOrden) {
                    int auxiliar = datos[posicion];
                    datos[posicion] = datos[posicion + 1];
                    datos[posicion + 1] = auxiliar;
                    huboIntercambio = true;
                }
            }
            pasadas++;
        } while (huboIntercambio);

        System.out.print("Ordenado: ");
        for (int posicion = 0; posicion < datos.length; posicion++) {
            System.out.print(datos[posicion] + " ");
        }
        System.out.println();
        System.out.println("Pasadas realizadas: " + pasadas);

        scanner.close();
    }
}
