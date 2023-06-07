/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.administracion_tarea.Model.Cliente;
import com.mariangel.administracion_tarea.Model.Itinerario;
import com.mariangel.administracion_tarea.Model.ItinerarioDto;
import com.mariangel.administracion_tarea.Model.Reserva;
import com.mariangel.administracion_tarea.Model.ReservaDto;
import com.mariangel.administracion_tarea.Model.Tour;
import com.mariangel.administracion_tarea.Service.ReservaService;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ChoiceBox<Tour> choiceBCodigoTour;
    @FXML
    private ChoiceBox<Cliente> choiceBCliente;
    @FXML
    private Label txtCostoTour;

    @FXML
    private DatePicker datePickerFechaReserva;
    @FXML
    private TextField txtCantidadPersonas;
    @FXML
    private Tab tabPaneReservaciones;
    @FXML
    private TableView<Reserva> tblvInformacionCliente;
    @FXML
    private TableColumn<Reserva, String> tblvIdReservaciones;
    @FXML
    private TableColumn<Reserva, Date> tblvFecReserva;
    @FXML
    private TableColumn<Reserva, String> tblvClienteReservaciones;
    @FXML
    private TableColumn<Reserva, String> tblvCodigoTourReservaciones;
    @FXML
    private TableColumn<Reserva, String> tblvMontoAbonadoReservaciones;
    @FXML
    private TableColumn<Reserva, String> tblvTourCostoReservaciones;

    @FXML
    private TextField txtFiltroBusqueda;
    @FXML
    private Button btnBuscarInformacion;

    //Itinerarios
    @FXML
    private Tab tapReservacionesInscribir;

    ReservaDto reserva;
    ItinerarioDto itinerario;

    List<Node> requeridos = new ArrayList<>();
    @FXML
    private TextField txtIdReservacion;
    @FXML
    private TableView<Itinerario> tblvInformacionItinerarios;
    @FXML
    private TableColumn<Itinerario, String> tblvID;
    @FXML
    private TableColumn<Itinerario, String> tblvCodigoTourItinerarios;
    @FXML
    private TableColumn<Itinerario, String> tblvLugar;
    @FXML
    private TableColumn<Itinerario, Long> tblvDuracion;
    @FXML
    private TableColumn<Itinerario, String> tblvActividades;
    @FXML
    private CheckBox checkBoxBusqueda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reserva = new ReservaDto();
        mostrarNombreCliente();
        mostrarCodigoTour();
        obtenerCostoTotalDelTour();
        txtMontoAbonado.setTextFormatter(Formato.getInstance().integerFormat());
        

        nuevaReserva();
        indicarRequeridosReserva();

        List<Tour> empresasList = obtenerCodigoTourBD();
        // Asignar la lista de empresas al ChoiceBox
        ObservableList<Tour> empresaObservableList = FXCollections.observableArrayList(empresasList);
        choiceBCodigoTour.setItems(empresaObservableList);

        List<Cliente> tiposlist = obtenerClienteBD();
        ObservableList<Cliente> tiposObservableList = FXCollections.observableArrayList(tiposlist);
        choiceBCliente.setItems(tiposObservableList);

        choiceBCodigoTour.setOnAction(event -> {
            String filtroTour = choiceBCodigoTour.getValue().getTrsCodigotour();
            List<Itinerario> list = obtenerItinerarios(filtroTour);
            ObservableList<Itinerario> observableList = FXCollections.observableArrayList(list);
            tblvInformacionItinerarios.setItems(observableList);
        }
        );

    }

    private void bindReserva(Boolean nuevo) {
        if (!nuevo) {
            txtIdReservacion.textProperty().bind(reserva.rsId);
        }

        choiceBCodigoTour.valueProperty().bindBidirectional(reserva.rsCodigotour);
        choiceBCliente.valueProperty().bindBidirectional(reserva.rsCedulacliente);
        datePickerFechaReserva.valueProperty().bindBidirectional(reserva.rsFechareserva);
        txtMontoAbonado.textProperty().bindBidirectional(reserva.rsMontoabonado);
        txtCostoTour.textProperty().bindBidirectional(reserva.rsTrscosto);
    }

    private void unbindReserva() {
        txtIdReservacion.textProperty().unbind();
        choiceBCliente.valueProperty().unbindBidirectional(reserva.rsCedulacliente);
        choiceBCodigoTour.valueProperty().unbindBidirectional(reserva.rsCodigotour);
        txtMontoAbonado.textProperty().unbindBidirectional(reserva.rsMontoabonado);
        txtCostoTour.textProperty().unbindBidirectional(reserva.rsTrscosto);
        datePickerFechaReserva.valueProperty().unbindBidirectional(reserva.rsFechareserva);
    }

    public void indicarRequeridosReserva() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtIdReservacion, datePickerFechaReserva, txtCostoTour,
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
        ReservaService service = new ReservaService();
        Respuesta respuesta = service.getReserva(pcedula);
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
            System.out.println(reserva.toString());
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

    public List<Itinerario> obtenerItinerarios(String tour) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Itinerario> itinerariosList = new ArrayList<>();
        try {
            String consulta = "SELECT i FROM Itinerario i";
            if (tour != null && !tour.isEmpty()) {
                consulta += " WHERE i.intCodigotour.trsCodigotour LIKE :tour";
            }
            TypedQuery<Itinerario> query = em.createQuery(consulta, Itinerario.class);
            if (tour != null && !tour.isEmpty()) {
                query.setParameter("tour", "%" + tour + "%");
            }

            itinerariosList = query.getResultList();

        } catch (Exception e) {
            System.out.println("Error al obtener todos los Tours de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return itinerariosList;
    }

    @FXML
    private void onActionBtnEliminarReservas(ActionEvent event) {
        try {
            if (reserva.rsId == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar reserva", getStage(), "Debe cargar el tipo de reserva que desea eliminar.");
            } else {
                ReservaService service = new ReservaService();
                Respuesta respuesta = service.eliminarReserva(reserva.getReservaId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar reserva", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar reserva", getStage(), "reserva eliminado correctamente.");
                    nuevaReserva();
                    // mediaPlayer.play();
                }
            }
            //  recargarItinerarios();
            // activarListenerGenerarCodigo();
        } catch (Exception ex) {
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, "Error eliminando el Reserva.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar reserva", getStage(), "Ocurrio un error eliminando  reserva.");
        }
    }

    @FXML
    private void onActionBtnCancelarReservas(ActionEvent event) {
        datePickerFechaReserva.setValue(null);
        choiceBCodigoTour.setValue(null);
        txtMontoAbonado.setText(null);
        txtCostoTour.setText(null);
        choiceBCliente.setValue(null);
        txtIdReservacion.setText(null);
    }

    @FXML
    private void onActionBtnModificarReservas(ActionEvent event) {
        try {
            unbindReserva();
            String idText = txtIdReservacion.getText();
            ReservaDto reservaDto = new ReservaDto();

            reservaDto.setReservaId(Long.parseLong(txtIdReservacion.getText()));
            reservaDto.setReservaTrscosto(Long.parseLong(txtCostoTour.getText()));
            reservaDto.setReservaMontoabonado(Long.parseLong(txtMontoAbonado.getText()));
            reservaDto.setReservaFecnac(datePickerFechaReserva.getValue());
            reservaDto.setCodigotour(choiceBCodigoTour.getValue());
            reservaDto.setRsCedulaCliente(choiceBCliente.getValue());

            ReservaService reservaService = new ReservaService();
            Respuesta respuesta = reservaService.modificarReserva(reservaDto, Long.parseLong(idText));
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar reserva", getStage(), "reserva actualizado correctamente.");
            //  mediaPlayer.play();
            // recargarreservas();

        } catch (Exception ex) {
            Logger.getLogger(ReservacionController.class.getName()).log(Level.SEVERE, "Error actualizando el reserva.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar reserva", getStage(), "Ocurrio un error al actualizar el reserva.");
        }
    }

    public static List<Cliente> obtenerClienteBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Cliente> clienteList = new ArrayList<>();
        try {
            clienteList = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los Reserva de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return clienteList;
    }
//Consultas ChoiceBox

    public static List<Tour> obtenerCodigoTourBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tour> toursList = new ArrayList<>();
        try {
            toursList = em.createQuery("SELECT t FROM Tour t", Tour.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos las tour de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return toursList;
    }

//obtiene todas la reservas
    public static List<Reserva> obtenerReservaBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Reserva> clienteList = new ArrayList<>();
        try {
            clienteList = em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos las reservas de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return clienteList;
    }

    //Consulta Informacion por 
    public static List<Reserva> obtenerReservaBD(String filtroNombre) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Reserva> reservaList = new ArrayList<>();
        try {
            String consulta = "SELECT r FROM Reserva r";
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                consulta += " WHERE t.rsCedulacliente LIKE :filtroNombre";

            }
            TypedQuery<Reserva> query = em.createQuery(consulta, Reserva.class);
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                query.setParameter("filtroNombre", "%" + filtroNombre + "%");
            }
            reservaList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las empresas de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return reservaList;
    }

    //ajustes en la consulta
    void mostrarCodigoTour() {
        choiceBCodigoTour.setConverter(new StringConverter<Tour>() {
            @Override
            public String toString(Tour tour) {
                return tour != null ? tour.toString() : "";
            }

            @Override
            public Tour fromString(String string) {
                return null;
            }
        });
    }

    void mostrarNombreCliente() {
        choiceBCliente.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente != null ? cliente.toString() : "";
            }

            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void onActionBtnBuscarPorFiltro(ActionEvent event) {
    }

    @FXML
    private void onSelectionReservaciones(Event event) {
        if (tabPaneReservaciones.isSelected()) {
            tblvIdReservaciones.setCellValueFactory(new PropertyValueFactory<>("rsId"));
            tblvFecReserva.setCellValueFactory(new PropertyValueFactory<>("rsFechareserva"));
            tblvClienteReservaciones.setCellValueFactory(new PropertyValueFactory<>("rsCedulacliente"));
            tblvCodigoTourReservaciones.setCellValueFactory(new PropertyValueFactory<>("rsCodigotour"));
            tblvMontoAbonadoReservaciones.setCellValueFactory(new PropertyValueFactory<>("rsMontoabonado"));
            tblvTourCostoReservaciones.setCellValueFactory(new PropertyValueFactory<>("rsTrscosto"));
            obtenerFechaLlegada();

            // recargarItinerarios();
            List<Reserva> list = obtenerReservaBD();
            ObservableList<Reserva> observableList = FXCollections.observableArrayList(list);
            // Asigna los nuevos datos a la TableView
            tblvInformacionCliente.setItems(observableList);
        }

        txtFiltroBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroNombre = newValue;
            List<Reserva> list = obtenerReservaBD(filtroNombre);
            ObservableList<Reserva> observableList = FXCollections.observableArrayList(list);
            tblvInformacionCliente.setItems(observableList);

        });

    }

    @FXML
    private void onSelectionReservacionesInscribir(Event event) {
        if (tapReservacionesInscribir.isSelected()) {
            tblvID.setCellValueFactory(new PropertyValueFactory<>("intId"));
            tblvCodigoTourItinerarios.setCellValueFactory(new PropertyValueFactory<>("intCodigotour"));
            tblvLugar.setCellValueFactory(new PropertyValueFactory<>("intLugar"));
            tblvDuracion.setCellValueFactory(new PropertyValueFactory<>("intDuracion"));
            tblvActividades.setCellValueFactory(new PropertyValueFactory<>("intActividades"));

        }
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

    void obtenerFechaLlegada() {
        tblvFecReserva.setCellFactory(column -> {
            return new TableCell<Reserva, Date>() {
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
      void obtenerCostoTotalDelTour(){
        txtCantidadPersonas.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                Long cantidadPersonas = Long.parseLong(newValue);
                Long costoPorPersona = choiceBCodigoTour.getValue().getTrsCostotour();
                Long costoTotal = cantidadPersonas * costoPorPersona;
                txtCostoTour.setText(String.valueOf(costoTotal));
            }
        });
    }
      private void ActivarBusqueda(){
      if(checkBoxBusqueda.isSelected()){
      btnBuscarClienteReservas.setVisible(true);
      txtIdReservacion.setVisible(true);
      lblIdReservacion
      }
      
      }
      
      
}
