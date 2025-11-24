package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaPerfil extends JFrame {
    private final VentanaMenu ventanaMenu;
    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private final JLabel lblNombre = new JLabel();
    private final JTextField txtNuevoNombre = new JTextField(15);
    private final JButton btnGuardarNombre = new JButton("Guardar Nombre");

    private final JLabel lblSaldo = new JLabel();
    private final JTextField txtMontoRecarga = new JTextField("100", 10);
    private final JButton btnRecargar = new JButton("Recargar Saldo");
    private final JButton btnVolver = new JButton("Volver al Menú");

    public VentanaPerfil(VentanaMenu menu, SessionController sc, RuletaController rc) {
        super("Perfil de Usuario");
        this.ventanaMenu = menu;
        this.sessionController = sc;
        this.ruletaController = rc;
        configurarComponentes();
        configurarVentana();
        agregarEventos();
        actualizarVista();
    }

    private void configurarComponentes() {
        setLayout(new BorderLayout(10, 10));
        JPanel panelPrincipal = new JPanel(new GridLayout(3, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de Nombre
        JPanel panelNombre = new JPanel(new FlowLayout());
        panelNombre.add(new JLabel("Nombre Actual:"));
        panelNombre.add(lblNombre);
        panelNombre.add(new JLabel("Nuevo Nombre:"));
        panelNombre.add(txtNuevoNombre);
        panelNombre.add(btnGuardarNombre);
        panelPrincipal.add(panelNombre);

        // Panel de Saldo y Recarga
        JPanel panelSaldo = new JPanel(new FlowLayout());
        panelSaldo.add(new JLabel("Saldo Actual:"));
        panelSaldo.add(lblSaldo);
        panelSaldo.add(new JLabel("Monto a Recargar:"));
        panelSaldo.add(txtMontoRecarga);
        panelSaldo.add(btnRecargar);
        panelPrincipal.add(panelSaldo);

        // Panel de Control
        JPanel panelVolver = new JPanel(new FlowLayout());
        panelVolver.add(btnVolver);
        panelPrincipal.add(panelVolver);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        btnVolver.addActionListener(e -> volverMenu());
        btnGuardarNombre.addActionListener(e -> guardarNombre());
        btnRecargar.addActionListener(e -> recargarSaldo());
    }

    private void actualizarVista() {
        lblNombre.setText(sessionController.getNombreUsuario());
        lblSaldo.setText("$" + ruletaController.getSaldo());
    }

    private void guardarNombre() {
        String nuevoNombre = txtNuevoNombre.getText();
        try {
            sessionController.setNombreUsuario(nuevoNombre);
            JOptionPane.showMessageDialog(this, "Nombre actualizado con éxito.");
            actualizarVista();
            ventanaMenu.actualizarBienvenida();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recargarSaldo() {
        try {
            int monto = Integer.parseInt(txtMontoRecarga.getText());
            ruletaController.depositar(monto);
            JOptionPane.showMessageDialog(this, "$" + monto + " recargados con éxito.");
            actualizarVista();
            ventanaMenu.actualizarBienvenida();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverMenu() {
        dispose();
        ventanaMenu.mostrarVentana();
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}
