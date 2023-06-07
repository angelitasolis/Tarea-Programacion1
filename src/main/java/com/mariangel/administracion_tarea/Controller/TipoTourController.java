/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.administracion_tarea.Model.TipoTourDto;
import com.mariangel.administracion_tarea.Model.Tipotour;
import com.mariangel.administracion_tarea.Service.TipotourService;
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
 * @author Mari
 */
public class TipoTourController extends Controller implements Initializable {

    @FXML
    private Button btn_retroceder;
    @FXML
    private Tab tabPaneGuardar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button btnBuscarTipoTour;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnModificar;
    @FXML
    private TextField ttNombre;
    @FXML
    private TextField txtPais;
    @FXML
    private MenuButton menuBtn;
    @FXML
    private MenuItem menuBtnNacional;
    @FXML
    private MenuItem menuBtnInternacional;
    @FXML
    private Tab tabPaneInformacion;
    @FXML
    private TableView<Tipotour> tblvInformacionTipoTour;
    @FXML
    private TableColumn<Tipotour, Long> tblvCodigo;
    @FXML
    private TableColumn<Tipotour, String> tblvTipoTour;
    @FXML
    private TableColumn<Tipotour, String> tblvNombre;
    @FXML
    private TableColumn<Tipotour, String> tblvPais;
    @FXML
    private TextField txtCodigoInformacion;
    private TipoTourDto tipotour;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipotour = new TipoTourDto();
        txtPais.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCodigo.setTextFormatter(Formato.getInstance().integerFormat());
        ttNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));

        nuevoTipoTour();
        indicarRequeridos();
        activarListenerGenerarCodigo();

    }

    @Override
    public void initialize() {

    }

    private void bindTipoTour(Boolean nuevo) {

        if (!nuevo) {
            txtCodigo.textProperty().bindBidirectional(tipotour.ttCodigo);
        }
        txtCodigo.textProperty().bindBidirectional(tipotour.ttCodigo);
        ttNombre.textProperty().bindBidirectional(tipotour.ttNombretour);
        txtPais.textProperty().bindBidirectional(tipotour.ttPais);
        menuBtn.textProperty().bindBidirectional(tipotour.ttTipo);

    }

    private void unbindTipoTour() {

        txtCodigo.textProperty().unbindBidirectional(tipotour.ttCodigo);
        ttNombre.textProperty().unbindBidirectional(tipotour.ttNombretour);
        txtPais.textProperty().unbindBidirectional(tipotour.ttPais);
        menuBtn.textProperty().unbindBidirectional(tipotour.ttTipo);
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCodigo, ttNombre, txtPais, menuBtn));

    }

    private void nuevoTipoTour() {
        unbindTipoTour();
        tipotour = new TipoTourDto();
        bindTipoTour(true);
        txtCodigo.clear();
        txtCodigo.requestFocus();
    }

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

    private void desactivarListenerGenerarCodigo() {
        ttNombre.textProperty().removeListener(generarCodigoListener);
        txtPais.textProperty().removeListener(generarCodigoListener);
        menuBtn.textProperty().removeListener(generarCodigoListener);

    }

    private void activarListenerGenerarCodigo() {
        ttNombre.textProperty().addListener(generarCodigoListener);
        txtPais.textProperty().addListener(generarCodigoListener);
        menuBtn.textProperty().addListener(generarCodigoListener);
    }
    private final ChangeListener<String> generarCodigoListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            Long nuevoCodigo = generarCodigoTipoTour();
            txtCodigo.setText(nuevoCodigo.toString());
        }
    };

    private void cargarCodigo(Long pcodigo) {
        TipotourService service = new TipotourService();
        Respuesta respuesta = service.getTipotour(pcodigo);

        if (respuesta.getEstado()) {
            desactivarListenerGenerarCodigo();
            unbindTipoTour();
            btnModificar.setVisible(true);

            tipotour = (TipoTourDto) respuesta.getResultado("Tipotour");

            bindTipoTour(false);
            
            
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar tipotour", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onAnctionBtnGuardar(ActionEvent event) {

        try {

            TipotourService tipoTourService = new TipotourService();
            Respuesta respuesta = tipoTourService.guardarTipotour(tipotour);

            if (!respuesta.getEstado()) {
                System.out.println("else" + tipotour.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipotour", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + tipotour.toString());
                unbindTipoTour();
                tipotour = (TipoTourDto) respuesta.getResultado("Tipotour");
                bindTipoTour(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Tipotour", getStage(), "Tipotour guardada correctamente.");
            }
            activarListenerGenerarCodigo();
        } catch (Exception ex) {
            System.out.println(tipotour.toString());
            Logger.getLogger(TipoTourController.class.getName()).log(Level.SEVERE, "Error guardando la tipotour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipotour", getStage(), "Ocurrio un error guardando la tipotour.");
        }

    }

    private void Guardar() {
        try {

            TipotourService tipoTourService = new TipotourService();
            Respuesta respuesta = tipoTourService.guardarTipotour(tipotour);

            if (!respuesta.getEstado()) {
                System.out.println("else" + tipotour.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipotour", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + tipotour.toString());
                unbindTipoTour();
                tipotour = (TipoTourDto) respuesta.getResultado("Tipotour");
                bindTipoTour(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Tipotour", getStage(), "Tipotour modificado correctamente.");
            }
        } catch (Exception ex) {
            System.out.println(tipotour.toString());
            Logger.getLogger(TipoTourController.class.getName()).log(Level.SEVERE, "Error guardando la tipotour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipotour", getStage(), "Ocurrio un error guardando la tipotour.");
        }
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (tipotour.getTptCodigo() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tipo Tour", getStage(), "Debe cargar el tipo de Tipo Tour que desea eliminar.");
            } else {

                TipotourService service = new TipotourService();
                Respuesta respuesta = service.eliminarTipotour(tipotour.getTptCodigo());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tipo tour", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Tipo tour", getStage(), "Tipo tour eliminado correctamente.");
                    nuevoTipoTour();
                    // mediaPlayer.play();
                }
            }
            activarListenerGenerarCodigo();
        } catch (Exception ex) {
            Logger.getLogger(TipoTourController.class.getName()).log(Level.SEVERE, "Error eliminando el Tipo Tour.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tipo Tour", getStage(), "Ocurrio un error eliminando el Tipo Tour.");
        }
    }

    @FXML
    private void onActionBuscarCodigo(ActionEvent event) {
        String codigoText = txtCodigo.getText();
         desactivarListenerGenerarCodigo();
        try {
            Long codigoLong = Long.parseLong(codigoText);
            cargarCodigo(codigoLong);
        } catch (NumberFormatException e) {
            System.out.println("Invalido formato.");
        }
        
    }

    @FXML
    private void onActionBtnModificar(ActionEvent event) {
        Guardar();
    }

    @FXML
    private void onActionmenuBtnNacional(ActionEvent event) {
        menuBtn.setText("NACIONAL");
    }

    @FXML
    private void onActionmenuBtnInternacional(ActionEvent event) {
        menuBtn.setText("INTERNACIONAL");
    }

    @FXML
    private void onSelectionInfoTabPane(Event event) {
        if (tabPaneInformacion.isSelected()) {
            tblvCodigo.setCellValueFactory(new PropertyValueFactory<>("ttCodigo"));
            tblvTipoTour.setCellValueFactory(new PropertyValueFactory<>("ttTipo"));
            tblvNombre.setCellValueFactory(new PropertyValueFactory<>("ttNombretour"));
            tblvPais.setCellValueFactory(new PropertyValueFactory<>("ttPais"));

            List<Tipotour> list = obtenerTipoTourBD();
            ObservableList<Tipotour> observableList = FXCollections.observableArrayList(list);

            // Asigna los nuevos datos a la TableView
            tblvInformacionTipoTour.setItems(observableList);
        }

        txtCodigoInformacion.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroNombre = newValue;
            List<Tipotour> list = obtenerTipoTourBD(filtroNombre);
            ObservableList<Tipotour> observableList = FXCollections.observableArrayList(list);
            tblvInformacionTipoTour.setItems(observableList);
        });
    }

    public static List<Tipotour> obtenerTipoTourBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tipotour> tipoTourList = new ArrayList<>();
        try {
            tipoTourList = em.createQuery("SELECT t FROM Tipotour t", Tipotour.class).getResultList();

        } catch (Exception e) {
            System.out.println("Error al obtener todos las tipos de tour de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tipoTourList;

    }

    public static List<Tipotour> obtenerTipoTourBD(String filtroNombre) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Tipotour> tipoTourList = new ArrayList<>();
        try {
            String consulta = "SELECT t FROM Tipotour t";
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                consulta += " WHERE t.ttNombretour LIKE :filtroNombre";

            }
            TypedQuery<Tipotour> query = em.createQuery(consulta, Tipotour.class);
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                query.setParameter("filtroNombre", "%" + filtroNombre + "%");
            }
            tipoTourList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las empresas de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tipoTourList;
    }

    @FXML
    private void onActionretroceder(ActionEvent event) {

        Stage currentStage = (Stage) btn_retroceder.getScene().getWindow();
        currentStage.close();
        FlowController.getInstance().goMain();
    }

    @FXML
    private void onSelectionGuardarTabPane(Event event) {
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

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
        desactivarListenerGenerarCodigo();
       
        ttNombre.clear();
        txtPais.clear();
        menuBtn.setText(null);
        activarListenerGenerarCodigo();
        txtCodigo.clear();

    }

}
