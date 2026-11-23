package Arrays;
import java.util.Scanner;

/**
 * ARRAY 12: Array de frecuencias (el valor leído ES el índice)
 *
 * ENUNCIADO:  a) Lee N notas enteras (0 a 10), cuenta cuántas hay de cada una,
 *                dibuja un histograma con asteriscos y muestra la moda.
 *             b) Lee una palabra y cuenta cuántas veces aparece cada letra (a-z).
 * SIRVE PARA: contar muchas categorías a la vez SIN escribir un if por categoría.
 * CONCEPTO CLAVE:
 *   - frecuencias[nota]++ : el dato se usa directamente como índice.
 *   - Tamaño = valor máximo + 1 (para las notas 0..10 hacen falta 11 casillas).
 *   - Letras: 'a' es un número (97); letra - 'a' da 0 para la a, 1 para la b...
 *   - Moda: el índice con la frecuencia mayor.
 * ERROR TIPICO: array demasiado pequeño (para notas hasta 10, new int[10] falla con un 10).
 * PRUEBA:     notas 7, 5, 7, 10, 7, 5 -> 5: **, 7: ***, 10: *, moda 7.
 *             palabra "casa" -> a: 2, c: 1, s: 1.
 */
public class Array12Frecuencias {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CANTIDAD_MIN = 1;
        final int CANTIDAD_MAX = 30;
        final int NOTA_MIN = 0;
        final int NOTA_MAX = 10;
        final int TOTAL_LETRAS = 26;
        final char PRIMERA_LETRA = 'a';
        final char ULTIMA_LETRA = 'z';

        // ----- a) Notas enteras -----
        int cantidad;
        do {
            System.out.println("Cuantas notas enteras (1-30): ");
            cantidad = scanner.nextInt();
        } while (cantidad < CANTIDAD_MIN || cantidad > CANTIDAD_MAX);

        int[] frecuencias = new int[NOTA_MAX + 1];

        for (int contador = 1; contador <= cantidad; contador++) {
            int nota;
            do {
                System.out.println("Nota " + contador + " (0-10): ");
                nota = scanner.nextInt();
            } while (nota < NOTA_MIN || nota > NOTA_MAX);
            frecuencias[nota]++;
        }

        int moda = 0;
        for (int nota = NOTA_MIN; nota <= NOTA_MAX; nota++) {
            if (frecuencias[nota] > 0) {
                System.out.print(nota + ": ");
                for (int asterisco = 1; asterisco <= frecuencias[nota]; asterisco++) {
                    System.out.print("*");
                }
                System.out.println(" (" + frecuencias[nota] + ")");
            }
            if (frecuencias[nota] > frecuencias[moda]) {
                moda = nota;
            }
        }
        System.out.println("Moda: " + moda + " (" + frecuencias[moda] + " veces)");

        // ----- b) Letras de una palabra -----
        System.out.println("Escribe una palabra (sin tildes ni espacios): ");
        String palabra = scanner.next().toLowerCase();

        int[] letras = new int[TOTAL_LETRAS];
        for (int posicion = 0; posicion < palabra.length(); posicion++) {
            char letra = palabra.charAt(posicion);
            if (letra >= PRIMERA_LETRA && letra <= ULTIMA_LETRA) {
                letras[letra - PRIMERA_LETRA]++;
            }
        }

        for (int indice = 0; indice < letras.length; indice++) {
            if (letras[indice] > 0) {
                char letra = (char) (PRIMERA_LETRA + indice);
                System.out.println(letra + ": " + letras[indice]);
            }
        }

        scanner.close();
    }
}
