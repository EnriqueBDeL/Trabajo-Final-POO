package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import control.Demarcacion;

import control.Directivo;
import static control.Estado.DISPONIBLE;
import control.Jugador;
import control.Tecnico;
import gestion.Cliente;
import gestion.Factura;
import gestion.GestionClub;
import gestion.Partido;
import vista.VentanaPrincipal;

public class Main {

    /**
     * Clase principal que inicia la aplicación del club deportivo.
     * 
     * Se encarga de lanzar la ventana principal de la interfaz gráfica.
     */
    
    
    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));

     
    }
}
