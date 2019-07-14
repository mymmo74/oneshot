/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ciacformazione.cloud.business;

import it.ciacformazione.cloud.Configuration;
import it.ciacformazione.cloud.entity.Utente;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @PersistenceContext
    EntityManager em;

    @Inject
    DocumentoStore docStore;

    public Optional<Utente> login(String usr, String pwd) {
        try {
            Utente p = em.createQuery("select e from Utente e "
                    + "where e.usr= :usr and e.pwd= :pwd", Utente.class)
                    .setParameter("usr", usr)
                    .setParameter("pwd", pwd)
                    .getSingleResult();
            return Optional.of(p);
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

    public List<Utente> findAll() {
        return em.createQuery("select e from Utente e order by e.cognome", Utente.class)
                .getResultList();
    }

    public Utente find(int id) {
        return em.find(Utente.class, id);
    }

    public Optional<Utente> findByUsr(String usr) {
        try {
            Utente p = em.createQuery("select e from Utente e "
                    + "where e.usr= :usr", Utente.class)
                    .setParameter("usr", usr)
                    .getSingleResult();
            return Optional.of(p);
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

    public Utente save(Utente a) {
        Utente saved = em.merge(a);
        Path path = Paths.get(Configuration.DOCUMENT_FOLDER + saved.getUser());
        if (Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(path);
            } catch (IOException ex) {
                throw new EJBException("save user failed...");
            }
        }
        return saved;
    }

    public void remove(int id) {
        Utente saved = find(id);
        em.createQuery("delete from Documento e where e.user= :usr")
                .setParameter("usr", saved)
                .executeUpdate();
        em.remove(saved);
        try {
            deleteDirectory(Paths.get(Configuration.DOCUMENT_FOLDER + saved.getUser()));
        } catch (IOException ex) {
            throw new EJBException("remove user failed...");
        }
    }

    private void deleteDirectory(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}