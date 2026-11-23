package Logica;
import java.util.Scanner;

/**
 * PASO 24: Menú que conserva datos entre opciones
 *
 * ENUNCIADO:  Muestra un menú: 1) añadir una nota, 2) ver estadísticas (cantidad,
 *             media, máxima y mínima), 3) salir. Se repite hasta elegir salir.
 * SIRVE PARA: programas interactivos que recuerdan lo ya introducido.
 * USA:        do-while con la opción como condición, constantes para las opciones,
 *             if / else if (sin switch, que necesita "break"), acumuladores y
 *             máximo/mínimo FUERA del bucle para que sobrevivan entre vueltas.
 * EN EL EXAMEN: "menú", "mientras no elija salir", "opción no válida".
 *             Hay que controlar el caso de ver estadísticas SIN notas (0 / 0 = NaN).
 * PRUEBA:     2 (sin notas) -> aviso;  1 con 6;  1 con 8;  2 -> 2 notas, media 7.0,
 *             máxima 8.0, mínima 6.0;  9 -> opción no válida;  3 -> sale.
 */
public class Paso24MenuConEstado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int OPCION_ANADIR = 1;
        final int OPCION_ESTADISTICAS = 2;
        final int OPCION_SALIR = 3;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;

        int cantidadNotas = 0;
        double sumaNotas = 0;
        double notaMaxima = NOTA_MINIMA;
        double notaMinima = NOTA_MAXIMA;
        int opcion;

        do {
            System.out.println("===== MENU =====");
            System.out.println(OPCION_ANADIR + ") Anadir una nota");
            System.out.println(OPCION_ESTADISTICAS + ") Ver estadisticas");
            System.out.println(OPCION_SALIR + ") Salir");
            System.out.println("Elige una opcion: ");
            opcion = scanner.nextInt();

            if (opcion == OPCION_ANADIR) {
                double nota;
                boolean notaValida;
                do {
                    System.out.println("Nota: ");
                    nota = scanner.nextDouble();
                    notaValida = nota >= NOTA_MINIMA && nota <= NOTA_MAXIMA;
                    if (!notaValida) {
                        System.out.println("No es valido, vuelve a intentar.");
                    }
                } while (!notaValida);

                cantidadNotas++;
                sumaNotas = sumaNotas + nota;
                if (nota > notaMaxima) {
                    notaMaxima = nota;
                }
                if (nota < notaMinima) {
                    notaMinima = nota;
                }
            } else if (opcion == OPCION_ESTADISTICAS) {
                if (cantidadNotas == 0) {
                    System.out.println("Todavia no hay notas.");
                } else {
                    System.out.println("Notas: " + cantidadNotas);
                    System.out.println("Media: " + sumaNotas / cantidadNotas);
                    System.out.println("Maxima: " + notaMaxima);
                    System.out.println("Minima: " + notaMinima);
                }
            } else if (opcion != OPCION_SALIR) {
                System.out.println("Opcion no valida.");
            }
        } while (opcion != OPCION_SALIR);

        System.out.println("Hasta luego.");
        scanner.close();
    }
}
