/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Paymentsystem;

/**
 *
 * @author Саня
 */
@Stateless
public class PaymentsystemFacade extends AbstractFacade<Paymentsystem> implements PaymentsystemFacadeLocal {

    @PersistenceContext(unitName = "CourseWorkDashaDBPaymentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentsystemFacade() {
        super(Paymentsystem.class);
    }
    
}
