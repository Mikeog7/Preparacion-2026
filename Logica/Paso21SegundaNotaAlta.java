package Logica;
import java.util.Scanner;

/**
 * PASO 21: Segunda nota más alta
 *
 * ENUNCIADO:  Muestra la nota más alta y la segunda más alta (si se repite el
 *             máximo, la segunda puede valer lo mismo). Mínimo 2 asignaturas.
 * SIRVE PARA: llevar un "podio" de dos puestos sin usar arrays.
 * USA:        dos variables de estado (mayor y segundo) con un valor "sin dato"
 *             por debajo del rango; el orden de los if importa.
 * EN EL EXAMEN: "las dos mejores", "el segundo mayor", "podio". Cuando entra un
 *             nuevo mayor, el mayor ANTERIOR pasa a ser el segundo.
 * PRUEBA:     7, 9, 5 -> 9 y 7.   9, 9, 3 -> 9 y 9.   3, 6, 8 -> 8 y 6.
 */
public class Paso21SegundaNotaAlta {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ASIGNATURAS_MIN = 2;
        final int ASIGNATURAS_MAX = 10;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final double SIN_NOTA = -1.0;

        int matriculas;
        do {
            System.out.println("En cuantas asignaturas estas matriculado (2-10): ");
            matriculas = scanner.nextInt();
        } while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX);

        double notaMaxima = SIN_NOTA;
        double segundaNota = SIN_NOTA;

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
                segundaNota = notaMaxima;       // el antiguo mayor baja al segundo puesto
                notaMaxima = notaAsignatura;
            } else if (notaAsignatura > segundaNota) {
                segundaNota = notaAsignatura;
            }
        }

        System.out.println("Nota mas alta: " + notaMaxima);
        System.out.println("Segunda nota mas alta: " + segundaNota);

        scanner.close();
    }
}
