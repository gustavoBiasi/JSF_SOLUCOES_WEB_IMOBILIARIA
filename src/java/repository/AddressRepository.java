/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import model.Address;

/**
 *
 * @author Lara
 */
public class AddressRepository implements Serializable{
    private EntityManager manager;
    
    public AddressRepository(EntityManager manager){
        this.manager=manager;
    }
    
    public void AddAddress(Address address){
        this.manager.persist(address);
    }
    
    
}
