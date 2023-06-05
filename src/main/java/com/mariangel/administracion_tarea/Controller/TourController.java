/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static com.mariangel.administracion_Tarea.controller.ClienteController.obtenerClientesBD;
import com.mariangel.administracion_tarea.Model.Cliente;
import com.mariangel.administracion_tarea.Model.ClienteDto;
import com.mariangel.administracion_tarea.Model.Empresa;
import com.mariangel.administracion_tarea.Model.ItinerarioDto;
import com.mariangel.administracion_tarea.Model.TipoTourDto;
import com.mariangel.administracion_tarea.Model.Tipotour;
import com.mariangel.administracion_tarea.Model.Tour;
import com.mariangel.administracion_tarea.Model.TourDto;
import com.mariangel.administracion_tarea.Service.ClienteService;
import com.mariangel.administracion_tarea.Service.TipotourService;
import com.mariangel.administracion_tarea.Service.TourService;
import com.mariangel.administracion_tarea.Utils.EntityManagerHelper;
import com.mariangel.administracion_tarea.Utils.FlowController;
import com.mariangel.administracion_tarea.Utils.Formato;
import com.mariangel.administracion_tarea.Utils.Mensaje;
import com.mariangel.administracion_tarea.Utils.Respuesta;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author jumac
 */
public class TourController extends Controller implements Initializable {

    @FXML
    private Tab tabPaneGuardar;
    @FXML
    private Button btn_retroceder;
    @FXML
    private Button btnGuardarTour;
    @FXML
    private Button btnEliminarTour;
    @FXML
    private Button btnCancelarTour;
    @FXML
    private Button btnModificarTour;
    @FXML
    private DatePicker datePickerFecSalidaGuardarTour;
    @FXML
    private DatePicker datePickerFecLlegadaGuardarTour;
    @FXML
    private TextField txtCostosGuardarTour;
    @FXML
    private TextField txtHoraSalidaGuardarTour;
    @FXML
    private TextField txtHoraLlegadaGuardarTour;
    @FXML
    private TextField txtFiltroBusqueda;
    @FXML
    private TextField txtCantidadMaxGuardarTour;
    @FXML
    private TextField txtNombreTourGuardar;
    @FXML
    private TextField txtCodigoTourGuardarTour;
    @FXML
    private ChoiceBox<Tipotour> choiceBTipoTourGuardarTour;
    @FXML
    private ChoiceBox<Empresa> choiceBCodigoEmpresaGuardarTour;

    @FXML
    private Tab tabPaneInformacion;
    @FXML
    private TableView<Tour> tblvInformacionCliente;
    @FXML
    private TableColumn<Tour, Empresa> tblvEmpresa;
    @FXML
    private TableColumn<Tour, String> tblvNombre;
    @FXML
    private TableColumn<Tour, Tipotour> tblvTipoTour;
    @FXML
    private TableColumn<Tour, String> tblvCostoPersona;
    @FXML
    private TableColumn<Tour, Date> tblvFechaLlegada;
    @FXML
    private TableColumn<Tour, String> tblvHoraLlegada;
    @FXML
    private TableColumn<Tour, Date> tblvFecSalida;
    @FXML
    private TableColumn<Tour, String> tblvHoraSalida;
    @FXML
    private TableColumn<Tour, String> tblvCantMaxClientes;
    @FXML
    private TableColumn<Tour, Long> tblvCodigoTour;

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
    private TextField txtDuracionGuardarItinerarios;
    @FXML
    private TextField txtLugarGuardarItinerarios;
    @FXML
    private TextField txtNombreEmpresaGuardarItinerarios;
    @FXML
    private TextField txtActividadesGuardarItinerarios;
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
    private Button btnBuscarTour;
    @FXML
    private Button btnBuscarInformacion;

    @FXML
    private DatePicker datePickerFecLlegadaGuardarItinerarios;

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

        List<Empresa> empresasList = obtenerEmpresaBD();
        // Asignar la lista de empresas al ChoiceBox
        ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList(empresasList);
        choiceBCodigoEmpresaGuardarTour.setItems(empresaObservableList);

        List<Tipotour> tiposlist = obtenerTipoTourBD();
        ObservableList<Tipotour> tiposObservableList = FXCollections.observableArrayList(tiposlist);
        choiceBTipoTourGuardarTour.setItems(tiposObservableList);

        //tour
        txtCodigoTourGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtCostosGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtHoraLlegadaGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtHoraSalidaGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtFiltroBusqueda.setTextFormatter(Formato.getInstance().integerFormat());
        txtCantidadMaxGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombreTourGuardar.setTextFormatter(Formato.getInstance().letrasFormat(30));

        activarListenerGenerarCodigo();

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

    public static List<Empresa> obtenerEmpresaBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Empresa> empresasList = new ArrayList<>();
        try {
            empresasList = em.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos las empresas de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return empresasList;
    }

    public static List<Tipotour> obtenerTipoTourBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tipotour> tipoToursList = new ArrayList<>();
        try {
            tipoToursList = em.createQuery("SELECT t FROM Tipotour t", Tipotour.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los tipoTours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tipoToursList;
    }

    @Override
    public void initialize() {

    }

    private void bindTour(Boolean nuevo) {
        if (!nuevo) {
            txtCodigoTourGuardarTour.textProperty().bindBidirectional(tour.trsCodigotour);
        }
        txtCodigoTourGuardarTour.textProperty().bindBidirectional(tour.trsCodigotour);
        txtNombreTourGuardar.textProperty().bindBidirectional(tour.trsNombre);
        txtCostosGuardarTour.textProperty().bindBidirectional(tour.trsCostotour);
        txtHoraLlegadaGuardarTour.textProperty().bindBidirectional(tour.trsHorallegada);
        txtHoraSalidaGuardarTour.textProperty().bindBidirectional(tour.trsHorasalida);
        datePickerFecLlegadaGuardarTour.valueProperty().bindBidirectional(tour.trsFechallegada);
        datePickerFecSalidaGuardarTour.valueProperty().bindBidirectional(tour.trsFechasalida);
        txtCantidadMaxGuardarTour.textProperty().bindBidirectional(tour.trsCantidadclientes);
        choiceBTipoTourGuardarTour.valueProperty().bindBidirectional(tour.trsTipotourcodigo);
        choiceBCodigoEmpresaGuardarTour.valueProperty().bindBidirectional(tour.trsEmpresacedjur);
        
        //MenuButton.textProperty().bindBidirectional(tour.);
         //txtFiltroBusqueda.textProperty().bindBidirectional(tour.);
         // txtTipoTourGuardarTour.textProperty().bindBidirectional(tour.trsTipotourcodigo);
    }

    private void unbindTour() {

        txtCodigoTourGuardarTour.textProperty().unbindBidirectional(tour.trsCodigotour);
        txtNombreTourGuardar.textProperty().unbindBidirectional(tour.trsNombre);
        txtCostosGuardarTour.textProperty().unbindBidirectional(tour.trsCostotour);
        txtHoraLlegadaGuardarTour.textProperty().unbindBidirectional(tour.trsHorallegada);
        txtHoraSalidaGuardarTour.textProperty().unbindBidirectional(tour.trsHorasalida);
        datePickerFecLlegadaGuardarTour.valueProperty().unbindBidirectional(tour.trsFechallegada);
        datePickerFecSalidaGuardarTour.valueProperty().unbindBidirectional(tour.trsFechasalida);
        txtCantidadMaxGuardarTour.textProperty().unbindBidirectional(tour.trsCantidadclientes);
        choiceBTipoTourGuardarTour.valueProperty().unbindBidirectional(tour.trsTipotourcodigo);
        choiceBCodigoEmpresaGuardarTour.valueProperty().unbindBidirectional(tour.trsEmpresacedjur);
    }
    //TOURS

    private void desactivarListenerGenerarCodigo() {
        txtCodigoTourGuardarTour.textProperty().removeListener(generarCodigoListener);
        txtCostosGuardarTour.textProperty().removeListener(generarCodigoListener);
        txtHoraLlegadaGuardarTour.textProperty().removeListener(generarCodigoListener);
        txtHoraSalidaGuardarTour.textProperty().removeListener(generarCodigoListener);
        datePickerFecLlegadaGuardarTour.textProperty().removeListener(generarCodigoListener);
        datePickerFecSalidaGuardarTour.textProperty().removeListener(generarCodigoListener);

    }

    private void activarListenerGenerarCodigo() {
        txtCodigoTourGuardarTour.textProperty().addListener(generarCodigoListener);
        txtNombreEmpresaGuardarTour.textProperty().addListener(generarCodigoListener);
        txtCostosGuardarTour.textProperty().addListener(generarCodigoListener);
        txtHoraLlegadaGuardarTour.textProperty().addListener(generarCodigoListener);
        txtHoraSalidaGuardarTour.textProperty().addListener(generarCodigoListener);
        datePickerFecLlegadaGuardarTour.textProperty().addListener(generarCodigoListener);
        datePickerFecSalidaGuardarTour.textProperty().addListener(generarCodigoListener);
        ;
    }
    private final ChangeListener<String> generarCodigoListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            Long nuevoCodigo = generarCodigoTipoTour();
            txtCodigoTourGuardarTour.setText(nuevoCodigo.toString());
        }
    };

    private Long generarCodigoTipoTour() {
        // Obtener el día actual en el formato deseado
        LocalDate fechaActual = LocalDate.now();
        String diaActual = fechaActual.format(DateTimeFormatter.ofPattern("dd"));

        // Generar un número aleatorio entre 100 y 999
        Random random = new Random();
        int numeroAleatorio = random.nextInt(900) + 100;

        // Combinar las partes para formar el código
        String codigo = diaActual + String.valueOf(numeroAleatorio);

        return Long.valueOf(codigo);
    }

    public void indicarRequeridosTour() {
        System.out.println(" ENTRO AL indicar requeridos de cargar paciente");
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCodigoTourGuardarTour,
        choiceBTipoTourGuardarTour, choiceBCodigoEmpresaGuardarTour, 
        txtCostosGuardarTour, txtHoraLlegadaGuardarTour, txtHoraSalidaGuardarTour, 
        datePickerFecLlegadaGuardarTour, datePickerFecSalidaGuardarTour, 
        txtNombreTourGuardar,  txtCantidadMaxGuardarTour));
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
            btnModificarTour.setVisible(true);
            bindTour(false);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }
    }

    public static List<Tour> obtenerToursBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tour> tourList = new ArrayList<>();
        try {
            tourList = em.createQuery("SELECT t FROM Tour t", Tour.class).getResultList();

        } catch (Exception e) {
            System.out.println("Error al obtener todos los tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tourList;
    }

    public static List<Tour> obtenerToursBD(String filtroNombre) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tour> tourList = new ArrayList<>();
        try {
            String consulta = "SELECT t FROM Tour t";
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                consulta += " WHERE t.trsNombre LIKE :filtroNombre";
            }
            TypedQuery<Tour> query = em.createQuery(consulta, Tour.class);
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                query.setParameter("filtroNombre", "%" + filtroNombre + "%");
            }
            tourList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tourList;
    }

    @FXML
    private void onActionBuscarTour(ActionEvent event) {
        String cedulaText = txtCodigoTourGuardarTour.getText();
        cargarTour(cedulaText);
        // mediaPlayer.play();
    }

    @FXML
    private void onAnctionBtnGuardarTour(ActionEvent event) {
        try {

            TourService tourService = new TourService();
            Respuesta respuesta = tourService.guardarTour(tour);

            if (!respuesta.getEstado()) {
                System.out.println("else" + tour.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tour", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + tour.toString());
                unbindTour();
                tour = (TourDto) respuesta.getResultado("Tour");
                bindTour(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tour", getStage(), "Tour guardado correctamente.");
            }
            activarListenerGenerarCodigo();
        } catch (Exception ex) {
            System.out.println(tour.toString());
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error guardando el tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tour", getStage(), "Ocurrio un error guardando la tour.");
        }
    }

    private void Guardar() {
        try {

            TourService tourService = new TourService();
            Respuesta respuesta = tourService.guardarTour(tour);

            if (!respuesta.getEstado()) {
                System.out.println("else" + tour.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tour", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + tour.toString());
                unbindTour();
                tour = (TourDto) respuesta.getResultado("Tour");
                bindTour(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tour", getStage(), "Tour guardado correctamente.");
            }
        } catch (Exception ex) {
            System.out.println(tour.toString());
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error guardando el tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tour", getStage(), "Ocurrio un error guardando la tour.");
        }
    }

    @FXML
    private void onActionBtnEliminarTour(ActionEvent event) {
        try {
            if (tour.trsCodigotour == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tour", getStage(), "Debe cargar el tipo de tour que desea eliminar.");
            } else {

                TourService service = new TourService();
                Respuesta respuesta = service.eliminarTour(tour.getTrsCodigo());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tour", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar tour", getStage(), "tour eliminado correctamente.");
                    nuevoTour();
                    // mediaPlayer.play();
                }
            }
            activarListenerGenerarCodigo();
        } catch (Exception ex) {
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error eliminando el Cliente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cliente", getStage(), "Ocurrio un error eliminando el Cliente.");
        }
    }

    @FXML
    private void onActionBtnCancelarTour(ActionEvent event) {
        datePickerFecLlegadaGuardarTour.setValue(null);
        datePickerFecSalidaGuardarTour.setValue(null);
        txtCodigoTourGuardarTour.clear();
        txtNombreEmpresaGuardarTour.clear();
        txtCostosGuardarTour.setText(null);
        txtHoraLlegadaGuardarTour.setText(null);
        txtHoraSalidaGuardarTour.setText(null);
        txtHoraLlegadaGuardarTour.setText(null);
        tblvInformacionCliente.getItems().clear();

        // txtTipoTourGuardarTour.
    }

    @FXML
    private void onActionBtnModificarTour(ActionEvent event) {
        Guardar();
    }

    @FXML
    private void onSelectionInfoTabPane(Event event) {
        if (tabPaneInformacion.isSelected()) {
            tblvCodigoTour.setCellValueFactory(new PropertyValueFactory<>("trsCodigotour"));
            tblvEmpresa.setCellValueFactory(new PropertyValueFactory<>("trsEmpresacedjur"));
            tblvNombre.setCellValueFactory(new PropertyValueFactory<>("trsNombre"));
            tblvTipoTour.setCellValueFactory(new PropertyValueFactory<>("trsTipotourcodigo"));
            tblvCostoPersona.setCellValueFactory(new PropertyValueFactory<>("trsCostotour"));
            tblvFechaLlegada.setCellValueFactory(new PropertyValueFactory<>("trsFechallegada"));
            tblvHoraLlegada.setCellValueFactory(new PropertyValueFactory<>("trsHorallegada"));
            tblvFecSalida.setCellValueFactory(new PropertyValueFactory<>("trsFechasalida"));
            tblvHoraSalida.setCellValueFactory(new PropertyValueFactory<>("trsHorasalida"));
            tblvCantMaxClientes.setCellValueFactory(new PropertyValueFactory<>("trsCantidadclientes"));

            List<Tour> list = obtenerToursBD();
            ObservableList<Tour> observableList = FXCollections.observableArrayList(list);

            // Asigna los nuevos datos a la TableView
            tblvInformacionCliente.setItems(observableList);
        }

        txtFiltroBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroNombre = newValue;
            List<Tour> list = obtenerToursBD(filtroNombre);
            ObservableList<Tour> observableList = FXCollections.observableArrayList(list);
            tblvInformacionCliente.setItems(observableList);
        });
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
