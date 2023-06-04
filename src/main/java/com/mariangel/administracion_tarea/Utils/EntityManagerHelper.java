/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariangel.administracion_tarea.Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ccarranza
 */
public class EntityManagerHelper {

    private static final EntityManagerHelper SINGLENTON = new EntityManagerHelper();
    private static EntityManagerFactory emf;
    private static EntityManager em;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("com.mariangel_Administracion_Tarea_PU");
            em = emf.createEntityManager();
        } catch (ExceptionInInitializerError e) {
            throw e;
        }
    }

    public static EntityManagerHelper getInstance() {

        return SINGLENTON;
    }

    public static EntityManager getManager() {
        if (em == null || !em.isOpen()) {
            emf = Persistence.createEntityManagerFactory("com.mariangel_Administracion_Tarea_PU");
            em = emf.createEntityManager();
        }
        return em;
    }
}
