/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "TBL_TOURS")
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t"),
    @NamedQuery(name = "Tour.findByTrsNombre", query = "SELECT t FROM Tour t WHERE t.trsNombre = :trsNombre"),
    @NamedQuery(name = "Tour.findByTrsFechasalida", query = "SELECT t FROM Tour t WHERE t.trsFechasalida = :trsFechasalida"),
    @NamedQuery(name = "Tour.findByTrsFechallegada", query = "SELECT t FROM Tour t WHERE t.trsFechallegada = :trsFechallegada"),
    @NamedQuery(name = "Tour.findByTrsCostotour", query = "SELECT t FROM Tour t WHERE t.trsCostotour = :trsCostotour"),
    @NamedQuery(name = "Tour.findByTrsCantidadclientes", query = "SELECT t FROM Tour t WHERE t.trsCantidadclientes = :trsCantidadclientes"),
    @NamedQuery(name = "Tour.findByTrsCodigotour", query = "SELECT t FROM Tour t WHERE t.trsCodigotour = :trsCodigotour"),
    @NamedQuery(name = "Tour.findByTrsHorasalida", query = "SELECT t FROM Tour t WHERE t.trsHorasalida = :trsHorasalida"),
    @NamedQuery(name = "Tour.findByTrsHorallegada", query = "SELECT t FROM Tour t WHERE t.trsHorallegada = :trsHorallegada")})
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "TRS_NOMBRE")
    private String trsNombre;
    @Basic(optional = false)
    @Column(name = "TRS_FECHASALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trsFechasalida;
    @Basic(optional = false)
    @Column(name = "TRS_FECHALLEGADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trsFechallegada;
    @Basic(optional = false)
    @Column(name = "TRS_COSTOTOUR")
    private Long trsCostotour;
    @Basic(optional = false)
    @Column(name = "TRS_CANTIDADCLIENTES")
    private Long trsCantidadclientes;
    @Id
    @Basic(optional = false)
    @Column(name = "TRS_CODIGOTOUR")
    private String trsCodigotour;
    @Basic(optional = false)
    @Column(name = "TRS_HORASALIDA")
    private short trsHorasalida;
    @Basic(optional = false)
    @Column(name = "TRS_HORALLEGADA")
    private short trsHorallegada;
    @JoinColumn(name = "TRS_EMPRESACEDJUR", referencedColumnName = "EM_CEDULAJURIDICA")
    @ManyToOne(optional = false)
    private Empresa trsEmpresacedjur;
    @JoinColumn(name = "TRS_TIPOTOURCODIGO", referencedColumnName = "TT_CODIGO")
    @ManyToOne(optional = false)
    private Tipotour trsTipotourcodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rsCodigotour")
    private Collection<Reserva> reservaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intCodigotour")
    private Collection<Itinerario> itinerarioCollection;

    public Tour() {
    }

    public Tour(String trsCodigotour) {
        this.trsCodigotour = trsCodigotour;
    }

    public Tour(String trsCodigotour, String trsNombre, Date trsFechasalida, Date trsFechallegada, Long trsCostotour, Long trsCantidadclientes, short trsHorasalida, short trsHorallegada, Tipotour trsTipotourcodigo) {
        this.trsCodigotour = trsCodigotour;
        this.trsNombre = trsNombre;
        this.trsFechasalida = trsFechasalida;
        this.trsFechallegada = trsFechallegada;
        this.trsCostotour = trsCostotour;
        this.trsCantidadclientes = trsCantidadclientes;
        this.trsHorasalida = trsHorasalida;
        this.trsHorallegada = trsHorallegada;
        this.trsTipotourcodigo = trsTipotourcodigo;
    }

    public Tour(TourDto tourDto) {
        this.trsCodigotour = tourDto.getTrsCodigo();
        actualizar(tourDto);
    }

    public void actualizar(TourDto tourDto) {
        this.trsCodigotour = tourDto.getTrsCodigo();
        this.trsNombre = tourDto.getTrsNombre();
        this.trsFechallegada = Date.from(tourDto.getTrsFechallegada().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.trsFechasalida = Date.from(tourDto.getTrsFechasalida().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.trsCostotour = tourDto.getTrsCosto();
        this.trsCantidadclientes = tourDto.getTrsClientes();
        this.trsHorasalida = tourDto.getTrsHorallegada();
        this.trsHorallegada = tourDto.getTrsHorallegada();
        this.trsTipotourcodigo = tourDto.getTipoToursCodigo();
    }

    public String getTrsNombre() {
        return trsNombre;
    }
     public void setTrsNombre(String trsNombre) {
        this.trsNombre = trsNombre;
    }
    
    public Tipotour getTrsTipoTourCodigo() {
        return trsTipotourcodigo;
    }
    
    
    public void setTrsTipoTourCodigo(Tipotour trsTipotourcodigo) {
        this.trsTipotourcodigo = trsTipotourcodigo;
    }

    public Date getTrsFechasalida() {
        return trsFechasalida;
    }

    public void setTrsFechasalida(Date trsFechasalida) {
        this.trsFechasalida = trsFechasalida;
    }

    public Date getTrsFechallegada() {
        return trsFechallegada;
    }

    public void setTrsFechallegada(Date trsFechallegada) {
        this.trsFechallegada = trsFechallegada;
    }

    public Long getTrsCostotour() {
        return trsCostotour;
    }

    public void setTrsCostotour(Long trsCostotour) {
        this.trsCostotour = trsCostotour;
    }

    public Long getTrsCantidadclientes() {
        return trsCantidadclientes;
    }

    public void setTrsCantidadclientes(Long trsCantidadclientes) {
        this.trsCantidadclientes = trsCantidadclientes;
    }

    public String getTrsCodigotour() {
        return trsCodigotour;
    }

    public void setTrsCodigotour(String trsCodigotour) {
        this.trsCodigotour = trsCodigotour;
    }

    public short getTrsHorasalida() {
        return trsHorasalida;
    }

    public void setTrsHorasalida(short trsHorasalida) {
        this.trsHorasalida = trsHorasalida;
    }

    public short getTrsHorallegada() {
        return trsHorallegada;
    }

    public void setTrsHorallegada(short trsHorallegada) {
        this.trsHorallegada = trsHorallegada;
    }

    public Empresa getTrsEmpresacedjur() {
        return trsEmpresacedjur;
    }

    public void setTrsEmpresacedjur(Empresa trsEmpresacedjur) {
        this.trsEmpresacedjur = trsEmpresacedjur;
    }

    public Tipotour getTrsTipotourcodigo() {
        return trsTipotourcodigo;
    }

    public void setTrsTipotourcodigo(Tipotour trsTipotourcodigo) {
        this.trsTipotourcodigo = trsTipotourcodigo;
    }

    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    public Collection<Itinerario> getItinerarioCollection() {
        return itinerarioCollection;
    }

    public void setItinerarioCollection(Collection<Itinerario> itinerarioCollection) {
        this.itinerarioCollection = itinerarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trsCodigotour != null ? trsCodigotour.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.trsCodigotour == null && other.trsCodigotour != null) || (this.trsCodigotour != null && !this.trsCodigotour.equals(other.trsCodigotour))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mariangel.administracion_tarea.Model.Tour[ trsCodigotour=" + trsCodigotour + " ]";
    }

}
