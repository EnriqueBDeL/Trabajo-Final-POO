package control;


/**
 * Representa a un directivo de un club de fútbol, quien hereda de la clase {@link Persona}.
 * Un directivo tiene un cargo dentro del club.
 */

public class Directivo extends Persona {
   
    
    /**
     * El cargo del directivo dentro del club (por ejemplo, presidente, director deportivo, etc.).
     */
   
    
    private String cargo;
  
    /**
     * Constructor para crear un nuevo directivo con un DNI, nombre, teléfono y cargo específicos.
     * 
     * @param dni El DNI del directivo.
     * @param nombre El nombre del directivo.
     * @param telefono El número de teléfono del directivo.
     * @param cargo El cargo del directivo dentro del club.
     */
  
    
    
    public Directivo(String cargo, String dni, String nombre, String apellido, String telefono) {
        super(dni, nombre, apellido, telefono);
        this.cargo = cargo;
    }

    
    
    /**
     * Obtiene el cargo del directivo.
     * 
     * @return El cargo del directivo.
     */
   
    
    public String getCargo() {
        return cargo;
    }
   
    
    /**
     * Establece el cargo del directivo.
     * 
     * @param cargo El nuevo cargo del directivo.
     */
   
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
   
    
    /**
     * Devuelve una representación en formato de texto del directivo, incluyendo su cargo.
     * 
     * @return Una cadena que representa al directivo con su información.
     */
   
    
    @Override
    public String toString() {
        return super.toString() + ", Cargo: " + cargo;
    }
    
	@Override
	public void modificarDatos() {
        }
        
}
