/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Service;

import com.mariangel.administracion_tarea.Model.Itinerario;
import com.mariangel.administracion_tarea.Model.ItinerarioDto;
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
public class ItinerarioService {
      EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getItinerario(Long id) {
        try {
            Query qryItinerario = em.createNamedQuery("Itinerario.findByIntId", Itinerario.class);
            qryItinerario.setParameter("intId", id);

            return new Respuesta(true, "", "", "Itinerario", new ItinerarioDto((Itinerario) qryItinerario.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe la itinerario con el código ingresado.", "getItinerario NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(ItinerarioService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el itinerario.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el itinerario.", "getItinerario NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(ItinerarioService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el itinerario.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la itinerario.", "getItinerario" + ex.getMessage());
        }
    }
 
    public Respuesta guardarItinerario(ItinerarioDto itinerarioDto) {
            try {
            et = em.getTransaction();
            et.begin();
            Itinerario itinerario;
            if(itinerarioDto.getItinerarioId() != null &&  itinerarioDto.getItinerarioId() > 0){
                itinerario = em.find(Itinerario.class, itinerarioDto.getItinerarioId());
                if (itinerario == null){
                    et.rollback();
                    return new Respuesta(false, "No se encrontrÃ³ el itinerario a modificar.", "guardarItinerario NoResultException");
                }
                itinerario.actualizar(itinerarioDto);
                itinerario = em.merge(itinerario);
            } else {
              itinerario = new Itinerario(itinerarioDto);
              em.persist(itinerario);
            }
            et.commit();
            return new Respuesta(true, "", "", "Itinerario", new ItinerarioDto(itinerario));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(ItinerarioService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el itinerario.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el itinerario.", "guardarItinerario " + ex.getMessage());
        }
       
    }
    
    public Respuesta eliminarItinerario(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            Itinerario itinerario;
            if (id != null && id > 0) {
                itinerario = em.find(Itinerario.class, id);
                if (itinerario == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el itinerario a eliminar.", "eliminarItinerario NoResultException");
                }
                em.remove(itinerario);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la itinerario a eliminar.", "eliminarItinerario NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el itinerario porque tiene relaciones con otros registros.", "eliminarItinerario " + ex.getMessage());
            }
            Logger.getLogger(ItinerarioService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la itinerario.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el itinerario.", "eliminarItinerario " + ex.getMessage());
        }
    }
    
     public Respuesta modificarTour(ItinerarioDto itinerarioDto, Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            if (itinerarioDto!= null) {
                Query query = em.createNamedQuery("Itinerario.findByIntId");
                query.setParameter("intId", id);
                Itinerario itinerario = (Itinerario) query.getSingleResult();
                System.out.println("Cédula del itinerario a buscar: " + itinerarioDto.getItinerarioId());
                if (itinerario == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el itinerario a actualizar.", "actualizarPaciente NoResultException");
                }

                itinerario.actualizar(itinerarioDto);
                itinerario = em.merge(itinerario);
                et.commit();
                em.refresh(itinerario);
                itinerario = em.find(Itinerario.class, itinerario.getIntId());
                return new Respuesta(true, "", "", "Itinerario", new ItinerarioDto(itinerario));
            } else {
                et.rollback();
                return new Respuesta(false, "No se proporcionó un codigo válida para actualizar el itinerario.", "actualizarPaciente InvalidParameterException");
            }
        } catch (NoResultException ex) {
            et.rollback();
            Logger.getLogger(TourService.class.getName()).log(Level.SEVERE, "Ocurrió un error al actualizar el itinerario.", ex);
            return new Respuesta(false, "Ocurrió un error al actualizar el itinerario.", "actualizarPaciente " + ex.getMessage());
        }
    }
    
    
    
}
