package gestion;

/**
 * Representa a un cliente de un club de fútbol, con un CIF y un nombre.
 */
public class Cliente {
    
    /**
     * El CIF del cliente, utilizado para identificarlo fiscalmente.
     */
    private String cif;
    
    /**
     * El nombre del cliente.
     */
    private String nombre;

    /**
     * Constructor para crear un nuevo cliente con un CIF y nombre especificados.
     * 
     * @param cif El CIF del cliente.
     * @param nombre El nombre del cliente.
     */
    public Cliente(String cif, String nombre) {
        this.cif = cif;
        this.nombre = nombre;
    }

    /**
     * Obtiene el CIF del cliente.
     * 
     * @return El CIF del cliente.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Establece el CIF del cliente.
     * 
     * @param cif El nuevo CIF del cliente.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve una representación en formato de texto del cliente, mostrando su CIF y nombre.
     * 
     * @return Una cadena que representa al cliente en formato texto.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "cif='" + cif + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
