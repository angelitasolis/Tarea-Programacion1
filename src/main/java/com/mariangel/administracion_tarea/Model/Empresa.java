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
@Table(name = "TBL_EMPRESA")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByEmNombre", query = "SELECT e FROM Empresa e WHERE e.emNombre = :emNombre"),
    @NamedQuery(name = "Empresa.findByEmCedulajuridica", query = "SELECT e FROM Empresa e WHERE e.emCedulajuridica = :emCedulajuridica"),
    @NamedQuery(name = "Empresa.findByEmTelefono", query = "SELECT e FROM Empresa e WHERE e.emTelefono = :emTelefono"),
    @NamedQuery(name = "Empresa.findByEmCorreo", query = "SELECT e FROM Empresa e WHERE e.emCorreo = :emCorreo"),
    @NamedQuery(name = "Empresa.findByEmFechafundacion", query = "SELECT e FROM Empresa e WHERE e.emFechafundacion = :emFechafundacion"),
    @NamedQuery(name = "Empresa.findByEmCalificacion", query = "SELECT e FROM Empresa e WHERE e.emCalificacion = :emCalificacion")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "EM_NOMBRE")
    private String emNombre;
    @Id
    @Basic(optional = false)
    @Column(name = "EM_CEDULAJURIDICA")
    private String emCedulajuridica;
    @Basic(optional = false)
    @Column(name = "EM_TELEFONO")
    private Long emTelefono;
    @Basic(optional = false)
    @Column(name = "EM_CORREO")
    private String emCorreo;
    @Basic(optional = false)
    @Column(name = "EM_FECHAFUNDACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emFechafundacion;
    @Column(name = "EM_CALIFICACION")
    private Long emCalificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trsEmpresacedjur")
    private Collection<Tour> tourCollection;

    public Empresa() {
    }

    public Empresa(String emCedulajuridica) {
        this.emCedulajuridica = emCedulajuridica;
    }

    public Empresa(String emCedulajuridica, String emNombre, Long emTelefono, String emCorreo, Date emFechafundacion) {
        this.emCedulajuridica = emCedulajuridica;
        this.emNombre = emNombre;
        this.emTelefono = emTelefono;
        this.emCorreo = emCorreo;
        this.emFechafundacion = emFechafundacion;
    }

    public Empresa(EmpresaDto empresaDto) {
        this.emCedulajuridica = empresaDto.getEmpresaCedJuridica();
        actualizar(empresaDto);
    }

    public void actualizar(EmpresaDto empresaDto) {
        this.emNombre = empresaDto.getEmpresaNombre();
        this.emTelefono = empresaDto.getEmpresaTelefono();
        this.emCorreo = empresaDto.getEmpresaCorreo();
        this.emCedulajuridica = empresaDto.getEmpresaCedJuridica();
        this.emFechafundacion = Date.from(empresaDto.getEmpresaFechafundacion().atStartOfDay(ZoneId.systemDefault()).toInstant());
    
    }

    public String getEmNombre() {
        return emNombre;
    }

    public void setEmNombre(String emNombre) {
        this.emNombre = emNombre;
    }

    public String getEmCedulajuridica() {
        return emCedulajuridica;
    }

    public void setEmCedulajuridica(String emCedulajuridica) {
        this.emCedulajuridica = emCedulajuridica;
    }

    public Long getEmTelefono() {
        return emTelefono;
    }

    public void setEmTelefono(Long emTelefono) {
        this.emTelefono = emTelefono;
    }

    public String getEmCorreo() {
        return emCorreo;
    }

    public void setEmCorreo(String emCorreo) {
        this.emCorreo = emCorreo;
    }

    public Date getEmFechafundacion() {
        return emFechafundacion;
    }

    public void setEmFechafundacion(Date emFechafundacion) {
        this.emFechafundacion = emFechafundacion;
    }

    public Long getEmCalificacion() {
        return emCalificacion;
    }

    public void setEmCalificacion(Long emCalificacion) {
        this.emCalificacion = emCalificacion;
    }

    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }

    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emCedulajuridica != null ? emCedulajuridica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.emCedulajuridica == null && other.emCedulajuridica != null) || (this.emCedulajuridica != null && !this.emCedulajuridica.equals(other.emCedulajuridica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mariangel.administracion_tarea.Model.Empresa[ emCedulajuridica=" + emCedulajuridica + " ]";
    }

}
