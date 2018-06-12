package com.example.poo.progra2.logica;

import java.util.ArrayList;

public class Calendario {
    public ArrayList<Entregable> entregables = new ArrayList<Entregable>();

    public void agregarEntregable(Entregable entregable){
        entregables.add(entregable);
    }
}
