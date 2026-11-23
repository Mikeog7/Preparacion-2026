// =============================================================================
// PASO 01 (commit "paso-01: Creacion de archivo README")
// No es código Java: es el README.md del repositorio (título y enunciado).
// =============================================================================

// =============================================================================
// PASO 02 (commit "paso-02: Creacion de archivo Examen.java con output")
// Se crea el archivo Examen.java. La clase se llama Examen (PascalCase, con
// mayúscula: una clase en minúscula cuenta como "no compila").
// Incluye el import del Scanner y el primer mensaje por pantalla.
// =============================================================================
import java.util.Scanner;

public class VersionDefinitivaParcial {

    public static void main(String[] args) {

        // =====================================================================
        // PASO 03 (implícito, no aparece en la lista de commits)
        // Scanner (lee el teclado) y constantes. Ningún número suelto en el
        // resto del programa: todos los límites del enunciado viven aquí.
        // Constantes: final + MAYÚSCULAS_CON_GUION_BAJO.
        // =====================================================================
        Scanner scanner = new Scanner(System.in);
        final int MAYOR_DE_EDAD = 18;
        final int EDAD_MINIMA = 17;
        final int EDAD_MAXIMA = 70;
        final int ASIGNATURAS_MAX = 10;
        final int ASIGNATURAS_MIN = 1;
        final int APROBADO = 5;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final double NOTA_MINIMA_PROMOCION = 4.0;

        // Salida del PASO 02: título del examen.
        System.out.println("Examen parcial PRG1");

        // =====================================================================
        // PASO 04 (commit "paso-04: Edad del alumno y asignacion de variable")
        // Se pide la edad con un mensaje y se guarda en una variable int.
        // nextInt() porque la edad es un número entero.
        // =====================================================================
        System.out.println("Cual es la edad del alumno: ");
        int edad = scanner.nextInt();

        // =====================================================================
        // PASO 06 (commit "paso-06: Verificacion del rango")
        // Valida que la edad esté entre EDAD_MINIMA y EDAD_MAXIMA.
        // "while": mientras la edad sea inválida, avisa y la vuelve a pedir.
        // No avanza hasta tener un dato correcto.
        // (En el historial este paso se hizo DESPUÉS del 05, pero en el código
        // final va ANTES: primero se valida, luego se clasifica.)
        // =====================================================================
        while (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            System.out.println("No es valido, vuelve a intentar: ");
            edad = scanner.nextInt();
        }

        // =====================================================================
        // PASO 05 (commit "paso-05: Verificar si es mayor de edad")
        // Operador ternario: condicion ? valorSiTrue : valorSiFalse.
        // Adecuado porque solo hay dos resultados posibles.
        // =====================================================================
        System.out.println(edad >= MAYOR_DE_EDAD ? "Es mayor de edad" : "Es menor de edad");

        // =====================================================================
        // PASO 07 (commit "paso-07: Pregunta de asignaturas")
        // Se pide el número de asignaturas en las que está matriculado.
        // =====================================================================
        System.out.println("En cuantas asignaturas estas matriculado: ");
        int matriculas = scanner.nextInt();

        // =====================================================================
        // PASO 08 (commit "paso-08: Creacion verificacion del rango")
        // Valida que las asignaturas estén entre 1 y 10.
        // "while" (no "if"): si el segundo intento también es inválido, sigue
        // pidiendo. Así nunca queda matriculas = 0, que daría NaN en la media.
        // =====================================================================
        while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX) {
            System.out.println("No esta dentro del rango (1-10). Vuelve a intentar: ");
            matriculas = scanner.nextInt();
        }

        // =====================================================================
        // VARIABLES DE ESTADO (se preparan para los pasos 10, 11, 14 y 15)
        // Se declaran FUERA del for para que acumulen durante todas las notas.
        // Si estuvieran dentro, se reiniciarían en cada vuelta.
        //  - sumaNotas:  acumulador (paso 10)
        //  - aprobadas:  contador (paso 11)
        //  - notaMaxima: empieza en el valor MÁS BAJO posible (paso 14)
        //  - notaMinima: empieza en el valor MÁS ALTO posible (paso 15)
        // =====================================================================
        double sumaNotas = 0;
        int aprobadas = 0;
        double notaMaxima = NOTA_MINIMA;
        double notaMinima = NOTA_MAXIMA;

        // =====================================================================
        // PASO 09 (commits "paso-09: Preguntar notas e imprimir" y "paso-09: Coreccion")
        // Bucle "for": se sabe cuántas vueltas son (matriculas), no un 10 fijo.
        // La variable se llama "asignatura" (lo que cuenta) y empieza en 1 para
        // mostrar el número al usuario sin sumar nada.
        // La corrección fue usar double (notas con decimales) y el límite <=.
        // =====================================================================
        for (int asignatura = 1; asignatura <= matriculas; asignatura++) {

            // -----------------------------------------------------------------
            // PASO 13 (commit "paso-13: Verificar dentro del rango de notas")
            // Pide la nota y repite hasta que esté entre 0 y 10.
            // "do-while": la nota se pide al menos una vez, y la lectura se
            // escribe una sola vez. "notaValida" es la variable de estado.
            // Va ANTES de los cálculos para no sumar notas inválidas.
            // -----------------------------------------------------------------
            double notaAsignatura;
            boolean notaValida = false;

            do {
                System.out.println("Nota de la asignatura " + asignatura + ": ");
                notaAsignatura = scanner.nextDouble();
                notaValida = notaAsignatura >= NOTA_MINIMA && notaAsignatura <= NOTA_MAXIMA;
                if (!notaValida) {                      // "!" = NOT: "si la nota NO es válida"
                    System.out.println("No es valido, vuelve a intentar.");
                }
            } while (!notaValida);                      // repite MIENTRAS no sea válida

            // -----------------------------------------------------------------
            // PASO 10 (parte del bucle): acumular la suma de las notas.
            // La división para la media se hace después del bucle.
            // -----------------------------------------------------------------
            sumaNotas = sumaNotas + notaAsignatura;

            // -----------------------------------------------------------------
            // PASO 11 (parte del bucle): contar asignaturas aprobadas.
            // Se incrementa con ++ dentro del if, sin tocar la variable del for.
            // -----------------------------------------------------------------
            if (notaAsignatura >= APROBADO) {
                aprobadas++;
            }

            // -----------------------------------------------------------------
            // PASO 14 (commit "paso-14: Nota mas alta")
            // Si la nota actual supera a la máxima guardada, la reemplaza.
            // -----------------------------------------------------------------
            if (notaAsignatura > notaMaxima) {
                notaMaxima = notaAsignatura;
            }

            // -----------------------------------------------------------------
            // PASO 15 (commit "paso-15: Nota mas baja")
            // Si la nota actual es menor que la mínima guardada, la reemplaza.
            // -----------------------------------------------------------------
            if (notaAsignatura < notaMinima) {
                notaMinima = notaAsignatura;
            }
        }

        // =====================================================================
        // RESULTADOS: se imprimen UNA vez, DESPUÉS del bucle.
        // =====================================================================

        // =====================================================================
        // PASO 10 (commit "paso-10: Media de las notas")
        // Media = suma / número de asignaturas. sumaNotas es double, por lo que
        // no hay división entera (17 / 3 daría 5 con enteros).
        // =====================================================================
        double media = sumaNotas / matriculas;
        System.out.println("La media es: " + media);

        // =====================================================================
        // PASO 11 (commit "paso-11: Materias aprobadas")
        // Muestra el contador acumulado en el bucle.
        // =====================================================================
        System.out.println("Asignaturas aprobadas: " + aprobadas);

        // =====================================================================
        // PASO 12 (commit "paso-12: Mostrar asignaturas suspensas")
        // Se calcula, no se cuenta aparte: suspensas = total - aprobadas.
        // =====================================================================
        int suspensas = matriculas - aprobadas;
        System.out.println("Asignaturas suspensas: " + suspensas);

        // =====================================================================
        // PASO 14 y PASO 15: se muestran la nota más alta y la más baja.
        // =====================================================================
        System.out.println("La nota mas alta es: " + notaMaxima);
        System.out.println("La nota mas baja es: " + notaMinima);

        // =====================================================================
        // PASO 16 (commit "paso-16: Funcion de promocionar o recuperar")
        // Promociona si la media es de aprobado Y la nota mínima llega a 4.
        // "&&" exige que se cumplan las dos condiciones.
        // Ejemplo: notas 6, 3.5, 8 -> media 5.83 pero mínima 3.5 < 4 -> Recupera.
        // =====================================================================
        boolean promociona = media >= APROBADO && notaMinima >= NOTA_MINIMA_PROMOCION;
        System.out.println(promociona ? "Promociona" : "Recupera");

        // Cierre del Scanner (buena práctica al terminar de leer).
        scanner.close();
    }
}