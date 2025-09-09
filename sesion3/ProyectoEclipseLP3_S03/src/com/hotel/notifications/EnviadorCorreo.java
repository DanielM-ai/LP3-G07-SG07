package com.hotel.notifications;

import com.hotel.interfaces.CanalNotificacion;

public class EnviadorCorreo implements CanalNotificacion {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("[CORREO] Enviando: '" + mensaje + "'");
    }
}