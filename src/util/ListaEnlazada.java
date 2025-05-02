package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una lista enlazada simple genérica.
 * @param <T> El tipo de elementos que contendrá la lista.
 */
public class ListaEnlazada<T> implements Iterable<T> {
    
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamaño;
    
    /**
     * Clase interna para representar los nodos de la lista.
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
     * Constructor de la lista enlazada vacía.
     */
    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        tamaño = 0;
    }
    
    /**
     * Agrega un elemento al final de la lista.
     * @param dato El elemento a agregar.
     */
    public void agregar(T dato) {
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
     * Elimina la primera ocurrencia del elemento especificado.
     * @param dato El elemento a eliminar.
     * @return true si se eliminó el elemento, false si no se encontró.
     */
    public boolean eliminar(T dato) {
        if (estaVacia()) {
            return false;
        }
        
        // Caso especial: el primer elemento es el que queremos eliminar
        if (primero.dato.equals(dato)) {
            primero = primero.siguiente;
            tamaño--;
            
            if (primero == null) {
                ultimo = null;
            }
            
            return true;
        }
        
        // Buscar el elemento a eliminar
        Nodo<T> actual = primero;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(dato)) {
            actual = actual.siguiente;
        }
        
        // Si encontramos el elemento, lo eliminamos
        if (actual.siguiente != null) {
            // Si es el último, actualizamos la referencia
            if (actual.siguiente == ultimo) {
                ultimo = actual;
            }
            
            actual.siguiente = actual.siguiente.siguiente;
            tamaño--;
            return true;
        }
        
        return false;
    }
    
    /**
     * Busca un elemento en la lista.
     * @param dato El elemento a buscar.
     * @return true si el elemento existe en la lista, false en caso contrario.
     */
    public boolean contiene(T dato) {
        Nodo<T> actual = primero;
        
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        
        return false;
    }
    
    /**
     * Devuelve el elemento en la posición especificada.
     * @param indice La posición del elemento a devolver.
     * @return El elemento en la posición especificada.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango.
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        
        Nodo<T> actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        
        return actual.dato;
    }
    
    /**
     * Verifica si la lista está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return primero == null;
    }
    
    /**
     * Devuelve el número de elementos en la lista.
     * @return El número de elementos en la lista.
     */
    public int tamaño() {
        return tamaño;
    }
    
    /**
     * Vacía la lista.
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
     * Devuelve una representación en cadena de la lista.
     * @return Una representación en cadena de la lista.
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
