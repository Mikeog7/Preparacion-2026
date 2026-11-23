package Logica;
import java.util.Scanner;

/**
 * PASO 20: Racha más larga de asignaturas aprobadas seguidas
 *
 * ENUNCIADO:  Muestra cuántas asignaturas consecutivas aprobó el alumno como máximo.
 * SIRVE PARA: medir secuencias seguidas (rachas), un clásico de examen.
 * USA:        dos contadores: rachaActual (se REINICIA a 0 al fallar) y
 *             rachaMaxima (guarda el récord). Es un "máximo" aplicado a un contador.
 * EN EL EXAMEN: cuando digan "seguidas", "consecutivas", "la racha más larga".
 *             Error típico: olvidar reiniciar rachaActual en el else.
 * PRUEBA:     notas 6, 7, 3, 8, 9, 9, 2 -> racha máxima 3 (8, 9, 9).
 *             Todas suspensas -> 0.  Todas aprobadas (4 notas) -> 4.
 */
public class Paso20RachaAprobadas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ASIGNATURAS_MIN = 1;
        final int ASIGNATURAS_MAX = 10;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final double APROBADO = 5.0;

        int matriculas;
        do {
            System.out.println("En cuantas asignaturas estas matriculado (1-10): ");
            matriculas = scanner.nextInt();
        } while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX);

        int rachaActual = 0;
        int rachaMaxima = 0;

        for (int asignatura = 1; asignatura <= matriculas; asignatura++) {
            double notaAsignatura;
            boolean notaValida;
            do {
                System.out.println("Nota de la asignatura " + asignatura + ": ");
                notaAsignatura = scanner.nextDouble();
                notaValida = notaAsignatura >= NOTA_MINIMA && notaAsignatura <= NOTA_MAXIMA;
                if (!notaValida) {
                    System.out.println("No es valido, vuelve a intentar.");
                }
            } while (!notaValida);

            if (notaAsignatura >= APROBADO) {
                rachaActual++;
                if (rachaActual > rachaMaxima) {
                    rachaMaxima = rachaActual;
                }
            } else {
                rachaActual = 0;
            }
        }

        System.out.println("Racha mas larga de aprobadas seguidas: " + rachaMaxima);

        scanner.close();
    }
}
