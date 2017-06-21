/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Саня
 */
@Entity
@Table(name = "paymentsystem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymentsystem.findAll", query = "SELECT p FROM Paymentsystem p")
    , @NamedQuery(name = "Paymentsystem.findByBalance", query = "SELECT p FROM Paymentsystem p WHERE p.balance = :balance")
    , @NamedQuery(name = "Paymentsystem.findBySecurityCode", query = "SELECT p FROM Paymentsystem p WHERE p.securityCode = :securityCode")
    , @NamedQuery(name = "Paymentsystem.findByAccountNumber", query = "SELECT p FROM Paymentsystem p WHERE p.accountNumber = :accountNumber")
    , @NamedQuery(name = "Paymentsystem.findByFirstName", query = "SELECT p FROM Paymentsystem p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Paymentsystem.findByPassportData", query = "SELECT p FROM Paymentsystem p WHERE p.passportData = :passportData")
    , @NamedQuery(name = "Paymentsystem.findByPhone", query = "SELECT p FROM Paymentsystem p WHERE p.phone = :phone")
    , @NamedQuery(name = "Paymentsystem.findByLastName", query = "SELECT p FROM Paymentsystem p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Paymentsystem.findByLogin", query = "SELECT p FROM Paymentsystem p WHERE p.login = :login")})
public class Paymentsystem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "balance")
    private int balance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "security_code")
    private String securityCode;
    @Size(max = 45)
    @Column(name = "account_number")
    private String accountNumber;
    @Size(max = 45)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 45)
    @Column(name = "passport_data")
    private String passportData;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Недопустимый формат номера телефона/факса (должен иметь формат xxx-xxx-xxxx)")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "phone")
    private String phone;
    @Size(max = 45)
    @Column(name = "last_name")
    private String lastName;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "login")
    private String login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountOfSender")
    private List<Paymentinformation> paymentinformationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountOfBeneficiary")
    private List<Paymentinformation> paymentinformationList1;

    public Paymentsystem() {
    }

    public Paymentsystem(String login) {
        this.login = login;
    }

    public Paymentsystem(String login, int balance, String securityCode) {
        this.login = login;
        this.balance = balance;
        this.securityCode = securityCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlTransient
    public List<Paymentinformation> getPaymentinformationList() {
        return paymentinformationList;
    }

    public void setPaymentinformationList(List<Paymentinformation> paymentinformationList) {
        this.paymentinformationList = paymentinformationList;
    }

    @XmlTransient
    public List<Paymentinformation> getPaymentinformationList1() {
        return paymentinformationList1;
    }

    public void setPaymentinformationList1(List<Paymentinformation> paymentinformationList1) {
        this.paymentinformationList1 = paymentinformationList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paymentsystem)) {
            return false;
        }
        Paymentsystem other = (Paymentsystem) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Paymentsystem[ login=" + login + " ]";
    }
    
}
