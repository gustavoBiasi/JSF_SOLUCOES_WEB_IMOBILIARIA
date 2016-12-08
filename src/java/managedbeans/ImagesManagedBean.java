/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;


import com.sun.prism.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import model.Photo;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import repository.PhotoRepository;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ApplicationScoped
public class ImagesManagedBean {
    
    PhotoRepository photoRepository = new PhotoRepository();
    public StreamedContent getImage() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
        {
            return new DefaultStreamedContent();
        }
        else
        {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Photo image = photoRepository.getById(Long.valueOf(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getData()));
        }
        
    }
}
