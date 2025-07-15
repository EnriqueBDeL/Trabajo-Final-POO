package control;

/**
 * Representa a un técnico (entrenador) de un equipo de fútbol, que hereda de la clase Persona.
 * El técnico tiene un puesto y una especialidad que determinan su rol en el equipo.
 */

public class Tecnico extends Persona {

    /**
     * Enum que representa los diferentes puestos que puede tener un técnico en el equipo.
     */
    
    private Puesto puesto;
    
    /**
     * Enum que representa las especialidades de un técnico.
     */
    
    private Especialidad especialidad;
    
    /**
     * Enum que define los posibles puestos para un técnico.
     */
    
    public enum Puesto {
        ENTRENADOR
    }
    
    /**
     * Enum que define las especialidades de un técnico.
     */
    
    public enum Especialidad {
        PRINCIPAL, PORTEROS, AYUDANTE, SEGUNDO
    }

    /**
     * Constructor para crear un nuevo técnico con un puesto y una especialidad especificados.
     * 
     * @param dni El DNI del técnico.
     * @param nombre El nombre del técnico.
     * @param telefono El número de teléfono del técnico.
     * @param puesto El puesto del técnico en el equipo.
     * @param especialidad La especialidad del técnico.
     */
 
    public Tecnico(Puesto puesto, Especialidad especialidad, String dni, String nombre, String apellido, String telefono) {    
        super(dni, nombre, apellido, telefono);
        this.puesto = puesto;
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el puesto del técnico.
     * 
     * @return El puesto del técnico.
     */
    public Puesto getPuesto() {
        return puesto;
    }

    /**
     * Establece el puesto del técnico.
     * 
     * @param puesto El nuevo puesto del técnico.
     */
    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    /**
     * Obtiene la especialidad del técnico.
     * 
     * @return La especialidad del técnico.
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del técnico.
     * 
     * @param especialidad La nueva especialidad del técnico.
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Devuelve una descripción del rol del técnico según su puesto y especialidad.
     * 
     * @return Una cadena que describe el rol del técnico.
     */
    public String definir() {
        switch (puesto) {
            case ENTRENADOR:
                switch (especialidad) {
                    case PRINCIPAL:
                        return "Dirige al equipo como entrenador principal.";
                    case PORTEROS:
                        return "Entrena a los porteros del equipo.";
                    case AYUDANTE:
                    case SEGUNDO:
                        return "Asiste al entrenador principal.";
                    default:
                        return "Especialidad no especificada.";
                }
            default:
                return "Puesto no especificado.";
        }
    }

    /**
     * Devuelve una representación en formato de texto del técnico, incluyendo su puesto y especialidad.
     * 
     * @return Una cadena que representa al técnico en formato texto.
     */
    @Override
    public String toString() {
        return super.toString() + ", Puesto: " + puesto + ", Especialidad: " + especialidad;
    }

    /**
    * Implementación vacía del método abstracto modificarDatos() definido en la clase Persona.
    * Esta subclase no requiere modificación de datos específica, por lo que no implementa ninguna lógica aquí.
    */
    
	@Override
	public void modificarDatos() {
		
	}
}
