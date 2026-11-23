# Ampliación del parcial: pasos 17 a 30

Catorce ejercicios independientes, cada uno más exigente que el anterior. Todos siguen las reglas de la rúbrica del parcial:
constantes `final` en MAYÚSCULAS, nombres completos (`scanner`, `notaMaxima`), sin arrays, sin `break` y sin `switch`,
mensaje antes de cada lectura y el bucle adecuado en cada caso.

Cada archivo empieza con un comentario con **ENUNCIADO, SIRVE PARA, USA, EN EL EXAMEN y PRUEBA**.

## Índice

| Paso | Archivo | Nivel | Concepto nuevo |
|---|---|---|---|
| 17 | Paso17ClasificarNotas | 1 | `else if` y varios contadores |
| 18 | Paso18PosicionMaxMin | 1 | Guardar la posición del máximo y mínimo |
| 19 | Paso19PrimeraSuspensa | 2 | Bandera "alguna vez" sin `break` |
| 20 | Paso20RachaAprobadas | 2 | Rachas: contador que se reinicia |
| 21 | Paso21SegundaNotaAlta | 2 | Podio de dos puestos sin arrays |
| 22 | Paso22MediaPonderada | 3 | Dos acumuladores y dos validaciones por vuelta |
| 23 | Paso23VariosAlumnos | 3 | Bucle dentro de bucle: variables globales y por alumno |
| 24 | Paso24MenuConEstado | 3 | Menú con `do-while` que recuerda datos |
| 25 | Paso25BecaCompuesta | 3 | `&&`, `\|\|`, paréntesis y booleanos con nombre |
| 26 | Paso26Triangulo | 4 | Bucles anidados con `print` / `println` |
| 27 | Paso27DigitosNumero | 4 | `% 10` y `/ 10` para recorrer cifras |
| 28 | Paso28NumerosPrimos | 4 | Búsqueda con bandera en la condición del `while` |
| 29 | Paso29Fibonacci | 5 | Variables que se desplazan; `long` |
| 30 | Paso30AdivinaNumero | 5 | `Random` y doble condición de parada |

## Si el enunciado dice... usa...

| El enunciado dice | Herramienta |
|---|---|
| "pide hasta que sea válido" | `while` (leer antes) o `do-while` (leer una vez) |
| "repite N veces", "para cada asignatura" | `for` con límite variable |
| "mientras el usuario quiera / hasta que elija salir" | `do-while` con la respuesta en la condición |
| "cuántas", "cuántos" | contador (`++`) fuera del bucle |
| "suma", "media", "total" | acumulador fuera, suma dentro, división después |
| "la más alta / baja" | máximo / mínimo inicializados con el extremo opuesto |
| "en qué asignatura", "cuál fue" | variable extra de posición dentro del mismo `if` |
| "alguna", "ninguna", "la primera vez" | bandera `boolean` que empieza en `false` |
| "seguidas", "consecutivas" | `rachaActual` (se reinicia) y `rachaMaxima` |
| "en cada tramo", "según el rango" | `else if` encadenados |
| "y", "o", "salvo que" | booleanos con nombre y `&&` / `\|\|` |
| "aleatorio" | `Random` |

## Dónde declarar cada variable

| Variable | Dónde |
|---|---|
| Constantes | Al principio del `main` |
| Acumuladores, contadores, máximo y mínimo de UN alumno | Dentro del bucle de alumnos, antes del `for` de notas |
| Acumuladores de TODOS los alumnos | Fuera del bucle de alumnos |
| La nota actual y su bandera de validez | Dentro del `for` |
| Los resultados | Después del bucle correspondiente, una sola vez |
