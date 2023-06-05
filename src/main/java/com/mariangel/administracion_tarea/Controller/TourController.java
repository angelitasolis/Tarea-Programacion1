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
import com.mariangel.administracion_tarea.Model.TourDto;
import com.mariangel.administracion_tarea.Service.TourService;
import com.mariangel.administracion_tarea.Utils.FlowController;
import com.mariangel.administracion_tarea.Utils.Formato;
import com.mariangel.administracion_tarea.Utils.Mensaje;
import com.mariangel.administracion_tarea.Utils.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
public class TourController extends Controller implements Initializable {

    @FXML
    private Button btn_retroceder;
    @FXML
    private Tab tabPaneGuardar;
    @FXML
    private DatePicker datePickerFecSalidaGuardarTour;
    @FXML
    private TextField txtCodigoTourGuardarTour;
    @FXML
    private TextField txtNombreEmpresaGuardarTour;
    @FXML
    private Button btnGuardarTour;
    @FXML
    private Button btnEliminarTour;
    @FXML
    private Button btnCancelarTour;
    @FXML
    private Button btnModificarTour;
    @FXML
    private TextField txtTipoTourGuardarTour;

    @FXML
    private DatePicker datePickerFecLlegadaGuardarTour;
    @FXML
    private TextField txtCostosGuardarTour;
    @FXML
    private TextField txtHoraSalidaGuardarTour;
    @FXML
    private TextField txtHoraLlegadaGuardarTour;
    @FXML
    private Tab tabPaneInformacion;
    @FXML
    private TableView<?> tblvInformacionCliente;
    @FXML
    private TableColumn<?, ?> tblvEmpresa;
    @FXML
    private TableColumn<?, ?> tblvNombre;
    @FXML
    private TableColumn<?, ?> tblvTipoTour;
    @FXML
    private TableColumn<?, ?> tblvCostoPersona;
    @FXML
    private TableColumn<?, ?> tblvDuracion;
    @FXML
    private TableColumn<?, ?> tblvFechaLlegada;
    @FXML
    private TableColumn<?, ?> tblvHoraLlegada;
    @FXML
    private TableColumn<?, ?> tblvFecSalida;
    @FXML
    private TableColumn<?, ?> tblvHoraSalida;
    @FXML
    private TextField txtFiltroBusqueda;
    @FXML
    private Button btnBuscarInformacion;
    @FXML
    private MenuButton MenuButton;
    @FXML
    private MenuItem menuBtnFiltroNombreTour;
    @FXML
    private MenuItem menuBtnFiltroEmpresa;
    @FXML
    private MenuItem menuBtnFiltroLugar;
    @FXML
    private Tab tabPaneGuardarItinerarios;
    @FXML
    private DatePicker datePickerFecSalidaGuardarItinerarios;
    @FXML
    private TextField txtLugarGuardarItinerarios;
    @FXML
    private TextField txtNombreEmpresaGuardarItinerarios;
    @FXML
    private Button btnBuscarClienteItinerarios;
    @FXML
    private Button btnGuardarItinerarios;
    @FXML
    private Button btnEliminarItinerarios;
    @FXML
    private Button btnCancelarItinerarios;
    @FXML
    private Button btnModificarItinerarios;
    @FXML
    private TextField txtActividadesGuardarItinerarios;
    @FXML
    private DatePicker datePickerFecLlegadaGuardarItinerarios;
    @FXML
    private TextField txtCantidadMaxGuardarTour;
    @FXML
    private TextField txtDuracionGuardarItinerarios;
    @FXML
    private Button btnBuscarTour;

    @FXML
    private TableColumn<?, ?> tblvCantMaxClientes;
    ItinerarioDto itinerario;
    TourDto tour;

    List<Node> requeridos = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        itinerario = new ItinerarioDto();
        tour = new TourDto();

        //tour
        txtNombreEmpresaGuardarTour.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCodigoTourGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtTipoTourGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtCostosGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtHoraLlegadaGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtHoraSalidaGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtFiltroBusqueda.setTextFormatter(Formato.getInstance().integerFormat());
        txtCantidadMaxGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());

        //itinerario
//        txtNombreEmpresaGuardarItinerarios.setTextFormatter(Formato.getInstance().integerFormat());
//        txtLugarGuardarItinerarios.setTextFormatter(Formato.getInstance().letrasFormat(30));
//        txtDuracionGuardarItinerarios.setTextFormatter(Formato.getInstance().integerFormat());
//        txtActividadesGuardarItinerarios.setTextFormatter(Formato.getInstance().letrasFormat(150));
        //nuevoItinerario();
        //indicarRequeridosItinerarios();
        indicarRequeridosTour();
        nuevoTour();

    }

    @Override
    public void initialize() {

    }

    private void bindTour(Boolean nuevo) {
        if (!nuevo) {
            txtCodigoTourGuardarTour.textProperty().bindBidirectional(tour.trsCodigotour);
        }
        txtCodigoTourGuardarTour.textProperty().bindBidirectional(tour.trsCodigotour);
        // txtTipoTourGuardarTour.textProperty().bindBidirectional(tour.trsTipotourcodigo);
        txtNombreEmpresaGuardarTour.textProperty().bindBidirectional(tour.trsNombre);
        txtCostosGuardarTour.textProperty().bindBidirectional(tour.trsCostotour);
        txtHoraLlegadaGuardarTour.textProperty().bindBidirectional(tour.trsHorallegada);
        txtHoraSalidaGuardarTour.textProperty().bindBidirectional(tour.trsHorasalida);
        //txtFiltroBusqueda.textProperty().bindBidirectional(tour.);
        txtHoraLlegadaGuardarTour.textProperty().bindBidirectional(tour.trsHorallegada);
        //MenuButton.textProperty().bindBidirectional(tour.);
        datePickerFecLlegadaGuardarTour.valueProperty().bindBidirectional(tour.trsFechallegada);
        datePickerFecSalidaGuardarTour.valueProperty().bindBidirectional(tour.trsFechasalida);

    }

    private void unbindTour() {

        txtCodigoTourGuardarTour.textProperty().unbindBidirectional(tour.trsCodigotour);
        // txtTipoTourGuardarTour.textProperty().unbindBidirectional(tour.trsTipotourcodigo);
        txtNombreEmpresaGuardarTour.textProperty().unbindBidirectional(tour.trsNombre);
        txtCostosGuardarTour.textProperty().unbindBidirectional(tour.trsCostotour);
        txtHoraLlegadaGuardarTour.textProperty().unbindBidirectional(tour.trsHorallegada);
        txtHoraSalidaGuardarTour.textProperty().unbindBidirectional(tour.trsHorasalida);
        //txtFiltroBusqueda.textProperty().unbindBidirectional(tour.);
        //MenuButton.textProperty().unbindBidirectional(tour.);
        datePickerFecLlegadaGuardarTour.valueProperty().unbindBidirectional(tour.trsFechallegada);
        datePickerFecSalidaGuardarTour.valueProperty().unbindBidirectional(tour.trsFechasalida);
    }
    //TOURS

    public void indicarRequeridosTour() {
        System.out.println(" ENTRO AL indicar requeridos de cargar paciente");
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCodigoTourGuardarTour, txtNombreEmpresaGuardarTour, txtCostosGuardarTour, txtHoraLlegadaGuardarTour, txtHoraSalidaGuardarTour, datePickerFecLlegadaGuardarTour, datePickerFecSalidaGuardarTour));
    }

    private void nuevoTour() {
        unbindTour();
        tour = new TourDto();
        bindTour(true);
        txtCodigoTourGuardarTour.clear();
        txtCodigoTourGuardarTour.requestFocus();
    }
    
       private void cargarTour(String pcodigo) {
        TourService service = new TourService();
        Respuesta respuesta = service.getTour(pcodigo);
        if (respuesta.getEstado()) {
            unbindTour();
            tour = (TourDto) respuesta.getResultado("Tour");

            bindTour(false);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }

    }
    

    @FXML
    private void onActionBuscarTour(ActionEvent event) {
        String cedulaText = txtCodigoTourGuardarTour.getText();
        cargarTour(cedulaText);
       // mediaPlayer.play();
    }

    @FXML
    private void onAnctionBtnGuardarTour(ActionEvent event) {
    }

    @FXML
    private void onActionBtnEliminarTour(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancelarTour(ActionEvent event) {
    }

    @FXML
    private void onActionBtnModificarTour(ActionEvent event) {
    }

    @FXML
    private void onSelectionGuardarTabPane(Event event) {
    }

    @FXML
    private void onActionBtnBuscarPorFiltro(ActionEvent event) {
    }

    @FXML
    private void onActionFiltroNombreTour(ActionEvent event) {
    }

    @FXML
    private void onActionFiltroEmpresa(ActionEvent event) {
    }

    @FXML
    private void onActionFiltroLugar(ActionEvent event) {
    }

    @FXML
    private void onActionMenuButton(ActionEvent event) {
    }

    @FXML
    private void onSelectionInfoTabPane(Event event) {
    }

    //ITINERARIOS
    @FXML
    private void onActionBuscarItinerario(ActionEvent event) {
    }

    @FXML
    private void onAnctionBtnGuardarItinerario(ActionEvent event) {
    }

    @FXML
    private void onActionBtnEliminarItinerario(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCancelarItinerario(ActionEvent event) {
    }

    @FXML
    private void onActionBtnModificarItinerario(ActionEvent event) {
    }

    @FXML
    private void onSelectionGuardarItinerariosTabPane(Event event) {
    }

    //AMBOS
    @FXML
    private void onActionretroceder(ActionEvent event) {
        Stage currentStage = (Stage) btn_retroceder.getScene().getWindow();
        currentStage.close();
        FlowController.getInstance().goMain();
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
