package Logica;
import java.util.Scanner;

/**
 * PASO 28: Números primos hasta un límite
 *
 * ENUNCIADO:  Pide un límite (2 a 10000) y muestra todos los primos hasta él,
 *             junto con cuántos hay.
 * SIRVE PARA: combinar un bucle exterior con una búsqueda interior que debe
 *             pararse al encontrar un divisor... SIN usar break.
 * USA:        for exterior (candidatos), while interior con bandera (esPrimo)
 *             en la condición, módulo (%), contador.
 * EN EL EXAMEN: "es primo", "divisores", "perfecto". La bandera sustituye al break:
 *             el while se corta porque "esPrimo" pasa a false.
 *             Basta probar divisores hasta la raíz: divisor * divisor <= candidato.
 * PRUEBA:     20 -> 2 3 5 7 11 13 17 19 (8 primos).   2 -> 2 (1 primo).
 */
public class Paso28NumerosPrimos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int LIMITE_MIN = 2;
        final int LIMITE_MAX = 10000;

        int limite;
        do {
            System.out.println("Limite (2-10000): ");
            limite = scanner.nextInt();
        } while (limite < LIMITE_MIN || limite > LIMITE_MAX);

        int cantidadPrimos = 0;

        for (int candidato = 2; candidato <= limite; candidato++) {
            boolean esPrimo = true;
            int divisor = 2;

            while (divisor * divisor <= candidato && esPrimo) {
                if (candidato % divisor == 0) {
                    esPrimo = false;
                }
                divisor++;
            }

            if (esPrimo) {
                System.out.print(candidato + " ");
                cantidadPrimos++;
            }
        }

        System.out.println();
        System.out.println("Hay " + cantidadPrimos + " primos hasta " + limite);

        scanner.close();
    }
}
