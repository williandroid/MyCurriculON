package br.auadeottoni.mycurriculon.modelo;

import java.util.Date;

public class Qualificacoes {

    private int idQualificacoes;
    private int idCurriculo;
    private Date data;
    private String descricao;

    public int getIdQualificacoes() {
        return idQualificacoes;
    }

    public void setIdQualificacoes(int idQualificacoes) {
        this.idQualificacoes = idQualificacoes;
    }

    public int getIdCurriculo() {
        return idCurriculo;
    }

    public void setIdCurriculo(int idCurriculo) {
        this.idCurriculo = idCurriculo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
} // fim da classe Qualificacoes
