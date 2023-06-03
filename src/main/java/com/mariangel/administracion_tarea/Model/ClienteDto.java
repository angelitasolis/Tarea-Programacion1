/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Model;

import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Administrador
 */
public class ClienteDto {

    public SimpleStringProperty cltCedula;
    public SimpleStringProperty cltNombre;
    public SimpleStringProperty cltPapellido;
    public SimpleStringProperty cltSapellido;
    public SimpleStringProperty cltTelefono;
    public SimpleStringProperty cltCorreo;
    public ObjectProperty<LocalDate> cltFechanac;

    public ClienteDto() {

        this.cltCedula = new SimpleStringProperty();
        this.cltNombre = new SimpleStringProperty();
        this.cltPapellido = new SimpleStringProperty();
        this.cltSapellido = new SimpleStringProperty();
        this.cltTelefono = new SimpleStringProperty();
        this.cltCorreo = new SimpleStringProperty();
        this.cltFechanac = new SimpleObjectProperty();

    }

    public ClienteDto(Cliente cliente) {
        this();
        this.cltNombre.set(cliente.getCltNombre());
        this.cltPapellido.set(cliente.getCltPapellido());
        this.cltSapellido.set(cliente.getCltSapellido());
        this.cltCedula.set(cliente.getCltCedula().toString());
        this.cltTelefono.set(Long.toString(cliente.getCltTelefono()));
        this.cltCorreo.set(cliente.getCltCorreo());
        this.cltFechanac.set(cliente.getCltFechanac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public void setClienteNombre(String pCliente) {
        this.cltNombre.set(pCliente);
    }

    public String getClienteNombre() {
        return cltNombre.get();
    }

    public String getClienteApellido() {
        return cltPapellido.get();
    }

    public void setClientePApellido(String pcltPapellido) {
        this.cltPapellido.set(pcltPapellido);
    }

    public String getClienteSApellido() {
        return cltSapellido.get();
    }

    public void setClienteSApellido(String pcltSapellido) {
        this.cltSapellido.set(pcltSapellido);
    }

    public String getClienteCedula() {
        return cltCedula.get();
    }

    public void setClienteCedula(String pcltSapellido) {
        this.cltCedula.set(pcltSapellido.toString());
    }

    public String getClienteCorreo() {
        return cltCorreo.get();
    }

    public void setClienteCorreo(String pcltSapellido) {
        this.cltCorreo.set(pcltSapellido.toString());
    }

    public Long getClienteTelefono() {
        String value = this.cltTelefono.get();
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        return null;

    }

    public void setClienteTelefono(Long pcltTelefono) {
        if (cltTelefono != null) {
            this.cltTelefono.set(Long.toString(pcltTelefono));
        } else {
            this.cltTelefono.set(null);
        }
    }

    public void setClienteFecnac(LocalDate pcltFechanac) {
        this.cltFechanac.set(pcltFechanac);
    }

    public LocalDate getClienteFecnac() {
        return cltFechanac.get();
    }

}
