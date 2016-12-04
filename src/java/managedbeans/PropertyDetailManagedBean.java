/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import model.Property;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import repository.PropertyRepository;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;
/**
 *
 * @author Lara
 */
@ManagedBean
@RequestScoped
public class PropertyDetailManagedBean implements Serializable {

    private MapModel geoModel;
   
    private String centerGeoMap = "41.850033, -87.6500523";
    private String centerRevGeoMap = "41.850033, -87.6500523";

    public MapModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public String getCenterRevGeoMap() {
        return centerRevGeoMap;
    }

    public void setCenterRevGeoMap(String centerRevGeoMap) {
        this.centerRevGeoMap = centerRevGeoMap;
    }
     
    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        property = propertyRepository.getById(1);
       
    }
    
    
    
    public void onGeocode(GeocodeEvent event) {
           List<GeocodeResult> results = event.getResults();
           
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
                
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                result.setAddress(id);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }
    public Property property;
    private PropertyRepository propertyRepository = new PropertyRepository();

     public String id;

     public Property getProperty(){
         return this.property;
     }
    public void setId(String id) {
            this.id = id;
    }

    public String detailAction() {
       //now action property contains "delete"
       this.property= propertyRepository.getById(Integer.parseInt(id));
       return "detail";
    }
    
    
}
