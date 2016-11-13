/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gustavo
 */
@Entity
public class PropertyCategory implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private long id;
    private String title;
    private String description;
    private boolean isEnabled;
 
    
    
    
}
