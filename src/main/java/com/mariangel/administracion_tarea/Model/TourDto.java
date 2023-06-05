/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Administrador
 */
public class TourDto {

    public SimpleStringProperty trsCodigotour;
    public SimpleStringProperty trsNombre;
    public ObjectProperty<LocalDate> trsFechasalida;
    public ObjectProperty<LocalDate> trsFechallegada;
    public SimpleStringProperty trsCostotour;
    public SimpleStringProperty trsCantidadclientes;
    public SimpleStringProperty trsHorasalida;
    public SimpleStringProperty trsHorallegada;
    public ObjectProperty<Tipotour> trsTipotourcodigo;
    public ObjectProperty<Empresa> trsEmpresacedjur;

    public TourDto() {
        this.trsCodigotour = new SimpleStringProperty();
        this.trsNombre = new SimpleStringProperty();
        this.trsFechasalida = new SimpleObjectProperty();
        this.trsFechallegada = new SimpleObjectProperty();
        this.trsCostotour = new SimpleStringProperty();
        this.trsCantidadclientes = new SimpleStringProperty();
        this.trsHorasalida = new SimpleStringProperty();
        this.trsHorallegada = new SimpleStringProperty();
        this.trsTipotourcodigo = new SimpleObjectProperty();
        this.trsEmpresacedjur = new SimpleObjectProperty();

    }

    public TourDto(Tour tour) {
        this();
        this.trsCodigotour.set(tour.getTrsCodigotour());
        this.trsNombre.set(tour.getTrsNombre());
        this.trsFechasalida.set(tour.getTrsFechasalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.trsFechallegada.set(tour.getTrsFechallegada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.trsCostotour.set(tour.getTrsCostotour().toString());
        this.trsCantidadclientes.set(tour.getTrsCantidadclientes().toString());
        this.trsHorasalida.set(Short.toString(tour.getTrsHorasalida()));
        this.trsHorallegada.set(Short.toString(tour.getTrsHorallegada()));
        this.trsTipotourcodigo.set(tour.getTrsTipotourcodigo());
        this.trsEmpresacedjur.set(tour.getTrsEmpresacedjur());
    }

    public Empresa getTEmpresaCedJur() {
        return trsEmpresacedjur.get();
    }

    public void setTEmpresaCedJur(Empresa pTrsEmpresacedjur) {
        this.trsEmpresacedjur.set(pTrsEmpresacedjur);
    }

    public Tipotour getTipoToursCodigo() {
        return trsTipotourcodigo.get();
    }

    public void setTipoToursCodigo(Tipotour tipotrsCodigo) {
        this.trsTipotourcodigo.set(tipotrsCodigo);
    }

    public String getTrsCodigo() {
        return trsCodigotour.get();
    }

    public String getTrsNombre() {
        return trsNombre.get();
    }

    public LocalDate getTrsFechasalida() {
        return trsFechasalida.get();
    }

    public LocalDate getTrsFechallegada() {
        return trsFechallegada.get();
    }

    public Long getTrsCosto() {
        return Long.valueOf(trsCostotour.get());
    }

    public Long getTrsClientes() {
        return Long.valueOf(trsCantidadclientes.get());
    }

    public Short getTrsHorasalida() {
        return Short.valueOf(trsHorasalida.get());
    }

    public Short getTrsHorallegada() {
        return Short.valueOf(trsHorallegada.get());
    }

    public void setTrsCodigo(String trsCodigo) {
        this.trsCodigotour.set(trsCodigo);
    }

    public void setTrsNombre(String trsNombre) {
        this.trsNombre.set(trsNombre);
    }

    public void setTrsFechasalida(LocalDate trsFechasalida) {
        this.trsFechasalida.set(trsFechasalida);
    }

    public void setTrsFechallegada(LocalDate trsFechallegada) {
        this.trsFechallegada.set(trsFechallegada);
    }

    public void setTrsCosto(Long trsCosto) {
        this.trsCostotour.set(trsCosto.toString());
    }

    public void setTrsClientes(Long trsClientes) {
        this.trsCantidadclientes.set(trsClientes.toString());
    }

    public void setTrsHorasalida(Short trsHorasalida) {
        this.trsHorasalida.set(trsHorasalida.toString());
    }

    public void setTrsHorallegada(Short trsHorallegada) {
        this.trsHorallegada.set(trsHorallegada.toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.trsCodigotour);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TourDto other = (TourDto) obj;
        return Objects.equals(this.trsCodigotour, other.trsCodigotour);
    }
}
