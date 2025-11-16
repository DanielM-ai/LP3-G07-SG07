package ejercicio_05_listas;

import javax.swing.JFrame;
 
public class PruebaLista
{
    public static void main(String[] args)
    {
        MarcoLista marcoLista = new MarcoLista(); // crea objeto MarcoLista
        marcoLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoLista.setSize(350, 150);
        marcoLista.setVisible(true);
    }
} // fin de la clase PruebaLista