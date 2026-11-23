package Logica;
import java.util.Scanner;

/**
 * PASO 23: Varios alumnos (bucle dentro de bucle)
 *
 * ENUNCIADO:  Repite el examen completo para varios alumnos mientras el usuario
 *             responda "s". Al final muestra: cuántos promocionan y cuántos
 *             recuperan, la media global de todas las notas y qué alumno tuvo la
 *             mejor media.
 * SIRVE PARA: saber DÓNDE declarar cada variable (fuera o dentro del bucle).
 * USA:        do-while exterior (al menos un alumno) + for interior (las notas),
 *             variables de estado GLOBALES fuera y POR ALUMNO dentro.
 * EN EL EXAMEN: "para cada alumno", "otro alumno (s/n)", "resumen del grupo".
 *             Regla: lo que se reinicia en cada alumno se declara DENTRO del
 *             do-while; lo que se acumula entre alumnos, FUERA.
 *             Trampa: la "mejor media" del primer alumno hay que fijarla aparte
 *             (alumnos == 1), si no un 0 de media no actualizaría al mejor.
 * PRUEBA:     alumno 1 (6, 3.5, 8) -> Recupera;  alumno 2 (7, 8) -> Promociona;
 *             resumen: 1 y 1, mejor alumno el 2.
 */
public class Paso23VariosAlumnos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ASIGNATURAS_MIN = 1;
        final int ASIGNATURAS_MAX = 10;
        final double NOTA_MINIMA = 0.0;
        final double NOTA_MAXIMA = 10.0;
        final double APROBADO = 5.0;
        final double NOTA_MINIMA_PROMOCION = 4.0;

        // Variables GLOBALES: fuera del bucle de alumnos.
        int alumnos = 0;
        int promocionan = 0;
        int recuperan = 0;
        double sumaGlobal = 0;
        int totalNotas = 0;
        double mejorMedia = NOTA_MINIMA;
        int mejorAlumno = 0;
        char respuesta;

        do {
            alumnos++;
            System.out.println("--- Alumno " + alumnos + " ---");

            int matriculas;
            do {
                System.out.println("En cuantas asignaturas esta matriculado (1-10): ");
                matriculas = scanner.nextInt();
            } while (matriculas < ASIGNATURAS_MIN || matriculas > ASIGNATURAS_MAX);

            // Variables POR ALUMNO: dentro del bucle para reiniciarse.
            double sumaNotas = 0;
            double notaMinima = NOTA_MAXIMA;

            for (int asignatura = 1; asignatura <= matriculas; asignatura++) {
                double notaAsignatura;
                boolean notaValida;
                do {
                    System.out.println("Nota de la asignatura " + asignatura + ": ");
                    notaAsignatura = scanner.nextDouble();
                    notaValida = notaAsignatura >= NOTA_MINIMA && notaAsignatura <= NOTA_MAXIMA;
                    if (!notaValida) {
                        System.out.println("No es valido, vuelve a intentar.");
                    }
                } while (!notaValida);

                sumaNotas = sumaNotas + notaAsignatura;
                if (notaAsignatura < notaMinima) {
                    notaMinima = notaAsignatura;
                }
            }

            double media = sumaNotas / matriculas;
            boolean promociona = media >= APROBADO && notaMinima >= NOTA_MINIMA_PROMOCION;
            System.out.println("Media: " + media + " -> " + (promociona ? "Promociona" : "Recupera"));

            // Actualizar los globales con los datos de este alumno.
            if (promociona) {
                promocionan++;
            } else {
                recuperan++;
            }
            sumaGlobal = sumaGlobal + sumaNotas;
            totalNotas = totalNotas + matriculas;
            if (alumnos == 1 || media > mejorMedia) {
                mejorMedia = media;
                mejorAlumno = alumnos;
            }

            System.out.println("Quieres registrar otro alumno? (s/n): ");
            respuesta = scanner.next().charAt(0);
        } while (respuesta == 's');

        System.out.println("===== RESUMEN =====");
        System.out.println("Alumnos: " + alumnos);
        System.out.println("Promocionan: " + promocionan + " | Recuperan: " + recuperan);
        System.out.println("Media global: " + sumaGlobal / totalNotas);
        System.out.println("Mejor media: " + mejorMedia + " (alumno " + mejorAlumno + ")");

        scanner.close();
    }
}
