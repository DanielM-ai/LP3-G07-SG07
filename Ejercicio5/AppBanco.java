package LP3;

import java.util.Scanner;

public class AppBanco {
    public static void main(String[] args) {
        // Se crea un arreglo de cuentas para demostrar el polimorfismo
        Cuenta[] cuentas = new Cuenta[4];
        cuentas[0] = new CuentaAhorro(1.5); // Cuenta 1 Ahorro
        cuentas[1] = new CuentaCorriente();   // Cuenta 2 Corriente
        cuentas[2] = new CuentaAhorro(1.2); // Cuenta 3 Ahorro
        cuentas[3] = new CuentaCorriente();   // Cuenta 4 Corriente
        
        // Depósitos iniciales
        cuentas[0].depositar(1000);
        cuentas[1].depositar(2000);
        cuentas[2].depositar(1500);
        cuentas[3].depositar(3000);

        Scanner in = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            System.out.print("\nD)epositar | R)etirar | C)onsultar | S)alir: ");
            String op = in.next().toUpperCase();

            if (op.equals("D") || op.equals("R")) {
                System.out.print("Ingrese número de cuenta (1-4) y monto: ");
                int num = in.nextInt();
                double monto = in.nextDouble();
                
                if (num > 0 && num <= cuentas.length) {
                    if (op.equals("D")) {
                        cuentas[num - 1].depositar(monto);
                    } else {
                        cuentas[num - 1].retirar(monto);
                    }
                    System.out.printf("Saldo Cuenta %d: S/.%.2f\n", num, cuentas[num - 1].getSaldo());
                } else {
                    System.out.println("Número de cuenta inválido.");
                }

            } else if (op.equals("C")) {
                System.out.println("\n--- Resumen de Cuentas ---");
                for (int i = 0; i < cuentas.length; i++) {
                    // Llamada polimórfica al método consultar
                    cuentas[i].consultar();
                    System.out.printf("Cuenta %d: Saldo S/.%.2f\n", (i + 1), cuentas[i].getSaldo());
                }
                
            } else if (op.equals("S")) {
                done = true;
                System.out.println("Gracias por usar el sistema.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
        in.close();
    }
}