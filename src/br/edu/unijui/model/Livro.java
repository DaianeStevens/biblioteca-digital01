package br.edu.unijui.model;

import java.util.Date;

/**
 *
 * @author daias
 */
public class Livro {
    private int Id;
    private String Titulo;
    private String Autor; 
    private int QtdExemplar;
    private Date DtInclusao;
    private Date DtBaixa;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    
    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }


    public int getQtdExemplar() {
        return QtdExemplar;
    }

    public void setQtdExemplar(int QtdExemplar) {
        this.QtdExemplar = QtdExemplar;
    }

    public Date getDtInclusao() {
        return DtInclusao;
    }

    public void setDtInclusao(Date DtInclusao) {
        this.DtInclusao = DtInclusao;
    }

    public Date getDtBaixa() {
        return DtBaixa;
    }

    public void setDtBaixa(Date DtBaixa) {
        this.DtBaixa = DtBaixa;
    }
    
    
}
