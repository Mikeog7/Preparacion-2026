package Arrays;
import java.util.Scanner;

/**
 * ARRAY 09: Insertar y eliminar con un array "parcialmente lleno"
 *
 * ENUNCIADO:  Un array con capacidad 10 contiene 5 valores (10, 20, 30, 40, 50).
 *             Inserta un valor en la posición que elija el usuario y después
 *             elimina otra posición.
 * SIRVE PARA: manejar listas con un array de tamaño fijo.
 * CONCEPTO CLAVE:
 *   - Variable "usados": cuántas casillas tienen datos reales (capacidad != usados).
 *   - INSERTAR: desplazar a la DERECHA empezando por el FINAL, luego colocar.
 *   - ELIMINAR: desplazar a la IZQUIERDA empezando por el PRINCIPIO, luego usados--.
 *   - Comprobar que hay sitio antes de insertar.
 * ERROR TIPICO: desplazar en el sentido contrario (se copia el mismo valor en
 *             todas las posiciones).
 * PRUEBA:     insertar 25 en la posición 2 -> 10 20 25 30 40 50.
 *             eliminar la posición 0 -> 20 25 30 40 50.
 */
public class Array09InsertarEliminar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int CAPACIDAD = 10;
        final int INICIALES = 5;
        final int PASO_INICIAL = 10;

        int[] datos = new int[CAPACIDAD];
        int usados = 0;
        for (int posicion = 0; posicion < INICIALES; posicion++) {
            datos[posicion] = (posicion + 1) * PASO_INICIAL;
            usados++;
        }

        System.out.print("Inicial: ");
        for (int posicion = 0; posicion < usados; posicion++) {
            System.out.print(datos[posicion] + " ");
        }
        System.out.println();

        // ----- INSERTAR -----
        if (usados == CAPACIDAD) {
            System.out.println("El array esta lleno.");
        } else {
            int posicionInsertar;
            do {
                System.out.println("Posicion donde insertar (0-" + usados + "): ");
                posicionInsertar = scanner.nextInt();
            } while (posicionInsertar < 0 || posicionInsertar > usados);

            System.out.println("Valor a insertar: ");
            int valor = scanner.nextInt();

            for (int indice = usados; indice > posicionInsertar; indice--) {
                datos[indice] = datos[indice - 1];
            }
            datos[posicionInsertar] = valor;
            usados++;
        }

        System.out.print("Tras insertar: ");
        for (int posicion = 0; posicion < usados; posicion++) {
            System.out.print(datos[posicion] + " ");
        }
        System.out.println();

        // ----- ELIMINAR -----
        if (usados == 0) {
            System.out.println("No hay datos que eliminar.");
        } else {
            int posicionEliminar;
            do {
                System.out.println("Posicion a eliminar (0-" + (usados - 1) + "): ");
                posicionEliminar = scanner.nextInt();
            } while (posicionEliminar < 0 || posicionEliminar >= usados);

            for (int indice = posicionEliminar; indice < usados - 1; indice++) {
                datos[indice] = datos[indice + 1];
            }
            usados--;
            datos[usados] = 0;   // limpiar la casilla que queda libre
        }

        System.out.print("Tras eliminar: ");
        for (int posicion = 0; posicion < usados; posicion++) {
            System.out.print(datos[posicion] + " ");
        }
        System.out.println();

        scanner.close();
    }
}
