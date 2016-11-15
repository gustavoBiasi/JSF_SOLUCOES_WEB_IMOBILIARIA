/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Category;

/**
 *
 * @author Lara
 */
public class CategoryRepository {
    private EntityManager manager;
    
    public CategoryRepository(EntityManager manager){
        this.manager=manager;
    }
    
    public List<Category> GetAll(){
        Query query = manager.createQuery("SELECT c FROM Category c");
        return (List<Category>) query.getResultList();
    }
    
    public void Add (Category category){
        this.manager.persist(category);
    }
}
