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
@Table(name = "matiere")
@NamedQueries({
        @NamedQuery(name = "Matiere.findAll", query = "SELECT m FROM Matiere m") })
@Cacheable(value = false)
public class Matiere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nummatiere")
    private Integer nummatiere;
    @Size(max = 255)
    @Column(name = "design")
    private String design;
    @Column(name = "coef")
    private Integer coef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matiere")
    private List<Note> noteList;

    public Matiere() {
    }

    public Matiere(Integer nummatiere) {
        this.nummatiere = nummatiere;
    }

    public Integer getNummatiere() {
        return nummatiere;
    }

    public void setNummatiere(Integer nummatiere) {
        this.nummatiere = nummatiere;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
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
        hash += (nummatiere != null ? nummatiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matiere)) {
            return false;
        }
        Matiere other = (Matiere) object;
        if ((this.nummatiere == null && other.nummatiere != null)
                || (this.nummatiere != null && !this.nummatiere.equals(other.nummatiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDesign();
    }

}
