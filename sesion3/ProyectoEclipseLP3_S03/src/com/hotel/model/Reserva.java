package com.hotel.model;

package com.hotel.model;

import com.hotel.interfaces.PoliticaCancelacion;
import java.util.Date;

public class Reserva {
    private Habitacion habitacion;
    private Cliente cliente;
    private Date fechaInicio;
    private PoliticaCancelacion politicaCancelacion;

    public Reserva(Habitacion habitacion, Cliente cliente, Date fechaInicio, PoliticaCancelacion politica) {
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.politicaCancelacion = politica;
    }

    public void cancelar() {
        if (politicaCancelacion.puedeCancelar(this)) {
            double penalizacion = politicaCancelacion.calcularPenalizacion(this);
            System.out.println("Reserva cancelada. Penalización: S/ " + penalizacion);
        } else {
            System.out.println("La reserva no puede ser cancelada.");
        }
    }
    
    // Getters
    public Cliente getCliente() {
        return cliente;
    }
}
public class Reserva {

}
