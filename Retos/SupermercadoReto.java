// =============================================================================
// SECCION 1: CABECERA E IMPORTS
// package: carpeta lógica donde vive la clase.
// Random:  genera los números aleatorios (llegadas de clientes y nº de packs).
// =============================================================================
package Retos;
import java.util.Random;

// -----------------------------------------------------------------------------
// RESUMEN DEL PROGRAMA
// Simula minuto a minuto un día en una tienda con 4 cajas.
//  - Cada minuto puede llegar un cliente nuevo (60 % de probabilidad).
//  - Cada cliente trae entre 5 y 15 packs y tarda 1 minuto por pack.
//  - Si una caja está libre, el siguiente cliente de la cola pasa a ella.
//  - La tienda abre de 09:00 a 21:00, pero la simulación continúa tras el cierre
//    hasta que se atiende a todos los que ya estaban dentro.
// -----------------------------------------------------------------------------
public class SupermercadoReto {

    public static void main(String[] args) {
        Random random = new Random();

        // =====================================================================
        // SECCION 2: PARÁMETROS DE LA SIMULACIÓN
        // El tiempo se mide en MINUTOS desde las 00:00:
        //   9 * 60 = 540 (09:00)   |   21 * 60 = 1260 (21:00)
        // =====================================================================
        int apertura = 9 * 60;      
        int cierre = 21 * 60;       
        double probLlegada = 0.60;  // 60 % de que llegue alguien cada minuto
        int minPacks = 5;           // mínimo de packs por cliente
        int maxPacks = 15;          // máximo de packs por cliente

        // =====================================================================
        // SECCION 3: CONTADORES GLOBALES
        //  llegados:  clientes que han llegado en total (el nº del último cliente)
        //  siguiente: nº del próximo cliente de la cola que pasará a una caja
        //  atendidos: clientes que ya terminaron de pagar
        // Clientes en cola = llegados - (siguiente - 1)
        // =====================================================================
        int llegados = 0;           
        int siguiente = 1;          
        int atendidos = 0;          


        // =====================================================================
        // SECCION 4: ESTADO DE CADA CAJA (4 cajas = 4 pares de variables)
        //  clienteN:  nº del cliente que está siendo atendido en la caja N
        //  restanteN: minutos que le quedan; 0 significa que la caja está LIBRE
        // =====================================================================
        int cliente1 = 0, restante1 = 0;
        int cliente2 = 0, restante2 = 0;
        int cliente3 = 0, restante3 = 0;
        int cliente4 = 0, restante4 = 0;

        // Reloj de la simulación: empieza a la hora de apertura.
        int minuto = apertura;

        // =====================================================================
        // SECCION 5: BUCLE PRINCIPAL (cada vuelta = 1 minuto)
        // Sigue mientras ocurra ALGUNA de estas condiciones (||):
        //   - aún no es la hora de cierre (minuto < cierre)
        //   - hay clientes esperando en la cola (llegados >= siguiente)
        //   - alguna caja sigue ocupada (restanteN > 0)
        // Así, tras cerrar, se sigue hasta vaciar cola y cajas.
        // =====================================================================
        while (minuto < cierre || llegados >= siguiente
                || restante1 > 0 || restante2 > 0 || restante3 > 0 || restante4 > 0) {

            // Cabecera del minuto, con la hora formateada (método hora, al final).
            System.out.println("==== " + hora(minuto) + " ====");

            // -----------------------------------------------------------------
            // PASO A: ¿LLEGA UN CLIENTE NUEVO?
            // Solo si la tienda sigue abierta (minuto < cierre) Y sale el 60 %.
            // random.nextDouble() da un decimal entre 0 y 1.
            // Tras el cierre no entra nadie nuevo.
            // -----------------------------------------------------------------
            if (minuto < cierre && random.nextDouble() < probLlegada) {
                llegados++;
                System.out.println("Llega el cliente #" + llegados);
            } else {
                System.out.println("No llega nadie nuevo");
            }

            // -----------------------------------------------------------------
            // PASO B: ASIGNAR CLIENTES A CAJAS LIBRES
            // Para cada caja: si está libre (restante == 0) Y hay alguien en cola
            // (siguiente <= llegados), el siguiente cliente pasa a ella:
            //   - se guarda su número en clienteN
            //   - se sortea su nº de packs: minPacks + nextInt(maxPacks - minPacks + 1)
            //     = un entero entre 5 y 15 (nextInt(11) da de 0 a 10)
            //   - se avanza "siguiente" para pasar al próximo de la cola
            // Las cajas se revisan en orden 1, 2, 3, 4: si hay pocos clientes,
            // las primeras cajas se llenan antes.
            // (Los cuatro bloques son idénticos; solo cambia el nº de caja.)
            // -----------------------------------------------------------------
            if (restante1 == 0 && siguiente <= llegados) {
                cliente1 = siguiente;
                restante1 = minPacks + random.nextInt(maxPacks - minPacks + 1);
                siguiente++;
                System.out.println("El cliente #" + cliente1 + " pasa a la caja 1 con " + restante1 + " packs");
            }
            if (restante2 == 0 && siguiente <= llegados) {
                cliente2 = siguiente;
                restante2 = minPacks + random.nextInt(maxPacks - minPacks + 1);
                siguiente++;
                System.out.println("El cliente #" + cliente2 + " pasa a la caja 2 con " + restante2 + " packs");
            }
            if (restante3 == 0 && siguiente <= llegados) {
                cliente3 = siguiente;
                restante3 = minPacks + random.nextInt(maxPacks - minPacks + 1);
                siguiente++;
                System.out.println("El cliente #" + cliente3 + " pasa a la caja 3 con " + restante3 + " packs");
            }
            if (restante4 == 0 && siguiente <= llegados) {
                cliente4 = siguiente;
                restante4 = minPacks + random.nextInt(maxPacks - minPacks + 1);
                siguiente++;
                System.out.println("El cliente #" + cliente4 + " pasa a la caja 4 con " + restante4 + " packs");
            }

            // -----------------------------------------------------------------
            // PASO C: MOSTRAR EL ESTADO DEL MINUTO
            // enCola = clientes que han llegado menos los que ya pasaron a caja.
            // mostrarCaja (método auxiliar, al final) imprime si cada caja está
            // libre o a quién atiende y cuántos minutos le quedan.
            // -----------------------------------------------------------------
            int enCola = llegados - (siguiente - 1);
            System.out.println("Personas en cola: " + enCola);
            mostrarCaja(1, cliente1, restante1);
            mostrarCaja(2, cliente2, restante2);
            mostrarCaja(3, cliente3, restante3);
            mostrarCaja(4, cliente4, restante4);

            
            // -----------------------------------------------------------------
            // PASO D: TRANSCURRE UN MINUTO EN CADA CAJA
            // Si la caja está ocupada (restante > 0), se descuenta 1 minuto
            // (1 pack procesado). Si llega a 0, el cliente termina: se avisa y
            // se suma uno a "atendidos". En el siguiente minuto la caja libre
            // podrá recibir al próximo cliente (Paso B).
            // -----------------------------------------------------------------
            if (restante1 > 0) {
                restante1--;
                if (restante1 == 0) {
                    System.out.println("  La caja 1 termina con el cliente #" + cliente1);
                    atendidos++;
                }
            }
            if (restante2 > 0) {
                restante2--;
                if (restante2 == 0) {
                    System.out.println("  La caja 2 termina con el cliente #" + cliente2);
                    atendidos++;
                }
            }
            if (restante3 > 0) {
                restante3--;
                if (restante3 == 0) {
                    System.out.println("  La caja 3 termina con el cliente #" + cliente3);
                    atendidos++;
                }
            }
            if (restante4 > 0) {
                restante4--;
                if (restante4 == 0) {
                    System.out.println("  La caja 4 termina con el cliente #" + cliente4);
                    atendidos++;
                }
            }

            // -----------------------------------------------------------------
            // PASO E: AVANZAR EL RELOJ
            // Sin esta línea el bucle sería infinito. Se imprime una línea en
            // blanco para separar los minutos en la salida.
            // -----------------------------------------------------------------
            minuto++;
            System.out.println();
        }

        // =====================================================================
        // SECCION 6: RESUMEN FINAL (una sola vez, después del bucle)
        // Al terminar, llegados y atendidos deben coincidir: todos los que
        // entraron fueron atendidos. "minuto" ya es la hora de fin real.
        // =====================================================================
        System.out.println("===== RESUMEN =====");
        System.out.println("Clientes llegados:  " + llegados);
        System.out.println("Clientes atendidos: " + atendidos);
        System.out.println("Fin de la simulación a las " + hora(minuto));
    }

    // =========================================================================
    // SECCION 7: MÉTODO hora(minutos)
    // Convierte minutos desde las 00:00 en texto "HH:MM".
    //   h = minutos / 60  (división entera: las horas completas)
    //   m = minutos % 60  (el resto: los minutos sueltos)
    // String.format("%02d:%02d") rellena con ceros a la izquierda (9 -> 09).
    // Ejemplo: 545 -> 09:05
    // =========================================================================
    static String hora(int minutos) {
        int h = minutos / 60;
        int m = minutos % 60;
        return String.format("%02d:%02d", h, m);
    }

    
    // =========================================================================
    // SECCION 8: MÉTODO mostrarCaja(numero, cliente, restante)
    // Imprime el estado de una caja:
    //   - restante == 0 -> "LIBRE"
    //   - si no         -> a qué cliente atiende y cuántos minutos le quedan
    // Evita repetir este if/else cuatro veces en main.
    // =========================================================================
    static void mostrarCaja(int numero, int cliente, int restante) {
        if (restante == 0) {
            System.out.println("  Caja " + numero + ": LIBRE");
        } else {
            System.out.println("  Caja " + numero + ": atendiendo al cliente #" + cliente
                    + " (le quedan " + restante + " min)");
        }
    }
}