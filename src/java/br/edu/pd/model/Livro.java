/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pd.model;

import br.edu.pd.library.dao.DAO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author EDER
 */
@Entity
public class Livro implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titulo;
    private String editora;
    private double ISBN;
    private int edicao;
    private String autor;

    public Livro(){}
    
    public Livro(String titulo, String editora, double ISBN, int edicao, String autor) {
        this.titulo = titulo;
        this.editora = editora;
        this.ISBN = ISBN;
        this.edicao = edicao;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public double getISBN() {
        return ISBN;
    }

    public void setISBN(double ISBN) {
        this.ISBN = ISBN;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    /*public static void main(String[] args){
         Livro l = new Livro("livro novo", "abril", 1, 2, "eder");
        
        
        //presiste aqui
        DAO.instace().getTransaction().begin();
        DAO.instace().persist(l);
        DAO.instace().getTransaction().commit();
    }*/
}
