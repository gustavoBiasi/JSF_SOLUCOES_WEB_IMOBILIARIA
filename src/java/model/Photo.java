/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gustavo
 */
@Entity
public class Photo implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="photo_id")
    private Integer id; 
    private String fileName;
   
    
}
