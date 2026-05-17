# 🌳 Árbol Binario de Búsqueda — BST en Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Universidad Da Vinci](https://img.shields.io/badge/Universidad-Da%20Vinci%20Guatemala-0057A8?style=for-the-badge)
![Estructuras de Datos](https://img.shields.io/badge/Curso-Estructuras%20de%20Datos-green?style=for-the-badge)
![Estado](https://img.shields.io/badge/Estado-Completado-brightgreen?style=for-the-badge)

> **Proyecto:** Fichas Técnicas de Estructuras Arbóreas y Aplicación Práctica  
> **Curso:** Estructuras de Datos — Ingeniería en Sistemas y Ciencias de la Computación  
> **Universidad:** Da Vinci de Guatemala

---

## 📹 Video de Demostración

> 🎬 **[Ver video en YouTube →](https://youtube.com/tu-enlace-aquí)**  
> *(Subir el video como **Público** y reemplazar el enlace)*

---

## 📋 Descripción

Implementación desde cero (sin librerías nativas de árboles de Java) de un **Árbol Binario de Búsqueda (BST)** que almacena números enteros.

### Propiedad BST

```
        45
       /  \
      22    78
     /  \  /  \
    11  33 67  89
   /  \  ...
  5   17
```

- Hijo izquierdo → valores **menores** que el padre
- Hijo derecho   → valores **mayores** que el padre

---

## 🗂️ Estructura del Proyecto

```
BST-Java/
├── src/
│   ├── main/java/bst/
│   │   ├── Nodo.java       ← Clase nodo (valor + hijo izq/der)
│   │   ├── BST.java        ← Implementación completa del árbol
│   │   └── Main.java       ← Demo interactiva (para el video)
│   └── test/java/bst/
│       └── BSTTest.java    ← Pruebas unitarias sin framework
├── docs/
│   └── Fichas_Tecnicas_Estructuras_Arboreas.pdf
├── compilar.sh             ← Script Linux/Mac/Codespaces
├── .gitignore
└── README.md
```

---

## ⚙️ Métodos Implementados

| Método | Firma | Complejidad Promedio | Descripción |
|--------|-------|---------------------|-------------|
| Insertar | `void insert(int valor)` | O(log n) | Coloca el nodo en la posición correcta |
| Buscar | `boolean search(int valor)` | O(log n) | Retorna `true` si el valor existe |
| Eliminar | `void delete(int valor)` | O(log n) | Elimina considerando los 3 casos |
| InOrder | `void recorridoInOrder()` | O(n) | Imprime en orden **ascendente** |
| PreOrder | `void recorridoPreOrder()` | O(n) | Imprime raíz **primero** |
| PostOrder | `void recorridoPostOrder()` | O(n) | Imprime raíz al **final** |

### 🗑️ Los 3 Casos de Eliminación

```
CASO 1 — Nodo hoja (sin hijos):
  Se elimina directamente, el padre apunta a null.

CASO 2 — Nodo con un solo hijo:
  El nodo es reemplazado por su único hijo.

CASO 3 — Nodo con dos hijos:
  Se encuentra el sucesor in-order (mínimo del sub-árbol derecho),
  se copia su valor al nodo actual, y se elimina el sucesor.
```

---

## 🚀 Cómo Compilar y Ejecutar

### Requisitos
- **Java JDK 11** o superior

### Opción 1 — Script (recomendado)
```bash
chmod +x compilar.sh
bash compilar.sh
```

### Opción 2 — Manual
```bash
# Crear carpetas de salida
mkdir -p out/main out/test

# Compilar
javac -d out/main src/main/java/bst/Nodo.java src/main/java/bst/BST.java src/main/java/bst/Main.java

# Ejecutar demo
java -cp out/main bst.Main

# Compilar y ejecutar pruebas
javac -cp out/main -d out/test src/test/java/bst/BSTTest.java
java -cp "out/main:out/test" bst.BSTTest
```

---

## 📊 Análisis de Complejidad Big-O — método `search()`

```
CASO PROMEDIO: O(log n)
  Árbol balanceado: cada comparación descarta la mitad.
  1,000,000 nodos -> máximo 20 comparaciones.

PEOR CASO: O(n)
  Datos ya ordenados -> árbol degenera a lista enlazada.
  Hay que recorrer todos los n nodos.

SOLUCIÓN: AVL o Red-Black Tree garantizan O(log n) siempre.
```

---

## 📄 Fichas Técnicas (Fase 1)

Documento PDF en [`docs/Fichas_Tecnicas_Estructuras_Arboreas.pdf`](docs/Fichas_Tecnicas_Estructuras_Arboreas.pdf)

Estructuras documentadas:
- Árbol Binario de Búsqueda (BST)
- Árbol Balanceado AVL
- Árbol B / B+
- Trie (Árbol de Prefijos)
- Heap (Montículo Min/Max)

---

## 👨‍💻 Autor

| Campo | Detalle |
|-------|---------|
| **Nombre** | *(tu nombre aquí)* |
| **Carné** | *(tu carné aquí)* |
| **Curso** | Estructuras de Datos |
| **Universidad** | Da Vinci de Guatemala |
