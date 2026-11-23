package Arrays;
import java.util.Arrays;

/**
 * ARRAY 15: Mezclar dos arrays ordenados (merge)
 *
 * ENUNCIADO:  Dados dos arrays ordenados de distinto tamaño, crea un tercero
 *             ordenado con todos sus elementos SIN volver a ordenar.
 * SIRVE PARA: el patrón de "tres índices": uno por cada array de entrada y uno
 *             para el de salida. Es la base de algoritmos de ordenación avanzados.
 * CONCEPTO CLAVE:
 *   - Mientras queden elementos en AMBOS: copiar el menor y avanzar solo ese índice.
 *   - Cuando uno se agota, copiar lo que queda del otro (dos while finales).
 *   - El tamaño del resultado es la suma de los tamaños.
 * ERROR TIPICO: avanzar los dos índices a la vez, o olvidar copiar los restos.
 * PRUEBA:     {1, 4, 7, 10, 15} y {2, 3, 8, 9, 20, 25, 30} ->
 *             [1, 2, 3, 4, 7, 8, 9, 10, 15, 20, 25, 30]
 */
public class Array15Mezclar {

    public static void main(String[] args) {
        int[] primero = {1, 4, 7, 10, 15};
        int[] segundo = {2, 3, 8, 9, 20, 25, 30};
        int[] mezcla = new int[primero.length + segundo.length];

        int indicePrimero = 0;
        int indiceSegundo = 0;
        int indiceMezcla = 0;

        while (indicePrimero < primero.length && indiceSegundo < segundo.length) {
            if (primero[indicePrimero] <= segundo[indiceSegundo]) {
                mezcla[indiceMezcla] = primero[indicePrimero];
                indicePrimero++;
            } else {
                mezcla[indiceMezcla] = segundo[indiceSegundo];
                indiceSegundo++;
            }
            indiceMezcla++;
        }

        while (indicePrimero < primero.length) {
            mezcla[indiceMezcla] = primero[indicePrimero];
            indicePrimero++;
            indiceMezcla++;
        }

        while (indiceSegundo < segundo.length) {
            mezcla[indiceMezcla] = segundo[indiceSegundo];
            indiceSegundo++;
            indiceMezcla++;
        }

        System.out.println("Primero: " + Arrays.toString(primero));
        System.out.println("Segundo: " + Arrays.toString(segundo));
        System.out.println("Mezcla:  " + Arrays.toString(mezcla));
    }
}
