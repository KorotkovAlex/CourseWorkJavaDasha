/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.List;
import javax.ejb.Local;
import model.Paymentinformation;

/**
 *
 * @author Саня
 */
@Local
public interface PaymentinformationFacadeLocal {

    void create(Paymentinformation paymentinformation);

    void edit(Paymentinformation paymentinformation);

    void remove(Paymentinformation paymentinformation);

    Paymentinformation find(Object id);

    List<Paymentinformation> findAll();

    List<Paymentinformation> findRange(int[] range);

    int count();
    
}
