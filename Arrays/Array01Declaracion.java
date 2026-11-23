package Arrays;
/**
 * ARRAY 01: Declarar, crear y acceder
 *
 * ENUNCIADO:  Crea arrays de distintos tipos, comprueba sus valores por defecto,
 *             modifica posiciones y provoca (controlado) un acceso fuera de rango.
 * SIRVE PARA: entender qué ES un array: una fila de casillas del MISMO tipo,
 *             con tamaño FIJO, numeradas desde 0.
 * CONCEPTO CLAVE:
 *   - new int[5]        crea 5 casillas (índices 0 a 4) inicializadas a 0
 *   - {6.5, 3.5, 8.0}   crea e inicializa a la vez
 *   - array.length      tamaño (SIN paréntesis; en String sí: texto.length())
 *   - último índice     array.length - 1
 *   - valores por defecto: int 0 | double 0.0 | boolean false | char '\0' | objetos null
 * ERROR TIPICO: usar el índice length (no existe) -> ArrayIndexOutOfBoundsException.
 * PRUEBA:     ejecútalo y comprueba que nombres[] muestra null y marcas[] false.
 */
public class Array01Declaracion {

    public static void main(String[] args) {
        int[] edades = new int[5];
        double[] notas = {6.5, 3.5, 8.0, 5.0, 9.5};
        String[] nombres = new String[3];
        boolean[] marcas = new boolean[3];

        System.out.println("Tamano de edades: " + edades.length);
        System.out.println("Tamano de notas: " + notas.length);

        System.out.println("--- Valores por defecto ---");
        for (int posicion = 0; posicion < edades.length; posicion++) {
            System.out.println("edades[" + posicion + "] = " + edades[posicion]);
        }
        for (int posicion = 0; posicion < nombres.length; posicion++) {
            System.out.println("nombres[" + posicion + "] = " + nombres[posicion]);
        }
        for (int posicion = 0; posicion < marcas.length; posicion++) {
            System.out.println("marcas[" + posicion + "] = " + marcas[posicion]);
        }

        System.out.println("--- Modificar posiciones ---");
        edades[0] = 20;
        edades[edades.length - 1] = 35;          // la ultima posicion es length - 1
        nombres[0] = "Ana";
        notas[1] = 4.0;
        for (int posicion = 0; posicion < edades.length; posicion++) {
            System.out.println("edades[" + posicion + "] = " + edades[posicion]);
        }
        System.out.println("notas[1] = " + notas[1]);
        System.out.println("ultima nota = " + notas[notas.length - 1]);

        System.out.println("--- Acceso fuera de rango ---");
        try {
            System.out.println(notas[notas.length]);   // indice 5 en un array de 5: NO existe
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
