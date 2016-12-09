/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.Address;
import model.Property;
import utils.EntityManagerSingleton;

/**
 *
 * @author Lara
 */
public class PropertyRepository implements Serializable{
    private EntityManager manager;
    
    public PropertyRepository(){
        this.manager = EntityManagerSingleton.getEntityManager();
    }
    
    public Long addProperty(Property property){
        try{
           if(!manager.getTransaction().isActive())  manager.getTransaction().begin();
            
            
            this.manager.persist(property);
            this.manager.flush();
            this.manager.getTransaction().commit();
            System.out.println("salvando propriedade sucesso");
            return property.getId();
        }catch(Exception e)
        {
            System.out.println("salvando falha propriedade");
            e.printStackTrace();
            return null;
        }
    }
    
    
    public void delete(Property p)
    {
       
        if(!manager.getTransaction().isActive())  manager.getTransaction().begin();
        manager.remove(p);
        manager.getTransaction().commit();
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
   public List<Property> findAllPropertiesOwned(long userId)
    {
          try{
            Query query = manager.createQuery("SELECT p FROM Property p WHERE p.owner.id = :userid");
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
    

    
    public void save(Property u)
    {
         if(!manager.getTransaction().isActive())  manager.getTransaction().begin();
        manager.persist(u);
        manager.getTransaction().commit();
    }
    public List<Property> findAllProperties(){
        try{
            Query query=manager.createQuery("SELECT p from Property p");
            return query.getResultList();
            
        }catch(NoResultException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Property> findFilteredProperties(String category, String state, String search){
        try{
            StringBuilder queryString = new StringBuilder("Select p from Property p WHERE 1=1 ");
            if(!category.equals("")) queryString.append("AND p.category.title = :category ");

            if(!state.equals("")) queryString.append("AND p.address.state = :state ");
       
            if(!search.equals("")) queryString.append("AND p.title LIKE :search ");
            Query query= manager.createQuery(queryString.toString());
            if(!category.equals("")) query.setParameter("category", category);
            if(!state.equals("")) query.setParameter("state", state);
            if(!search.equals("")) query.setParameter("search", "%"+search+"%");
           
            return query.getResultList();
            
        }catch(NoResultException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public Property getById(Long id){
        try{
            return this.manager.find(Property.class, id);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
