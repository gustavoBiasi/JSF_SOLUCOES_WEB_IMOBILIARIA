/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "rent_user_property")
@AssociationOverrides({
		@AssociationOverride(name = "pk.user",
			joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "pk.property",
			joinColumns = @JoinColumn(name = "property_id")) })
public class Rent implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
   
    @EmbeddedId
    public RentId getPK()
    {
        return pk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  
    public void setPk(RentId pk) {
        this.pk = pk;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }
    
    @Transient 
    public User getUser()
    {
        return getPK().getUser();
    }
    
    public void setUser(User user)
    {
        getPK().setUser(user);
    }
    
     @Transient 
    public Property getProperty()
    {
        return getPK().getProperty();
    }
    
    public void setProperty(Property property)
    {
        getPK().setProperty(property);
    }
   
    private RentId pk = new RentId();
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    private BigDecimal dailyCost;
    
    
}
