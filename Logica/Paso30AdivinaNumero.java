package Logica;
import java.util.Random;
import java.util.Scanner;

/**
 * PASO 30: Simulación con azar (adivina el número)
 *
 * ENUNCIADO:  El programa elige un número secreto entre 1 y 100. El jugador tiene
 *             7 intentos: tras cada fallo se le dice si debe subir o bajar y si
 *             está "caliente" (a 5 o menos de distancia). Al final se indica si
 *             ganó y en cuántos intentos, o cuál era el número.
 * SIRVE PARA: el patrón de las simulaciones (como BusquedaMarco o RetoCCCF).
 * USA:        Random, do-while con DOS condiciones de parada (acertó O se
 *             acabaron los intentos), bandera boolean, do-while anidado para
 *             validar sin gastar intento, ternario, Math.abs.
 * EN EL EXAMEN: "aleatorio", "máximo de intentos", "hasta que...". Una
 *             condición compuesta en el while: seguir MIENTRAS (!acertado && intentos < MAX).
 *             Un intento inválido NO debe contar como intento.
 *             Para pruebas repetibles puedes usar new Random(42): la misma semilla
 *             da siempre el mismo número.
 * PRUEBA:     acertar a la primera -> "Acertaste en 1 intento(s)".
 *             Fallar 7 veces -> "Has perdido. El numero era X".
 */
public class Paso30AdivinaNumero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        final int NUMERO_MIN = 1;
        final int NUMERO_MAX = 100;
        final int INTENTOS_MAXIMOS = 7;
        final int DISTANCIA_CALIENTE = 5;

        int numeroSecreto = NUMERO_MIN + random.nextInt(NUMERO_MAX - NUMERO_MIN + 1);
        int intentos = 0;
        boolean acertado = false;

        System.out.println("He pensado un numero entre " + NUMERO_MIN + " y " + NUMERO_MAX);
        System.out.println("Tienes " + INTENTOS_MAXIMOS + " intentos.");

        do {
            intentos++;

            int numeroIntento;
            do {
                System.out.println("Intento " + intentos + ": ");
                numeroIntento = scanner.nextInt();
                if (numeroIntento < NUMERO_MIN || numeroIntento > NUMERO_MAX) {
                    System.out.println("Fuera de rango (1-100).");
                }
            } while (numeroIntento < NUMERO_MIN || numeroIntento > NUMERO_MAX);

            acertado = numeroIntento == numeroSecreto;

            if (!acertado) {
                System.out.println(numeroIntento < numeroSecreto ? "Prueba con un numero mas alto" : "Prueba con un numero mas bajo");
                int diferencia = Math.abs(numeroIntento - numeroSecreto);
                if (diferencia <= DISTANCIA_CALIENTE) {
                    System.out.println("Caliente!");
                }
                System.out.println("Intentos restantes: " + (INTENTOS_MAXIMOS - intentos));
            }
        } while (!acertado && intentos < INTENTOS_MAXIMOS);

        if (acertado) {
            System.out.println("Acertaste en " + intentos + " intento(s)");
        } else {
            System.out.println("Has perdido. El numero era " + numeroSecreto);
        }

        scanner.close();
    }
}
