package modelo;

import util.Pila;
import util.ListaEnlazada;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa el historial de compras de un usuario.
 */
public class HistorialCompras {
    private Pila<Orden> ordenes;
    private String usuario;
    
    /**
     * Constructor para la clase HistorialCompras.
     * @param usuario Usuario propietario del historial de compras
     */
    public HistorialCompras(String usuario) {
        this.usuario = usuario;
        this.ordenes = new Pila<>();
    }
    
    /**
     * Agrega una orden al historial de compras.
     * @param orden Orden a agregar
     */
    public void agregarOrden(Orden orden) {
        if (orden.getUsuario().equals(usuario)) {
            ordenes.apilar(orden);
        }
    }
    
    /**
     * Crea una nueva orden a partir de un carrito y la agrega al historial.
     * @param carrito Carrito de compras
     * @return La orden creada
     */
    public Orden crearOrden(Carrito carrito) {
        if (carrito.getUsuario().equals(usuario) && carrito.cantidadItems() > 0) {
            Orden nuevaOrden = new Orden(carrito);
            
            // Procesa la orden (reduce stock)
            if (nuevaOrden.procesar()) {
                // Agrega la orden al historial
                ordenes.apilar(nuevaOrden);
                
                // Vacía el carrito
                carrito.vaciar();
                
                return nuevaOrden;
            }
        }
        return null;
    }
    
    /**
     * Obtiene la última orden del historial.
     * @return La última orden del historial o null si no hay órdenes
     */
    public Orden obtenerUltimaOrden() {
        if (!ordenes.estaVacia()) {
            return ordenes.verTope();
        }
        return null;
    }
    
    /**
     * Obtiene todas las órdenes del historial.
     * @return Lista de órdenes del historial
     */
    public ListaEnlazada<Orden> obtenerOrdenes() {
        ListaEnlazada<Orden> listaOrdenes = new ListaEnlazada<>();
        
        // Crea una copia temporal de la pila
        Pila<Orden> pilaTemp = new Pila<>();
        
        // Desapila la pila original y apila en la pila temporal
        while (!ordenes.estaVacia()) {
            Orden orden = ordenes.desapilar();
            listaOrdenes.agregar(orden);
            pilaTemp.apilar(orden);
        }
        
        // Restaura la pila original
        while (!pilaTemp.estaVacia()) {
            ordenes.apilar(pilaTemp.desapilar());
        }
        
        return listaOrdenes;
    }
    
    /**
     * Obtiene las órdenes de una fecha específica.
     * @param año Año
     * @param mes Mes
     * @param dia Día
     * @return Lista de órdenes de la fecha especificada
     */
    public ListaEnlazada<Orden> obtenerOrdenesPorFecha(int año, int mes, int dia) {
        ListaEnlazada<Orden> ordenesDelDia = new ListaEnlazada<>();
        ListaEnlazada<Orden> todasLasOrdenes = obtenerOrdenes();
        
        for (int i = 0; i < todasLasOrdenes.tamaño(); i++) {
            Orden orden = todasLasOrdenes.obtener(i);
            LocalDateTime fecha = orden.getFecha();
            
            if (fecha.getYear() == año && fecha.getMonthValue() == mes && fecha.getDayOfMonth() == dia) {
                ordenesDelDia.agregar(orden);
            }
        }
        
        return ordenesDelDia;
    }
    
    /**
     * Obtiene el usuario propietario del historial de compras.
     * @return Usuario propietario del historial de compras
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Obtiene el número de órdenes en el historial.
     * @return Número de órdenes en el historial
     */
    public int cantidadOrdenes() {
        return ordenes.tamaño();
    }
    
    /**
     * Obtiene la fecha de la última compra formateada.
     * @return Fecha de la última compra formateada o una cadena vacía si no hay compras
     */
    public String obtenerFechaUltimaCompra() {
        Orden ultimaOrden = obtenerUltimaOrden();
        if (ultimaOrden != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
            return ultimaOrden.getFecha().format(formatter);
        }
        return "";
    }
}
