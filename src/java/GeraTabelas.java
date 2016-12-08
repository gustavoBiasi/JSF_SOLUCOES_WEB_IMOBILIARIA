
import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Category;
import model.Property;
import model.User;
import repository.PropertyRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lara
 */
public class GeraTabelas {
    public static void main (String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HomeComingPU");
        entityManagerFactory.close();
        System.out.println("GeraTabelas.main()");
        System.out.println("salvando propriedade");
        Property property=new Property();
        property.setTitle("Casa na praia");
        property.setSalePrice(new BigDecimal("1.1"));
        property.setBathrooms(2);
        property.setBedrooms(3);
        Category category=new Category();
        category.setDescription("PRAIANA");
        category.setTitle("PRAIANA");
        property.setCategory(category);
        property.setDailyRentPrice(BigDecimal.valueOf(20.0));
        property.setParkingSlot(3);
        property.setIsRentable(true);
        property.setIsVendible(false);
        User user = new User();
        user.setName("Lara");
        user.setEmail("teste@teste.com");
        user.setPassword("teste");
        user.setPhone("(22)9999-9999");
        property.setOwner(user);
        property.setSquareMeter(200.00);
        Address address = new Address();
        address.setCep("05201-210");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setStreet("Rua demifonte");
        address.setDistrict("Perus");
        address.setAdditionalInfo("Jardim Adelfiore");
        address.setNumber("9");
        property.setAddress(address); 
        property.setDescription("Casa n praia, ótima localização. Beira mar e brisa leve.");
        PropertyRepository propertyrepository=new PropertyRepository();
        propertyrepository.addProperty(property);
        System.out.println("salvando propriedade");
    }
    
}
