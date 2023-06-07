/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.administracion_tarea.Model.Empresa;
import com.mariangel.administracion_tarea.Model.Itinerario;
import com.mariangel.administracion_tarea.Model.ItinerarioDto;
import com.mariangel.administracion_tarea.Model.Tipotour;
import com.mariangel.administracion_tarea.Model.Tour;
import com.mariangel.administracion_tarea.Model.TourDto;
import com.mariangel.administracion_tarea.Service.ItinerarioService;
import com.mariangel.administracion_tarea.Service.TourService;
import com.mariangel.administracion_tarea.Utils.EntityManagerHelper;
import com.mariangel.administracion_tarea.Utils.FlowController;
import com.mariangel.administracion_tarea.Utils.Formato;
import com.mariangel.administracion_tarea.Utils.Mensaje;
import com.mariangel.administracion_tarea.Utils.Respuesta;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Mari
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
    private TableColumn<Tour, String> tblvCodigoTour;

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
    private TextField txtDuracionGuardarItinerarios;
    @FXML
    private TextField txtLugarGuardarItinerarios;
    @FXML
    private TextField txtIdGuardarItinerarios;
    @FXML
    private TextField txtActividadesGuardarItinerarios;
    @FXML
    private ChoiceBox<Tour> choiceBCodigoTour;
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

    ItinerarioDto itinerario;
    TourDto tour;

    List<Node> requeridos = new ArrayList<>();
    @FXML
    private CheckBox checkBoxActivarBusqueda;
    @FXML
    private Label lblIdItinerarios;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        tour = new TourDto();
        mostrarNombreDelTipo();
        mostrarNombreDeEmpresa();

        List<Empresa> empresasList = obtenerEmpresaBD();
        // Asignar la lista de empresas al ChoiceBox
        ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList(empresasList);
        choiceBCodigoEmpresaGuardarTour.setItems(empresaObservableList);

        List<Tipotour> tiposlist = obtenerTipoTourBD();
        ObservableList<Tipotour> tiposObservableList = FXCollections.observableArrayList(tiposlist);
        choiceBTipoTourGuardarTour.setItems(tiposObservableList);
        activarListenerGenerarCodigo();

        //tour
        txtCodigoTourGuardarTour.setTextFormatter(Formato.getInstance().cedulaFormat(30));
        txtCostosGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtHoraLlegadaGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtHoraSalidaGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtCantidadMaxGuardarTour.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombreTourGuardar.setTextFormatter(Formato.getInstance().letrasFormat(30));

        indicarRequeridosTour();
        nuevoTour();
        //itinerario
        itinerario = new ItinerarioDto();
        txtIdGuardarItinerarios.setTextFormatter(Formato.getInstance().integerFormat());
        txtLugarGuardarItinerarios.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtDuracionGuardarItinerarios.setTextFormatter(Formato.getInstance().integerFormat());
        txtActividadesGuardarItinerarios.setTextFormatter(Formato.getInstance().letrasFormat(150));

        tblvID.setCellValueFactory(new PropertyValueFactory<>("intId"));
        tblvCodigoTourItinerarios.setCellValueFactory(new PropertyValueFactory<>("intCodigotour"));
        tblvLugar.setCellValueFactory(new PropertyValueFactory<>("intLugar"));
        tblvDuracion.setCellValueFactory(new PropertyValueFactory<>("intDuracion"));
        tblvActividades.setCellValueFactory(new PropertyValueFactory<>("intActividades"));

        List<Tour> Codigoslist = obtenerTourCodigoBD();
        ObservableList<Tour> ToursObservableList = FXCollections.observableArrayList(Codigoslist);
        choiceBCodigoTour.setItems(ToursObservableList);

        nuevoItinerario();
        indicarRequeridosItinerario();

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

    public void indicarRequeridosTour() {
        System.out.println(" ENTRO AL indicar requeridos de cargar paciente");
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCodigoTourGuardarTour,
                choiceBTipoTourGuardarTour, choiceBCodigoEmpresaGuardarTour,
                txtCostosGuardarTour, txtHoraLlegadaGuardarTour, txtHoraSalidaGuardarTour,
                datePickerFecLlegadaGuardarTour, datePickerFecSalidaGuardarTour,
                txtNombreTourGuardar, txtCantidadMaxGuardarTour));
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
            desactivarListenerGenerarCodigo();

            unbindTour();
            btnModificarTour.setVisible(true);

            tour = (TourDto) respuesta.getResultado("Tour");

            bindTour(false);

            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar tour", getStage(), respuesta.getMensaje());
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
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error eliminando el Tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tour", getStage(), "Ocurrio un error eliminando el Tour.");
        }
    }

    @FXML
    private void onActionBtnCancelarTour(ActionEvent event) {
        desactivarListenerGenerarCodigo();

        datePickerFecLlegadaGuardarTour.setValue(null);
        datePickerFecSalidaGuardarTour.setValue(null);
        choiceBCodigoEmpresaGuardarTour.setValue(null);
        choiceBTipoTourGuardarTour.setValue(null);
        txtCostosGuardarTour.setText(null);
        txtHoraLlegadaGuardarTour.setText(null);
        txtHoraSalidaGuardarTour.setText(null);
        txtHoraLlegadaGuardarTour.setText(null);
        txtCantidadMaxGuardarTour.setText(null);
        txtNombreTourGuardar.setText(null);

        tblvInformacionCliente.getItems().clear();
        activarListenerGenerarCodigo();
        txtCodigoTourGuardarTour.clear();
    }

    @FXML
    private void onActionBtnModificarTour(ActionEvent event) {
        try {
            unbindTour();
            unbindItinerarios();
            String idText = txtIdGuardarItinerarios.getText();
            ItinerarioDto itinerarioDto = new ItinerarioDto();

            String cedulaText = txtCodigoTourGuardarTour.getText();
            TourDto tourDto = new TourDto();
            tourDto.setTrsNombre(txtNombreTourGuardar.getText());
            tourDto.setTrsClientes(Long.parseLong(txtCantidadMaxGuardarTour.getText()));
            tourDto.setTrsFechallegada(datePickerFecLlegadaGuardarTour.getValue());
            tourDto.setTrsFechasalida(datePickerFecSalidaGuardarTour.getValue());
            tourDto.setTrsHorallegada(Short.parseShort(txtHoraLlegadaGuardarTour.getText()));
            tourDto.setTrsHorasalida(Short.parseShort(txtHoraSalidaGuardarTour.getText()));
            tourDto.setTrsCosto(Long.parseLong(txtCostosGuardarTour.getText()));
            tourDto.setTrsCodigo(txtCodigoTourGuardarTour.getText());
            tourDto.setTEmpresaCedJur(choiceBCodigoEmpresaGuardarTour.getValue());
            tourDto.setTipoToursCodigo(choiceBTipoTourGuardarTour.getValue());

            TourService pacientesService = new TourService();
            Respuesta respuesta = pacientesService.modificarTour(tourDto, cedulaText);
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar paciente", getStage(), "Tour actualizado correctamente.");
            //  mediaPlayer.play();
        } catch (Exception ex) {
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error actualizando el Tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar Tour", getStage(), "Ocurrio un error al actualizar el Tour.");
        }
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
            obtenerFechaLlegada();
            obtenerFechaSalida();
            List<Tour> list = obtenerToursBD();
            ObservableList<Tour> observableList = FXCollections.observableArrayList(list);

            // Asigna los nuevos datos a la TableView
            tblvInformacionCliente.setItems(observableList);
        }
    }

    @FXML
    private void onSelectionGuardarTabPane(Event event) {
    }

    @FXML
    private void onActionBtnBuscarPorFiltro(ActionEvent event) {
    }

    //Ajustes a la consulta
    void mostrarNombreDeEmpresa() {
        choiceBCodigoEmpresaGuardarTour.setConverter(new StringConverter<Empresa>() {
            @Override
            public String toString(Empresa empresa) {
                return empresa != null ? empresa.toString() : "";
            }

            @Override
            public Empresa fromString(String string) {
                return null;
            }
        });
    }

    void mostrarNombreDelTipo() {
        choiceBTipoTourGuardarTour.setConverter(new StringConverter<Tipotour>() {
            @Override
            public String toString(Tipotour tipotour) {
                return tipotour != null ? tipotour.toString() : "";
            }

            @Override
            public Tipotour fromString(String string) {
                return null;
            }
        });
    }

//Consultas
    @FXML
    private void onActionFiltroNombreTour(ActionEvent event) {

        txtFiltroBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroTour = newValue;
            List<Tour> list = obtenerTourBD(filtroTour);
            ObservableList<Tour> observableList = FXCollections.observableArrayList(list);
            tblvInformacionCliente.setItems(observableList);
        });
    }

    @FXML
    private void onActionFiltroEmpresa(ActionEvent event) {

        txtFiltroBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroEmpresa = newValue;
            List<Tour> list = obtenerToursPorNombreEmpresa(filtroEmpresa);
            ObservableList<Tour> observableList = FXCollections.observableArrayList(list);
            tblvInformacionCliente.setItems(observableList);
        });
    }

    @FXML
    private void onActionFiltroLugar(ActionEvent event) {
        txtFiltroBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroLugar = newValue;
            List<Tour> list = obtenerToursPorPais(filtroLugar);
            ObservableList<Tour> observableList = FXCollections.observableArrayList(list);
            tblvInformacionCliente.setItems(observableList);
        });
    }

    public static List<Tour> obtenerTourBD(String filtroNombre) {
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
            System.out.println("Error al obtener todos los Tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tourList;
    }

    public List<Tour> obtenerToursPorNombreEmpresa(String nombreEmpresa) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tour> tourList = new ArrayList<>();
        try {
            String consulta = "SELECT t FROM Tour t";
            if (nombreEmpresa != null && !nombreEmpresa.isEmpty()) {
                consulta += " WHERE t.trsEmpresacedjur.emNombre LIKE :nombreEmpresa";
            }
            TypedQuery<Tour> query = em.createQuery(consulta, Tour.class);
            if (nombreEmpresa != null && !nombreEmpresa.isEmpty()) {
                query.setParameter("nombreEmpresa", "%" + nombreEmpresa + "%");
            }

            tourList = query.getResultList();

        } catch (Exception e) {
            System.out.println("Error al obtener todos los Tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tourList;
    }

    public List<Tour> obtenerToursPorPais(String nombrePais) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tour> tourList = new ArrayList<>();
        try {
            String consulta = "SELECT t FROM Tour t";
            if (nombrePais != null && !nombrePais.isEmpty()) {
                consulta += " WHERE t.trsTipotourcodigo.ttPais LIKE :nombreEmpresa";
            }
            TypedQuery<Tour> query = em.createQuery(consulta, Tour.class);
            if (nombrePais != null && !nombrePais.isEmpty()) {
                query.setParameter("nombreEmpresa", "%" + nombrePais + "%");
            }

            tourList = query.getResultList();

        } catch (Exception e) {
            System.out.println("Error al obtener todos los Tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tourList;

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

    //Genera codigo
    private void activarListenerGenerarCodigo() {
        txtNombreTourGuardar.textProperty().addListener(generarCodigoListener);

    }

    private void desactivarListenerGenerarCodigo() {
        txtNombreTourGuardar.textProperty().removeListener(generarCodigoListener);

    }
    private final ChangeListener<String> generarCodigoListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            String nuevoCodigo = generarCodigoTour();

            txtCodigoTourGuardarTour.setText(nuevoCodigo);
        }
    };

    private String generarCodigoTour() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(900) + 100;
        String nombre = "TOUR";
        String primerasCuatroLetras = nombre.substring(0, Math.min(nombre.length(), 4));
        String codigo = primerasCuatroLetras + "-" + String.valueOf(numeroAleatorio);
        return codigo;

    }

    //ITINERARIOS
    private void bindItinerarios(Boolean nuevo) {
        if (!nuevo) {
            txtIdGuardarItinerarios.textProperty().bind(itinerario.intId);
        }
        txtLugarGuardarItinerarios.textProperty().bindBidirectional(itinerario.intLugar);
        choiceBCodigoTour.valueProperty().bindBidirectional(itinerario.intCodigotour);
        txtDuracionGuardarItinerarios.textProperty().bindBidirectional(itinerario.intDuracion);
        txtActividadesGuardarItinerarios.textProperty().bindBidirectional(itinerario.intActividades);
    }

    private void unbindItinerarios() {
        txtIdGuardarItinerarios.textProperty().unbind();
        txtLugarGuardarItinerarios.textProperty().unbindBidirectional(itinerario.intLugar);
        choiceBCodigoTour.valueProperty().unbindBidirectional(itinerario.intCodigotour);
        txtDuracionGuardarItinerarios.textProperty().unbindBidirectional(itinerario.intDuracion);
        txtActividadesGuardarItinerarios.textProperty().unbindBidirectional(itinerario.intActividades);
    }

    public void indicarRequeridosItinerario() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtIdGuardarItinerarios, txtLugarGuardarItinerarios,
                choiceBCodigoTour, txtDuracionGuardarItinerarios, txtActividadesGuardarItinerarios));
    }

    private void nuevoItinerario() {
        System.out.println(" ENTRO AL Nuevo paciente  de cargar paciente");
        unbindItinerarios();
        itinerario = new ItinerarioDto();
        bindItinerarios(true);
        txtIdGuardarItinerarios.clear();
        txtIdGuardarItinerarios.requestFocus();
    }

    private void cargarItinerario(Long pcedula) {
        ItinerarioService service = new ItinerarioService();
        Respuesta respuesta = service.getItinerario(pcedula);
        if (respuesta.getEstado()) {
            unbindItinerarios();
            itinerario = (ItinerarioDto) respuesta.getResultado("Itinerario");
            bindItinerarios(false);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Itinerario", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onActionBuscarItinerario(ActionEvent event) {
        String idText = txtIdGuardarItinerarios.getText();
        Long id = Long.parseLong(idText);
        cargarItinerario(id);

    }

    @FXML
    private void onAnctionBtnGuardarItinerario(ActionEvent event) {
        Itinerario nuevoItinerario = new Itinerario();

        try {

            ItinerarioService itinerarioService = new ItinerarioService();
            Respuesta respuesta = itinerarioService.guardarItinerario(itinerario);

            if (!respuesta.getEstado()) {
                System.out.println("else" + itinerario.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar itinerario", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + itinerario.toString());
                unbindTour();
                itinerario = (ItinerarioDto) respuesta.getResultado("Itinerario");
                bindTour(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar itinerario", getStage(), "Itinerario guardado correctamente.");
            }
            recargarItinerarios();

            // activarListenerGenerarCodigo();
        } catch (Exception ex) {
            System.out.println(itinerario.toString());
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error guardando el itinerario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar itinerario", getStage(), "Ocurrio un error guardando el itinerario.");
        }
    }

    @FXML
    private void onActionBtnEliminarItinerario(ActionEvent event) {
        try {
            if (itinerario.intId == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tour", getStage(), "Debe cargar el tipo de tour que desea eliminar.");
            } else {
                ItinerarioService service = new ItinerarioService();
                Respuesta respuesta = service.eliminarItinerario(itinerario.getItinerarioId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar itinerario", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar itinerario", getStage(), "itinerario eliminado correctamente.");
                    nuevoTour();
                    // mediaPlayer.play();
                }
            }
            recargarItinerarios();
            // activarListenerGenerarCodigo();
        } catch (Exception ex) {
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error eliminando el Tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tour", getStage(), "Ocurrio un error eliminando el Tour.");
        }
    }

    @FXML
    private void onActionBtnCancelarItinerario(ActionEvent event) {
        // desactivarListenerGenerarCodigo();
        txtIdGuardarItinerarios.setText(null);
        choiceBCodigoTour.setValue(null);
        txtLugarGuardarItinerarios.setText(null);
        txtDuracionGuardarItinerarios.setText(null);
        txtActividadesGuardarItinerarios.setText(null);

        // activarListenerGenerarCodigo();
        txtIdGuardarItinerarios.setText(null);
    }

    @FXML
    private void onActionBtnModificarItinerario(ActionEvent event) {
        try {
            unbindItinerarios();

            String idText = txtIdGuardarItinerarios.getText();
            ItinerarioDto itinerarioDto = new ItinerarioDto();

            itinerarioDto.setItinerarioActividades(txtActividadesGuardarItinerarios.getText());
            itinerarioDto.setItinerarioDuracion(Short.parseShort(txtDuracionGuardarItinerarios.getText()));
            itinerarioDto.setItinerarioLugar(txtLugarGuardarItinerarios.getText());
            itinerarioDto.setItinerarioId(Long.parseLong(txtIdGuardarItinerarios.getText()));
            itinerarioDto.setTourCodigo(choiceBCodigoTour.getValue());

            ItinerarioService itinerarioService = new ItinerarioService();
            Respuesta respuesta = itinerarioService.modificarTour(itinerarioDto, Long.parseLong(idText));
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar Itinerario", getStage(), "Itinerario actualizado correctamente.");
            //  mediaPlayer.play();

            recargarItinerarios();

        } catch (Exception ex) {
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error actualizando el Itinerario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar Itinerario", getStage(), "Ocurrio un error al actualizar el Itinerario.");
        }
    }

    public static List<Tour> obtenerTourCodigoBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tour> tipoToursList = new ArrayList<>();
        try {
            tipoToursList = em.createQuery("SELECT t FROM Tour t", Tour.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los Tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tipoToursList;
    }

//      public static List<Tour> TourCodigoBD()(String filtroNombre) {
//        EntityManager em = EntityManagerHelper.getManager();
//        List<Tour> tourList = new ArrayList<>();
//        try {
//            String consulta = "SELECT t FROM Tour t";
//            if (filtroNombre != null && !filtroNombre.isEmpty()) {
//                consulta += " WHERE t.trsNombre LIKE :filtroNombre";
//            }
//            TypedQuery<Tour> query = em.createQuery(consulta, Tour.class);
//            if (filtroNombre != null && !filtroNombre.isEmpty()) {
//                query.setParameter("filtroNombre", "%" + filtroNombre + "%");
//            }
//            tourList = query.getResultList();
//        } catch (Exception e) {
//            System.out.println("Error al obtener todas las tours de la base de datos");
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        return tourList;
//    }
    public static List<Itinerario> obtenerItinerariosBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Itinerario> itinerarioList = new ArrayList<>();
        try {
            itinerarioList = em.createQuery("SELECT i FROM Itinerario i", Itinerario.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los Tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return itinerarioList;
    }

    @FXML
    private void onSelectionGuardarItinerariosTabPane(Event event) {
        if (tabPaneGuardarItinerarios.isSelected()) {
            tblvID.setCellValueFactory(new PropertyValueFactory<>("intId"));
            tblvCodigoTourItinerarios.setCellValueFactory(new PropertyValueFactory<>("intCodigotour"));
            tblvLugar.setCellValueFactory(new PropertyValueFactory<>("intLugar"));
            tblvDuracion.setCellValueFactory(new PropertyValueFactory<>("intDuracion"));
            tblvActividades.setCellValueFactory(new PropertyValueFactory<>("intActividades"));

            recargarItinerarios();
        }
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

    void obtenerFechaSalida() {
        tblvFecSalida.setCellFactory(column -> {
            return new TableCell<Tour, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Establecer el formato de fecha medio
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = dateFormat.format(item);

                        setText(formattedDate);
                    }
                }
            };
        });
    }

    void obtenerFechaLlegada() {
        tblvFechaLlegada.setCellFactory(column -> {
            return new TableCell<Tour, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Establecer el formato de fecha medio
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = dateFormat.format(item);

                        setText(formattedDate);
                    }
                }
            };
        });
    }

    private void recargarItinerarios() {
        List<Itinerario> list = obtenerItinerariosBD();
        ObservableList<Itinerario> itinearariosList = FXCollections.observableArrayList(list);
        tblvInformacionItinerarios.setItems(itinearariosList);
    }

    @FXML
    private void onActionActivarBusqueda(ActionEvent event) {
        if (checkBoxActivarBusqueda.isSelected()) {
            btnBuscarClienteItinerarios.setVisible(true);
            txtIdGuardarItinerarios.setVisible(true);
            lblIdItinerarios.setVisible(true);
        } else {
            btnBuscarClienteItinerarios.setVisible(false);
            txtIdGuardarItinerarios.setVisible(false);
            lblIdItinerarios.setVisible(false);
        }
    }

}
