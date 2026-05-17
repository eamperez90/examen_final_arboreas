package bst;

/**
 * ============================================================
 *  ÁRBOL BINARIO DE BÚSQUEDA (BST) — Implementación en Java
 * ============================================================
 *  Universidad Da Vinci de Guatemala
 *  Curso: Estructuras de Datos
 *
 *  MÉTODOS IMPLEMENTADOS:
 *    - insert(int valor)          → O(log n) promedio / O(n) peor caso
 *    - search(int valor)          → O(log n) promedio / O(n) peor caso
 *    - delete(int valor)          → O(log n) promedio / O(n) peor caso
 *    - recorrido InOrder          → O(n)
 *    - recorrido PreOrder         → O(n)
 *    - recorrido PostOrder        → O(n)
 *
 *  PROPIEDAD BST:
 *    Para todo nodo N:
 *      • Todos los valores en sub-árbol izquierdo  < N.valor
 *      • Todos los valores en sub-árbol derecho    > N.valor
 * ============================================================
 */
public class BST {

    /** Raíz del árbol. null cuando el árbol está vacío. */
    private Nodo raiz;

    // ─────────────────────────────────────────────────────────────────
    //  CONSTRUCTOR
    // ─────────────────────────────────────────────────────────────────

    /** Crea un árbol vacío. */
    public BST() {
        this.raiz = null;
    }

    // ─────────────────────────────────────────────────────────────────
    //  INSERT — Insertar un valor
    // ─────────────────────────────────────────────────────────────────

    /**
     * Inserta un nuevo valor en el árbol manteniendo la propiedad BST.
     * Si el valor ya existe, la inserción se ignora (sin duplicados).
     *
     * Complejidad: O(log n) promedio | O(n) peor caso (árbol degenerado)
     *
     * @param valor el entero a insertar
     */
    public void insert(int valor) {
        raiz = insertRecursivo(raiz, valor);
    }

    /**
     * Auxiliar recursivo de insert.
     * Recorre el árbol hacia la posición correcta y crea el nodo hoja.
     *
     * @param nodo  nodo actual en la recursión
     * @param valor valor a insertar
     * @return      sub-árbol modificado
     */
    private Nodo insertRecursivo(Nodo nodo, int valor) {
        // Caso base: posición encontrada → crear nodo hoja
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (valor < nodo.valor) {
            // El valor es menor → va al sub-árbol izquierdo
            nodo.izquierdo = insertRecursivo(nodo.izquierdo, valor);

        } else if (valor > nodo.valor) {
            // El valor es mayor → va al sub-árbol derecho
            nodo.derecho = insertRecursivo(nodo.derecho, valor);

        }
        // Si valor == nodo.valor → duplicado, no se inserta

        return nodo;
    }

    // ─────────────────────────────────────────────────────────────────
    //  SEARCH — Buscar un valor
    // ─────────────────────────────────────────────────────────────────

    /**
     * Busca un valor en el árbol.
     *
     * Complejidad: O(log n) promedio | O(n) peor caso
     *
     * @param valor el entero a buscar
     * @return true si el valor existe en el árbol, false en caso contrario
     */
    public boolean search(int valor) {
        return searchRecursivo(raiz, valor);
    }

    /**
     * Auxiliar recursivo de search.
     * En cada nodo compara y decide si bajar izquierda, derecha o encontró el valor.
     *
     * @param nodo  nodo actual
     * @param valor valor buscado
     * @return      true si se encontró
     */
    private boolean searchRecursivo(Nodo nodo, int valor) {
        // Caso base 1: llegamos a null → el valor NO existe
        if (nodo == null) {
            return false;
        }

        // Caso base 2: encontramos el valor
        if (valor == nodo.valor) {
            return true;
        }

        if (valor < nodo.valor) {
            // El valor es menor → buscar en sub-árbol izquierdo
            return searchRecursivo(nodo.izquierdo, valor);
        } else {
            // El valor es mayor → buscar en sub-árbol derecho
            return searchRecursivo(nodo.derecho, valor);
        }
    }

    // ─────────────────────────────────────────────────────────────────
    //  DELETE — Eliminar un valor (3 casos)
    // ─────────────────────────────────────────────────────────────────

    /**
     * Elimina un valor del árbol considerando los 3 casos críticos:
     *   Caso 1 — Nodo hoja (sin hijos):     se elimina directamente.
     *   Caso 2 — Nodo con un hijo:           se reemplaza por ese hijo.
     *   Caso 3 — Nodo con dos hijos:         se reemplaza con el sucesor
     *             in-order (mínimo del sub-árbol derecho) y se elimina
     *             ese sucesor de su posición original.
     *
     * Complejidad: O(log n) promedio | O(n) peor caso
     *
     * @param valor el entero a eliminar
     */
    public void delete(int valor) {
        raiz = deleteRecursivo(raiz, valor);
    }

    /**
     * Auxiliar recursivo de delete.
     *
     * @param nodo  nodo actual en la recursión
     * @param valor valor a eliminar
     * @return      sub-árbol resultante después de la eliminación
     */
    private Nodo deleteRecursivo(Nodo nodo, int valor) {
        // Caso base: el valor no existe en el árbol
        if (nodo == null) {
            System.out.println("  [AVISO] El valor " + valor + " no existe en el árbol.");
            return null;
        }

        if (valor < nodo.valor) {
            // El valor está en el sub-árbol izquierdo
            nodo.izquierdo = deleteRecursivo(nodo.izquierdo, valor);

        } else if (valor > nodo.valor) {
            // El valor está en el sub-árbol derecho
            nodo.derecho = deleteRecursivo(nodo.derecho, valor);

        } else {
            // ── ENCONTRAMOS el nodo a eliminar ──────────────────────

            // CASO 1: Nodo hoja — sin hijos
            if (nodo.izquierdo == null && nodo.derecho == null) {
                System.out.println("  [DELETE] Caso 1: nodo hoja " + valor + " eliminado.");
                return null;
            }

            // CASO 2a: Solo tiene hijo derecho
            if (nodo.izquierdo == null) {
                System.out.println("  [DELETE] Caso 2: nodo " + valor + " reemplazado por hijo derecho.");
                return nodo.derecho;
            }

            // CASO 2b: Solo tiene hijo izquierdo
            if (nodo.derecho == null) {
                System.out.println("  [DELETE] Caso 2: nodo " + valor + " reemplazado por hijo izquierdo.");
                return nodo.izquierdo;
            }

            // CASO 3: Tiene DOS hijos
            //   → Encontrar el sucesor in-order: mínimo del sub-árbol derecho
            //   → Copiar su valor al nodo actual
            //   → Eliminar el sucesor de su posición original
            Nodo sucesor = encontrarMinimo(nodo.derecho);
            System.out.println("  [DELETE] Caso 3: nodo " + valor
                    + " → sucesor in-order es " + sucesor.valor);
            nodo.valor  = sucesor.valor;
            nodo.derecho = deleteRecursivo(nodo.derecho, sucesor.valor);
        }

        return nodo;
    }

    /**
     * Encuentra el nodo con el valor mínimo en un sub-árbol.
     * El mínimo siempre está en el extremo izquierdo.
     *
     * @param nodo raíz del sub-árbol
     * @return     nodo con el valor mínimo
     */
    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // ─────────────────────────────────────────────────────────────────
    //  RECORRIDOS — InOrder, PreOrder, PostOrder
    // ─────────────────────────────────────────────────────────────────

    /**
     * Recorrido In-Order: Izquierdo → Raíz → Derecho.
     * Produce los valores en orden ASCENDENTE.
     * Uso real: obtener lista ordenada de elementos.
     */
    public void recorridoInOrder() {
        System.out.print("  InOrder  (asc): [ ");
        inOrderRecursivo(raiz);
        System.out.println("]");
    }

    private void inOrderRecursivo(Nodo nodo) {
        if (nodo != null) {
            inOrderRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inOrderRecursivo(nodo.derecho);
        }
    }

    /**
     * Recorrido Pre-Order: Raíz → Izquierdo → Derecho.
     * Visita la raíz PRIMERO antes que sus hijos.
     * Uso real: serializar/copiar la estructura del árbol.
     */
    public void recorridoPreOrder() {
        System.out.print("  PreOrder (raíz): [ ");
        preOrderRecursivo(raiz);
        System.out.println("]");
    }

    private void preOrderRecursivo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preOrderRecursivo(nodo.izquierdo);
            preOrderRecursivo(nodo.derecho);
        }
    }

    /**
     * Recorrido Post-Order: Izquierdo → Derecho → Raíz.
     * Visita la raíz AL FINAL, después de ambos hijos.
     * Uso real: liberar memoria (eliminar árbol desde las hojas).
     */
    public void recorridoPostOrder() {
        System.out.print("  PostOrder(hojas): [ ");
        postOrderRecursivo(raiz);
        System.out.println("]");
    }

    private void postOrderRecursivo(Nodo nodo) {
        if (nodo != null) {
            postOrderRecursivo(nodo.izquierdo);
            postOrderRecursivo(nodo.derecho);
            System.out.print(nodo.valor + " ");
        }
    }

    // ─────────────────────────────────────────────────────────────────
    //  UTILIDADES
    // ─────────────────────────────────────────────────────────────────

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si el árbol no tiene nodos
     */
    public boolean estaVacio() {
        return raiz == null;
    }

    /**
     * Retorna el valor mínimo almacenado en el árbol.
     *
     * @return el menor entero del árbol
     * @throws IllegalStateException si el árbol está vacío
     */
    public int getMinimo() {
        if (estaVacio()) throw new IllegalStateException("El árbol está vacío.");
        return encontrarMinimo(raiz).valor;
    }

    /**
     * Retorna el valor máximo almacenado en el árbol.
     *
     * @return el mayor entero del árbol
     * @throws IllegalStateException si el árbol está vacío
     */
    public int getMaximo() {
        if (estaVacio()) throw new IllegalStateException("El árbol está vacío.");
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.valor;
    }

    /**
     * Calcula la altura del árbol (número de niveles).
     * Un árbol vacío tiene altura 0; un nodo hoja tiene altura 1.
     *
     * @return altura del árbol
     */
    public int getAltura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) return 0;
        int altIzq = alturaRecursiva(nodo.izquierdo);
        int altDer = alturaRecursiva(nodo.derecho);
        return 1 + Math.max(altIzq, altDer);
    }

    /**
     * Cuenta el número total de nodos en el árbol.
     *
     * @return cantidad de elementos almacenados
     */
    public int contarNodos() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + contarRecursivo(nodo.izquierdo) + contarRecursivo(nodo.derecho);
    }

    /**
     * Imprime una representación visual del árbol en consola
     * (rotada 90°: raíz a la izquierda, hojas a la derecha).
     */
    public void imprimirArbol() {
        System.out.println("  Estructura del árbol (raíz izquierda → hojas derecha):");
        imprimirRecursivo(raiz, "", true);
    }

    private void imprimirRecursivo(Nodo nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + nodo.valor);
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");
            imprimirRecursivo(nodo.izquierdo,  nuevoPrefijo, nodo.derecho == null);
            imprimirRecursivo(nodo.derecho,    nuevoPrefijo, true);
        }
    }
}
