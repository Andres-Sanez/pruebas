package modelo;

import javafx.scene.image.Image;

/**
 * Clase que representa un producto de zapatilla en el sistema.
 */
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagenUrl;
    private int stock;
    private String categoria;
    
    /**
     * Constructor para la clase Producto.
     * 
     * @param id Identificador único del producto
     * @param nombre Nombre del producto
     * @param descripcion Descripción detallada del producto
     * @param precio Precio del producto
     * @param imagenUrl URL de la imagen del producto (ruta relativa al proyecto)
     * @param stock Cantidad disponible del producto
     * @param categoria Categoría a la que pertenece el producto
     */
    public Producto(int id, String nombre, String descripcion, double precio, String imagenUrl, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.stock = stock;
        this.categoria = categoria;
    }
    
    /**
     * Obtiene el ID del producto.
     * @return ID del producto
     */
    public int getId() {
        return id;
    }
    
    /**
     * Establece el ID del producto.
     * @param id Nuevo ID del producto
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del producto.
     * @param nombre Nuevo nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la descripción del producto.
     * @return Descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Establece la descripción del producto.
     * @param descripcion Nueva descripción del producto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Obtiene el precio del producto.
     * @return Precio del producto
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * Establece el precio del producto.
     * @param precio Nuevo precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Obtiene la URL de la imagen del producto.
     * @return URL de la imagen del producto
     */
    public String getImagenUrl() {
        return imagenUrl;
    }
    
    /**
     * Establece la URL de la imagen del producto.
     * @param imagenUrl Nueva URL de la imagen del producto
     */
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
    
    /**
     * Obtiene el stock disponible del producto.
     * @return Stock disponible del producto
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * Establece el stock disponible del producto.
     * @param stock Nuevo stock disponible del producto
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * Obtiene la categoría a la que pertenece el producto.
     * @return Categoría del producto
     */
    public String getCategoria() {
        return categoria;
    }
    
    /**
     * Establece la categoría a la que pertenece el producto.
     * @param categoria Nueva categoría del producto
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Obtiene la imagen del producto como un objeto Image de JavaFX.
     * @return Imagen del producto
     */
    public Image getImagen() {
        try {
            return new Image(getClass().getResourceAsStream(imagenUrl));
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + imagenUrl);
            return null;
        }
    }
    
    /**
     * Verifica si hay suficiente stock del producto para una cantidad dada.
     * @param cantidad Cantidad a verificar
     * @return true si hay suficiente stock, false en caso contrario
     */
    public boolean hayStock(int cantidad) {
        return stock >= cantidad;
    }
    
    /**
     * Reduce el stock del producto en la cantidad dada.
     * @param cantidad Cantidad a reducir
     */
    public void reducirStock(int cantidad) {
        if (cantidad > 0 && hayStock(cantidad)) {
            stock -= cantidad;
        }
    }
    
    /**
     * Aumenta el stock del producto en la cantidad dada.
     * @param cantidad Cantidad a aumentar
     */
    public void aumentarStock(int cantidad) {
        if (cantidad > 0) {
            stock += cantidad;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Producto producto = (Producto) obj;
        return id == producto.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    
    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}