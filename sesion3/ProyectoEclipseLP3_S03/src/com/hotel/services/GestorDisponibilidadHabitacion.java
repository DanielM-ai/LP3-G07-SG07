package com.hotel.services;

import com.hotel.model.Habitacion;
import com.hotel.model.Reserva;
import java.util.Date;
import java.util.List;

public class GestorDisponibilidadHabitacion {
    public boolean verificarDisponibilidad(Habitacion habitacion, List<Reserva> reservas, Date fechaInicio, Date fechaFin) {
        System.out.println("Verificando disponibilidad para la habitación " + habitacion.getNumero() + "...");
        // Aquí iría la lógica real para comprobar el cruce de fechas
        return true; // Simplificado para el ejemplo
    }
}