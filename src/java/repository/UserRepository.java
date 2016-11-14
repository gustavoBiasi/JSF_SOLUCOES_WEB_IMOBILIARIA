/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.User;

/**
 *
 * @author Lara
 */
public class UserRepository {
    private EntityManager manager;
    
    public UserRepository(EntityManager manager){
        this.manager=manager;
    }
    
    public void AddUser(User user){
        this.manager.persist(user);
    }
    
    public User Authenticate(String login, String password){
        Query query = this.manager.createQuery("Select x from User x where x.email="+login+" and x.password ="+password);
        return (User) query.getSingleResult();
    }
}
