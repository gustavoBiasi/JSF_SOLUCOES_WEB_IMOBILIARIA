/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
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
import model.Photo;

import model.Property;
import model.User;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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
public class RegisterPropertyManagedBean implements Serializable {
    Property property = new Property();
    CategoryRepository categoryRepository = new CategoryRepository();
    
    PropertyRepository propertyRepository = new PropertyRepository();
    private Map<Long,String> categories;
    
    private String addedPhotos = null;
    
    private List<UploadedFile> photos = new ArrayList<UploadedFile>();
    private String cep = "";

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
     
    public String getAddedPhotos() {
        return addedPhotos;
    }
    
    
    
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
    
    public String createProperty()
    {
        if(property != null  )
        {
            for(UploadedFile photo : photos)
            {
            
                property.getPhotos().add(new Photo(UploadFileUtil.upload(photo, SessionUtils.getUserId())));
            
            }
        
            
            propertyRepository.addProperty(property);
            property = new Property();
           
        }
        FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique sua conexão", "Ocorreu um erro!"));
        return null;
    }
    

    
    public void addPhoto(FileUploadEvent event)
    {

        photos.add(event.getFile());
        
        

    }

    public List<UploadedFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UploadedFile> photos) {
        this.photos = photos;
    }

   
  
    
    public void displayErrorMessage(String message)
    {
                FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "Ocorreu um erro!"));
        
    }
}
