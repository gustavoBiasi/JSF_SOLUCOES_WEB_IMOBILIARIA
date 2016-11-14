/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Gustavo
 */
@Entity
public class Sale implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    private Property property;
    private User formerOwner;
    private User newOwner;
    private BigDecimal cost;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

}
