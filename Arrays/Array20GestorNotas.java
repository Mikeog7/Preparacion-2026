package Arrays;
import java.util.Scanner;

/**
 * ARRAY 20: Gestor de notas (integrador)
 *
 * ENUNCIADO:  Menú para gestionar hasta 20 notas: 1) añadir, 2) mostrar,
 *             3) buscar una nota, 4) eliminar una posición, 5) ordenar,
 *             6) estadísticas, 7) salir.
 * SIRVE PARA: juntar TODO lo aprendido: array parcialmente lleno (usadas),
 *             inserción al final, eliminación con desplazamiento, búsqueda,
 *             ordenación, estadísticas y métodos que reciben el array.
 * CONCEPTO CLAVE:
 *   - notas (el array) + usadas (cuántas casillas tienen datos reales).
 *   - Todos los recorridos llegan hasta "usadas", NO hasta notas.length.
 *   - Los métodos reciben el array Y el número de usadas.
 *   - Menú: do-while con la opción como condición, if / else if (sin switch).
 * ERROR TIPICO: recorrer hasta notas.length (cuenta los ceros sin usar) o
 *             olvidar usadas-- al eliminar.
 * PRUEBA:     1(6) 1(3) 1(8) 2 -> 6, 3, 8.  5 -> 3, 6, 8.  3(6) -> posición 2.
 *             4(1) -> elimina el 3.  6 -> media 7.0, máx 8, mín 6, 2 aprobadas.
 */
public class Array20GestorNotas {

    static final int CAPACIDAD = 20;
    static final double NOTA_MINIMA = 0.0;
    static final double NOTA_MAXIMA = 10.0;
    static final double APROBADO = 5.0;
    static final int NO_ENCONTRADA = -1;
    static final int POSICION_MINIMA = 1;

    static void mostrarNotas(double[] notas, int usadas) {
        if (usadas == 0) {
            System.out.println("La lista esta vacia.");
        } else {
            for (int indice = 0; indice < usadas; indice++) {
                System.out.println((indice + 1) + ") " + notas[indice]);
            }
        }
    }

    static int buscarPrimera(double[] notas, int usadas, double buscada) {
        int posicion = NO_ENCONTRADA;
        int indice = 0;
        while (indice < usadas && posicion == NO_ENCONTRADA) {
            if (notas[indice] == buscada) {
                posicion = indice;
            }
            indice++;
        }
        return posicion;
    }

    static void ordenar(double[] notas, int usadas) {
        boolean huboIntercambio;
        int pasadas = 0;
        do {
            huboIntercambio = false;
            for (int indice = 0; indice < usadas - 1 - pasadas; indice++) {
                if (notas[indice] > notas[indice + 1]) {
                    double auxiliar = notas[indice];
                    notas[indice] = notas[indice + 1];
                    notas[indice + 1] = auxiliar;
                    huboIntercambio = true;
                }
            }
            pasadas++;
        } while (huboIntercambio);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int OPCION_ANADIR = 1;
        final int OPCION_MOSTRAR = 2;
        final int OPCION_BUSCAR = 3;
        final int OPCION_ELIMINAR = 4;
        final int OPCION_ORDENAR = 5;
        final int OPCION_ESTADISTICAS = 6;
        final int OPCION_SALIR = 7;

        double[] notas = new double[CAPACIDAD];
        int usadas = 0;
        int opcion;

        do {
            System.out.println("===== GESTOR DE NOTAS =====");
            System.out.println(OPCION_ANADIR + ") Anadir nota");
            System.out.println(OPCION_MOSTRAR + ") Mostrar notas");
            System.out.println(OPCION_BUSCAR + ") Buscar una nota");
            System.out.println(OPCION_ELIMINAR + ") Eliminar una posicion");
            System.out.println(OPCION_ORDENAR + ") Ordenar de menor a mayor");
            System.out.println(OPCION_ESTADISTICAS + ") Estadisticas");
            System.out.println(OPCION_SALIR + ") Salir");
            System.out.println("Elige una opcion: ");
            opcion = scanner.nextInt();

            if (opcion == OPCION_ANADIR) {
                if (usadas == CAPACIDAD) {
                    System.out.println("La lista esta llena.");
                } else {
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
                    notas[usadas] = nota;
                    usadas++;
                }
            } else if (opcion == OPCION_MOSTRAR) {
                mostrarNotas(notas, usadas);
            } else if (opcion == OPCION_BUSCAR) {
                System.out.println("Nota a buscar: ");
                double buscada = scanner.nextDouble();
                int posicion = buscarPrimera(notas, usadas, buscada);
                if (posicion == NO_ENCONTRADA) {
                    System.out.println("Esa nota no esta en la lista.");
                } else {
                    System.out.println("Primera aparicion en la posicion " + (posicion + 1));
                }
            } else if (opcion == OPCION_ELIMINAR) {
                if (usadas == 0) {
                    System.out.println("No hay notas que eliminar.");
                } else {
                    int posicionEliminar;
                    do {
                        System.out.println("Posicion a eliminar (1-" + usadas + "): ");
                        posicionEliminar = scanner.nextInt();
                    } while (posicionEliminar < POSICION_MINIMA || posicionEliminar > usadas);

                    for (int indice = posicionEliminar - 1; indice < usadas - 1; indice++) {
                        notas[indice] = notas[indice + 1];
                    }
                    usadas--;
                    notas[usadas] = 0;
                    System.out.println("Nota eliminada.");
                }
            } else if (opcion == OPCION_ORDENAR) {
                ordenar(notas, usadas);
                System.out.println("Notas ordenadas.");
            } else if (opcion == OPCION_ESTADISTICAS) {
                if (usadas == 0) {
                    System.out.println("Todavia no hay notas.");
                } else {
                    double suma = notas[0];
                    double notaMaxima = notas[0];
                    double notaMinima = notas[0];
                    int aprobadas = 0;
                    if (notas[0] >= APROBADO) {
                        aprobadas++;
                    }
                    for (int indice = 1; indice < usadas; indice++) {
                        suma = suma + notas[indice];
                        if (notas[indice] > notaMaxima) {
                            notaMaxima = notas[indice];
                        }
                        if (notas[indice] < notaMinima) {
                            notaMinima = notas[indice];
                        }
                        if (notas[indice] >= APROBADO) {
                            aprobadas++;
                        }
                    }
                    System.out.println("Notas: " + usadas);
                    System.out.println("Media: " + suma / usadas);
                    System.out.println("Maxima: " + notaMaxima + " | Minima: " + notaMinima);
                    System.out.println("Aprobadas: " + aprobadas + " | Suspensas: " + (usadas - aprobadas));
                }
            } else if (opcion != OPCION_SALIR) {
                System.out.println("Opcion no valida.");
            }
        } while (opcion != OPCION_SALIR);

        System.out.println("Hasta luego.");
        scanner.close();
    }
}
