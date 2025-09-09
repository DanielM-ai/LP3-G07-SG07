package com.hotel.interfaces;

import com.hotel.model.Reserva;

public interface PoliticaCancelacion {
    boolean puedeCancelar(Reserva reserva);
    double calcularPenalizacion(Reserva reserva);
}