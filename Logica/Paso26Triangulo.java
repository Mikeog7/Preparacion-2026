package Logica;
import java.util.Scanner;

/**
 * PASO 26: Figuras con bucles anidados
 *
 * ENUNCIADO:  Pide una altura (1 a 20) y dibuja con asteriscos un triángulo
 *             rectángulo y una pirámide centrada.
 * SIRVE PARA: entender cómo un bucle exterior (filas) controla a uno interior
 *             (columnas), y la diferencia entre print y println.
 * USA:        for dentro de for, print (sin salto) y println (salto al final de fila).
 * EN EL EXAMEN: "dibuja", "tabla de multiplicar", "tablero". La relación clave:
 *             el límite del bucle interior depende de la fila del exterior.
 * PRUEBA:     altura 4 ->
 *               *          y        *
 *               **                  ***
 *               ***                *****
 *               ****              *******
 */
public class Paso26Triangulo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ALTURA_MIN = 1;
        final int ALTURA_MAX = 20;

        int altura;
        do {
            System.out.println("Altura (1-20): ");
            altura = scanner.nextInt();
        } while (altura < ALTURA_MIN || altura > ALTURA_MAX);

        System.out.println("Triangulo:");
        for (int fila = 1; fila <= altura; fila++) {
            for (int estrella = 1; estrella <= fila; estrella++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("Piramide:");
        for (int fila = 1; fila <= altura; fila++) {
            for (int espacio = 1; espacio <= altura - fila; espacio++) {
                System.out.print(" ");
            }
            for (int estrella = 1; estrella <= 2 * fila - 1; estrella++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}
