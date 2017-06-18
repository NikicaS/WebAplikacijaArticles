/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

/**
 *
 * @author nikica1
 */
import database.DbBroker;
import java.util.ArrayList;

public class Article {
    
    public int id;
    public String title, image, text;
    public String rDate;
    public int author;
    public boolean active;
    
    public Article(int id, String title, String image, String text, String rDate, int author){
        
        this.id = id;
        this.title = title;
        this.image = image;
        this.text = text;
        this.rDate = rDate;
        this.author = author;
    }
    
    public Article(String title, String image, String text, String rDate, int author){
       
        this.title = title;
        this.image = image;
        this.text = text;
        this.rDate = rDate;
        this.author = author;
 
    }
    
    public void insert(){
        
        DbBroker db = new DbBroker();
        db.obicnaKomanda("insert into `articles`(`title`,`image`,`text`,`rDate`,`author`) values('"+this.title+
                "','" +this.image+"','"+this.text+"','"+this.rDate+"','"+this.author+"')");
        
    }
    
    public static Article getById(Integer id){
        
        DbBroker db = new DbBroker();
        
        ArrayList<String[]> qRes = db.getArr("select * from `articles` where `art_id`='"+id.toString()+"'");
        
        return new Article(Integer.parseInt(qRes.get(0)[0]),
                            qRes.get(0)[1],qRes.get(0)[2],
                            qRes.get(0)[3], qRes.get(0)[4],
                            Integer.parseInt(qRes.get(0)[5]));
        
    }
    
    public static ArrayList<Article> getAll(){
        
        DbBroker db = new DbBroker();
        
        ArrayList<String[]> qRes = db.getArr("select * from `articles`");
        
        ArrayList<Article> res = new ArrayList<Article>();
        
        for(String[]s : qRes)
            res.add(new Article(Integer.parseInt(s[0]),
                                s[1], s[2], s[3], s[4],
                                Integer.parseInt(s[5]));
            return res;
        
                
    }
}
