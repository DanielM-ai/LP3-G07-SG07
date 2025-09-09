package com.hotel.policies;

import com.hotel.interfaces.PoliticaCancelacion;
import com.hotel.model.Reserva;

public class PoliticaCancelacionFlexible implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        System.out.println("Política Flexible: Se permite la cancelación.");
        return true;
    }
    @Override
    public double calcularPenalizacion(Reserva reserva) {
        return 0.0;
    }
}