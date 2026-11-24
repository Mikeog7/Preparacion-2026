import java.util.Scanner;

/**
 * PASO 05: Verificar si es mayor de edad
 *
 * SIRVE PARA: clasificar un dato en dos categorías.
 * USA:        operador ternario (condicion ? siTrue : siFalse), operador >=,
 *             constante final. El ternario es ideal con solo DOS resultados.
 * PRUEBA:     17 -> "Es menor de edad" | 18 -> "Es mayor de edad" (caso límite).
 */
public class Paso05MayorDeEdad {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int MAYOR_DE_EDAD = 18;

        System.out.println("Cual es la edad del alumno: ");
        int edad = scanner.nextInt();

        System.out.println(edad >= MAYOR_DE_EDAD ? "Es mayor de edad" : "Es menor de edad");

        scanner.close();
    }
}