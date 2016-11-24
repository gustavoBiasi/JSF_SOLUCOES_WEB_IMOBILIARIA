/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.Property;
import utils.EntityManagerSingleton;

/**
 *
 * @author Lara
 */
public class PropertyRepository {
    private EntityManager manager;
    
    public PropertyRepository(){
        this.manager = EntityManagerSingleton.getEntityManager();
    }
    
    public boolean addProperty(Property property){
        try{
            this.manager.persist(property);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Property> findAllPropertiesNotOwned(long userId)
    {
          try{
            Query query = manager.createQuery("SELECT p FROM Property p WHERE p.owner.id != :userid");
            query.setParameter("userid", userId);
            return query.getResultList();
        }catch(NoResultException e) {
            return null;
        }
        
    }
    
    public List<Property> findAllFavoritedProperties(long userId)
    {
          try{
            Query query = manager.createQuery("select fav from User u join u.favorites fav where u.id = :userid" );
            query.setParameter("userid", userId);
            return query.getResultList();
        }catch(NoResultException e) {
            return null;
        }
        
    }
    
    
}
