/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
@Entity
@Table(name = "note")
@NamedQueries({
        @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n") })
@Cacheable(value = false)
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotePK notePK;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Column(name = "note")
    private Float note;
    @JoinColumn(name = "numetudiant", referencedColumnName = "numetudiant", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Etudiant etudiant;
    @JoinColumn(name = "nummatiere", referencedColumnName = "nummatiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matiere matiere;

    public Note() {
    }

    public Note(NotePK notePK) {
        this.notePK = notePK;
    }

    public Note(int numetudiant, int nummatiere) {
        this.notePK = new NotePK(numetudiant, nummatiere);
    }

    public NotePK getNotePK() {
        return notePK;
    }

    public void setNotePK(NotePK notePK) {
        this.notePK = notePK;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notePK != null ? notePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.notePK == null && other.notePK != null)
                || (this.notePK != null && !this.notePK.equals(other.notePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Note[ notePK=" + notePK + " ]";
    }

}
