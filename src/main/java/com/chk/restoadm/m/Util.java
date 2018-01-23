/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author archey
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_user")
public class Util extends Model{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String lName;
    private String fName;
    private String login;
    private String passwd;
    private String email;
    private String address;
    private String city;
    private String zipcode;
    private String tel;
//    private String type_user;

    public Util() {
    }

    public Util(String lName, String fName, String login, String passwd, String email, String address, String city, String zipcode, String tel) {
        this.lName = lName;
        this.fName = fName;
        this.login = login;
        this.passwd = passwd;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.tel = tel;
    }
    
    // GETTERS & SETTERS
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

//    public String getType_user() {
//        return type_user;
//    }
//
//    public void setType_user(String type_user) {
//        this.type_user = type_user;
//    }
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", passwd=" + passwd + ", email=" + email + ", tel=" + tel + '}';
    }
    
    
}
