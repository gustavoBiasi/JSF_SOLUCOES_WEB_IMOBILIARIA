/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
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


    
    public CepService()
    {
        
    }
 
    @SuppressWarnings("rawtypes")
    public CepService(String cep) {
        
        try {
            String url = 
                    "http://viacep.com.br/ws/" + cep
                            + "/json/";
 
            JSONObject json = getDocumento(url);
 
           
            
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
 
    public JSONObject getDocumento(String url) throws DocumentException, IOException {
        InputStream is = new URL(url).openStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset().forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          is.close();
          return json;
        }catch(Exception e) 
        {
            e.printStackTrace();
            is.close();
        }
        return null;
    }
    

    private static String readAll(Reader rd) throws IOException {
      StringBuilder sb = new StringBuilder();
      int cp;
      while ((cp = rd.read()) != -1) {
        sb.append((char) cp);
      }
      return sb.toString();
    }

}
