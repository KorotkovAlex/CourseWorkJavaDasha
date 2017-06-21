/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.List;
import javax.ejb.Local;
import model.Paymentsystem;

/**
 *
 * @author Саня
 */
@Local
public interface PaymentsystemFacadeLocal {

    void create(Paymentsystem paymentsystem);

    void edit(Paymentsystem paymentsystem);

    void remove(Paymentsystem paymentsystem);

    Paymentsystem find(Object id);

    List<Paymentsystem> findAll();

    List<Paymentsystem> findRange(int[] range);

    int count();
    
}
