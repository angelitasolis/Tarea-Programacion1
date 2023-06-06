/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "TBL_ITINERARIO")
@NamedQueries({
    @NamedQuery(name = "Itinerario.findAll", query = "SELECT i FROM Itinerario i"),
    @NamedQuery(name = "Itinerario.findByIntId", query = "SELECT i FROM Itinerario i WHERE i.intId = :intId"),
    @NamedQuery(name = "Itinerario.findByIntLugar", query = "SELECT i FROM Itinerario i WHERE i.intLugar = :intLugar"),
    @NamedQuery(name = "Itinerario.findByIntDuracion", query = "SELECT i FROM Itinerario i WHERE i.intDuracion = :intDuracion"),
    @NamedQuery(name = "Itinerario.findByIntActividades", query = "SELECT i FROM Itinerario i WHERE i.intActividades = :intActividades")})
public class Itinerario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "INT_ID")
    private Long intId;
    @Basic(optional = false)
    @Column(name = "INT_LUGAR")
    private String intLugar;
    @Column(name = "INT_DURACION")
    private Short intDuracion;
    @Column(name = "INT_ACTIVIDADES")
    private String intActividades;
    @JoinColumn(name = "INT_CODIGOTOUR", referencedColumnName = "TRS_CODIGOTOUR")
    @ManyToOne(optional = false)
    private Tour intCodigotour;

    public Itinerario() {
    }

    public Itinerario(Long intId) {
        this.intId = intId;
    }

    public Itinerario(Long intId, String intLugar) {
        this.intId = intId;
        this.intLugar = intLugar;
    }
    
      public Itinerario(ItinerarioDto itinerarioDto) {
        this.intId = itinerarioDto.getItinerarioId();
        actualizar(itinerarioDto);
    }

    public void actualizar(ItinerarioDto itinerarioDto) {
        this.intId = itinerarioDto.getItinerarioId();
        this.intLugar = itinerarioDto.getItinerarioLugar();
        this.intDuracion = itinerarioDto.getItinerarioDuracion();
        this.intActividades = itinerarioDto.getItinerarioActividades();
        this.intCodigotour = itinerarioDto.getTourCodigo();
        
    }
    
     public Tour getTourCodigo() {
        return intCodigotour;
    }
   
    public void setTourCodigo(Tour intCodigotour) {
        this.intCodigotour = intCodigotour;
    }
    
    
    public Long getIntId() {
        return intId;
    }

    public void setIntId(Long intId) {
        this.intId = intId;
    }

    public String getIntLugar() {
        return intLugar;
    }

    public void setIntLugar(String intLugar) {
        this.intLugar = intLugar;
    }

    public Short getIntDuracion() {
        return intDuracion;
    }

    public void setIntDuracion(Short intDuracion) {
        this.intDuracion = intDuracion;
    }

    public String getIntActividades() {
        return intActividades;
    }

    public void setIntActividades(String intActividades) {
        this.intActividades = intActividades;
    }

    public Tour getIntCodigotour() {
        return intCodigotour;
    }

    public void setIntCodigotour(Tour intCodigotour) {
        this.intCodigotour = intCodigotour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intId != null ? intId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itinerario)) {
            return false;
        }
        Itinerario other = (Itinerario) object;
        if ((this.intId == null && other.intId != null) || (this.intId != null && !this.intId.equals(other.intId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mariangel.administracion_tarea.Model.Itinerario[ intId=" + intId + " ]";
    }
    
}
