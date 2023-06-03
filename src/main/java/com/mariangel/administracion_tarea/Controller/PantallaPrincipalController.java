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
public class PantallaPrincipalController extends Controller implements Initializable {

    @FXML
    private Button btnPEmpMantenimiento;
    @FXML
    private Button btnPReservaciones;
    @FXML
    private Button btnPTiposTours;
    @FXML
    private Button btnPTours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void mostrarPMantenimientoDeEmpresas(ActionEvent event) {
        Stage currentStage = (Stage) btnPEmpMantenimiento.getScene().getWindow();
        currentStage.close(); 
        FlowController.getInstance().goViewInWindow("MantenimientoEmpresa");
    }

    @FXML
    private void mostrarPReservaciones(ActionEvent event) {
        Stage currentStage = (Stage) btnPEmpMantenimiento.getScene().getWindow();
        currentStage.close(); 
        FlowController.getInstance().goViewInWindow("Reservacion");
    }

    @FXML
    private void mostrarPTiposDeTours(ActionEvent event) {
        Stage currentStage = (Stage) btnPEmpMantenimiento.getScene().getWindow();
        currentStage.close(); 
        FlowController.getInstance().goViewInWindow("TipoTour");
    }

    @FXML
    private void mostrarPTours(ActionEvent event) {
       Stage currentStage = (Stage) btnPEmpMantenimiento.getScene().getWindow();
        currentStage.close(); 
        FlowController.getInstance().goViewInWindow("Tour");}

    @Override
    public void initialize() {
        
    }

}
