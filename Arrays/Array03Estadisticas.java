package Arrays;
import java.util.Scanner;

/**
 * ARRAY 03: Suma, media, máximo y mínimo (y lo que SOLO permite un array)
 *
 * ENUNCIADO:  Lee N notas y calcula suma, media, máxima y mínima con su posición.
 *             Después cuenta cuántas notas superan la media.
 * SIRVE PARA: ver la gran ventaja del array: los datos se GUARDAN, así que puedes
 *             recorrerlos otra vez (la media no se conoce hasta terminar de sumar).
 * CONCEPTO CLAVE:
 *   - Con arrays el máximo/mínimo se puede inicializar con notas[0] y recorrer
 *     desde el índice 1 (ya no hace falta el "extremo opuesto").
 *   - Segunda pasada: comparar cada nota con la media.
 * ERROR TIPICO: comparar con la media dentro del primer bucle (aún está incompleta).
 * PRUEBA:     6, 3.5, 8, 5, 9.5 -> suma 32, media 6.4, máx 9.5 (asignatura 5),
 *             mín 3.5 (asignatura 2), superan la media: 2 (el 8 y el 9.5; el 6 no).
 */
public class Array03Estadisticas {

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

        // Primera pasada: suma, maximo y minimo.
        double suma = notas[0];
        double notaMaxima = notas[0];
        double notaMinima = notas[0];
        int posicionMaxima = 0;
        int posicionMinima = 0;

        for (int posicion = 1; posicion < notas.length; posicion++) {
            suma = suma + notas[posicion];
            if (notas[posicion] > notaMaxima) {
                notaMaxima = notas[posicion];
                posicionMaxima = posicion;
            }
            if (notas[posicion] < notaMinima) {
                notaMinima = notas[posicion];
                posicionMinima = posicion;
            }
        }
        double media = suma / notas.length;

        // Segunda pasada: ya conocemos la media.
        int superiores = 0;
        for (int posicion = 0; posicion < notas.length; posicion++) {
            if (notas[posicion] > media) {
                superiores++;
            }
        }

        System.out.println("Suma: " + suma);
        System.out.println("Media: " + media);
        System.out.println("Maxima: " + notaMaxima + " (asignatura " + (posicionMaxima + 1) + ")");
        System.out.println("Minima: " + notaMinima + " (asignatura " + (posicionMinima + 1) + ")");
        System.out.println("Notas por encima de la media: " + superiores);

        scanner.close();
    }
}
