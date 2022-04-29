/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
@Embeddable
public class NotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "numetudiant")
    private int numetudiant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nummatiere")
    private int nummatiere;

    public NotePK() {
    }

    public NotePK(int numetudiant, int nummatiere) {
        this.numetudiant = numetudiant;
        this.nummatiere = nummatiere;
    }

    public int getNumetudiant() {
        return numetudiant;
    }

    public void setNumetudiant(int numetudiant) {
        this.numetudiant = numetudiant;
    }

    public int getNummatiere() {
        return nummatiere;
    }

    public void setNummatiere(int nummatiere) {
        this.nummatiere = nummatiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numetudiant;
        hash += (int) nummatiere;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotePK)) {
            return false;
        }
        NotePK other = (NotePK) object;
        if (this.numetudiant != other.numetudiant) {
            return false;
        }
        if (this.nummatiere != other.nummatiere) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.NotePK[ numetudiant=" + numetudiant + ", nummatiere=" + nummatiere + " ]";
    }

}
