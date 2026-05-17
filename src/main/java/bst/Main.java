package bst;

public class Main {

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

        // FASE 1: Insercion
        titulo("FASE 1 -- INSERCION DE VALORES");

        int[] valores = {45, 22, 78, 11, 33, 67, 89, 5, 17, 28, 38, 55, 72, 84, 95};
        System.out.println(CYAN + "  Insertando:" + RESET);
        for (int v : valores) {
            arbol.insert(v);
            System.out.printf("    insert(%3d) -> arbol con %2d nodo(s)%n",
                    v, arbol.contarNodos());
        }

        System.out.println();
        arbol.imprimirArbol();
        info("Altura del arbol", arbol.getAltura());
        info("Total de nodos",   arbol.contarNodos());
        info("Minimo",           arbol.getMinimo());
        info("Maximo",           arbol.getMaximo());

        // FASE 2: Busqueda
        titulo("FASE 2 -- BUSQUEDA (search)");

        int[] buscar = {33, 72, 100, 5, 60, 95};
        for (int v : buscar) {
            boolean encontrado = arbol.search(v);
            String marca = encontrado ? GREEN + "ENCONTRADO" : RED + "NO EXISTE";
            System.out.printf("  search(%3d) -> %s%s%n", v, marca, RESET);
        }

        // FASE 3: Recorridos
        titulo("FASE 3 -- RECORRIDOS DEL ARBOL");
        System.out.println(YELLOW + "  Explicacion:" + RESET);
        System.out.println("  InOrder   -> Izq -> Raiz -> Der  (orden ASCENDENTE)");
        System.out.println("  PreOrder  -> Raiz -> Izq -> Der  (copiar arbol)");
        System.out.println("  PostOrder -> Izq -> Der -> Raiz  (liberar memoria)");
        System.out.println();
        arbol.recorridoInOrder();
        arbol.recorridoPreOrder();
        arbol.recorridoPostOrder();

        // FASE 4: Eliminacion
        titulo("FASE 4 -- ELIMINACION (los 3 casos)");

        subtitulo("Caso 1 -- Nodo hoja (sin hijos): eliminar 5");
        arbol.delete(5);
        arbol.recorridoInOrder();

        arbol.delete(84);
        subtitulo("Caso 2 -- Nodo con un hijo: eliminar 89 (hijo derecho: 95)");
        arbol.delete(89);
        arbol.recorridoInOrder();

        subtitulo("Caso 3 -- Nodo con dos hijos: eliminar 22");
        System.out.println("  -> Sucesor in-order de 22 = minimo del sub-arbol derecho");
        arbol.delete(22);
        arbol.recorridoInOrder();

        subtitulo("Intento de eliminar valor que NO existe: 999");
        arbol.delete(999);

        // Estado final
        titulo("ESTADO FINAL DEL ARBOL");
        arbol.imprimirArbol();
        System.out.println();
        arbol.recorridoInOrder();
        arbol.recorridoPreOrder();
        arbol.recorridoPostOrder();
        info("Nodos restantes", arbol.contarNodos());
        info("Altura actual",   arbol.getAltura());

        // Complejidad Big-O
        titulo("COMPLEJIDAD Big-O -- METODO search()");
        System.out.println(PURPLE);
        System.out.println("  ANALISIS:");
        System.out.println("  ----------------------------------------------------------");
        System.out.println("  [*] CASO PROMEDIO: O(log n)");
        System.out.println("      En un BST balanceado, cada comparacion descarta");
        System.out.println("      la mitad del arbol restante. Con altura h ~ log2(n):");
        System.out.println("      1,000,000 nodos -> max. 20 comparaciones.");
        System.out.println();
        System.out.println("  [*] PEOR CASO: O(n)");
        System.out.println("      Datos ya ordenados -> arbol degenera a lista enlazada.");
        System.out.println("      Hay que recorrer todos los n nodos.");
        System.out.println();
        System.out.println("  [*] SOLUCION: Usar AVL o Red-Black Tree.");
        System.out.println("      Garantizan O(log n) en TODOS los casos.");
        System.out.println("  ----------------------------------------------------------");
        System.out.println(RESET);

        footer();
    }

    static void banner() {
        System.out.println(BLUE + BOLD);
        System.out.println("  +----------------------------------------------------------+");
        System.out.println("  |     ARBOL BINARIO DE BUSQUEDA (BST) - JAVA               |");
        System.out.println("  |     Universidad Da Vinci de Guatemala                    |");
        System.out.println("  |     Curso: Estructuras de Datos                          |");
        System.out.println("  +----------------------------------------------------------+");
        System.out.println(RESET);
    }

    static void titulo(String texto) {
        System.out.println();
        System.out.println(CYAN + BOLD + "  +-----------------------------------------------------+");
        System.out.printf( "  |  %-52s|%n", texto);
        System.out.println("  +-----------------------------------------------------+" + RESET);
    }

    static void subtitulo(String texto) {
        System.out.println(YELLOW + "\n  >> " + texto + RESET);
    }

    static void info(String label, int valor) {
        System.out.printf("  %s%-22s%s -> %s%d%s%n",
                BOLD, label + ":", RESET, GREEN, valor, RESET);
    }

    static void footer() {
        System.out.println(BLUE + "  ==========================================================" + RESET);
        System.out.println(CYAN + "  Fin de la demostracion -- Estructuras de Datos" + RESET);
        System.out.println();
    }
}
