/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Photo;

/**
 *
 * @author Lara
 */
public class PhotoRepository {
    private EntityManager manager;
    public PhotoRepository(EntityManager manager){
        this.manager=manager;
    }
    public void Add(Photo photo){
        this.manager.persist(photo);
    }
    // preciso do acesso  bidirecional, ou seja, nas fotos preciso que as propriedades estejam mapeadas
   
}
