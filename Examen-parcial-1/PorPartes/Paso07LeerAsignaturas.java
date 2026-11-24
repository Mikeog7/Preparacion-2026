import java.util.Scanner;

/**
 * PASO 07: Pregunta de asignaturas
 *
 * SIRVE PARA: obtener cuántas veces se repetirá después el bucle de notas.
 * USA:        Scanner, int, nextInt().
 * PRUEBA:     escribe 4 -> imprime "Matriculado en 4 asignaturas".
 */
public class Paso07LeerAsignaturas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("En cuantas asignaturas estas matriculado: ");
        int matriculas = scanner.nextInt();

        System.out.println("Matriculado en " + matriculas + " asignaturas");

        scanner.close();
    }
}