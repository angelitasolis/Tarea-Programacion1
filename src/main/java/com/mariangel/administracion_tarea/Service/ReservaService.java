/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Service;

import com.mariangel.administracion_tarea.Model.Cliente;
import com.mariangel.administracion_tarea.Model.ClienteDto;
import com.mariangel.administracion_tarea.Model.Reserva;
import com.mariangel.administracion_tarea.Model.ReservaDto;
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
public class ReservaService {
EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getReserva(Long pCedulaCargar) {
        try {
            Query qryReserva = em.createNamedQuery("Reserva.findByRsId", Reserva.class);
            qryReserva.setParameter("rsId", pCedulaCargar);

            return new Respuesta(true, "", "", "Reserva", new ReservaDto((Reserva) qryReserva.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe la reservacion ingresada.", "getReserva NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(ReservaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la reservacion.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la reservacion.", "getReserva NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(ReservaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la reservacion.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la reservacion.", "getReserva " + ex.getMessage());
        }
    }

    public Respuesta guardarReserva(ReservaDto reservacionDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Reserva reservacion;
            if (reservacionDto.getReservaId() == null) {
                reservacion = em.find(Reserva.class, reservacionDto.getReservaId());
                if (reservacion == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la reservacion a modificar.", "guardarReserva NoResultException");
                }
                reservacion.actualizar(reservacionDto);
                reservacion = em.merge(reservacion);
            } else {
                reservacion = new Reserva(reservacionDto);
                em.persist(reservacion);
            }

            et.commit();
            return new Respuesta(true, "", "", "Reserva", new ReservaDto(reservacion));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(ReservaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar la reservacion.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la reservacion.", "guardarReserva " + ex.getMessage());
        }
    }

    public Respuesta eliminarReserva(Long pCedulaEliminar) {
        try {
            et = em.getTransaction();
            et.begin();
            Reserva reservacion;
            if (pCedulaEliminar != null) {
                reservacion = em.find(Reserva.class, pCedulaEliminar);
                if (reservacion == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la reservacion a eliminar.", "eliminarReserva NoResultException");
                }
                em.remove(reservacion);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la reservacion a eliminar.", "eliminarReserva NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la reservacion porque tiene relaciones con otros registros.", "eliminarReserva " + ex.getMessage());
            }
            Logger.getLogger(ReservaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el reservacion.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la reservacion.", "eliminarReserva " + ex.getMessage());
        }
    }
    /*
    public Respuesta modificarReserva(ReservaDto reservacionDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Reserva reservacion;
            if (reservacionDto.getRsvId() != null) {
                reservacion = em.find(Reserva.class, reservacionDto.getRsvId());
                if (reservacion == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la reservacion a modificar.", "guardarReserva NoResultException");
                }
                reservacion.actualizar(reservacionDto);
                reservacion = em.merge(reservacion);
            } else {
                reservacion = new Reserva(reservacionDto);
                em.persist(reservacion);
            }
            et.commit();
            return new Respuesta(true, "", "", "Reservas", new ReservaDto(reservacion));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(ReservaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar la reservacion.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la reservacion.", "guardarReserva " + ex.getMessage());
        }
    }*/
}
