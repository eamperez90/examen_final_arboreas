package bst;

/**
 * ============================================================
 *  PRUEBAS UNITARIAS — BST (sin framework externo)
 * ============================================================
 *  Valida todos los métodos requeridos del proyecto.
 *  Ejecutar: javac + java BSTTest desde src/test/java/bst/
 * ============================================================
 */
public class BSTTest {

    static int pruebas = 0;
    static int exitosas = 0;
    static int fallidas = 0;

    public static void main(String[] args) {
        System.out.println("\n  ═══════════════════════════════════════");
        System.out.println("   SUITE DE PRUEBAS — BST");
        System.out.println("  ═══════════════════════════════════════\n");

        testInsertYSearch();
        testDeleteCaso1_NodoHoja();
        testDeleteCaso2_UnHijo();
        testDeleteCaso3_DosHijos();
        testDeleteNoExiste();
        testArbolVacio();
        testDuplicados();
        testUtilidades();

        resumen();
    }

    // ─── Tests ────────────────────────────────────────────────────────────────

    static void testInsertYSearch() {
        grupo("INSERT y SEARCH");
        BST b = new BST();
        b.insert(50); b.insert(30); b.insert(70);
        b.insert(20); b.insert(40);

        ok("search(50) == true",  b.search(50));
        ok("search(30) == true",  b.search(30));
        ok("search(20) == true",  b.search(20));
        ok("search(99) == false", !b.search(99));
        ok("search(0)  == false", !b.search(0));
    }

    static void testDeleteCaso1_NodoHoja() {
        grupo("DELETE — Caso 1: Nodo hoja");
        BST b = new BST();
        b.insert(50); b.insert(30); b.insert(70);

        b.delete(30);
        ok("30 eliminado (nodo hoja)",    !b.search(30));
        ok("50 sigue existiendo",          b.search(50));
        ok("70 sigue existiendo",          b.search(70));
        ok("Nodos restantes = 2",         b.contarNodos() == 2);
    }

    static void testDeleteCaso2_UnHijo() {
        grupo("DELETE — Caso 2: Un hijo");
        BST b = new BST();
        b.insert(50); b.insert(30); b.insert(20); // 30 solo tiene hijo izquierdo (20)

        b.delete(30);
        ok("30 eliminado",                !b.search(30));
        ok("20 sigue (hijo adoptado)",     b.search(20));
        ok("50 sigue existiendo",          b.search(50));
        ok("Nodos restantes = 2",         b.contarNodos() == 2);

        // Caso 2b: solo hijo derecho
        BST b2 = new BST();
        b2.insert(50); b2.insert(30); b2.insert(40); // 30 solo tiene hijo derecho (40)
        b2.delete(30);
        ok("30 eliminado (hijo derecho)", !b2.search(30));
        ok("40 adoptado por 50",           b2.search(40));
    }

    static void testDeleteCaso3_DosHijos() {
        grupo("DELETE — Caso 3: Dos hijos");
        BST b = new BST();
        b.insert(50); b.insert(30); b.insert(70);
        b.insert(20); b.insert(40); b.insert(60); b.insert(80);

        // Eliminar 30 (tiene hijos 20 y 40; sucesor in-order = 40)
        b.delete(30);
        ok("30 eliminado",                !b.search(30));
        ok("20 sigue existiendo",          b.search(20));
        ok("40 sigue existiendo",          b.search(40));
        ok("50 sigue existiendo",          b.search(50));
        ok("Propiedad BST mantenida",      b.search(40) && !b.search(30));
        ok("Nodos = 6 tras eliminar 30",  b.contarNodos() == 6);

        // Eliminar raíz
        b.delete(50);
        ok("Raíz 50 eliminada",           !b.search(50));
        ok("Árbol sigue siendo válido",    b.search(40) && b.search(70));
    }

    static void testDeleteNoExiste() {
        grupo("DELETE — Valor inexistente");
        BST b = new BST();
        b.insert(10); b.insert(20);

        b.delete(999);  // no debe lanzar excepción
        ok("Árbol intacto tras delete(999)", b.contarNodos() == 2);
        ok("10 sigue existiendo",             b.search(10));
    }

    static void testArbolVacio() {
        grupo("Árbol VACÍO");
        BST b = new BST();
        ok("estaVacio() == true",  b.estaVacio());
        ok("search en vacío",     !b.search(5));
        ok("contarNodos() == 0",   b.contarNodos() == 0);
        ok("getAltura() == 0",     b.getAltura() == 0);

        b.delete(5); // no debe lanzar excepción
        ok("delete en vacío no lanza",  true);
    }

    static void testDuplicados() {
        grupo("Duplicados");
        BST b = new BST();
        b.insert(10); b.insert(10); b.insert(10);
        ok("Solo 1 nodo tras 3 insert(10)", b.contarNodos() == 1);
    }

    static void testUtilidades() {
        grupo("Utilidades (getMinimo, getMaximo, getAltura)");
        BST b = new BST();
        b.insert(50); b.insert(30); b.insert(70);
        b.insert(20); b.insert(80);

        ok("getMinimo() == 20",  b.getMinimo() == 20);
        ok("getMaximo() == 80",  b.getMaximo() == 80);
        ok("getAltura() == 3",   b.getAltura() == 3);
        ok("contarNodos() == 5", b.contarNodos() == 5);
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    static void grupo(String nombre) {
        System.out.println("  ── " + nombre + " ──");
    }

    static void ok(String descripcion, boolean condicion) {
        pruebas++;
        if (condicion) {
            exitosas++;
            System.out.println("    ✔ " + descripcion);
        } else {
            fallidas++;
            System.out.println("    ✘ FALLA: " + descripcion);
        }
    }

    static void resumen() {
        System.out.println();
        System.out.println("  ═══════════════════════════════════════");
        System.out.printf ("   Total: %d  |  ✔ %d  |  ✘ %d%n",
                pruebas, exitosas, fallidas);
        System.out.println("  ═══════════════════════════════════════\n");
        if (fallidas == 0) {
            System.out.println("  ✅  Todas las pruebas pasaron correctamente.\n");
        } else {
            System.out.println("  ⚠️  Hay pruebas fallidas. Revisar implementación.\n");
        }
    }
}
