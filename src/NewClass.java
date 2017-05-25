
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrigo
 */
public class NewClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String salir;
        do {
                
            salir = sc.nextLine();
            
        } while (!(salir.equalsIgnoreCase("S") || salir.equalsIgnoreCase("N")));
        
    }
    
}
