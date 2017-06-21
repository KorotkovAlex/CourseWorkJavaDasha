/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import facades.RoleFacadeLocal;
import facades.UserFacadeLocal;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import model.Paymentsystem;
import model.Role;
import model.User;
import transaction.Transaction;

/**
 *
 * @author Саня
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable{

    /**
     * Creates a new instance of UserManagedBean
     */
    @EJB
    private UserFacadeLocal userFacadeLocal;
    private User user = new User();
    @EJB 
    private RoleFacadeLocal roleFacadeLocal;   
    private Role role = new Role();
    
    @EJB
    private Transaction transaction;
    
    
    
    public UserManagedBean() {
    }
    
    public List<User> findAll () {
        return this.userFacadeLocal.findAll();
    }
    
    public String create() {
        this.userFacadeLocal.create(getUser());
//        this.userFacadeLocal.addInJoinTable(user.getLogin(), user.getIdRole());
        return "/faces/index.xhtml";
        
    }
    
    public String createNewUser() throws NoSuchAlgorithmException {
        String password = getHex(user.getPassword());
        user.setPassword(password);        
        transaction.createUserAndPaymentSystem(user, createPaymentSystem(user));
        return "/faces/index.xhtml";
        
    }
    private static Paymentsystem createPaymentSystem(User user) {
        Paymentsystem paymentsystem = new Paymentsystem();       
        paymentsystem.setBalance(100000);
        paymentsystem.setSecurityCode(user.getPassword());
//        paymentsystem.setAccountNumber("AAA");  
//        paymentsystem.setFirstName(user.getFirstName());
//        paymentsystem.setPassportData(user.getPassportData());
//        paymentsystem.setPhone(user.getPhone());
//        paymentsystem.setLastName(user.getLastName());
        paymentsystem.setLogin(user.getLogin());
        return paymentsystem;        
    }
    private static String getHex(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String edit(User user) {
        this.setUser(user);
        return "edit";
    }
    
    public String edit() {
        this.userFacadeLocal.edit(this.getUser());
        return "index";
    }
    public void remove(User user) {
        this.userFacadeLocal.remove(user);
    }

    public void find(Object id) {
        user = this.userFacadeLocal.find(id);
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}