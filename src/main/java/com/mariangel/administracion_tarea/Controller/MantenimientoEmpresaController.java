/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariangel.administracion_Tarea.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.administracion_tarea.Model.EmpresaDto;
import com.mariangel.administracion_tarea.Service.EmpresaService;
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
    private TableView<?> tblvInformacionEmpresas;
    @FXML
    private TableColumn<?, ?> tblvCedulaEmpresa;
    @FXML
    private TableColumn<?, ?> tblvNombreEmpresa;
    @FXML
    private TableColumn<?, ?> tblvTelefono;
    @FXML
    private TableColumn<?, ?> tblvCalificacion;
    @FXML
    private TableColumn<?, ?> tblvAnnoFundacion;
    @FXML
    private TableColumn<?, ?> tblvCorreo;
    @FXML
    private TextField txtNombreEmpresaInformacion;
    @FXML
    private Button btnBuscarInformacion;
    EmpresaDto empresa;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        empresa = new EmpresaDto();

        txtTelefono.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCorreo.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtNombreEmpresa.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCedulaJuridica.setTextFormatter(Formato.getInstance().integerFormat());
        txtCalificacion.setTextFormatter(Formato.getInstance().integerFormat());

    }

    private void bindEmpresa(Boolean nuevo) {
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
        System.out.println(" ENTRO AL Nuevo empresa  de cargar empresa");
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
            System.out.println("despues del unbind");
            empresa = (EmpresaDto) respuesta.getResultado("Empresas");

            bindEmpresa(false);
            System.out.println("METODO CARGAR Empresa Empresa despues del bind" + pcedula);
            validarRequeridos();
            System.out.println("valida requeridos" + pcedula);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empresa", getStage(), respuesta.getMensaje());
        }

    }

    @FXML
    private void onAnctionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();

            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empresa", getStage(), invalidos);
            } else {
                EmpresaService empresaService = new EmpresaService();
                System.out.println(empresa);
                Respuesta respuesta = empresaService.guardarEmpresa(empresa);

                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empresa", getStage(), respuesta.getMensaje());
                } else {
                    unbindEmpresa();
                    empresa = (EmpresaDto) respuesta.getResultado("Empresas");
                    System.out.println("aqui estoy" + empresa.toString());
                    bindEmpresa(false);

                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar empresa", getStage(), "Empresa guardo con éxito.");
                    //Sonido

                }
            }
        } catch (Exception ex) {

            Logger.getLogger(MantenimientoEmpresaController.class.getName()).log(Level.SEVERE, "Error guardando el empresa.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empresa", getStage(), "Ocurrio un error guardando el empresa.");
        }
    }

    @FXML
    private void onActionBtnModificar(ActionEvent event) {
        try {
            unbindEmpresa();
            txtCedulaJuridica.textProperty().bindBidirectional(empresa.emCedulajuridica);
            txtTelefono.textProperty().bindBidirectional(empresa.emTelefono);
            txtCorreo.textProperty().bindBidirectional(empresa.emCorreo);
            txtNombreEmpresa.textProperty().bindBidirectional(empresa.emNombre);
            txtCalificacion.textProperty().bindBidirectional(empresa.emCalificacion);
            datePickerFecFunda.valueProperty().bindBidirectional(empresa.emFechafundacion);
            
            String cedulaText = txtCedulaJuridica.getText();
            //long extranno
            long cedula = Long.parseLong(cedulaText);
            EmpresaDto empresaDto = new EmpresaDto();
            empresaDto.setEmpresaCedJuridica((txtCedulaJuridica.getText()));
            empresaDto.setEmpresaNombre(txtTelefono.getText());
            empresaDto.setEmpresaCorreo(txtCorreo.getText());
            empresaDto.setEmpresaNombre(txtNombreEmpresa.getText());
            empresaDto.setEmpresCalificacion(Long.parseLong(txtCalificacion.getText()));
            empresaDto.setEmpresaFechafundacion(datePickerFecFunda.getValue());

            EmpresaService empresasService = new EmpresaService();
            Respuesta respuesta = empresasService.modificarPaciente(empresaDto, cedula);
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar empresa", getStage(), "Empresa actualizado correctamente.");
            // mediaPlayer.play();
        } catch (Exception ex) {
            Logger.getLogger(MantenimientoEmpresaController.class.getName()).log(Level.SEVERE, "Error actualizando el Empresa.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar Empresa", getStage(), "Ocurrio un error al actualizar el Empresa.");
        }
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
    }

    @FXML
    private void onSelectionGuardarTabPane(Event event) {
    }

    @FXML
    private void onActionBtnBuscarPorNombre(ActionEvent event) {
    }

    @FXML
    private void onSelectionInfoTabPane(Event event) {
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

    @FXML
    private void onActionretroceder(ActionEvent event) {
        Stage currentStage = (Stage) btn_retroceder.getScene().getWindow();
        currentStage.close();
        FlowController.getInstance().goMain();
    }
}
