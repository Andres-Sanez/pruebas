package modelo;

import util.ListaEnlazada;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona el catálogo de productos disponibles.
 */
public class CatalogoProductos {
    private static CatalogoProductos instancia;
    private ListaEnlazada<Producto> productos;
    private Map<String, ListaEnlazada<Producto>> categorias;
    
    /**
     * Constructor privado para implementar el patrón Singleton.
     */
    private CatalogoProductos() {
        productos = new ListaEnlazada<>();
        categorias = new HashMap<>();
        inicializarCatalogo();
    }
    
    /**
     * Obtiene la instancia única del catálogo de productos.
     * @return Instancia única del catálogo de productos
     */
    public static CatalogoProductos getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoProductos();
        }
        return instancia;
    }
    
    /**
     * Inicializa el catálogo con productos predefinidos.
     */
    private void inicializarCatalogo() {
        // Agregar productos de ejemplo (10 modelos de zapatillas Jordan)
        
        // Air Jordan 1
        agregarProducto(new Producto(
            1, 
            "Air Jordan 1 Retro High OG",
            "Las zapatillas Air Jordan 1 Retro High OG ofrecen el estilo icónico y la comodidad del diseño original de 1985. Con una parte superior de cuero de primera calidad y la amortiguación Air-Sole en el talón.",
            180.0,
            "/recursos/imagenes/jordan1.png",
            10,
            "Jordan 1"
        ));
        
        // Air Jordan 3
        agregarProducto(new Producto(
            2, 
            "Air Jordan 3 Retro",
            "Las Air Jordan 3 Retro rinden homenaje al modelo original con detalles de diseño auténticos y los materiales clásicos. Incluye la icónica estampado de cemento y el logo de Jumpman en el talón.",
            210.0,
            "/recursos/imagenes/jordan3.png",
            8,
            "Jordan 3"
        ));
        
        // Air Jordan 4
        agregarProducto(new Producto(
            3, 
            "Air Jordan 4 Retro",
            "Las zapatillas Air Jordan 4 Retro ofrecen el mismo estilo revolucionario y la máxima comodidad que las hacen tan populares. Con una parte superior de cuero y malla, así como unidades de amortiguación visibles.",
            200.0,
            "/recursos/imagenes/jordan4.png",
            12,
            "Jordan 4"
        ));
        
        // Air Jordan 5
        agregarProducto(new Producto(
            4, 
            "Air Jordan 5 Retro",
            "Las Air Jordan 5 Retro son fieles al look original con su parte superior de cuero premium y su icónica suela inspirada en los aviones de combate. Incluye la unidad Air visible en el talón para mayor comodidad.",
            220.0,
            "/recursos/imagenes/jordan5.png",
            6,
            "Jordan 5"
        ));
        
        // Air Jordan 6
        agregarProducto(new Producto(
            5, 
            "Air Jordan 6 Retro",
            "Las zapatillas Air Jordan 6 Retro son una recreación del modelo con el que Michael Jordan ganó su primer campeonato. Presentan una parte superior de cuero y nubuck con detalles perforados.",
            190.0,
            "/recursos/imagenes/jordan6.png",
            10,
            "Jordan 6"
        ));
        
        // Air Jordan 11
        agregarProducto(new Producto(
            6, 
            "Air Jordan 11 Retro",
            "Las Air Jordan 11 Retro son conocidas por su elegante combinación de cuero y charol en la parte superior. Incluyen una entresuela de espuma y una unidad Air de longitud completa para una amortiguación excepcional.",
            220.0,
            "/recursos/imagenes/jordan11.png",
            5,
            "Jordan 11"
        ));
        
        // Air Jordan 12
        agregarProducto(new Producto(
            7, 
            "Air Jordan 12 Retro",
            "Las zapatillas Air Jordan 12 Retro presentan la combinación original de cuero y tela con costuras inspiradas en el sol naciente de la bandera japonesa. Ofrecen amortiguación Zoom Air para una sensación reactiva.",
            190.0,
            "/recursos/imagenes/jordan12.png",
            7,
            "Jordan 12"
        ));
        
        // Air Jordan 13
        agregarProducto(new Producto(
            8, 
            "Air Jordan 13 Retro",
            "Las Air Jordan 13 Retro están inspiradas en la 'pata de pantera' con su suela distintiva y el logo holográfico en el talón. Presentan una combinación de cuero y gamuza para un look premium.",
            190.0,
            "/recursos/imagenes/jordan13.png",
            9,
            "Jordan 13"
        ));
        
        // Air Jordan 1 Low
        agregarProducto(new Producto(
            9, 
            "Air Jordan 1 Low",
            "Las Air Jordan 1 Low ofrecen el estilo icónico del modelo original pero con un perfil más bajo. Construidas con materiales premium y detalles auténticos para un look clásico.",
            120.0,
            "/recursos/imagenes/jordan1low.png",
            15,
            "Jordan 1"
        ));
        
        // Air Jordan 4 Retro SE
        agregarProducto(new Producto(
            10, 
            "Air Jordan 4 Retro SE",
            "Edición especial de las Air Jordan 4 Retro con materiales premium y colorways exclusivos. Mantienen todos los detalles icónicos del modelo original.",
            220.0,
            "/recursos/imagenes/jordan4se.png",
            16,
            "Jordan 4"
        ));
    }
    
    /**
     * Agrega un producto al catálogo.
     * @param producto Producto a agregar
     */
    private void agregarProducto(Producto producto) {
        if (producto == null) {
            return;
        }
        
        productos.add(producto);
        
        // Agregar el producto a su categoría correspondiente
        String categoria = producto.getCategoria();
        if (!categorias.containsKey(categoria)) {
            categorias.put(categoria, new ListaEnlazada<>());
        }
        categorias.get(categoria).add(producto);
    }
    
    /**
     * Obtiene todas las categorías disponibles.
     * @return Lista de categorías
     */
    public ListaEnlazada<String> obtenerCategorias() {
        ListaEnlazada<String> listaCategorias = new ListaEnlazada<>();
        for (String categoria : categorias.keySet()) {
            listaCategorias.agregar(categoria);
        }
        return listaCategorias;
    }
    
    /**
     * Obtiene todos los productos del catálogo.
     * @return Lista de productos
     */
    public ListaEnlazada<Producto> obtenerProductos() {
        return productos;
    }
    
    /**
     * Obtiene los productos de una categoría específica.
     * @param categoria Categoría de productos
     * @return Lista de productos de la categoría
     */
    public ListaEnlazada<Producto> obtenerProductosPorCategoria(String categoria) {
        if (categoria == null || !categorias.containsKey(categoria)) {
            return new ListaEnlazada<>();
        }
        return categorias.get(categoria);
    }
    
    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto
     * @return Producto o null si no existe
     */
    public Producto obtenerProductoPorId(int id) {
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
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
        
        ListaEnlazada<Producto> resultados = new ListaEnlazada<>();
        String nombreLowerCase = nombre.toLowerCase();
        
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (producto.getNombre().toLowerCase().contains(nombreLowerCase)) {
                resultados.add(producto);
            }
        }
        
        return resultados;
    }
    
    /**
     * Actualiza el stock de un producto.
     * @param idProducto ID del producto
     * @param nuevoStock Nuevo stock del producto
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean actualizarStock(int idProducto, int nuevoStock) {
        if (nuevoStock < 0) {
            return false;
        }
        
        Producto producto = obtenerProductoPorId(idProducto);
        if (producto != null) {
            producto.setStock(nuevoStock);
            return true;
        }
        
        return false;
    }
    
    /**
     * Ordena los productos por precio (ascendente).
     * @return Lista de productos ordenados por precio
     */
    public ListaEnlazada<Producto> ordenarProductosPorPrecioAsc() {
        ListaEnlazada<Producto> listaOrdenada = new ListaEnlazada<>();
        
        // Copiar todos los productos a la nueva lista
        for (int i = 0; i < productos.size(); i++) {
            listaOrdenada.add(productos.get(i));
        }
        
        // Ordenar por precio (ascendente) usando algoritmo simple de ordenamiento burbuja
        for (int i = 0; i < listaOrdenada.size() - 1; i++) {
            for (int j = 0; j < listaOrdenada.size() - i - 1; j++) {
                Producto producto1 = listaOrdenada.get(j);
                Producto producto2 = listaOrdenada.get(j + 1);
                
                if (producto1.getPrecio() > producto2.getPrecio()) {
                    // Intercambiar
                    listaOrdenada.set(j, producto2);
                    listaOrdenada.set(j + 1, producto1);
                }
            }
        }
        
        return listaOrdenada;
    }
    
    /**
     * Ordena los productos por precio (descendente).
     * @return Lista de productos ordenados por precio
     */
    public ListaEnlazada<Producto> ordenarProductosPorPrecioDesc() {
        ListaEnlazada<Producto> listaOrdenada = new ListaEnlazada<>();
        
        // Copiar todos los productos a la nueva lista
        for (int i = 0; i < productos.size(); i++) {
            listaOrdenada.add(productos.get(i));
        }
        
        // Ordenar por precio (descendente) usando algoritmo simple de ordenamiento burbuja
        for (int i = 0; i < listaOrdenada.size() - 1; i++) {
            for (int j = 0; j < listaOrdenada.size() - i - 1; j++) {
                Producto producto1 = listaOrdenada.get(j);
                Producto producto2 = listaOrdenada.get(j + 1);
                
                if (producto1.getPrecio() < producto2.getPrecio()) {
                    // Intercambiar
                    listaOrdenada.set(j, producto2);
                    listaOrdenada.set(j + 1, producto1);
                }
            }
        }
        
        return listaOrdenada;
    }
}