/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gustavo
 */
public class EntityManagerSingleton {
    
    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if(entityManager == null)  
        {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HomeComingPU");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void setEntityManager(EntityManager entityManager) {
        EntityManagerSingleton.entityManager = entityManager;
    }
    
    
}
