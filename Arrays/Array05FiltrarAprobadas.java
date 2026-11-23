package Arrays;
import java.util.Scanner;

/**
 * ARRAY 05: Filtrar en un array nuevo
 *
 * ENUNCIADO:  Lee N notas y crea un segundo array solo con las aprobadas.
 * SIRVE PARA: aprender que un array NO crece: si el tamaño final depende de los
 *             datos, hay que CONTAR primero y CREAR después.
 * CONCEPTO CLAVE:
 *   - Dos índices: uno recorre el origen y otro (destino) escribe en el nuevo.
 *   - new double[0] es válido (array vacío).
 * ERROR TIPICO: usar el mismo índice para leer y escribir: dejaría huecos con 0.0.
 * PRUEBA:     notas 6, 3.5, 8, 4.9, 5 -> aprobadas: 6, 8, 5 (3 elementos).
 */
public class Array05FiltrarAprobadas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CANTIDAD_MIN = 1;
        final int CANTIDAD_MAX = 10;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final double APROBADO = 5.0;

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

        // Paso 1: contar para saber el tamano del array nuevo.
        int cantidadAprobadas = 0;
        for (int posicion = 0; posicion < notas.length; posicion++) {
            if (notas[posicion] >= APROBADO) {
                cantidadAprobadas++;
            }
        }

        // Paso 2: crear el array del tamano exacto y copiar con un indice de destino.
        double[] aprobadas = new double[cantidadAprobadas];
        int destino = 0;
        for (int posicion = 0; posicion < notas.length; posicion++) {
            if (notas[posicion] >= APROBADO) {
                aprobadas[destino] = notas[posicion];
                destino++;
            }
        }

        if (aprobadas.length == 0) {
            System.out.println("No hay ninguna nota aprobada");
        } else {
            System.out.println("Notas aprobadas (" + aprobadas.length + "):");
            for (double nota : aprobadas) {
                System.out.println(nota);
            }
        }

        scanner.close();
    }
}
