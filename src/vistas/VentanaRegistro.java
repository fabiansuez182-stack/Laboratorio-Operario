package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import datos.ModeloDatos;
import logica.Operario;
import logica.Procesos;

public class VentanaRegistro extends JFrame implements ActionListener {
    
    private JTextField txtDocumento, txtNombre, txtSueldo, txtAntiguedad;
    private JLabel lblResultadoAumento, lblResultadoSueldoFinal;
    private JButton btnRegistrar, btnCancelar, btnConsultar, btnActualizar, btnEliminar, btnListar;
    
    private Procesos misProcesos;
    private ModeloDatos miModeloDatos;

    public VentanaRegistro() {
        misProcesos = new Procesos();
        miModeloDatos = new ModeloDatos();
        
        setTitle("Sistema de Gestión de Operarios");
        setSize(480, 450);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JLabel lblTitulo = new JLabel("REGISTRO DE OPERARIOS");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 18));
        lblTitulo.setBounds(100, 15, 300, 30);
        add(lblTitulo);

        JLabel lblDoc = new JLabel("Documento:");
        lblDoc.setBounds(30, 70, 100, 20);
        add(lblDoc);
        txtDocumento = new JTextField();
        txtDocumento.setBounds(150, 70, 150, 20);
        add(txtDocumento);

        JLabel lblNom = new JLabel("Nombre:");
        lblNom.setBounds(30, 110, 100, 20);
        add(lblNom);
        txtNombre = new JTextField();
        txtNombre.setBounds(150, 110, 250, 20);
        add(txtNombre);

        JLabel lblSueldo = new JLabel("Sueldo Base:");
        lblSueldo.setBounds(30, 150, 100, 20);
        add(lblSueldo);
        txtSueldo = new JTextField();
        txtSueldo.setBounds(150, 150, 150, 20);
        add(txtSueldo);

        JLabel lblAntiguedad = new JLabel("Antigüedad (Años):");
        lblAntiguedad.setBounds(30, 190, 120, 20);
        add(lblAntiguedad);
        txtAntiguedad = new JTextField();
        txtAntiguedad.setBounds(150, 190, 130, 20);
        add(txtAntiguedad);

        lblResultadoAumento = new JLabel("Aumento aplicado: $0.0");
        lblResultadoAumento.setForeground(Color.BLUE);
        lblResultadoAumento.setBounds(30, 230, 300, 20);
        add(lblResultadoAumento);

        lblResultadoSueldoFinal = new JLabel("Sueldo Total a Pagar: $0.0");
        lblResultadoSueldoFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResultadoSueldoFinal.setForeground(new Color(0, 100, 0));
        lblResultadoSueldoFinal.setBounds(30, 250, 300, 20);
        add(lblResultadoSueldoFinal);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(30, 300, 100, 25);
        btnRegistrar.addActionListener(this);
        add(btnRegistrar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(140, 300, 100, 25);
        btnConsultar.addActionListener(this);
        add(btnConsultar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(250, 300, 100, 25);
        btnActualizar.addActionListener(this);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(30, 340, 100, 25);
        btnEliminar.addActionListener(this);
        add(btnEliminar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(140, 340, 100, 25);
        btnListar.addActionListener(this);
        add(btnListar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 340, 100, 25);
        btnCancelar.addActionListener(this);
        add(btnCancelar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) registrarOperador();
        else if (e.getSource() == btnCancelar) limpiarCampos();
        else if (e.getSource() == btnConsultar) consultar();
        else if (e.getSource() == btnActualizar) actualizar();
        else if (e.getSource() == btnEliminar) eliminar();
        else if (e.getSource() == btnListar) miModeloDatos.imprimirLista();
    }

    private void registrarOperador() {
        try {
            Operario miOperador = new Operario();
            miOperador.setDocumento(txtDocumento.getText().trim());
            miOperador.setNombre(txtNombre.getText().trim());
            miOperador.setSueldoActual(Double.parseDouble(txtSueldo.getText()));
            miOperador.setAntiguedad(Integer.parseInt(txtAntiguedad.getText()));

            misProcesos.calcularSueldoNuevo(miOperador);

            String respuesta = miModeloDatos.registrarOperario(miOperador);

            if (respuesta.equals("Sí")) {
                lblResultadoAumento.setText("Aumento aplicado: $" + "" + miOperador.getAumento());
                lblResultadoSueldoFinal.setText("Sueldo Total a Pagar: $" + "" + miOperador.getSueldoNuevo());
                JOptionPane.showMessageDialog(this, "Operario registrado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "El documento ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error de formato numérico.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtDocumento.setText("");
        txtNombre.setText("");
        txtSueldo.setText("");
        txtAntiguedad.setText("");
        lblResultadoAumento.setText("Aumento aplicado: $0.0");
        lblResultadoSueldoFinal.setText("Sueldo Total a Pagar: $0.0");
    }

    private void consultar() {
        String doc = JOptionPane.showInputDialog(this, "Ingrese el documento a consultar:");
        if (doc != null && !doc.trim().isEmpty()) {
            Operario op = miModeloDatos.consultarOperario(doc.trim());
            if (op != null) {
                txtDocumento.setText(op.getDocumento());
                txtNombre.setText(op.getNombre());
                txtSueldo.setText("" + op.getSueldoActual());
                txtAntiguedad.setText("" + op.getAntiguedad());
                
                lblResultadoAumento.setText("Aumento aplicado: $" + "" + op.getAumento());
                lblResultadoSueldoFinal.setText("Sueldo Total a Pagar: $" + "" + op.getSueldoNuevo());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el operario.");
            }
        }
    }

    private void actualizar() {
        try {
            Operario op = new Operario();
            op.setDocumento(txtDocumento.getText().trim());
            op.setNombre(txtNombre.getText().trim());
            op.setSueldoActual(Double.parseDouble(txtSueldo.getText()));
            op.setAntiguedad(Integer.parseInt(txtAntiguedad.getText()));

            misProcesos.calcularSueldoNuevo(op);

            String mensaje = miModeloDatos.actualizarOperario(op);
            JOptionPane.showMessageDialog(this, mensaje);
            
            if(mensaje.equals("Actualizado")){
                lblResultadoAumento.setText("Aumento aplicado: $" + "" + op.getAumento());
                lblResultadoSueldoFinal.setText("Sueldo Total a Pagar: $" + "" + op.getSueldoNuevo());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Asegúrese de cargar primero los datos o usar formato correcto.");
        }
    }

    private void eliminar() {
        String doc = JOptionPane.showInputDialog(this, "Ingrese documento a eliminar:");
        if (doc != null && !doc.trim().isEmpty()) {
            if (miModeloDatos.eliminarOperario(doc.trim())) {
                JOptionPane.showMessageDialog(this, "Operario eliminado del sistema.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el operario.");
            }
        }
    }
}