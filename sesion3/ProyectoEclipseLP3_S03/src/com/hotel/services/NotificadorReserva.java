package com.hotel.services;

import com.hotel.interfaces.CanalNotificacion;
import com.hotel.model.Reserva;

public class NotificadorReserva {
    private CanalNotificacion canal;

    // La dependencia se inyecta por el constructor
    public NotificadorReserva(CanalNotificacion canal) {
        this.canal = canal;
    }

    public void notificarNuevaReserva(Reserva reserva) {
        String mensaje = "Reserva confirmada para " + reserva.getCliente().getNombre();
        canal.enviarNotificacion(mensaje);
    }
}