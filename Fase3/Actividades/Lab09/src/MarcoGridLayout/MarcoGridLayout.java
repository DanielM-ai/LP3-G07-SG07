package MarcoGridLayout;

//Fig. 12.43: MarcoGridLayout.java [cite: 1306]
//GridLayout que contiene seis botones. [cite: 1307]
import java.awt.GridLayout; 
import java.awt.Container; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame; 
import javax.swing.JButton; 

public class MarcoGridLayout extends JFrame implements ActionListener 
{
 private final JButton[] botones; // arreglo de botones [cite: 1318]
 private static final String[] nombres = 
     { "uno", "dos", "tres", "cuatro", "cinco", "seis" };
 private boolean alternar = true; // alterna entre dos esquemas [cite: 1322]
 private Container contenedor; // contenedor del marco [cite: 1323]
 private GridLayout cuadricula1; // primer objeto GridLayout [cite: 1325]
 private GridLayout cuadricula2; // segundo objeto GridLayout [cite: 1326]

 // constructor sin argumentos [cite: 1331]
 public MarcoGridLayout() 
 {
     // Título modificado según la Actividad 1 
     super("Actividad 3: GridLayout - Mansilla-Marquez/G09"); 
     
     // 2 filas, 3 columnas, 5px de espacio horizontal y vertical [cite: 1342]
     cuadricula1 = new GridLayout(2, 3, 5, 5); 
     // 3 filas, 2 columnas, sin espacio [cite: 1343]
     cuadricula2 = new GridLayout(3, 2); 
     
     contenedor = getContentPane(); 
     setLayout(cuadricula1); // establece el esquema inicial [cite: 1345]
     
     botones = new JButton[nombres.length]; 

     for (int cuenta = 0; cuenta < nombres.length; cuenta++) 
     {
         botones[cuenta] = new JButton(nombres[cuenta]); 
         botones[cuenta].addActionListener(this); // registra componente de escucha [cite: 1356]
         add(botones[cuenta]); // agrega boton a objeto JFrame [cite: 1359]
     }
 }

 // maneja eventos de boton, alternando entre los esquemas [cite: 1366]
 @Override
 public void actionPerformed(ActionEvent evento) 
 {
     if (alternar) // si es true, establece cuadricula2 
         contenedor.setLayout(cuadricula2); 
     else // si es false, establece cuadricula1 [cite: 1378]
         contenedor.setLayout(cuadricula1); 

     alternar = !alternar; // invierte el valor del booleano [cite: 1383]
     contenedor.validate(); // redistribuye el contenedor 
 }
} // fin de la clase MarcoGridLayout [cite: 1389]
