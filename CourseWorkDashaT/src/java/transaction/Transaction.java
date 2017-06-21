/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;

import facades.Order1FacadeLocal;
import facades.PaymentinformationFacadeLocal;
import facades.PaymentsystemFacadeLocal;
import facades.RoleFacadeLocal;
import facades.UserFacadeLocal;
import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import model.Order1;
import model.Paymentinformation;
import model.Paymentsystem;
import model.Role;
import model.User;

@Stateless
@LocalBean  
@TransactionManagement(TransactionManagementType.CONTAINER)  
@ApplicationException(rollback=true,inherited=false)
public class Transaction implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private Order1FacadeLocal order1FacadeLocal;
    @EJB
    private PaymentsystemFacadeLocal paymentsystemFacadeLocal;
    @EJB
    private UserFacadeLocal userFacadeLocal;
    @EJB
    private RoleFacadeLocal roleFacadeLocal;
    @EJB
    private PaymentinformationFacadeLocal paymentinformationFacadeLocal;

    @Resource
    SessionContext cont;
    
    Logger log;
    //point 6
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
    public void createUserAndPaymentSystem(User user, Paymentsystem paymentsystem){
        createUserAndRole(user);
        createPaymentSystem(paymentsystem);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
    public void createUserAndRole(User user){
        Role role  = new Role();
        userFacadeLocal.create(user);
        role.setName("Client");
        role.setLogin(user);
        this.roleFacadeLocal.create(role);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
    public void createPaymentSystem(Paymentsystem paymentsystem){
       paymentsystemFacadeLocal.create(paymentsystem);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
    public void createOrderAndPaymentInformation(Order1 order1, Paymentinformation paymentinformation){
        createOrder(order1);
        createPaymentInformation(paymentinformation);
        updatePaymentSystem(paymentinformation.getAccountOfBeneficiary());
        updatePaymentSystem(paymentinformation.getAccountOfSender());
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
    public void createOrder(Order1 order1){
        order1FacadeLocal.create(order1);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
    public void createPaymentInformation(Paymentinformation paymentinformation){
       paymentinformationFacadeLocal.create(paymentinformation);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
    public void updatePaymentSystem(Paymentsystem paymentsystem){
       paymentsystemFacadeLocal.edit(paymentsystem);
    }
    
    
//    //Work with rolback in first method
//    //point 7
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
//    public void createTransaction2(Ordering ordering, DataAboutPay2 dataAboutPay){
//            createOrderingPoint7(ordering);
//            dataAboutPay.setIdO(2);
//            if(!cont.getRollbackOnly()) {
//            createDataAboutPayPoint7(dataAboutPay);
//            } 
//    }
//    
//    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
//    public Ordering createOrderingPoint7(Ordering ordering){
//       orderingFacadeLocal.create(ordering);
//       cont.setRollbackOnly();
//       return ordering;
//    }
//    
//    @TransactionAttribute(TransactionAttributeType.SUPPORTS ) 
//    public void createDataAboutPayPoint7(DataAboutPay2 dataAboutPay){
//       dataAboutPayFacadeLocal.create(dataAboutPay);
//    }
//    //Work with rolback in second method
//    //8th item
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
//    public void createTransaction3(Ordering ordering, DataAboutPay2 dataAboutPay){   
//
//            Ordering ordering2 = createOrderingPoint8(ordering);
//            dataAboutPay.setIdO(ordering2.getIDOrdering());
//            createDataAboutPayPoint8(dataAboutPay); 
//            
//    }
//    
//    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
//    public Ordering createOrderingPoint8(Ordering ordering){
//       orderingFacadeLocal.create(ordering);
//       return ordering;
//    }
//    
//    @TransactionAttribute(TransactionAttributeType.SUPPORTS ) 
//    public void createDataAboutPayPoint8(DataAboutPay2 dataAboutPay){
//       dataAboutPayFacadeLocal.create(dataAboutPay);
//       cont.setRollbackOnly();
//    }
//    
//
//    //Work with roolnack in first method, but add data in second db
//    //9th item
////    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
////    public String createTransaction4(Ordering ordering, DataAboutPay dataAboutPay){
////        log = Logger.getLogger(this.getClass().getName());
////        try {
////            Ordering ordering2 = createOrdering2(ordering);
////            dataAboutPay.setIDOrdering(ordering2.getIDOrdering());
////        } catch (Exception e) {
////            createDataAboutPay3(dataAboutPay);
////            log.log(Level.INFO, " Ошибка 1 {0} ", e.getMessage() + "       " + Arrays.toString(e.getStackTrace()));
////        }
////        return "/index.xhtml";
////    }
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
//    public void createTransaction4(Ordering ordering, DataAboutPay2 dataAboutPay){
//        log = Logger.getLogger(this.getClass().getName());        
//            Ordering ord = createOrderingPoint9(ordering);
//            dataAboutPay.setIdO(ord.getIDOrdering());
//            createDataAboutPayPoint9(dataAboutPay);
//    }
//
//    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
//    public Ordering createOrderingPoint9(Ordering ordering){
//       orderingFacadeLocal.create(ordering);
//       return ordering;
//    }
//    
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW ) 
//    public void createDataAboutPayPoint9(DataAboutPay2 dataAboutPay){
//       dataAboutPayFacadeLocal.create(dataAboutPay);
//    }
//    
//    //Work with rollback in second method, but add data in first db
//    //10th item 
//    @TransactionAttribute(TransactionAttributeType.SUPPORTS) 
//    public void createTransaction5(Ordering ordering, DataAboutPay2 dataAboutPay){
//        log = Logger.getLogger(this.getClass().getName());
//        Ordering ordering2 = createOrderingPoint10(ordering);
//        dataAboutPay.setIdO(ordering2.getIDOrdering());
//        createDataAboutPayPoint10(dataAboutPay);
//    }
//    
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
//    public Ordering createOrderingPoint10(Ordering ordering){
//       orderingFacadeLocal.create(ordering);
//       return ordering;
//    }
//    
//    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
//    public void createDataAboutPayPoint10(DataAboutPay2 dataAboutPay){
//       dataAboutPayFacadeLocal.create(dataAboutPay);
//       cont.setRollbackOnly();
//    }
    

}
