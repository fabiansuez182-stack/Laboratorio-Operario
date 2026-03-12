package logica;

public class Procesos {
    
    public void calcularSueldoNuevo(Operario miOperador) {
        double sueldo = miOperador.getSueldoActual();
        int antiguedad = miOperador.getAntiguedad();
        double aumento = 0;

        if (sueldo < 500 && antiguedad >= 10) {
            aumento = sueldo * 0.20;
        } else if (sueldo < 500 && antiguedad < 10) {
            aumento = sueldo * 0.05;
        } else if (sueldo >= 500) {
            aumento = 0;
        }

        miOperador.setAumento(aumento);
        miOperador.setSueldoNuevo(sueldo + aumento);
    }
}