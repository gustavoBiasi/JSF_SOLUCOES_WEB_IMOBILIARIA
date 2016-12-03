/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Category;

import model.Property;
import repository.CategoryRepository;

import repository.PropertyRepository;
import utils.SessionUtils;
import utils.UploadFileUtil;


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
    
    
    private List<Part> photos = new ArrayList<Part>();
    private Part currentPhoto;
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
    
    public void addPhoto()
    {
        if(currentPhoto != null)
        {
            photos.add(currentPhoto);
            currentPhoto = null;
        }
    }
    
    public void send()
    {
       
        
        // salva fotos
        for(Part photo : photos)
        {
            
            UploadFileUtil.upload(photo, SessionUtils.getUserId());
        }
    }
}
