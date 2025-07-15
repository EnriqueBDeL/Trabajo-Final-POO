package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import gestion.Nomina;

/**
 * Representa a una persona que forma parte de un club de fútbol (puede ser jugador, directivo, técnico, etc.).
 * Contiene información básica como el DNI, nombre, teléfono y estado de eliminación.
 */

public abstract class Persona {

    protected String dni;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected boolean eliminado;
    protected Date fechaEliminacion;
    protected List<Nomina> nominas;
 

 /**
 * Constructor de la clase Persona.
 *
 * Inicializa dni, nombre, apellido y teléfono con los valores dados,
 * además establece eliminado como false y crea una lista vacía para nóminas.
 *
 * @param dni       DNI de la persona.
 * @param nombre    Nombre de la persona.
 * @param apellido  Apellido de la persona.
 * @param telefono  Teléfono de contacto.
 */

    public Persona(String dni, String nombre, String apellido, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.eliminado = false;
        this.nominas = new ArrayList<>();
    }

    
    /**
    * Devuelve el DNI de la persona.
    * 
    * @return el DNI como cadena de texto.
    */
    public String getDni() {
        return dni;
    }

    
   
    /**
    * Devuelve el nombre de la persona.
    * 
    * @return el nombre como cadena de texto.
    */
    
    public String getNombre() {
        return nombre;
    }

    
    /**
     * Devuelve el apellido de la persona.
     * 
     * @return el apellido como cadena de texto.
     */

    public String getApellido() {
        return apellido;
    }

    
    
    /**
    * Establece el apellido de la persona.
    * 
    * @param apellido el nuevo apellido a asignar.
    */
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
    /**
     * Establece el nombre completo de la persona.
     * 
     * @param nombre El nuevo nombre completo de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de teléfono de la persona.
     * 
     * @return El número de teléfono de la persona.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono de la persona.
     * 
     * @param telefono El nuevo número de teléfono de la persona.
     */
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    /**
     * Verifica si la persona ha sido eliminada.
     * 
     * @return {@code true} si la persona está eliminada, {@code false} en caso contrario.
     */
    
    public boolean isEliminado() {
        return eliminado;
    }
    
    
   /**
    * Cambia el valor de eliminado.
    *
    * @param eliminado true si está eliminado, false si no.
    */
    
    public void setEliminado(boolean eliminado ) {
        this.eliminado = eliminado;
    }
    
    
    /**
     * Obtiene la fecha en la que la persona fue eliminada.
     * 
     * @return La fecha de eliminación, o NULL si la persona no ha sido eliminada.
     */
    
    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }
    
    
    /**
     * Cambia la fecha de eliminación.
     *
     * @param fecha la nueva fecha de eliminación.
     */
    
    public void setFechaEliminacion(Date fecha) {
        this.fechaEliminacion = fecha;
    }
    
    
   /**
 * Marca la persona como eliminada y registra la fecha actual de eliminación.
 */

    public void eliminar() {
        this.eliminado = true;
        this.fechaEliminacion = new Date();
        System.out.println("[DEBUG] Persona eliminada: DNI=" + dni + ", Fecha=" + fechaEliminacion);

    }
 
    
    /**
     * Agrega una nómina a la lista de nóminas.
     *
     * @param nomina la nómina que se quiere agregar.
     */
    
    public void agregarNomina(Nomina nomina) {
        this.nominas.add(nomina);
    }
  
    
    /**
     * Método abstracto para modificar los datos.
     * 
     * Las clases hijas deben implementar este método con o sin modificaciones.
     */
    public abstract void modificarDatos();
  
    
 
 /**
 * Devuelve una representación en forma de cadena de texto de la persona.
 * Incluye el DNI, nombre, apellido y teléfono. Si la persona está marcada como eliminada,
 * se antepone la etiqueta "[ELIMINADO]".
 *
 * @return una cadena que representa la información básica de la persona.
 */   
    @Override
    public String toString() {
        String pre = eliminado ? "[ELIMINADO] " : "";
        return pre + "DNI: " + dni + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Telefono: " + telefono;
    }

}
