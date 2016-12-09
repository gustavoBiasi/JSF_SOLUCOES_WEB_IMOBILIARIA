/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.User;
import utils.EntityManagerSingleton;

/**
 *
 * @author Lara
 */
public class UserRepository implements Serializable{
    private EntityManager manager;
    
    public UserRepository(){
        this.manager = EntityManagerSingleton.getEntityManager();
    }
    
    public Long addUser(User user){
        try{
            if(!manager.getTransaction().isActive())  manager.getTransaction().begin();
            this.manager.persist(user);
            return user.getId();
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }
    public boolean removeUser(User user){
        try{
            this.manager.remove(user);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public User getUser(Long userId)
    {
        User user = null;
        try{
            user = this.manager.find(User.class, userId);
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
    public User authenticateUser(String email, String password){
        try{
             
            Query query = this.manager.createQuery("select u from User u where u.email= :login and u.password = :password");
            query.setParameter("login", email);
            query.setParameter("password", password);
            
            return (User) query.getResultList().get(0);
        }catch(ArrayIndexOutOfBoundsException e)
        {
            return null;
        }
    }
    
    public void save(User u)
    {
        manager.persist(u);
        
    }
    
    public User findUserByEmail(String email)
    {
        try
        {
            Query query = this.manager.createQuery("select u from User u where u.email = :email");
            query.setParameter("email", email);
            
            return (User) query.getSingleResult();
        }
        catch(NoResultException e)
        {
            return null;
        }
    }
    
    
}
