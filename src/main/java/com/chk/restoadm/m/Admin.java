/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author archey
 */
@Entity
@DiscriminatorValue("a")
public class Admin extends Util{

    public Admin() {
    }
    
    public Admin(String lName, String fName, String login, String passwd, String email, String address, String city, String zipcode, String tel) {
        super(lName, fName, login, passwd, email, address, city, zipcode, tel);
    }
    
    
    
}
