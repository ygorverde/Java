/*
Ygor Verde
 */
package Calculadora;    

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class Cliente {
      public static void main (String args[]){
       Scanner sc = new Scanner (System.in);
          try{
              Registry meuRegistro = LocateRegistry.getRegistry("localhost", 1099);
                      Calculadora c = (Calculadora)Naming.lookup("//localhost/Calculadora");
                      int a,b;
                          System.out.println("  CALCULADORA RMI\n"+
                                  "ADIÇÃO          (1)\n"+
                                  "SUBTRAÇÃO       (2)\n"+
                                  "MULTIPLICAÇÃO   (3)\n"+
                                  "DIVISÃO         (4)\n");
                          System.out.println("DIGITE A OPÇÃO DESEJADA: \n");
                          int opcao = sc.nextInt();
                          switch(opcao){
                            case 1:
                                  System.out.println("Digite o primeiro digito para adição");
                                   a = sc.nextInt();
                                  System.out.println("Digite o segundo digito para adição");
                                  b = sc.nextInt();
                                  System.out.println("RESULTADO: "+c.adicionar(a, b));
                                  break;
                             case 2:
                                  System.out.println("Digite o primeiro digito para subtração");
                                   a = sc.nextInt();
                                  System.out.println("Digite o segundo digito para subtração");
                                   b = sc.nextInt();
                                  System.out.println("RESULTADO: "+c.subtrair(a, b));
                                  break;
                            case 3:
                                  System.out.println("Digite o primeiro digito para multiplicação");
                                   a = sc.nextInt();
                                  System.out.println("Digite o segundo digito para divisão");
                                   b = sc.nextInt();
                                  System.out.println("RESULTADO: "+c.multiplicar(a, b));
                                  break;
                            case 4:
                                  System.out.println("Digite o primeiro digito para divisão");
                                   a = sc.nextInt();
                                  System.out.println("Digite o segundo digito para divisão");
                                   b = sc.nextInt();
                                  System.out.println("RESULTADO: "+c.dividir(a, b));
                                  break;
                          }
                     
          }catch(Exception ex){
              System.out.println ("Servidor calculadora falhou!! \n" + ex);
          }
          

      }
}