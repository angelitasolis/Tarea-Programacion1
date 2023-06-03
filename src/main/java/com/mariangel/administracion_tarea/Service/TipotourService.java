/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Service;

import com.mariangel.administracion_tarea.Model.TipoTourDto;
import com.mariangel.administracion_tarea.Model.Tipotour;
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
public class TipotourService {
      EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getTipotour(Long id) {
        try {
            Query qryTipotour = em.createNamedQuery("Tipotour.findByTtCodigo", Tipotour.class);
            qryTipotour.setParameter("ttCodigo", id);

            return new Respuesta(true, "", "", "Tipotour", new TipoTourDto((Tipotour) qryTipotour.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe  tipoTour con el código ingresado.", "getTipotour NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TipotourService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tipo tour.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la tipoTour.", "getTipotour NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TipotourService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tipo tour.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la tipoTour.", "getTipotour" + ex.getMessage());
        }
    }
 
    public Respuesta guardarTipotour(TipoTourDto tipoToursDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Tipotour tipoTour;
            if (tipoToursDto.getTptCodigo() != null && tipoToursDto.getTptCodigo() > 0) {
                tipoTour = em.find(Tipotour.class, tipoToursDto.getTptCodigo());
                if (tipoTour == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el tipo tour a modificar.", "guardarEmpleado NoResultException");
                }
                tipoTour.actualizar(tipoToursDto);
                tipoTour = em.merge(tipoTour);
            } else {
                tipoTour = new Tipotour(tipoToursDto);
                em.persist(tipoTour);
            }
            et.commit();
            return new Respuesta(true, "", "", "Tipotour", new TipoTourDto(tipoTour));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TipotourService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el tipoTour.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el tipoTour.", "guardar tipoTour " + ex.getMessage());
        }
    }
    
    
    
    public Respuesta eliminarTipotour(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            Tipotour tipoTour;
            if (id != null && id > 0) {
                tipoTour = em.find(Tipotour.class, id);
                if (tipoTour == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la tipoTour a eliminar.", "eliminarTipotour NoResultException");
                }
                em.remove(tipoTour);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la tipoTour a eliminar.", "eliminarTipotour NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la tipoTour porque tiene relaciones con otros registros.", "eliminarTipotour " + ex.getMessage());
            }
            Logger.getLogger(TipotourService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la tipoTour.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la tipoTour.", "eliminarTipotour " + ex.getMessage());
        }
    }
}
