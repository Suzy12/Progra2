package com.example.poo.progra2.logica;

public class Entregable {
    private String titulo;
    private String descripcion;
    private String fechaDeEntrega;
    private boolean isElectronico;
    private int nota;
    private String fechaNota;
    private String comentarios;

    public Entregable(){}

    public Entregable(String pTitulo,String pDescripcion,String pFechaDeEntrega,boolean pElectronico){
        setTitulo(pTitulo);
        setTitulo(pTitulo);
        setDescripcion(pDescripcion);
        setFechaDeEntrega(pFechaDeEntrega);
        setElectronico(pElectronico);
    }

    public Entregable(String pTitulo, String pDescripcion, String pFechaDeEntrega, boolean pElectronico, int pNota, String pFechaNota, String pComentarios){
        setTitulo(pTitulo);
        setDescripcion(pDescripcion);
        setFechaDeEntrega(pFechaDeEntrega);
        setElectronico(pElectronico);
        setNota(pNota);
        setFechaNota(pFechaNota);
        setComentarios(pComentarios);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(String fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public boolean isElectronico() {
        return isElectronico;
    }

    public void setElectronico(boolean electronico) {
        isElectronico = electronico;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getFechaNota() {
        return fechaNota;
    }

    public void setFechaNota(String fechaNota) {
        this.fechaNota = fechaNota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
