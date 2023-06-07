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
@Table(name = "TBL_CLIENTE")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCltCedula", query = "SELECT c FROM Cliente c WHERE c.cltCedula = :cltCedula"),
    @NamedQuery(name = "Cliente.findByCltNombre", query = "SELECT c FROM Cliente c WHERE c.cltNombre = :cltNombre"),
    @NamedQuery(name = "Cliente.findByCltPapellido", query = "SELECT c FROM Cliente c WHERE c.cltPapellido = :cltPapellido"),
    @NamedQuery(name = "Cliente.findByCltSapellido", query = "SELECT c FROM Cliente c WHERE c.cltSapellido = :cltSapellido"),
    @NamedQuery(name = "Cliente.findByCltTelefono", query = "SELECT c FROM Cliente c WHERE c.cltTelefono = :cltTelefono"),
    @NamedQuery(name = "Cliente.findByCltCorreo", query = "SELECT c FROM Cliente c WHERE c.cltCorreo = :cltCorreo"),
    @NamedQuery(name = "Cliente.findByCltFechanac", query = "SELECT c FROM Cliente c WHERE c.cltFechanac = :cltFechanac")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLT_CEDULA")
    private String cltCedula;
    @Basic(optional = false)
    @Column(name = "CLT_NOMBRE")
    private String cltNombre;
    @Basic(optional = false)
    @Column(name = "CLT_PAPELLIDO")
    private String cltPapellido;
    @Basic(optional = false)
    @Column(name = "CLT_SAPELLIDO")
    private String cltSapellido;
    @Basic(optional = false)
    @Column(name = "CLT_TELEFONO")
    private Long cltTelefono;
    @Basic(optional = false)
    @Column(name = "CLT_CORREO")
    private String cltCorreo;
    @Basic(optional = false)
    @Column(name = "CLT_FECHANAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cltFechanac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rsCedulacliente")
    private Collection<Reserva> reservaCollection;

    public Cliente() {
    }

    public Cliente(String cltCedula) {
        this.cltCedula = cltCedula;
    }

    public Cliente(String cltCedula, String cltNombre, String cltPapellido, String cltSapellido, Long cltTelefono, String cltCorreo, Date cltFechanac) {
        this.cltCedula = cltCedula;
        this.cltNombre = cltNombre;
        this.cltPapellido = cltPapellido;
        this.cltSapellido = cltSapellido;
        this.cltTelefono = cltTelefono;
        this.cltCorreo = cltCorreo;
        this.cltFechanac = cltFechanac;
    }

    public Cliente(ClienteDto clienteDto) {
        this.cltCedula = clienteDto.getClienteCedula();
        actualizar(clienteDto);
    }

    public void actualizar(ClienteDto clienteDto) {
        this.cltCedula = clienteDto.getClienteCedula();
        this.cltNombre = clienteDto.getClienteNombre();
        this.cltPapellido = clienteDto.getClienteApellido();
        this.cltSapellido = clienteDto.getClienteSApellido();
        this.cltTelefono= clienteDto.getClienteTelefono();
        this.cltCorreo= clienteDto.getClienteCorreo();
        this.cltFechanac = Date.from(clienteDto.getClienteFecnac().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public String getCltCedula() {
        return cltCedula;
    }

    public void setCltCedula(String cltCedula) {
        this.cltCedula = cltCedula;
    }

    public String getCltNombre() {
        return cltNombre;
    }

    public void setCltNombre(String cltNombre) {
        this.cltNombre = cltNombre;
    }

    public String getCltPapellido() {
        return cltPapellido;
    }

    public void setCltPapellido(String cltPapellido) {
        this.cltPapellido = cltPapellido;
    }

    public String getCltSapellido() {
        return cltSapellido;
    }

    public void setCltSapellido(String cltSapellido) {
        this.cltSapellido = cltSapellido;
    }

    public Long getCltTelefono() {
        return cltTelefono;
    }

    public void setCltTelefono(Long cltTelefono) {
        this.cltTelefono = cltTelefono;
    }

    public String getCltCorreo() {
        return cltCorreo;
    }

    public void setCltCorreo(String cltCorreo) {
        this.cltCorreo = cltCorreo;
    }

    public Date getCltFechanac() {
        return cltFechanac;
    }

    public void setCltFechanac(Date cltFechanac) {
        this.cltFechanac = cltFechanac;
    }

    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cltCedula != null ? cltCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cltCedula == null && other.cltCedula != null) || (this.cltCedula != null && !this.cltCedula.equals(other.cltCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nombre: "  + cltNombre +  cltPapellido + ", Cedula: " + cltCedula;
    }

   

}
