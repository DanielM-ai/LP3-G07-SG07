package actividad2_y_4;
public class ExcepcionPilaLlena extends RuntimeException {
    public ExcepcionPilaLlena(String message) {
        super(message);
    }
}