package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import util.ListaEnlazada;

/**
 * Clase que representa una orden de compra.
 */
public class Orden {
    private int id;
    private String usuario;
    private ListaEnlazada<DetalleOrden> detalles;
    private LocalDateTime fecha;
    private EstadoOrden estado;
    private static int contadorId = 1;
    
    /**
     * Enumeración para los estados posibles de una orden.
     */
    public enum EstadoOrden {
        PENDIENTE("Pendiente"),
        PROCESANDO("Procesando"),
        ENVIADO("Enviado"),
        ENTREGADO("Entregado"),
        CANCELADO("Cancelado"),
        COMPLETADO("Completed");
        
        private final String texto;
        
        EstadoOrden(String texto) {
            this.texto = texto;
        }
        
        public String getTexto() {
            return texto;
        }
    }
    
    /**
     * Constructor para la clase Orden.
     * @param usuario Usuario que realiza la orden
     */
    public Orden(String usuario) {
        this.id = contadorId++;
        this.usuario = usuario;
        this.detalles = new ListaEnlazada<>();
        this.fecha = LocalDateTime.now();
        this.estado = EstadoOrden.PENDIENTE;
    }
    
    /**
     * Constructor para la clase Orden a partir de un carrito.
     * @param carrito Carrito de compras
     */
    public Orden(Carrito carrito) {
        this(carrito.getUsuario());
        
        // Crea detalles de orden a partir de los ítems del carrito
        for (int i = 0; i < carrito.getItems().tamaño(); i++) {
            Carrito.ItemCarrito item = carrito.getItems().obtener(i);
            agregarDetalle(item.getProducto(), item.getCantidad());
        }
    }
    
    /**
     * Agrega un detalle a la orden.
     * @param producto Producto
     * @param cantidad Cantidad
     */
    public void agregarDetalle(Producto producto, int cantidad) {
        // Verifica si el producto ya está en los detalles
        for (int i = 0; i < detalles.tamaño(); i++) {
            DetalleOrden detalle = detalles.obtener(i);
            if (detalle.getProducto().equals(producto)) {
                detalle.setCantidad(detalle.getCantidad() + cantidad);
                return;
            }
        }
        
        // Si el producto no está en los detalles, lo agrega
        detalles.agregar(new DetalleOrden(producto, cantidad));
    }
    
    /**
     * Obtiene el ID de la orden.
     * @return ID de la orden
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el usuario que realizó la orden.
     * @return Usuario que realizó la orden
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Obtiene los detalles de la orden.
     * @return Detalles de la orden
     */
    public ListaEnlazada<DetalleOrden> getDetalles() {
        return detalles;
    }
    
    /**
     * Obtiene la fecha de la orden.
     * @return Fecha de la orden
     */
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    /**
     * Obtiene la fecha formateada de la orden.
     * @return Fecha formateada de la orden
     */
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return fecha.format(formatter);
    }
    
    /**
     * Obtiene el estado de la orden.
     * @return Estado de la orden
     */
    public EstadoOrden getEstado() {
        return estado;
    }
    
    /**
     * Establece el estado de la orden.
     * @param estado Nuevo estado de la orden
     */
    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }
    
    /**
     * Calcula el total de la orden.
     * @return Total de la orden
     */
    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < detalles.tamaño(); i++) {
            DetalleOrden detalle = detalles.obtener(i);
            total += detalle.getSubtotal();
        }
        return total;
    }
    
    /**
     * Procesa la orden, reduciendo el stock de los productos.
     * @return true si se procesó correctamente, false en caso contrario
     */
    public boolean procesar() {
        // Verifica que haya suficiente stock de todos los productos
        for (int i = 0; i < detalles.tamaño(); i++) {
            DetalleOrden detalle = detalles.obtener(i);
            if (!detalle.getProducto().hayStock(detalle.getCantidad())) {
                return false;
            }
        }
        
        // Reduce el stock de los productos
        for (int i = 0; i < detalles.tamaño(); i++) {
            DetalleOrden detalle = detalles.obtener(i);
            detalle.getProducto().reducirStock(detalle.getCantidad());
        }
        
        // Actualiza el estado de la orden
        estado = EstadoOrden.PROCESANDO;
        return true;
    }
    
    /**
     * Clase interna que representa un detalle de orden.
     */
    public static class DetalleOrden {
        private Producto producto;
        private int cantidad;
        private double precioUnitario;
        
        /**
         * Constructor para la clase DetalleOrden.
         * @param producto Producto
         * @param cantidad Cantidad
         */
        public DetalleOrden(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
            this.precioUnitario = producto.getPrecio();
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
         * Obtiene el precio unitario.
         * @return Precio unitario
         */
        public double getPrecioUnitario() {
            return precioUnitario;
        }
        
        /**
         * Calcula el subtotal del detalle.
         * @return Subtotal del detalle
         */
        public double getSubtotal() {
            return precioUnitario * cantidad;
        }
        
        @Override
        public String toString() {
            return producto.getNombre() + " x " + cantidad + " = $" + getSubtotal();
        }
    }
}
