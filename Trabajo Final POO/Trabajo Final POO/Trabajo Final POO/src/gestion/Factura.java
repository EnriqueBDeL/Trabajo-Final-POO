package gestion;

/**
 * Representa una factura emitida a un cliente, con detalles como el código, la cantidad, la fecha de pago y el cliente.
 */
public class Factura {

    /**
     * Contador estático para generar un código único para cada factura.
     */
    private static int contador = 1;

    /**
     * El código único de la factura.
     */
    private int codigoFactura;

    /**
     * La cantidad total de la factura.
     */
    private double cantidad;

    /**
     * La fecha en la que se realiza el pago de la factura.
     */
    private String fechaPago;

    /**
     * El cliente al que se le emite la factura.
     */
    private Cliente cliente;

    /**
     * Constructor para crear una nueva factura con la cantidad, fecha de pago y cliente especificados.
     * El código de la factura es generado automáticamente y se incrementa con cada nueva factura.
     * 
     * @param cantidad La cantidad total de la factura.
     * @param fechaPago La fecha en la que se realiza el pago de la factura.
     * @param cliente El cliente al que se le emite la factura.
     */
    public Factura(double cantidad, String fechaPago, Cliente cliente) {
        this.codigoFactura = contador++; // Incrementa el contador para cada nueva factura
        this.cantidad = cantidad;
        this.fechaPago = fechaPago;
        this.cliente = cliente;
    }

    /**
     * Obtiene el código único de la factura.
     * 
     * @return El código de la factura.
     */
    public int getCodigoFactura() {
        return codigoFactura;
    }

    /**
     * Obtiene la cantidad total de la factura.
     * 
     * @return La cantidad de la factura.
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de la factura.
     * 
     * @param cantidad La nueva cantidad de la factura.
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la fecha de pago de la factura.
     * 
     * @return La fecha de pago de la factura.
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * Establece la fecha de pago de la factura.
     * 
     * @param fechaPago La nueva fecha de pago de la factura.
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Obtiene el cliente al que se le emite la factura.
     * 
     * @return El cliente al que se le emite la factura.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente al que se le emite la factura.
     * 
     * @param cliente El nuevo cliente al que se le emite la factura.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve una representación en formato de texto de la factura, que incluye el código, la cantidad, la fecha de pago y el cliente.
     * 
     * @return Una cadena que representa a la factura.
     */
    @Override
    public String toString() {
        return "Factura{" +
                "codigoFactura=" + codigoFactura +
                ", cantidad=" + cantidad + " €" +
                ", fechaPago=" + fechaPago +
                ", cliente=" + cliente +
                '}';
    }
}
