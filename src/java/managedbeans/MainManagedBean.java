/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Property;
import model.User;
import repository.AddressRepository;
import repository.PropertyRepository;
import repository.UserRepository;
import utils.SessionUtils;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class MainManagedBean implements Serializable{
    private UserRepository userRepository = new UserRepository();
    private PropertyRepository propertyRepository = new PropertyRepository();
    private AddressRepository addressRepository = new AddressRepository();
    private User user = new User();
    private List<Property> myProperties;
    private List<Property> favoriteProperties;
    private List<Property> allProperties;

    @PostConstruct
    public void init()
    {

        user = userRepository.getUser(Long.parseLong(SessionUtils.getUserId()));
        
        allProperties = propertyRepository.findAllProperties();
        favoriteProperties = propertyRepository.findAllFavoritedProperties(user.getId());
        
        
        
        
    }


    public List<String> getBrands() {
        return addressRepository.findAllPropertyStates();
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

    public List<Property> getMyProperties() {
        return myProperties;
    }

    public void setMyProperties(List<Property> myProperties) {
        this.myProperties = myProperties;
    }

    public List<Property> getFavoriteProperties() {
        return favoriteProperties;
    }

    public void setFavoriteProperties(List<Property> favoriteProperties) {
        this.favoriteProperties = favoriteProperties;
    }

    public List<Property> getAllProperties() {
        return allProperties;
    }

    public void setAllProperties(List<Property> allProperties) {
        this.allProperties = allProperties;
    }
    
    
}
