package mvc_E01;

import controller.CarritoController;
import model.Carrito;
import model.HistorialManager; 
import view.CarritoView;

public class MainApp {
    public static void main(String[] args) {
        
        // 1. CREAR EL MODELO
        Carrito carritoModelo = new Carrito();
        HistorialManager historial = new HistorialManager(); 

        // 2. CREAR LA VISTA (Maneja el I/O por consola)
        CarritoView carritoVista = new CarritoView();
        
        // 3. CREAR EL CONTROLADOR y CONECTAR M-V
        CarritoController controlador = new CarritoController(carritoModelo, carritoVista, historial);
        
        // 4. INICIAR EL BUCLE INTERACTIVO
        // El control de la aplicación pasa al método dentro del Controller
        controlador.ejecutarAplicacion(); 
    }
}

