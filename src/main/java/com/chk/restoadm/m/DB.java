/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chk.restoadm.m;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 * Gives access to database interaction through static methods
 * @author archey
 * @param <T>
 */
public abstract class DB {
    // BackgroundColor + TextColor
    // Some don't have background cause they are bright
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[47m" + "\u001B[30m";
    public static final String ANSI_RED = "\u001B[47m" + "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[47m" + "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[47m" + "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[46m" + "\u001B[37m";
    
    public static final int IMG_WIDTH = 250;
    public static final int IMG_HEIGHT = 170;
    
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>Blue</b>
     * @param s The string to print
     */
    public static final void printBlue(String s){
        System.out.println(DB.ANSI_BLUE + s + DB.ANSI_RESET);
    }
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>Cyan</b>
     * @param s The string to print
     */
    public static final void printCyan(String s){
        System.out.println(DB.ANSI_CYAN + s + DB.ANSI_RESET);
    }
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>Black</b>
     * @param s The string to print
     */
    public static final void printBlack(String s){
        System.out.println(DB.ANSI_BLACK + s + DB.ANSI_RESET);
    }
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>Cyan</b>
     * @param s The string to print
     */
    public static final void printRed(String s){
        System.out.println(DB.ANSI_RED + s + DB.ANSI_RESET);
    }
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>Green</b>
     * @param s The string to print
     */
    public static final void printGreen(String s){
        System.out.println(DB.ANSI_GREEN + s + DB.ANSI_RESET);
    }
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>Yellow</b>
     * @param s The string to print
     */
    public static final void printYel(String s){
        System.out.println(DB.ANSI_YELLOW + s + DB.ANSI_RESET);
    }
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>Purple</b>
     * @param s The string to print
     */
    public static final void printPur(String s){
        System.out.println(DB.ANSI_PURPLE + s + DB.ANSI_RESET);
    }
    /**
     * <h2>Use with a dark theme like Dracula</h2>
     * Uses <i>System.out.println</i> to print in <b>White</b>
     * @param s The string to print
     */
    public static final void printWhite(String s){
        System.out.println(DB.ANSI_WHITE + s + DB.ANSI_RESET);
    }
    
    
    // ================= - Operations - ================= //
    
    /**
     * <h2>Retrieves all entries of the table associated to the Class provided</h2>
     * <b><i>Example:</i></b>
     * <pre><em>ArrayList<Util> obj = DB.findAll(Util.class);</em></pre>
     * @param <T extends Model> The type
     * @param c The name of the class must be the one tagged with @Entity
     * @return ArrayList<Object is T>
     */
    public static<T extends Model> ArrayList<T> findAll(Class c){
//        System.out.println(table.toString());
        ArrayList<T> listAll = new ArrayList<T>();
        String table = c.getSimpleName();
        
        EntityManager em = DB.openInstance();
        
        String query = String.format("SELECT o FROM %s as o ", table);
        
        TypedQuery<T> q = em.createQuery(query, c);
        
        listAll.addAll(q.getResultList());
        
        DB.closeInstance(em);
        return listAll;
    }
    
    /**
     * <h2>Retrieves all entries of the table associated to the name of the Class which is annotated w/ @Entity provided</h2>
     * <b><u>The name of the class must start with a Capital letter</u></b>
     * <b><i>Example:</i></b>
     * <pre><em>ArrayList<Util> obj = DB.findAll("Util");</em></pre>
     * @bug doesn't find Class
     * @param <T extends Model>
     * @param className <b><u>The name of the class must start with a Capital letter</u></b>
     * @return ArrayList<Object is T>
     */
    public static<T extends Model> ArrayList<T> findAll(String className){
        ArrayList<T> listAll = new ArrayList<T>();
        EntityManager em = DB.openInstance();
        
        try {
            Class c = Class.forName(className);
            System.out.println(DB.ANSI_GREEN + c.getSimpleName());
            String query = String.format("SELECT o FROM %s as p ", className);
            
            TypedQuery<T> q = em.createQuery(query, c);
            
            listAll.addAll(q.getResultList());
            
        } catch (ClassNotFoundException ex) {
            System.out.println(DB.ANSI_RED + "[--] Damn the poney got lost >.< \n" + DB.ANSI_RESET);
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
            DB.closeInstance(em);
            return listAll;
    }
    
    public static<T extends Model> ArrayList<T> paramFindAll(Class c, String labelParam, long id){
        ArrayList<T> listAll = new ArrayList<T>();
        
        EntityManager em = DB.openInstance();
        
        try{
            String query = String.format("SELECT o FROM %s as o WHERE %s = :%s", c.getSimpleName(), labelParam, labelParam);
            TypedQuery<T> q = em.createQuery(query, c);
            q.setParameter(labelParam, id);
            listAll.addAll(q.getResultList());
            
        }catch(Exception ex){
            System.out.println(DB.ANSI_RED + "[--] Damn the poney got lost >.< \n" + DB.ANSI_RESET);
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DB.closeInstance(em);
        
        return listAll;
    }
    
    /**
     * <h2>Find an entry in the database from the Class and the id</h2>
     * <b><i>Example:</i></b>
     * <pre><em>Util obj = DB.findOne(Util.class, 0L);
     * // In case of inheritence
     * if(obj.getType_user() == 'a'){
     *    Admin adminObj = (Admin)obj;
     * }</em></pre>
     * @param <T extends Model> The type of the Object
     * @param c Class tagged with @Entity
     * @param id The id of the row to fetch
     * @return <Object is T>
     */
    public static<T extends Model> T findOne(Class c, long id){
        
        EntityManager em = DB.openInstance();
        
        String query = String.format("SELECT o FROM %s as o WHERE o = :id", c.getSimpleName());
        TypedQuery<T> q = em.createQuery(query, c);
        q.setParameter("id", id);
        T obj = q.getSingleResult();
        
        DB.closeInstance(em);
        
        return obj;
    }
    
    public static<T extends Util> Object testLogin(Class c, String login, String pass){
        
        EntityManager em = DB.openInstance();
        
        String type = toLowerCase(c.getSimpleName().substring(0, 1));
        try{
            String query = String.format("SELECT o FROM %s as o WHERE login = :login AND passwd = :pass AND type_user = :type", "Util");
            Query q = em.createQuery(query);
            q.setParameter("login", login);
            q.setParameter("pass", pass);
            q.setParameter("type", type);
            
            List<Object> liObj = q.getResultList();
            Object obj = null;
            if(liObj.size() > 0){
                obj = liObj.get(0);
            }
            
            DB.closeInstance(em);
            return obj;
        } catch(NoResultException ex){
            printRed("NOT FOUND");
            DB.closeInstance(em);
            return null;
        }
       
    }
    
    public static<T extends Util> boolean userValid(T util){
        
        EntityManager em = DB.openInstance();
        boolean success = false;
        
        try{
            String query = "SELECT o FOM %s as o WHERE login = :login";
            Query q = em.createQuery(query);
            q.setParameter("login", util.getLogin());
            Object obj = q.getFirstResult();
        }catch(NoResultException ex){
            printGreen("LOGIN IS VALID");
            success = true;
        }
        
        DB.closeInstance(em);
        return success;
    }
    
    /**
     * <h2>Find an entry in the database from the Object's id</h2>
     * <b><i>Example:</i></b>
     * <pre><em>Util obj = new Util();
     * obj.setId(3L);
     * obj = DB.findOne(obj);
     * // In case of inheritence
     * if(obj.getType_user() == 'a'){
     *    Admin adminObj = (Admin)obj;
     * }</em></pre>
     * @param <T extends Model>
     * @param obj
     * @return <Object is T> 
     */
    public static<T extends Model> T findOne(T obj){
        
        EntityManager em = DB.openInstance();
        
        Class c = obj.getClass();
        String query = String.format("SELECT o FROM %s as o WHERE o = :id", c.getSimpleName());
        TypedQuery<T> q = em.createQuery(query, c);
        q.setParameter("id", obj.getId());
        T object = q.getSingleResult();
        
        DB.closeInstance(em);
        
        return object;
    }
    
    /**
     * <h2>Delete an entry in the database associated to the object provided</h2>
     * <b><i>Example:</i></b>
     * <pre><em>Admin objectAdmin = new Admin(); // assume all the attributes are initialized
     * boolean success = DB.delOne(objectAdmin);</em></pre>
     * @param <T extends Model>
     * @param obj The object representing the entry to delete
     * @return boolean - true if success, false if failure
     */
    public static<T extends Model> boolean delOne(T obj){
        
        EntityManager em = DB.openTransaction();
        boolean success = false;
        try{
            em.remove(obj);
            T check = DB.findOne(obj);
            if (  check == null ){
                success = true;
            }
        } catch (Exception ex){
            System.out.println(DB.ANSI_RED + "[--] Omg magic trick didn't work !!\n" + ex.toString() + DB.ANSI_RESET);
        }
            DB.closeTransaction(em);
            System.out.println(DB.ANSI_CYAN + "[++] Brave yourslef! Deletion succeed!" + DB.ANSI_RESET);
            return success;
        
    }
    
    /**
     * <h2>Delete an entry in the database associated to the Class / id provided</h2>
     * <b><i>Example:</i></b>
     * <pre><em>Admin objectAdmin = new Admin(); // assume all the attributes are initialized
     * boolean success = DB.delOne(Admin.class, objectAdmin.getId());</em></pre>
     * @param <T extends Model>
     * @param c A class annotated with @Entity
     * @param id The id of the entry/object to delete from the database
     * @return boolean - true if success, false if failure
     */
    public static<T extends Model> boolean delOne(Class c, long id){
        
        EntityManager em = DB.openTransaction();
        boolean success = false;
        
        try{
            T toDel = DB.findOne(c, id);
            success = DB.delOne(toDel);
        } catch(Exception ex) {
            System.out.println(DB.ANSI_RED + "[--] Error while deleting\n" + ex.toString() + DB.ANSI_RESET);
        }
            DB.closeTransaction(em);
            System.out.println(DB.ANSI_CYAN + "[++] Brave yourslef! Deletion succeed!" + DB.ANSI_RESET);
            return success;
        
    }
    
    /**
     * <h2>Update an entry in the database associated to the Object provided</h2>
     * <b><i>Example:</i></b>
     * <pre><em>Admin objectAdmin = new Admin(); // assume all the attributes are initialized
     * objectAdmin.setEmail("new@email.com");
     * boolean success = DB.updateOne(objectAdmin);</em></pre>
     * @param <T extends Model>
     * @param obj
     * @return 
     */
    public static<T extends Model> boolean updateOne(T obj){
        boolean success = false;
        String info = DB.ANSI_RED + "[--] Summer time sadness... Nothing changed!" + DB.ANSI_RESET;
        
        EntityManager em = DB.openTransaction();
        
        T bfUpdate = DB.findOne(obj.getClass(), obj.getId());
        T afUpdate = em.merge(obj);
        
        if(!bfUpdate.equals(afUpdate)){
            success = true;
            info = DB.ANSI_CYAN + "[++] Summer time sadness... Nothing changed!" + DB.ANSI_RESET;
        }
        
        System.out.println(info);
        DB.closeTransaction(em);
        
        return success;
    }
    
    public static<T extends Model> boolean createOne(T obj) {
        boolean success = false;
        String info = "[--] Forever alone !? Nothing new then!";
        try{
            EntityManager em = DB.openTransaction();

            em.persist(obj);
    //        em.getTransaction().setRollbackOnly();

            DB.closeTransaction(em);
            /*
            if (DB.findOne(obj) != null){*/
                success = true;
                info = "[++] Much Wow! Much New Stuff!";
            /*}*/
            DB.printYel(info);
        } catch (Exception ex) {
            DB.printRed("Then the error was created...");
            System.out.println(ex);
        }
        DB.printYel(info);
        return success;
    }
    
    public static<T extends Model> boolean createMany(Collection<T> collT){
        boolean success = false;
        
        EntityManager em = DB.openTransaction();
        String info = "[--] Forever alone !? Nothing new then!";
        
        try{
            for(T obj: collT){
                em.persist(obj);
            }
            success = true;
            info = "[++] Much Wow! Much New Stuff!";
            DB.closeTransaction(em);
        }catch(Exception ex){
            DB.printRed("Then the error was created...");
            System.out.println(ex);
        }
        
        DB.printYel(info);
        
        return success;
    }
    
    
    
    
    
    // ================= - EntityManager - ================= //
    
    /**
     * <h2>WHEN <u>WRITING</u> TO THE DATABASE</h2>
     * <ul><li>Make an instance of the EntityManager</li>
     * <li>Open a transaction </li></ul>
     * @return EntityManager
     */
   private static EntityManager openTransaction(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        return em;
    }
   
   /**
    * <h2>WHEN <u>READING</u> TO THE DATABASE</h2>
    * Make an instance of the EntityManager
    * @return EntityManager
    */
    private static EntityManager openInstance(){
        return PersistenceManager.INSTANCE.getEntityManager();
    }
    
    /**
     * <h2>Close an <u>Instance</u></h2>
     * <ul><li>Close the EntityManager</li>
     * <li>Close the PersistenceManager</li>
     * @param em The EntityManager to close
     */
    private static void closeInstance(EntityManager em){
//        em.close();
//        PersistenceManager.INSTANCE.close();
    }
    
    /**
     * <h2>Close a <u>Transaction</u></h2>
     * <ul><li><strong>Commit first</strong></li>
     * <li>Then close the EntityManager</li>
     * <li>Close the PersistenceManager</li></ul>
     * <em>So no the commit is included to this function</em>
     * @param em The EntityManager to close
     */
    private static void closeTransaction(EntityManager em){
        em.getTransaction().commit();
//        em.close();
//        PersistenceManager.INSTANCE.close();
    }
    
    
}
