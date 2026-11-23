package Logica;
import java.util.Scanner;

/**
 * PASO 27: Trabajar con las cifras de un número
 *
 * ENUNCIADO:  Pide un entero positivo (hasta 999999999). Muestra cuántas cifras
 *             tiene, la suma de sus cifras, el número invertido y si es capicúa.
 * SIRVE PARA: descomponer un número sin convertirlo a texto.
 * USA:        while con la pareja  % 10 (última cifra)  y  / 10 (quita la última),
 *             una COPIA del número para no perder el original, acumuladores.
 * EN EL EXAMEN: "suma de cifras", "invertir", "capicúa", "cuántos dígitos".
 *             Error típico: modificar el número original y luego compararlo.
 * PRUEBA:     12321 -> 5 cifras, suma 9, invertido 12321, capicúa.
 *             1230  -> 4 cifras, suma 6, invertido 321 (el cero inicial desaparece), no capicúa.
 */
public class Paso27DigitosNumero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NUMERO_MIN = 1;
        final int NUMERO_MAX = 999999999;
        final int BASE = 10;

        int numero;
        do {
            System.out.println("Escribe un entero positivo: ");
            numero = scanner.nextInt();
        } while (numero < NUMERO_MIN || numero > NUMERO_MAX);

        int copia = numero;
        int cantidadCifras = 0;
        int sumaCifras = 0;
        int invertido = 0;

        while (copia > 0) {
            int cifra = copia % BASE;
            sumaCifras = sumaCifras + cifra;
            invertido = invertido * BASE + cifra;
            cantidadCifras++;
            copia = copia / BASE;
        }

        System.out.println("Cifras: " + cantidadCifras);
        System.out.println("Suma de cifras: " + sumaCifras);
        System.out.println("Invertido: " + invertido);
        System.out.println(invertido == numero ? "Es capicua" : "No es capicua");

        scanner.close();
    }
}
