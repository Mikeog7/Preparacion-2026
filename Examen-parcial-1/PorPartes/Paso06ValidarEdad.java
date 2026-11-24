import java.util.Scanner;

/**
 * PASO 06: Verificación del rango de la edad
 *
 * SIRVE PARA: obligar a que el dato esté en un rango. No avanza hasta que sea válido.
 * USA:        while, operador || (O), constantes, relectura con nextInt().
 *             "while" y no "if": el if solo comprueba una vez.
 * PRUEBA:     5, 90, 17 -> rechaza 5 y 90, acepta 17.
 *             Límites: 16 (rechaza), 17 (acepta), 70 (acepta), 71 (rechaza).
 */
public class Paso06ValidarEdad {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int EDAD_MINIMA = 17;
        final int EDAD_MAXIMA = 70;

        System.out.println("Cual es la edad del alumno: ");
        int edad = scanner.nextInt();

        while (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            System.out.println("No es valido, vuelve a intentar: ");
            edad = scanner.nextInt();
        }

        System.out.println("Edad valida: " + edad);

        scanner.close();
    }
}