/*
Ygor Verde
 */
package Calculadora;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculadoraServer {

    public static void main (String[] argv) {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind ("Calculadora", new calculadoraRemoto());
            System.out.println ("Servidor Iniciado!");
        } catch (Exception e) {
            System.out.println ("Servidor falhou: " + e);
        }
    }
}

