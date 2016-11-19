/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.User;
import utils.EntityManagerSingleton;

/**
 *
 * @author Lara
 */
public class UserRepository {
    private EntityManager manager;
    
    public UserRepository(){
        this.manager = EntityManagerSingleton.getEntityManager();
    }
    
    public boolean addUser(User user){
        try{
            this.manager.persist(user);
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
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
    
    public User authenticateUser(String login, String password){
        try{
             
            Query query = this.manager.createQuery("select u from User u where u.email= :login and u.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            
            return (User) query.getSingleResult();
        }catch(NoResultException e)
        {
            return null;
        }
    }
    
    
}
