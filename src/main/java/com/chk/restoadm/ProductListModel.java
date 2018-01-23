/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm;

import com.chk.restoadm.m.Product;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author sbastide
 */
public class ProductListModel extends AbstractListModel{
    private ArrayList<Product> listProd;

    public ProductListModel(ArrayList<Product> listProd) {
        this.listProd = listProd;
    }
    
    @Override
    public int getSize() {
        return this.getListProd().size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.getListProd().get(i);
    }

    public ArrayList<Product> getListProd() {
        return listProd;
    }

    public void setListProd(ArrayList<Product> listProd) {
        this.listProd = listProd;
    }
    
    
    
}
