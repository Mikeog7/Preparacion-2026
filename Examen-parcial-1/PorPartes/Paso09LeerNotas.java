import java.util.Scanner;

/**
 * PASO 09: Preguntar notas e imprimir (con la corrección)
 *
 * SIRVE PARA: repetir una petición tantas veces como asignaturas haya.
 *             (Aquí aún no se valida: eso llega en los pasos 08 y 13.)
 * USA:        for con límite variable (no un 10 fijo), double + nextDouble()
 *             para aceptar decimales como 6.5, contador con nombre expresivo
 *             ("asignatura") que empieza en 1.
 *             La corrección típica: double en vez de int, y "<=" como límite.
 * PRUEBA:     3 asignaturas, notas 6.5, 3, 8 -> repite cada nota.
 */
public class Paso09LeerNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("En cuantas asignaturas estas matriculado (1-10): ");
        int matriculas = scanner.nextInt();

        for (int asignatura = 1; asignatura <= matriculas; asignatura++) {
            System.out.println("Nota de la asignatura " + asignatura + ": ");
            double notaAsignatura = scanner.nextDouble();
            System.out.println("Nota: " + notaAsignatura);
        }

        scanner.close();
    }
}