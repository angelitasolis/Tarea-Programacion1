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
public class ReservaDto {

    public SimpleStringProperty rsId;
    public SimpleStringProperty rsMontoabonado;
    public ObjectProperty<LocalDate> rsFechareserva;
    public SimpleStringProperty rsTrscosto;
    public ObjectProperty<Tour> rsCodigotour;
    public ObjectProperty<Cliente> rsCedulacliente;

    public ReservaDto() {
        this.rsId = new SimpleStringProperty();
        this.rsMontoabonado = new SimpleStringProperty();
        this.rsFechareserva = new SimpleObjectProperty();
        this.rsTrscosto = new SimpleStringProperty();
        this.rsCodigotour = new SimpleObjectProperty();
        this.rsCedulacliente = new SimpleObjectProperty();
    }

    public ReservaDto(Reserva reserva) {
        this();
        this.rsId.set(reserva.getRsId().toString());
        this.rsMontoabonado.set(reserva.getRsMontoabonado().toString());
        this.rsFechareserva.set(reserva.getRsFechareserva().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.rsTrscosto.set(reserva.getRsMontoabonado().toString());
        this.rsCodigotour.set(reserva.getRsCodigotour());
        this.rsCedulacliente.set(reserva.getRsCedulacliente());
    }

    public Tour getCodigotour() {
        return rsCodigotour.get();
    }

    public void setCodigotour(Tour rsCodigotour) {
        this.rsCodigotour.set(rsCodigotour);
    }

    public Cliente getRsCedulaCliente() {
        return rsCedulacliente.get();
    }

    public void setRsCedulaCliente(Cliente rsCedulacliente) {
        this.rsCedulacliente.set(rsCedulacliente);
    }

    public Long getReservaTrscosto() {
        String value = this.rsTrscosto.get();
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        return null;

    }

    public void setReservaTrscosto(Long prsTrscosto) {
        if (rsTrscosto != null) {
            this.rsTrscosto.set(Long.toString(prsTrscosto));
        }
    }

    public Long getReservaId() {
        String value = this.rsId.get();
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        return null;

    }

    public void setReservaId(Long prsId) {
        if (rsId != null) {
            this.rsId.set(Long.toString(prsId));
        }
    }

    public void setReservaMontoabonado(Long prsMontoabonado) {
        if (rsMontoabonado != null) {
            this.rsMontoabonado.set(Long.toString(prsMontoabonado));
        } else {
            this.rsMontoabonado.set(null);
        }
    }

    public Long getReservaMontoabonado() {
        String value = this.rsMontoabonado.get();
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        return null;

    }

    public void setReservaFecnac(LocalDate prsFechareserva) {
        this.rsFechareserva.set(prsFechareserva);
    }

    public LocalDate getReservaFecnac() {
        return rsFechareserva.get();
    }
}
