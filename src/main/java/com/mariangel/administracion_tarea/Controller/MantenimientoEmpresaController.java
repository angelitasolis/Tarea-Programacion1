/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.mariangel.administracion_tarea.Utils.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jumac
 */
public class MantenimientoEmpresaController extends Controller implements Initializable {

    @FXML
    private Button btn_retroceder;
    @FXML
    private Tab tabPaneGuardar;
    @FXML
    private TextField txtTelefono;
    @FXML
    private DatePicker datePickerFecFunda;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtNombreEmpresa;
    @FXML
    private TextField txtCedulaJuridica;
    @FXML
    private Button btnBuscarEmpresa;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnModificar;
    @FXML
    private TextField txtCalificacion;
    @FXML
    private Tab tabPaneInformacion;
    @FXML
    private TableView<?> tblvInformacionPacientes;
    @FXML
    private TableColumn<?, ?> tblvCedulaEmpresa;
    @FXML
    private TableColumn<?, ?> tblvNombreEmpresa;
    @FXML
    private TableColumn<?, ?> tblvTelefono;
    @FXML
    private TableColumn<?, ?> tblvCalificacion;
    @FXML
    private TableColumn<?, ?> tblvAnnoFundacion;
    @FXML
    private TableColumn<?, ?> tblvCorreo;
    @FXML
    private TextField txtNombreEmpresaInformacion;
    @FXML
    private Button btnBuscarInformacion;

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

    @FXML
    private void onActionBuscarEmpresa(ActionEvent event) {
    }

    @FXML
    private void onAnctionBtnGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnModificar(ActionEvent event) {
    }

    @FXML
    private void onSelectionGuardarTabPane(Event event) {
    }

    @FXML
    private void onActionBtnBuscarPorNombre(ActionEvent event) {
    }

    @FXML
    private void onSelectionInfoTabPane(Event event) {
    }
    
}
