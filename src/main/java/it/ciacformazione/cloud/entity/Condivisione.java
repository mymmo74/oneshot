/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ciacformazione.cloud.entity;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tss
 */
@Entity
@Table(name = "t_condivisioni")
public class Condivisione implements Serializable {
    @Id
    @Column(name = "id_condivisione")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utente", referencedColumnName = "id_utente")
    private Utente utente;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    private Documento doc;

    public Condivisione() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Condivisione other = (Condivisione) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condivisione{" + "id=" + id + ", utente=" + utente + ", doc=" + doc + '}';
    }
    
    
}
