package bst;

/**
 * Nodo individual del Árbol Binario de Búsqueda.
 * Almacena un valor entero y referencias al hijo izquierdo y derecho.
 *
 * @author  Estudiante — Universidad Da Vinci de Guatemala
 * @course  Estructuras de Datos
 */
public class Nodo {

    /** Valor almacenado en este nodo. */
    int valor;

    /** Referencia al hijo izquierdo (valores menores). */
    Nodo izquierdo;

    /** Referencia al hijo derecho (valores mayores). */
    Nodo derecho;

    /**
     * Constructor: crea un nodo hoja con el valor dado.
     *
     * @param valor el entero a almacenar en este nodo
     */
    public Nodo(int valor) {
        this.valor     = valor;
        this.izquierdo = null;
        this.derecho   = null;
    }
}
