/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sbastide
 */
public class DefaultTableModelCustom<T>  extends AbstractTableModel{
    private ArrayList<T> listObj;

    public DefaultTableModelCustom(ArrayList<T> listObj) {
        this.listObj = listObj;
    }
    
    
    
    @Override
    public int getRowCount() {
        return this.getListObj().size();
    }

    @Override
    public int getColumnCount() {
        Object objetListe = this.getListObj().get(0);
        Field[] champs = objetListe.getClass().getDeclaredFields();
        
        return champs.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object objet = this.getListObj().get(rowIndex);
        Method[] methodes = objet.getClass().getDeclaredMethods();
        Method[] tableauMethodesGet = new Method[methodes.length];
        
        int j = 0;
        for (int i=0; i < methodes.length; i++)
        {
            if (methodes[i].getName().contains("get"))
            {
                tableauMethodesGet[j] = methodes[i];
                j++;
            }
        }
        
        Method methode = tableauMethodesGet[columnIndex];
        
        try
        {
            return methode.invoke(objet, null);
        }
        
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException ex)
        {
            return 0;
        }   
    }
    
    /*@Override
    public String getColumnName(int column)
    {
        Object objetListe = this.getListObj().get(0);
        Field[] champs = objetListe.getClass().getDeclaredFields();
        Field colonne = champs[column];
        
        String[] nomColonneSplit = colonne.getName().split("\\.");
        int longueurColonneSplit = nomColonneSplit.length - 1;
        
        return nomColonneSplit[longueurColonneSplit];
    }*/
    
    // GETTERS & SETTERS

    public ArrayList<T> getListObj() {
        return listObj;
    }

    public void setListObj(ArrayList<T> listObj) {
        this.listObj = listObj;
    }
    
}
