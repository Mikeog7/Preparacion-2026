// =============================================================================
// VERSION MEJORADA DEL MUNDO CON MATRICES (archivo aparte del original)
//
// Mismo juego, mismas teclas y mismo mapa, pero corrigiendo los limites del
// codigo del profesor. Cada cambio esta marcado como [MEJORA n] con:
//   - QUE cambia
//   - POR QUE es mejor
//   - CUANDO sirve (en que situaciones merece la pena)
//
// COMO EJECUTARLO (compila desde la carpeta que CONTIENE a MatricesMundoAbierto):
//     javac -encoding UTF-8 MatricesMundoAbierto/versionMejorada/MundoConMatricesMejorado.java
//     java MatricesMundoAbierto.versionMejorada.MundoConMatricesMejorado
// =============================================================================
package MatricesMundoAbierto.versionMejorada;

import java.util.Scanner;

public class MundoConMatricesMejorado {

    // -------------------------------------------------------------------------
    // [MEJORA 1] CONSTANTES EN LUGAR DE VALORES SUELTOS
    // QUE:    simbolos, teclas y posicion inicial pasan a constantes (static final,
    //         en MAYUSCULAS_CON_GUION_BAJO) al principio de la clase.
    // POR QUE: cambiar una tecla o el punto de partida es tocar UNA linea, y el
    //         nombre explica el significado ("TECLA_ARRIBA" dice mas que "w").
    // CUANDO: siempre que un valor se use en mas de un sitio o pueda cambiar.
    //
    // Nota: en un array "final" no se puede reasignar la VARIABLE, pero SI se
    // podrian cambiar sus posiciones. Aqui son tablas de consulta que no se tocan.
    // -------------------------------------------------------------------------
    static final String PERSONAJE = "_O_";
    static final String[] MOSAICOS = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };

    // -------------------------------------------------------------------------
    // [MEJORA 2] REGLAS DEL TERRENO EN DATOS (arrays paralelos)
    // QUE:    NOMBRES y TRANSITABLE comparten indice con MOSAICOS: la posicion i
    //         de los tres arrays habla del terreno con ID i.
    //           TRANSITABLE[3] = false  -> el agua (ID 3) bloquea el paso.
    // POR QUE: las reglas del mundo viven en una tabla, no en una cadena de if.
    //         Añadir un terreno nuevo = añadir una posicion a cada array.
    // CUANDO: juegos, mapas, tableros: cualquier caso donde cada tipo de casilla
    //         tenga propiedades (coste, daño, si se puede cruzar...).
    //
    // Los nombres y el "transitable" son una INTERPRETACION a partir de los
    // simbolos (el codigo original no los define): ajusta la tabla si tu
    // profesor usa otro significado.
    // -------------------------------------------------------------------------
    static final String[] NOMBRES = { "hierba", "edificio", "camino", "agua", "arena",
            "piedra", "arbusto", "montana", "arbol", "tesoro" };
    static final boolean[] TRANSITABLE = { true, false, true, false, true, true, true, false, false, true };

    static final String TECLA_ARRIBA = "w";
    static final String TECLA_IZQUIERDA = "a";
    static final String TECLA_ABAJO = "s";
    static final String TECLA_DERECHA = "d";
    static final String TECLA_SALIR = "q";

    static final int FILA_INICIAL = 11;
    static final int COLUMNA_INICIAL = 6;

    // -------------------------------------------------------------------------
    // [MEJORA 3] METODOS PEQUEÑOS CON UNA SOLA TAREA
    // QUE:    la comprobacion de limites y el dibujo salen de main a metodos.
    // POR QUE: main se lee como una historia corta (dibujar, leer, mover) y cada
    //         metodo se puede probar y reutilizar por separado.
    // CUANDO: en cuanto un bloque tiene un nombre claro ("dibujar el mundo") o se
    //         repetiria en otro sitio.
    // -------------------------------------------------------------------------

    // Devuelve true si (fila, columna) existe dentro de la matriz.
    static boolean estaDentroDelMundo(int[][] mundo, int fila, int columna) {
        return fila >= 0 && fila < mundo.length
                && columna >= 0 && columna < mundo[0].length;
    }

    // Dibuja el mundo con el personaje encima. La matriz se recibe por
    // referencia: el metodo la lee pero no la modifica.
    static void dibujarMundo(int[][] mundo, int[] posicionPersonaje) {
        for (int fila = 0; fila < mundo.length; fila++) {
            for (int columna = 0; columna < mundo[fila].length; columna++) {
                if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                    System.out.print(PERSONAJE);
                } else {
                    System.out.print(MOSAICOS[mundo[fila][columna]]);
                }
            }
            System.out.println();
        }
        System.out.println("-".repeat(mundo[0].length * MOSAICOS[0].length()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // El mapa es identico al del profesor.
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

        int[] posicionPersonaje = { FILA_INICIAL, COLUMNA_INICIAL };

        // ---------------------------------------------------------------------
        // [MEJORA 4] SIN CODIGO MUERTO
        // QUE:    se eliminan personaje2 y mosaicos2, que no se usaban.
        // POR QUE: lo que no se usa confunde a quien lee y hace pensar que hay
        //         un modo compacto que en realidad no existe.
        // CUANDO: siempre; se borra lo que sobra (el control de versiones lo guarda).
        //
        // Se añade un contador de pasos, para tener un resultado final.
        // ---------------------------------------------------------------------
        int pasos = 0;
        boolean estaJugando = true;

        // ---------------------------------------------------------------------
        // [MEJORA 5] MENSAJE DE FEEDBACK
        // QUE:    una variable de texto guarda el aviso del turno anterior
        //         ("No puedes salir del mundo", "Te bloquea: agua"...).
        // POR QUE: el jugador sabe POR QUE no se ha movido. En el original, una
        //         tecla ignorada es indistinguible de un fallo del programa.
        // CUANDO: en cualquier programa interactivo: todo rechazo debe explicarse.
        // ---------------------------------------------------------------------
        String mensaje = "";

        do {
            dibujarMundo(mundo, posicionPersonaje);

            // Se aprovecha el array NOMBRES para decir sobre que terreno esta.
            int terrenoActual = mundo[posicionPersonaje[0]][posicionPersonaje[1]];
            System.out.println("Pasos: " + pasos + " | Estas sobre: " + NOMBRES[terrenoActual]);
            if (!mensaje.isEmpty()) {
                System.out.println(mensaje);
                mensaje = "";
            }

            System.out.print("WASD (Q para salir) -> ");
            String opcionDelUsuario = scanner.nextLine();

            // -----------------------------------------------------------------
            // [MEJORA 6] CALCULAR LA POSICION NUEVA ANTES DE APLICARLA
            // QUE:    el movimiento se guarda primero en nuevaFila/nuevaColumna;
            //         la posicion REAL solo cambia si el movimiento es valido.
            // POR QUE: en el original se modificaba la posicion directamente y ya
            //         no habia vuelta atras (el personaje salia del mapa).
            // CUANDO: siempre que una accion pueda ser rechazada: se calcula el
            //         resultado en variables temporales, se valida y entonces se aplica.
            //
            // quiereMover distingue "pulso una direccion" de "pulso otra cosa";
            // asi una tecla no valida no cuenta como paso.
            // -----------------------------------------------------------------
            int nuevaFila = posicionPersonaje[0];
            int nuevaColumna = posicionPersonaje[1];
            boolean quiereMover = true;

            if (opcionDelUsuario.equalsIgnoreCase(TECLA_ARRIBA)) {
                nuevaFila--;
            } else if (opcionDelUsuario.equalsIgnoreCase(TECLA_IZQUIERDA)) {
                nuevaColumna--;
            } else if (opcionDelUsuario.equalsIgnoreCase(TECLA_ABAJO)) {
                nuevaFila++;
            } else if (opcionDelUsuario.equalsIgnoreCase(TECLA_DERECHA)) {
                nuevaColumna++;
            } else {
                quiereMover = false;
                if (opcionDelUsuario.equalsIgnoreCase(TECLA_SALIR)) {
                    // [MEJORA 7] "estaJugando = false" en vez de "= !estaJugando":
                    // dice lo que hace. Invertir una variable solo es correcto si
                    // se sabe que valia true; asignar el valor es siempre correcto.
                    estaJugando = false;
                } else {
                    mensaje = "Tecla no valida. Usa W, A, S, D o Q.";
                }
            }

            if (quiereMover) {
                // -------------------------------------------------------------
                // [MEJORA 8] COMPROBAR LIMITES Y TERRENO, EN ESTE ORDEN
                // QUE:    primero se comprueba que la casilla EXISTA y despues
                //         que sea TRANSITABLE.
                // POR QUE: el orden importa: si se mirara el terreno de una
                //         casilla que no existe, el programa se caeria con
                //         ArrayIndexOutOfBoundsException. El else if encadenado
                //         garantiza que la segunda comprobacion solo se hace si
                //         la primera paso.
                // CUANDO: en todo recorrido de matriz con movimiento: juegos de
                //         tablero, laberintos, simulaciones, vecinos de una celda.
                // -------------------------------------------------------------
                if (!estaDentroDelMundo(mundo, nuevaFila, nuevaColumna)) {
                    mensaje = "No puedes salir del mundo.";
                } else if (!TRANSITABLE[mundo[nuevaFila][nuevaColumna]]) {
                    mensaje = "Te bloquea: " + NOMBRES[mundo[nuevaFila][nuevaColumna]] + ".";
                } else {
                    posicionPersonaje[0] = nuevaFila;
                    posicionPersonaje[1] = nuevaColumna;
                    pasos++;
                }
            }
        } while (estaJugando);

        // [MEJORA 9] RESUMEN FINAL Y CIERRE DEL SCANNER
        // QUE:    se muestra el resultado y se libera el recurso con close().
        // POR QUE: buena practica: lo que se abre se cierra, y el jugador ve
        //         como ha acabado la partida.
        System.out.println("Has dado " + pasos + " pasos. Hasta luego.");
        scanner.close();
    }
}

// =============================================================================
// SINTESIS: ORIGINAL FRENTE A MEJORADO
//
// | Aspecto            | Original                          | Mejorado                              |
// |--------------------|-----------------------------------|---------------------------------------|
// | Limites del mapa   | No se comprueban: el personaje    | Se valida antes de mover y se avisa   |
// |                    | sale y desaparece                 |                                       |
// | Terrenos           | Todo se puede pisar               | Tabla TRANSITABLE (agua, montañas...) |
// | Teclas y valores   | "w", "q", "_O_", { 11, 6 } sueltos| Constantes con nombre                 |
// | Tecla no valida    | Se ignora en silencio             | Mensaje al jugador                    |
// | Estructura         | Todo en main                      | Metodos dibujarMundo y estaDentro...  |
// | Codigo sobrante    | personaje2 y mosaicos2 sin usar   | Eliminado                             |
// | Salir              | estaJugando = !estaJugando        | estaJugando = false                   |
// | Scanner            | No se cierra                      | scanner.close()                       |
//
// LO QUE SE CONSERVA (porque esta bien en el original)
//   - Matriz de IDs + array de simbolos: datos separados de la presentacion.
//   - Personaje como int[] { fila, columna }, aparte de la matriz.
//   - do-while con variable de estado.
//   - equalsIgnoreCase para aceptar mayusculas y minusculas.
//
// CUANDO USAR ESTE PATRON
//   Juegos de casillas, laberintos, tableros, simulaciones con mapa, planos de
//   un edificio, cuadriculas de pixeles... En general: datos en una matriz de IDs,
//   reglas en arrays indexados por ID, y validacion ANTES de modificar el estado.
//
// MEJORAS FUTURAS (no incluidas para no complicar)
//   - Camara que muestre solo una ventana del mapa (ejercicio Matriz13).
//   - Recoger tesoros y modificar la matriz (ejercicio Matriz14).
//   - Niebla de guerra (ejercicio Matriz12) o enemigos (ejercicio Matriz19).
// =============================================================================