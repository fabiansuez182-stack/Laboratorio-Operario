package datos;

import java.util.HashMap;
import logica.Operario;

public class ModeloDatos {
    
    private HashMap<String, Operario> mapaOperarios;

    public ModeloDatos() {
        mapaOperarios = new HashMap<>();
    }

    public String registrarOperario(Operario op) {
        if (!mapaOperarios.containsKey(op.getDocumento())) {
            mapaOperarios.put(op.getDocumento(), op);
            return "Sí"; 
        } else {
            return "No"; 
        }
    }

    public Operario consultarOperario(String documento) {
        return mapaOperarios.getOrDefault(documento, null);
    }

    public String actualizarOperario(Operario op) {
        if (mapaOperarios.containsKey(op.getDocumento())) {
            mapaOperarios.put(op.getDocumento(), op);
            return "Actualizado";
        }
        return "No existe";
    }

    public boolean eliminarOperario(String documento) {
        if (mapaOperarios.containsKey(documento)) {
            mapaOperarios.remove(documento);
            return true;
        }
        return false;
    }

    public void imprimirLista() {
        System.out.println("--- LISTA TOTAL DE OPERARIOS REGISTRADOS ---");
        if (mapaOperarios.isEmpty()) {
            System.out.println("No hay operarios en el sistema.");
        } else {
            for (Operario op : mapaOperarios.values()) {
                System.out.println(op.toString());
            }
        }
        System.out.println("--------------------------------------------");
    }
}