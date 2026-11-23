package Arrays;
import java.util.Arrays;

/**
 * ARRAY 07: Copiar arrays, alias y comparación
 *
 * ENUNCIADO:  Demuestra la diferencia entre asignar un array a otra variable
 *             (alias) y copiarlo de verdad, y entre comparar con == o por contenido.
 * SIRVE PARA: entender que un array es una REFERENCIA: la variable guarda
 *             "dónde está" el array, no los datos.
 * CONCEPTO CLAVE:
 *   - b = a            NO copia: a y b son dos nombres del MISMO array.
 *   - copia manual     new int[n] + for.
 *   - Arrays.copyOf(a, n)         copia (y puede ampliar/recortar).
 *   - Arrays.copyOfRange(a, i, j) copia las posiciones i a j-1.
 *   - a == b           compara referencias.  Arrays.equals(a, b) compara contenido.
 *   - Arrays.toString(a) para imprimir (System.out.println(a) muestra [I@1b6d3586).
 * ERROR TIPICO: creer que "copia = original" crea una copia independiente.
 * PRUEBA:     ejecútalo: el original cambia al tocar el alias, pero no al tocar la copia.
 */
public class Array07CopiaYAlias {

    public static void main(String[] args) {
        System.out.println("--- Alias (NO es una copia) ---");
        int[] original = {1, 2, 3, 4, 5};
        int[] alias = original;
        alias[0] = 99;
        System.out.println("original: " + Arrays.toString(original));   // [99, 2, 3, 4, 5]

        System.out.println("--- Copia manual ---");
        int[] datos = {1, 2, 3, 4, 5};
        int[] copiaManual = new int[datos.length];
        for (int posicion = 0; posicion < datos.length; posicion++) {
            copiaManual[posicion] = datos[posicion];
        }
        copiaManual[0] = 99;
        System.out.println("datos:       " + Arrays.toString(datos));        // intacto
        System.out.println("copiaManual: " + Arrays.toString(copiaManual));

        System.out.println("--- Con la libreria ---");
        final int ELEMENTOS_EXTRA = 3;
        int[] copiaLibreria = Arrays.copyOf(datos, datos.length);
        int[] ampliada = Arrays.copyOf(datos, datos.length + ELEMENTOS_EXTRA);   // extras a 0
        int[] parte = Arrays.copyOfRange(datos, 1, 4);                             // indices 1 a 3
        System.out.println("copia:    " + Arrays.toString(copiaLibreria));
        System.out.println("ampliada: " + Arrays.toString(ampliada));
        System.out.println("parte:    " + Arrays.toString(parte));

        System.out.println("--- Comparar ---");
        System.out.println("datos == copiaLibreria: " + (datos == copiaLibreria));              // false
        System.out.println("equals por contenido:   " + Arrays.equals(datos, copiaLibreria));   // true
    }
}
