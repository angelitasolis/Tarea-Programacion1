/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "TBL_TIPOTOUR")
@NamedQueries({
    @NamedQuery(name = "Tipotour.findAll", query = "SELECT t FROM Tipotour t"),
    @NamedQuery(name = "Tipotour.findByTtCodigo", query = "SELECT t FROM Tipotour t WHERE t.ttCodigo = :ttCodigo"),
    @NamedQuery(name = "Tipotour.findByTtTipo", query = "SELECT t FROM Tipotour t WHERE t.ttTipo = :ttTipo"),
    @NamedQuery(name = "Tipotour.findByTtNombretour", query = "SELECT t FROM Tipotour t WHERE t.ttNombretour = :ttNombretour"),
    @NamedQuery(name = "Tipotour.findByTtPais", query = "SELECT t FROM Tipotour t WHERE t.ttPais = :ttPais")})
public class Tipotour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TT_CODIGO")
    private Long ttCodigo;
    @Basic(optional = false)
    @Column(name = "TT_TIPO")
    private String ttTipo;
    @Basic(optional = false)
    @Column(name = "TT_NOMBRETOUR")
    private String ttNombretour;
    @Basic(optional = false)
    @Column(name = "TT_PAIS")
    private String ttPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trsTipotourcodigo")
    private Collection<Tour> tourCollection;

    public Tipotour() {
    }

    public Tipotour(Long ttCodigo) {
        this.ttCodigo = ttCodigo;
    }

    public Tipotour(Long ttCodigo, String ttTipo, String ttNombretour, String ttPais) {
        this.ttCodigo = ttCodigo;
        this.ttTipo = ttTipo;
        this.ttNombretour = ttNombretour;
        this.ttPais = ttPais;
    }

    public Tipotour(TipoTourDto tipotourDto) {
        this.ttCodigo = tipotourDto.getTptCodigo();
        actualizar(tipotourDto);
    }

    public void actualizar(TipoTourDto tipoTourDto) {
        this.ttCodigo = tipoTourDto.getTptCodigo();
        this.ttTipo = tipoTourDto.getTptTipo();
        this.ttNombretour = tipoTourDto.getTptNombre();
        this.ttPais = tipoTourDto.getTptPais();
    }

    public Long getTtCodigo() {
        return ttCodigo;
    }

    public void setTtCodigo(Long ttCodigo) {
        this.ttCodigo = ttCodigo;
    }

    public String getTtTipo() {
        return ttTipo;
    }

    public void setTtTipo(String ttTipo) {
        this.ttTipo = ttTipo;
    }

    public String getTtNombretour() {
        return ttNombretour;
    }

    public void setTtNombretour(String ttNombretour) {
        this.ttNombretour = ttNombretour;
    }

    public String getTtPais() {
        return ttPais;
    }

    public void setTtPais(String ttPais) {
        this.ttPais = ttPais;
    }

    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }

    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.ttCodigo);
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
        final Tipotour other = (Tipotour) obj;
        return Objects.equals(this.ttCodigo, other.ttCodigo);
    }

    @Override
    public String toString() {
        return "Tipotour{" + "ttCodigo=" + ttCodigo + ", ttTipo=" + ttTipo + ", ttNombretour=" + ttNombretour + ", ttPais=" + ttPais + ", tourCollection=" + tourCollection + '}';
    }

}
