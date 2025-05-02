package controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modelo.Carrito;
import modelo.Producto;
import modelo.HistorialCompras;
import modelo.Orden;

/**
 * Controlador para el carrito de compras.
 */
public class CarritoControlador {
    
    private Carrito carrito;
    private HistorialCompras historialCompras;
    
    /**
     * Constructor para la clase CarritoControlador.
     * @param username Usuario actual
     */
    public CarritoControlador(String username) {
        carrito = new Carrito(username);
        historialCompras = new HistorialCompras(username);
    }
    
    /**
     * Constructor para la clase CarritoControlador que recibe un carrito existente.
     * @param carrito Carrito existente
     */
    public CarritoControlador(Carrito carrito) {
        this.carrito = carrito;
        this.historialCompras = new HistorialCompras(carrito.getUsuario());
    }
    
    /**
     * Agrega un producto al carrito.
     * @param producto Producto a agregar
     * @param cantidad Cantidad del producto
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            return false;
        }
        
        boolean resultado = carrito.agregarProducto(producto, cantidad);
        
        if (!resultado) {
            mostrarAlerta(AlertType.ERROR, "Error", 
                    "No hay suficiente stock disponible de " + producto.getNombre());
        }
        
        return resultado;
    }
    
    /**
     * Elimina un producto del carrito.
     * @param producto Producto a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarProducto(Producto producto) {
        if (producto == null) {
            return false;
        }
        
        return carrito.eliminarProducto(producto);
    }
    
    /**
     * Actualiza la cantidad de un producto en el carrito.
     * @param producto Producto a actualizar
     * @param cantidad Nueva cantidad
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean actualizarCantidad(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            return false;
        }
        
        boolean resultado = carrito.actualizarCantidad(producto, cantidad);
        
        if (!resultado) {
            mostrarAlerta(AlertType.ERROR, "Error", 
                    "No hay suficiente stock disponible de " + producto.getNombre());
        }
        
        return resultado;
    }
    
    /**
     * Obtiene el carrito.
     * @return Carrito actual
     */
    public Carrito getCarrito() {
        return carrito;
    }
    
    /**
     * Obtiene el historial de compras.
     * @return Historial de compras
     */
    public HistorialCompras getHistorialCompras() {
        return historialCompras;
    }
    
    /**
     * Vacía el carrito.
     */
    public void vaciarCarrito() {
        carrito.vaciar();
    }
    
    /**
     * Procesa la compra actual.
     * @return true si la compra se procesó correctamente, false en caso contrario
     */
    public boolean procesarCompra() {
        if (carrito.getProductos().isEmpty()) {
            mostrarAlerta(AlertType.ERROR, "Error", "El carrito está vacío.");
            return false;
        }
        
        Orden nuevaOrden = new Orden(carrito);
        historialCompras.agregarOrden(nuevaOrden);
        boolean resultado = true;
        
        if (resultado) {
            carrito.vaciar();
            mostrarAlerta(AlertType.INFORMATION, "Compra Exitosa", 
                    "Su compra ha sido procesada con éxito.");
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", 
                    "Hubo un problema al procesar su compra.");
        }
        
        return resultado;
    }
    
    /**
     * Muestra una alerta.
     * @param tipo Tipo de alerta
     * @param titulo Título de la alerta
     * @param mensaje Mensaje de la alerta
     */
    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}