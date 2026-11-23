package Arrays;
import java.util.Scanner;

/**
 * ARRAY 02: Leer datos en un array y mostrarlos
 *
 * ENUNCIADO:  Pide cuántas notas (1 a 10), léelas validando 0-10 y muéstralas de
 *             tres formas: con índice, con for-each y en orden inverso.
 * SIRVE PARA: el patrón básico: VALIDAR el tamaño ANTES de crear el array y
 *             rellenarlo con un for.
 * CONCEPTO CLAVE:
 *   - El tamaño se decide al crear (new double[cantidad]) y ya no cambia.
 *   - for con índice: cuando necesitas la posición o modificar.
 *   - for-each (double nota : notas): cuando solo necesitas el valor.
 *   - Inverso: empezar en length - 1 y bajar hasta 0.
 * ERROR TIPICO: crear el array antes de validar (tamaño 0 o negativo -> error).
 * PRUEBA:     3 notas: 6.5, 3, 8 -> se muestran en orden y luego 8, 3, 6.5.
 */
public class Array02LeerYMostrar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CANTIDAD_MIN = 1;
        final int CANTIDAD_MAX = 10;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;

        int cantidad;
        do {
            System.out.println("Cuantas notas (1-10): ");
            cantidad = scanner.nextInt();
        } while (cantidad < CANTIDAD_MIN || cantidad > CANTIDAD_MAX);

        double[] notas = new double[cantidad];

        for (int posicion = 0; posicion < notas.length; posicion++) {
            boolean notaValida;
            do {
                System.out.println("Nota " + (posicion + 1) + ": ");
                notas[posicion] = scanner.nextDouble();
                notaValida = notas[posicion] >= NOTA_MINIMA && notas[posicion] <= NOTA_MAXIMA;
                if (!notaValida) {
                    System.out.println("No es valido, vuelve a intentar.");
                }
            } while (!notaValida);
        }

        System.out.println("--- Con indice ---");
        for (int posicion = 0; posicion < notas.length; posicion++) {
            System.out.println("Nota " + (posicion + 1) + ": " + notas[posicion]);
        }

        System.out.println("--- Con for-each ---");
        for (double nota : notas) {
            System.out.println(nota);
        }

        System.out.println("--- En orden inverso ---");
        for (int posicion = notas.length - 1; posicion >= 0; posicion--) {
            System.out.println(notas[posicion]);
        }

        scanner.close();
    }
}
