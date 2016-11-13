/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gustavo
 */
@Embeddable
public class RentId implements java.io.Serializable{
    @ManyToOne
    private User user;
    @ManyToOne
    private Property property;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
   
    
    
}
