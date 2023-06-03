/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.mariangel.administracion_tarea.Utils.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jumac
 */
public class TipoTourController extends Controller implements Initializable {

    @FXML
    private Button btn_retroceder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionretroceder(ActionEvent event) {

        Stage currentStage = (Stage) btn_retroceder.getScene().getWindow();
        currentStage.close();
        FlowController.getInstance().goMain();
    }

}
