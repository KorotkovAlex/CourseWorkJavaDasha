/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import facades.PaymentinformationFacadeLocal;
import facades.PaymentsystemFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.Paymentinformation;
import model.Paymentsystem;

/**
 *
 * @author Саня
 */
@Named(value = "paymentManagedBean")
@SessionScoped
public class PaymentManagedBean implements Serializable {

    public PaymentManagedBean() {
    }
    
    @EJB
    private PaymentsystemFacadeLocal paymentsystemFacadeLocal;
    @EJB
    private PaymentinformationFacadeLocal paymentinformationFacadeLocal;
    
    private Paymentinformation paymentinformation = new Paymentinformation();
    private Paymentsystem paymentsystem = new Paymentsystem();    
    private Paymentsystem paymentsystem1 = new Paymentsystem();   
    
     public List<Paymentsystem> findAll () {
        return paymentsystemFacadeLocal.findAll();
    }
    
    public String create() {
        this.paymentsystemFacadeLocal.create(getPaymentsystem());
        return "/faces/index.xhtml";
    }

    public String edit(Paymentsystem paymentsystem) {
        this.setPaymentsystem(paymentsystem);
        return "edit";
    }
    
    public String edit() {
        this.paymentsystemFacadeLocal.edit(this.getPaymentsystem());
        return "index";
    }
    public void remove(Paymentsystem paymentsystem) {
        this.paymentsystemFacadeLocal.remove(paymentsystem);
    }

    public void find(Object id) {        
        paymentsystem1 = this.paymentsystemFacadeLocal.find(id);
    }
    
    public Paymentinformation getPaymentinformation() {
        return paymentinformation;
    }

    public void setPaymentinformation(Paymentinformation paymentinformation) {
        this.paymentinformation = paymentinformation;
    }

    public Paymentsystem getPaymentsystem() {
        return paymentsystem;
    }

    public void setPaymentsystem(Paymentsystem paymentsystem) {
        this.paymentsystem = paymentsystem;
    }

    public Paymentsystem getPaymentsystem1() {
        return paymentsystem1;
    }

    public void setPaymentsystem1(Paymentsystem paymentsystem1) {
        this.paymentsystem1 = paymentsystem1;
    }    
}
