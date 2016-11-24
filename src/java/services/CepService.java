/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Gustavo
 */
public class CepService {
    private String state = "";
    private String city = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
    private String district = "";

    private String street = "";
 
    private int success = 0;


    
    
 
    @SuppressWarnings("rawtypes")
    public CepService(String cep) {
        
        try {
            URL url = new URL(
                    "https://viacep.com.br/ws/" + cep
                            + "/json/");
 
            Document document = getDocumento(url);
 
            Element root = document.getRootElement();
 
            JSONObject json = new JSONObject(document);
            
            try{
                this.city = json.get("localidade").toString();
                this.street = json.get("logradouro").toString();
                this.district  = json.get("bairro").toString();
                this.state = json.get("uf").toString();
                success = 1;
            }catch(JSONException e)
            {
                e.printStackTrace();
                success = 0;
            }
           
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
 
        return document;
    }
}
