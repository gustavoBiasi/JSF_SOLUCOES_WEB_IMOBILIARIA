/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Category;
import repository.CategoryRepository;


/**
 *
 * @author Gustavo
 */

@ManagedBean(name = "categoryConverterBean")
@FacesConverter(value = "categoryConverter", forClass = model.Category.class)
public class CategoryConverter implements Converter{
  
    private CategoryRepository categoryRepository = new CategoryRepository();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
 
        if(string.equals("")) return null;
        return categoryRepository.findById(Long.parseLong(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       
       
       
       
        
        if (o == null || o.equals("") || o.equals("null")) {
            return "";
        } else {
            Category c = null;
            try{
                c = (Category) o;
                this.addAttribute(uic, c);
                
                Long codigo = c.getId();
                if(codigo != null) return String.valueOf(codigo);
            }
            catch(ClassCastException e)
           {
             e.printStackTrace();
           }
            
            return  ((Category) o).getTitle();
        }
        
    }
    protected void addAttribute(UIComponent component, Category o)
    {
        String key = String.valueOf(o.getId());
        this.getAttributesFrom(component).put(key,o);
    }
    
    protected Map<String, Object> getAttributesFrom(UIComponent component)
    {
        return component.getAttributes();
    }
}
