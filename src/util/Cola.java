package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una cola utilizando una lista enlazada.
 * @param <T> El tipo de elementos que contendrá la cola.
 */
public class Cola<T> implements Iterable<T> {
    
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamaño;
    
    /**
     * Clase interna para representar los nodos de la cola.
     */
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        
        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
    
    /**
     * Constructor de la cola vacía.
     */
    public Cola() {
        primero = null;
        ultimo = null;
        tamaño = 0;
    }
    
    /**
     * Agrega un elemento al final de la cola.
     * @param dato El elemento a agregar.
     */
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        
        if (estaVacia()) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        
        ultimo = nuevo;
        tamaño++;
    }
    
    /**
     * Elimina y retorna el elemento al inicio de la cola.
     * @return El elemento que estaba al inicio de la cola.
     * @throws NoSuchElementException si la cola está vacía.
     */
    public T desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        
        T dato = primero.dato;
        primero = primero.siguiente;
        tamaño--;
        
        if (primero == null) {
            ultimo = null;
        }
        
        return dato;
    }
    
    /**
     * Retorna el elemento al inicio de la cola sin eliminarlo.
     * @return El elemento al inicio de la cola.
     * @throws NoSuchElementException si la cola está vacía.
     */
    public T verPrimero() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        
        return primero.dato;
    }
    
    /**
     * Verifica si la cola está vacía.
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return primero == null;
    }
    
    /**
     * Devuelve el número de elementos en la cola.
     * @return El número de elementos en la cola.
     */
    public int tamaño() {
        return tamaño;
    }
    
    /**
     * Vacía la cola.
     */
    public void vaciar() {
        primero = null;
        ultimo = null;
        tamaño = 0;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = primero;
            
            @Override
            public boolean hasNext() {
                return actual != null;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
    
    /**
     * Devuelve una representación en cadena de la cola.
     * @return Una representación en cadena de la cola.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        
        Nodo<T> actual = primero;
        while (actual != null) {
            sb.append(actual.dato);
            
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            
            actual = actual.siguiente;
        }
        
        sb.append("]");
        return sb.toString();
    }
}
