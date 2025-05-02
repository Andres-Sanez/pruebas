package modelo;

import util.ListaEnlazada;

/**
 * Clase que representa una lista de deseos.
 */
public class ListaDeseos {
    private ListaEnlazada<Producto> productos;
    private String usuario;
    
    /**
     * Constructor para la clase ListaDeseos.
     * @param usuario Usuario propietario de la lista de deseos
     */
    public ListaDeseos(String usuario) {
        this.usuario = usuario;
        this.productos = new ListaEnlazada<>();
    }
    
    /**
     * Agrega un producto a la lista de deseos.
     * @param producto Producto a agregar
     * @return true si se agregó correctamente, false si ya estaba en la lista
     */
    public boolean agregarProducto(Producto producto) {
        if (!contiene(producto)) {
            productos.agregar(producto);
            return true;
        }
        return false;
    }
    
    /**
     * Elimina un producto de la lista de deseos.
     * @param producto Producto a eliminar
     * @return true si se eliminó correctamente, false si no estaba en la lista
     */
    public boolean eliminarProducto(Producto producto) {
        return productos.eliminar(producto);
    }
    
    /**
     * Verifica si un producto está en la lista de deseos.
     * @param producto Producto a verificar
     * @return true si el producto está en la lista, false en caso contrario
     */
    public boolean contiene(Producto producto) {
        return productos.contiene(producto);
    }
    
    /**
     * Obtiene la lista de productos.
     * @return Lista de productos
     */
    public ListaEnlazada<Producto> getProductos() {
        return productos;
    }
    
    /**
     * Obtiene el usuario propietario de la lista de deseos.
     * @return Usuario propietario de la lista de deseos
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Obtiene el número de productos en la lista de deseos.
     * @return Número de productos en la lista de deseos
     */
    public int cantidadProductos() {
        return productos.tamaño();
    }
    
    /**
     * Vacía la lista de deseos.
     */
    public void vaciar() {
        productos.vaciar();
    }
    
    /**
     * Mueve un producto de la lista de deseos al carrito.
     * @param producto Producto a mover
     * @param carrito Carrito de compras
     * @param cantidad Cantidad a agregar al carrito
     * @return true si se movió correctamente, false en caso contrario
     */
    public boolean moverAlCarrito(Producto producto, Carrito carrito, int cantidad) {
        if (contiene(producto) && carrito.agregarProducto(producto, cantidad)) {
            return eliminarProducto(producto);
        }
        return false;
    }
}