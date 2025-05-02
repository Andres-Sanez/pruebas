
package controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.Usuario;
import vista.PrincipalVista;

public class LoginControlador {
    
    public boolean validarCredenciales(String username, String password) {
        // Conecta con el modelo para validar las credenciales
        Usuario usuario = new Usuario(username, password);
        return usuario.validarCredenciales();
    }
    
    public boolean registrarUsuario(String username, String password) {
        // Valida que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        Usuario usuario = new Usuario(username, password);
        
        // Verifica si el usuario ya existe
        if (usuario.existeUsuario()) {
            return false;
        }
        
        // Registra el nuevo usuario
        return usuario.registrarUsuario();
    }
    
    public boolean existeUsuario(String username) {
        Usuario usuario = new Usuario(username, "");
        return usuario.existeUsuario();
    }
    
    public void mostrarAlertaExito(String username) {
        mostrarAlerta(AlertType.INFORMATION, "Inicio de Sesión Exitoso", 
                    "Bienvenido " + username + " al Proyecto Final EDD!");
    }
    
    public void mostrarAlertaError() {
        mostrarAlerta(AlertType.ERROR, "Error de Inicio de Sesión", 
                    "Usuario o contraseña incorrectos");
    }
    
    public void mostrarAlertaRegistroExito() {
        mostrarAlerta(AlertType.INFORMATION, "Registro Exitoso", 
                    "Usuario registrado correctamente. Ahora puedes iniciar sesión.");
    }
    
    public void mostrarAlertaRegistroError() {
        mostrarAlerta(AlertType.ERROR, "Error de Registro", 
                    "El usuario ya existe o los datos son inválidos.");
    }
    
    public void abrirVentanaPrincipal(Stage currentStage, String username) {
        try {
            // Cierra la ventana actual
            currentStage.close();
            
            // Abre la ventana principal
            PrincipalVista principalVista = new PrincipalVista();
            principalVista.mostrar(username);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Error", 
                    "No se pudo abrir la ventana principal: " + e.getMessage());
        }
    }
    
    private void mostrarAlerta(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}