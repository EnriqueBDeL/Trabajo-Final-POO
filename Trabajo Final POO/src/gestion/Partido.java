package gestion;

import java.util.List;

import control.Jugador;
import java.util.Date;

/**
 * Representa un partido de fútbol, incluyendo información sobre los equipos, goles y jugadores participantes.
 */

public class Partido {
    
    /**
     * La lista de jugadores que participaron en el partido.
     */
    private List<Jugador> jugadoresParticipantes;
    
    /**
     * El nombre del equipo rival contra el cual se jugó el partido.
     */
    private String equipoRival;
    
    /**
     * La fecha en la que se jugó el partido.
     */
    private Date fecha;
    
    /**
     * La cantidad de goles anotados por el equipo local.
     */
    private int golesLocal;
    
    /**
     * La cantidad de goles anotados por el equipo visitante.
     */
    private int golesVisitante;
    
    /**
     * Indica si el partido se jugó en casa (como equipo local) o fuera (como visitante).
     */
    private boolean esLocal;

    /*id del partido*/
    private int id; 
    
    /*contador para calcular id */
    private static int contadorId = 1;
    /**
     * Constructor para crear un nuevo partido con todos los detalles especificados.
     * 
     * @param equipoRival El nombre del equipo rival.
     * @param fecha La fecha en la que se jugó el partido.
     * @param golesLocal La cantidad de goles anotados por el equipo local.
     * @param golesVisitante La cantidad de goles anotados por el equipo visitante.
     * @param esLocal Indica si el equipo jugó como local o visitante.
     * @param jugadoresParticipantes La lista de jugadores que participaron en el partido.
     */
    public Partido(String equipoRival, Date fecha, int golesLocal, int golesVisitante, boolean esLocal, List<Jugador> jugadoresParticipantes) {
        this.id = contadorId++; 
        this.equipoRival = equipoRival;
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.esLocal = esLocal;
        this.jugadoresParticipantes = jugadoresParticipantes;
    }

    /**
    * Obtiene la lista de jugadores que participaron en el partido.
    * @return Lista de jugadores participantes.
    */
   public List<Jugador> getJugadoresParticipantes() {
       return jugadoresParticipantes;
   }

   /**
    * Establece la lista de jugadores que participaron en el partido.
    * @param jugadoresParticipantes Lista de jugadores participantes.
    */
   public void setJugadoresParticipantes(List<Jugador> jugadoresParticipantes) {
       this.jugadoresParticipantes = jugadoresParticipantes;
   }

   /**
    * Obtiene el nombre del equipo rival.
    * @return Nombre del equipo rival.
    */
   public String getEquipoRival() {
       return equipoRival;
   }

   /**
    * Establece el nombre del equipo rival.
    * @param equipoRival Nombre del equipo rival.
    */
   public void setEquipoRival(String equipoRival) {
       this.equipoRival = equipoRival;
   }

   /**
    * Obtiene la fecha del partido.
    * @return Fecha del partido.
    */
   public Date getFecha() {
       return fecha;
   }

   /**
    * Establece la fecha del partido.
    * @param fecha Fecha del partido.
    */
   public void setFecha(Date fecha) {
       this.fecha = fecha;
   }

   /**
    * Obtiene los goles anotados por el equipo local.
    * @return Número de goles del equipo local.
    */
   public int getGolesLocal() {
       return golesLocal;
   }

   /**
    * Establece los goles anotados por el equipo local.
    * @param golesLocal Número de goles del equipo local.
    */
   public void setGolesLocal(int golesLocal) {
       this.golesLocal = golesLocal;
   }

   /**
    * Obtiene los goles anotados por el equipo visitante.
    * @return Número de goles del equipo visitante.
    */
   public int getGolesVisitante() {
       return golesVisitante;
   }

   /**
    * Establece los goles anotados por el equipo visitante.
    * @param golesVisitante Número de goles del equipo visitante.
    */
   public void setGolesVisitante(int golesVisitante) {
       this.golesVisitante = golesVisitante;
   }

   /**
    * Indica si el equipo local es el equipo propio.
    * @return true si es local, false en caso contrario.
    */
   public boolean isEsLocal() {
       return esLocal;
   }

   /**
    * Establece si el equipo local es el equipo propio.
    * @param esLocal true si es local, false en caso contrario.
    */
   public void setEsLocal(boolean esLocal) {
       this.esLocal = esLocal;
   }

   /**
    * Obtiene el identificador único del partido.
    * @return ID del partido.
    */
   public int getId() {
       return id;
   }

   /**
    * Establece el identificador único del partido.
    * @param id ID del partido.
    */
   public void setId(int id) {
       this.id = id;
   }

    /**
     * Devuelve una representación en formato de texto del partido, incluyendo el nombre del equipo rival,
     * la fecha, el resultado, si el equipo jugó como local o visitante, y los jugadores que participaron.
     * 
     * @return Una cadena que representa al partido.
     */
    @Override
    public String toString() {
        return "Partido contra " + equipoRival + " el " + fecha + 
               " | Resultado: " + golesLocal + "-" + golesVisitante + 
               (esLocal ? " (Local)" : " (Visitante)") +
               ", Jugadores: " + jugadoresParticipantes;
    }
}
