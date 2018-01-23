/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *
 * @author archey
 */
@Entity
public class Command extends Model{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private Date dateOrder = new Date();;
    private Date dateDelivPlan;
    private Date dateDelivAct;
    @ManyToMany
    private Collection<Product> products;
    @ManyToOne
    private Util customer;

    public Command() {
    }

    public Command(Date dateDelivPlan, Collection<Product> products, Util customer) {
        this.dateDelivPlan = dateDelivPlan;
//        this.dateDelivAct = dateDelivAct;
        this.products = products;
        this.customer = customer;
    }
    public Command(Date dateDelivPlan, Date dateDelivAct, Collection<Product> products, Util customer) {
        this.dateDelivPlan = dateDelivPlan;
//        this.dateDelivAct = dateDelivAct;
        this.products = products;
        this.customer = customer;
        this.dateDelivAct = dateDelivAct;
    }
    
    // ============= - GETTERS & SETTERS - ============= //

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDateDelivPlan() {
        return dateDelivPlan;
    }

    public void setDateDelivPlan(Date dateDelivPlan) {
        this.dateDelivPlan = dateDelivPlan;
    }

    public Date getDateDelivAct() {
        return dateDelivAct;
    }

    public void setDateDelivAct(Date dateDelivAct) {
        this.dateDelivAct = dateDelivAct;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Util getCustomer() {
        return customer;
    }

    public void setCustomer(Util customer) {
        this.customer = customer;
    }
    
    public double getTotal(){
        double total = 0;
        
        for(Product prod:this.products){
            total += prod.getTTC();
        }
        
        return total;
    }

    @Override
    public String toString() {
        return  id + "; " + dateOrder + "; " + getTotal() + "; " + customer.getlName() + " " + customer.getfName();
    }
    
}
