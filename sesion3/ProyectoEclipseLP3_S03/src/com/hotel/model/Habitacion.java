
package com.hotel.model;

public class Habitacion {
    private int numero;
    private String tipo;
    private double precioBase;

    public Habitacion(int numero, String tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }
}