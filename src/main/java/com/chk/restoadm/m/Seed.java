/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import java.util.ArrayList;

/**
 *
 * @author sbastide
 */
public abstract class Seed {

    public Seed() {
    }
    
    
    public static void admins(){
        Admin adm1 = new Admin();
    }
    
//    public static void products(){
//        boolean success;
//        Product prod1 = new Product("Rougail saucisses", "saucisses piment tomates", "cari", "m");
//        Image img1 = new Image("img/img1.jpg", "full", prod1);
//        Image img2 = new Image("img/img2.jpg", "thumb", prod1);
//        ArrayList<Image> listImg1 = new ArrayList<>();
//        listImg1.add(img1);
//        listImg1.add(img2);
//        prod1.setImages(listImg1);
//        //success = DB.createOne(prod1);
//        //success = DB.createOne(img1);
//        //success = DB.createOne(img2);
//        
//        Product prod2 = new Product("Massalé cabri", "cabri massalé", "cari", "m");
//        Image img3 = new Image("img/img3.jpg", "full", prod2);
//        Image img4 = new Image("img/img4.jpg", "thumb", prod2);
//        ArrayList<Image> listImg2 = new ArrayList<>();
//        listImg2.add(img3);
//        listImg2.add(img4);
//        prod2.setImages(listImg2);
//        success = DB.createOne(prod2);
//        success = DB.createOne(img3);
//        success = DB.createOne(img4);
//    }
}
