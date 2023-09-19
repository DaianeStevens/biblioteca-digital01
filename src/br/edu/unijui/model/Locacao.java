package br.edu.unijui.model;

import java.util.Date;

/**
 *
 * @author daias
 */
public class Locacao {
    private int Id;
    private int IdUsuario;
    private Date DtPrazoDevolucao;
    private Date DtLocacao;
    private Date DtDevolucao;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public Date getDtPrazoDevolucao() {
        return DtPrazoDevolucao;
    }

    public void setDtPrazoDevolucao(Date DtPrazoDevolucao) {
        this.DtPrazoDevolucao = DtPrazoDevolucao;
    }

    public Date getDtLocacao() {
        return DtLocacao;
    }

    public void setDtLocacao(Date DtLocacao) {
        this.DtLocacao = DtLocacao;
    }

    public Date getDtDevolucao() {
        return DtDevolucao;
    }

    public void setDtDevolucao(Date DtDevolucao) {
        this.DtDevolucao = DtDevolucao;
    }

    
    
}
