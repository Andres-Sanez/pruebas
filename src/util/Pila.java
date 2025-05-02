package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una pila utilizando una lista enlazada.
 * @param <T> El tipo de elementos que contendrá la pila.
 */
public class Pila<T> implements Iterable<T> {
    
    private Nodo<T> tope;
    private int tamaño;
    
    /**
     * Clase interna para representar los nodos de la pila.
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
     * Constructor de la pila vacía.
     */
    public Pila() {
        tope = null;
        tamaño = 0;
    }
    
    /**
     * Agrega un elemento en el tope de la pila.
     * @param dato El elemento a agregar.
     */
    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamaño++;
    }
    
    /**
     * Elimina y retorna el elemento en el tope de la pila.
     * @return El elemento que estaba en el tope de la pila.
     * @throws NoSuchElementException si la pila está vacía.
     */
    public T desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        
        T dato = tope.dato;
        tope = tope.siguiente;
        tamaño--;
        return dato;
    }
    
    /**
     * Retorna el elemento en el tope de la pila sin eliminarlo.
     * @return El elemento en el tope de la pila.
     * @throws NoSuchElementException si la pila está vacía.
     */
    public T verTope() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        
        return tope.dato;
    }
    
    /**
     * Verifica si la pila está vacía.
     * @return true si la pila está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return tope == null;
    }
    
    /**
     * Devuelve el número de elementos en la pila.
     * @return El número de elementos en la pila.
     */
    public int tamaño() {
        return tamaño;
    }
    
    /**
     * Vacía la pila.
     */
    public void vaciar() {
        tope = null;
        tamaño = 0;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = tope;
            
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
     * Devuelve una representación en cadena de la pila.
     * @return Una representación en cadena de la pila.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        
        Nodo<T> actual = tope;
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
