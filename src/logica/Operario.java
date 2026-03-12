package logica;

public class Operario {
    private String documento;
    private String nombre;
    private double sueldoActual;
    private int antiguedad;
    private double sueldoNuevo;
    private double aumento;

    public Operario() {
    }

    public Operario(String documento, String nombre, double sueldoActual, int antiguedad) {
        this.documento = documento;
        this.nombre = nombre;
        this.sueldoActual = sueldoActual;
        this.antiguedad = antiguedad;
    }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSueldoActual() { return sueldoActual; }
    public void setSueldoActual(double sueldoActual) { this.sueldoActual = sueldoActual; }

    public int getAntiguedad() { return antiguedad; }
    public void setAntiguedad(int antiguedad) { this.antiguedad = antiguedad; }

    public double getSueldoNuevo() { return sueldoNuevo; }
    public void setSueldoNuevo(double sueldoNuevo) { this.sueldoNuevo = sueldoNuevo; }

    public double getAumento() { return aumento; }
    public void setAumento(double aumento) { this.aumento = aumento; }

    @Override
    public String toString() {
        return "Operario [Doc=" + documento + ", Nombre=" + nombre + ", Sueldo Base=" + sueldoActual 
                + ", Antigüedad=" + antiguedad + " años, Aumento=" + aumento + ", Nuevo Sueldo=" + sueldoNuevo + "]";
    }
}