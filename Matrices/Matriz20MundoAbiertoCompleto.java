package Matrices;
import java.util.Scanner;

/**
 * MATRIZ 20: Mundo abierto completo (integrador)
 *
 * ENUNCIADO:  Versión completa del juego de referencia (mapa de 20 x 25): movimiento
 *             con WASD, límites del mapa, terrenos que bloquean, tesoros que se
 *             recogen (tipo 9, 100 puntos), cámara de 9 x 15 centrada en el
 *             personaje, tecla M para ver el mapa completo y Q para salir.
 * SIRVE PARA: juntar TODO lo anterior en un programa con métodos pequeños.
 * QUE CORRIGE DEL ORIGINAL:
 *   - Límites: el personaje ya no sale del mapa.
 *   - Colisiones: no se pisa agua, montañas, árboles ni edificios.
 *   - Teclas y números en constantes; una tecla no válida avisa en vez de ignorarse.
 *   - Sin variables sin usar (mosaicos2, personaje2).
 *   - Salida con estaJugando = false (más claro que negar la variable).
 * CONCEPTO CLAVE:
 *   - dibujar(...) recibe la matriz y el rango de filas y columnas a pintar:
 *     sirve igual para la cámara y para el mapa completo.
 *   - Tabla de terrenos en tres arrays paralelos (símbolo, nombre, transitable).
 *   - El mapa también es el estado: los tesoros recogidos se borran de la matriz.
 * NOTA:       la cantidad de tesoros se cuenta del mapa. Si alguno quedara aislado por
 *             agua o montañas, podrías comprobarlo con el ejercicio 18 (mapa de distancias).
 * PRUEBA:     empieza en (11, 6) sobre un camino. M alterna entre cámara y mapa completo.
 */
public class Matriz20MundoAbiertoCompleto {

    static final int HIERBA = 0;
    static final int TESORO = 9;
    static final int ALTO_VISTA = 9;
    static final int ANCHO_VISTA = 15;
    static final int MITAD = 2;
    static final int PUNTOS_TESORO = 100;

    static final String TECLA_ARRIBA = "w";
    static final String TECLA_IZQUIERDA = "a";
    static final String TECLA_ABAJO = "s";
    static final String TECLA_DERECHA = "d";
    static final String TECLA_SALIR = "q";
    static final String TECLA_MAPA = "m";

    static void dibujar(int[][] mundo, String[] mosaicos, String personaje, int[] posicionPersonaje,
                        int filaInicio, int filaFin, int columnaInicio, int columnaFin) {
        for (int fila = filaInicio; fila < filaFin; fila++) {
            for (int columna = columnaInicio; columna < columnaFin; columna++) {
                if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                    System.out.print(personaje);
                } else {
                    System.out.print(mosaicos[mundo[fila][columna]]);
                }
            }
            System.out.println();
        }
        System.out.println("-".repeat((columnaFin - columnaInicio) * mosaicos[0].length()));
    }

    static int contarTesoros(int[][] mundo) {
        int cantidad = 0;
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                if (mundo[fila][columna] == TESORO) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String PERSONAJE = "_O_";
        final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        final String[] NOMBRES = { "hierba", "edificio", "camino", "agua", "arena", "piedra",
                "arbusto", "montana", "arbol", "tesoro" };
        final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };

        int[][] mundo = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 },
                { 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 },
                { 0, 7, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 9, 3, 3, 3, 3, 3, 3, 3, 3 },
                { 7, 0, 0, 0, 0, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 7, 3, 3, 3, 3 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 2, 2, 3, 3 },
                { 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 7, 7, 2, 2, 3 },
                { 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 0, 0, 2, 0, 0, 0, 8, 7, 7, 2, 3 },
                { 5, 5, 5, 5, 5, 0, 2, 0, 0, 5, 5, 5, 5, 3, 0, 0, 2, 0, 0, 0, 0, 5, 7, 2, 2 },
                { 5, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 5, 3, 0, 0, 2, 0, 0, 0, 0, 8, 7, 7, 2 },
                { 5, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 5, 3, 0, 0, 2, 0, 0, 0, 0, 0, 5, 7, 7 },
                { 5, 0, 1, 1, 1, 0, 2, 0, 1, 1, 1, 0, 5, 3, 8, 8, 2, 8, 8, 8, 8, 5, 5, 7, 7 },
                { 5, 0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0, 5, 3, 6, 6, 2, 6, 6, 6, 5, 5, 5, 7, 7 },
                { 5, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 3, 6, 6, 2, 6, 5, 5, 5, 5, 5, 7, 7 },
                { 5, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 3, 6, 6, 2, 5, 5, 5, 8, 8, 8, 5, 7 },
                { 5, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 3, 6, 5, 2, 5, 6, 6, 6, 8, 8, 5, 7 },
                { 5, 0, 1, 1, 9, 1, 1, 1, 9, 1, 1, 0, 5, 3, 5, 5, 2, 6, 6, 8, 8, 8, 8, 8, 7 },
                { 5, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 5, 3, 5, 6, 2, 6, 6, 6, 8, 1, 1, 1, 1 },
                { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 3, 4, 6, 2, 2, 2, 2, 2, 9, 0, 0, 0 },
                { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 4, 6, 6, 8, 8, 8, 8, 1, 0, 0, 0 },
                { 7, 7, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 6, 6, 8, 8, 8, 8, 1, 0, 5, 0 },
        };

        int[] posicionPersonaje = { 11, 6 };
        int puntuacion = 0;
        int pasos = 0;
        int tesorosRestantes = contarTesoros(mundo);
        boolean verMapaCompleto = false;
        boolean estaJugando = true;
        String mensaje = "";

        do {
            int filaInicio = 0;
            int filaFin = mundo.length;
            int columnaInicio = 0;
            int columnaFin = mundo[0].length;

            if (!verMapaCompleto) {
                filaInicio = posicionPersonaje[0] - ALTO_VISTA / MITAD;
                if (filaInicio < 0) {
                    filaInicio = 0;
                }
                if (filaInicio > mundo.length - ALTO_VISTA) {
                    filaInicio = mundo.length - ALTO_VISTA;
                }
                columnaInicio = posicionPersonaje[1] - ANCHO_VISTA / MITAD;
                if (columnaInicio < 0) {
                    columnaInicio = 0;
                }
                if (columnaInicio > mundo[0].length - ANCHO_VISTA) {
                    columnaInicio = mundo[0].length - ANCHO_VISTA;
                }
                filaFin = filaInicio + ALTO_VISTA;
                columnaFin = columnaInicio + ANCHO_VISTA;
            }

            dibujar(mundo, MOSAICOS, PERSONAJE, posicionPersonaje, filaInicio, filaFin, columnaInicio, columnaFin);
            System.out.println("Pasos: " + pasos + " | Puntuacion: " + puntuacion
                    + " | Tesoros restantes: " + tesorosRestantes);
            System.out.println("Estas sobre: " + NOMBRES[mundo[posicionPersonaje[0]][posicionPersonaje[1]]]);
            if (!mensaje.isEmpty()) {
                System.out.println(mensaje);
                mensaje = "";
            }
            System.out.print("WASD mover | M mapa completo | Q salir -> ");
            String tecla = scanner.nextLine();

            int nuevaFila = posicionPersonaje[0];
            int nuevaColumna = posicionPersonaje[1];
            boolean intentaMover = true;

            if (tecla.equalsIgnoreCase(TECLA_ARRIBA)) {
                nuevaFila--;
            } else if (tecla.equalsIgnoreCase(TECLA_ABAJO)) {
                nuevaFila++;
            } else if (tecla.equalsIgnoreCase(TECLA_IZQUIERDA)) {
                nuevaColumna--;
            } else if (tecla.equalsIgnoreCase(TECLA_DERECHA)) {
                nuevaColumna++;
            } else {
                intentaMover = false;
                if (tecla.equalsIgnoreCase(TECLA_SALIR)) {
                    estaJugando = false;
                } else if (tecla.equalsIgnoreCase(TECLA_MAPA)) {
                    verMapaCompleto = !verMapaCompleto;
                } else {
                    mensaje = "Tecla no valida.";
                }
            }

            if (intentaMover) {
                boolean dentroDelMapa = nuevaFila >= 0 && nuevaFila < mundo.length
                        && nuevaColumna >= 0 && nuevaColumna < mundo[0].length;
                if (!dentroDelMapa) {
                    mensaje = "No puedes salir del mapa.";
                } else if (!TRANSITABLE[mundo[nuevaFila][nuevaColumna]]) {
                    mensaje = "Te bloquea: " + NOMBRES[mundo[nuevaFila][nuevaColumna]] + ".";
                } else {
                    posicionPersonaje[0] = nuevaFila;
                    posicionPersonaje[1] = nuevaColumna;
                    pasos++;
                    if (mundo[nuevaFila][nuevaColumna] == TESORO) {
                        puntuacion = puntuacion + PUNTOS_TESORO;
                        tesorosRestantes--;
                        mundo[nuevaFila][nuevaColumna] = HIERBA;
                        mensaje = "Has encontrado un tesoro!";
                    }
                }
            }

            if (tesorosRestantes == 0 && estaJugando) {
                estaJugando = false;
            }
        } while (estaJugando);

        System.out.println("===== FIN DEL JUEGO =====");
        System.out.println("Pasos: " + pasos);
        System.out.println("Puntuacion: " + puntuacion);
        if (tesorosRestantes == 0) {
            System.out.println("Has recogido todos los tesoros. Victoria!");
        } else {
            System.out.println("Te faltaron " + tesorosRestantes + " tesoros.");
        }

        scanner.close();
    }
}
