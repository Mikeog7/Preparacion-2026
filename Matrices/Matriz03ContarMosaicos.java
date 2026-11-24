package Matrices;
/**
 * MATRIZ 03: Contar cuántas casillas hay de cada tipo
 *
 * ENUNCIADO:  Recorre el mapa y muestra cuántas casillas hay de cada tipo, su
 *             porcentaje y cuál es el tipo más abundante.
 * SIRVE PARA: combinar una matriz con un array de FRECUENCIAS: el ID de cada
 *             casilla se usa directamente como índice del contador.
 * CONCEPTO CLAVE:
 *   - conteo[mundo[fila][columna]]++ : un solo if-less contador para todos los tipos.
 *   - Total de casillas = filas * columnas.
 *   - Máximo: se compara cada conteo con el del mejor tipo hasta el momento.
 *   - printf: %s texto, %d entero, %.1f decimal con 1 cifra, %% el símbolo %.
 * ERROR TIPICO: array de conteo con tamaño menor que el nº de tipos (falla con el ID mayor).
 * PRUEBA:     hierba 31 (51,7 %), camino 14, agua 6, montana 3, arbol 3,
 *             tesoro 2, edificio 1. Suma total 60.
 */
public class Matriz03ContarMosaicos {

    public static void main(String[] args) {
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final String[] NOMBRES = { "hierba", "edificio", "camino", "agua", "arena", "piedra",
                "arbusto", "montana", "arbol", "tesoro" };

        int[][] mundo = {
                { 0, 0, 0, 3, 3, 0, 0, 7, 7, 9 },
                { 0, 2, 2, 2, 3, 0, 0, 0, 7, 0 },
                { 0, 2, 1, 2, 3, 3, 0, 0, 0, 0 },
                { 0, 2, 2, 2, 0, 3, 8, 8, 0, 0 },
                { 0, 0, 2, 0, 0, 0, 8, 9, 0, 0 },
                { 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }
        };

        int[] conteo = new int[MOSAICOS.length];
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                conteo[mundo[fila][columna]]++;
            }
        }

        int totalCasillas = mundo.length * mundo[0].length;
        int tipoMasAbundante = 0;

        for (int tipo = 0; tipo < conteo.length; tipo++) {
            if (conteo[tipo] > 0) {
                double porcentaje = conteo[tipo] * 100.0 / totalCasillas;
                System.out.printf("%s %-9s %3d casillas (%.1f%%)%n", MOSAICOS[tipo], NOMBRES[tipo], conteo[tipo], porcentaje);
            }
            if (conteo[tipo] > conteo[tipoMasAbundante]) {
                tipoMasAbundante = tipo;
            }
        }

        System.out.println("Total de casillas: " + totalCasillas);
        System.out.println("Tipo mas abundante: " + NOMBRES[tipoMasAbundante]);
    }
}
