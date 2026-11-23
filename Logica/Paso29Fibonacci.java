package Logica;
import java.util.Scanner;

/**
 * PASO 29: Series con variables de estado (Fibonacci y factorial)
 *
 * ENUNCIADO:  a) Pide cuántos términos (1 a 40) y muestra la serie de Fibonacci
 *                (1 1 2 3 5 8...) con la suma de sus términos pares.
 *             b) Pide un número (0 a 20) y calcula su factorial.
 * SIRVE PARA: calcular cada valor a partir de los anteriores.
 * USA:        variables "anterior" y "actual" que se desplazan en cada vuelta,
 *             tipo long (los valores crecen rápido), acumulador multiplicativo
 *             que empieza en 1 (no en 0).
 * EN EL EXAMEN: "serie", "cada término es la suma de los dos anteriores", "factorial".
 *             Error típico: sobrescribir "actual" antes de guardar el siguiente.
 * PRUEBA:     6 términos -> 1 1 2 3 5 8, suma de pares 10.   5! = 120.   0! = 1.
 */
public class Paso29Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int TERMINOS_MIN = 1;
        final int TERMINOS_MAX = 40;
        final int FACTORIAL_MIN = 0;
        final int FACTORIAL_MAX = 20;

        // ----- a) Fibonacci -----
        int terminos;
        do {
            System.out.println("Cuantos terminos de Fibonacci (1-40): ");
            terminos = scanner.nextInt();
        } while (terminos < TERMINOS_MIN || terminos > TERMINOS_MAX);

        long anterior = 0;
        long actual = 1;
        long sumaPares = 0;

        for (int termino = 1; termino <= terminos; termino++) {
            System.out.print(actual + " ");
            if (actual % 2 == 0) {
                sumaPares = sumaPares + actual;
            }
            long siguiente = anterior + actual;   // se calcula ANTES de desplazar
            anterior = actual;
            actual = siguiente;
        }
        System.out.println();
        System.out.println("Suma de los terminos pares: " + sumaPares);

        // ----- b) Factorial -----
        int numero;
        do {
            System.out.println("Numero para el factorial (0-20): ");
            numero = scanner.nextInt();
        } while (numero < FACTORIAL_MIN || numero > FACTORIAL_MAX);

        long factorial = 1;
        for (int factor = 2; factor <= numero; factor++) {
            factorial = factorial * factor;
        }
        System.out.println(numero + "! = " + factorial);

        scanner.close();
    }
}
