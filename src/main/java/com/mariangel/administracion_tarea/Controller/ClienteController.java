/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.administracion_tarea.Model.ClienteDto;
import com.mariangel.administracion_tarea.Model.Cliente;
import com.mariangel.administracion_tarea.Service.ClienteService;
import com.mariangel.administracion_tarea.Utils.EntityManagerHelper;
import com.mariangel.administracion_tarea.Utils.FlowController;
import com.mariangel.administracion_tarea.Utils.Formato;
import com.mariangel.administracion_tarea.Utils.Mensaje;
import com.mariangel.administracion_tarea.Utils.Respuesta;
import java.net.URL;
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
import javafx.scene.control.DatePicker;
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
    private TableColumn<Cliente, String> tblvCedula;
    @FXML
    private TableColumn<Cliente, String> tblvNombre;
    @FXML
    private TableColumn<Cliente, String> tblvPApellido;
    @FXML
    private TableColumn<Cliente, String> tblvSApellido;
    @FXML
    private TableColumn<Cliente, Long> tblvTelefono;
    @FXML
    private TableColumn<Cliente, String> tblvCorreo;
    @FXML
    private TableColumn<Cliente, Date> tblvFecNac;

    ClienteDto cliente;
    List<Node> requeridos = new ArrayList<>();
    @FXML
    private TableView<Cliente> tblvInformacionCliente;
    @FXML
    private TextField txtNombreClienteInformacion;
    @FXML
    private Button btnBuscarInformacion;

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

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCedula, txtNombreCliente, txtPApellido, txtSApellido, txtCorreo, txtTelefono, datePickerFecNac));
    }

    private void nuevoCliente() {
        unbindCliente();
        cliente = new ClienteDto();
        bindCliente(true);
        txtCedula.clear();
        txtCedula.requestFocus();
    }
    
    @FXML
    private void onActionBuscarCliente(ActionEvent event) {
        String cedulaText = txtCedula.getText();
        cargarCliente(cedulaText);
    }

    private void cargarCliente(String pcedula) {
        ClienteService service = new ClienteService();
        Respuesta respuesta = service.getCliente(pcedula);

        if (respuesta.getEstado()) {
            unbindCliente();
            cliente = (ClienteDto) respuesta.getResultado("Cliente");
            bindCliente(false);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar cliente", getStage(), respuesta.getMensaje());
        }

    }

    public static List<Cliente> obtenerClientesBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Cliente> clientesList = new ArrayList<>();
        try {
            clientesList = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();

        } catch (Exception e) {
            System.out.println("Error al obtener todos los clientes de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return clientesList;
    }

    public static List<Cliente> obtenerClientesBD(String filtroNombre) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Cliente> clientesList = new ArrayList<>();
        try {
            String consulta = "SELECT c FROM Cliente c";
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                consulta += " WHERE c.cltNombre LIKE :filtroNombre";
            }
            TypedQuery<Cliente> query = em.createQuery(consulta, Cliente.class);
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                query.setParameter("filtroNombre", "%" + filtroNombre + "%");
            }
            clientesList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las clientes de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return clientesList;
    }

    @FXML
    private void onAnctionBtnGuardar(ActionEvent event) {
        try {

            ClienteService clienteService = new ClienteService();
            Respuesta respuesta = clienteService.guardarCliente(cliente);

            if (!respuesta.getEstado()) {
                System.out.println("else" + cliente.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Cliente", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + cliente.toString());
                unbindCliente();
                cliente = (ClienteDto) respuesta.getResultado("Cliente");
                bindCliente(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Cliente", getStage(), "Cliente guardada correctamente.");
            }
        } catch (Exception ex) {
            System.out.println(cliente.toString());
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "Error guardando la cliente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar cliente", getStage(), "Ocurrio un error guardando la cliente.");
        }
    }

    private void Guardar() {
        try {

            ClienteService clienteService = new ClienteService();
            Respuesta respuesta = clienteService.guardarCliente(cliente);

            if (!respuesta.getEstado()) {
                System.out.println("else" + cliente.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Cliente", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + cliente.toString());
                unbindCliente();
                cliente = (ClienteDto) respuesta.getResultado("Cliente");
                bindCliente(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Cliente", getStage(), "Cliente guardada correctamente.");
            }
        } catch (Exception ex) {
            System.out.println(cliente.toString());
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "Error guardando la cliente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar cliente", getStage(), "Ocurrio un error guardando la cliente.");
        }
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (cliente.getClienteCedula() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cliente", getStage(), "Debe cargar el tipo de Cliente que desea eliminar.");
            } else {

                ClienteService service = new ClienteService();
                Respuesta respuesta = service.eliminarCliente(cliente.getClienteCedula());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cliente", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Cliente", getStage(), "Cliente eliminado correctamente.");
                    nuevoCliente();
                    // mediaPlayer.play();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "Error eliminando el Cliente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cliente", getStage(), "Ocurrio un error eliminando el Cliente.");
        }
    }

    @FXML
    private void onActionBtnModificar(ActionEvent event) {
        Guardar();
    }

    @FXML
    private void onSelectionInfoTabPane(Event event) {
        if (tabPaneInformacion.isSelected()) {
            tblvCedula.setCellValueFactory(new PropertyValueFactory<>("cltCedula"));
            tblvNombre.setCellValueFactory(new PropertyValueFactory<>("cltNombre"));
            tblvTelefono.setCellValueFactory(new PropertyValueFactory<>("cltTelefono"));
            tblvPApellido.setCellValueFactory(new PropertyValueFactory<>("cltPapellido"));
            tblvSApellido.setCellValueFactory(new PropertyValueFactory<>("cltSapellido"));
            tblvCorreo.setCellValueFactory(new PropertyValueFactory<>("cltCorreo"));
            tblvFecNac.setCellValueFactory(new PropertyValueFactory<>("cltFechanac"));

            List<Cliente> list = obtenerClientesBD();
            ObservableList<Cliente> observableList = FXCollections.observableArrayList(list);

            // Asigna los nuevos datos a la TableView
            tblvInformacionCliente.setItems(observableList);
        }

        txtNombreClienteInformacion.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroNombre = newValue;
            List<Cliente> list = obtenerClientesBD(filtroNombre);
            ObservableList<Cliente> observableList = FXCollections.observableArrayList(list);
            tblvInformacionCliente.setItems(observableList);
        });
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
    private void onActionBtnBuscarPorNombre(ActionEvent event) {

    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
        datePickerFecNac.setValue(null);
        txtCorreo.clear();
        txtCedula.clear();
        txtSApellido.setText(null);
        txtNombreCliente.setText(null);
        txtPApellido.setText(null);
        txtTelefono.setText(null);
        tblvInformacionCliente.getItems().clear();

    }

    @FXML
    private void onSelectionGuardarTabPane(Event event) {

    }

    @FXML
    private void onActionretroceder(ActionEvent event) {
        Stage currentStage = (Stage) btn_retroceder.getScene().getWindow();
        currentStage.close();
        FlowController.getInstance().goMain();
    }
}
