package bst;

import java.util.Scanner;

public class Main {

    // Colores ANSI
    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String GREEN  = "\u001B[32m";
    static final String CYAN   = "\u001B[36m";
    static final String YELLOW = "\u001B[33m";
    static final String RED    = "\u001B[31m";
    static final String BLUE   = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";

    // Nodo interno
    static class Nodo {
        int valor;
        Nodo izquierdo, derecho;
        Nodo(int v) { this.valor = v; }
    }

    static Nodo raiz = null;
    static Scanner sc = new Scanner(System.in);

    // =========================================================
    //  MAIN
    // =========================================================
    public static void main(String[] args) {
        banner();
        int op;
        do {
            menu();
            op = leerEntero("  Selecciona una opcion: ");
            System.out.println();
            switch (op) {
                case 1: insertar();   break;
                case 2: buscar();     break;
                case 3: eliminar();   break;
                case 4: recorridos(); break;
                case 5: verArbol();   break;
                case 6: bigO();       break;
                case 7: demo();       break;
                case 0: System.out.println(CYAN + "  Hasta luego." + RESET); break;
                default: System.out.println(RED + "  Opcion invalida. Intenta de nuevo." + RESET);
            }
        } while (op != 0);
        sc.close();
    }

    // =========================================================
    //  INSERT
    // =========================================================
    static void insert(int valor) {
        raiz = insertRec(raiz, valor);
    }
    static Nodo insertRec(Nodo n, int v) {
        if (n == null) return new Nodo(v);
        if (v < n.valor)       n.izquierdo = insertRec(n.izquierdo, v);
        else if (v > n.valor)  n.derecho   = insertRec(n.derecho, v);
        return n;
    }

    // =========================================================
    //  SEARCH
    // =========================================================
    static boolean search(int valor) {
        return searchRec(raiz, valor);
    }
    static boolean searchRec(Nodo n, int v) {
        if (n == null)       return false;
        if (v == n.valor)    return true;
        if (v < n.valor)     return searchRec(n.izquierdo, v);
        return searchRec(n.derecho, v);
    }

    // =========================================================
    //  DELETE — 3 casos
    // =========================================================
    static void delete(int valor) {
        raiz = deleteRec(raiz, valor);
    }
    static Nodo deleteRec(Nodo n, int v) {
        if (n == null) {
            System.out.println(RED + "  [!] " + v + " no existe en el arbol." + RESET);
            return null;
        }
        if (v < n.valor) {
            n.izquierdo = deleteRec(n.izquierdo, v);
        } else if (v > n.valor) {
            n.derecho = deleteRec(n.derecho, v);
        } else {
            // Caso 1: nodo hoja
            if (n.izquierdo == null && n.derecho == null) {
                System.out.println(GREEN + "  [Caso 1] Nodo hoja " + v + " eliminado." + RESET);
                return null;
            }
            // Caso 2: un solo hijo
            if (n.izquierdo == null) {
                System.out.println(GREEN + "  [Caso 2] " + v + " reemplazado por hijo derecho." + RESET);
                return n.derecho;
            }
            if (n.derecho == null) {
                System.out.println(GREEN + "  [Caso 2] " + v + " reemplazado por hijo izquierdo." + RESET);
                return n.izquierdo;
            }
            // Caso 3: dos hijos — sucesor in-order
            Nodo suc = minNodo(n.derecho);
            System.out.println(GREEN + "  [Caso 3] " + v + " -> sucesor in-order = " + suc.valor + RESET);
            n.valor  = suc.valor;
            n.derecho = deleteRec(n.derecho, suc.valor);
        }
        return n;
    }
    static Nodo minNodo(Nodo n) {
        while (n.izquierdo != null) n = n.izquierdo;
        return n;
    }

    // =========================================================
    //  RECORRIDOS
    // =========================================================
    static void recorridoInOrder() {
        System.out.print("  InOrder   (asc):   [ ");
        inOrder(raiz);
        System.out.println("]");
    }
    static void inOrder(Nodo n) {
        if (n != null) { inOrder(n.izquierdo); System.out.print(n.valor + " "); inOrder(n.derecho); }
    }

    static void recorridoPreOrder() {
        System.out.print("  PreOrder  (raiz):  [ ");
        preOrder(raiz);
        System.out.println("]");
    }
    static void preOrder(Nodo n) {
        if (n != null) { System.out.print(n.valor + " "); preOrder(n.izquierdo); preOrder(n.derecho); }
    }

    static void recorridoPostOrder() {
        System.out.print("  PostOrder (hojas): [ ");
        postOrder(raiz);
        System.out.println("]");
    }
    static void postOrder(Nodo n) {
        if (n != null) { postOrder(n.izquierdo); postOrder(n.derecho); System.out.print(n.valor + " "); }
    }

    // =========================================================
    //  UTILIDADES
    // =========================================================
    static boolean estaVacio()  { return raiz == null; }
    static int contarNodos()    { return contarRec(raiz); }
    static int contarRec(Nodo n){ return n == null ? 0 : 1 + contarRec(n.izquierdo) + contarRec(n.derecho); }
    static int getAltura()      { return alturaRec(raiz); }
    static int alturaRec(Nodo n){ return n == null ? 0 : 1 + Math.max(alturaRec(n.izquierdo), alturaRec(n.derecho)); }
    static int getMinimo()      { return minNodo(raiz).valor; }
    static int getMaximo() {
        Nodo a = raiz;
        while (a.derecho != null) a = a.derecho;
        return a.valor;
    }

    static void imprimirArbol() {
        System.out.println("  Estructura del arbol:");
        imprimirRec(raiz, "", true);
    }
    static void imprimirRec(Nodo n, String pre, boolean ultimo) {
        if (n != null) {
            System.out.println(pre + (ultimo ? "L-- " : "|-- ") + n.valor);
            String np = pre + (ultimo ? "    " : "|   ");
            imprimirRec(n.izquierdo, np, n.derecho == null);
            imprimirRec(n.derecho,   np, true);
        }
    }

    // =========================================================
    //  OPCIONES DEL MENU
    // =========================================================
    static void insertar() {
        titulo("INSERTAR VALOR(ES)");
        int n = leerEntero("  Cuantos valores vas a ingresar? ");
        for (int i = 1; i <= n; i++) {
            int val = leerEntero("  Valor " + i + ": ");
            if (search(val)) {
                System.out.println(YELLOW + "  [!] " + val + " ya existe (duplicado ignorado)." + RESET);
            } else {
                insert(val);
                System.out.println(GREEN + "  [+] " + val + " insertado. Total: " + contarNodos() + " nodo(s)." + RESET);
            }
        }
        System.out.println();
        if (!estaVacio()) imprimirArbol();
    }

    static void buscar() {
        titulo("BUSCAR VALOR");
        if (estaVacio()) { System.out.println(RED + "  El arbol esta vacio. Inserta valores primero." + RESET); return; }
        int val = leerEntero("  Valor a buscar: ");
        if (search(val))
            System.out.println(GREEN + "\n  ENCONTRADO: " + val + " existe en el arbol." + RESET);
        else
            System.out.println(RED   + "\n  NO EXISTE:  " + val + " no esta en el arbol." + RESET);
    }

    static void eliminar() {
        titulo("ELIMINAR VALOR");
        if (estaVacio()) { System.out.println(RED + "  El arbol esta vacio. Inserta valores primero." + RESET); return; }
        System.out.println("  Arbol actual:");
        recorridoInOrder();
        System.out.println();
        int val = leerEntero("  Valor a eliminar: ");
        System.out.println();
        delete(val);
        System.out.println();
        if (!estaVacio()) imprimirArbol();
        else System.out.println(YELLOW + "  El arbol quedo vacio." + RESET);
    }

    static void recorridos() {
        titulo("RECORRIDOS DEL ARBOL");
        if (estaVacio()) { System.out.println(RED + "  El arbol esta vacio. Inserta valores primero." + RESET); return; }
        System.out.println(CYAN + "  InOrder   = orden ascendente:" + RESET);
        recorridoInOrder();
        System.out.println();
        System.out.println(CYAN + "  PreOrder  = raiz primero (util para copiar el arbol):" + RESET);
        recorridoPreOrder();
        System.out.println();
        System.out.println(CYAN + "  PostOrder = raiz al final (util para liberar memoria):" + RESET);
        recorridoPostOrder();
    }

    static void verArbol() {
        titulo("ESTRUCTURA DEL ARBOL");
        if (estaVacio()) { System.out.println(RED + "  El arbol esta vacio. Inserta valores primero." + RESET); return; }
        imprimirArbol();
        System.out.println();
        System.out.printf("  %s%-18s%s -> %s%d%s%n", BOLD, "Total nodos:", RESET, GREEN, contarNodos(), RESET);
        System.out.printf("  %s%-18s%s -> %s%d%s%n", BOLD, "Altura:",      RESET, GREEN, getAltura(),   RESET);
        System.out.printf("  %s%-18s%s -> %s%d%s%n", BOLD, "Minimo:",      RESET, GREEN, getMinimo(),   RESET);
        System.out.printf("  %s%-18s%s -> %s%d%s%n", BOLD, "Maximo:",      RESET, GREEN, getMaximo(),   RESET);
    }

    static void bigO() {
        titulo("COMPLEJIDAD Big-O -- METODO search()");
        System.out.println(PURPLE);
        System.out.println("  [*] CASO PROMEDIO: O(log n)");
        System.out.println("      Cada comparacion descarta la mitad del arbol.");
        System.out.println("      1,000,000 nodos -> max. 20 comparaciones.");
        System.out.println();
        System.out.println("  [*] PEOR CASO: O(n)");
        System.out.println("      Datos ordenados -> arbol degenera a lista enlazada.");
        System.out.println("      Hay que recorrer todos los n nodos.");
        System.out.println();
        System.out.println("  [*] SOLUCION: AVL o Red-Black Tree -> O(log n) siempre.");
        System.out.println(RESET);
    }

    static void demo() {
        titulo("CARGAR DATOS DE DEMO");
        int[] vals = {45, 22, 78, 11, 33, 67, 89, 5, 17, 28, 38, 55, 72, 84, 95};
        System.out.println(YELLOW + "  Insertando: 45 22 78 11 33 67 89 5 17 28 38 55 72 84 95" + RESET);
        int cnt = 0;
        for (int v : vals) {
            if (!search(v)) { insert(v); cnt++; }
        }
        System.out.println(GREEN + "  " + cnt + " valores insertados. Total: " + contarNodos() + " nodos." + RESET);
        System.out.println();
        imprimirArbol();
        System.out.println();
        recorridoInOrder();
        recorridoPreOrder();
        recorridoPostOrder();
    }

    // =========================================================
    //  HELPERS
    // =========================================================
    static int leerEntero(String prompt) {
        while (true) {
            System.out.print(BOLD + prompt + RESET);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) {
                System.out.println(RED + "  [!] Ingresa solo numeros enteros." + RESET);
            }
        }
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

    static void menu() {
        System.out.println(CYAN + "  +--------------------------+" + RESET);
        System.out.println(CYAN + "  |      MENU PRINCIPAL      |" + RESET);
        System.out.println(CYAN + "  +--------------------------+" + RESET);
        System.out.println("  " + GREEN  + "[1]" + RESET + " Insertar valor(es)");
        System.out.println("  " + BLUE   + "[2]" + RESET + " Buscar valor");
        System.out.println("  " + RED    + "[3]" + RESET + " Eliminar valor");
        System.out.println("  " + PURPLE + "[4]" + RESET + " Ver recorridos (InOrder/PreOrder/PostOrder)");
        System.out.println("  " + YELLOW + "[5]" + RESET + " Ver estructura del arbol");
        System.out.println("  " + CYAN   + "[6]" + RESET + " Informacion Big-O");
        System.out.println("  " + GREEN  + "[7]" + RESET + " Cargar datos de demo");
        System.out.println("  " + BOLD   + "[0]" + RESET + " Salir");
        System.out.println();
        System.out.printf("  Nodos en arbol: %s%s%d%s%n", GREEN, BOLD, contarNodos(), RESET);
        System.out.println();
    }

    static void titulo(String t) {
        System.out.println();
        System.out.println(CYAN + BOLD + "  +-----------------------------------------------------+");
        System.out.printf( "  |  %-52s|%n", t);
        System.out.println("  +-----------------------------------------------------+" + RESET);
    }
}
