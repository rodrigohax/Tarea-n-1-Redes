
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Servidor extends Thread {

    BufferedReader delCliente;
    PrintWriter alCliente;
    Socket connectionSocket;
    static ArrayList<Usuario> listadoUsuarios = new ArrayList<>();
    static Usuario usuarioLogueado;

    public Servidor(Socket s) {
        connectionSocket = s;
        try {
            delCliente = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            alCliente = new PrintWriter(connectionSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Problema creacion socket");
        }
    }

    @Override
    public void run() {
        // while(true){
        try {
            String usuario, password;
            alCliente.println("Ingrese su usuario");
            usuario = delCliente.readLine();
            alCliente.println("Ingrese su contraseña");
            password = delCliente.readLine();
            if (buscarUsuario(usuario)) {
                usuarioLogueado = obtenerUsuario(usuario,password);
            }else{
                listadoUsuarios.add(new Usuario(usuario,password));
                usuarioLogueado = obtenerUsuario(usuario,password);
            }

            alCliente.println("1.- Consultar hora y día actual");
            alCliente.println("2.- Consultar hora u día de la última consulta");
            alCliente.println("3.- Listar seudónimos de los usuarios registrados");
            alCliente.println("4.- Enviar Mensaje a usuario");
            alCliente.println("5.- Consultar si existen mensajes hacia su usuario");
            alCliente.println("6.- Borrar registro del usuario");
            alCliente.println("7.- Finalizar sesión");

            String clientSentence = delCliente.readLine();
            comando(clientSentence);
        } catch (IOException e) {
            System.out.println("Problema lectura/escritura en socket");
        }
        // }
    }

    public static void main(String argv[]) throws Exception {
        String clientSentence;

        ServerSocket welcomeSocket = new ServerSocket(1337);

        while (true) {

            Socket connectionSocket = welcomeSocket.accept();

            // crear hebra para nuevo cliente
            Servidor clienteNuevo
                    = new Servidor(connectionSocket);
            clienteNuevo.start();

        }
        // si se usa una condicion para quebrar el ciclo while, se deben cerrar los sockets!
        // connectionSocket.close(); 
        // welcomeSocket.close(); 
    }

    public void comando(String opcion) throws IOException {
        int numeroUsuarios = listadoUsuarios.size();
        usuarioLogueado.setUltimoIngreso(Fecha.obtenerHoraYDia());
        switch (opcion) {
            case "1":
                alCliente.println(Fecha.obtenerHoraYDia());
                break;
            case "2":
                String consulta = usuarioLogueado.getUltimoIngreso();
                System.out.println(consulta);
                alCliente.println(consulta);
                break;
            case "3":
                alCliente.println(numeroUsuarios);
                for (int i = 0; i < numeroUsuarios; i++) {
                    alCliente.println(i + 1 + ") " + listadoUsuarios.get(i).getNombre());
                }
                break;
            case "4":
                alCliente.println(numeroUsuarios);
                for (int i = 0; i < numeroUsuarios; i++) {
                    alCliente.println(i + 1 + ") " + listadoUsuarios.get(i).getNombre());
                }
                alCliente.println("Seleccione el usuario a quien desea enviar el mensaje");
                int idUsuario = Integer.parseInt(delCliente.readLine());
                Usuario receptor = listadoUsuarios.get(idUsuario-1);
                receptor.mensajes.add(new Mensaje(usuarioLogueado,receptor,"asd"));
                System.out.println(listadoUsuarios.get(idUsuario-1).toString());
                break;
            case "6":
                listadoUsuarios.remove(usuarioLogueado);
                alCliente.println("Usuario eliminado :)");
                break;

            case "7":
                alCliente.println("chao");
                break;
            default:
                alCliente.println("error");

        }
    }

    public boolean buscarUsuario(String nombre) {
        for (Usuario aux : listadoUsuarios) {
            if (aux.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    public Usuario obtenerUsuario(String nombre, String password) {
        for (Usuario aux : listadoUsuarios) {
            if (nombre.equals(aux.getNombre()) && password.equals(aux.getPassword())) {
                return aux;
            }
        }
        return null;
    }
}
