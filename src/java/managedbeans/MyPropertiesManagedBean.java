/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import model.Property;
import model.User;
import org.primefaces.model.map.DefaultMapModel;

import repository.AddressRepository;
import repository.CategoryRepository;
import repository.PropertyRepository;
import repository.UserRepository;
import utils.SessionUtils;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class MyPropertiesManagedBean implements Serializable{
    private UserRepository userRepository = new UserRepository();
    private PropertyRepository propertyRepository = new PropertyRepository();
 
    
    private User user = new User();
   

    private List<Property> allProperties;


    @PostConstruct
    public void init()
    {

        user = userRepository.getUser(Long.parseLong(SessionUtils.getUserId()));
        
        allProperties = new ArrayList(user.getOwnProperties());

        
    }



    public PropertyRepository getPropertyRepository() {
        return propertyRepository;
    }

    public void setPropertyRepository(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String edit(Property p)
    {
        
        return null;
    }
    
    public void delete(Property p)
    {
        propertyRepository.delete(p);
        user.getOwnProperties().remove(p);
        userRepository.save(user);
        allProperties = new ArrayList(user.getOwnProperties());
    }

    public List<Property> getAllProperties() {
        return allProperties;
    }

    public void setAllProperties(List<Property> allProperties) {
        this.allProperties = allProperties;
    }
    
    
}
