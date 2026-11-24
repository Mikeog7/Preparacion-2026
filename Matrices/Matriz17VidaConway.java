package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 17: Juego de la vida (generaciones)
 *
 * ENUNCIADO:  En una cuadrícula de 8 x 8 hay un "planeador" (5 celdas vivas). Pide
 *             cuántas generaciones simular (1 a 12) y dibuja cada una.
 *             Reglas: una celda viva con 2 o 3 vecinas vivas sigue viva; una muerta
 *             con exactamente 3 vecinas vivas nace; en cualquier otro caso muere o
 *             sigue muerta.
 * SIRVE PARA: el patrón de DOS matrices: leer la generación actual y escribir la
 *             siguiente. Si se escribiera sobre la misma, las celdas ya cambiadas
 *             alterarían el cálculo de las demás.
 * CONCEPTO CLAVE:
 *   - siguienteGeneracion(actual) crea una matriz nueva y la devuelve.
 *   - generacion = siguienteGeneracion(generacion): la variable pasa a apuntar a la nueva.
 *   - Contar vecinas igual que en el buscaminas (8 direcciones con control de bordes).
 *   - Bordes sin "dar la vuelta": lo que sale por el borde desaparece.
 * ERROR TIPICO: actualizar la misma matriz mientras se recorre.
 * PRUEBA:     el planeador avanza una celda en diagonal (abajo-derecha) cada 4 generaciones.
 */
public class Matriz17VidaConway {

    static final int VIVA = 1;
    static final int MUERTA = 0;
    static final int DESPLAZAMIENTO_MIN = -1;
    static final int DESPLAZAMIENTO_MAX = 1;
    static final int VECINAS_SUPERVIVENCIA_MIN = 2;
    static final int VECINAS_SUPERVIVENCIA_MAX = 3;
    static final int VECINAS_NACIMIENTO = 3;

    static int contarVecinas(int[][] matriz, int fila, int columna) {
        int vecinas = 0;
        for (int desplazamientoFila = DESPLAZAMIENTO_MIN; desplazamientoFila <= DESPLAZAMIENTO_MAX; desplazamientoFila++) {
            for (int desplazamientoColumna = DESPLAZAMIENTO_MIN; desplazamientoColumna <= DESPLAZAMIENTO_MAX; desplazamientoColumna++) {
                int filaVecina = fila + desplazamientoFila;
                int columnaVecina = columna + desplazamientoColumna;
                boolean esLaMisma = desplazamientoFila == 0 && desplazamientoColumna == 0;
                boolean dentro = filaVecina >= 0 && filaVecina < matriz.length
                        && columnaVecina >= 0 && columnaVecina < matriz[0].length;
                if (!esLaMisma && dentro && matriz[filaVecina][columnaVecina] == VIVA) {
                    vecinas++;
                }
            }
        }
        return vecinas;
    }

    static int[][] siguienteGeneracion(int[][] actual) {
        int[][] siguiente = new int[actual.length][actual[0].length];
        for (int fila = 0; fila < actual.length; fila++) {
            for (int columna = 0; columna < actual[fila].length; columna++) {
                int vecinas = contarVecinas(actual, fila, columna);
                if (actual[fila][columna] == VIVA) {
                    if (vecinas >= VECINAS_SUPERVIVENCIA_MIN && vecinas <= VECINAS_SUPERVIVENCIA_MAX) {
                        siguiente[fila][columna] = VIVA;
                    }
                } else if (vecinas == VECINAS_NACIMIENTO) {
                    siguiente[fila][columna] = VIVA;
                }
            }
        }
        return siguiente;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int FILAS = 8;
        final int COLUMNAS = 8;
        final int GENERACIONES_MIN = 1;
        final int GENERACIONES_MAX = 12;

        int[][] generacion = new int[FILAS][COLUMNAS];
        generacion[0][1] = VIVA;
        generacion[1][2] = VIVA;
        generacion[2][0] = VIVA;
        generacion[2][1] = VIVA;
        generacion[2][2] = VIVA;

        int generaciones;
        do {
            System.out.println("Cuantas generaciones (1-12): ");
            generaciones = scanner.nextInt();
        } while (generaciones < GENERACIONES_MIN || generaciones > GENERACIONES_MAX);

        for (int numero = 0; numero <= generaciones; numero++) {
            System.out.println("--- Generacion " + numero + " ---");
            for (int fila = 0; fila < generacion.length; fila++) {
                for (int columna = 0; columna < generacion[fila].length; columna++) {
                    System.out.print(generacion[fila][columna] == VIVA ? " # " : " . ");
                }
                System.out.println();
            }
            if (numero < generaciones) {
                generacion = siguienteGeneracion(generacion);
            }
        }

        scanner.close();
    }
}
