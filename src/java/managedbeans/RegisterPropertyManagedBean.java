/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Category;

import model.Property;
import repository.CategoryRepository;

import repository.PropertyRepository;


/**
 *
 * @author Gustavo
 */
@ViewScoped
@ManagedBean
public class RegisterPropertyManagedBean {
    Property property = new Property();
    CategoryRepository categoryRepository = new CategoryRepository();
    
    PropertyRepository propertyRepository = new PropertyRepository();
    private Map<Long,String> categories;
    
    @PostConstruct
    public void init()
    {
        categories = new HashMap<>();
        List<Category> categoryList = categoryRepository.getAllCategories();
        
        for(Category c : categoryList)
        {
            categories.put(c.getId(), c.getTitle());
        }
    }

    public Map<Long, String> getCategories() {
        return categories;
    }
    
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    
    public String createUser()
    {
        if(property != null  )
        {
            
            propertyRepository.addProperty(property);
            property = new Property();
           
        }
        FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email já registrado", "Ocorreu um erro!"));
        return null;
    }
    
    public void getAddressDetail()
    {
   

    }
}
