/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import model.Property;
import repository.PropertyRepository;

/**
 *
 * @author Lara
 */
@ManagedBean
@RequestScoped
public class PropertyDetailManagedBean {

    public Property property;
    private PropertyRepository pr = new PropertyRepository();

     public String id;

     public Property getProperty(){
         return this.property;
     }
	public void setId(String id) {
		this.id = id;
	}

	public String detailAction() {
	   //now action property contains "delete"
           this.property=pr.getById(Integer.parseInt(id));
           return "detail";
	}
}
