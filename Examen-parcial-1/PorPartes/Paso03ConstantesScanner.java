import java.util.Scanner;

/**
 * PASO 03 (implícito): Scanner y constantes
 *
 * SIRVE PARA: preparar la lectura de teclado y guardar todos los límites del
 *             enunciado en un solo sitio (cambiar un límite = tocar una línea).
 * USA:        Scanner, final, constantes en MAYÚSCULAS_CON_GUION_BAJO,
 *             nextInt(), concatenación de textos.
 * PRUEBA:     escribe un número cualquiera: lo repite y muestra las constantes.
 */
public class Paso03ConstantesScanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int MAYOR_DE_EDAD = 18;
        final int EDAD_MINIMA = 17;
        final int EDAD_MAXIMA = 70;
        final int ASIGNATURAS_MIN = 1;
        final int ASIGNATURAS_MAX = 10;
        final int APROBADO = 5;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final double NOTA_MINIMA_PROMOCION = 4.0;

        System.out.println("Escribe un numero entero: ");
        int numero = scanner.nextInt();
        System.out.println("Has escrito: " + numero);

        System.out.println("Edad permitida: " + EDAD_MINIMA + " a " + EDAD_MAXIMA);
        System.out.println("Mayoria de edad: " + MAYOR_DE_EDAD);
        System.out.println("Asignaturas: " + ASIGNATURAS_MIN + " a " + ASIGNATURAS_MAX);
        System.out.println("Notas: " + NOTA_MINIMA + " a " + NOTA_MAXIMA);
        System.out.println("Aprobado desde: " + APROBADO);
        System.out.println("Nota minima para promocionar: " + NOTA_MINIMA_PROMOCION);

        scanner.close();
    }
}