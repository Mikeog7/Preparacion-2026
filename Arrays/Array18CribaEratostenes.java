package Arrays;
import java.util.Scanner;

/**
 * ARRAY 18: Criba de Eratóstenes (array de boolean)
 *
 * ENUNCIADO:  Pide un límite (2 a 1000) y muestra todos los primos hasta él
 *             usando la criba: tachar los múltiplos de cada primo.
 * SIRVE PARA: usar un array de boolean como "tablero de marcas" donde el ÍNDICE
 *             es el número y el VALOR dice si sigue siendo candidato.
 * CONCEPTO CLAVE:
 *   - esPrimo[numero] empieza en true para todos (a partir del 2).
 *   - Para cada número que sigue siendo primo, se tachan sus múltiplos
 *     empezando en numero * numero (los anteriores ya los tachó otro primo).
 *   - Tamaño límite + 1 para que exista el índice "limite".
 *   - Mucho más rápido que probar divisores uno a uno (paso 28 del parcial).
 * ERROR TIPICO: array de tamaño "limite" (falta el último índice).
 * PRUEBA:     30 -> 2 3 5 7 11 13 17 19 23 29 (10 primos).
 */
public class Array18CribaEratostenes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int LIMITE_MIN = 2;
        final int LIMITE_MAX = 1000;

        int limite;
        do {
            System.out.println("Limite (2-1000): ");
            limite = scanner.nextInt();
        } while (limite < LIMITE_MIN || limite > LIMITE_MAX);

        boolean[] esPrimo = new boolean[limite + 1];
        for (int numero = 2; numero <= limite; numero++) {
            esPrimo[numero] = true;
        }

        for (int numero = 2; numero * numero <= limite; numero++) {
            if (esPrimo[numero]) {
                for (int multiplo = numero * numero; multiplo <= limite; multiplo = multiplo + numero) {
                    esPrimo[multiplo] = false;
                }
            }
        }

        int cantidadPrimos = 0;
        for (int numero = 2; numero <= limite; numero++) {
            if (esPrimo[numero]) {
                System.out.print(numero + " ");
                cantidadPrimos++;
            }
        }
        System.out.println();
        System.out.println("Hay " + cantidadPrimos + " primos hasta " + limite);

        scanner.close();
    }
}
