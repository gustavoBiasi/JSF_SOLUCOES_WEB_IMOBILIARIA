/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import model.User;
import repository.UserRepository;
import services.CepService;

/**
 *
 * @author Gustavo
 */
@ViewScoped
@ManagedBean
public class SignUpManagedBean implements Serializable{
    User user = new User();
    String cep = "";

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    UserRepository userRepository = new UserRepository();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String createUser()
    {
        if(user != null && userRepository.findUserByEmail(user.getEmail()) == null )
        {
            
            userRepository.addUser(user);
            user = new User();
            return "/login";
        }
        FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email já registrado", "Ocorreu um erro!"));
        return null;
    }
    
    public void refreshCep()
    {
      
          if(cep.length() == 8 )
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

                 user.getAdress().setCity(cepService.getCity());
                 user.getAdress().setDistrict(cepService.getDistrict());
                 user.getAdress().setState(cepService.getState());
                 user.getAdress().setStreet(cepService.getStreet());
                 user.getAdress().setCep(cep);
            }
          
          }
                  

    }
}
