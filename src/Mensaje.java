/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrigo
 */
public class Mensaje {
    private Usuario emisor;
    private String fecha;
    private String mensaje;

    public Mensaje(Usuario emisor, Usuario receptor, String mensaje) {
        this.emisor = emisor;
        this.mensaje = mensaje;
        this.fecha = Fecha.obtenerHoraYDia();
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Emisor: " + emisor.getNombre() + "\tEnviado: " + fecha + "\tMensaje: " + mensaje;
    }
}
