/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *
 * @author archey
 */
@Entity
public class Product extends Model{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String type;
    private String taille;
    @OneToMany(cascade=CascadeType.PERSIST)
    private Collection<Tarif> prices;
    @OneToMany(cascade=CascadeType.PERSIST)
    private Collection<Image> images;
//    @ManyToMany
//    private Collection<Command> orders;

    public Product() {
    }
/*
    public Product(String name, String description, String type, String taille, ArrayList<Tarif> tarif) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.taille = taille;
        this.orders = null;
        this.images = null;
        this.prices = tarif;
    }
*/
    public Product(String name, String description, String type, String taille, Collection<Tarif> prices, Collection<Image> images) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.taille = taille;
        this.prices = prices;
        this.images = images;
    }
    public Product(String name, String description, String type, String taille) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.taille = taille;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public Collection<Tarif> getPrices() {
        return prices;
    }

    public void setPrices(Collection<Tarif> prices) {
        this.prices = prices;
    }

    public Collection<Image> getImages() {
        return images;
    }

    public void setImages(Collection<Image> images) {
        this.images = images;
    }

//    public Collection<Command> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Collection<Command> orders) {
//        this.orders = orders;
//    }
    
    public Tarif getPrice(){
        Date d = new Date();
        Tarif thePrice = new Tarif();
        for (Tarif Price : this.getPrices()) {
            if (Price.getDateValid().after(d)){
                d = Price.getDateValid();
                thePrice = Price;
            }
        }
        return thePrice;
    }
    
    public String getHtString(){
        return String.format("%1$,.2f€", this.getPrice().getHt());
    }
    
    public String getTvaString(){
        return String.format("%1$,.2f€", this.getPrice().getTaxe());
    }

    public double getTTC(){
        return this.getPrice().getTTC();
    }
    
    public String getTTCString(){
        return String.format("%1$,.2f€", this.getTTC());
    }
    
    @Override
    public String toString() {
        return name + ";  " + type + ";  " + taille + " = " + this.getTTCString();
    }
}
