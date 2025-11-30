package patron.command;

// 1. CLASES Y COMANDOS (Fuera de la clase principal)

// Interfaz Command
interface Command {
    void execute();
}

// Receiver (El Televisor real)
class Televisor {
    private int volumen = 10;
    
    public void encender() {
        System.out.println("TV: Encendido.");
    }
    public void apagar() {
        System.out.println("TV: Apagado.");
    }
    public void subirVolumen() {
        volumen++;
        System.out.println("TV: Volumen subido a " + volumen);
    }
    public void bajarVolumen() {
        volumen--;
        System.out.println("TV: Volumen bajado a " + volumen);
    }
    public void silenciar() {
        System.out.println("TV: Silenciado (Mute activado).");
    }
}

// Comandos Concretos (Encapsulan la acción)
class EncenderCmd implements Command {
    private Televisor tv;
    public EncenderCmd(Televisor tv) { this.tv = tv; }
    @Override
    public void execute() { tv.encender(); }
}

class ApagarCmd implements Command {
    private Televisor tv;
    public ApagarCmd(Televisor tv) { this.tv = tv; }
    @Override
    public void execute() { tv.apagar(); }
}

class SubirVolumenCmd implements Command {
    private Televisor tv;
    public SubirVolumenCmd(Televisor tv) { this.tv = tv; }
    @Override
    public void execute() { tv.subirVolumen(); }
}

class BajarVolumenCmd implements Command {
    private Televisor tv;
    public BajarVolumenCmd(Televisor tv) { this.tv = tv; }
    @Override
    public void execute() { tv.bajarVolumen(); }
}

class SilenciarCmd implements Command {
    private Televisor tv;
    public SilenciarCmd(Televisor tv) { this.tv = tv; }
    @Override
    public void execute() { tv.silenciar(); }
}

// Invoker (El Control Remoto)
class ControlRemoto {
    private Command[] botones;

    public ControlRemoto() {
        botones = new Command[5]; // 5 botones programables
    }

    public void setComando(int indice, Command comando) {
        botones[indice] = comando;
    }

    public void presionarBoton(int indice) {
        if (indice >= 0 && indice < botones.length && botones[indice] != null) {
            botones[indice].execute();
        } else {
            System.out.println("Botón " + indice + " no configurado.");
        }
    }
}

// 2. CLASE PRINCIPAL (Debe llamarse MainCommand)
public class MainCommand {
    public static void main(String[] args) {
        Televisor miTV = new Televisor();
        ControlRemoto control = new ControlRemoto();

        // Configuración de los botones del control (Parametrización)
        control.setComando(0, new EncenderCmd(miTV));
        control.setComando(1, new ApagarCmd(miTV));
        control.setComando(2, new SubirVolumenCmd(miTV));
        control.setComando(3, new BajarVolumenCmd(miTV));
        control.setComando(4, new SilenciarCmd(miTV));

        System.out.println("--- Prueba del Patrón Command (Control Remoto) ---");
        
        // Simulación de uso
        control.presionarBoton(0); // Botón 0: Encender
        control.presionarBoton(2); // Botón 2: Subir Volumen
        control.presionarBoton(2); // Botón 2: Subir Volumen de nuevo
        control.presionarBoton(4); // Botón 4: Silenciar
        control.presionarBoton(3); // Botón 3: Bajar Volumen
        control.presionarBoton(1); // Botón 1: Apagar
    }
}