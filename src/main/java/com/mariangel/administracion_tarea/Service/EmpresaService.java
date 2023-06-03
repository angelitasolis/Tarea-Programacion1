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
      EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getEmpresa(String ced) {
        try {
            Query qryEmpresas = em.createNamedQuery("Empresa.findByEmCedulajuridica", Empresa.class);
            qryEmpresas.setParameter("emCedulajuridica", ced);

            return new Respuesta(true, "", "", "Empresas", new EmpresaDto((Empresa) qryEmpresas.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe la empresa con el código ingresado.", "getEmpresa NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la empresa.", "getEmpresa NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la empresa.", "getEmpresa" + ex.getMessage());
        }
    }
 
    public Respuesta guardarEmpresa(EmpresaDto empresaDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Empresa empresa;
            if (empresaDto.getEmpresaCedJuridica() != null ) {
                empresa = em.find(Empresa.class, empresaDto.getEmpresaCedJuridica());
                if (empresa == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el empleado a modificar.", "guardarEmpleado NoResultException");
                }
                empresa.actualizar(empresaDto);
                empresa = em.merge(empresa);
            } else {
                empresa = new Empresa(empresaDto);
                em.persist(empresa);
            }
            et.commit();
            return new Respuesta(true, "", "", "Empresas", new EmpresaDto(empresa));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el empresa.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el empresa.", "guardar empresa " + ex.getMessage());
        }
    }
    
    
    
    public Respuesta eliminarEmpresa(String ced) {
        try {
            et = em.getTransaction();
            et.begin();
            Empresa empresa;
            if (ced != null ) {
                empresa = em.find(Empresa.class, ced);
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
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la empresa.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la empresa.", "eliminarEmpresa " + ex.getMessage());
        }
    }
}
