/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.administracion_tarea.Model.Itinerario;
import com.mariangel.administracion_tarea.Model.ItinerarioDto;
import com.mariangel.administracion_tarea.Model.ReservaDto;
import com.mariangel.administracion_tarea.Model.Tour;
import com.mariangel.administracion_tarea.Service.ItinerarioService;
import com.mariangel.administracion_tarea.Service.ReservaService;
import com.mariangel.administracion_tarea.Utils.FlowController;
import com.mariangel.administracion_tarea.Utils.Formato;
import com.mariangel.administracion_tarea.Utils.Mensaje;
import com.mariangel.administracion_tarea.Utils.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    private TableView<Itinerario> tblvInformacionItinerarios;
    @FXML
    private TableColumn<Itinerario, String> tblvID;
    @FXML
    private TableColumn<Itinerario, Tour> tblvCodigoTourItinerarios;
    @FXML
    private TableColumn<Itinerario, String> tblvLugar;
    @FXML
    private TableColumn<Itinerario, String> tblvDuracion;
    @FXML
    private TableColumn<Itinerario, String> tblvActividades;
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
        choiceBCliente.valueProperty().unbindBidirectional(reserva.rsCedulacliente);
        choiceBCodigoTour.valueProperty().unbindBidirectional(reserva.rsCodigotour);
        txtMontoAbonado.textProperty().unbindBidirectional(reserva.rsMontoabonado);
        txtTourCosto.textProperty().unbindBidirectional(reserva.rsTrscosto);
        datePickerFechaReserva.valueProperty().unbindBidirectional(reserva.rsFechareserva);
    }

    public void indicarRequeridosReserva() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(datePickerFechaReserva, txtTourCosto,
                choiceBCodigoTour, choiceBCliente, txtMontoAbonado));
    }

    private void nuevaReserva() {
        unbindReserva();
        reserva = new ReservaDto();
        bindReserva(true);
        txtIdReservacion.clear();
        txtIdReservacion.requestFocus();
    }

    private void cargarReserva(Long pcedula) {
        ItinerarioService service = new ItinerarioService();
        Respuesta respuesta = service.getItinerario(pcedula);
        if (respuesta.getEstado()) {
            unbindReserva();
            reserva = (ReservaDto) respuesta.getResultado("Reserva");
            bindReserva(false);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Reserva", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onActionBuscarReservas(ActionEvent event) {
        String idText = txtIdReservacion.getText();
        Long id = Long.parseLong(idText);
        cargarReserva(id);
    }

    @FXML
    private void onAnctionBtnGuardarReservas(ActionEvent event) {

        try {
            ReservaService reservaService = new ReservaService();
            Respuesta respuesta = reservaService.guardarReserva(reserva);

            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar reserva", getStage(), respuesta.getMensaje());
            } else {
                unbindReserva();
                reserva = (ReservaDto) respuesta.getResultado("Reserva");
                bindReserva(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar reserva", getStage(), "Reserva guardado correctamente.");
            }
            //recargarItinerarios();
            // activarListenerGenerarCodigo();
        } catch (Exception ex) {
            System.out.println(reserva.toString());
            Logger.getLogger(ReservacionController.class.getName()).log(Level.SEVERE, "Error guardando reserva.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar reserva", getStage(), "Ocurrio un error guardando el reserva.");
        }
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
