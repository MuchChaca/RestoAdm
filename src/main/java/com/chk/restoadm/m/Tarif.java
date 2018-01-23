/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 *
 * @author archey
 */
@Entity
public class Tarif extends Model {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private double ht;
    private double taxe;
    private Date dateValid = new Date();
    /*@ManyToOne
    private Product product;*/

    public Tarif() {
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

    public double getHt() {
        return ht;
    }

    public void setHt(double ht) {
        this.ht = ht;
    }

    public double getTaxe() {
        return taxe;
    }

    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }

    public Date getDateValid() {
        return dateValid;
    }

    public void setDateValid(Date dateValid) {
        this.dateValid = dateValid;
    }
/*
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    */
    public void setHt(String s){
        double d = Double.parseDouble(s);
        this.setHt(d);
    }
    public void setTaxe(String s) {
        double d = Double.parseDouble(s);
        this.setTaxe(d);
    }
    
    public double getTTC(){
        return this.getHt() * (1 + this.getTaxe());
    }
    
    
    
    
    
    
    @Override
    public String toString() {
        return "Tarif{" + "id=" + id + ", ht=" + ht + ", taxe=" + taxe  + '}';
    }
}
