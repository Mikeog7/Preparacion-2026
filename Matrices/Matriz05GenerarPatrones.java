package Matrices;
/**
 * MATRIZ 05: Generar mapas con bucles (patrones)
 *
 * ENUNCIADO:  Crea y dibuja cuatro mapas de 7 x 11 SIN escribir los datos a mano:
 *             un marco de agua, una diagonal de camino, un tablero alternado y
 *             franjas horizontales.
 * SIRVE PARA: relacionar la posición (fila, columna) con el contenido de la casilla.
 * CONCEPTO CLAVE:
 *   - Borde: fila == 0 || fila == FILAS - 1 || columna == 0 || columna == COLUMNAS - 1
 *   - Diagonal: fila == columna.
 *   - Alternado: (fila + columna) % 2 == 0.
 *   - Franjas: patron[fila % patron.length] (el resto repite el patrón).
 *   - new int[F][C] ya viene a 0: solo se escriben las casillas distintas.
 *   - Un método recibe la matriz (por referencia) y la dibuja.
 * ERROR TIPICO: usar FILAS en lugar de COLUMNAS al revés (mapa no cuadrado) o
 *             olvidar el "- 1" en el borde derecho/inferior.
 * PRUEBA:     el marco debe tener agua en toda la primera y última fila y en la
 *             primera y última columna.
 */
public class Matriz05GenerarPatrones {

    static final int HIERBA = 0;
    static final int CAMINO = 2;
    static final int AGUA = 3;
    static final int ARENA = 4;
    static final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };

    static void mostrar(String titulo, int[][] matriz) {
        System.out.println("--- " + titulo + " ---");
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                System.out.print(MOSAICOS[matriz[fila][columna]]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final int FILAS = 7;
        final int COLUMNAS = 11;

        int[][] marco = new int[FILAS][COLUMNAS];
        int[][] diagonal = new int[FILAS][COLUMNAS];
        int[][] alternado = new int[FILAS][COLUMNAS];
        int[][] franjas = new int[FILAS][COLUMNAS];
        int[] patronFranjas = { HIERBA, CAMINO, AGUA };

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {

                boolean esBorde = fila == 0 || fila == FILAS - 1 || columna == 0 || columna == COLUMNAS - 1;
                if (esBorde) {
                    marco[fila][columna] = AGUA;
                }

                if (fila == columna) {
                    diagonal[fila][columna] = CAMINO;
                }

                alternado[fila][columna] = (fila + columna) % 2 == 0 ? HIERBA : ARENA;

                franjas[fila][columna] = patronFranjas[fila % patronFranjas.length];
            }
        }

        mostrar("Marco de agua", marco);
        mostrar("Diagonal de camino", diagonal);
        mostrar("Tablero alternado", alternado);
        mostrar("Franjas horizontales", franjas);
    }
}
