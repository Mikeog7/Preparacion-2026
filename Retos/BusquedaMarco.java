// =============================================================================
// SECCION 1: CABECERA E IMPORTS
// package: indica la carpeta lógica donde vive la clase (Retos). Si compilas
//          fuera de esa estructura de carpetas puede dar problemas.
// Locale:  permite fijar el formato de los decimales (punto en vez de coma).
// Random:  genera los números aleatorios de la simulación.
// =============================================================================
package Retos;
import java.util.Locale;
import java.util.Random;

// -----------------------------------------------------------------------------
// RESUMEN DEL PROGRAMA
// Simula día a día el viaje de Marco, que busca a su madre. Ella se aleja y él la
// persigue. Los km de cada día dependen del azar (clima, el mono Amedio,
// avistamientos). Termina cuando la distancia entre ambos llega a 0 o menos.
// -----------------------------------------------------------------------------
public class BusquedaMarco {

    // Único generador aleatorio, compartido por toda la clase (static final).
    static final Random RND = new Random();

    
    // =========================================================================
    // SECCION 2: CONSTANTES (ningún número suelto en el resto del código)
    // "static final" = constante de clase, visible desde todos los métodos.
    // =========================================================================

    // --- Punto de partida: km que separan a Marco de su madre al empezar ---
    static final double DISTANCIA_INICIAL = 350.0;   

  
    // --- Marco: rango de velocidad (km/h) y de horas de marcha por día,
    //     más las probabilidades de sus sucesos aleatorios ---
    static final double VEL_MARCO_MIN = 10, VEL_MARCO_MAX = 15;   
    static final double HORAS_MARCO_MIN = 8, HORAS_MARCO_MAX = 10;
    static final double P_LLUVIA_FUERTE_MARCO = 0.10;   // 10 % lluvia fuerte
    static final double P_LLUVIA_NORMAL_MARCO = 0.30;   // 30 % lluvia normal
    static final double P_MONO_ESCAPA = 0.15;           // 15 % el mono se escapa
    static final double P_MONO_CANSADO = 0.25;          // 25 % el mono se cansa

    
    // --- Madre: va más lenta y camina menos horas; solo le afecta el clima ---
    static final double VEL_MADRE_MIN = 6, VEL_MADRE_MAX = 9;     
    static final double HORAS_MADRE_MIN = 6, HORAS_MADRE_MAX = 9;
    static final double P_LLUVIA_FUERTE_MADRE = 0.10;
    static final double P_LLUVIA_NORMAL_MADRE = 0.30;

    
    // --- Avistamiento: si Marco está a menos de 50 km, hay un 50 % de que le
    //     den una pista y corra 25 km extra ---
    static final double DISTANCIA_AVISTAMIENTO = 50.0;
    static final double P_AVISTAMIENTO = 0.50;
    static final double KM_EXTRA = 25.0;

    // --- Códigos del clima: números que representan cada estado del tiempo ---
    static final int BUEN_CLIMA = 0, LLUVIA_NORMAL = 1, LLUVIA_FUERTE = 2;

    // =========================================================================
    // SECCION 3: MÉTODO entre(min, max)
    // Devuelve un decimal aleatorio entre min y max.
    // nextDouble() da un valor entre 0 y 1; se escala multiplicando por el
    // ancho del rango (max - min) y sumando min.
    // Se usa para sortear velocidades y horas.
    // =========================================================================
    static double entre(double min, double max) {
        return min + RND.nextDouble() * (max - min);
    }

    // =========================================================================
    // SECCION 4: MÉTODO sortearClima(pFuerte, pNormal)
    // Tira UN solo dado (r, entre 0 y 1) y lo compara por tramos:
    //   r < pFuerte             -> lluvia fuerte  (10 %)
    //   r < pFuerte + pNormal   -> lluvia normal  (30 %)
    //   en otro caso            -> buen clima     (60 %)
    // Los if son excluyentes porque cada uno termina con "return".
    // =========================================================================
    static int sortearClima(double pFuerte, double pNormal) {
        double r = RND.nextDouble();
        if (r < pFuerte) return LLUVIA_FUERTE;
        if (r < pFuerte + pNormal) return LLUVIA_NORMAL;
        return BUEN_CLIMA;
    }

    // =========================================================================
    // SECCION 5: MÉTODOS DE MENSAJE DEL CLIMA
    // Convierten el código del clima en un texto usando un switch.
    // "default" cubre el buen clima. Hay dos métodos porque los textos son
    // distintos para Marco y para su madre.
    // =========================================================================
    static String mensajeClimaMarco(int clima) {
        switch (clima) {
            case LLUVIA_FUERTE: return "Ha llovido muchisimo!";    
            case LLUVIA_NORMAL: return "Ha llovido un poco";
            default:            return "Ha hecho un buen dia";
        }
    }

    static String mensajeClimaMadre(int clima) {
        switch (clima) {
            case LLUVIA_FUERTE: return "A mama le ha llovido muchisimo";
            case LLUVIA_NORMAL: return "A mama le ha llovido un poco";
            default:            return "A mama le ha hecho un buen dia";
        }
    }

    // =========================================================================
    // SECCION 6: MÉTODO jornadaMarco() -> un día de Marco
    // Devuelve los km que recorre ese día.
    // =========================================================================
    static double jornadaMarco() {
        // Paso 1: sortear velocidad y horas dentro de sus rangos.
        double velocidad = entre(VEL_MARCO_MIN, VEL_MARCO_MAX);
        double horas = entre(HORAS_MARCO_MIN, HORAS_MARCO_MAX);

        // Paso 2: sortear el clima y ajustar la velocidad.
        //   lluvia fuerte -> velocidad x 0,25   |   lluvia normal -> x 0,75
        int clima = sortearClima(P_LLUVIA_FUERTE_MARCO, P_LLUVIA_NORMAL_MARCO);
        if (clima == LLUVIA_FUERTE) velocidad *= 0.25;
        else if (clima == LLUVIA_NORMAL) velocidad *= 0.75;

        // Paso 3: dos sucesos INDEPENDIENTES con el mono Amedio.
        //   (cada comparación usa su propia tirada de dado)
        boolean seEscapa = RND.nextDouble() < P_MONO_ESCAPA;
        boolean seCansa = RND.nextDouble() < P_MONO_CANSADO; 

        // Efecto del mono:
        //   cansado -> Marco lo lleva en brazos, velocidad x 0,90
        //   escapa  -> pierde 2 horas buscándolo; Math.max(0, ...) evita que
        //              las horas queden negativas
        if (seCansa) velocidad *= 0.90;
        if (seEscapa) horas = Math.max(0, horas - 2);

        // Paso 4: distancia = velocidad x tiempo.
        double km = velocidad * horas;

        // Paso 5: mostrar lo ocurrido. Los mensajes del mono solo salen si pasó.
        //   printf: %.2f = decimal con 2 cifras, %n = salto de línea.
        System.out.println(mensajeClimaMarco(clima));
        if (seCansa)  System.out.println("Amedio se ha cansado, lo llevo en brazos!");
        if (seEscapa) System.out.println("He perdido tiempo buscando a Amedio!");
        System.out.printf("Avance %.2f horas a %.2f Km/h recorriendo %.2f Km%n",
                horas, velocidad, km);

        // Paso 6: devolver los km para restarlos de la distancia en main.
        return km;
    }

    // =========================================================================
    // SECCION 7: MÉTODO jornadaMadre() -> un día de la madre
    // Misma estructura que la de Marco, pero más simple:
    //  - rangos propios de velocidad y horas
    //  - solo le afecta el clima (lluvia fuerte x 0,50; lluvia normal x 0,75)
    //  - no hay mono ni sucesos extra
    // =========================================================================
    static double jornadaMadre() {
        double velocidad = entre(VEL_MADRE_MIN, VEL_MADRE_MAX);
        double horas = entre(HORAS_MADRE_MIN, HORAS_MADRE_MAX);

        int clima = sortearClima(P_LLUVIA_FUERTE_MADRE, P_LLUVIA_NORMAL_MADRE);
        if (clima == LLUVIA_FUERTE) velocidad *= 0.50;
        else if (clima == LLUVIA_NORMAL) velocidad *= 0.75;

        double km = velocidad * horas;

        System.out.println(mensajeClimaMadre(clima));
        System.out.printf("Mama pudo avanzar %.2f horas a %.2f Km/h recorriendo %.2f Km%n",
                horas, velocidad, km);
        return km;
    }

    // =========================================================================
    // SECCION 8: MAIN -> LA SIMULACIÓN COMPLETA
    // =========================================================================
    public static void main(String[] args) {
        // Preparación: decimales con punto, distancia inicial y contador de días.
        Locale.setDefault(Locale.US);   

        double distancia = DISTANCIA_INICIAL;
        int dia = 0;

        System.out.println("DIARIO DEL VIAJE DE MARCO");
        System.out.println("=========================");

        // Bucle principal: se repite MIENTRAS Marco no haya alcanzado a su madre.
        // Cada vuelta del while es un día completo.
        while (distancia > 0) {
            // 1) Empieza un nuevo día.
            dia++;
            System.out.println("DIA " + dia);

            // 2) Juegan los dos: cada método imprime su jornada y devuelve km.
            double kmMarco = jornadaMarco();
            double kmMadre = jornadaMadre();

            // 3) Actualizar la distancia:
            //    Marco la REDUCE al avanzar; la madre la AUMENTA al alejarse.
            distancia = distancia - kmMarco + kmMadre;

            // 4) Avistamiento: solo si aún no la alcanzó (distancia > 0), está
            //    cerca (< 50 km) y sale el 50 % de probabilidad.
            //    El "distancia > 0" evita un avistamiento tras el reencuentro.
            if (distancia > 0 && distancia < DISTANCIA_AVISTAMIENTO
                    && RND.nextDouble() < P_AVISTAMIENTO) {
                System.out.println("A Marco le dicen que han visto a su mama, y rompe a correr!!!");
                distancia -= KM_EXTRA;
            }

            // 5) Cierre del día: dos casos excluyentes.
            if (distancia <= 0) {
                // Reencuentro: línea de 60 asteriscos con repeat() y mensaje final.
                // El while termina porque distancia ya no es > 0.
                String estrellas = "*".repeat(60);
                System.out.println(estrellas);
                System.out.println("Al final del dia " + dia + " Marco encuentra a su madre!!!");
                System.out.println(estrellas);
            } else {
                // Aún no se encuentran: se muestra la distancia que queda
                // (%d = entero, %.2f = decimal) y una línea separadora.
                System.out.printf("Al final del dia %d la distancia entre Marco y su Madre es de %.2f Km%n",
                        dia, distancia);
                System.out.println("- ".repeat(34).trim());
            }
        }
    }
}