/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.TABLE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name="tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    
    @Temporal(TemporalType.DATE)
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
     
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address adress = new Address();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "favorite_user_property", joinColumns = {
        @JoinColumn(name = "user_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "property_id",
                    nullable = false, updatable = false) 
    })
    private Set<Property> favorites = new HashSet<Property>(0);

    public Set<Property> getOwnProperties() {
        return ownProperties;
    }

    public void setOwnProperties(Set<Property> ownProperties) {
        this.ownProperties = ownProperties;
    }

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Property> ownProperties = new HashSet<Property>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public Set<Property> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Property> favorites) {
        this.favorites = favorites;
    }
    
   
}
