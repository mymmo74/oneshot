/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ciacformazione.cloud.business;

import it.ciacformazione.cloud.Configuration;
import it.ciacformazione.cloud.entity.Utente;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class UtenteStore {
    
    @PersistenceContext()
    EntityManager em;
    
    /**
     * Restituisce tutti gli Utenti da DB
     *
     * @return tutti gli Utenti
     */
    public List<Utente> all() {
        return em.createQuery("select e FROM Utente e ORDER BY e.cognome", Utente.class)
                .getResultList();
    }

    /**
     * Insert o Update Utente su DB
     *
     * @param a
     * @return
     */
    public Utente save(Utente a) {
        Utente utente= em.merge(a);
        Path path = Paths.get(Configuration.DOCUMENT_FOLDER + utente.getUser());
        if (Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(path);
            } catch (IOException ex) {
                throw new EJBException("save user failed...");
            }
        }
        return utente;
    }
    
    /**
     * Restituisce l'Utente con id
     *
     * @param id
     * @return
     */
    public Utente find(int id) {
        return em.find(Utente.class, id);
    }
    
    /**
     * Rimuove da DB il Tag tramite id
     *
     * @param id
     */
    public void remove(int id) {
        Utente toremove = em.find(Utente.class, id);
        em.remove(toremove);
    }
    
    public Optional<Utente> findByUsr(String usr) {
        try {
            Utente p = em.createQuery("select e from Utente e "
                    + "where e.user= :usr", Utente.class)
                    .setParameter("usr", usr)
                    // se non è messo nel try catch se non trova il dato va in errore
                    .getSingleResult();
            return Optional.of(p);
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }
    
    public Optional<Utente> login(String usr, String pwd) {
        try {
            Utente p = em.createQuery("select e from Utente e "
                    + "where e.user= :usr and e.pw= :pwd", Utente.class)
                    .setParameter("usr", usr)
                    .setParameter("pwd", pwd)
                    .getSingleResult();
            return Optional.of(p);
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }
}
