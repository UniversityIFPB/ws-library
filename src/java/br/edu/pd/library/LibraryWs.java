/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pd.library;

import br.edu.pd.library.dao.DAO;
import br.edu.pd.library.dao.Sqlitedb;
import br.edu.pd.model.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import org.hibernate.Transaction;

/**
 *
 * @author EDER
 */
@WebService(serviceName = "LibraryWs")
public class LibraryWs {

    private DAO dao;
    

    public LibraryWs() {
        this.dao = new DAO();
    }
    
    
    /**
     * metodo responsavel por cadastrar um novo livro no sistema
     * @param titulo
     **/
    //@WebMethod(operationName = "cadastro")
    public Livro cadastro(@WebParam(name = "titulo")String titulo, @WebParam(name = "editora")String editora, @WebParam(name = "isbn")double ISBN, @WebParam(name = "edicao")int edicao, @WebParam(name = "autor")String autor){
        Livro l = new Livro(titulo, editora, ISBN, edicao, autor);
       
        //adicionando no banco de dados
        return this.dao.save(l);
    }
    
        
    /**
     * esse metodo é responsavel por consultar o livro por isbn
     **/
    @WebMethod(operationName = "consultar")
    public Livro consultar(@WebParam(name = "isbn")double isbn){
        return dao.select(isbn);
    }
    
    /**
     * metodo responsavel po altera as informações e jogar no banco de dados
     **/
    @WebMethod(operationName = "alterar")
    public void alterar(@WebParam(name = "titulo")String titulo, @WebParam(name = "editora")String editora, @WebParam(name = "isbn")double ISBN, @WebParam(name = "edicao")int edicao, @WebParam(name = "autor")String autor){
        Livro lNew = new Livro();     
        
        lNew.setAutor(autor);
        lNew.setEdicao(edicao);
        lNew.setEditora(editora);
        lNew.setISBN(ISBN);
        lNew.setTitulo(titulo);
        
        this.dao.update(ISBN, lNew);
    }
    
    /**
     * metodo responsavel por remover o livro e apresentar as informações do livroo que foi removido
     **/
    @WebMethod(operationName = "excluir")
    public String excluir(@WebParam(name = "isbn")double isbn){
        Livro l = this.consultar(isbn);
        
        if(this.dao.remove(l) == null)
            return null;
        
        return "Livro deletado com sucesso!!<br><br>"+
        "Titulo: "+l.getTitulo()+
        "<br>Author: " + l.getAutor()+
        "<br>Editora: "+ l.getEditora()+
        "<br>ISBN: " + l.getISBN()+
        "<br>Edição: "+ l.getEdicao();
    }
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
}
