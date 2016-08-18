/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pd.library.dao;

import br.edu.pd.model.Livro;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.primefaces.application.resource.barcode.Int2of5Generator;

/**
 *
 * @author EDER
 */
public class DAO {
    private EntityManager em = null;
    
    public EntityManager instace(){
        if(this.em == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
            this.em = factory.createEntityManager();
        }
            
        return em;
    }
    
    public List getAll(){
        return (List)this.instace().createQuery("select e from livro e").getResultList();
    }
    
    //pegando do banco
    public Livro select(double isbn){
        try{
            return (Livro)this.instace().createQuery("select e from Livro e where e.ISBN="+isbn).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
    public void update(double ISBN, Livro l){
        Livro lOld = this.select(ISBN);
        this.instace().getTransaction().begin();
        this.em.remove(lOld);
         this.em.merge(l);
        this.instace().getTransaction().commit();
    }
    
    public Livro remove(Livro l){
        try{
            this.instace().getTransaction().begin();
            this.em.remove(l);            
            this.instace().getTransaction().commit();
            return l;
            
        }catch(Exception e){
            return null;
        }       
    }
    //salvando no banco
    public Livro save(Livro l){        
        this.instace().getTransaction().begin();
        this.instace().persist(l); 
        this.instace().getTransaction().commit();
        return l;
    }
}
