package com.mariangel.administracion_tarea;

import com.mariangel.administracion_tarea.Utils.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

     @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage,null);                                        
        //stage.getIcons().add(new Image("com/jumaikel/lab3/images/Icono.png"));
        stage.setTitle("Administración Tours");
        //FlowController.getInstance().goViewInWindow("Personas");
        FlowController.getInstance().goMain();
    }

    public static void main(String[] args) {
        launch();
    }

}