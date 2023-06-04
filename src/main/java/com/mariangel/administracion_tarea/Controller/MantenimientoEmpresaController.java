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
import com.mariangel.administracion_tarea.Model.EmpresaDto;
import com.mariangel.administracion_tarea.Service.EmpresaService;
import com.mariangel.administracion_tarea.Utils.EntityManagerHelper;
import com.mariangel.administracion_tarea.Utils.FlowController;
import com.mariangel.administracion_tarea.Utils.Formato;
import com.mariangel.administracion_tarea.Utils.Mensaje;
import com.mariangel.administracion_tarea.Utils.Respuesta;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
import javafx.scene.control.TabPane;
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
    private TableView<Empresa> tblvInformacionEmpresas;
    @FXML
    private TableColumn<Empresa, String> tblvCedulaEmpresa;
    @FXML
    private TableColumn<Empresa, String> tblvNombreEmpresa;
    @FXML
    private TableColumn<Empresa, Long> tblvTelefono;
    @FXML
    private TableColumn<Empresa, Long> tblvCalificacion;
    @FXML
    private TableColumn<Empresa, Date> tblvAnnoFundacion;
    @FXML
    private TableColumn<Empresa, String> tblvCorreo;
    @FXML
    private TextField txtNombreEmpresaInformacion;
    @FXML
    private Button btnBuscarInformacion;
    private EmpresaDto empresa;
    List<Node> requeridos = new ArrayList<>();
    @FXML
    private TabPane tblv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        empresa = new EmpresaDto();

        txtTelefono.setTextFormatter(Formato.getInstance().integerFormat());
        txtCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(100));
        txtNombreEmpresa.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCedulaJuridica.setTextFormatter(Formato.getInstance().cedulaFormat(30));
        txtCalificacion.setTextFormatter(Formato.getInstance().integerFormat());
        nuevaEmpresa();
        indicarRequeridos();
    }

    private void bindEmpresa(Boolean nuevo) {
        System.out.println(empresa);
        if (!nuevo) {
            txtCedulaJuridica.textProperty().bindBidirectional(empresa.emCedulajuridica);
        }
        txtCedulaJuridica.textProperty().bindBidirectional(empresa.emCedulajuridica);
        txtTelefono.textProperty().bindBidirectional(empresa.emTelefono);
        txtCorreo.textProperty().bindBidirectional(empresa.emCorreo);
        txtNombreEmpresa.textProperty().bindBidirectional(empresa.emNombre);
        txtCalificacion.textProperty().bindBidirectional(empresa.emCalificacion);
        datePickerFecFunda.valueProperty().bindBidirectional(empresa.emFechafundacion);
    }

    private void unbindEmpresa() {
        System.out.println(empresa);
        txtCedulaJuridica.textProperty().unbindBidirectional(empresa.emCedulajuridica);
        txtTelefono.textProperty().unbindBidirectional(empresa.emTelefono);
        txtCorreo.textProperty().unbindBidirectional(empresa.emCorreo);
        txtNombreEmpresa.textProperty().unbindBidirectional(empresa.emNombre);
        txtCalificacion.textProperty().unbindBidirectional(empresa.emCalificacion);
        datePickerFecFunda.valueProperty().unbindBidirectional(empresa.emFechafundacion);
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCedulaJuridica, txtNombreEmpresa, txtTelefono, txtCorreo, txtCalificacion, datePickerFecFunda));
    }

    private void nuevaEmpresa() {
        unbindEmpresa();
        empresa = new EmpresaDto();
        bindEmpresa(true);
        txtCedulaJuridica.clear();
        txtCedulaJuridica.requestFocus();
    }

    private void cargarEmpresa(String pcedula) {
        EmpresaService service = new EmpresaService();

        Respuesta respuesta = service.getEmpresa(pcedula);
        if (respuesta.getEstado()) {
            unbindEmpresa();
            btnModificar.setVisible(true);
            System.out.println("despues del unbind");
            empresa = (EmpresaDto) respuesta.getResultado("Empresa");

            bindEmpresa(false);
            System.out.println("METODO CARGAR Empresa Empresa despues del bind" + pcedula);

            System.out.println("valida requeridos" + pcedula);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empresa", getStage(), respuesta.getMensaje());
        }

    }

    @FXML
    private void onAnctionBtnGuardar(ActionEvent event) {

        try {

            EmpresaService empresaService = new EmpresaService();
            Respuesta respuesta = empresaService.guardarEmpresa(empresa);

            if (!respuesta.getEstado()) {
                System.out.println("else" + empresa.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Empresa", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + empresa.toString());
                unbindEmpresa();
                empresa = (EmpresaDto) respuesta.getResultado("Empresa");
                bindEmpresa(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Empresa", getStage(), "Empresa guardada correctamente.");
            }
        } catch (Exception ex) {
            System.out.println(empresa.toString());
            Logger.getLogger(MantenimientoEmpresaController.class.getName()).log(Level.SEVERE, "Error guardando la empresa.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empresa", getStage(), "Ocurrio un error guardando la empresa.");
        }
         
    }

    private void Guardar() {
        try {

            EmpresaService empresaService = new EmpresaService();
            Respuesta respuesta = empresaService.guardarEmpresa(empresa);

            if (!respuesta.getEstado()) {
                System.out.println("else" + empresa.toString());
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Empresa", getStage(), respuesta.getMensaje());
            } else {
                System.out.println("else" + empresa.toString());
                unbindEmpresa();
                empresa = (EmpresaDto) respuesta.getResultado("Empresa");
                bindEmpresa(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Empresa", getStage(), "Empresa guardada correctamente.");
            }
        } catch (Exception ex) {
            System.out.println(empresa.toString());
            Logger.getLogger(MantenimientoEmpresaController.class.getName()).log(Level.SEVERE, "Error guardando la empresa.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empresa", getStage(), "Ocurrio un error guardando la empresa.");
        }
    }

    @FXML
    private void onActionBtnModificar(ActionEvent event) {
        Guardar();

    }

    @FXML
    private void onActionBuscarEmpresa(ActionEvent event) {
        String cedulaText = txtCedulaJuridica.getText();
        cargarEmpresa(cedulaText);
      
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (empresa.getEmpresaCedJuridica() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empresa", getStage(), "Debe cargar el tipo de Empresa que desea eliminar.");
            } else {

                EmpresaService service = new EmpresaService();
                Respuesta respuesta = service.eliminarEmpresa(empresa.getEmpresaCedJuridica());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empresa", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Empresa", getStage(), "Empresa eliminado correctamente.");
                    nuevaEmpresa();
                    // mediaPlayer.play();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MantenimientoEmpresaController.class.getName()).log(Level.SEVERE, "Error eliminando el Empresa.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empresa", getStage(), "Ocurrio un error eliminando el Empresa.");
        }
    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {

        txtCedulaJuridica.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtNombreEmpresa.clear();
        txtCalificacion.clear();
        datePickerFecFunda.setValue(null);

    }

    @FXML
    private void onSelectionGuardarTabPane(Event event) {

    }

    @FXML
    private void onActionBtnBuscarPorNombre(ActionEvent event) {
    }

    @FXML
    private void onSelectionInfoTabPane(Event event) {
        if (tabPaneInformacion.isSelected()) {
            tblvNombreEmpresa.setCellValueFactory(new PropertyValueFactory<>("emNombre"));
            tblvCedulaEmpresa.setCellValueFactory(new PropertyValueFactory<>("emCedulajuridica"));
            tblvTelefono.setCellValueFactory(new PropertyValueFactory<>("emTelefono"));
            tblvCalificacion.setCellValueFactory(new PropertyValueFactory<>("emCalificacion"));
            tblvAnnoFundacion.setCellValueFactory(new PropertyValueFactory<>("emFechafundacion"));
            tblvCorreo.setCellValueFactory(new PropertyValueFactory<>("emCorreo"));

            List<Empresa> list = obtenerEmpresaBD();
            ObservableList<Empresa> observableList = FXCollections.observableArrayList(list);

            // Asigna los nuevos datos a la TableView
            tblvInformacionEmpresas.setItems(observableList);
        }

        txtNombreEmpresaInformacion.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroNombre = newValue;
            List<Empresa> list = obtenerEmpresaBD(filtroNombre);
            ObservableList<Empresa> observableList = FXCollections.observableArrayList(list);
            tblvInformacionEmpresas.setItems(observableList);
        });
    }

    @Override
    public void initialize() {
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

    public static List<Empresa> obtenerEmpresaBD(String filtroNombre) {
        EntityManager em = EntityManagerHelper.getManager();
        List<Empresa> empresasList = new ArrayList<>();
        try {
            String consulta = "SELECT p FROM Empresa p";
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                consulta += " WHERE p.emNombre LIKE :filtroNombre";
            }
            TypedQuery<Empresa> query = em.createQuery(consulta, Empresa.class);
            if (filtroNombre != null && !filtroNombre.isEmpty()) {
                query.setParameter("filtroNombre", "%" + filtroNombre + "%");
            }
            empresasList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las empresas de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return empresasList;
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
    private void onActionretroceder(ActionEvent event) {
        Stage currentStage = (Stage) btn_retroceder.getScene().getWindow();
        currentStage.close();
        FlowController.getInstance().goMain();
    }
}
