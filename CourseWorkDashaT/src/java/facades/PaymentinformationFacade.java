/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Paymentinformation;

/**
 *
 * @author Саня
 */
@Stateless
public class PaymentinformationFacade extends AbstractFacade<Paymentinformation> implements PaymentinformationFacadeLocal {

    @PersistenceContext(unitName = "CourseWorkDashaDBPaymentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentinformationFacade() {
        super(Paymentinformation.class);
    }
    
}
