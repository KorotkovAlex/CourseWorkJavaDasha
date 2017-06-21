/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import facades.Order1FacadeLocal;
import facades.PaymentinformationFacadeLocal;
import facades.PaymentsystemFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.Order1;
import model.Paymentinformation;
import model.Paymentsystem;
import model.Product;
import model.User;
import transaction.Transaction;

/**
 *
 * @author Саня
 */
@Named(value = "orderManagedBean")
@SessionScoped
public class OrderManagedBean implements Serializable {
    public OrderManagedBean() {
    }
    @EJB
    private Order1FacadeLocal order1FacadeLocal;
    @EJB
    private PaymentinformationFacadeLocal paymentinformationFacadeLocal;
    @EJB 
    private Transaction transaction;
    @EJB
    private PaymentsystemFacadeLocal paymentsystemFacadeLocal;
    
    private Paymentinformation paymentinformation = new Paymentinformation();
    private Order1 order1 = new Order1();
    private Product product = new Product();
    private User user = new User();
    
    public List<Order1> findAll () {
        return order1FacadeLocal.findAll();
    }
    
    public String create() {
        this.order1FacadeLocal.create(getOrder1());
        return "/faces/index.xhtml";
    }
    
    public String createOrderAndPayment() {
        order1.setOrderPrice(order1.getQuantityOfProducts() * product.getPrice());
        //order1.setOrderStatus(false);
        order1.setUserLogin(user);
        order1.setIdProduct(product);
        paymentinformation.setPrice(order1.getQuantityOfProducts() * product.getPrice());
        paymentinformation.setCode("code");     
        paymentinformation.setAccountOfBeneficiary(paymentsystemFacadeLocal.find("Company"));
        paymentinformation.setAccountOfSender(paymentsystemFacadeLocal.find(user.getLogin()));
        if(paymentinformation.getAccountOfSender().getBalance() >=  order1.getOrderPrice()) {
            paymentinformation.getAccountOfBeneficiary().setBalance(paymentinformation.getAccountOfBeneficiary().getBalance()+order1.getOrderPrice());      
            paymentinformation.getAccountOfSender().setBalance(paymentinformation.getAccountOfSender().getBalance()-order1.getOrderPrice());
            transaction.createOrderAndPaymentInformation(order1,paymentinformation);
        }
        return "/faces/index.xhtml";
    }

    public String edit(Order1 order1) {
        this.setOrder1(order1);
        return "edit";
    }
    
    public String edit() {
        this.order1FacadeLocal.edit(getOrder1());
        return "index";
    }
    public void remove(Paymentsystem paymentsystem) {
        this.order1FacadeLocal.remove(getOrder1());
    }

    public Order1 find(Object id) {
        return this.order1FacadeLocal.find(id);
    }

    public Order1 getOrder1() {
        return order1;
    }

    public void setOrder1(Order1 order1) {
        this.order1 = order1;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    
}
