package modelo;

import util.ListaEnlazada;

/**
 * Clase que representa un carrito de compras.
 */
public class Carrito {
    private ListaEnlazada<ItemCarrito> items;
    private String usuario;
    
    /**
     * Constructor para la clase Carrito.
     * @param usuario Usuario propietario del carrito
     */
    public Carrito(String usuario) {
        this.usuario = usuario;
        this.items = new ListaEnlazada<>();
    }
    
    /**
     * Agrega un producto al carrito.
     * @param producto Producto a agregar
     * @param cantidad Cantidad del producto
     * @return true si se agregó correctamente, false si no hay suficiente stock
     */
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (!producto.hayStock(cantidad)) {
            return false;
        }
        
        // Verifica si el producto ya está en el carrito
        for (int i = 0; i < items.tamaño(); i++) {
            ItemCarrito item = items.obtener(i);
            if (item.getProducto().equals(producto)) {
                // Si el producto ya está, actualiza la cantidad si hay stock
                if (producto.hayStock(item.getCantidad() + cantidad)) {
                    item.setCantidad(item.getCantidad() + cantidad);
                    return true;
                } else {
                    return false;
                }
            }
        }
        
        // Si el producto no está en el carrito, lo agrega
        items.agregar(new ItemCarrito(producto, cantidad));
        return true;
    }
    
    /**
     * Elimina un producto del carrito.
     * @param producto Producto a eliminar
     * @return true si se eliminó correctamente, false si no estaba en el carrito
     */
    public boolean eliminarProducto(Producto producto) {
        for (int i = 0; i < items.tamaño(); i++) {
            ItemCarrito item = items.obtener(i);
            if (item.getProducto().equals(producto)) {
                items.eliminar(item);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Actualiza la cantidad de un producto en el carrito.
     * @param producto Producto a actualizar
     * @param cantidad Nueva cantidad
     * @return true si se actualizó correctamente, false si no hay suficiente stock
     */
    public boolean actualizarCantidad(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            return eliminarProducto(producto);
        }
        
        for (int i = 0; i < items.tamaño(); i++) {
            ItemCarrito item = items.obtener(i);
            if (item.getProducto().equals(producto)) {
                if (producto.hayStock(cantidad)) {
                    item.setCantidad(cantidad);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    
    /**
     * Calcula el subtotal del carrito.
     * @return Subtotal del carrito
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < items.tamaño(); i++) {
            ItemCarrito item = items.obtener(i);
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }
    
    /**
     * Vacía el carrito.
     */
    public void vaciar() {
        items.vaciar();
    }
    
    /**
     * Obtiene la lista de ítems del carrito.
     * @return Lista de ítems del carrito
     */
    public ListaEnlazada<ItemCarrito> getItems() {
        return items;
    }
    
    /**
     * Obtiene el usuario propietario del carrito.
     * @return Usuario propietario del carrito
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Obtiene el número de ítems en el carrito.
     * @return Número de ítems en el carrito
     */
    public int cantidadItems() {
        return items.tamaño();
    }
    
    /**
     * Clase interna que representa un ítem del carrito.
     */
    public static class ItemCarrito {
        private Producto producto;
        private int cantidad;
        
        /**
         * Constructor para la clase ItemCarrito.
         * @param producto Producto
         * @param cantidad Cantidad
         */
        public ItemCarrito(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }
        
        /**
         * Obtiene el producto.
         * @return Producto
         */
        public Producto getProducto() {
            return producto;
        }
        
        /**
         * Obtiene la cantidad.
         * @return Cantidad
         */
        public int getCantidad() {
            return cantidad;
        }
        
        /**
         * Establece la cantidad.
         * @param cantidad Nueva cantidad
         */
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
        
        /**
         * Calcula el subtotal del ítem.
         * @return Subtotal del ítem
         */
        public double getSubtotal() {
            return producto.getPrecio() * cantidad;
        }
        
        @Override
        public String toString() {
            return producto.getNombre() + " x " + cantidad + " = $" + getSubtotal();
        }
    }
}
