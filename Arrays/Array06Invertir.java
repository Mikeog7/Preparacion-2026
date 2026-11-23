package Arrays;
import java.util.Scanner;

/**
 * ARRAY 06: Invertir un array y detectar palíndromos
 *
 * ENUNCIADO:  Lee N enteros. Crea una copia invertida, comprueba si el array es
 *             un palíndromo (se lee igual al revés) y después invierte el
 *             original "en el sitio".
 * SIRVE PARA: el intercambio con variable auxiliar y el recorrido hasta la mitad.
 * CONCEPTO CLAVE:
 *   - Nuevo array: invertido[length - 1 - i] = datos[i]
 *   - En el sitio: intercambiar datos[i] con datos[length - 1 - i] solo hasta la
 *     MITAD (length / 2); si se llega al final se deshace lo hecho.
 *   - Intercambio: aux = a; a = b; b = aux (sin aux se pierde un valor).
 * ERROR TIPICO: recorrer todo el array al invertir en el sitio (queda igual).
 * PRUEBA:     1 2 3 2 1 -> palíndromo.   1 2 3 4 -> invertido 4 3 2 1, no palíndromo.
 */
public class Array06Invertir {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CANTIDAD_MIN = 1;
        final int CANTIDAD_MAX = 10;
        final int MITAD = 2;

        int cantidad;
        do {
            System.out.println("Cuantos numeros (1-10): ");
            cantidad = scanner.nextInt();
        } while (cantidad < CANTIDAD_MIN || cantidad > CANTIDAD_MAX);

        int[] datos = new int[cantidad];
        for (int posicion = 0; posicion < datos.length; posicion++) {
            System.out.println("Numero " + (posicion + 1) + ": ");
            datos[posicion] = scanner.nextInt();
        }

        // 1) Copia invertida en un array nuevo.
        int[] invertido = new int[datos.length];
        for (int posicion = 0; posicion < datos.length; posicion++) {
            invertido[datos.length - 1 - posicion] = datos[posicion];
        }

        // 2) Palindromo: comparar el original con la copia invertida.
        boolean esPalindromo = true;
        for (int posicion = 0; posicion < datos.length && esPalindromo; posicion++) {
            if (datos[posicion] != invertido[posicion]) {
                esPalindromo = false;
            }
        }
        System.out.println(esPalindromo ? "Es palindromo" : "No es palindromo");

        // 3) Invertir el original en el sitio (solo hasta la mitad).
        for (int posicion = 0; posicion < datos.length / MITAD; posicion++) {
            int auxiliar = datos[posicion];
            datos[posicion] = datos[datos.length - 1 - posicion];
            datos[datos.length - 1 - posicion] = auxiliar;
        }

        System.out.println("Original invertido en el sitio:");
        for (int posicion = 0; posicion < datos.length; posicion++) {
            System.out.print(datos[posicion] + " ");
        }
        System.out.println();

        scanner.close();
    }
}
