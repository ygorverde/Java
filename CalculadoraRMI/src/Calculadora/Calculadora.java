/**
 *
 * @author Ygor
 */
package Calculadora;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {
    public int adicionar(int a, int b) throws RemoteException;
    public int subtrair(int a, int b) throws RemoteException;
    public int multiplicar(int a, int b) throws RemoteException;
    public int dividir(int a, int b) throws RemoteException;
    
}
