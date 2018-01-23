/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author archey
 */
@Entity
@DiscriminatorValue("c")
public class Customer extends Util{
    
//    @OneToMany
//    private Collection<Command> commands;
    
    public Customer() {
    }

    public Customer(String lName, String fName, String login, String passwd, String email, String address, String city, String zipcode, String tel) {
        super(lName, fName, login, passwd, email, address, city, zipcode, tel);
//        this.commands = commands;
    }
    
    // GETTERS & SETTERS

//    public Collection<Command> getCommands() {
//        return commands;
//    }
//
//    public void setCommands(Collection<Command> commands) {
//        this.commands = commands;
//    }
    
    
}
