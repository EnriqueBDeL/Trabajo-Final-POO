package gestion;

import java.util.ArrayList;

import control.Persona;
import java.util.List;

/**
 * Representa una nómina para un empleado en un mes y año determinados.
 * La nómina contiene un conjunto de conceptos, cada uno con su descripción y codigo,
 * y permite calcular el total de la nómina sumando los importes de esos conceptos.
 */
public class Nomina {
    
    /**
     * El mes en el que se genera la nómina.
     */
    public String mes;
    
    /**
     * El año en el que se genera la nómina.
     */
    public int anio;
    
    /**
     * El empleado al que pertenece la nómina.
     */
    public Persona empleado;
    
    /**
     * Lista de conceptos de la nómina, cada uno con su descripción e importe.
     */
    public ArrayList<ConceptoNomina> conceptos;

   /**
 * Constructor para crear una nueva nómina.
 * 
 * @param mes El mes en el que se genera la nómina.
 * @param anio El año en el que se genera la nómina.
 */
    public Nomina(String mes, int anio) {
    this.mes = mes;
    this.anio = anio;
    this.conceptos = new ArrayList<>();
    }

    /**
     * Agrega un concepto a la nómina.
     * 
     * @param concepto El concepto que se quiere agregar.
     */
    public void agregarConcepto(ConceptoNomina concepto) {
        this.conceptos.add(concepto);
        System.out.println("[DEBUG] Concepto agregado: " + concepto);
    }

    /**
     * Calcula el total de la nómina sumando los importes de todos los conceptos.
     * 
     * @return El total de la nómina.
     */
    public double calcularTotal() {
    	 double total = conceptos.stream().mapToDouble(ConceptoNomina::getImporte).sum();
         System.out.println("[DEBUG] Total calculado para nómina de " + empleado.getDni() + ": " + total);
         return total;  
    
    }

    /**
     * Devuelve una representación en formato de texto de la nómina, incluyendo el nombre del empleado,
     * el mes y el año, los conceptos con sus descripciones e importes, y el total de la nómina.
     * 
     * @return Una cadena que representa la nómina en formato texto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nómina de ").append(empleado.getNombre()).append("\n")
          .append("Mes: ").append(mes).append(", Año: ").append(anio).append("\n")
          .append("Conceptos:\n");
        for (ConceptoNomina c : conceptos) {
            sb.append(" - ").append(c.getDescripcion()).append(": ").append(c.getImporte()).append(" €\n");
        }
        sb.append("Total: ").append(calcularTotal()).append(" €\n");
        return sb.toString();
         
    }
    
    
    
    /**
    * Devuelve una cadena con los códigos de los conceptos de la nómina separados por comas.
    *
    * @return String con los códigos de los conceptos, separados por comas.
    */
    
    public String getCodigosConceptosComoString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < conceptos.size(); i++) {
            sb.append(conceptos.get(i).getCodigo());
            if (i < conceptos.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    
  
    /**
    * Agrega a la nómina los conceptos cuyo código esté en la lista proporcionada,
    * buscando entre los conceptos disponibles.
    *
    * @param codigos Lista de códigos de conceptos a agregar.
    * @param conceptosDisponibles Lista de conceptos disponibles para buscar.
    */
    
    public void agregarConceptosPorCodigo(List<String> codigos, List<ConceptoNomina> conceptosDisponibles) {
        for (String codigo : codigos) {
            for (ConceptoNomina c : conceptosDisponibles) {
                if (c.getCodigo().equals(codigo)) {
                    this.conceptos.add(c);
                    break;
                }
            }
        }
    }
  
    
}
