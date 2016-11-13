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

/**
 *
 * @author Gustavo
 * 
 */
//lara: o address estava sem a anotação entity.
@Entity
public class Address {
    @Id
    @Column(name = "address_id")
    //lara, acrescentei o generatedvalue
    @GeneratedValue
    private Integer id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String number;
    private String cep;
    private String additionalInfo;
    
}
