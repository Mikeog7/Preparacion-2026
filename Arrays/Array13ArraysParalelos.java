package Arrays;
import java.util.Scanner;

/**
 * ARRAY 13: Arrays paralelos (nombres y notas)
 *
 * ENUNCIADO:  Lee N alumnos (nombre y nota). Muestra la tabla, el mejor y el peor
 *             alumno, busca un alumno por nombre y muestra el ranking de mayor a
 *             menor nota.
 * SIRVE PARA: relacionar datos de distinto tipo: la MISMA posición en dos arrays
 *             describe al MISMO alumno.
 * CONCEPTO CLAVE:
 *   - nombres[i] y notas[i] van siempre juntos.
 *   - Al ORDENAR, hay que intercambiar los dos arrays a la vez; si no, se
 *     desalinean y cada nota queda con el nombre equivocado.
 *   - Los Strings se comparan con equals(), nunca con ==.
 * ERROR TIPICO: ordenar solo el array de notas.
 * PRUEBA:     Ana 6, Luis 3.5, Marta 8 -> mejor Marta, peor Luis,
 *             ranking: Marta, Ana, Luis.  Buscar "Luis" -> 3.5.
 */
public class Array13ArraysParalelos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ALUMNOS_MIN = 1;
        final int ALUMNOS_MAX = 8;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final int NO_ENCONTRADO = -1;

        int cantidad;
        do {
            System.out.println("Cuantos alumnos (1-8): ");
            cantidad = scanner.nextInt();
        } while (cantidad < ALUMNOS_MIN || cantidad > ALUMNOS_MAX);

        String[] nombres = new String[cantidad];
        double[] notas = new double[cantidad];

        for (int posicion = 0; posicion < cantidad; posicion++) {
            System.out.println("Nombre del alumno " + (posicion + 1) + ": ");
            nombres[posicion] = scanner.next();
            boolean notaValida;
            do {
                System.out.println("Nota de " + nombres[posicion] + ": ");
                notas[posicion] = scanner.nextDouble();
                notaValida = notas[posicion] >= NOTA_MINIMA && notas[posicion] <= NOTA_MAXIMA;
                if (!notaValida) {
                    System.out.println("No es valido, vuelve a intentar.");
                }
            } while (!notaValida);
        }

        System.out.println("--- Tabla ---");
        for (int posicion = 0; posicion < cantidad; posicion++) {
            System.out.println(nombres[posicion] + ": " + notas[posicion]);
        }

        int posicionMejor = 0;
        int posicionPeor = 0;
        for (int posicion = 1; posicion < cantidad; posicion++) {
            if (notas[posicion] > notas[posicionMejor]) {
                posicionMejor = posicion;
            }
            if (notas[posicion] < notas[posicionPeor]) {
                posicionPeor = posicion;
            }
        }
        System.out.println("Mejor alumno: " + nombres[posicionMejor] + " (" + notas[posicionMejor] + ")");
        System.out.println("Peor alumno: " + nombres[posicionPeor] + " (" + notas[posicionPeor] + ")");

        System.out.println("Nombre a buscar: ");
        String buscado = scanner.next();
        int posicionBuscado = NO_ENCONTRADO;
        int indice = 0;
        while (indice < cantidad && posicionBuscado == NO_ENCONTRADO) {
            if (nombres[indice].equals(buscado)) {
                posicionBuscado = indice;
            }
            indice++;
        }
        if (posicionBuscado == NO_ENCONTRADO) {
            System.out.println(buscado + " no esta en la lista");
        } else {
            System.out.println(buscado + " tiene un " + notas[posicionBuscado]);
        }

        // Ordenar de mayor a menor INTERCAMBIANDO LOS DOS ARRAYS A LA VEZ.
        boolean huboIntercambio;
        int pasadas = 0;
        do {
            huboIntercambio = false;
            for (int posicion = 0; posicion < cantidad - 1 - pasadas; posicion++) {
                if (notas[posicion] < notas[posicion + 1]) {
                    double auxiliarNota = notas[posicion];
                    notas[posicion] = notas[posicion + 1];
                    notas[posicion + 1] = auxiliarNota;

                    String auxiliarNombre = nombres[posicion];
                    nombres[posicion] = nombres[posicion + 1];
                    nombres[posicion + 1] = auxiliarNombre;

                    huboIntercambio = true;
                }
            }
            pasadas++;
        } while (huboIntercambio);

        System.out.println("--- Ranking ---");
        for (int posicion = 0; posicion < cantidad; posicion++) {
            System.out.println((posicion + 1) + ". " + nombres[posicion] + " (" + notas[posicion] + ")");
        }

        scanner.close();
    }
}
