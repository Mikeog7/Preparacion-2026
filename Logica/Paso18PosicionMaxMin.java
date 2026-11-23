package Logica;
import java.util.Scanner;

/**
 * PASO 18: Nota más alta y más baja CON su asignatura
 *
 * ENUNCIADO:  Muestra la nota más alta y la más baja, e indica en qué número de
 *             asignatura ocurre cada una. Si hay empate, se queda la primera.
 * SIRVE PARA: guardar no solo el valor sino DÓNDE ocurrió.
 * USA:        máximo y mínimo (pasos 14 y 15) + una variable más por cada uno,
 *             comparación ESTRICTA (> y <) para quedarse con la primera aparición.
 * EN EL EXAMEN: cuando pidan "en qué asignatura" o "cuál fue". Se actualizan las
 *             dos variables DENTRO del mismo if.
 * PRUEBA:     notas 6, 9, 3, 9 -> máxima 9 en la asignatura 2 (no la 4), mínima 3 en la 3.
 */
public class Paso18PosicionMaxMin {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ASIGNATURAS_MIN = 1;
        final int ASIGNATURAS_MAX = 10;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;

        int matriculas;
        do {
            System.out.println("En cuantas asignaturas estas matriculado (1-10): ");
            matriculas = scanner.nextInt();
        } while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX);

        double notaMaxima = NOTA_MINIMA;
        double notaMinima = NOTA_MAXIMA;
        int asignaturaMaxima = 0;
        int asignaturaMinima = 0;

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

            if (notaAsignatura > notaMaxima) {
                notaMaxima = notaAsignatura;
                asignaturaMaxima = asignatura;
            }
            if (notaAsignatura < notaMinima) {
                notaMinima = notaAsignatura;
                asignaturaMinima = asignatura;
            }
        }

        System.out.println("La nota mas alta es " + notaMaxima + " (asignatura " + asignaturaMaxima + ")");
        System.out.println("La nota mas baja es " + notaMinima + " (asignatura " + asignaturaMinima + ")");

        scanner.close();
    }
}
