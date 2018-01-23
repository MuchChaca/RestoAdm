/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author sbastide
 */
public class DefaultListModelCustom<T extends Object>  extends AbstractListModel {

    private ArrayList<T> listObj;

    public DefaultListModelCustom(ArrayList<T> listObj) {
        this.listObj = listObj;
    }
    
    @Override
    public int getSize() {
        return this.getListObj().size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.getListObj().get(i);
    }
    
    
    // GETTERS & SETTERS

    public ArrayList<T> getListObj() {
        return listObj;
    }

    public void setListObj(ArrayList<T> listObj) {
        this.listObj = listObj;
    }
    
    public void add(T obj){
        this.listObj.add(obj);
    }
    
}
