package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import Controlador.ResultadoController;
import Modelo.Resultado;
import Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRuleta extends JFrame {
    private final VentanaMenu ventanaMenu;
    private final JComboBox<String> cmbTipoApuesta = new JComboBox<>(new String[]{"Color", "Paridad"});
    private final JComboBox<String> cmbOpcionApuesta = new JComboBox<>(new String[]{"ROJO", "NEGRO"});
    private final JTextField txtMonto = new JTextField("100", 5);
    private final JButton btnGirar = new JButton("Girar");
    private final JLabel lblSaldo = new JLabel();
    private final JTextArea txtHistorial = new JTextArea(7, 40);
    private final JButton btnVolver = new JButton("Volver al Menú");


    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;
    private final SessionController sc;

    public VentanaRuleta(VentanaMenu menu, ResultadoController resultadoController, RuletaController ruletaController, SessionController sc) {
        super("Jugar Ruleta - " + sc.getNombreUsuario());
        this.ventanaMenu = menu;
        this.ruletaController = ruletaController;
        this.resultadoController = resultadoController;
        this.sc = sc;
        configurarComponentes();
        configurarVentana();
        agregarEventos();
        actualizarVista(null); // Actualiza la vista inicial (saldo e historial)
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
            cmbOpcionApuesta.addItem(TipoApuesta.ROJO.name());
            cmbOpcionApuesta.addItem(TipoApuesta.NEGRO.name());
        } else if ("Paridad".equals(tipo)) {
            cmbOpcionApuesta.addItem(TipoApuesta.PAR.name());
            cmbOpcionApuesta.addItem(TipoApuesta.IMPAR.name());
        }
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            String opcionStr = (String) cmbOpcionApuesta.getSelectedItem();

            // Llama al controlador para ejecutar toda la lógica
            Resultado resultado = ruletaController.jugarRonda(TipoApuesta.valueOf(opcionStr), monto);

            actualizarVista(resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número entero válido.",
                    "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Apuesta", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Actualiza el saldo y el historial en la interfaz.
     */
    private void actualizarVista(Resultado resultadoRonda) {
        lblSaldo.setText("Saldo: $" + ruletaController.getSaldo());

        // Actualizar el JTextArea con el historial reciente
        List<Resultado> historial = ruletaController.getResultadoController().getHistorialReciente(7);
        txtHistorial.setText("");
        txtHistorial.append("--- Últimas Rondas ---\n");
        if (historial.isEmpty()) {
            txtHistorial.append("Aún no hay resultados de juego.\n");
        } else {
            for (Resultado item : historial) {
                txtHistorial.append(item.toString() + "\n");
            }
        }

        // Actualiza el saldo en el menú principal
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
