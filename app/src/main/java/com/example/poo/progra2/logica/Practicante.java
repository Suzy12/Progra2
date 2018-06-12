package com.example.poo.progra2.logica;

import java.util.ArrayList;

public class Practicante {

    private String nombre;
    private String carnet;
    private String contrasena;
    private String fechaDeNacimiento;
    private String direccion;
    private String correoProfCurso;
    private String correoProfAsesor;
    private String empresa;
    private ArrayList<Minuta> minutas = new ArrayList<Minuta>();
    private ArrayList<Entregable> entregables = new ArrayList<Entregable>();

    public Practicante(){

    }

    public Practicante(String pNombre, String pCarnet, String pContrasena, String pFecha, String pDir, String pCorreoProfAsesor, String pCorreoProfCurso, String pEmpresa) {
        setNombre(pNombre);
        setCarnet(pCarnet);
        setContrasena(pContrasena);
        setFechaDeNacimiento(pFecha);
        setDireccion(pDir);
        setCorreoProfAsesor(pCorreoProfAsesor);
        setCorreoProfCurso(pCorreoProfCurso);
        setEmpresa(pEmpresa);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoProfCurso() {
        return correoProfCurso;
    }

    public void setCorreoProfCurso(String correoProfCurso) {
        this.correoProfCurso = correoProfCurso;
    }

    public String getCorreoProfAsesor() {
        return correoProfAsesor;
    }

    public void setCorreoProfAsesor(String correoProfAsesor) {
        this.correoProfAsesor = correoProfAsesor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public ArrayList<Minuta> getMinutas() {
        return minutas;
    }


    public ArrayList<Entregable> getEntregables() {
        return entregables;
    }

}
