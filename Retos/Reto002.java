package Retos;

/**
 * Cambio en billetes y monedas con el menor número de piezas.
 *
 * Algoritmo voraz: para cada denominación, de mayor a menor,
 *   cantidad = resto / denominación   (cuántas piezas caben)
 *   resto    = resto % denominación   (lo que queda por devolver)
 *
 * Ejemplo: pagar 37 con 100 -> cambio 63 -> 1x50, 1x10, 1x2, 1x1.
 */
import java.util.Scanner;
public class Reto002 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int BILLETE_100 = 100;
        final int BILLETE_50 = 50;
        final int BILLETE_20 = 20;
        final int BILLETE_10 = 10;
        final int BILLETE_5 = 5;
        final int MONEDA_2 = 2;
        final int MONEDA_1 = 1;

        // Importe a pagar: debe ser positivo.
        int importeAPagar;
        do {
            System.out.println("¿Cuantos euros hay que pagar?");
            importeAPagar = scanner.nextInt();
            if (importeAPagar <= 0) {
                System.out.println("El importe debe ser mayor que 0.");
            }
        } while (importeAPagar <= 0);

        // Importe pagado: no puede ser menor que lo que se debe.
        int importePagado;
        do {
            System.out.println("¿Con cuantos euros paga?");
            importePagado = scanner.nextInt();
            if (importePagado < importeAPagar) {
                System.out.println("No es suficiente: faltan " + (importeAPagar - importePagado) + " €.");
            }
        } while (importePagado < importeAPagar);

        // Cambio = lo que entrega el cliente MENOS lo que debe.
        int cambio = importePagado - importeAPagar;

        if (cambio == 0) {
            System.out.println("Importe exacto: no hay cambio.");
        } else {
            System.out.println("Cambio a devolver: " + cambio + " €");

            int resto = cambio;
            resto = mostrarPiezas(resto, BILLETE_100, "billete");
            resto = mostrarPiezas(resto, BILLETE_50, "billete");
            resto = mostrarPiezas(resto, BILLETE_20, "billete");
            resto = mostrarPiezas(resto, BILLETE_10, "billete");
            resto = mostrarPiezas(resto, BILLETE_5, "billete");
            resto = mostrarPiezas(resto, MONEDA_2, "moneda");
            resto = mostrarPiezas(resto, MONEDA_1, "moneda");
        }

        scanner.close();
    }

    /**
     * Calcula cuántas piezas de una denominación caben en el resto, las muestra
     * (solo si hay alguna) y devuelve lo que queda por repartir.
     */
    static int mostrarPiezas(int resto, int denominacion, String tipo) {
        int cantidad = resto / denominacion;
        if (cantidad > 0) {
            System.out.println(cantidad + " " + tipo + "(s) de " + denominacion + " €");
        }
        return resto % denominacion;
    }
}