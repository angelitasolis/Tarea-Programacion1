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
public class ItinerarioDto {

    public SimpleStringProperty intId;
    public SimpleStringProperty intLugar;
    public SimpleStringProperty intDuracion;
    public SimpleStringProperty intActividades;
    public SimpleStringProperty intCodigotour;

    public ItinerarioDto() {

        this.intId = new SimpleStringProperty();
        this.intLugar = new SimpleStringProperty();
        this.intDuracion = new SimpleStringProperty();
        this.intActividades = new SimpleStringProperty();

    }

    public ItinerarioDto(Itinerario itinerario) {
        this();
        this.intId.set(Long.toString(itinerario.getIntId()));
        this.intLugar.set(itinerario.getIntLugar());
        this.intDuracion.set(Short.toString(itinerario.getIntDuracion()));
        this.intActividades.set(itinerario.getIntActividades());

    }

    public void setItinerarioId(Long pacNombre) {
        if (pacNombre != null) {
            this.intId.set(Long.toString(pacNombre));
        } else {
            this.intId.set(null);
        }
    }
    

    public Long getItinerarioId() {
        String value = this.intId.get();
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    public String getItinerarioLugar() {
        return intLugar.get();
    }

    public void setItinerarioLugar(String pintLugar) {
        this.intLugar.set(pintLugar);
    }

    public Short getItinerarioDuracion() {
        String value = this.intDuracion.get();
        if (value != null) {
            try {
                return Short.parseShort(value);
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    public void setItinerarioDuracion(Short pintDuracion) {
        if (intDuracion != null) {
            this.intDuracion.set(Short.toString(pintDuracion));
        } else {
            this.intDuracion.set(null);
        }
    }

    public void setItinerarioActividades(String pintActividades) {
        this.intActividades.set(pintActividades);
    }

    public String getItinerarioActividades() {
        return intActividades.get();
    }

}
