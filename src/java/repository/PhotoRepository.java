/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Photo;
import utils.EntityManagerSingleton;

/**
 *
 * @author Lara
 */
public class PhotoRepository {
    private EntityManager manager;
    public PhotoRepository(){
        this.manager = EntityManagerSingleton.getEntityManager();
    }
    public boolean addPhoto(Photo photo){
      
        try{
            this.manager.persist(photo);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    
    // preciso do acesso  bidirecional, ou seja, nas fotos preciso que as propriedades estejam mapeadas
   
}
