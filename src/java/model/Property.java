/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gustavo
 */
@Entity
public class Property implements Serializable{
    @Id
    @GeneratedValue()
    @Column(name = "property_id")
    private Long id;
   
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
    private User owner;
    
    private String title;

 
    
    private BigDecimal  salePrice;
    
    
    private BigDecimal dailyRentPrice;
    
   
    private boolean isRentable;
    
   
    private boolean isVendible;
    
    
    private Double squareMeter;
    private String description;
    
    private Integer bathrooms;
    private Integer bedrooms;
    private Integer parkingSlot;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Category category = new Category();
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private Collection<Photo> photos = new ArrayList<Photo>();
	
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address = new Address();
    
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    
   
    public Photo getFirstPhoto()
    {
        
        if(photos.size() > 0) return (Photo) photos.toArray()[0];
        
        return new Photo();
    }

    public String getCurrencySalePrice()
    {
        if(salePrice == null) return "-";
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
        return n.format(this.salePrice.divide(new BigDecimal(100)));
    }
    
    public String getCurrencyDailyRent()
    {
        if(dailyRentPrice == null) return "-";
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
        return n.format(this.dailyRentPrice.divide(new BigDecimal(100)));
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
   public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getDailyRentPrice() {
        return dailyRentPrice;
    }

    public void setDailyRentPrice(BigDecimal dailyRentPrice) {
        this.dailyRentPrice = dailyRentPrice;
    }

    public boolean isIsRentable() {
        return isRentable;
    }

    public void setIsRentable(boolean isRentable) {
        this.isRentable = isRentable;
    }

    public boolean isIsVendible() {
        return isVendible;
    }

    public void setIsVendible(boolean isVendible) {
        this.isVendible = isVendible;
    }

    public Double getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(Double squareMeter) {
        this.squareMeter = squareMeter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(Integer parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public Category getCategoriy() {
        return category;
    }

    public void setCategoriy(Category categoriy) {
        this.category = categoriy;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

 
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
}
