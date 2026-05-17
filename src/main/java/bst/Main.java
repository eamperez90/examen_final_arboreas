package bst;

/**
 * ============================================================
 *  DEMOSTRACIÓN COMPLETA — Árbol Binario de Búsqueda (BST)
 * ============================================================
 *  Universidad Da Vinci de Guatemala
 *  Curso: Estructuras de Datos
 *
 *  Este archivo ejecuta todos los requisitos del proyecto:
 *    ✅ Inserción de al menos 5 valores
 *    ✅ Búsqueda (retorna true/false)
 *    ✅ Eliminación (los 3 casos)
 *    ✅ Recorridos InOrder, PreOrder, PostOrder
 *    ✅ Visualización de la estructura del árbol
 * ============================================================
 */
public class Main {

    // ── Constantes de formato ────────────────────────────────────────────────
    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String GREEN  = "\u001B[32m";
    static final String CYAN   = "\u001B[36m";
    static final String YELLOW = "\u001B[33m";
    static final String RED    = "\u001B[31m";
    static final String BLUE   = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {

        banner();

        BST arbol = new BST();

        // ── FASE 1: Inserción ────────────────────────────────────────────────
        titulo("FASE 1 — INSERCIÓN DE VALORES");

        int[] valores = {45, 22, 78, 11, 33, 67, 89, 5, 17, 28, 38, 55, 72, 84, 95};
        System.out.println(CYAN + "  Insertando: " + RESET);
        for (int v : valores) {
            arbol.insert(v);
            System.out.printf("    insert(%3d) → árbol con %2d nodo(s)%n",
                    v, arbol.contarNodos());
        }

        System.out.println();
        arbol.imprimirArbol();
        info("Altura del árbol", arbol.getAltura());
        info("Total de nodos",   arbol.contarNodos());
        info("Mínimo",           arbol.getMinimo());
        info("Máximo",           arbol.getMaximo());

        // ── FASE 2: Búsqueda ─────────────────────────────────────────────────
        titulo("FASE 2 — BÚSQUEDA (search)");

        int[] buscar = {33, 72, 100, 5, 60, 95};
        for (int v : buscar) {
            boolean encontrado = arbol.search(v);
            String  marca      = encontrado ? GREEN + "✔ ENCONTRADO" : RED + "✘ NO EXISTE";
            System.out.printf("  search(%3d) → %s%s%n", v, marca, RESET);
        }

        // ── FASE 3: Recorridos ───────────────────────────────────────────────
        titulo("FASE 3 — RECORRIDOS DEL ÁRBOL");
        System.out.println(YELLOW + "  Explicación de cada recorrido:" + RESET);
        System.out.println("  • InOrder   → Izq → Raíz → Der   (resultado: orden ASCENDENTE)");
        System.out.println("  • PreOrder  → Raíz → Izq → Der   (útil para COPIAR el árbol)");
        System.out.println("  • PostOrder → Izq → Der → Raíz   (útil para LIBERAR memoria)");
        System.out.println();
        arbol.recorridoInOrder();
        arbol.recorridoPreOrder();
        arbol.recorridoPostOrder();

        // ── FASE 4: Eliminación ──────────────────────────────────────────────
        titulo("FASE 4 — ELIMINACIÓN (los 3 casos)");

        // CASO 1: Nodo hoja (sin hijos)
        subtitulo("Caso 1 — Nodo hoja (sin hijos): eliminar 5");
        arbol.delete(5);
        arbol.recorridoInOrder();

        // CASO 2: Nodo con un solo hijo
        // Eliminamos 84 primero (hoja) para que 89 quede con un solo hijo (95)
        arbol.delete(84);
        subtitulo("Caso 2 — Nodo con un hijo: eliminar 89 (solo tiene hijo derecho: 95)");
        arbol.delete(89);
        arbol.recorridoInOrder();

        // CASO 3: Nodo con dos hijos
        subtitulo("Caso 3 — Nodo con dos hijos: eliminar 22");
        System.out.println("  → Sucesor in-order de 22 = mínimo del sub-árbol derecho");
        arbol.delete(22);
        arbol.recorridoInOrder();

        // Eliminación adicional: nodo inexistente
        subtitulo("Intento de eliminar valor que NO existe: 999");
        arbol.delete(999);

        // ── Estado final ─────────────────────────────────────────────────────
        titulo("ESTADO FINAL DEL ÁRBOL");
        arbol.imprimirArbol();
        System.out.println();
        arbol.recorridoInOrder();
        arbol.recorridoPreOrder();
        arbol.recorridoPostOrder();
        info("Nodos restantes", arbol.contarNodos());
        info("Altura actual",   arbol.getAltura());

        // ── Explicación Big-O ────────────────────────────────────────────────
        titulo("COMPLEJIDAD Big-O — MÉTODO search()");
        System.out.println(PURPLE + """
                  ANÁLISIS:
                  ─────────────────────────────────────────────────────────────
                  • CASO PROMEDIO: O(log n)
                    En un BST balanceado, cada comparación descarta la mitad
                    del árbol restante. Con n nodos y altura h ≈ log₂(n),
                    el número máximo de comparaciones es log₂(n).
                    Ejemplo: 1,000,000 nodos → máx. 20 comparaciones.

                  • PEOR CASO: O(n)
                    Si el árbol está completamente desbalanceado (todos los
                    nodos en la misma dirección, como con datos ya ordenados:
                    insert(1), insert(2), insert(3)...), degenera a lista
                    enlazada y la búsqueda requiere recorrer todos los n nodos.

                  • SOLUCIÓN: Usar variantes auto-balanceadas (AVL, Red-Black)
                    garantizan O(log n) en TODOS los casos.
                  ─────────────────────────────────────────────────────────────
                """ + RESET);

        footer();
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  HELPERS DE FORMATO
    // ─────────────────────────────────────────────────────────────────────────

    static void banner() {
        System.out.println(BLUE + BOLD);
        System.out.println("  ╔══════════════════════════════════════════════════════════╗");
        System.out.println("  ║       ÁRBOL BINARIO DE BÚSQUEDA (BST) — JAVA            ║");
        System.out.println("  ║   Universidad Da Vinci de Guatemala                     ║");
        System.out.println("  ║   Curso: Estructuras de Datos                           ║");
        System.out.println("  ╚══════════════════════════════════════════════════════════╝");
        System.out.println(RESET);
    }

    static void titulo(String texto) {
        System.out.println();
        System.out.println(CYAN + BOLD
                + "  ┌─────────────────────────────────────────────────────┐");
        System.out.printf("  │  %-52s│%n", texto);
        System.out.println("  └─────────────────────────────────────────────────────┘"
                + RESET);
    }

    static void subtitulo(String texto) {
        System.out.println(YELLOW + "\n  ▶  " + texto + RESET);
    }

    static void info(String label, int valor) {
        System.out.printf("  %s%-22s%s → %s%d%s%n",
                BOLD, label + ":", RESET, GREEN, valor, RESET);
    }

    static void footer() {
        System.out.println(BLUE
                + "  ════════════════════════════════════════════════════════"
                + RESET);
        System.out.println(CYAN + "  Fin de la demostración — Estructuras de Datos" + RESET);
        System.out.println();
    }
}
