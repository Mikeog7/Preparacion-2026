package Matrices;
/**
 * MATRIZ 15: Contar zonas conectadas (lagos)
 *
 * ENUNCIADO:  En un mapa con casillas de agua (3) dispersas, cuenta cuántos lagos
 *             hay (grupos de casillas de agua unidas por arriba, abajo, izquierda
 *             o derecha), el tamaño de cada uno y cuál es el mayor.
 * SIRVE PARA: razonar con VECINDAD en una matriz y con etiquetas.
 * CONCEPTO CLAVE:
 *   - Matriz de etiquetas int[][] (0 = sin zona): cada lago recibe un número.
 *   - Se recorre el mapa; al encontrar agua SIN etiquetar, se crea una zona nueva
 *     y se "expande": se repiten barridos etiquetando las casillas de agua que
 *     tienen un vecino ya etiquetado con esa zona, hasta que un barrido no cambia nada.
 *   - Vecinos de 4 direcciones; antes de mirar un vecino se comprueba que exista
 *     (fila > 0, fila < filas - 1, columna > 0, columna < columnas - 1).
 *   - Tamaño de cada zona: contar cuántas casillas tienen su etiqueta.
 * ERROR TIPICO: leer un vecino sin comprobar el borde, o contar como un mismo lago
 *             dos grupos que solo se tocan en diagonal (aquí NO se unen).
 * PRUEBA:     7 lagos con tamaños 3, 4, 3, 5, 3, 3 y 1; el mayor mide 5 (zona 4).
 */
public class Matriz15ZonasConectadas {

    static final int AGUA = 3;

    static boolean tieneVecino(int[][] etiquetas, int fila, int columna, int zona) {
        boolean tiene = false;
        if (fila > 0 && etiquetas[fila - 1][columna] == zona) {
            tiene = true;
        }
        if (fila < etiquetas.length - 1 && etiquetas[fila + 1][columna] == zona) {
            tiene = true;
        }
        if (columna > 0 && etiquetas[fila][columna - 1] == zona) {
            tiene = true;
        }
        if (columna < etiquetas[fila].length - 1 && etiquetas[fila][columna + 1] == zona) {
            tiene = true;
        }
        return tiene;
    }

    public static void main(String[] args) {
        int[][] mapa = {
                { 3, 3, 0, 0, 0, 0, 3, 3, 0, 0 },
                { 3, 0, 0, 3, 3, 0, 3, 3, 0, 0 },
                { 0, 3, 0, 3, 0, 0, 0, 0, 0, 3 },
                { 0, 3, 0, 0, 0, 0, 0, 0, 3, 3 },
                { 0, 3, 3, 0, 0, 3, 0, 0, 0, 0 },
                { 0, 3, 0, 0, 0, 3, 3, 0, 0, 3 }
        };

        int[][] etiquetas = new int[mapa.length][mapa[0].length];
        int zonas = 0;

        for (int fila = 0; fila < mapa.length; fila++) {
            for (int columna = 0; columna < mapa[fila].length; columna++) {
                if (mapa[fila][columna] == AGUA && etiquetas[fila][columna] == 0) {
                    zonas++;
                    etiquetas[fila][columna] = zonas;

                    boolean hayCambios;
                    do {
                        hayCambios = false;
                        for (int filaBarrido = 0; filaBarrido < mapa.length; filaBarrido++) {
                            for (int columnaBarrido = 0; columnaBarrido < mapa[filaBarrido].length; columnaBarrido++) {
                                boolean esAguaSinEtiqueta = mapa[filaBarrido][columnaBarrido] == AGUA
                                        && etiquetas[filaBarrido][columnaBarrido] == 0;
                                if (esAguaSinEtiqueta && tieneVecino(etiquetas, filaBarrido, columnaBarrido, zonas)) {
                                    etiquetas[filaBarrido][columnaBarrido] = zonas;
                                    hayCambios = true;
                                }
                            }
                        }
                    } while (hayCambios);
                }
            }
        }

        System.out.println("--- Mapa de lagos (numero = zona) ---");
        for (int fila = 0; fila < etiquetas.length; fila++) {
            for (int columna = 0; columna < etiquetas[fila].length; columna++) {
                if (etiquetas[fila][columna] == 0) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + etiquetas[fila][columna] + " ");
                }
            }
            System.out.println();
        }

        int zonaMayor = 0;
        int tamanoMayor = 0;
        for (int zona = 1; zona <= zonas; zona++) {
            int tamano = 0;
            for (int fila = 0; fila < etiquetas.length; fila++) {
                for (int columna = 0; columna < etiquetas[fila].length; columna++) {
                    if (etiquetas[fila][columna] == zona) {
                        tamano++;
                    }
                }
            }
            System.out.println("Lago " + zona + ": " + tamano + " casillas");
            if (tamano > tamanoMayor) {
                tamanoMayor = tamano;
                zonaMayor = zona;
            }
        }

        System.out.println("Numero de lagos: " + zonas);
        System.out.println("Lago mayor: " + zonaMayor + " con " + tamanoMayor + " casillas");
    }
}
