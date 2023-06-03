/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Service;

import com.mariangel.administracion_tarea.Model.Cliente;
import com.mariangel.administracion_tarea.Model.ClienteDto;
import com.mariangel.administracion_tarea.Utils.EntityManagerHelper;
import com.mariangel.administracion_tarea.Utils.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class ClienteService {

    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getCliente(String pCedulaCargar) {
        try {
            Query qryCliente = em.createNamedQuery("Cliente.findByCltCedula", Cliente.class);
            qryCliente.setParameter("cltCedula", pCedulaCargar);

            return new Respuesta(true, "", "", "Cliente", new ClienteDto((Cliente) qryCliente.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe el cliente ingresado.", "getCliente NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el cliente.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el cliente.", "getCliente NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el cliente.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el cliente.", "getCliente " + ex.getMessage());
        }
    }

    public Respuesta eliminarCliente(String pCedulaEliminar) {
        try {
            et = em.getTransaction();
            et.begin();
            Cliente cliente;
            if (pCedulaEliminar != null) {
                cliente = em.find(Cliente.class, pCedulaEliminar);
                if (cliente == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el cliente a eliminar.", "eliminarCliente NoResultException");
                }
                em.remove(cliente);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el cliente a eliminar.", "eliminarCliente NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el cliente porque tiene relaciones con otros registros.", "eliminarCliente " + ex.getMessage());
            }
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el cliente.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el cliente.", "eliminarCliente " + ex.getMessage());
        }
    }
}
/*
    public Respuesta modificarCliente(ClienteDto clienteDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Cliente cliente;
            if (clienteDto.getCltCedula() != null) {
                cliente = em.find(Cliente.class, clienteDto.getCltCedula());
                if (cliente == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el cliente a modificar.", "guardarCliente NoResultException");
                }
                cliente.actualizar(clienteDto);
                cliente = em.merge(cliente);
            } else {
                cliente = new Cliente(clienteDto);
                em.persist(cliente);
            }
            et.commit();
            return new Respuesta(true, "", "", "Clientes", new ClienteDto(cliente));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar el cliente.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el cliente.", "guardarCliente " + ex.getMessage());
        }
    }*/
