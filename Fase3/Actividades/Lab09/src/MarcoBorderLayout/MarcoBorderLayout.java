package MarcoBorderLayout;

//Fig. 12.41: MarcoBorderLayout.java [cite: 1127]
//BorderLayout que contiene cinco botones. [cite: 1128]
import java.awt.BorderLayout; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame; 
import javax.swing.JButton; 

public class MarcoBorderLayout extends JFrame implements ActionListener 
{
 private final JButton botones[]; // arreglo de botones para ocultar porciones [cite: 1138]
 private static final String nombres[] = {"Ocultar Norte", "Ocultar Sur", 
     "Ocultar Este", "Ocultar Oeste", "Ocultar Centro"}; 
 private final BorderLayout esquema; 

 // establece la GUI y el manejo de eventos [cite: 1146]
 public MarcoBorderLayout() 
 {
     // Título modificado según la Actividad 1 
     super("Actividad 2: BorderLayout - Mansilla-Marquez/G09");

     esquema = new BorderLayout(5, 5); // espacios de 5 píxeles 
     setLayout(esquema); 
     botones = new JButton[nombres.length]; 

     // crea objetos JButton y registra componentes de escucha para ellos [cite: 1169]
     for (int cuenta = 0; cuenta < nombres.length; cuenta++) 
     {
         botones[cuenta] = new JButton(nombres[cuenta]); 
         botones[cuenta].addActionListener(this); // registra el listener [cite: 1172]
     }

     add(botones[0], BorderLayout.NORTH); // agrega botón a la región NORTE [cite: 1173]
     add(botones[1], BorderLayout.SOUTH); // agrega botón a la región SUR [cite: 1175]
     add(botones[2], BorderLayout.EAST); // agrega botón a la región ESTE [cite: 1177]
     add(botones[3], BorderLayout.WEST); // agrega botón a la región OESTE [cite: 1179]
     add(botones[4], BorderLayout.CENTER); // agrega botón a la región CENTRO [cite: 1181]
 }

 // maneja los eventos de botón [cite: 1190]
 @Override
 public void actionPerformed(ActionEvent evento) 
 {
     // comprueba el origen del evento y distribuye el panel de contenido [cite: 1196]
     for (JButton boton : botones) 
     {
         if (evento.getSource() == boton) 
             boton.setVisible(false); // oculta el botón oprimido 
         else
             boton.setVisible(true); // muestra los demás botones 
     }

     esquema.layoutContainer(getContentPane()); // distribuye el panel de contenido 
 }
} // fin de la clase MarcoBorderLayout [cite: 1216]