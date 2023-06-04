/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.mariangel.administracion_tarea.Model.Cliente;
import com.mariangel.administracion_tarea.Model.ClienteDto;
import com.mariangel.administracion_tarea.Utils.FlowController;
import com.mariangel.administracion_tarea.Utils.Formato;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class ClienteController extends Controller implements Initializable {

    @FXML
    private Button btn_retroceder;
    @FXML
    private Tab tabPaneGuardar;
    @FXML
    private TextField txtTelefono;
    @FXML
    private DatePicker datePickerFecNac;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtCedula;
    @FXML
    private Button btnBuscarCliente;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnModificar;
    @FXML
    private TextField txtPApellido;
    @FXML
    private TextField txtSApellido;
    @FXML
    private Tab tabPaneInformacion;
    @FXML
    private TableView<?> tblvInformacionPacientes;
    @FXML
    private TableColumn<Cliente, ?> tblvCedula;
    @FXML
    private TableColumn<Cliente, ?> tblvNombre;
    @FXML
    private TableColumn<Cliente, ?> tblvPApellido;
    @FXML
    private TableColumn<Cliente, ?> tblvSApellido;
    @FXML
    private TableColumn<Cliente, ?> tblvTelefono;
    @FXML
    private TableColumn<Cliente, ?> tblvCorreo;
    @FXML
    private TableColumn<Cliente, ?> tblvFecNac;
    
    
    ClienteDto cliente;
    List<Node> requeridos = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new ClienteDto();

        txtNombreCliente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPApellido.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtSApellido.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(100));
        txtCedula.setTextFormatter(Formato.getInstance().cedulaFormat(30));
        txtTelefono.setTextFormatter(Formato.getInstance().integerFormat());
        
        indicarRequeridos();
        nuevoCliente();
    }
     private void bindCliente(Boolean nuevo) {
        if (!nuevo) {
            txtCedula.textProperty().bindBidirectional(cliente.cltCedula);
        }
        txtCedula.textProperty().bindBidirectional(cliente.cltCedula);
        txtNombreCliente.textProperty().bindBidirectional(cliente.cltNombre);
        txtPApellido.textProperty().bindBidirectional(cliente.cltPapellido);
        txtSApellido.textProperty().bindBidirectional(cliente.cltSapellido);
        txtCorreo.textProperty().bindBidirectional(cliente.cltCorreo);
        txtTelefono.textProperty().bindBidirectional(cliente.cltTelefono);
        datePickerFecNac.valueProperty().bindBidirectional(cliente.cltFechanac);
        

    }

    private void unbindCliente() {

        txtCedula.textProperty().unbindBidirectional(cliente.cltCedula);
        txtNombreCliente.textProperty().unbindBidirectional(cliente.cltNombre);
        txtPApellido.textProperty().unbindBidirectional(cliente.cltPapellido);
        txtSApellido.textProperty().unbindBidirectional(cliente.cltSapellido);
        txtCorreo.textProperty().unbindBidirectional(cliente.cltCorreo);
        txtTelefono.textProperty().bindBidirectional(cliente.cltTelefono);
        datePickerFecNac.valueProperty().unbindBidirectional(cliente.cltFechanac);
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
    private void onSelectionInfoTabPane(Event event) {
    }

}
