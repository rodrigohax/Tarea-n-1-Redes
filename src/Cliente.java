/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.net.Socket;

/**
 *
 * @author rodrigo
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        //Stream para leer los datos desde teclado
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        //Creacion de socket para cliente
        Socket socketCliente = new Socket("localhost", 1337);
        System.out.println("Conexion aceptada. " + socketCliente.toString());

        //Stream de envio de datos al servidor
        DataOutputStream alServidor = new DataOutputStream(socketCliente.getOutputStream());

        //Stream recepcion datos del servidor
        BufferedReader delServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

        String mensajeServidor = "";
        String mensaje;

        /*
        *   LEER USUARIO
         */
        System.out.println(delServidor.readLine());
        mensaje = teclado.readLine();
        alServidor.writeBytes(mensaje + "\n");
        /*
            LEER PASSWORD
         */
        System.out.println(delServidor.readLine());
        mensaje = teclado.readLine();
        alServidor.writeBytes(mensaje + "\n");

        /*
            RECIBIR MENSAJE DE BIENVENIDA
        */
        System.out.println(delServidor.readLine());
        /*
        *   LEER OPCIONES
         */
        recibirMenuOpciones(delServidor);
        mensaje = teclado.readLine();
        alServidor.writeBytes(mensaje + "\n");
        int numeroUsuarios;
        switch (mensaje) {
            case "3":
                numeroUsuarios = Integer.parseInt(delServidor.readLine());
                for (int i = 0; i < numeroUsuarios; i++) {
                    System.out.println(delServidor.readLine());
                }
                break;
            case "4":
                numeroUsuarios = Integer.parseInt(delServidor.readLine());
                for (int i = 0; i < numeroUsuarios; i++) {
                    System.out.println(delServidor.readLine());
                }
                System.out.println(delServidor.readLine());
                do {
                    mensaje = teclado.readLine();
                } while (Integer.parseInt(mensaje) > numeroUsuarios);

                alServidor.writeBytes(mensaje + "\n");

                System.out.println(delServidor.readLine());
                mensaje = teclado.readLine();
                alServidor.writeBytes(mensaje + "\n");
                System.out.println(delServidor.readLine());
                break;
            case "5":
                int numeroMensajes = Integer.parseInt(delServidor.readLine());
                if (numeroMensajes == 0) {
                    System.out.println(delServidor.readLine());
                } else {
                    for (int i = 0; i < numeroMensajes; i++) {
                        System.out.println(delServidor.readLine());
                    }
                }
                break;
            case "7":
                System.exit(0);
            default:
                mensajeServidor = delServidor.readLine();
                System.out.println(mensajeServidor);
                break;
        }
        socketCliente.close();
    }

    public static void recibirMenuOpciones(BufferedReader delServidor) throws IOException {
        for (int i = 0; i < 7; i++) {
            System.out.println(delServidor.readLine());
        }
    }
}
