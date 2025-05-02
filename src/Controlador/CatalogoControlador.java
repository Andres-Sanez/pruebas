package controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modelo.Producto;
import modelo.CatalogoProductos;
import modelo.Carrito;
import modelo.ListaDeseos;
import util.ListaEnlazada;

/**
 * Controlador para el catálogo de productos.
 */
public class CatalogoControlador {
    
    private CatalogoProductos catalogo;
    private ListaDeseos listaDeseos;
    private Carrito carrito;
    
    /**
     * Constructor para la clase CatalogoControlador.
     * @param username Usuario actual
     */
    public CatalogoControlador(String username) {
        catalogo = CatalogoProductos.getInstancia();
        listaDeseos = new ListaDeseos(username);
        carrito = new Carrito(username);
    }
    
    /**
     * Obtiene todos los productos del catálogo.
     * @return Lista de productos
     */
    public ListaEnlazada<Producto> obtenerTodosProductos() {
        return catalogo.obtenerProductos();
    }
    
    /**
     * Obtiene los productos de una categoría específica.
     * @param categoria Categoría de productos
     * @return Lista de productos de la categoría
     */
    public ListaEnlazada<Producto> obtenerProductosPorCategoria(String categoria) {
        return catalogo.obtenerProductosPorCategoria(categoria);
    }
    
    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto
     * @return Producto o null si no existe
     */
    public Producto obtenerProductoPorId(int id) {
        return catalogo.obtenerProductoPorId(id);
    }
    
    /**
     * Agrega un producto al carrito.
     * @param producto Producto a agregar
     * @param cantidad Cantidad del producto
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean agregarAlCarrito(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            return false;
        }
        
        boolean resultado = carrito.agregarProducto(producto, cantidad);
        
        if (resultado) {
            mostrarAlerta(AlertType.INFORMATION, "Agregado al Carrito", 
                    producto.getNombre() + " ha sido agregado al carrito.");
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", 
                    "No hay suficiente stock disponible.");
        }
        
        return resultado;
    }
    
    /**
     * Agrega un producto a la lista de deseos.
     * @param producto Producto a agregar
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean agregarAListaDeseos(Producto producto) {
        if (producto == null) {
            return false;
        }
        
        boolean resultado = listaDeseos.agregarProducto(producto);
        
        if (resultado) {
            mostrarAlerta(AlertType.INFORMATION, "Agregado a Lista de Deseos", 
                    producto.getNombre() + " ha sido agregado a tu lista de deseos.");
        } else {
            mostrarAlerta(AlertType.INFORMATION, "Producto Duplicado", 
                    "Este producto ya está en tu lista de deseos.");
        }
        
        return resultado;
    }
    
    /**
     * Obtiene las categorías disponibles.
     * @return Lista de categorías
     */
    public ListaEnlazada<String> obtenerCategorias() {
        return catalogo.obtenerCategorias();
    }
    
    /**
     * Obtiene el carrito de compras.
     * @return Carrito de compras
     */
    public Carrito getCarrito() {
        return carrito;
    }
    
    /**
     * Obtiene la lista de deseos.
     * @return Lista de deseos
     */
    public ListaDeseos getListaDeseos() {
        return listaDeseos;
    }
    
    /**
     * Busca productos por nombre.
     * @param nombre Nombre del producto a buscar
     * @return Lista de productos que coinciden con el nombre
     */
    public ListaEnlazada<Producto> buscarProductosPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ListaEnlazada<>();
        }
        return catalogo.buscarProductosPorNombre(nombre);
    }
    
    /**
     * Ordena los productos por precio (ascendente).
     * @return Lista de productos ordenados por precio
     */
    public ListaEnlazada<Producto> ordenarProductosPorPrecioAsc() {
        return catalogo.ordenarProductosPorPrecioAsc();
    }
    
    /**
     * Ordena los productos por precio (descendente).
     * @return Lista de productos ordenados por precio
     */
    public ListaEnlazada<Producto> ordenarProductosPorPrecioDesc() {
        return catalogo.ordenarProductosPorPrecioDesc();
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