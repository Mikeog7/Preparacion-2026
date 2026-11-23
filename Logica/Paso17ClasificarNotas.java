package Logica;
import java.util.Scanner;

/**
 * PASO 17: Clasificar las notas por tramos
 *
 * ENUNCIADO:  Pide las notas de N asignaturas y cuenta cuántas son suspenso (<5),
 *             aprobado (5 a 6.99), notable (7 a 8.99) y sobresaliente (9 o más).
 * SIRVE PARA: repartir cada dato en UNA sola categoría.
 * USA:        if / else if encadenados, varios contadores, validación de nota (paso 13).
 * EN EL EXAMEN: cuando pidan "cuántas de cada tipo" o "cuántos en cada tramo".
 *             Si usas varios "if" sueltos en vez de "else if", una nota puede
 *             contarse en dos tramos.
 * PRUEBA:     notas 4.9, 5, 7, 9, 10 -> 1 suspenso, 1 aprobado, 1 notable, 2 sobresalientes.
 */
public class Paso17ClasificarNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ASIGNATURAS_MIN = 1;
        final int ASIGNATURAS_MAX = 10;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final double APROBADO = 5.0;
        final double NOTABLE = 7.0;
        final double SOBRESALIENTE = 9.0;

        int matriculas;
        do {
            System.out.println("En cuantas asignaturas estas matriculado (1-10): ");
            matriculas = scanner.nextInt();
        } while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX);

        int suspensos = 0;
        int aprobados = 0;
        int notables = 0;
        int sobresalientes = 0;

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

            if (notaAsignatura < APROBADO) {
                suspensos++;
            } else if (notaAsignatura < NOTABLE) {
                aprobados++;
            } else if (notaAsignatura < SOBRESALIENTE) {
                notables++;
            } else {
                sobresalientes++;
            }
        }

        System.out.println("Suspensos: " + suspensos);
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Notables: " + notables);
        System.out.println("Sobresalientes: " + sobresalientes);

        scanner.close();
    }
}
