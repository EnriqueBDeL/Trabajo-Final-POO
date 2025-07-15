package gestion;

/**
 * Representa un concepto de una nómina, con un código, una descripción y un importe asociado.
 * Un concepto puede ser un salario, un descuento, un bono u otro tipo de pago relacionado con la nómina de un empleado.
 */
public class ConceptoNomina {
    
    /**
     * El código único del concepto, utilizado para identificarlo.
     */
    private String codigo;
    
    /**
     * La descripción del concepto, que explica de qué se trata (por ejemplo, salario base, bono, etc.).
     */
    private String descripcion;
    
    /**
     * El importe asociado al concepto, es decir, la cantidad de dinero que representa este concepto en la nómina.
     */
    private float importe;

    /**
     * Constructor para crear un nuevo concepto de nómina con un código, descripción e importe específicos.
     * 
     * @param codigo El código del concepto.
     * @param descripcion La descripción del concepto.
     * @param importe El importe del concepto.
     */
    public ConceptoNomina(String codigo, String descripcion, float importe) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    /**
     * Obtiene el código del concepto.
     * 
     * @return El código del concepto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene la descripción del concepto.
     * 
     * @return La descripción del concepto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del concepto.
     * 
     * @param descripcion La nueva descripción del concepto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el importe del concepto.
     * 
     * @return El importe del concepto.
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Establece el importe del concepto.
     * 
     * @param importe El nuevo importe del concepto.
     */
    public void setImporte(float importe) {
        this.importe = importe;
    }
    
    
    /**
    * Devuelve una representación en forma de cadena del objeto ConceptoNomina,
    * que incluye el código, la descripción y el importe.
    *
    * @return una cadena con el formato: "codigo, descripcion, importe".
    */
    
    @Override
    public String toString() {
        return codigo + ", " + descripcion + ", " + importe;
    }
    
}

