
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrigo
 */
public class Usuario {
    private String nombre;
    private String password;
    private String ultimoIngreso;
    ArrayList<Mensaje> mensajes;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.ultimoIngreso = Fecha.obtenerHoraYDia();
        this.mensajes = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(String ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
    
    

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", password=" + password + ", ultimoIngreso=" + ultimoIngreso + ", mensajes=" + mensajes + '}';
    }
   
}
