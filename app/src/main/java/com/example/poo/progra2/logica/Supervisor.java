package com.example.poo.progra2.logica;

public class Supervisor {

    private String Nombre;
    private String puesto;
    private String telefono;
    private String email;

    public Supervisor(String pNombre, String pPuesto, String pTel, String pEmail){
        setNombre(pNombre);
        setPuesto(pPuesto);
        setTelefono(pTel);
        setEmail(pEmail);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
