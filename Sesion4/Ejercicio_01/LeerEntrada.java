package Ejercicio_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LeerEntrada {
    private Reader stream;

    /**
     * Constructor
     *
     * @param fuente la fuente de datos
     */
    public LeerEntrada(InputStream fuente) {
        stream = new InputStreamReader(fuente);
    }

    /**
     * Obtiene el siguiente caracter del teclado, ignorando los caracteres de espacio en blanco.
     *
     * @return el caracter escrito
     * @throws IOException
     */
    public char getChar() throws IOException {
        char c;
        // Bucle para leer y descartar cualquier caracter de espacio en blanco
        do {
            c = (char) this.stream.read();
        } while (Character.isWhitespace(c) && c != 0);

        return c;
    }
}