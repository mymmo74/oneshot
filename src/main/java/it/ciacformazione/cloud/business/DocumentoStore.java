
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ciacformazione.cloud.business;

import it.ciacformazione.cloud.Configuration;
import it.ciacformazione.cloud.entity.Documento;
import it.ciacformazione.cloud.entity.Utente;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class DocumentoStore {
    @PersistenceContext
    EntityManager em;
    
    Utente loggato;
    
    @PostConstruct
    public void init(){
        loggato = em.find(Utente.class, 7);
        
    }
    
    public List<Documento> all(){
        return em.createQuery("select e from Documento e where e.utente= :usr")
                .setParameter("usr", loggato)
                .getResultList();
    }
    
    public Documento find(Long id){
        return em.find(Documento.class, id);
    }
    
    public Documento save(Documento d, InputStream is) {
        d.setUtente(loggato);
        Documento saved = em.merge(d);
        try {
            Files.copy(is, documentPath(saved.getNome_file()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new EJBException("save document failed...");
        }
        return saved;
    }
    
    public void remove(Long id) {
        Documento saved = find(id);
        try {
            Files.delete(documentPath(saved.getNome_file()));
        } catch (IOException ex) {
            throw new EJBException("delete document failed...");
        }
        em.remove(saved);
    }
    
    private Path documentPath(String name){
        System.out.println("user: " + loggato + " name: " + name);
        return Paths.get(Configuration.DOCUMENT_FOLDER + 
                loggato.getUser()+ "/" + name);
    }

    void removeByUser(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
