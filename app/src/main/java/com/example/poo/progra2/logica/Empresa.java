package com.example.poo.progra2.logica;

public class Empresa {

    private String nombre;
    private String direccion;
    private String telefono;
    private String supervisor;

    public Empresa(){

    }

    public Empresa(String pNombre, String pDir, String pTel, String pSup){
        setNombre(pNombre);
        setDireccion(pDir);
        setTelefono(pTel);
        setSupervisor(pSup);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getCorreoSupervisor(){
        String[] output = supervisor.split(";");
        return output[0];
    }

    public String getNombreSupervisor(){
        String[] output = supervisor.split(";");
        return output[1];
    }
}
