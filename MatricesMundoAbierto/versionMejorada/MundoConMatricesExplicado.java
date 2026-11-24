// =============================================================================
// CODIGO ORIGINAL DEL PROFESOR, COMENTADO (el codigo no se ha modificado)
//
// QUE HACE EL PROGRAMA
//   Dibuja un mapa de 20 x 25 casillas en la consola y un personaje encima.
//   El usuario escribe W, A, S o D (y pulsa Enter) para moverlo, o Q para salir.
//   Tras cada tecla, el mapa se vuelve a dibujar.
//
// IDEA CLAVE
//   El mapa NO guarda dibujos: guarda NUMEROS (IDs de terreno) en una matriz.
//   Otro array (mosaicos) traduce cada numero a su simbolo. El personaje no esta
//   dentro de la matriz: es un dato aparte (su fila y su columna).
//
// COMO EJECUTARLO
//   Por el "package", compila desde la carpeta que CONTIENE a MatricesMundoAbierto:
//     javac -encoding UTF-8 MatricesMundoAbierto/MundoConMatrices.java
//     java MatricesMundoAbierto.MundoConMatrices
// =============================================================================
package MatricesMundoAbierto.versionMejorada;

// Scanner permite leer lo que el usuario escribe por teclado.
import java.util.Scanner;

public class MundoConMatricesExplicado {
    public static void main(String[] args) {
        // Scanner sobre System.in (el teclado). Nunca se cierra: en un programa tan
        // corto no pasa nada, pero lo correcto es scanner.close() al terminar.
        Scanner scanner = new Scanner(System.in);

        // ---------------------------------------------------------------------
        // SECCION 1: LA MATRIZ DEL MUNDO
        // int[][] = array de arrays. Se accede asi: mundo[fila][columna]
        //   - SIEMPRE primero la fila (vertical), luego la columna (horizontal).
        //   - mundo.length     = 20 (numero de filas)
        //   - mundo[0].length  = 25 (numero de columnas)
        //   - La fila 0 es la de ARRIBA: "subir" significa que la fila DISMINUYE.
        // Cada numero es un tipo de terreno. Su dibujo esta en el array mosaicos
        // (mas abajo): 0 es " . ", 1 es "[#]", 2 es " · ", 3 es "~~~"...
        // Leyendo los simbolos, lo natural es: 0 suelo/hierba, 1 muros o casas,
        // 2 camino, 3 agua, 7 montanas, 8 arboles (el codigo no lo declara: es
        // una interpretacion).
        // Todas las filas miden 25: es una matriz "rectangular". Si una fila fuera
        // mas corta, el programa seguiria funcionando porque el bucle usa
        // mundo[fila].length, pero el mapa se veria deformado.
        // ---------------------------------------------------------------------
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

        // ---------------------------------------------------------------------
        // SECCION 2: EL PERSONAJE
        // posicionPersonaje es un array de DOS posiciones: { fila, columna }.
        //   [0] = fila     [1] = columna
        // Empieza en la fila 11, columna 6 (en el mapa hay un camino, ID 2).
        // El personaje NO esta dentro de "mundo": asi la matriz conserva el
        // terreno que hay debajo y el personaje puede moverse sin borrar nada.
        //
        // "_O_" tiene 3 caracteres, igual que cada mosaico (" . ", "[#]"...).
        // Es IMPORTANTE: si midiera distinto, las columnas se desalinearian.
        //
        // personaje2 y mosaicos2 (mas abajo) NO SE USAN en ningun sitio. Parecen
        // una version compacta de 1 caracter por casilla, preparada para un
        // posible cambio de estilo, pero no esta conectada al programa.
        // ---------------------------------------------------------------------
        int[] posicionPersonaje = { 11, 6 };
        String personaje = "_O_";
        String personaje2 = "+";

        // ---------------------------------------------------------------------
        // SECCION 3: LA TABLA DE TRADUCCION (ID -> SIMBOLO)
        // El numero guardado en la matriz es la POSICION dentro de este array:
        //   mosaicos[0] = " . "    mosaicos[3] = "~~~"    mosaicos[9] = ":|:"
        // Asi la matriz guarda datos compactos (numeros) y la presentacion
        // (los dibujos) esta separada. Cambiar el aspecto del mapa es cambiar
        // este array, sin tocar la matriz.
        // Ojo: "/^\\" se escribe con DOS barras porque en Java la barra invertida
        // es un caracter especial; en pantalla se ve /^\ .
        // Si en la matriz hubiera un 10 (o mas), mosaicos[10] daria error
        // (ArrayIndexOutOfBoundsException): cada ID necesita su simbolo.
        // ---------------------------------------------------------------------
        String[] mosaicos = { " . ", "[#]", " · ", "~~~", "'''", ":::", ", '", "/^\\", "^Y^", ":|:" };
        String[] mosaicos2 = { " ", "#", "·", "~", "'", ":", ",", "^", "Y", "|" };

        // Variable de estado: mientras sea true, el juego sigue.
        boolean estaJugando = true;

        // ---------------------------------------------------------------------
        // SECCION 4: BUCLE PRINCIPAL (do-while)
        // Cada vuelta = un "fotograma": dibujar, leer tecla, actualizar posicion.
        // do-while porque el mapa debe dibujarse AL MENOS una vez antes de pedir
        // nada al usuario. La condicion se evalua al final: while (estaJugando).
        // ---------------------------------------------------------------------
        do {
            // -----------------------------------------------------------------
            // SECCION 4.1: DIBUJAR EL MUNDO (bucles anidados)
            // for exterior: recorre las FILAS (de arriba abajo).
            // for interior: recorre las COLUMNAS de esa fila (de izquierda a derecha).
            // mundo[fila].length es el numero de columnas de ESA fila.
            // -----------------------------------------------------------------
            for (int fila = 0; fila < mundo.length; fila++) {
                for (int columna = 0; columna < mundo[fila].length; columna++) {
                    // Pregunta en cada casilla: ¿es la del personaje?
                    // Hay que comparar fila Y columna (&&), si no se dibujaria
                    // el personaje en toda la fila o en toda la columna.
                    if (fila == posicionPersonaje[0] && columna == posicionPersonaje[1]) {
                        System.out.print(personaje);
                    } else {
                        // Doble indexacion: mundo[fila][columna] da el ID (un
                        // numero) y ese numero indexa el array de simbolos.
                        System.out.print(mosaicos[mundo[fila][columna]]);
                    }
                }
                // print (sin salto) dentro de la fila; println al terminarla.
                System.out.println();
            }
            // Linea separadora del ancho exacto del mapa:
            // 25 columnas x 3 caracteres por mosaico = 75 guiones.
            // "-".repeat(n) repite el texto n veces.
            System.out.println("-".repeat(mundo[0].length * mosaicos[0].length()));

            // -----------------------------------------------------------------
            // SECCION 4.2: LEER LA TECLA
            // print (no println) para que el usuario escriba en la misma linea.
            // nextLine() lee la linea ENTERA, por eso hay que pulsar Enter tras
            // cada letra. Si el usuario escribe "w " o "ww" no coincidira con "w".
            // -----------------------------------------------------------------
            System.out.print("WASD (Q para salir) -> ");
            String opcionDelUsuario = scanner.nextLine();

            // -----------------------------------------------------------------
            // SECCION 4.3: ACTUALIZAR LA POSICION
            // equalsIgnoreCase acepta mayusculas y minusculas ("W" y "w").
            // Strings siempre con equals/equalsIgnoreCase, nunca con ==.
            //   w -> arriba    : fila - 1   (la fila 0 es la de arriba)
            //   a -> izquierda : columna - 1
            //   s -> abajo     : fila + 1
            //   d -> derecha   : columna + 1
            // La cadena de else if hace que solo se ejecute UNA de las ramas.
            // -----------------------------------------------------------------
            if (opcionDelUsuario.equalsIgnoreCase("w")) {
                posicionPersonaje[0]--;
            } else if (opcionDelUsuario.equalsIgnoreCase("a")) {
                posicionPersonaje[1]--;
            } else if (opcionDelUsuario.equalsIgnoreCase("s")) {
                posicionPersonaje[0]++;
            } else if (opcionDelUsuario.equalsIgnoreCase("d")) {
                posicionPersonaje[1]++;
            } else if(opcionDelUsuario.equalsIgnoreCase("q")){
                // Invierte la variable: como valia true, pasa a false y el
                // bucle termina. Funciona, pero "estaJugando = false" diria lo
                // mismo y se entiende mejor a primera vista.
                estaJugando = !estaJugando;
            }
            // Cualquier otra tecla no entra en ninguna rama: no pasa nada y el
            // mapa se redibuja sin avisar al usuario de que la tecla no vale.
        } while (estaJugando);
    }
}

// =============================================================================
// LIMITES DEL CODIGO ORIGINAL (que ocurre en la practica)
//
// 1) NO SE COMPRUEBAN LOS LIMITES.
//    Si el personaje esta en la fila 0 y pulsas "w", su fila pasa a -1. El
//    programa NO se cae: el dibujo solo compara coordenadas, y ninguna casilla
//    coincide con (-1, columna). Resultado: el personaje DESAPARECE del mapa.
//    Si vuelves a entrar en el rango (pulsando "s"), reaparece.
//
// 2) NO HAY COLISIONES. El personaje atraviesa agua (3), montanas (7), arboles
//    (8) y edificios (1) como si fueran suelo.
//
// 3) TECLAS NO VALIDAS se ignoran sin ningun aviso.
//
// 4) VALORES "MAGICOS": "w", "a", "s", "d", "q", "_O_" y { 11, 6 } estan
//    escritos a mano en el codigo. Si hay que cambiarlos, se buscan uno a uno.
//
// 5) CODIGO MUERTO: personaje2 y mosaicos2 se declaran pero no se usan.
//
// 6) EL SCANNER NO SE CIERRA (scanner.close()).
//
// En el archivo MundoConMatricesMejorado.java se resuelven todos.
// =============================================================================