# Matrices: 20 ejercicios basados en el juego "MundoAbierto"

Cada archivo es un programa independiente con su comentario inicial: **ENUNCIADO, SIRVE PARA, CONCEPTO CLAVE, ERROR TIPICO y PRUEBA**.
Siguen las convenciones del resto del repo: constantes `final` en MAYÚSCULAS, nombres completos (`scanner`, `posicionPersonaje`),
mensaje antes de cada lectura, sin `break` y sin `switch`.

> **Aviso:** la rúbrica del parcial de PRG1 trata las matrices (y los arrays) como un concepto NO permitido.
> Esta carpeta sirve para dominar el tema; en ese examen concreto resuelve con acumuladores y contadores.
>
> Si compilas en Windows y los símbolos `·` salen raros, usa `javac -encoding UTF-8 Archivo.java`.

## Convención de terrenos (propia de estos ejercicios)

| ID | Símbolo | Terreno | ¿Transitable? |
|---|---|---|---|
| 0 | ` . ` | hierba | sí |
| 1 | `[#]` | edificio | no |
| 2 | ` · ` | camino | sí |
| 3 | `~~~` | agua | no |
| 4 | `'''` | arena | sí |
| 5 | `:::` | piedra | sí |
| 6 | `, '` | arbusto | sí |
| 7 | `/^\` | montaña | no |
| 8 | `^Y^` | árbol | no |
| 9 | `:\|:` | tesoro | sí |

## Índice

| Nº | Archivo | Nivel | Qué aprendes |
|---|---|---|---|
| 01 | Matriz01Declaracion | Básico | `[fila][columna]`, `length` de filas y columnas, matriz irregular |
| 02 | Matriz02MostrarMundo | Básico | Tabla de traducción: el ID de la matriz indexa un array de símbolos |
| 03 | Matriz03ContarMosaicos | Básico | Array de frecuencias aplicado a una matriz; porcentajes con `printf` |
| 04 | Matriz04BuscarMosaico | Básico | Búsqueda 2D: todas las apariciones y la primera sin `break` |
| 05 | Matriz05GenerarPatrones | Intermedio | Rellenar con bucles: marco, diagonal, alternado, franjas |
| 06 | Matriz06SumasFilasColumnas | Intermedio | Recorrer por filas y por columnas; diagonal |
| 07 | Matriz07Transformaciones | Intermedio | Transpuesta, espejos y rotación 90° |
| 08 | Matriz08PersonajeDibujado | Intermedio | Posición como `int[]{fila, columna}`; arrays paralelos |
| 09 | Matriz09MovimientoLimites | Intermedio | Movimiento WASD sin salirse del mapa |
| 10 | Matriz10Colisiones | Intermedio | Reglas del mundo en un `boolean[]` indexado por terreno |
| 11 | Matriz11CasillasVisitadas | Avanzado | Segunda matriz de estado (`boolean[][]`) y porcentajes |
| 12 | Matriz12NieblaDeGuerra | Avanzado | Distancia entre coordenadas con `Math.abs` |
| 13 | Matriz13Camara | Avanzado | Dibujar una ventana de la matriz con ajuste de bordes |
| 14 | Matriz14Tesoros | Avanzado | Modificar la matriz durante el juego; condición de victoria |
| 15 | Matriz15ZonasConectadas | Avanzado | Vecindad de 4 direcciones y etiquetado de zonas |
| 16 | Matriz16Buscaminas | Avanzado | Vecindad de 8 direcciones con control de bordes |
| 17 | Matriz17VidaConway | Avanzado | Dos matrices: leer la generación actual, escribir la siguiente |
| 18 | Matriz18MapaDistancias | Avanzado | Algoritmo de ola: camino más corto por capas |
| 19 | Matriz19Perseguidor | Avanzado | Dos posiciones, colisiones y un enemigo que persigue |
| 20 | Matriz20MundoAbiertoCompleto | Integrador | El juego completo con todo lo anterior y métodos |

## Análisis del código de referencia (`MundoAbierto`)

| Aspecto | Qué hace bien | Qué mejorar | Ejercicio que lo resuelve |
|---|---|---|---|
| Mundo | `int[][]` con IDs y un array de símbolos aparte | — | 02 |
| Posición | `int[] {fila, columna}` | — | 08 |
| Dibujo | Compara cada casilla con la posición del personaje | Dibuja siempre el mundo entero | 13 |
| Movimiento | Calcula 4 direcciones con `equalsIgnoreCase` | **No comprueba límites**: el personaje sale del mapa y desaparece | 09 |
| Terreno | — | Se puede pisar agua, montañas y edificios | 10 |
| Salida | `estaJugando = !estaJugando` | Mejor `estaJugando = false` | 09 en adelante |
| Código sobrante | — | `mosaicos2` y `personaje2` nunca se usan | 20 |
| Constantes | — | `"w"`, `"a"`, `"s"`, `"d"`, `{ 11, 6 }` y `"_O_"` sueltos | 09 en adelante |
| Teclas inválidas | — | Se ignoran sin avisar | 09 |

## Cómo funciona una matriz

| Idea | Regla |
|---|---|
| Qué es | Un array de arrays: filas que contienen columnas |
| Crear | `int[][] m = new int[filas][columnas];` o con llaves anidadas |
| Acceder | `m[fila][columna]`, SIEMPRE fila primero |
| Filas | `m.length` |
| Columnas | `m[0].length` (o `m[fila].length` en matrices irregulares) |
| Recorrer por filas | `for fila` por fuera, `for columna` por dentro |
| Recorrer por columnas | Se intercambian los dos `for` |
| Suma parcial de fila o columna | Se declara DENTRO del bucle exterior |
| Vecinos de 4 direcciones | `(fila-1, columna)`, `(fila+1, columna)`, `(fila, columna-1)`, `(fila, columna+1)` |
| Vecinos de 8 direcciones | Dos bucles de desplazamiento `-1..1`, descartando `(0, 0)` |
| Antes de leer un vecino | Comprobar que no se sale de la matriz |
| Copiar una matriz | `b = a` NO copia (alias); hay que crear una nueva y copiar fila a fila |
| Dos matrices del mismo tamaño | Una para los datos y otra para el estado (visitadas, etiquetas, distancias) |

## Errores típicos y cómo evitarlos

| Error | Causa | Solución |
|---|---|---|
| `ArrayIndexOutOfBoundsException` | Índice igual a `length`, o vecino sin comprobar el borde | Usar `< length` y comprobar el borde antes de leer |
| Datos "girados" | `[columna][fila]` en vez de `[fila][columna]` | Recordar: fila primero |
| La suma de la fila 2 incluye la de la fila 1 | Acumulador declarado fuera del bucle exterior | Declararlo dentro del bucle de filas |
| El personaje se sale y desaparece | Mover sin comprobar límites | Calcular la posición nueva y validarla antes de aplicarla |
| Leer una casilla inexistente | Comprobar el terreno antes que el límite | Primero el borde, después el terreno |
| Resultado distinto al esperado en Life o buscaminas | Modificar la misma matriz mientras se recorre | Escribir el resultado en otra matriz |
| Transponer sobre la misma matriz | Se pisan valores | Crear una matriz nueva con las dimensiones intercambiadas |
| Porcentaje siempre 0 | División entera | Multiplicar por `100.0` |
| Contar dos veces la misma casilla | Contador sin comprobar el estado previo | Usar una matriz `boolean[][]` de visitadas |

## ¿Cuándo usar una matriz?

| Situación | Herramienta |
|---|---|
| Datos en tabla con filas y columnas (alumnos x asignaturas) | Matriz |
| Mapas, tableros, juegos de casillas | Matriz de IDs |
| Estado extra por casilla | Otra matriz del mismo tamaño |
| Solo una secuencia de valores | Array simple |
| Solo necesito totales | Acumuladores (más simple) |
