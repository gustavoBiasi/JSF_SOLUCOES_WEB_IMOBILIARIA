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
public class MainManagedBean implements Serializable{
    private UserRepository userRepository = new UserRepository();
    private PropertyRepository propertyRepository = new PropertyRepository();
    private AddressRepository addressRepository = new AddressRepository();
    private CategoryRepository categoryRepository = new CategoryRepository();
    private User user = new User();
   
    private List<Property> favoriteProperties;
    private List<Property> allProperties;
    private List<Property> filteredProperties;
    
    private String selectedState = "";
    private String selectedCategory = "";
    private String searchProperty = "";
    private String fav = "0";
    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    @PostConstruct
    public void init()
    {

        user = userRepository.getUser(Long.parseLong(SessionUtils.getUserId()));
        
        allProperties = propertyRepository.findAllProperties();
       
        favoriteProperties = new ArrayList();
        favoriteProperties.addAll(user.getFavorites());
        
        
        fav = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("fav");
        if(fav != null) 
        {
            allProperties = new ArrayList(favoriteProperties);
            
        }

        filteredProperties = new ArrayList(allProperties);
        
       
        
        
        
    }
    
    public boolean getIsFavorited(Property p)
    {
        
        return user.getFavorites().contains(p);
    }

    
    public void favoriteProperty(Property p)
    {
        if(user.getFavorites().contains(p)) user.getFavorites().remove(p);
        else user.getFavorites().add(p);
        userRepository.save(user);

        
    }

  
    
    public void filterProperties(final AjaxBehaviorEvent event)
    {
        filteredProperties = new ArrayList(propertyRepository.findFilteredProperties(selectedCategory, selectedState, searchProperty));
        
      
    }

    public String getSearchProperty() {
        return searchProperty;
    }

    public void setSearchProperty(String searchProperty) {
        this.searchProperty = searchProperty;
    }
    
   

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<String> getAllStates() {
        return addressRepository.findAllPropertyStates();
    }
    
    public List<String> getAllCategories() {
        return categoryRepository.getAllCategoriesAsString();
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

    public List<Property> getFilteredProperties() {
        return filteredProperties;
    }

    public void setFilteredProperties(List<Property> filteredProperties) {
        this.filteredProperties = filteredProperties;
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
