import java.util.Scanner;

/**
 * PASO 15: Nota más baja
 *
 * SIRVE PARA: encontrar el valor menor de una serie de notas.
 * USA:        variable de estado notaMinima inicializada con el valor MÁS ALTO
 *             posible (NOTA_MAXIMA): es la regla contraria al paso 14.
 *             Error típico: inicializarla en 0 (ninguna nota sería menor).
 * PRUEBA:     notas 6, 3.5, 8 -> 3.5.  Una sola asignatura con nota 10 -> 10.0.
 */
public class Paso15NotaMinima {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final double NOTA_MAXIMA = 10.0;

        System.out.println("En cuantas asignaturas estas matriculado (1-10): ");
        int matriculas = scanner.nextInt();

        double notaMinima = NOTA_MAXIMA;

        for (int asignatura = 1; asignatura <= matriculas; asignatura++) {
            System.out.println("Nota de la asignatura " + asignatura + ": ");
            double notaAsignatura = scanner.nextDouble();
            if (notaAsignatura < notaMinima) {
                notaMinima = notaAsignatura;
            }
        }

        System.out.println("La nota mas baja es: " + notaMinima);

        scanner.close();
    }
}