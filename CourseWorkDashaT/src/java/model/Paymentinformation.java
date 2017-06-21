/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Саня
 */
@Entity
@Table(name = "paymentinformation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymentinformation.findAll", query = "SELECT p FROM Paymentinformation p")
    , @NamedQuery(name = "Paymentinformation.findByIdpayment", query = "SELECT p FROM Paymentinformation p WHERE p.idpayment = :idpayment")
    , @NamedQuery(name = "Paymentinformation.findByData", query = "SELECT p FROM Paymentinformation p WHERE p.data = :data")
    , @NamedQuery(name = "Paymentinformation.findByCode", query = "SELECT p FROM Paymentinformation p WHERE p.code = :code")
    , @NamedQuery(name = "Paymentinformation.findByOrderNumber", query = "SELECT p FROM Paymentinformation p WHERE p.orderNumber = :orderNumber")
    , @NamedQuery(name = "Paymentinformation.findByPrice", query = "SELECT p FROM Paymentinformation p WHERE p.price = :price")})
public class Paymentinformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpayment")
    private Integer idpayment;
    @Size(max = 45)
    @Column(name = "data")
    private String data;
    @Size(max = 45)
    @Column(name = "code")
    private String code;
    @Size(max = 45)
    @Column(name = "order_number")
    private String orderNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @JoinColumn(name = "account_of_sender", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Paymentsystem accountOfSender;
    @JoinColumn(name = "account_of_beneficiary", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Paymentsystem accountOfBeneficiary;

    public Paymentinformation() {
    }

    public Paymentinformation(Integer idpayment) {
        this.idpayment = idpayment;
    }

    public Paymentinformation(Integer idpayment, int price) {
        this.idpayment = idpayment;
        this.price = price;
    }

    public Integer getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(Integer idpayment) {
        this.idpayment = idpayment;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Paymentsystem getAccountOfSender() {
        return accountOfSender;
    }

    public void setAccountOfSender(Paymentsystem accountOfSender) {
        this.accountOfSender = accountOfSender;
    }

    public Paymentsystem getAccountOfBeneficiary() {
        return accountOfBeneficiary;
    }

    public void setAccountOfBeneficiary(Paymentsystem accountOfBeneficiary) {
        this.accountOfBeneficiary = accountOfBeneficiary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpayment != null ? idpayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paymentinformation)) {
            return false;
        }
        Paymentinformation other = (Paymentinformation) object;
        if ((this.idpayment == null && other.idpayment != null) || (this.idpayment != null && !this.idpayment.equals(other.idpayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Paymentinformation[ idpayment=" + idpayment + " ]";
    }
    
}
