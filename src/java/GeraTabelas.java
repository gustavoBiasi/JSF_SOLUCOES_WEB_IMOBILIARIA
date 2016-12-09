
import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Category;
import model.Property;
import model.User;
import repository.CategoryRepository;
import repository.PropertyRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lara
 */
public class GeraTabelas {
    public static void main (String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HomeComingPU");
        entityManagerFactory.close();
      
        CategoryRepository categoryRepository = new CategoryRepository();
        
        Category a = new Category();
        a.setIsEnabled(true);
        a.setTitle("Casa");
        a.setDescription("Casa comum");
        categoryRepository.addCategory(a);
        System.out.println("salvando categoria");
        
        Category b = new Category();
        b.setIsEnabled(true);
        b.setTitle("Apartamento");
        b.setDescription("Apartamento médio");
        categoryRepository.addCategory(b);
        System.out.println("salvando categoria");
        
        Category c = new Category();
        c.setIsEnabled(true);
        c.setTitle("Duplex");
        c.setDescription("Casa de dois andares");
        categoryRepository.addCategory(c);
        System.out.println("salvando categoria");
        
        Category d = new Category();
        d.setIsEnabled(true);
        d.setTitle("Condomínio");
        d.setDescription("Condôminio fechado");
        categoryRepository.addCategory(d);
        System.out.println("salvando categoria");
    }
    
}
