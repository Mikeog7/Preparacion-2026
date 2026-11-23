package Arrays;
import java.util.Scanner;

/**
 * ARRAY 16: Matriz de notas (alumnos x asignaturas)
 *
 * ENUNCIADO:  Para 3 alumnos y 4 asignaturas, lee todas las notas (validadas).
 *             Muestra la tabla, la media de cada alumno, la media de cada
 *             asignatura, el mejor alumno y la nota más alta con su posición.
 * SIRVE PARA: arrays de DOS dimensiones: filas y columnas.
 * CONCEPTO CLAVE:
 *   - double[][] notas = new double[ALUMNOS][ASIGNATURAS];  notas[fila][columna]
 *   - notas.length = nº de FILAS;  notas[0].length = nº de COLUMNAS.
 *   - Recorrer por filas: bucle externo = filas, interno = columnas.
 *   - Recorrer por columnas: se INTERCAMBIAN los bucles.
 *   - La suma parcial de cada fila/columna se declara DENTRO del bucle exterior
 *     (se reinicia en cada fila o columna).
 * ERROR TIPICO: escribir notas[columna][fila] (índices al revés) o declarar la
 *             suma fuera del bucle exterior (arrastra el total anterior).
 * PRUEBA:     usa notas fáciles (todas 5) y comprueba que todas las medias son 5.
 */
public class Array16MatrizNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ALUMNOS = 3;
        final int ASIGNATURAS = 4;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;

        double[][] notas = new double[ALUMNOS][ASIGNATURAS];

        for (int alumno = 0; alumno < ALUMNOS; alumno++) {
            for (int asignatura = 0; asignatura < ASIGNATURAS; asignatura++) {
                boolean notaValida;
                do {
                    System.out.println("Alumno " + (alumno + 1) + ", asignatura " + (asignatura + 1) + ": ");
                    notas[alumno][asignatura] = scanner.nextDouble();
                    notaValida = notas[alumno][asignatura] >= NOTA_MINIMA
                            && notas[alumno][asignatura] <= NOTA_MAXIMA;
                    if (!notaValida) {
                        System.out.println("No es valido, vuelve a intentar.");
                    }
                } while (!notaValida);
            }
        }

        System.out.println("--- Tabla (filas = alumnos) ---");
        for (int alumno = 0; alumno < ALUMNOS; alumno++) {
            for (int asignatura = 0; asignatura < ASIGNATURAS; asignatura++) {
                System.out.print(notas[alumno][asignatura] + "\t");
            }
            System.out.println();
        }

        System.out.println("--- Media por alumno (por filas) ---");
        int mejorAlumno = 0;
        double mejorMedia = 0;
        for (int alumno = 0; alumno < ALUMNOS; alumno++) {
            double sumaAlumno = 0;                          // se reinicia en cada fila
            for (int asignatura = 0; asignatura < ASIGNATURAS; asignatura++) {
                sumaAlumno = sumaAlumno + notas[alumno][asignatura];
            }
            double mediaAlumno = sumaAlumno / ASIGNATURAS;
            System.out.println("Alumno " + (alumno + 1) + ": " + mediaAlumno);
            if (alumno == 0 || mediaAlumno > mejorMedia) {
                mejorMedia = mediaAlumno;
                mejorAlumno = alumno;
            }
        }
        System.out.println("Mejor alumno: " + (mejorAlumno + 1) + " con media " + mejorMedia);

        System.out.println("--- Media por asignatura (por columnas) ---");
        for (int asignatura = 0; asignatura < ASIGNATURAS; asignatura++) {
            double sumaAsignatura = 0;                      // se reinicia en cada columna
            for (int alumno = 0; alumno < ALUMNOS; alumno++) {
                sumaAsignatura = sumaAsignatura + notas[alumno][asignatura];
            }
            System.out.println("Asignatura " + (asignatura + 1) + ": " + sumaAsignatura / ALUMNOS);
        }

        double notaMaxima = notas[0][0];
        int filaMaxima = 0;
        int columnaMaxima = 0;
        for (int alumno = 0; alumno < ALUMNOS; alumno++) {
            for (int asignatura = 0; asignatura < ASIGNATURAS; asignatura++) {
                if (notas[alumno][asignatura] > notaMaxima) {
                    notaMaxima = notas[alumno][asignatura];
                    filaMaxima = alumno;
                    columnaMaxima = asignatura;
                }
            }
        }
        System.out.println("Nota mas alta: " + notaMaxima + " (alumno " + (filaMaxima + 1)
                + ", asignatura " + (columnaMaxima + 1) + ")");

        scanner.close();
    }
}
