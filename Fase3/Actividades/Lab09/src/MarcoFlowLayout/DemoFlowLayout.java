package MarcoFlowLayout;


//Fig. 12.40: DemoFlowLayout.java
//Prueba MarcoFlowLayout.
import javax.swing.JFrame;

//Si tus archivos están dentro del paquete "MarcoFlowLayout",
//borra esta línea. Si los moviste a "src", déjala.
//package MarcoFlowLayout; 

public class DemoFlowLayout
{
 public static void main(String[] args)
 {
     MarcoFlow marcoFlowLayout = new MarcoFlow();
     marcoFlowLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     marcoFlowLayout.setSize(300, 75); // establece el tamaño del marco
     marcoFlowLayout.setVisible(true); // muestra el marco
 }
}