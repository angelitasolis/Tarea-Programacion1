/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.administracion_tarea.Model.ItinerarioDto;
import com.mariangel.administracion_tarea.Model.ReservaDto;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mari
 */
public class ReservacionController extends Controller implements Initializable {

    @FXML
    private Button btn_retroceder;
    @FXML
    private Button btnBuscarClienteReservas;
    @FXML
    private Button btnGuardarReservas;
    @FXML
    private Button btnEliminarReservas;
    @FXML
    private Button btnCancelarIReservas;
    @FXML
    private Button btnModificarReservas;
    @FXML
    private TextField txtMontoAbonado;
    @FXML
    private TextField txtTourCosto;
    @FXML
    private ChoiceBox<?> choiceBCodigoTour;
    @FXML
    private ChoiceBox<?> choiceBCliente;
    @FXML
    private DatePicker datePickerFechaReserva;
    private TextField txtCantidadPersonas;
    @FXML
    private Tab tabPaneReservaciones;
    @FXML
    private TableView<?> tblvInformacionCliente;
    @FXML
    private TableColumn<?, ?> tblvIdReservaciones;
    @FXML
    private TableColumn<?, ?> tblvCodigoTour;
    @FXML
    private TableColumn<?, ?> tblvEmpresa;
    @FXML
    private TableColumn<?, ?> tblvNombre;
    @FXML
    private TableColumn<?, ?> tblvTipoTour;
    @FXML
    private TableColumn<?, ?> tblvCantMaxClientes;
    @FXML
    private TableColumn<?, ?> tblvCostoPersona;
    @FXML
    private TextField txtFiltroBusqueda;
    @FXML
    private Button btnBuscarInformacion;

    //Itinerarios
    @FXML
    private TableView<?> tblvInformacionItinerarios;
    @FXML
    private TableColumn<?, ?> tblvID;
    @FXML
    private TableColumn<?, ?> tblvCodigoTourItinerarios;
    @FXML
    private TableColumn<?, ?> tblvLugar;
    @FXML
    private TableColumn<?, ?> tblvDuracion;
    @FXML
    private TableColumn<?, ?> tblvActividades;
    @FXML
    private Tab tapReservacionesInscribir;

    ReservaDto reserva;
    ItinerarioDto itinerario;

    List<Node> requeridos = new ArrayList<>();
    @FXML
    private TextField txtIdReservacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtMontoAbonado.setTextFormatter(Formato.getInstance().cedulaFormat(30));
        txtTourCosto.setTextFormatter(Formato.getInstance().integerFormat());
        
        nuevaReserva();
        indicarRequeridosReserva();
    }
    
        private void bindReserva(Boolean nuevo) {
        if (!nuevo) {
            txtIdReservacion.textProperty().bind(reserva.rsId);
        }
        
        choiceBCodigoTour.valueProperty().bindBidirectional(reserva.rsCodigotour);
        choiceBCliente.valueProperty().bindBidirectional(reserva.rsCedulacliente);
        datePickerFechaReserva.valueProperty().bindBidirectional(reserva.rsFechareserva);
        txtMontoAbonado.textProperty().bindBidirectional(reserva.rsMontoabonado);
        txtTourCosto.textProperty().bindBidirectional(reserva.rsTrscosto);
    }

    private void unbindReserva() {
        txtIdGuardarItinerarios.textProperty().unbind();
        txtLugarGuardarItinerarios.textProperty().unbindBidirectional(itinerario.intLugar);
        choiceBCodigoTour.valueProperty().unbindBidirectional(itinerario.intCodigotour);
        txtDuracionGuardarItinerarios.textProperty().unbindBidirectional(itinerario.intDuracion);
        txtActividadesGuardarItinerarios.textProperty().unbindBidirectional(itinerario.intActividades);
    }
    
    
    
    
    
    
    @FXML
    private void onActionBuscarReservas(ActionEvent event) {
    }

    @FXML
    private void onAnctionBtnGuardarReservas(ActionEvent event) {
    }

    @FXML
    private void onActionBtnEliminarReservas(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancelarReservas(ActionEvent event) {
    }

    @FXML
    private void onActionBtnModificarReservas(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscarPorFiltro(ActionEvent event) {
    }

    @FXML
    private void onSelectionReservaciones(Event event) {
    }

    @FXML
    private void onSelectionReservacionesInscribir(Event event) {
    }

    @FXML
    private void onActionretroceder(ActionEvent event) {
        Stage currentStage = (Stage) btn_retroceder.getScene().getWindow();
        currentStage.close();
        FlowController.getInstance().goMain();
    }

    @Override
    public void initialize() {

    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && !((JFXTextField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

}
