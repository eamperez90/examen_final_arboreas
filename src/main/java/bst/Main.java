package bst;

import java.util.Scanner;

public class Main {

    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String GREEN  = "\u001B[32m";
    static final String CYAN   = "\u001B[36m";
    static final String YELLOW = "\u001B[33m";
    static final String RED    = "\u001B[31m";
    static final String BLUE   = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";

    static BST arbol = new BST();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        banner();

        int opcion;
        do {
            menu();
            opcion = leerEntero("  Selecciona una opcion: ");
            System.out.println();

            switch (opcion) {
                case 1: opcionInsertar();     break;
                case 2: opcionBuscar();       break;
                case 3: opcionEliminar();     break;
                case 4: opcionRecorridos();   break;
                case 5: opcionVerArbol();     break;
                case 6: opcionInfo();         break;
                case 7: opcionCargarDemo();   break;
                case 0:
                    System.out.println(CYAN + "  Saliendo... Hasta luego." + RESET);
                    break;
                default:
                    System.out.println(RED + "  Opcion invalida. Intenta de nuevo." + RESET);
            }

        } while (opcion != 0);

        sc.close();
    }

    // ─── OPCIONES ─────────────────────────────────────────────────────────────

    static void opcionInsertar() {
        titulo("INSERTAR VALOR");
        System.out.print(YELLOW + "  Cuantos valores deseas insertar? " + RESET);
        int n = leerEntero("");
        for (int i = 1; i <= n; i++) {
            int val = leerEntero("  Valor " + i + ": ");
            boolean existia = arbol.search(val);
            arbol.insert(val);
            if (existia) {
                System.out.println(RED + "  [!] " + val + " ya existe en el arbol (duplicado ignorado)." + RESET);
            } else {
                System.out.println(GREEN + "  [+] " + val + " insertado. Total nodos: " + arbol.contarNodos() + RESET);
            }
        }
        System.out.println();
        arbol.imprimirArbol();
    }

    static void opcionBuscar() {
        titulo("BUSCAR VALOR");
        int val = leerEntero("  Valor a buscar: ");
        boolean encontrado = arbol.search(val);
        if (encontrado) {
            System.out.println(GREEN + "\n  ENCONTRADO: " + val + " existe en el arbol." + RESET);
        } else {
            System.out.println(RED + "\n  NO EXISTE: " + val + " no esta en el arbol." + RESET);
        }
    }

    static void opcionEliminar() {
        titulo("ELIMINAR VALOR");
        if (arbol.estaVacio()) {
            System.out.println(RED + "  El arbol esta vacio. Inserta valores primero." + RESET);
            return;
        }
        System.out.println("  Arbol actual:");
        arbol.recorridoInOrder();
        System.out.println();
        int val = leerEntero("  Valor a eliminar: ");
        if (!arbol.search(val)) {
            System.out.println(RED + "\n  [!] " + val + " no existe en el arbol." + RESET);
            return;
        }
        arbol.delete(val);
        System.out.println();
        if (arbol.estaVacio()) {
            System.out.println(YELLOW + "  El arbol quedo vacio." + RESET);
        } else {
            arbol.imprimirArbol();
        }
    }

    static void opcionRecorridos() {
        titulo("RECORRIDOS DEL ARBOL");
        if (arbol.estaVacio()) {
            System.out.println(RED + "  El arbol esta vacio. Inserta valores primero." + RESET);
            return;
        }
        System.out.println(CYAN + "  InOrder   (Izq -> Raiz -> Der) = orden ASCENDENTE:" + RESET);
        arbol.recorridoInOrder();
        System.out.println();
        System.out.println(CYAN + "  PreOrder  (Raiz -> Izq -> Der) = copiar arbol:" + RESET);
        arbol.recorridoPreOrder();
        System.out.println();
        System.out.println(CYAN + "  PostOrder (Izq -> Der -> Raiz) = liberar memoria:" + RESET);
        arbol.recorridoPostOrder();
    }

    static void opcionVerArbol() {
        titulo("ESTRUCTURA DEL ARBOL");
        if (arbol.estaVacio()) {
            System.out.println(RED + "  El arbol esta vacio." + RESET);
            return;
        }
        arbol.imprimirArbol();
        System.out.println();
        System.out.printf("  %s%-20s%s -> %s%d%s%n", BOLD, "Total nodos:", RESET, GREEN, arbol.contarNodos(), RESET);
        System.out.printf("  %s%-20s%s -> %s%d%s%n", BOLD, "Altura:",      RESET, GREEN, arbol.getAltura(),   RESET);
        System.out.printf("  %s%-20s%s -> %s%d%s%n", BOLD, "Minimo:",      RESET, GREEN, arbol.getMinimo(),   RESET);
        System.out.printf("  %s%-20s%s -> %s%d%s%n", BOLD, "Maximo:",      RESET, GREEN, arbol.getMaximo(),   RESET);
    }

    static void opcionInfo() {
        titulo("COMPLEJIDAD Big-O -- METODO search()");
        System.out.println(PURPLE);
        System.out.println("  [*] CASO PROMEDIO: O(log n)");
        System.out.println("      Cada comparacion descarta la mitad del arbol.");
        System.out.println("      1,000,000 nodos -> max. 20 comparaciones.");
        System.out.println();
        System.out.println("  [*] PEOR CASO: O(n)");
        System.out.println("      Datos ya ordenados -> arbol degenera a lista.");
        System.out.println("      Hay que recorrer todos los n nodos.");
        System.out.println();
        System.out.println("  [*] SOLUCION: AVL o Red-Black Tree -> O(log n) siempre.");
        System.out.println(RESET);
    }

    static void opcionCargarDemo() {
        titulo("CARGAR DATOS DE DEMO");
        System.out.println(YELLOW + "  Cargando: 45 22 78 11 33 67 89 5 17 28 38 55 72 84 95" + RESET);
        int[] demo = {45, 22, 78, 11, 33, 67, 89, 5, 17, 28, 38, 55, 72, 84, 95};
        int insertados = 0;
        for (int v : demo) {
            if (!arbol.search(v)) {
                arbol.insert(v);
                insertados++;
            }
        }
        System.out.println(GREEN + "  " + insertados + " valores insertados. Total nodos: " + arbol.contarNodos() + RESET);
        System.out.println();
        arbol.imprimirArbol();
    }

    // ─── HELPERS ──────────────────────────────────────────────────────────────

    static int leerEntero(String prompt) {
        while (true) {
            System.out.print(BOLD + prompt + RESET);
            try {
                String linea = sc.nextLine().trim();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
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
        System.out.println(CYAN + "  |        MENU PRINCIPAL    |" + RESET);
        System.out.println(CYAN + "  +--------------------------+" + RESET);
        System.out.println("  " + GREEN  + "[1]" + RESET + " Insertar valor(es)");
        System.out.println("  " + BLUE   + "[2]" + RESET + " Buscar valor");
        System.out.println("  " + RED    + "[3]" + RESET + " Eliminar valor");
        System.out.println("  " + PURPLE + "[4]" + RESET + " Ver recorridos (InOrder / PreOrder / PostOrder)");
        System.out.println("  " + YELLOW + "[5]" + RESET + " Ver estructura del arbol");
        System.out.println("  " + CYAN   + "[6]" + RESET + " Informacion Big-O");
        System.out.println("  " + GREEN  + "[7]" + RESET + " Cargar datos de demo automaticamente");
        System.out.println("  " + BOLD   + "[0]" + RESET + " Salir");
        System.out.println();
        System.out.printf("  Nodos en arbol: %s%d%s%n", GREEN + BOLD, arbol.contarNodos(), RESET);
        System.out.println();
    }

    static void titulo(String texto) {
        System.out.println();
        System.out.println(CYAN + BOLD + "  +-----------------------------------------------------+");
        System.out.printf( "  |  %-52s|%n", texto);
        System.out.println("  +-----------------------------------------------------+" + RESET);
    }
}
