/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import javax.persistence.EntityManager;
import model.Property;

/**
 *
 * @author Lara
 */
public class PropertyRepository {
    private EntityManager manager;
    
    public PropertyRepository(EntityManager manager){
        this.manager=manager;
    }
    
    public void AddProperty(Property property){
        this.manager.persist(property);
    }
}
