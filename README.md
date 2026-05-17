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

Implementación desde cero (sin librerías nativas de árboles de Java) de un **Árbol Binario de Búsqueda (BST)** que almacena números enteros. El proyecto forma parte de la **Fase 2** del curso de Estructuras de Datos.

### ¿Qué es un BST?

Un Árbol Binario de Búsqueda es una estructura jerárquica donde:
- El **hijo izquierdo** de todo nodo contiene valores **menores** que él.
- El **hijo derecho** de todo nodo contiene valores **mayores** que él.
- Esta propiedad se cumple recursivamente en todos los sub-árboles.

```
        50
       /  \
      30    70
     /  \  /  \
    20  40 60  80
```

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
├── compilar.bat            ← Script Windows
├── compilar.sh             ← Script Linux/Mac
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
  Se encuentra el SUCESOR IN-ORDER (mínimo del sub-árbol derecho),
  se copia su valor al nodo actual, y se elimina el sucesor.
```

---

## 🚀 Cómo Compilar y Ejecutar

### Requisitos
- **Java JDK 11** o superior instalado
- Variable de entorno `JAVA_HOME` configurada

### Windows
```bat
compilar.bat
```

### Linux / macOS
```bash
chmod +x compilar.sh
bash compilar.sh
```

### Manual paso a paso
```bash
# 1. Crear carpeta de salida
mkdir -p out/main out/test

# 2. Compilar clases principales
javac -d out/main src/main/java/bst/Nodo.java src/main/java/bst/BST.java src/main/java/bst/Main.java

# 3. Ejecutar la demo
java -cp out/main bst.Main

# 4. Compilar y ejecutar pruebas
javac -cp out/main -d out/test src/test/java/bst/BSTTest.java
java -cp "out/main:out/test" bst.BSTTest    # Linux/Mac
java -cp "out/main;out/test" bst.BSTTest    # Windows
```

---

## 📊 Análisis de Complejidad Big-O

### Método `search(int valor)` — Explicación detallada

```
CASO PROMEDIO: O(log n)
  En un árbol BST balanceado, cada comparación descarta la MITAD
  del árbol restante. Con n nodos y altura h ≈ log₂(n):
  
    n = 1,000,000 nodos → máximo 20 comparaciones
    n = 1,000,000,000   → máximo 30 comparaciones
  
  Esto es porque la altura del árbol crece logarítmicamente.

PEOR CASO: O(n)
  Si los datos se insertan ya ordenados (ej: 1, 2, 3, 4, 5...),
  el árbol degenera a una lista enlazada:
  
    1 → 2 → 3 → 4 → 5
  
  En este caso hay que recorrer todos los n nodos para buscar.

SOLUCIÓN: variantes auto-balanceadas (AVL, Red-Black Tree)
  garantizan O(log n) en TODOS los casos.
```

---

## 📄 Fichas Técnicas (Fase 1)

El documento PDF con las 5 fichas técnicas se encuentra en [`docs/Fichas_Tecnicas_Estructuras_Arboreas.pdf`](docs/Fichas_Tecnicas_Estructuras_Arboreas.pdf)

Estructuras documentadas:
- 📘 Árbol Binario de Búsqueda (BST)
- 📗 Árbol Balanceado AVL
- 📙 Árbol B / B+
- 📒 Trie (Árbol de Prefijos)
- 📕 Heap (Montículo Min/Max)

---

## 👨‍💻 Autor

| Campo | Detalle |
|-------|---------|
| **Nombre** | *(tu nombre aquí)* |
| **Carné** | *(tu carné aquí)* |
| **Curso** | Estructuras de Datos |
| **Universidad** | Da Vinci de Guatemala |
| **Año** | 2025 |

---

## 📜 Licencia

Proyecto académico — Universidad Da Vinci de Guatemala.  
Uso educativo únicamente.
