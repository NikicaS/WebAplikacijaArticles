/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import java.util.ArrayList;
import database.DbBroker;

/**
 *
 * @author nikica1
 */
public class User {
   
    
    public int userId;
    public String nick;
    public String pass;
    
    public User(int userId, String nick, String pass){
        this.userId = userId;
        this.nick = nick;
        this.pass = pass;
    }
    
    public User(String nick, String pass){
        
        this.nick = nick;
        this.pass = pass;
    }
    
    public void insert(){
        
        DbBroker db = new DbBroker();
        db.obicnaKomanda("insert into `users`(`nick`,`pass`) values('"+this.nick+"','"+this.pass+"')" );
             
    }
    
    public static User getById(Integer id){
    
        DbBroker db = new DbBroker();
        ArrayList<String[]> qRes = db.getArr("select * from `users` where `user_id`='"+id.toString()+"'");
        return new User(Integer.parseInt(qRes.get(0)[0]), qRes.get(0)[1], qRes.get(0)[2]);
    
    }
  
    
    public static ArrayList<User> getAll(){
    
        DbBroker db = new DbBroker();
        ArrayList<String[]> qRes = db.getArr("select * from `users`");
        ArrayList<User> res = new ArrayList<User>();
        for(String[] s : qRes) 
            res.add(new User(Integer.parseInt(s[0]), s[1], s[2]));
        return res;
    } 
    public static int checkUser(String nick, String pass){
    
        nick = nick.trim().replace("'", "");
        pass = pass.trim().replace("'", "");
        
        DbBroker db = new DbBroker();
        
        int res = db.scalar("select user_id from `users` where `nick`='"+nick+"' and `pass`='"+pass+"'");
        if(res<1)
            return -1;
        else
            return res;
     }


    

    


}
