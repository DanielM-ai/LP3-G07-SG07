package Ejercicio_02;

public class TemperaturaSemana {
    private double[] temperaturas = new double[7]; // 0=Lunes ... 6=Domingo
    private final String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    public void setTemperatura(int diaIndex, double temp) {
        if (diaIndex >= 0 && diaIndex < 7) {
            temperaturas[diaIndex] = temp;
        }
    }

    public double getTemperatura(int diaIndex) {
        return temperaturas[diaIndex];
    }

    public String getDia(int index) {
        return dias[index];
    }

    public int getCantidadDias() {
        return 7;
    }
}