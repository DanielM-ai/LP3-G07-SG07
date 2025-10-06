package mvc_E02;

import controller.InventarioController;
import model.InventarioModel;
import view.InventarioView;

public class MainApp {
    public static void main(String[] args) {
        
        // 1. CREAR EL MODELO
        InventarioModel modelo = new InventarioModel();

        // 2. CREAR LA VISTA
        InventarioView vista = new InventarioView();
        
        // 3. CREAR EL CONTROLADOR y CONECTAR M-V
        InventarioController controlador = new InventarioController(modelo, vista);
        
        // 4. INICIAR EL BUCLE INTERACTIVO
        controlador.ejecutarAplicacion(); 
    }
}

