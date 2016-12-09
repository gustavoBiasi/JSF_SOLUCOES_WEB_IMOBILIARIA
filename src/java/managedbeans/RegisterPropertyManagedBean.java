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
import javax.faces.model.SelectItem;
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
import repository.UserRepository;
import services.CepService;
import utils.SessionUtils;


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
    
    private List<Category> categories = new ArrayList<Category>();

    
    private List<UploadedFile> photos = new ArrayList<UploadedFile>();
    private String cep = "";

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
     

    
    
    
    @PostConstruct
    public void init()
    {
        
               
        categories = categoryRepository.getAllCategories();
        photos = new ArrayList<UploadedFile>();
       
       
        
        
    }

    
   
    public List<Category> getCategories() {
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
        UserRepository userRepository = new UserRepository();
        User u = userRepository.getUser(Long.parseLong(SessionUtils.getUserId()));
        
        if(property != null && u != null  )
        {
            
            
            property.setOwner(u);
           
            Long newId = propertyRepository.addProperty(property);
            if(newId == null)
            {
                FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique sua conexão", "Ocorreu um erro!"));
            }
            property = new Property();
            return "detail?id=" + newId;
        }
        FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique sua conexão", "Ocorreu um erro!"));
        return null;
    }
    

    
    public void addPhoto(FileUploadEvent event)
    {

        Photo p = new Photo();
        p.setData(event.getFile().getContents());
        p.setFileName(event.getFile().getFileName());
        property.getPhotos().add(p);
        
        

    }

    public List<UploadedFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UploadedFile> photos) {
        this.photos = photos;
    }
    public void refreshCep()
    {

             if(cep.length() == 8)
             {
              CepService cepService = new CepService(this.cep);

              if(cepService.getSuccess() == 0)
              {

                  FacesContext.getCurrentInstance().addMessage(
                       null, 
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cep Inválido", "Ocorreu um erro!"));
              }
              else
              {

                   property.getAddress().setCity(cepService.getCity());
                   property.getAddress().setDistrict(cepService.getDistrict());
                   property.getAddress().setState(cepService.getState());
                   property.getAddress().setStreet(cepService.getStreet());
                   property.getAddress().setCep(cep);
              }
            }


    }
   
  
    
    public void displayErrorMessage(String message)
    {
                FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "Ocorreu um erro!"));
        
    }
}
