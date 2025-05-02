
package modelo;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String username;
    private String password;
    private static Map<String, String> usuariosRegistrados = new HashMap<>();
    
    static {
        // Usuario predeterminado para pruebas
        usuariosRegistrados.put("admin", "admin");
    }
    
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean validarCredenciales() {
        // Verifica si el usuario existe y si la contraseña es correcta
        return usuariosRegistrados.containsKey(username) && 
               usuariosRegistrados.get(username).equals(password);
    }
    
    public boolean existeUsuario() {
        // Verifica si el usuario ya existe
        return usuariosRegistrados.containsKey(username);
    }
    
    public boolean registrarUsuario() {
        // Si el usuario no existe, lo registra
        if (!existeUsuario()) {
            usuariosRegistrados.put(username, password);
            return true;
        }
        return false;
    }
    
    // Getters y setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}