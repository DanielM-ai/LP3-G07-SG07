package MarcoFlowLayout;

//Fig. 12.39: MarcoFlowLayout.java [cite: 923]
//FlowLayout permite que los componentes fluyan a través de varias líneas. [cite: 924]
import java.awt.FlowLayout; 
import java.awt.Container; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame; 
import javax.swing.JButton; 

public class MarcoFlow extends JFrame 
{
 private final JButton botonJButtonIzquierda; // botón para establecer la alineación a la izquierda [cite: 937]
 private final JButton botonJButtonCentro; // botón para establecer la alineación al centro [cite: 938]
 private final JButton botonJButtonDerecha; // botón para establecer la alineación a la derecha [cite: 939]
 private final FlowLayout esquema; // objeto esquema [cite: 940]
 private final Container contenedor; // contenedor para establecer el esquema [cite: 942]

 // establece la GUI y registra los componentes de escucha de botones [cite: 945]
 public MarcoFlow() 
 {
     // Título modificado según la Actividad 1 
     super("Actividad 1: FlowLayout - Mansilla-Marquez/G07"); 

     esquema = new FlowLayout(); 
     contenedor = getContentPane(); // obtiene contenedor para esquema [cite: 955]
     setLayout(esquema); 

     // establece botonJButtonIzquierda y registra componente de escucha [cite: 965]
     botonJButtonIzquierda = new JButton("Izquierda"); 
     add(botonJButtonIzquierda); // agrega botón Izquierda al marco [cite: 967]
     botonJButtonIzquierda.addActionListener(
         new ActionListener() // clase interna anónima [cite: 969]
         {
             // procesa evento de botonJButtonIzquierda [cite: 973]
             @Override
             public void actionPerformed(ActionEvent evento) 
             {
                 esquema.setAlignment(FlowLayout.LEFT); 
                 // realinea los componentes adjuntos [cite: 983]
                 esquema.layoutContainer(contenedor); 
             }
         }
     ); 

     // establece botonJButtonCentro y registra componente de escucha [cite: 997]
     botonJButtonCentro = new JButton("Centro");
     add(botonJButtonCentro); // agrega botón Centro al marco [cite: 1000]
     botonJButtonCentro.addActionListener(
         new ActionListener() // clase interna anónima [cite: 1002]
         {
             // procesa evento de botonJButtonCentro [cite: 1006]
             @Override
             public void actionPerformed(ActionEvent evento)
             {
                 esquema.setAlignment(FlowLayout.CENTER); 
                 // realinea los componentes adjuntos [cite: 1016]
                 esquema.layoutContainer(contenedor); 
             }
         }
     ); 

     // establece botonJButtonDerecha y registra componente de escucha [cite: 1027]
     botonJButtonDerecha = new JButton("Derecha"); 
     add(botonJButtonDerecha); // agrega botón Derecha al marco [cite: 1031]
     botonJButtonDerecha.addActionListener(
         new ActionListener() // clase interna anónima [cite: 1034]
         {
             // procesa evento de botonJButtonDerecha [cite: 1038]
             @Override
             public void actionPerformed(ActionEvent evento)
             {
                 esquema.setAlignment(FlowLayout.RIGHT); 
                 // realinea los componentes adjuntos [cite: 1048]
                 esquema.layoutContainer(contenedor); 
             }
         }
     ); 
 } // fin del constructor de MarcoFlowLayout [cite: 1058]
} // fin de la clase MarcoFlowLayout [cite: 1059]
