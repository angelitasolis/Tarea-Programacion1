/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Service;

import com.mariangel.administracion_tarea.Model.Tour;
import com.mariangel.administracion_tarea.Model.TourDto;
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
public class TourService {

    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getTour(String pCedulaCargar) {
        try {
            Query qryTour = em.createNamedQuery("Tour.findByTrsCodigotour", Tour.class);
            qryTour.setParameter("trsCodigotour", pCedulaCargar);

            return new Respuesta(true, "", "", "Tour", new TourDto((Tour) qryTour.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe el tour ingresado.", "getTour NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TourService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tour.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tour.", "getTour NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TourService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tour.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tour.", "getTour " + ex.getMessage());
        }
    }

    public Respuesta guardarTour(TourDto tourDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Tour tour;
            if (tourDto.getTrsCodigo() == null) {
                tour = em.find(Tour.class, tourDto.getTrsCodigo());
                if (tour == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el tour a modificar.", "guardarTour NoResultException");
                }
                tour.actualizar(tourDto);
                tour = em.merge(tour);
            } else {
                tour = new Tour(tourDto);
                em.persist(tour);
            }

            et.commit();
            return new Respuesta(true, "", "", "Tour", new TourDto(tour));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TourService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar el tour.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el tour.", "guardarTour " + ex.getMessage());
        }
    }

    public Respuesta eliminarTour(String pCedulaEliminar) {
        try {
            et = em.getTransaction();
            et.begin();
            Tour tour;
            if (pCedulaEliminar != null) {
                tour = em.find(Tour.class, pCedulaEliminar);
                if (tour == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el tour a eliminar.", "eliminarTour NoResultException");
                }
                em.remove(tour);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el tour a eliminar.", "eliminarTour NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el tour porque tiene relaciones con otros registros.", "eliminarTour " + ex.getMessage());
            }
            Logger.getLogger(TourService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el tour.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el tour.", "eliminarTour " + ex.getMessage());
        }
    }
    
    public Respuesta modificarTour(TourDto tourDto, String id) {
        try {
            et = em.getTransaction();
            et.begin();
            if (tourDto!= null) {
                Query query = em.createNamedQuery("Tour.findByTrsCodigotour");
                query.setParameter("trsCodigotour", id);
                Tour tour = (Tour) query.getSingleResult();
                System.out.println("Cédula del tour a buscar: " + tourDto.getTrsCodigo());
                if (tour == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el tour a actualizar.", "actualizarPaciente NoResultException");
                }

                tour.actualizar(tourDto);
                tour = em.merge(tour);
                et.commit();
                em.refresh(tour);
                tour = em.find(Tour.class, tour.getTrsCodigotour());
                return new Respuesta(true, "", "", "Tour", new TourDto(tour));
            } else {
                et.rollback();
                return new Respuesta(false, "No se proporcionó un codigo válida para actualizar el tour.", "actualizarPaciente InvalidParameterException");
            }
        } catch (NoResultException ex) {
            et.rollback();
            Logger.getLogger(TourService.class.getName()).log(Level.SEVERE, "Ocurrió un error al actualizar el tour.", ex);
            return new Respuesta(false, "Ocurrió un error al actualizar el tour.", "actualizarPaciente " + ex.getMessage());
        }
    }

}
