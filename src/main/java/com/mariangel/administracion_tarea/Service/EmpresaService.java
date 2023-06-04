/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.administracion_tarea.Service;

import com.mariangel.administracion_tarea.Model.Empresa;
import com.mariangel.administracion_tarea.Model.EmpresaDto;
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
public class EmpresaService {

   EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    public Respuesta getEmpresa(String pCedulaCargar) {
        try {
            Query qryEmpresa = em.createNamedQuery("Empresa.findByEmCedulajuridica", Empresa.class);
            qryEmpresa.setParameter("emCedulajuridica", pCedulaCargar);

            return new Respuesta(true, "", "", "Empresa", new EmpresaDto((Empresa) qryEmpresa.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe el empresa ingresado.", "getEmpresa NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la empresa.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la empresa.", "getEmpresa NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la empresa.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la empresa.", "getEmpresa " + ex.getMessage());
        }
    }

    public Respuesta guardarEmpresa(EmpresaDto empresaDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Empresa empresa = em.find(Empresa.class, empresaDto.getEmpresaCedJuridica());

            if (empresa != null) {
                // La empresa ya existe, se debe actualizar
                empresa.actualizar(empresaDto);
                empresa = em.merge(empresa);
            } else {
                // La empresa no existe, se debe crear
                empresa = new Empresa(empresaDto);
                em.persist(empresa);
            }

            et.commit();
            return new Respuesta(true, "", "", "Empresa", new EmpresaDto(empresa));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar la empresa.", ex);
            return new Respuesta(false, "Ocurrió un error al guardar la empresa.", "guardarEmpresa " + ex.getMessage());
        }
    }

    public Respuesta eliminarEmpresa(String pCedulaEliminar) {
        try {
            et = em.getTransaction();
            et.begin();
            Empresa empresa;
            if (pCedulaEliminar != null) {
                empresa = em.find(Empresa.class, pCedulaEliminar);
                if (empresa == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la empresa a eliminar.", "eliminarEmpresa NoResultException");
                }
                em.remove(empresa);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la empresa a eliminar.", "eliminarEmpresa NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la empresa porque tiene relaciones con otros registros.", "eliminarEmpresa " + ex.getMessage());
            }
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar la empresa.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la empresa.", "eliminarEmpresa " + ex.getMessage());
        }
    }
//    public Respuesta modificarPaciente(EmpresaDto empresaDto, Long id) {
//        try {
//            et = em.getTransaction();
//            et.begin();
//            if (empresaDto != null) {
//                Query query = em.createNamedQuery("Empresa.findByPkPacCedula");
//                query.setParameter("pkPacCedula", id);
//                Empresa empresa = (Empresa) query.getSingleResult();
//                System.out.println("Cédula del empresa a buscar: " + empresaDto.getEmpresaCedJuridica());
//                if (empresa == null) {
//                    et.rollback();
//                    return new Respuesta(false, "No se encontró el empresa a actualizar.", "actualizarPaciente NoResultException");
//                }
//
//                empresa.actualizar(empresaDto);
//                empresa = em.merge(empresa);
//                et.commit();
//                em.refresh(empresa);
//                empresa = em.find(Empresa.class, empresa.getEmCedulajuridica());
//                return new Respuesta(true, "", "", "Paciente", new EmpresaDto(empresa));
//            } else {
//                et.rollback();
//                return new Respuesta(false, "No se proporcionó una cédula válida para actualizar el empresa.", "actualizarPaciente InvalidParameterException");
//            }
//        } catch (NoResultException ex) {
//            et.rollback();
//            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al actualizar el empresa.", ex);
//            return new Respuesta(false, "Ocurrió un error al actualizar el empresa.", "actualizarPaciente " + ex.getMessage());
//        }
//    }
}
