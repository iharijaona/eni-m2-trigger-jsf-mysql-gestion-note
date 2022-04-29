/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
@Entity
@Table(name = "etudiant")
@NamedQueries({
        @NamedQuery(name = "Etudiant.findAll", query = "SELECT e FROM Etudiant e") })
@Cacheable(value = false)
public class Etudiant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numetudiant")
    private Integer numetudiant;

    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Column(name = "moyenne")
    private Float moyenne;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiant")
    private List<Note> noteList;

    public Etudiant() {
    }

    public Etudiant(Integer numetudiant) {
        this.numetudiant = numetudiant;
    }

    public Integer getNumetudiant() {
        return numetudiant;
    }

    public void setNumetudiant(Integer numetudiant) {
        this.numetudiant = numetudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Float moyenne) {
        this.moyenne = moyenne;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numetudiant != null ? numetudiant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.numetudiant == null && other.numetudiant != null)
                || (this.numetudiant != null && !this.numetudiant.equals(other.numetudiant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNom();
    }

}
