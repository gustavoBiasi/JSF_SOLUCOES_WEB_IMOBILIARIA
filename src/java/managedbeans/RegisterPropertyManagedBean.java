/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Address;
import model.Property;
import model.User;
import repository.PropertyRepository;
import repository.UserRepository;

/**
 *
 * @author Gustavo
 */
@ViewScoped
@ManagedBean
public class RegisterPropertyManagedBean {
    Property property = new Property();
  
    
    PropertyRepository propertyRepository = new PropertyRepository();

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
