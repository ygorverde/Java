/*
Ygor Verde
 */
package Calculadora;

import Calculadora.Calculadora;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class calculadoraRemoto extends java.rmi.server.UnicastRemoteObject implements Calculadora  {
    
    public calculadoraRemoto()throws RemoteException{   
        int a,b;
    }
    
    

    public int adicionar(int a, int b)throws RemoteException{
        return a+b;
    }
    
    
  
       public int subtrair(int a, int b)throws RemoteException{
        return a-b;
    }
       
   
           public int multiplicar(int a, int b)throws RemoteException{
        return a*b;
    }
           
    
           public int dividir(int a, int b)throws RemoteException{
        return a/b;
    }
       
    
    

}