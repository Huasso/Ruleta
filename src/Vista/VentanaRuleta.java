package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.Resultado;
import Modelo.ApuestaBase;
import Modelo.ApuestaRojo;
import Modelo.ApuestaNegro;
import Modelo.ApuestaPar;
import Modelo.ApuestaImpar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRuleta extends JFrame {

    private final JComboBox<String> cmbTipoApuesta = new JComboBox<>(new String[]{"Color", "Paridad"});
    private final JComboBox<String> cmbOpcionApuesta = new JComboBox<>(new String[]{"ROJO", "NEGRO"});
    private final JTextField txtMonto = new JTextField("100", 5);
    private final JButton btnGirar = new JButton("Girar");
    private final JLabel lblSaldo = new JLabel();
    private final JTextArea txtHistorial = new JTextArea(7, 40);
    private final JButton btnVolver = new JButton("Volver al Menú");

    private final VentanaMenu ventanaMenu;
    private final RuletaController ruletaController;
    private final SessionController sc;

    public VentanaRuleta(VentanaMenu menu, RuletaController ruletaController, SessionController sc) {
        super("Jugar Ruleta - " + sc.getNombreUsuario());
        this.ventanaMenu = menu;
        this.ruletaController = ruletaController;

        this.sc = sc;
        configurarComponentes();
        configurarVentana();
        agregarEventos();
        actualizarVista(null);
    }

    private void configurarComponentes() {
        setLayout(new BorderLayout(10, 10));
        JPanel panelApuesta = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        panelApuesta.add(new JLabel("Tipo de apuesta:"));
        panelApuesta.add(cmbTipoApuesta);
        panelApuesta.add(new JLabel("Opción:"));
        panelApuesta.add(cmbOpcionApuesta);
        panelApuesta.add(new JLabel("Monto: $"));
        panelApuesta.add(txtMonto);
        panelApuesta.add(btnGirar);
        panelApuesta.add(lblSaldo);

        txtHistorial.setEditable(false);
        JScrollPane scrollHistorial = new JScrollPane(txtHistorial);

        this.add(panelApuesta, BorderLayout.NORTH);
        this.add(scrollHistorial, BorderLayout.CENTER);
        this.add(btnVolver, BorderLayout.SOUTH);
    }

    private void configurarVentana() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        btnGirar.addActionListener(e -> jugar());
        btnVolver.addActionListener(e -> volverMenu());
        cmbTipoApuesta.addActionListener(e -> actualizarOpcionesApuesta());
    }

    private void actualizarOpcionesApuesta() {
        String tipo = (String) cmbTipoApuesta.getSelectedItem();
        cmbOpcionApuesta.removeAllItems();

        if ("Color".equals(tipo)) {
            cmbOpcionApuesta.addItem("ROJO");
            cmbOpcionApuesta.addItem("NEGRO");
        } else if ("Paridad".equals(tipo)) {
            cmbOpcionApuesta.addItem("PAR");
            cmbOpcionApuesta.addItem("IMPAR");
        }
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            String opcionStr = (String) cmbOpcionApuesta.getSelectedItem();

            ApuestaBase apuesta;
            switch (opcionStr) {
                case "ROJO": apuesta = new ApuestaRojo(monto); break;
                case "NEGRO": apuesta = new ApuestaNegro(monto); break;
                case "PAR": apuesta = new ApuestaPar(monto); break;
                case "IMPAR": apuesta = new ApuestaImpar(monto); break;
                default: throw new IllegalArgumentException("Opción de apuesta no válida.");
            }

            Resultado resultado = ruletaController.jugarRonda(apuesta);

            actualizarVista(resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número entero válido.",
                    "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Apuesta", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void actualizarVista(Resultado resultadoRonda) {
        lblSaldo.setText("Saldo: $" + ruletaController.getSaldo());


        List<Resultado> historial = ruletaController.getHistorialReciente(7);
        txtHistorial.setText("");
        txtHistorial.append("--- Últimas Rondas ---\n");
        if (historial.isEmpty()) {
            txtHistorial.append("Aún no hay resultados de juego.\n");
        } else {
            for (Resultado item : historial) {
                txtHistorial.append(item.toString() + "\n");
            }
        }

        ventanaMenu.actualizarBienvenida();
    }

    private void volverMenu() {
        this.dispose();
        ventanaMenu.mostrarVentana();
    }

    public void mostrarVentana() {
        this.setVisible(true);
    }
}
