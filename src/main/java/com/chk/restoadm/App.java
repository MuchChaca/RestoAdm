/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm;

import com.chk.restoadm.m.Util;

/**
 *
 * @author archey
 */
public class App {
    // Type of user (admin / customer)
    public static Util CUR_USER;
    public static String CUR_TYPE = "c";
    public static final String ADMIN = "a";
    public static final String CUSTO = "c";
    
    public static void main(String args[]) {
        connection connectF = new connection();
        connectF.setVisible(true);
//        mainFrame mFrame = new mainFrame();
//        mFrame.setVisible(true);
    }
}
