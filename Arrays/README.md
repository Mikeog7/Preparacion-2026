# Arrays: 20 ejercicios de básico a avanzado

Cada archivo es un programa independiente con su comentario inicial: **ENUNCIADO, SIRVE PARA, CONCEPTO CLAVE, ERROR TIPICO y PRUEBA**.
Siguen las mismas convenciones que el resto del repo: constantes `final` en MAYÚSCULAS, nombres completos (`scanner`, `notaMaxima`),
mensaje antes de cada lectura, sin `break` y sin `switch`.

> **Aviso:** la rúbrica del parcial de PRG1 trata los arrays como un concepto NO permitido (nivel de código "Malo").
> Esta carpeta sirve para dominar el tema; en ese examen concreto resuelve con acumuladores y contadores.

## Índice

| Nº | Archivo | Nivel | Qué aprendes |
|---|---|---|---|
| 01 | Array01Declaracion | Básico | Crear, valores por defecto, `length`, índices, fuera de rango |
| 02 | Array02LeerYMostrar | Básico | Validar el tamaño antes de crear; `for`, for-each e inverso |
| 03 | Array03Estadisticas | Básico | Suma, media, máx/mín con posición; segunda pasada |
| 04 | Array04Buscar | Intermedio | Búsqueda lineal: primera, última y recuento |
| 05 | Array05FiltrarAprobadas | Intermedio | Contar primero, crear después; índice de destino |
| 06 | Array06Invertir | Intermedio | Intercambio con auxiliar; palíndromo |
| 07 | Array07CopiaYAlias | Intermedio | Referencias, `copyOf`, `equals` frente a `==` |
| 08 | Array08Rotar | Intermedio | Desplazar elementos; el sentido del recorrido |
| 09 | Array09InsertarEliminar | Intermedio | Array parcialmente lleno (`usados`) y desplazamientos |
| 10 | Array10Burbuja | Intermedio | Ordenar; bandera de optimización; ascendente/descendente |
| 11 | Array11BusquedaBinaria | Avanzado | Buscar en ordenado descartando mitades |
| 12 | Array12Frecuencias | Avanzado | El dato como índice; histograma; letras con `char` |
| 13 | Array13ArraysParalelos | Avanzado | Dos arrays alineados; ordenar ambos a la vez |
| 14 | Array14Unicos | Avanzado | Quitar duplicados con búsqueda interna |
| 15 | Array15Mezclar | Avanzado | Merge con tres índices |
| 16 | Array16MatrizNotas | Avanzado | Matriz `[fila][columna]`; medias por fila y columna |
| 17 | Array17MatrizCuadrada | Avanzado | Diagonales, transpuesta y simetría |
| 18 | Array18CribaEratostenes | Avanzado | Array de `boolean` como tablero de marcas |
| 19 | Array19ArraysYMetodos | Avanzado | Paso por referencia: modificar frente a reasignar |
| 20 | Array20GestorNotas | Integrador | Menú que une todo: añadir, buscar, eliminar, ordenar y estadísticas |

## Cómo funciona un array

| Idea | Regla |
|---|---|
| Qué es | Varias casillas del MISMO tipo, con tamaño FIJO, numeradas desde 0 |
| Crear | `int[] a = new int[5];` o `int[] a = {1, 2, 3};` |
| Tamaño | `a.length` (sin paréntesis) |
| Último índice | `a.length - 1` |
| Por defecto | `int` 0, `double` 0.0, `boolean` false, `char` '\0', objetos `null` |
| Copiar | `b = a` NO copia (alias); usa un bucle o `Arrays.copyOf` |
| Comparar | `==` compara referencias; `Arrays.equals(a, b)` compara contenido |
| Imprimir | `Arrays.toString(a)`; `System.out.println(a)` solo muestra una dirección |
| Matriz | `int[][] m = new int[filas][columnas];` `m.length` = filas, `m[0].length` = columnas |
| Strings dentro | comparar con `equals()`, nunca con `==` |

## Errores típicos y cómo evitarlos

| Error | Causa | Solución |
|---|---|---|
| `ArrayIndexOutOfBoundsException` | Índice `length`, o `<=` en el bucle | Usar `< length`; en parejas vecinas, `< length - 1` |
| Máximo siempre 0 | Inicializado en 0 con notas negativas | Inicializar con `datos[0]` |
| "Copia" que cambia el original | `b = a` | Copiar elemento a elemento |
| Ceros al final contados como datos | Recorrer hasta `length` en vez de `usados` | Llevar una variable `usados` |
| Datos desalineados | Ordenar un solo array paralelo | Intercambiar todos los arrays a la vez |
| Valores pisados al rotar | Sentido de recorrido equivocado | Izquierda: de izquierda a derecha. Derecha: de derecha a izquierda |
| Array vacío con tamaño 0 | Crear antes de validar | Validar el tamaño y luego `new` |
| Suma de una fila arrastra la anterior | Acumulador fuera del bucle exterior | Declararlo dentro del bucle de filas |

## ¿Cuándo usar un array?

| Situación | Herramienta |
|---|---|
| Solo necesito totales (suma, contador, máximo) | Variables (acumuladores): más simple |
| Necesito volver a recorrer los datos (por ejemplo, comparar con la media) | Array |
| Necesito ordenar, buscar o invertir | Array |
| Los valores son categorías pequeñas que cuento | Array de frecuencias |
| Datos en tabla (alumnos x asignaturas) | Matriz |
| No sé cuántos datos habrá | Array con capacidad + `usados` |
