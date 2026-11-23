package Arrays;
import java.util.Arrays;

/**
 * ARRAY 19: Arrays y métodos (paso por referencia)
 *
 * ENUNCIADO:  Crea métodos que reciben arrays: sumar, máximo, comprobar si está
 *             ordenado, contar mayores que un límite, duplicar (modifica el
 *             array recibido) y crear un invertido (devuelve un array nuevo).
 *             Demuestra qué le pasa al array original en cada caso.
 * SIRVE PARA: entender que a un método se le pasa la REFERENCIA del array, no
 *             una copia de los datos.
 * CONCEPTO CLAVE:
 *   - Si el método MODIFICA posiciones (datos[i] = ...), el cambio se ve fuera.
 *   - Si el método REASIGNA el parámetro (datos = new int[...]), fuera NO cambia:
 *     solo se cambia la referencia local.
 *   - Un método puede devolver un array nuevo (int[] como tipo de retorno).
 *   - Los métodos que solo leen no deben modificar el array recibido.
 * ERROR TIPICO: esperar que "datos = new int[3]" dentro del método cambie el array de fuera.
 * PRUEBA:     ejecútalo: tras duplicar, el original cambia; tras intentarReemplazar, no.
 */
public class Array19ArraysYMetodos {

    static final int FACTOR_DUPLICAR = 2;

    static int sumar(int[] datos) {
        int suma = 0;
        for (int posicion = 0; posicion < datos.length; posicion++) {
            suma = suma + datos[posicion];
        }
        return suma;
    }

    static int maximo(int[] datos) {
        int valorMaximo = datos[0];
        for (int posicion = 1; posicion < datos.length; posicion++) {
            if (datos[posicion] > valorMaximo) {
                valorMaximo = datos[posicion];
            }
        }
        return valorMaximo;
    }

    static boolean esOrdenado(int[] datos) {
        boolean ordenado = true;
        for (int posicion = 0; posicion < datos.length - 1 && ordenado; posicion++) {
            if (datos[posicion] > datos[posicion + 1]) {
                ordenado = false;
            }
        }
        return ordenado;
    }

    static int contarMayoresQue(int[] datos, int limite) {
        int cantidad = 0;
        for (int posicion = 0; posicion < datos.length; posicion++) {
            if (datos[posicion] > limite) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // MODIFICA el array recibido: el cambio se ve fuera del metodo.
    static void duplicar(int[] datos) {
        for (int posicion = 0; posicion < datos.length; posicion++) {
            datos[posicion] = datos[posicion] * FACTOR_DUPLICAR;
        }
    }

    // DEVUELVE un array nuevo: el original queda intacto.
    static int[] crearInvertido(int[] datos) {
        int[] invertido = new int[datos.length];
        for (int posicion = 0; posicion < datos.length; posicion++) {
            invertido[datos.length - 1 - posicion] = datos[posicion];
        }
        return invertido;
    }

    // REASIGNA el parametro: solo cambia la referencia LOCAL, no el array de fuera.
    static void intentarReemplazar(int[] datos) {
        datos = new int[] {0, 0, 0};
    }

    public static void main(String[] args) {
        int[] datos = {3, 8, 1, 9, 4};
        System.out.println("Datos: " + Arrays.toString(datos));

        System.out.println("Suma: " + sumar(datos));
        System.out.println("Maximo: " + maximo(datos));
        System.out.println("Ordenado? " + esOrdenado(datos));
        System.out.println("Mayores que 3: " + contarMayoresQue(datos, 3));

        int[] invertido = crearInvertido(datos);
        System.out.println("Invertido: " + Arrays.toString(invertido));
        System.out.println("Original tras invertir: " + Arrays.toString(datos));     // intacto

        duplicar(datos);
        System.out.println("Original tras duplicar: " + Arrays.toString(datos));     // CAMBIA

        intentarReemplazar(datos);
        System.out.println("Original tras intentarReemplazar: " + Arrays.toString(datos));   // NO cambia
    }
}
