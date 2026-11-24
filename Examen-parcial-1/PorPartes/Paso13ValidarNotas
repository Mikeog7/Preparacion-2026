import java.util.Scanner;

/**
 * PASO 12: Mostrar asignaturas suspensas
 *
 * SIRVE PARA: obtener las suspensas a partir de datos que ya se tienen.
 * USA:        una resta: suspensas = matriculas - aprobadas. Si un dato se puede
 *             CALCULAR, no se cuenta aparte (menos código, menos errores).
 *             Aquí se piden los dos datos directamente para probar solo este paso.
 * PRUEBA:     matriculas 5, aprobadas 3 -> suspensas 2.
 */
public class Paso12Suspensas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Total de asignaturas: ");
        int matriculas = scanner.nextInt();
        System.out.println("Asignaturas aprobadas: ");
        int aprobadas = scanner.nextInt();

        int suspensas = matriculas - aprobadas;
        System.out.println("Asignaturas suspensas: " + suspensas);

        scanner.close();
    }
}