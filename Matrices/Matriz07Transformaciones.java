package Matrices;
/**
 * MATRIZ 07: Transpuesta, espejos y rotación 90 grados
 *
 * ENUNCIADO:  Dada una matriz de 3 filas x 5 columnas, crea y dibuja: la
 *             transpuesta, el espejo horizontal, el espejo vertical y la
 *             rotación de 90 grados a la derecha.
 * SIRVE PARA: practicar la relación entre las coordenadas de origen y destino
 *             (es la base de girar mapas, imágenes o piezas).
 * CONCEPTO CLAVE:
 *   - Transpuesta:        destino[columna][fila]              = origen[fila][columna]
 *   - Espejo horizontal:  destino[fila][columnas - 1 - columna] = origen[fila][columna]
 *   - Espejo vertical:    destino[filas - 1 - fila][columna]    = origen[fila][columna]
 *   - Rotación 90 horaria: destino[columna][filas - 1 - fila]  = origen[fila][columna]
 *   - Al transponer o rotar, las DIMENSIONES SE INTERCAMBIAN: new int[columnas][filas].
 *   - Siempre sobre una matriz NUEVA (sobre la misma se pisarían valores).
 * ERROR TIPICO: crear la matriz destino con las mismas dimensiones al rotar.
 * PRUEBA:     la rotada tiene 5 filas x 3 columnas; la esquina superior izquierda
 *             del original acaba en la esquina superior derecha de la rotada.
 */
public class Matriz07Transformaciones {

    static final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };

    static void mostrar(String titulo, int[][] matriz) {
        System.out.println("--- " + titulo + " (" + matriz.length + " x " + matriz[0].length + ") ---");
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                System.out.print(MOSAICOS[matriz[fila][columna]]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] original = {
                { 7, 0, 3, 3, 0 },
                { 0, 1, 1, 3, 0 },
                { 2, 2, 2, 0, 8 }
        };

        int filas = original.length;
        int columnas = original[0].length;

        int[][] transpuesta = new int[columnas][filas];
        int[][] espejoHorizontal = new int[filas][columnas];
        int[][] espejoVertical = new int[filas][columnas];
        int[][] rotada = new int[columnas][filas];

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                transpuesta[columna][fila] = original[fila][columna];
                espejoHorizontal[fila][columnas - 1 - columna] = original[fila][columna];
                espejoVertical[filas - 1 - fila][columna] = original[fila][columna];
                rotada[columna][filas - 1 - fila] = original[fila][columna];
            }
        }

        mostrar("Original", original);
        mostrar("Transpuesta", transpuesta);
        mostrar("Espejo horizontal", espejoHorizontal);
        mostrar("Espejo vertical", espejoVertical);
        mostrar("Rotada 90 grados a la derecha", rotada);
    }
}
