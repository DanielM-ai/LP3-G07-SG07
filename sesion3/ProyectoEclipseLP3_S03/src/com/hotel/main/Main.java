package com.hotel.main;

import com.hotel.interfaces.CanalNotificacion;
import com.hotel.interfaces.PoliticaCancelacion;
import com.hotel.model.Cliente;
import com.hotel.model.Habitacion;
import com.hotel.model.Reserva;
import com.hotel.notifications.EnviadorCorreo;
import com.hotel.notifications.EnviadorSMS;
import com.hotel.policies.PoliticaCancelacionFlexible;
import com.hotel.services.NotificadorReserva;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // --- 1. Configuración de dependencias (DIP) ---
        // Podemos elegir fácilmente qué canal de notificación usar
        CanalNotificacion canalPreferido = new EnviadorCorreo(); 
        // CanalNotificacion canalAlternativo = new EnviadorSMS();
        
        NotificadorReserva notificador = new NotificadorReserva(canalPreferido);

        // --- 2. Creación de la reserva (OCP) ---
        // Creamos los objetos necesarios
        Cliente cliente = new Cliente("Juan Perez");
        Habitacion habitacionSuite = new Habitacion(101, "Suite", 500.0);
        
        // Elegimos la política de cancelación
        PoliticaCancelacion politicaFlexible = new PoliticaCancelacionFlexible();
        
        // Creamos la reserva
        Reserva miReserva = new Reserva(habitacionSuite, cliente, new Date(), politicaFlexible);
        
        System.out.println("✅ Reserva creada exitosamente.");
        
        // Usamos el servicio de notificación
        notificador.notificarNuevaReserva(miReserva);
        
        System.out.println("\n--- Intentando cancelar la reserva ---");
        
        // --- 3. Cancelación de la reserva ---
        // La lógica de cancelacsión está encapsulada en la política
        miReserva.cancelar();
    }
}