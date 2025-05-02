
package vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controlador.LoginControlador;

public class LoginVista extends Application {
    
    private TextField userTextField;
    private PasswordField pwBox;
    private Text actiontarget;
    private LoginControlador controlador;
    
    @Override
    public void start(Stage primaryStage) {
        // Inicializo el controlador
        controlador = new LoginControlador();
        
        primaryStage.setTitle("Proyecto Final EDD - Login");
        
        // el grid que contendrá los elementos
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // Título del formulario
        Text scenetitle = new Text("Proyecto Final EDD");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        // Etiqueta y campo de usuario
        Label userName = new Label("Usuario:");
        grid.add(userName, 0, 1);
        
        userTextField = new TextField();
        userTextField.setPromptText("Ingrese su nombre de usuario");
        grid.add(userTextField, 1, 1);
        
        // Etiqueta y campo de contraseña
        Label pw = new Label("Contraseña:");
        grid.add(pw, 0, 2);
        
        pwBox = new PasswordField();
        pwBox.setPromptText("Ingrese su contraseña");
        grid.add(pwBox, 1, 2);
        
        // Botones de iniciar sesión y registrarse
        Button btnLogin = new Button("Iniciar Sesión");
        Button btnRegistro = new Button("Registrarse");
        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnRegistro, btnLogin);
        grid.add(hbBtn, 1, 4);
        
        // Texto para mensajes de estado
        actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        // Acción del botón de iniciar sesión
        btnLogin.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            
            if (controlador.validarCredenciales(username, password)) {
                controlador.mostrarAlertaExito(username);
                actiontarget.setText("Inicio de sesión exitoso");
                // Abrimos la ventana principal
                controlador.abrirVentanaPrincipal(primaryStage, username);
            } else {
                controlador.mostrarAlertaError();
                actiontarget.setText("Credenciales incorrectas");
            }
        });
        
        // Acción del botón de registro
        btnRegistro.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            
            if (controlador.registrarUsuario(username, password)) {
                controlador.mostrarAlertaRegistroExito();
                actiontarget.setText("Usuario registrado correctamente");
                
                // Limpiar los campos de texto después del registro 
                userTextField.clear();
                pwBox.clear();
            } else {
                controlador.mostrarAlertaRegistroError();
                actiontarget.setText("Error al registrar usuario");
            }
        });
        
        // Crear la escena
        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}