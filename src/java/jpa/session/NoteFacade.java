/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Note;

/**
 *
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
@Stateless
public class NoteFacade extends AbstractFacade<Note> {
    @PersistenceContext(unitName = "M2-Trigger-JsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteFacade() {
        super(Note.class);
    }

}
