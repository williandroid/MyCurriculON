package br.auadeottoni.mycurriculon.modelo;

import java.util.Date;

public class InfoExtra {

    private int idInfoExtra;
    private Curriculo curriculo;
    private String descricao;
    private Date data;

    public int getIdInfoExtra() {
        return idInfoExtra;
    }

    public void setIdInfoExtra(int idInfoExtra) {
        this.idInfoExtra = idInfoExtra;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
} // fim da classe InfoExtra
