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
import model.User;
import repository.UserRepository;
import services.CepService;

/**
 *
 * @author Gustavo
 */
@ViewScoped
@ManagedBean
public class SignUpManagedBean {
    User user = new User();
  
    
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
           CepService cepService = new CepService(user.getAdress().getCep());
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
           }
          
                  

    }
}
