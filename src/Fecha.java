
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrigo
 */
public class Fecha {
    
        private static String obtenerHora() {
        Calendar calendario = new GregorianCalendar();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);
        return hora + ":" + minutos + ":" + segundos;
    }

    private static String obtenerDiaActual() {
        Calendar calendario = new GregorianCalendar();
        int mes = calendario.get(Calendar.MONTH) + 1;
        return calendario.get(Calendar.DAY_OF_MONTH) + "/" + mes
                + "/" + calendario.get(Calendar.YEAR);
    }

    public static String obtenerHoraYDia() {
        String hora = obtenerHora();
        String dia = obtenerDiaActual();
        return "Hora: " +hora + " Día: " + dia;
    }
    
}
