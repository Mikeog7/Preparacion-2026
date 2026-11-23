package Logica;
import java.util.Scanner;

/**
 * PASO 22: Media ponderada por créditos
 *
 * ENUNCIADO:  Para cada asignatura pide la nota y sus créditos (1 a 12). Calcula
 *             la media simple y la media ponderada (nota x créditos / total créditos).
 * SIRVE PARA: acumular DOS sumas distintas en el mismo bucle y dividir una por otra.
 * USA:        dos acumuladores (sumaPonderada, sumaCreditos), dos validaciones
 *             por vuelta, operaciones con double.
 * EN EL EXAMEN: "ponderada", "según el peso", "cada asignatura vale distinto".
 *             Error típico: dividir la ponderada entre el nº de asignaturas.
 * PRUEBA:     (nota 8, 6 créditos) y (nota 4, 3 créditos) ->
 *             media simple 6.0 y ponderada 6.666...
 */
public class Paso22MediaPonderada {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ASIGNATURAS_MIN = 1;
        final int ASIGNATURAS_MAX = 10;
        final int CREDITOS_MIN = 1;
        final int CREDITOS_MAX = 12;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;

        int matriculas;
        do {
            System.out.println("En cuantas asignaturas estas matriculado (1-10): ");
            matriculas = scanner.nextInt();
        } while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX);

        double sumaNotas = 0;
        double sumaPonderada = 0;
        int sumaCreditos = 0;

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

            int creditos;
            do {
                System.out.println("Creditos de la asignatura " + asignatura + " (1-12): ");
                creditos = scanner.nextInt();
            } while (creditos < CREDITOS_MIN || creditos > CREDITOS_MAX);

            sumaNotas = sumaNotas + notaAsignatura;
            sumaPonderada = sumaPonderada + notaAsignatura * creditos;
            sumaCreditos = sumaCreditos + creditos;
        }

        double mediaSimple = sumaNotas / matriculas;
        double mediaPonderada = sumaPonderada / sumaCreditos;

        System.out.println("Media simple: " + mediaSimple);
        System.out.println("Media ponderada: " + mediaPonderada);

        scanner.close();
    }
}
