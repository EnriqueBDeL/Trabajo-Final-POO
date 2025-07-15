package control;

import javax.swing.JOptionPane;


/**
 * Representa a un jugador de fútbol, heredando de la clase Persona.
 * Un jugador tiene atributos como su demarcación, edad, valor de mercado y disponibilidad.
 */


public class Jugador extends Persona {
    
    private Demarcacion demarcacion;
    private int edad;
    private double valorMercado;
    private Estado estado; 
   
    
    /**
     * Constructor para crear un nuevo jugador.
     * 
     * @param dni El DNI del jugador.
     * @param nombre El nombre del jugador.
     * @param telefono El número de teléfono del jugador.
     * @param demarcacion La posición del jugador en el campo.
     * @param edad La edad del jugador.
     * @param valorMercado El valor de mercado del jugador.
     * @param apellido
     * @param estado
     */
   
    
    public Jugador(Demarcacion demarcacion, int edad, double valorMercado, Estado estado, String dni, String nombre, String apellido, String telefono) {
        super(dni, nombre, apellido, telefono);
        this.demarcacion = demarcacion;
        this.edad = edad;
        this.valorMercado = valorMercado;
        this.estado = estado;
    }

    
    
    /**
     * Obtiene la edad del jugador.
     * 
     * @return La edad del jugador.
     */
  
    
    public int getEdad() {
        return edad;
    }
   
    
    /**
     * Establece la edad del jugador.
     * 
     * @param edad La nueva edad del jugador.
     */
   
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
   
    /**
     * Obtiene el valor de mercado del jugador.
     * 
     * @return El valor de mercado del jugador.
     */
    
    
    public double getValorMercado() {
        return valorMercado;
    }
    
    
    /**
     * Establece el valor de mercado del jugador.
     * 
     * @param valorMercado El nuevo valor de mercado del jugador.
     */
   
    
    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }
   
    
    /**
    * Obtiene la demarcación del jugador.
    * @return la demarcación actual.
    */
   
    public Demarcacion getDemarcacion() { return demarcacion; }
  
    
    /**
     * Establece la demarcación del jugador.
     * @param demarcacion la nueva demarcación a asignar.
     */
   
    public void setDemarcacion(Demarcacion demarcacion) { this.demarcacion = demarcacion; }
   
    
    /**
    * Obtiene el estado del jugador.
    * @return el estado actual (por ejemplo, lesionado o disponible).
    */
   
    public Estado getEstado() { return estado; }
   
    
    /**
    * Establece el estado del jugador.
    * @param estado el nuevo estado a asignar.
    */
  
    public void setEstado(Estado estado) { this.estado = estado; }
    
    
    /**
     * Devuelve una representación en formato de texto del jugador, incluyendo su información personal,
     * demarcación, edad, valor de mercado y disponibilidad.
     * 
     * @return Una cadena que representa al jugador en formato texto.
     */
    
     @Override
    public String toString() {
        String prefix = eliminado ? "[ELIMINADO] " : "";
        return prefix + String.format("DNI: %s, Nombre: %s, Apellido: %s, Teléfono: %s, Edad: %d, Valor: %.2fM€, Estado: %s, Demarcación: %s", dni, nombre, apellido, telefono, edad, valorMercado, estado, demarcacion);
    }

    
    /**
    * Permite modificar los datos del jugador solicitando nueva información
    * mediante cuadros de diálogo.
    */
    
    @Override
    public void modificarDatos() {
        this.nombre = JOptionPane.showInputDialog("Nuevo nombre:", this.nombre);
        this.telefono = JOptionPane.showInputDialog("Nuevo teléfono:", this.telefono);
        this.edad = Integer.parseInt(JOptionPane.showInputDialog("Nueva edad:", this.edad));
        this.valorMercado = Double.parseDouble(JOptionPane.showInputDialog("Nuevo valor de mercado:", this.valorMercado));
    }
    
}
