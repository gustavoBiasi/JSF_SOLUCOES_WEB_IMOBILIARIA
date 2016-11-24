/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;


import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Property;
import model.User;
import repository.PropertyRepository;
import repository.UserRepository;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class LoginManagedBean {
    
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
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("login", user.getEmail());
            session.setAttribute("userId", user.getId());
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
