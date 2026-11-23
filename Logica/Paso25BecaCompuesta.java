package Logica;
import java.util.Scanner;

/**
 * PASO 25: Condición compuesta con && , || y paréntesis
 *
 * ENUNCIADO:  Un alumno tiene beca si:
 *               (a) cumple el perfil académico: media >= mediaRequerida, ninguna
 *                   suspensa y menos de 25 años; O
 *               (b) tiene 3 o más sobresalientes (beca por excelencia).
 *             La mediaRequerida es 6.5, pero baja a 6.0 si es familia numerosa.
 * SIRVE PARA: construir decisiones con varias reglas sin perderse.
 * USA:        boolean parciales con nombre, ternario para el umbral, && antes
 *             que || (por eso los paréntesis), entrada s/n con char.
 * EN EL EXAMEN: "o", "y", "salvo que", "excepto si". Parte la condición en
 *             booleanos con nombre y combínalos al final: se lee casi como el enunciado.
 * PRUEBA:     media 7, 0 susp, 22 años            -> beca (a)
 *             media 7, 1 susp, 22 años, 0 sobres. -> NO
 *             media 5, 2 susp, 30 años, 3 sobres. -> beca (b)
 *             media 6.2, 0 susp, 22 años, familia s -> beca (6.2 >= 6.0)
 */
public class Paso25BecaCompuesta {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final double MEDIA_BECA = 6.5;
        final double MEDIA_BECA_FAMILIA_NUMEROSA = 6.0;
        final int EDAD_LIMITE_BECA = 25;
        final int SOBRESALIENTES_EXCELENCIA = 3;

        System.out.println("Edad del alumno: ");
        int edad = scanner.nextInt();
        System.out.println("Media del alumno: ");
        double media = scanner.nextDouble();
        System.out.println("Asignaturas suspensas: ");
        int suspensas = scanner.nextInt();
        System.out.println("Sobresalientes: ");
        int sobresalientes = scanner.nextInt();
        System.out.println("Familia numerosa? (s/n): ");
        char respuesta = scanner.next().charAt(0);

        boolean familiaNumerosa = respuesta == 's';
        double mediaRequerida = familiaNumerosa ? MEDIA_BECA_FAMILIA_NUMEROSA : MEDIA_BECA;

        boolean cumplePerfilAcademico = media >= mediaRequerida && suspensas == 0 && edad < EDAD_LIMITE_BECA;
        boolean cumpleExcelencia = sobresalientes >= SOBRESALIENTES_EXCELENCIA;
        boolean tieneBeca = cumplePerfilAcademico || cumpleExcelencia;

        System.out.println(tieneBeca ? "Tiene beca" : "No tiene beca");

        scanner.close();
    }
}
