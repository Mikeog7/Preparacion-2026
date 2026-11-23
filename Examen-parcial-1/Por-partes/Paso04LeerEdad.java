import java.util.Scanner;

/**
 * PASO 04: Edad del alumno y asignación a variable
 *
 * SIRVE PARA: pedir un dato al usuario (con mensaje) y guardarlo en una variable.
 * USA:        Scanner, int, nextInt() (la edad es un entero).
 * PRUEBA:     escribe 20 -> imprime "La edad es: 20".
 */
public class Paso04LeerEdad {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cual es la edad del alumno: ");
        int edad = scanner.nextInt();

        System.out.println("La edad es: " + edad);

        scanner.close();
    }
}