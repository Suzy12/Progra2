package com.example.poo.progra2.logica;

public class Profesor {
    private String nombre;
    private String email;
    private String telefono;
    private String contrasena;

    public Profesor(){

    }

    public Profesor(String pNombre,String pEmail,String pTel,String pContra){
        setNombre(pNombre);
        setEmail(pEmail);
        setTelefono(pTel);
        setContrasena(pContra);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
