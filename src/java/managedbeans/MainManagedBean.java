/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Property;
import model.User;
import repository.PropertyRepository;
import repository.UserRepository;
import utils.SessionUtils;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class MainManagedBean {
    private UserRepository userRepository = new UserRepository();
    private PropertyRepository propertyRepository = new PropertyRepository();
    private User user = new User();
    private List<Property> myProperties;
    private List<Property> favoriteProperties;
    private List<Property> allProperties;

    @PostConstruct
    public void init()
    {

        user = userRepository.getUser(Integer.parseInt(SessionUtils.getUserId()));
        allProperties = propertyRepository.findAllPropertiesNotOwned(user.getId());
        favoriteProperties = propertyRepository.findAllFavoritedProperties(user.getId());
        
    }
}
