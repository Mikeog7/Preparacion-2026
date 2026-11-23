package Logica;
import java.util.Scanner;

/**
 * PASO 19: ¿Hay alguna suspensa? ¿Cuál fue la primera?
 *
 * ENUNCIADO:  Indica si el alumno tiene alguna asignatura suspensa y, si la tiene,
 *             el número de la primera.
 * SIRVE PARA: detectar "si ocurrió alguna vez" un suceso, y recordar cuándo.
 * USA:        variable de estado boolean (haySuspensa) que empieza en false y solo
 *             pasa a true; se sigue leyendo todo SIN "break" (no permitido).
 * EN EL EXAMEN: cuando digan "alguna", "ninguna", "la primera vez que...",
 *             "si hubo algún...". Se pone la bandera a false ANTES del bucle.
 * PRUEBA:     notas 6, 4, 3, 8 -> hay suspensa, la primera es la asignatura 2.
 *             notas 5, 7 -> ninguna suspensa.
 */
public class Paso19PrimeraSuspensa {

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

        boolean haySuspensa = false;
        int primeraSuspensa = 0;

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

            // Solo la PRIMERA suspensa activa la bandera y guarda la posición.
            if (notaAsignatura < APROBADO && !haySuspensa) {
                haySuspensa = true;
                primeraSuspensa = asignatura;
            }
        }

        if (haySuspensa) {
            System.out.println("Hay suspensas. La primera es la asignatura " + primeraSuspensa);
        } else {
            System.out.println("No tiene ninguna asignatura suspensa");
        }

        scanner.close();
    }
}
