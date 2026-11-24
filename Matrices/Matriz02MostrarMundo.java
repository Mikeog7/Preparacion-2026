package Matrices;
/**
 * MATRIZ 02: Dibujar un mundo con una tabla de traducción
 *
 * ENUNCIADO:  Dada una matriz de números que representa un mapa, dibújala usando
 *             un símbolo distinto para cada número. Muestra también la leyenda.
 * SIRVE PARA: el patrón del juego de referencia: la matriz guarda IDs (números) y
 *             un array auxiliar los convierte en texto.
 * CONCEPTO CLAVE:
 *   - mosaicos[mundo[fila][columna]]: el valor de la matriz se usa como ÍNDICE del
 *     array de símbolos. Datos compactos (números), presentación aparte.
 *   - print dentro de la fila, println al terminar cada fila.
 *   - "-".repeat(n) para dibujar un separador del ancho del mapa.
 * ERROR TIPICO: un ID de la matriz mayor o igual que mosaicos.length -> el programa
 *             se cae. Cada número del mapa debe tener su símbolo.
 * PRUEBA:     el mapa es de 6 filas x 10 columnas; el separador mide 30 caracteres.
 *
 * CONVENCION DE ESTOS EJERCICIOS (propia): 0 hierba, 1 edificio, 2 camino, 3 agua,
 * 4 arena, 5 piedra, 6 arbusto, 7 montana, 8 arbol, 9 tesoro.
 */
public class Matriz02MostrarMundo {

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

        System.out.println("--- Los datos (numeros) ---");
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                System.out.print(mundo[fila][columna] + " ");
            }
            System.out.println();
        }

        System.out.println("--- El mundo (simbolos) ---");
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                System.out.print(MOSAICOS[mundo[fila][columna]]);
            }
            System.out.println();
        }
        System.out.println("-".repeat(mundo[0].length * MOSAICOS[0].length()));

        System.out.println("--- Leyenda ---");
        for (int tipo = 0; tipo < MOSAICOS.length; tipo++) {
            System.out.println(tipo + " = " + MOSAICOS[tipo] + " " + NOMBRES[tipo]);
        }
    }
}
