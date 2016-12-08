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
import javax.servlet.http.HttpSession;

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
public class LoginManagedBean implements Serializable{
    
    private UserRepository userRepository = new UserRepository();
    private User user = new User();
    
    
    private PropertyRepository propertyRepository = new PropertyRepository();
    
    
    public String send()
    {
        user = userRepository.authenticateUser(user.getEmail(), user.getPassword());
        if(user == null)
        {
            user = new User();
            
            FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário/Senha inválidos", "Ocorreu um erro!"));
                return null;
        }
        
        else 
        {
            // Armazenando o login do usuario na Sessao
            
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user.getEmail());
            session.setAttribute("userid", user.getId().toString());
            return "/main";
        }
    
    
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
