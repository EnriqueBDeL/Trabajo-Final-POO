package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import control.Demarcacion;
import control.Directivo;
import control.Estado;
import control.Jugador;
import control.Tecnico;
import control.Tecnico.Especialidad;
import control.Tecnico.Puesto;
import control.Persona;
import gestion.ConceptoNomina;
import gestion.Factura;
import gestion.GestionClub;
import gestion.Nomina;
import gestion.Partido;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Ventana principal de la aplicación para la gestión de un club de fútbol.
 * 
 * Esta clase extiende JFrame y contiene la interfaz gráfica con botones para
 * realizar diversas operaciones como añadir, eliminar y listar jugadores,
 * técnicos y directivos, además de gestionar partidos, nóminas y facturas.
 * 
 */

public class VentanaPrincipal extends JFrame {
  
    
    /**
     * Instancia de la clase GestionClub que maneja la lógica y datos del club.
     */
   
    private GestionClub gestionClub;

    


    
  
    
 //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||   
   
     /**
     * Constructor que inicializa la ventana principal con el logo, los botones funcionales
     * y carga la información inicial desde los archivos de jugadores, técnicos y directivos.
     */
    
    public VentanaPrincipal() {
      
         this.gestionClub = GestionClub.getInstancia();
         
        cargarJugadoresDesdeArchivo("jugadores.txt");
        cargarTecnicosDesdeArchivo("tecnicos.txt");
        cargarDirectivosDesdeArchivo("directivos.txt");
        gestionClub.cargarConceptosDesdeArchivo("conceptos.txt");

        
        setTitle("Gestión del Club de Fútbol");
        setSize(720, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null); 

        //getContentPane().setBackground(new Color(177, 240, 150));
        
        
// Panel de botones
        JPanel panelBotones = new JPanel(null);
        panelBotones.setBounds(0, 0, 360, 850);
        //panelBotones.setBackground(new Color(177, 240, 150));
        configurarBotones(panelBotones);
        add(panelBotones);
        
        
// Logo grande
        JLabel logoLabel = crearLogoGrande("/imagenes/SoccerFootball.png", 300, 300);
        logoLabel.setBounds(390, 150, 300, 300); 
        add(logoLabel);

        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/imagenes/SoccerFootball.png"));
        setIconImage(iconoVentana.getImage());

        setVisible(true);
    }
    
    
     /**
     * Crea un JLabel con un logo en un lado de la interfaz.
     *
     * @param ruta  Ruta del recurso de la imagen.
     * @param ancho Ancho deseado.
     * @param alto  Alto deseado.
     * @return JLabel con la imagen redimensionada.
     */
    
    private JLabel crearLogoGrande(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(imagen));
    }

//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||    
    

     
    
    
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
     
    /**
     * Configura los botones del panel con sus posiciones y acciones.
     *
     * @param panel JPanel donde se añadirán los botones.
     */
    
    private void configurarBotones(JPanel panel) {
        panel.add(crearBoton("Añadir Jugador", 30, e -> accionAnadirJugador(e), new Color(173, 216, 230)));
        panel.add(crearBoton("Añadir Técnico", 70, e -> accionAnadirTecnico(e), new Color(173, 216, 230)));
        panel.add(crearBoton("Añadir Directivo", 110, e -> accionAnadirDirectivo(e), new Color(173, 216, 230)));
        panel.add(crearBoton("Eliminar Persona", 150, e -> accionEliminarPersona(e), new Color(255, 110, 110))); 
        panel.add(crearBoton("Modificar Persona", 190, e -> accionModificarPersona(e), new Color(200, 162, 200)));
        panel.add(crearBoton("Listar Jugadores", 230, e -> accionListarJugadores(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Listar Técnicos", 270, e -> accionListarTecnicos(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Listar Directivos", 310, e -> accionListarDirectivos(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Listar Eliminados", 350, e -> accionListarPersonasEliminadas(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Listar Partidos", 390, e -> accionListarPartidos(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Listar Facturas", 430, e -> accionListarFacturas(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Listar Nóminas", 470, e -> accionListarNominas(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Listar Conceptos", 510, e -> accionListarConceptos(e), new Color(255, 255, 224)));
        panel.add(crearBoton("Registrar Partido", 550, e -> accionRegistrarPartido(e), new Color(173, 216, 230)));
        panel.add(crearBoton("Crear Nómina", 590, e -> accionCrearNomina(e), new Color(173, 216, 230)));
        panel.add(crearBoton("Crear Factura", 630, e -> accionCrearFactura(e), new Color(173, 216, 230)));
        panel.add(crearBoton("Crear Concepto", 670, e -> accionAnadirConceptoNomina(e), new Color(173, 216, 230)));
        panel.add(crearBoton("Ver Estadísticas", 710, e -> accionMostrarEstadisticas(e), new Color(204, 255, 204)));

        panel.add(crearBoton("Salir", 750, e -> System.exit(0), new Color(255, 125, 191))); 

    }
    
    
    
    /**
     * Crea un botón con texto, posición vertical y acción asignada.
     *
     * @param texto  Texto que aparece en el botón.
     * @param y      Posición vertical en el panel.
     * @param accion Acción que se ejecuta al pulsar el botón.
     * @return JButton configurado.
     */
    
    
    
   private JButton crearBoton(String texto, int y, ActionListener accion, Color colorFondo) {
    JButton boton = new JButton(texto);
    boton.setBounds(80, y, 200, 30);
    boton.addActionListener(accion);
    boton.setBackground(colorFondo);
    boton.setForeground(Color.BLACK);
    boton.setOpaque(true);
    boton.setBorderPainted(false);
    return boton;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
  
    
  
    
    /**
    * Carga los jugadores desde un archivo de texto, verifica si alguno está marcado como eliminado
    * y lo registra en el sistema de gestión del club.
    *
    * @param rutaArchivo Ruta al archivo que contiene los datos de los jugadores.
    */
    
    public void cargarJugadoresDesdeArchivo(String rutaArchivo) {
    Map<String, Date> eliminados = cargarEliminados();

    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split(", ");
            if (partes.length < 8) {
                System.err.println("Línea con formato incorrecto: " + linea);
                continue;
            }

            String dni = partes[0].split(": ")[1];
            String nombre = partes[1].split(": ")[1];
            String apellido = partes[2].split(": ")[1];
            String telefono = partes[3].split(": ")[1];
            int edad = Integer.parseInt(partes[4].split(": ")[1]);

            String valorStr = partes[5].split(": ")[1].replace("M€", "").replace(",", ".");
            double valorMercado = Double.parseDouble(valorStr);

            Estado estado = Estado.valueOf(partes[6].split(": ")[1]);
            Demarcacion demarcacion = Demarcacion.valueOf(partes[7].split(": ")[1]);

            Jugador jugador = new Jugador(demarcacion, edad, valorMercado, estado, dni, nombre, apellido, telefono);

            if (eliminados.containsKey(dni)) {
                jugador.setEliminado(true);
                jugador.setFechaEliminacion(eliminados.get(dni));
            }

            gestionClub.altaJugador(jugador);
        }
    } catch (IOException | IllegalArgumentException ex) {
        ex.printStackTrace();
    }
}

  
  /**
   * Carga los técnicos desde un archivo de texto, verifica si alguno está marcado como eliminado
   * y los registra en el sistema de gestión del club.
   *
   * @param rutaArchivo Ruta al archivo que contiene los datos de los técnicos.
   */
    
    public void cargarTecnicosDesdeArchivo(String rutaArchivo) {
      Map<String, Date> eliminados = cargarEliminados();

      try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
          String linea;
          while ((linea = br.readLine()) != null) {
              linea = linea.trim();
              if (linea.isEmpty()) continue;

              String[] partes = linea.split(", ");
              if (partes.length < 6) {
                  System.err.println("Línea con formato incorrecto: " + linea);
                  continue;
              }

              String dni = partes[0].split(": ")[1];
              String nombre = partes[1].split(": ")[1];
              String apellido = partes[2].split(": ")[1];
              String telefono = partes[3].split(": ")[1];
              Puesto puesto = Puesto.valueOf(partes[4].split(": ")[1]);
              Especialidad especialidad = Especialidad.valueOf(partes[5].split(": ")[1]);

              Tecnico tecnico = new Tecnico(puesto, especialidad, dni, nombre, apellido, telefono);

              if (eliminados.containsKey(dni)) {
                  tecnico.setEliminado(true);
                  tecnico.setFechaEliminacion(eliminados.get(dni));
              }

              gestionClub.altaTecnico(tecnico);
          }
      } catch (IOException | IllegalArgumentException ex) {
          ex.printStackTrace();
      }
  }

   /**
   * Carga los directivos desde un archivo de texto y los da de alta en el sistema.
   * Si el DNI de un directivo está en la lista de eliminados, se marca como eliminado.
   *
   * @param nombreArchivo Ruta del archivo que contiene los datos de los directivos.
   */
    
    private void cargarDirectivosDesdeArchivo(String nombreArchivo) {
    Map<String, Date> eliminados = cargarEliminados();

    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(", ");
            if (partes.length == 5) {
                String dni = partes[0].split(": ")[1].trim();
                String nombre = partes[1].split(": ")[1].trim();
                String apellido = partes[2].split(": ")[1].trim();
                String telefono = partes[3].split(": ")[1].trim();
                String cargo = partes[4].split(": ")[1].trim();

                Directivo directivo = new Directivo(cargo, dni, nombre, apellido, telefono);

                if (eliminados.containsKey(dni)) {
                    directivo.setEliminado(true);
                    directivo.setFechaEliminacion(eliminados.get(dni));
                }

                gestionClub.altaDirectivo(directivo);
            }
        }
    } catch (IOException | ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
    }
}

  
    
   
/**
 * Carga los DNI y fechas de eliminación de personas desde el archivo "eliminados.txt".
 * Esta información se utiliza para marcar como eliminados a jugadores, técnicos o directivos.
 *
 * @return Un mapa con los DNI como claves y las fechas de eliminación como valores.
 */
    

  private Map<String, Date> cargarEliminados() {
    Map<String, Date> eliminados = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("eliminados.txt"))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length == 2) {
                String dni = partes[0].trim();
                LocalDate fecha = LocalDate.parse(partes[1].trim());
                eliminados.put(dni, java.sql.Date.valueOf(fecha));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return eliminados;
}

   
  
  
    
     /**
     * Muestra un formulario para añadir un jugador y gestiona la creación
     * y alta del mismo en la gestión del club.
     *
     * @param e Evento de acción generado por el botón.
     */  

    private void accionAnadirJugador(ActionEvent e) {
        JPanel formPanel = crearFormularioJugador();

    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagenes/Football.png"));

    Image iconoRedimensionado = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    ImageIcon iconoPequeño = new ImageIcon(iconoRedimensionado);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "Añadir Jugador", JOptionPane.OK_CANCEL_OPTION,  JOptionPane.PLAIN_MESSAGE, iconoPequeño);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String dni = obtenerTextoCampo(formPanel, 0);
                String nombre = obtenerTextoCampo(formPanel, 1);
                String apellido = obtenerTextoCampo(formPanel, 2);
                String telefono = obtenerTextoCampo(formPanel, 3);

                Demarcacion demarcacion = (Demarcacion) ((JComboBox<?>) formPanel.getComponent(9)).getSelectedItem();
                int edad = Integer.parseInt(obtenerTextoCampo(formPanel, 5));
                double valorMercado = Double.parseDouble(obtenerTextoCampo(formPanel, 6));
                Estado estado = (Estado) ((JComboBox<?>) formPanel.getComponent(15)).getSelectedItem();

                Jugador jugador = new Jugador(demarcacion, edad, valorMercado, estado, dni, nombre, apellido, telefono);
                gestionClub.altaJugador(jugador);

                 guardarListadoEnArchivo("jugadores.txt", gestionClub.listarJugadores());

                JOptionPane.showMessageDialog(this, "Jugador añadido exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    
    /**
     * Muestra un formulario para añadir un técnico y gestiona la creación
     * y alta del mismo en la gestión del club.
     *
     * @param e Evento de acción generado por el botón.
     */

    private void accionAnadirTecnico(ActionEvent e) {
        JPanel formPanel = crearFormularioTecnico();
        
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagenes/ball-field-football.png"));

        Image iconoRedimensionado = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoPequeño = new ImageIcon(iconoRedimensionado);
        
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Añadir Técnico", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, iconoPequeño);

        if (result == JOptionPane.OK_OPTION) {
            try {
            String dni = obtenerTextoCampo(formPanel, 0);
            String nombre = obtenerTextoCampo(formPanel, 1);
            String apellido = obtenerTextoCampo(formPanel, 2);
            String telefono = obtenerTextoCampo(formPanel, 3);
            
            Puesto puesto = (Puesto) ((JComboBox<?>) formPanel.getComponent(9)).getSelectedItem();
            Especialidad especialidad = (Especialidad) ((JComboBox<?>) formPanel.getComponent(11)).getSelectedItem();


               
                Tecnico tecnico = new Tecnico(puesto, especialidad, dni, nombre, apellido, telefono);
                gestionClub.altaTecnico(tecnico);
                
                guardarListadoEnArchivo("tecnicos.txt", gestionClub.listarTecnicos());
                
                JOptionPane.showMessageDialog(this, "Técnico añadido exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    
     /**
     * Muestra un formulario para añadir un directivo y gestiona la creación
     * y alta del mismo en la gestión del club.
     *
     * @param e Evento de acción generado por el botón.
     */
    
    private void accionAnadirDirectivo(ActionEvent e) {
        JPanel formPanel = crearFormularioDirectivo();
        
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagenes/caucasian_head_man_person_peopl.png"));

        Image iconoRedimensionado = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoPequeño = new ImageIcon(iconoRedimensionado);
        
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Añadir Directivo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, iconoPequeño);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String dni = obtenerTextoCampo(formPanel, 0);
                String nombre = obtenerTextoCampo(formPanel, 1);
                String apellido = obtenerTextoCampo(formPanel, 2);
                String telefono = obtenerTextoCampo(formPanel, 3);
                String cargo = obtenerTextoCampo(formPanel, 4);


                Directivo directivo = new Directivo(cargo, dni, nombre, apellido, telefono);
                gestionClub.altaDirectivo(directivo);
                
                guardarListadoEnArchivo("directivos.txt", gestionClub.listarDirectivos());
                
                JOptionPane.showMessageDialog(this, "Directivo añadido exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


private void accionAnadirConceptoNomina(ActionEvent e) {
    JPanel formPanel = crearFormularioConceptoNomina();

    ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Concepto.png")); 
    Image imagen = icono.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    ImageIcon iconoPequeño = new ImageIcon(imagen);

    int result = JOptionPane.showConfirmDialog(this, formPanel, "Crear Concepto",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, iconoPequeño);

    if (result == JOptionPane.OK_OPTION) {
        try {
            JTextField codigoField = (JTextField) formPanel.getClientProperty("codigoField");
            JTextField descripcionField = (JTextField) formPanel.getClientProperty("descripcionField");
            JTextField importeField = (JTextField) formPanel.getClientProperty("importeField");

            String codigo = codigoField.getText().trim();
            String descripcion = descripcionField.getText().trim();
            float importe = Float.parseFloat(importeField.getText().trim());

            ConceptoNomina concepto = new ConceptoNomina(codigo, descripcion, importe);
        gestionClub.altaConcepto(concepto);
        guardarListadoEnArchivoConceptos("conceptos.txt", gestionClub.listarConceptos());


            JOptionPane.showMessageDialog(this, "Concepto Nómina añadido exitosamente.");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Importe no válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en la entrada de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    /**
     * Crea un formulario para ingresar datos de un jugador.
     * 
     * El formulario contiene campos para DNI, nombre, teléfono, demarcación,
     * edad, valor de mercado y estado físico.
     * 
     * Los campos DNI, nombre, teléfono, edad y valor de mercado son campos de texto.
     * Los campos demarcación y estado físico son desplegables basados en los valores
     * de los enums Demarcacion y Estado, respectivamente.
     * 
     * El formulario está organizado en un panel con un diseño de cuadrícula
     * de dos columnas, donde cada etiqueta está seguida de su campo correspondiente.
     * 
     * @return un JPanel que contiene el formulario con los campos para un jugador
     */

    private JPanel crearFormularioJugador() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.add(new JLabel("DNI:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Nombre:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Apellido:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Teléfono:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Demarcación:")); formPanel.add(new JComboBox<>(Demarcacion.values()));
        formPanel.add(new JLabel("Edad:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Valor de Mercado:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Estado Físico:")); formPanel.add(new JComboBox<>(Estado.values()));
        return formPanel;
    }
    
    
   
    /**
    * Crea un formulario para ingresar datos de un técnico.
    * 
    * El formulario contiene campos para DNI, nombre, teléfono, puesto y especialidad.
    * Los campos de DNI, nombre y teléfono son campos de texto, mientras que
    * los campos de puesto y especialidad son desplegables basados en los valores
    * de los enums Puesto y Especialidad.
    * 
    * El formulario está organizado en un panel con un diseño de cuadrícula
    * de dos columnas, donde cada etiqueta está seguida de su campo correspondiente.
    * 
    * @return un JPanel que contiene el formulario con los campos para un técnico
    */
    
    private JPanel crearFormularioTecnico() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.add(new JLabel("DNI:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Nombre:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Apellido:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Teléfono:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Puesto:")); formPanel.add(new JComboBox<>(Puesto.values()));
        formPanel.add(new JLabel("Especialidad:")); formPanel.add(new JComboBox<>(Especialidad.values()));
        return formPanel;
    }

    /**
     * Crea un formulario para ingresar datos de un directivo.
     * 
     * El formulario contiene campos para DNI, nombre, teléfono y cargo, organizados
     * en un panel con un diseño de cuadrícula de dos columnas, donde cada etiqueta
     * está seguida de su campo de texto correspondiente.
     * 
     * @return un JPanel que contiene el formulario con los campos para un directivo
     */    
    
    private JPanel crearFormularioDirectivo() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.add(new JLabel("DNI:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Nombre:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Apellido:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Teléfono:")); formPanel.add(new JTextField());
        formPanel.add(new JLabel("Cargo:")); formPanel.add(new JTextField());
        return formPanel;
    }
    
    
private JPanel crearFormularioConceptoNomina() {
    JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

    panel.add(new JLabel("Código:"));
    JTextField codigoField = new JTextField();
    panel.add(codigoField);

    panel.add(new JLabel("Descripción:"));
    JTextField descripcionField = new JTextField();
    panel.add(descripcionField);

    panel.add(new JLabel("Importe:"));
    JTextField importeField = new JTextField();
    panel.add(importeField);

    // Guardamos referencias para usar luego:
    panel.putClientProperty("codigoField", codigoField);
    panel.putClientProperty("descripcionField", descripcionField);
    panel.putClientProperty("importeField", importeField);

    return panel;
}
 
    
    
    /**
     * Obtiene el texto del campo ubicado en una posición específica dentro de un JPanel.
     * 
     * Este método asume que el panel contiene pares de componentes: un JLabel seguido de un campo de entrada,
     * que puede ser un JTextField o un JComboBox. Según el índice del campo, extrae el texto correspondiente.
     * 
     * @param formPanel el JPanel que contiene los campos de entrada
     * @param campoIndex el índice del campo a obtener (considerando solo los campos de entrada, no los JLabel)
     * @return el texto contenido en el campo, o una cadena vacía si el componente no es JTextField ni JComboBox
     */
    
    private String obtenerTextoCampo(JPanel formPanel, int campoIndex) {
        Component component = formPanel.getComponent((campoIndex * 2) + 1); 
        if (component instanceof JTextField) {
            return ((JTextField) component).getText().trim();
        } else if (component instanceof JComboBox<?>) {
            Object selectedItem = ((JComboBox<?>) component).getSelectedItem();
            return selectedItem != null ? selectedItem.toString().trim() : "";
        }
        return "";
    }

    /**
     * Permite eliminar un jugador del club mediante su DNI.
     * 
     * Muestra un cuadro de diálogo solicitando el DNI del jugador que se desea eliminar.
     * Si se proporciona un DNI válido, se llama al método eliminarPersona de gestionClub para eliminar al jugador.
     * Finalmente, muestra un mensaje indicando que el jugador fue eliminado (si existía).
     * 
     * @param e evento de acción generado al solicitar la eliminación de un jugador
     */
    

    private void accionEliminarPersona(ActionEvent e) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagenes/Football.png"));
        ImageIcon iconoPequeño = new ImageIcon(originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));

        JTextField dniField = new JTextField();
        Object[] message = {
            "Ingrese el DNI de la persona a eliminar:", dniField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Eliminar Persona",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, iconoPequeño);

        if (option == JOptionPane.OK_OPTION) {
            String dni = dniField.getText().trim();
            if (!dni.isEmpty()) {
                Optional<Persona> personaOpt = gestionClub.obtenerPersonaPorDni(dni);
                if (personaOpt.isPresent()) {
                    Persona persona = personaOpt.get();
                    if (!persona.isEliminado()) {

                        persona.setEliminado(true);  // Marcar como eliminada

                        LocalDate fechaEliminacion = LocalDate.now();  // Guardar fecha de eliminación


                        gestionClub.guardarListaPersonas();  // Método que sobrescribe el archivo completo


                        String registro = persona.getDni() + "," + fechaEliminacion;  //Guardar registro en archivo de eliminados
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eliminados.txt", true))) {
                            writer.write(registro);
                            writer.newLine();
                        } catch (IOException ioEx) {
                            ioEx.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(this,
                                "Persona eliminada correctamente: " + persona.getNombre(),
                                "Éxito", JOptionPane.INFORMATION_MESSAGE, iconoPequeño);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Esta persona ya fue eliminada previamente.",
                                "Aviso", JOptionPane.WARNING_MESSAGE, iconoPequeño);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No se encontró ninguna persona con ese DNI.",
                            "Error", JOptionPane.ERROR_MESSAGE, iconoPequeño);
                }
            }
        }
    }


    /**
     * Permite modificar los datos de un jugador existente en el club.
     * 
     * Primero solicita el DNI del jugador a modificar mediante un cuadro de diálogo.
     * Si el DNI es válido, muestra otro diálogo para ingresar el nuevo nombre y teléfono.
     * Luego, actualiza la información del jugador usando el método modificarPersona de gestionClub.
     * Finalmente, muestra un mensaje confirmando que la modificación fue realizada (si el jugador existía).
     * 
     * @param e evento de acción generado al solicitar la modificación de un jugador
     */


    private void accionModificarPersona(ActionEvent e) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagenes/Football.png"));
        ImageIcon iconoPequeño = new ImageIcon(originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));

        JTextField dniField = new JTextField();
        Object[] dniMessage = { "Ingrese el DNI de la persona a modificar:", dniField };
        int optionDni = JOptionPane.showConfirmDialog(this, dniMessage, "Modificar Persona",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, iconoPequeño);

        if (optionDni == JOptionPane.OK_OPTION) {
            String dni = dniField.getText().trim();
            Optional<Persona> personaOpt = gestionClub.obtenerPersonaPorDni(dni);

            if (personaOpt.isPresent()) {
                Persona persona = personaOpt.get();

                if (persona instanceof Jugador jugador) {
                    modificarFormularioJugador(jugador);
                } else if (persona instanceof Tecnico tecnico) {
                    modificarFormularioTecnico(tecnico);
                } else if (persona instanceof Directivo directivo) {
                    modificarFormularioDirectivo(directivo);
                } else {
                    JOptionPane.showMessageDialog(this, "Tipo de persona desconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna persona con ese DNI.",
                        "Error", JOptionPane.ERROR_MESSAGE, iconoPequeño);
            }
        }
    }


/**
 * Muestra un formulario con los datos actuales de un jugador para permitir su modificación.
 * 
 * El DNI se muestra en el formulario pero no puede ser editado. 
 * Cuando el usuario confirma los cambios, se actualizan los datos del jugador y se guardan en el archivo.
 *
 * @param jugador El jugador cuyos datos se quieren modificar.
 */

    private void modificarFormularioJugador(Jugador jugador) {
        JPanel panel = crearFormularioJugador();

        ((JTextField) panel.getComponent(1)).setText(jugador.getDni());       
        ((JTextField) panel.getComponent(3)).setText(jugador.getNombre());
        ((JTextField) panel.getComponent(5)).setText(jugador.getApellido());
        ((JTextField) panel.getComponent(7)).setText(jugador.getTelefono());
        ((JComboBox<?>) panel.getComponent(9)).setSelectedItem(jugador.getDemarcacion());
        ((JTextField) panel.getComponent(11)).setText(String.valueOf(jugador.getEdad()));
        ((JTextField) panel.getComponent(13)).setText(String.valueOf(jugador.getValorMercado()));
        ((JComboBox<?>) panel.getComponent(15)).setSelectedItem(jugador.getEstado());

        JTextField dniField = (JTextField) panel.getComponent(1);
        dniField.setEditable(false);

        int result = JOptionPane.showConfirmDialog(this, panel, "Modificar Jugador", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            jugador.setNombre(obtenerTextoCampo(panel, 1));
            jugador.setApellido(obtenerTextoCampo(panel, 2));
            jugador.setTelefono(obtenerTextoCampo(panel, 3));
            jugador.setDemarcacion(Demarcacion.valueOf(obtenerTextoCampo(panel, 4)));
            jugador.setEdad(Integer.parseInt(obtenerTextoCampo(panel, 5)));
            jugador.setValorMercado(Double.parseDouble(obtenerTextoCampo(panel, 6)));
            jugador.setEstado(Estado.valueOf(obtenerTextoCampo(panel, 7)));

        guardarListadoEnArchivo("jugadores.txt", gestionClub.listarJugadores());        
        JOptionPane.showMessageDialog(this, "Jugador modificado correctamente.");
        }
    }

    /**
     * Muestra un formulario con los datos actuales de un técnico para permitir su modificación.
     * 
     * El DNI se muestra pero no es editable. Si el usuario confirma los cambios,
     * actualiza los datos del técnico y guarda la lista actualizada en el archivo correspondiente.
     *
     * @param tecnico El técnico cuyos datos se desean modificar.
     */

    private void modificarFormularioTecnico(Tecnico tecnico) {
        JPanel panel = crearFormularioTecnico();

        ((JTextField) panel.getComponent(1)).setText(tecnico.getDni());
        ((JTextField) panel.getComponent(3)).setText(tecnico.getNombre());
        ((JTextField) panel.getComponent(5)).setText(tecnico.getApellido());
        ((JTextField) panel.getComponent(7)).setText(tecnico.getTelefono());
        ((JComboBox<?>) panel.getComponent(9)).setSelectedItem(tecnico.getPuesto());
        ((JComboBox<?>) panel.getComponent(11)).setSelectedItem(tecnico.getEspecialidad());


        JTextField dniField = (JTextField) panel.getComponent(1);
        dniField.setEditable(false);

        int result = JOptionPane.showConfirmDialog(this, panel, "Modificar Técnico", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            tecnico.setNombre(obtenerTextoCampo(panel, 1));
            tecnico.setApellido(obtenerTextoCampo(panel, 2));
            tecnico.setTelefono(obtenerTextoCampo(panel, 3));
            tecnico.setPuesto(Puesto.valueOf(obtenerTextoCampo(panel, 4)));
            tecnico.setEspecialidad(Especialidad.valueOf(obtenerTextoCampo(panel, 5)));

            guardarListadoEnArchivo("tecnicos.txt", gestionClub.listarTecnicos());
            JOptionPane.showMessageDialog(this, "Técnico modificado correctamente.");
        }
    }


    /**
    * Muestra un formulario con los datos actuales de un directivo para permitir su modificación.
    * 
    * El DNI se muestra en el formulario pero no puede ser editado.
    * Si el usuario confirma los cambios, actualiza los datos del directivo
    * y guarda la lista actualizada en el archivo correspondiente.
    * 
    * @param directivo El directivo cuyos datos se quieren modificar.
    */
 
    
    private void modificarFormularioDirectivo(Directivo directivo) {
        JPanel panel = crearFormularioDirectivo();

        ((JTextField) panel.getComponent(1)).setText(directivo.getDni());
        ((JTextField) panel.getComponent(3)).setText(directivo.getNombre());
        ((JTextField) panel.getComponent(5)).setText(directivo.getApellido());
        ((JTextField) panel.getComponent(7)).setText(directivo.getTelefono());
        ((JTextField) panel.getComponent(9)).setText(directivo.getCargo());

        JTextField dniField = (JTextField) panel.getComponent(1);
        dniField.setEditable(false);

        int result = JOptionPane.showConfirmDialog(this, panel, "Modificar Directivo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            directivo.setNombre(obtenerTextoCampo(panel, 1));
            directivo.setApellido(obtenerTextoCampo(panel, 2));
            directivo.setTelefono(obtenerTextoCampo(panel, 3));
            directivo.setCargo(obtenerTextoCampo(panel, 4));

            guardarListadoEnArchivo("directivos.txt", gestionClub.listarDirectivos());
            JOptionPane.showMessageDialog(this, "Directivo modificado correctamente.");
        }
    }

    /**
     * Guarda una lista de objetos en un archivo de texto.
     * 
     * Cada objeto de la lista se guarda en una línea separada, 
     * usando el método {@code toString()} de cada objeto.
     * 
     * @param nombreArchivo Nombre del archivo donde se guardará la lista.
     * @param lista         Lista de objetos a guardar en el archivo.
     */

    private void guardarListadoEnArchivo(String nombreArchivo, List lista) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo));
            for (Object item : lista) {
                writer.println(item.toString());
            }
            writer.close();
           // JOptionPane.showMessageDialog(null, "Listado guardado en: " + nombreArchivo);
        } catch (IOException ex) {
           // JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    
    
    /**
    * Guarda una lista de conceptos de nómina en un archivo de texto.
    * Cada concepto se guarda en una línea con el formato: "codigo, descripcion, importe".
    *
    * @param nombreArchivo el nombre del archivo donde se guardará la información
    * @param lista la lista de objetos ConceptoNomina que se desea guardar
    */

    private void guardarListadoEnArchivoConceptos(String nombreArchivo, List<ConceptoNomina> lista) {
       try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
           for (ConceptoNomina concepto : lista) {
               writer.println(concepto.getCodigo() + ", " + concepto.getDescripcion() + ", " + concepto.getImporte());
           }
       } catch (IOException ex) {
           ex.printStackTrace();
           JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
   }
  
  

    /**
    * Muestra un listado con los jugadores activos del club.
    * 
    * @param e evento de acción generado al solicitar listar jugadores
    */

    private void accionListarJugadores(ActionEvent e) {
        List<Jugador> jugadores = gestionClub.listarJugadores();
        mostrarListado("Jugadores Registrados", jugadores);
    }


    
   /**
    * Muestra un listado con los técnicos activos del club.
    * 
    * @param e evento de acción generado al solicitar listar técnicos
    */ 
    
    private void accionListarTecnicos(ActionEvent e) {
        List<Tecnico> tecnicos = gestionClub.listarTecnicos();
        mostrarListado("Técnicos Activos", tecnicos);
        //guardarListadoEnArchivo("tecnicos.txt", tecnicos);
    }
    
    
    
    /**
     * Muestra un listado con los directivos activos del club.
     * 
     * @param e evento de acción generado al solicitar listar directivos
     */
    
   
    private void accionListarDirectivos(ActionEvent e) {
        List<Directivo> directivos = gestionClub.listarDirectivos();
        mostrarListado("Directivos Activos", directivos);
        //guardarListadoEnArchivo("directivos.txt", directivos);
    }
    
    
    
    /**
    * Muestra un listado con las personas eliminadas del club.
    * 
    * @param e evento de acción generado al solicitar listar personas eliminadas
    */
    
  private void accionListarPersonasEliminadas(ActionEvent e) {
    File archivo = new File("eliminados.txt");

    if (!archivo.exists()) {
        JOptionPane.showMessageDialog(this, "No hay personas eliminadas registradas.", "Personas Eliminadas", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    StringBuilder contenidoFormateado = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length == 2) {
                contenidoFormateado.append("DNI: ").append(partes[0].trim())
                                  .append(" | Fecha Eliminación: ").append(partes[1].trim())
                                  .append("\n");
            } else {
                contenidoFormateado.append(linea).append("\n");
            }
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error leyendo archivo eliminados.txt", "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        return;
    }

    JTextArea textArea = new JTextArea(contenidoFormateado.toString());
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(400, 300));

    JOptionPane.showMessageDialog(this, scrollPane, "Personas Eliminadas", JOptionPane.INFORMATION_MESSAGE);
}



    /**
    * Muestra un listado con los partidos registrados en el club.
    * 
    * @param e evento de acción generado al solicitar listar partidos
    */

    private void accionListarPartidos(ActionEvent e) {
    List<Partido> partidos = gestionClub.listarPartidos();
    mostrarListado("Partidos Registrados", partidos);
    //guardarListadoEnArchivo("partidos.txt", partidos);
    }

    /**
    * Muestra un listado con las facturas emitidas por el club.
    * 
    * @param e evento de acción generado al solicitar listar facturas
    */
    
    private void accionListarFacturas(ActionEvent e) {
    List<Factura> facturas = gestionClub.listarFacturas();
    mostrarListado("Facturas Emitidas", facturas);
    //guardarListadoEnArchivo("facturas.txt", facturas);
    }   

    
    private void accionListarConceptos(ActionEvent e) {
    List<ConceptoNomina> conceptos = gestionClub.listarConceptos();
    mostrarListado("Conceptos de Nómina", conceptos);
    }
 


    
    
    
/**
 * Muestra un cuadro de diálogo para crear una nómina solicitando al usuario
 * el DNI, el mes y el año correspondientes.
 * 
 * El diálogo utiliza un icono pequeño de nómina y campos de texto para
 * que el usuario introduzca los datos necesarios.
 *
 * Si el usuario confirma y los campos están correctamente rellenados,
 * intenta convertir el año a entero y llama al método para crear la nómina.
 * En caso de que el año no sea válido, muestra un mensaje de error.
 * 
 * @param e evento de acción generado al pulsar el botón para crear nómina
 */
    
private void accionCrearNomina(ActionEvent e) {
    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagenes/nomina.png"));
    ImageIcon iconoPequeño = new ImageIcon(originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));

    JTextField dniField = new JTextField();
    JTextField mesField = new JTextField();
    JTextField anioField = new JTextField();
    JTextField codigosConceptosField = new JTextField();

    Object[] message = {
        "DNI del empleado:", dniField,
        "Mes de la nómina:", mesField,
        "Año de la nómina:", anioField,
        "Códigos de conceptos (separados por coma):", codigosConceptosField
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Crear Nómina",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, iconoPequeño);

    if (option == JOptionPane.OK_OPTION) {
        String dni = dniField.getText().trim();
        String mes = mesField.getText().trim();
        String anioStr = anioField.getText().trim();
        String codigosTexto = codigosConceptosField.getText().trim();

        if (!dni.isEmpty() && !mes.isEmpty() && !anioStr.isEmpty()) {
            try {
                int anio = Integer.parseInt(anioStr);
                Optional<Persona> personaOpt = gestionClub.buscarPersona(dni);

                if (personaOpt.isPresent()) {
                    Persona persona = personaOpt.get();
                    Nomina nomina = new Nomina(mes, anio);
                    nomina.empleado = persona;

                    List<ConceptoNomina> todosConceptos = gestionClub.listarConceptos();
                    Set<String> codigosBuscados = Arrays.stream(codigosTexto.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toSet());

                    for (ConceptoNomina concepto : todosConceptos) {
                        if (codigosBuscados.contains(concepto.getCodigo())) {
                            nomina.agregarConcepto(concepto);
                        }
                    }

                    persona.agregarNomina(nomina);


                    guardarNominaEnArchivo(nomina);

                    JOptionPane.showMessageDialog(this, "Nómina creada con los conceptos indicados.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró persona con ese DNI.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Año inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

/**
 * Acción que se ejecuta al solicitar listar las nóminas.
 * Lee el archivo "nominas.txt" línea por línea y muestra el contenido en una ventana de listado.
 * Si el archivo no existe, muestra un mensaje informativo.
 * Si ocurre un error al leer el archivo, muestra un mensaje de error.
 *
 * @param e evento de acción que dispara este método
 */

private void accionListarNominas(ActionEvent e) {
    List<String> listadoNominas = new ArrayList<>();

    File archivo = new File("nominas.txt");
    if (!archivo.exists()) {
        JOptionPane.showMessageDialog(this, "No hay nóminas guardadas.", "Información", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            listadoNominas.add(linea);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al leer el archivo de nóminas: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    mostrarListado("Listado de Nóminas", listadoNominas);
}


/**
 * Guarda una nómina en el archivo "nominas.txt" en modo append (agregando al final).
 * El formato guardado es: dni,mes,anio,codigo1,codigo2,...
 *
 * @param nomina objeto Nomina que se desea guardar en el archivo.
 */

private void guardarNominaEnArchivo(Nomina nomina) {
    String nombreArchivo = "nominas.txt";
    try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, true))) {
        String dni = nomina.empleado.getDni();
        String mes = nomina.mes;
        int anio = nomina.anio;

        StringBuilder codigos = new StringBuilder();
        for (ConceptoNomina concepto : nomina.conceptos) {
            codigos.append(concepto.getCodigo()).append(",");
        }

        if (codigos.length() > 0) {
            codigos.setLength(codigos.length() - 1);
        }

        writer.println(dni + "," + mes + "," + anio + "," + codigos.toString());
        System.out.println("[DEBUG] Nómina guardada en nominas.txt");

    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al guardar nómina en archivo: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}


/**
 * Muestra un diálogo para crear una nueva factura solicitando los datos necesarios al usuario.
 * 
 * El diálogo incluye campos para el código de la factura, cantidad, fecha de pago,
 * nombre del cliente y CIF del cliente.
 * 
 * Si el usuario confirma y todos los campos están correctamente rellenados,
 * intenta convertir la cantidad a un valor numérico y llama al método para crear la factura.
 * 
 * Se muestran mensajes de éxito o error según corresponda. 
 * 
 * Utiliza un icono pequeño de factura en el diálogo.
 * 
 * @param e evento de acción generado al pulsar el botón "Crear Factura"
 */

private void accionCrearFactura(ActionEvent e) {
    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagenes/factura.png"));
    ImageIcon iconoPequeño = new ImageIcon(originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));

    JTextField codigoField = new JTextField();
    JTextField cantidadField = new JTextField();
    JTextField fechaPagoField = new JTextField();
    JTextField clienteNombreField = new JTextField();
    JTextField clienteCIFField = new JTextField();

    Object[] message = {
        "Código de la factura:", codigoField,
        "Cantidad:", cantidadField,
        "Fecha de pago:", fechaPagoField,
        "Nombre del cliente:", clienteNombreField,
        "CIF del cliente:", clienteCIFField
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Crear Factura",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, iconoPequeño);

    if (option == JOptionPane.OK_OPTION) {
        String codigo = codigoField.getText();
        String cantidadStr = cantidadField.getText();
        String fechaPago = fechaPagoField.getText();
        String clienteNombre = clienteNombreField.getText();
        String clienteCIF = clienteCIFField.getText();

        if (codigo != null && !codigo.trim().isEmpty() &&
            cantidadStr != null && !cantidadStr.trim().isEmpty() &&
            fechaPago != null && !fechaPago.trim().isEmpty() &&
            clienteNombre != null && !clienteNombre.trim().isEmpty() &&
            clienteCIF != null && !clienteCIF.trim().isEmpty()) {

            try {
                double cantidad = Double.parseDouble(cantidadStr.trim());
                gestionClub.crearFactura(cantidad, fechaPago, new gestion.Cliente(clienteCIF, clienteNombre));
                JOptionPane.showMessageDialog(this, "Factura creada exitosamente.",
                        "Resultado", JOptionPane.INFORMATION_MESSAGE, iconoPequeño);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida.",
                        "Error", JOptionPane.ERROR_MESSAGE, iconoPequeño);
            }
        }
    }
}


    /**
     * Muestra una lista en un cuadro de diálogo con scroll.
     *
     * @param titulo Título del cuadro de diálogo.
     * @param lista  Lista de objetos a mostrar.
     */

    private void mostrarListado(String titulo, java.util.List<?> lista) {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para mostrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder mensaje = new StringBuilder(titulo + ":\n\n");
            lista.forEach(item -> mensaje.append(item).append("\n"));

            JTextArea textArea = new JTextArea(mensaje.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
        }
    }

 /**
 * Maneja la acción de registrar un nuevo partido en el sistema.
 * 
 * Este método despliega un formulario para ingresar los datos de un partido,
 * incluyendo el rival, fecha, goles, si el equipo fue local, y los jugadores
 * que participaron (identificados por su DNI).
 * 
 * Luego de validar los datos ingresados, se crea un objeto @code Partido y se
 * guarda en el sistema a través del objeto gestionClub. También se actualizan
 * los registros de los jugadores que participaron en el partido.
 * 
 * En caso de errores (como formato de fecha inválido, goles no numéricos, jugadores
 * inexistentes, eliminados o lesionados), se muestra un mensaje de error apropiado
 * al usuario.
 *
 * @param e el evento de acción al presionar el botón.
 */
  
  private void accionRegistrarPartido(ActionEvent e) {

    JPanel panel = new JPanel(new GridLayout(0,2,5,5));
    JTextField rivalField    = new JTextField();
    JTextField fechaField    = new JTextField();
    JTextField golesLField   = new JTextField();
    JTextField golesVField   = new JTextField();
    JCheckBox  localCheck    = new JCheckBox("¿Local?", true);
    JTextField jugadoresField= new JTextField(); 
    panel.add(new JLabel("Rival:")); panel.add(rivalField);
    panel.add(new JLabel("Fecha (dd/MM/yyyy):")); panel.add(fechaField);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);

    panel.add(new JLabel("Goles Local:")); panel.add(golesLField);
    panel.add(new JLabel("Goles Visitante:")); panel.add(golesVField);
    panel.add(new JLabel("Local?"));panel.add(localCheck);
    panel.add(new JLabel("DNIs jugadores (coma):")); panel.add(jugadoresField);

    int res = JOptionPane.showConfirmDialog(this, panel, 
            "Registrar Partido", JOptionPane.OK_CANCEL_OPTION);
    if (res != JOptionPane.OK_OPTION) return;

    try {
        String rival = rivalField.getText().trim();
        String fechaTexto = fechaField.getText().trim();
        Date fecha = sdf.parse(fechaTexto);             
        int golesLocal    = Integer.parseInt(golesLField.getText().trim());
        int golesVisitante= Integer.parseInt(golesVField.getText().trim());
        boolean esLocal   = localCheck.isSelected();


        List<control.Jugador> listaJug = new ArrayList<>();
        String[] dnis = jugadoresField.getText().split(",");
        for (String dni: dnis) {
            dni = dni.trim();
           Jugador jugador = gestionClub.buscarJugadorPorDni(dni);
            if (jugador == null) {
                throw new IllegalArgumentException("Jugador con DNI " + dni + " no encontrado.");
            }
            if (jugador.isEliminado()) {
                throw new IllegalArgumentException("Jugador " + dni + " está eliminado.");
            }
            if (jugador.getEstado() == Estado.LESIONADO) {
                throw new IllegalArgumentException("Jugador " + dni + " está lesionado.");
            }
            listaJug.add(jugador);

          
        }

      
        Partido partido = new Partido(rival, fecha, golesLocal, golesVisitante, esLocal, listaJug);
        gestionClub.añadirPartido(partido);

     
        gestionClub.guardarPartidosEnArchivo(partido);
        gestionClub.guardarJugadoresQueHanJugado(listaJug);

        JOptionPane.showMessageDialog(this, 
          "Partido ID " + partido.getId() + " registrado correctamente.");

    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this, "Goles inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException iae) {
        JOptionPane.showMessageDialog(this, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

  
 /**
 * Muestra las estadísticas del club en un cuadro de diálogo.
 *
 * @param e el evento que desencadena la acción.
 */
  
  

  private void accionMostrarEstadisticas(ActionEvent e) {
    String estadisticas = gestionClub.generarEstadisticasClub();
    JOptionPane.showMessageDialog(this, estadisticas, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
}


    
    /**
     * Método principal para lanzar la aplicación.
     *
     */
    
    public static void main(String[] args) {
             SwingUtilities.invokeLater(VentanaPrincipal::new);
    } 

  
}
