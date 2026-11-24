import java.util.Scanner;

/**
 * PASO 08: Verificación del rango de asignaturas
 *
 * SIRVE PARA: garantizar que el número de asignaturas esté entre 1 y 10.
 *             Evita matriculas = 0, que daría NaN al calcular la media (0 / 0).
 * USA:        while, operador ||, constantes.
 *             El original usaba "if" (reintenta una sola vez); "while" repite
 *             hasta que sea válido.
 * PRUEBA:     0, 11, 3 -> rechaza 0 y 11, acepta 3.
 *             Límites: 0 (rechaza), 1 (acepta), 10 (acepta), 11 (rechaza).
 */
public class Paso08ValidarAsignaturas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ASIGNATURAS_MIN = 1;
        final int ASIGNATURAS_MAX = 10;

        System.out.println("En cuantas asignaturas estas matriculado: ");
        int matriculas = scanner.nextInt();

        while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX) {
            System.out.println("No esta dentro del rango (1-10). Vuelve a intentar: ");
            matriculas = scanner.nextInt();
        }

        System.out.println("Asignaturas validas: " + matriculas);

        scanner.close();
    }
}