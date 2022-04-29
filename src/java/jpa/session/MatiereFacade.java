/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Matiere;

/**
 *
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
@Stateless
public class MatiereFacade extends AbstractFacade<Matiere> {
    @PersistenceContext(unitName = "M2-Trigger-JsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatiereFacade() {
        super(Matiere.class);
    }

}
