package com.example.poo.progra2.logica;

public class Periodo {
    private String semestre;
    private String ano;
    private Calendario calendario = null;

    public Periodo(){

    }

    public Periodo(String pSemestre,String pAno){
        setSemestre(pSemestre);
        setAno(pAno);
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
}
