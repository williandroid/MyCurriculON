package br.auadeottoni.mycurriculon.modelo;

import java.util.Date;

public class ExperienciaProfissional {

    private int idExpProfissonal;
    private Curriculo curriculo;
    private Date anoInicio;
    private Date anoFinal;
    private String cargo;
    private String princAtividades;
    private String empresa;

    public int getIdExpProfissonal() {
        return idExpProfissonal;
    }

    public void setIdExpProfissonal(int idExpProfissonal) {
        this.idExpProfissonal = idExpProfissonal;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public Date getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(Date anoInicio) {
        this.anoInicio = anoInicio;
    }

    public Date getAnoFinal() {
        return anoFinal;
    }

    public void setAnoFinal(Date anoFinal) {
        this.anoFinal = anoFinal;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPrincAtividades() {
        return princAtividades;
    }

    public void setPrincAtividades(String princAtividades) {
        this.princAtividades = princAtividades;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
} // fim da classe ExperienciaProfissional
