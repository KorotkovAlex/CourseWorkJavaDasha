/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;


import facades.ProductFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.Paymentsystem;
import model.Product;

/**
 *
 * @author Саня
 */
@Named(value = "productManagedBean")
@Dependent
public class ProductManagedBean {

    /**
     * Creates a new instance of ProductManagedBean
     */
    public ProductManagedBean() {
    }
    @EJB
    private ProductFacadeLocal productFacadeLocal;
    
    private Product product = new Product();
    
    
     public List<Product> findAll () {
        return productFacadeLocal.findAll();
    }
    
    public String create() {
        this.productFacadeLocal.create(getProduct());
        return "/faces/index.xhtml";
    }

    public String edit(Product product) {
        this.setProduct(product);
        return "edit";
    }
    
    public String edit() {
        this.productFacadeLocal.edit(getProduct());
        return "index";
    }
    public void remove(Product product) {
        this.productFacadeLocal.remove(product);
    }

    public Product find(Object id) {
        return this.productFacadeLocal.find(id);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    

    
}
