/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

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
public class Image extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String imgPath;
    private String description;
    /*@ManyToOne
    private Product product;*/
    
    public Image() {
    }

    /*public Image(String imgPath, String description, Product product) {
        this.imgPath = imgPath;
        this.description = description;
       // this.product = product;
    }*/
    
    public Image(String imgPath, String description) {
        this.imgPath = imgPath;
        this.description = description;
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

    public String getPath() {
        return imgPath;
    }

    public void setPath(String path) {
        this.imgPath = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
/*
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
*/
    @Override
    public String toString() {
        return description + " " + imgPath;
    }
    
    
}
