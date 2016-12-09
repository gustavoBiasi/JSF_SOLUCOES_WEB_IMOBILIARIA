/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.Serializable;
import javax.persistence.EntityManager;

import model.Photo;
import utils.EntityManagerSingleton;

/**
 *
 * @author Lara
 */
public class PhotoRepository implements Serializable{
    private EntityManager manager;
    public PhotoRepository(){
        this.manager = EntityManagerSingleton.getEntityManager();
    }
    public boolean addPhoto(Photo photo){
      
        try{
            if(!manager.getTransaction().isActive())  manager.getTransaction().begin();
            this.manager.persist(photo);
            manager.getTransaction().commit();
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
        
    public Photo getById(Long id){
        try{
            return this.manager.find(Photo.class, id);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
   
   
}
