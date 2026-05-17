# Árbol Binario de Búsqueda (BST) — Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Universidad Da Vinci](https://img.shields.io/badge/Universidad-Da%20Vinci%20Guatemala-0057A8?style=for-the-badge)
![Curso](https://img.shields.io/badge/Curso-Estructuras%20de%20Datos-green?style=for-the-badge)

> **Universidad Da Vinci de Guatemala**  
> Facultad de Ingeniería en Sistemas y Ciencias de la Computación  
> Curso: Estructuras de Datos

---

## Video de Demostración

**[Ver video en YouTube →](https://youtube.com/tu-enlace-aqui)**

---

## ¿Qué es un Árbol Binario de Búsqueda?

Un **Árbol Binario de Búsqueda (BST)** es una estructura de datos jerárquica donde cada nodo tiene como máximo dos hijos. Su regla fundamental es:

```
    hijo izquierdo  <  nodo padre  <  hijo derecho
```

Esta propiedad se cumple en **todos** los sub-árboles de forma recursiva, lo que permite búsquedas eficientes.

**Ejemplo visual:**

```
        45
       /  \
      22    78
     /  \  /  \
    11  33 67  89
   /  \       /  \
  5   17     84   95
```

---

## Métodos Implementados

| Método | Descripción | Complejidad Promedio | Complejidad Peor Caso |
|--------|-------------|---------------------|----------------------|
| `insert(int valor)` | Inserta un valor manteniendo la propiedad BST | O(log n) | O(n) |
| `search(int valor)` | Retorna `true` si el valor existe | O(log n) | O(n) |
| `delete(int valor)` | Elimina un valor (3 casos) | O(log n) | O(n) |
| `recorridoInOrder()` | Imprime valores en orden **ascendente** | O(n) | O(n) |
| `recorridoPreOrder()` | Imprime la raíz **primero** | O(n) | O(n) |
| `recorridoPostOrder()` | Imprime la raíz al **final** | O(n) | O(n) |

---

## Los 3 Casos de Eliminación

**Caso 1 — Nodo hoja (sin hijos)**
```
  Antes:  22          Después: 22
         /  \                 /
        11  33               11
       /
      5   ← eliminar
```
El nodo se elimina directamente. El padre apunta a `null`.

---

**Caso 2 — Nodo con un solo hijo**
```
  Antes:  45          Después:  45
         /                     /
        22   ← eliminar        11
       /
      11
```
El nodo es reemplazado por su único hijo.

---

**Caso 3 — Nodo con dos hijos**
```
  Antes:  45          Después:  45
         /                     /
        22   ← eliminar        28
       /  \                   /  \
      11  33                 11  33
         /                      
        28  ← sucesor in-order  
```
Se busca el **sucesor in-order** (el mínimo del sub-árbol derecho), se copia su valor al nodo eliminado y se borra el sucesor de su posición original.

---

##  Los 3 Recorridos

| Recorrido | Orden | Resultado con el árbol de demo | Uso real |
|-----------|-------|-------------------------------|----------|
| **InOrder** | Izq → Raíz → Der | `5 11 17 22 28 33 38 45 55 67 72 78 84 89 95` | Obtener lista ordenada |
| **PreOrder** | Raíz → Izq → Der | `45 22 11 5 17 33 28 38 78 67 55 72 89 84 95` | Copiar/serializar el árbol |
| **PostOrder** | Izq → Der → Raíz | `5 17 11 28 38 33 22 55 72 67 84 95 89 78 45` | Liberar memoria |

---

##  Complejidad Big-O — Método `search()`

### ¿Por qué O(log n) en promedio?

En un BST balanceado, la altura del árbol es `h ≈ log₂(n)`. Cada comparación descarta la mitad del árbol restante:

```
  n = 1,000,000 nodos  →  máximo 20 comparaciones
  n = 1,000,000,000    →  máximo 30 comparaciones
```

### ¿Por qué O(n) en el peor caso?

Si los datos se insertan ya ordenados (`insert(1), insert(2), insert(3)...`), el árbol degenera a una lista enlazada:

```
  1
   \
    2
     \
      3
       \
        4   ← hay que recorrer todos los nodos
```

### Solución

Usar variantes auto-balanceadas como **AVL** o **Red-Black Tree** que garantizan O(log n) en **todos** los casos mediante rotaciones automáticas.

---

##  Menú Interactivo

Al ejecutar el programa aparece un menú con las siguientes opciones:

```
  +--------------------------+
  |      MENU PRINCIPAL      |
  +--------------------------+
  [1] Insertar valor(es)
  [2] Buscar valor
  [3] Eliminar valor
  [4] Ver recorridos (InOrder/PreOrder/PostOrder)
  [5] Ver estructura del arbol
  [6] Informacion Big-O
  [7] Cargar datos de demo
  [0] Salir
```

### Guía de uso rápido

1. Presionar `7` para cargar los 15 valores de demo automáticamente.
2. Presionar `5` para ver la estructura visual del árbol.
3. Presionar `4` para ver los 3 recorridos.
4. Presionar `2` y escribir un número para buscarlo.
5. Presionar `3` para eliminar un valor (muestra cuál de los 3 casos aplica).

---

##  Ejecutar en GitHub Codespaces / Linux / Mac

```bash
mkdir -p out && javac -d out src/Main.java && java -cp out bst.Main
```

### Requisitos

- Java JDK 11 o superior

---

##  Estructura del Proyecto

```
BST-Simple/
├── src/
│   └── Main.java    ← todo en un solo archivo (Nodo + BST + Menú)
├── docs/
│   └── Fichas_Tecnicas_Estructuras_Arboreas.pdf
├── .gitignore
└── README.md
```

---

##  Fichas Técnicas (Fase 1)

El documento PDF con las 5 fichas técnicas está en [`docs/Fichas_Tecnicas_Estructuras_Arboreas.pdf`](docs/Fichas_Tecnicas_Estructuras_Arboreas.pdf)

Estructuras documentadas:
- Árbol Binario de Búsqueda (BST)
- Árbol Balanceado AVL
- Árbol B / B+
- Trie (Árbol de Prefijos)
- Heap (Montículo Min/Max)

---

##  Autor

| Campo | Detalle |
|-------|---------|
| **Nombre** | *(Eddy Alexander Amperez Carranza)* |
| **Carné** | *(202500054)* |
| **Curso** | Estructuras de Datos |
| **Universidad** | Da Vinci de Guatemala |
