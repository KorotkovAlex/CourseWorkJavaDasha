/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Саня
 */
@Entity
@Table(name = "order1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o")
    , @NamedQuery(name = "Order1.findByIdOrder", query = "SELECT o FROM Order1 o WHERE o.idOrder = :idOrder")
    , @NamedQuery(name = "Order1.findByOrderData", query = "SELECT o FROM Order1 o WHERE o.orderData = :orderData")
    , @NamedQuery(name = "Order1.findByQuantityOfProducts", query = "SELECT o FROM Order1 o WHERE o.quantityOfProducts = :quantityOfProducts")
    , @NamedQuery(name = "Order1.findByOrderNumber", query = "SELECT o FROM Order1 o WHERE o.orderNumber = :orderNumber")
    , @NamedQuery(name = "Order1.findByOrderStatus", query = "SELECT o FROM Order1 o WHERE o.orderStatus = :orderStatus")
    , @NamedQuery(name = "Order1.findByOrderPrice", query = "SELECT o FROM Order1 o WHERE o.orderPrice = :orderPrice")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_order")
    private Integer idOrder;
    @Column(name = "order_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderData;
    @Column(name = "quantity_of_products")
    private Integer quantityOfProducts;
    @Column(name = "order_number")
    private Boolean orderNumber;
    @Column(name = "order_status")
    private Boolean orderStatus;
    @Column(name = "order_price")
    private Integer orderPrice;
    @JoinColumn(name = "user_login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private User userLogin;
    @JoinColumn(name = "id_product", referencedColumnName = "idproduct")
    @ManyToOne(optional = false)
    private Product idProduct;

    public Order1() {
    }

    public Order1(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Date getOrderData() {
        return orderData;
    }

    public void setOrderData(Date orderData) {
        this.orderData = orderData;
    }

    public Integer getQuantityOfProducts() {
        return quantityOfProducts;
    }

    public void setQuantityOfProducts(Integer quantityOfProducts) {
        this.quantityOfProducts = quantityOfProducts;
    }

    public Boolean getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Boolean orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Order1[ idOrder=" + idOrder + " ]";
    }
    
}
