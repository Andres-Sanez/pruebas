
package vista;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrincipalVista {
    
    public void mostrar(String username) {
        Stage stage = new Stage();
        stage.setTitle("Proyecto Final EDD - Ventana Principal");
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        
        Label bienvenidaLabel = new Label("Bienvenido, " + username + "!");
        bienvenidaLabel.setFont(new Font("Arial", 24));
        
        root.setCenter(bienvenidaLabel);
        
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
