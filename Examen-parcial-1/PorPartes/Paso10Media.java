import java.util.Scanner;

/**
 * PASO 10: Media de las notas
 *
 * SIRVE PARA: calcular la media de las notas introducidas.
 * USA:        acumulador (sumaNotas): declarado FUERA del for, sumado DENTRO,
 *             dividido DESPUÉS (una sola vez). Tipo double para no perder
 *             decimales (con int, 17 / 3 daría 5).
 * PRUEBA:     3 asignaturas, notas 6, 3.5, 8 -> suma 17.5, media 5.833...
 */
public class Paso10Media {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("En cuantas asignaturas estas matriculado (1-10): ");
        int matriculas = scanner.nextInt();

        double sumaNotas = 0;

        for (int asignatura = 1; asignatura <= matriculas; asignatura++) {
            System.out.println("Nota de la asignatura " + asignatura + ": ");
            double notaAsignatura = scanner.nextDouble();
            sumaNotas = sumaNotas + notaAsignatura;
        }

        double media = sumaNotas / matriculas;
        System.out.println("La media es: " + media);

        scanner.close();
    }
}