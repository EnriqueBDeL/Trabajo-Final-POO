package gestion;

import control.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Clase que centraliza la lógica de gestión del club, incluyendo alta, baja y modificación
 * de personal, registro de partidos, etc.
 */

public class GestionClub {
    
    
    private static GestionClub instancia;

    private List<Jugador> jugadores;
    private List<Tecnico> tecnicos;
    private List<Directivo> directivos;
    private List<Partido> partidos;
    private List<Factura> facturas;
    private Map<String, Persona> personasMap;
    private List<Jugador> jugadoresEliminados = new ArrayList<>();
    private List<ConceptoNomina> conceptosNomina = new ArrayList<>();



    /**
    * Constructor de la clase GestionClub.
    * 
    * Inicializa las listas de jugadores, técnicos, directivos, partidos y facturas,
    * así como el mapa de personas. También añade algunos datos de ejemplo
    * para comenzar con información cargada.
    */

    private GestionClub() {
        this.jugadores = new ArrayList<>();
        this.tecnicos = new ArrayList<>();
        this.directivos = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.personasMap = new HashMap<>(); 
        System.out.println("[DEBUG] Inicializada Gestión del Club.");
    
        //jugadores.add(new Jugador(DELANTERO, 36, 50.0, DISPONIBLE, "12345678A", "Lionel", "Messi", "600123456"));
        //tecnicos.add(new Tecnico(ENTRENADOR, PRINCIPAL, "22334455B", "Pep", "Guardiola", "600654321"));
        //directivos.add(new Directivo("Presidente", "33445566C", "Joan", "Laporta", "600987654"));
        //partidos.add(new Partido("Real Madrid", "2024-05-15", 3, 2, true, jugadores));
        //facturas.add(new Factura(50000.0, "2024-08-13", new Cliente("B12345678", "Proveedor de Equipación")));
    }

    
     /**
     * Devuelve la instancia única de la clase GestionClub.
     * 
     * Si aún no existe una instancia, se crea una nueva. Este método garantiza
     * que siempre se utilice una única instancia de GestionClub durante la
     * ejecución del programa (patrón Singleton).
     *
     * @return la instancia única de GestionClub.
     */
    
       public static GestionClub getInstancia() {
        if (instancia == null) {
            instancia = new GestionClub();
        }
        return instancia;
    }
    
       
       
    /**
    * Comprueba si un DNI ya existe en el mapa de personas.
    *
    * @param dni el DNI a comprobar.
    * @return true si el DNI ya está registrado, false si no.
    */
    
    private boolean dniExiste(String dni) {   //creamos método para que no se pueda repetir dni
    return personasMap.containsKey(dni);
    }
    
    /**
    * Da de alta un nuevo jugador si su DNI no existe ya.
    *
    * @param jugador el jugador que se quiere añadir.
    * @return true si el DNI ya existía y no se pudo añadir, false si se añadió correctamente.
    */

    
    public boolean altaJugador(Jugador jugador) {   //queremos que cada jugador tenga su propio dni
         if (dniExiste(jugador.getDni())) {
       // System.out.println("[ERROR] Ya existe una persona con el DNI=" + jugador.getDni());
       JOptionPane.showMessageDialog(null, "Ya existe una persona con el DNI " + jugador.getDni(), "Error", JOptionPane.ERROR_MESSAGE);
       return true;
    }else{
          personasMap.put(jugador.getDni(), jugador);   //guardar en el mapa y en la lista de los jugadores
        jugadores.add(jugador);
        System.out.println("[DEBUG] Alta de Jugador: " + jugador);
    }    
        return false;
    }

    
    /**
    * Da de alta un nuevo técnico si su DNI no existe ya.
    *
    * @param tecnico el técnico que se quiere añadir.
    */
    
    public void altaTecnico(Tecnico tecnico) {
        if (dniExiste(tecnico.getDni())) {
        System.out.println("[ERROR] Ya existe una persona con el DNI=" + tecnico.getDni());
        return;
    }
        personasMap.put(tecnico.getDni(), tecnico);
        tecnicos.add(tecnico);
        System.out.println("[DEBUG] Alta de Técnico: " + tecnico);
    }

    
    /**
    * Da de alta un nuevo directivo si su DNI no existe ya.
    *
    * @param directivo el directivo que se quiere añadir.
    */

    public void altaDirectivo(Directivo directivo) {
        if (dniExiste(directivo.getDni())) {
        System.out.println("[ERROR] Ya existe una persona con el DNI=" + directivo.getDni());
        return;
    }
        personasMap.put(directivo.getDni(), directivo);
        directivos.add(directivo);
        System.out.println("[DEBUG] Alta de Directivo: " + directivo);
    }
    
   /**
 * Devuelve un {@code Optional<Persona>} correspondiente al DNI especificado.
 * Si no existe ninguna persona con ese DNI, se devuelve un {@code Optional.empty()}.
 *
 * @param dni el DNI de la persona que se desea buscar.
 * @return un {@code Optional<Persona>} con la persona encontrada, o vacío si no existe.
 */

    
  public Optional<Persona> obtenerPersonaPorDni(String dni) {
    return Optional.ofNullable(personasMap.get(dni));
}

    
  
  
    
//persona
    
    /**
    * Elimina a una persona según su DNI.
    *
    * @param dni el DNI de la persona a eliminar.
    */
    
public void eliminarPersona(String dni) {
    Optional<Persona> persona = buscarPersona(dni);
    if (persona.isPresent()) {
        Persona p = persona.get();
        if (!p.isEliminado()) {
            p.eliminar();  // marca como eliminada y registra la fecha
            System.out.println("[DEBUG] Persona eliminada: " + p);
        } else {
            System.out.println("[DEBUG] Persona ya estaba eliminada.");
        }
    } else {
        System.out.println("[DEBUG] No se encontró persona con DNI: " + dni);
    }
}



    /**
    * Modifica el nombre y teléfono de una persona según su DNI.
    *
    * @param dni el DNI de la persona a modificar.
    * @param nuevoNombre el nuevo nombre a asignar.
    * @param nuevoTelefono el nuevo teléfono a asignar.
    */
    
    public void modificarPersona(String dni, String nuevoNombre, String nuevoTelefono) {
        Optional<Persona> persona = buscarPersona(dni);
        if (persona.isPresent()) {
            persona.get().setNombre(nuevoNombre);
            persona.get().setTelefono(nuevoTelefono);
            System.out.println("[DEBUG] Modificación de persona: " + persona.get());
        } else {
            System.out.println("[DEBUG] No se encontró persona con DNI: " + dni);
        }
    }

   /**
 * Busca persona y Ddvuelve un {@code Optional<Persona>} correspondiente al DNI especificado.
 *
 * @param dni el DNI de la persona.
 * @return un {@code Optional<Persona>} con la persona si se encuentra, o vacío si no.
 */

    
    public Optional<Persona> buscarPersona(String dni) {
    return Optional.ofNullable(personasMap.get(dni));
}

    
    


 
    

    
    /**
    * Crea una nómina para una persona según su DNI.
    *
    * @param dni el dni de la persona.
    * @param mes el mes de la nómina.
    * @param anio el año de la nómina.
    */
    
  public void crearNomina(String dni, String mes, int anio) {
    Optional<Persona> persona = buscarPersona(dni);
    if (persona.isPresent()) {
        Nomina nomina = new Nomina(mes, anio);
        nomina.empleado = persona.get();  // Asignar el empleado a la nómina

        for (ConceptoNomina concepto : listarConceptos()) { // Agregar todos los conceptos actuales 

            nomina.agregarConcepto(concepto);
        }

    
        persona.get().agregarNomina(nomina);     // Asociar la nómina al empleado

        System.out.println("[DEBUG] Nómina creada para DNI=" + dni);
    } else {
        System.out.println("[DEBUG] No se encontró persona con DNI: " + dni + " para crear nómina.");
    }
}

        
//factura
        
    /**
     * Crea una factura y la añade a la lista de facturas.
     *
     * @param cantidad el importe de la factura.
     * @param fechaPago la fecha de pago de la factura.
     * @param cliente el cliente asociado a la factura.
     */
        
        public void crearFactura(double cantidad, String fechaPago, Cliente cliente) {
        Factura factura = new Factura(cantidad, fechaPago, cliente);
        facturas.add(factura);
        System.out.println("[DEBUG] Factura creada: " + factura);
    }

    /** 
    * Devuelve una copia de la lista de jugadores.
    *
    * @return lista de jugadores.
    */
          public List<Jugador> listarJugadores() {
        return new ArrayList<>(jugadores); // Devuelve una copia para evitar modificaciones externas
    }
          
    /**
    * Devuelve una copia de la lista de técnicos.
    *
    * @return lista de técnicos.
    */
          
        public List<Tecnico> listarTecnicos() {
        return new ArrayList<>(tecnicos);
    }
        
        
    /**
    * Devuelve una copia de la lista de directivos.
    *
    * @return lista de directivos.
    */ 
        
         public List<Directivo> listarDirectivos() {
        return new ArrayList<>(directivos);
    }
       
 
         
    /**
    * Devuelve una copia de la lista de partidos.
    *
    * @return lista de partidos.
    */  
         public List<Partido> listarPartidos() {
        return new ArrayList<>(partidos);
    }
          
         
   /**
    * Guarda en un archivo la información de todas las personas registradas.
    * Los datos incluyen DNI, nombre, apellido, teléfono y estado de eliminación.
    */      
         
   public void guardarListaPersonas() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
        for (Persona persona : personasMap.values()) {
            writer.write(persona.getDni() + "," +
                         persona.getNombre() + "," +
                         persona.getApellido() + "," +
                         persona.getTelefono() + "," +
                         persona.isEliminado());  
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
         
         
    /**
     * Añade un partido a la lista de partidos del club.
     *
     * @param p el partido a añadir.
     */

   public void añadirPartido(Partido p) {
         partidos.add(p);
    }

    /**
    * Guarda la información de un partido en el archivo "partidos.txt".
    *
    * @param partido el partido a guardar.
    */
      
      public void guardarPartidosEnArchivo(Partido partido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("partidos.txt", true))) {
            writer.write(partido.toString());
            writer.newLine(); 
        } catch (IOException e) {
        System.out.println("Error al guardar el partido en archivo.");
    }
}



   /**
   * Genera un archivo con los datos de los jugadores disponibles (nombre, DNI y demarcación).
   */     
      
    public void generarArchivoJugadores() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("jugadores_disponibles.txt"))) {
        for (Jugador j : jugadores) {
            String linea = j.getNombre() + "," + j.getDni() + "," + j.getDemarcacion();
            writer.write(linea);
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al generar el archivo de jugadores disponibles.");
        e.printStackTrace();
    }
}

    
    
/**
 * Busca un jugador activo y no lesionado por su DNI.
 *
 * @param dni el DNI del jugador a buscar.
 * @return el jugador encontrado o  null si no existe o no está disponible.
 */
    
public Jugador buscarJugadorPorDni(String dni) {
    for (Jugador j : jugadores) {
        if (j.getDni().equals(dni) && !jugadoresEliminados.contains(j) && j.getEstado() != Estado.LESIONADO) {
            return j;
        }
    }
    return null;
}




/**
 * Guarda en un archivo los DNI de los jugadores que han participado en un partido.
 *
 * @param jugadores lista de jugadores que han jugado.
 */

    public void guardarJugadoresQueHanJugado(List<Jugador> jugadores) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("jugadores_que_han_jugado.txt", true))) {
        for (Jugador j : jugadores) {
            writer.write(j.getDni());  
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar jugadores que han jugado.");
    }
}

    
    
    
 /**
 * Genera un resumen de estadísticas de los partidos jugados por el club.
 *
 * @return una cadena con la cantidad de partidos ganados, perdidos y empatados.
 */
    
public String generarEstadisticasClub() {
    int ganados = 0;
    int perdidos = 0;
    int empatados = 0;

    for (Partido p : partidos) {
        if (p.isEsLocal()) {
            if (p.getGolesLocal() > p.getGolesVisitante()) ganados++;
            else if (p.getGolesLocal() < p.getGolesVisitante()) perdidos++;
            else empatados++;
        } else {
            if (p.getGolesVisitante() > p.getGolesLocal()) ganados++;
            else if (p.getGolesVisitante() < p.getGolesLocal()) perdidos++;
            else empatados++;
        }
    }

    return "Estadísticas del Club:\n" +
           "Partidos Ganados: " + ganados + "\n" +
           "Partidos Perdidos: " + perdidos + "\n" +
           "Partidos Empatados: " + empatados;
}


         
    /**
    * Devuelve una copia de la lista de facturas.
    *
    * @return lista de facturas.
    */ 

    public List<Factura> listarFacturas() {
        return new ArrayList<>(facturas);
    }

    /**
    * Da de alta un nuevo concepto de nómina y lo añade a la lista de conceptos existentes.
    *
    * @param concepto El objeto ConceptoNomina que se desea añadir.
    */
    
    public void altaConcepto(ConceptoNomina concepto) {
        conceptosNomina.add(concepto);
        System.out.println("[DEBUG] Concepto añadido: " + concepto);
    }

    
    /**
    * Devuelve una lista con todos los conceptos de nómina registrados en el sistema.
    * 
    * @return Una nueva lista que contiene todos los objetos ConceptoNomina.
    */
    
    public List<ConceptoNomina> listarConceptos() {
        return new ArrayList<>(conceptosNomina);
    }

    /**
    * Carga los conceptos de nómina desde un archivo de texto especificado.
    * 
    * Cada línea del archivo debe contener un concepto en el formato: codigo, descripcion, importe.
    * Las líneas vacías se ignoran. Si alguna línea no tiene el formato correcto o contiene un importe inválido,
    * se muestra una advertencia por consola y se omite esa línea.
    *
    * @param nombreArchivo El nombre del archivo desde el cual se cargarán los conceptos.
    */

   public void cargarConceptosDesdeArchivo(String nombreArchivo) {
       conceptosNomina.clear();
       try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
           String linea;
           while ((linea = reader.readLine()) != null) {
               if (linea.trim().isEmpty()) continue; 
               String[] partes = linea.split(",\\s*");
               if (partes.length != 3) {
                   System.out.println("[WARN] Línea inválida en archivo conceptos: " + linea);
                   continue;
               }
               try {
                   String codigo = partes[0].trim();
                   String descripcion = partes[1].trim();
                   float importe = Float.parseFloat(partes[2].trim());

                   ConceptoNomina concepto = new ConceptoNomina(codigo, descripcion, importe);
                   conceptosNomina.add(concepto);
               } catch (NumberFormatException e) {
                   System.out.println("[WARN] Importe inválido en línea: " + linea);
               }
           }
           System.out.println("[DEBUG] Conceptos cargados desde archivo: " + nombreArchivo);
       } catch (IOException e) {
           e.printStackTrace();
           System.out.println("[ERROR] Error cargando conceptos desde archivo.");
       }
   }
    

   /**
    * Guarda una lista de nóminas en un archivo de texto.
    * 
    * Cada línea del archivo contiene: dni, mes, año y los códigos de conceptos separados por comas.
    *
    * @param nombreArchivo Nombre del archivo donde se guardarán las nóminas.
    * @param nominas Lista de nóminas a guardar.
    */
   
    private void guardarNominasEnArchivo(String nombreArchivo, List<Nomina> nominas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Nomina nomina : nominas) {
                String linea = nomina.empleado.getDni() + "," + nomina.mes + "," + nomina.anio + "," + nomina.getCodigosConceptosComoString();
                writer.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
    * Carga nóminas desde un archivo de texto y las asigna a las personas correspondientes.
    *
    * Cada línea debe tener al menos: dni, mes, año y uno o más códigos de conceptos separados por comas.
    * Busca la persona por DNI y añade la nómina con los conceptos indicados.
    *
    * @param nombreArchivo Nombre del archivo desde donde se leerán las nóminas.
    * @param conceptosDisponibles Lista de conceptos disponibles para buscar y asignar por código.
    */

    private void cargarNominasDesdeArchivo(String nombreArchivo, List<ConceptoNomina> conceptosDisponibles) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length < 4) continue; 

                String dni = partes[0].trim();
                String mes = partes[1].trim();
                int anio = Integer.parseInt(partes[2].trim());

                Optional<Persona> personaOpt = buscarPersona(dni); 
                if (personaOpt.isEmpty()) continue;

                Persona persona = personaOpt.get();
                Nomina nomina = new Nomina(mes, anio);
                nomina.empleado = persona;

                List<String> codigos = Arrays.asList(partes).subList(3, partes.length);
                nomina.agregarConceptosPorCodigo(codigos, conceptosDisponibles);

                persona.agregarNomina(nomina);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
