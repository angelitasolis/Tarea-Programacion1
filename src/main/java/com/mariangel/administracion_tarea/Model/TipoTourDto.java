/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Model;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Administrador
 */
public class TipoTourDto {
    public SimpleStringProperty ttCodigo;
    public SimpleStringProperty ttTipo;
    public SimpleStringProperty ttNombretour;
    public SimpleStringProperty ttPais;
    
        public TipoTourDto() {
        this.ttCodigo = new SimpleStringProperty();
        this.ttTipo = new SimpleStringProperty();
        this.ttNombretour = new SimpleStringProperty();
        this.ttPais = new SimpleStringProperty();
    }

    public TipoTourDto(Tipotour tipotour) {
        this();
        this.ttCodigo.set(tipotour.getTtCodigo().toString());
        this.ttTipo.set(tipotour.getTtTipo());
        this.ttNombretour.set(tipotour.getTtNombretour());
        this.ttPais.set(tipotour.getTtPais());
    }

    public Long getTptCodigo() {
        return Long.valueOf(ttCodigo.get());
    }

    public String getTptTipo() {
        return ttTipo.get();
    }

    public String getTptNombre() {
        return ttNombretour.get();
    }

    public String getTptPais() {
        return ttPais.get();
    }

    public void setTptCodigo(Long tptCodigo) {
         this.ttCodigo.set(tptCodigo.toString());
    
    }

    public void setTptTipo(String tptTipo) {
        this.ttTipo.set(tptTipo);
    }

    public void setTptNombre(String tptNombre) {
        this.ttNombretour.set(tptNombre);
    }

    public void setTptPais(String tptPais) {
        this.ttPais.set(tptPais);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.ttCodigo);
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
        final TipoTourDto other = (TipoTourDto) obj;
        return Objects.equals(this.ttCodigo, other.ttCodigo);
    }

    @Override
    public String toString() {
        return "TipoTourDto{" + "ttCodigo=" + ttCodigo + ", ttTipo=" + ttTipo + ", ttNombretour=" + ttNombretour + ", ttPais=" + ttPais + '}';
    }

   
}
