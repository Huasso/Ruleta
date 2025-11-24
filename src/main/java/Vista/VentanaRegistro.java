package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private final VentanaLogin ventanaLogin;
    private final SessionController sessionController;

    private final JTextField txtUsername = new JTextField(15);
    private final JPasswordField txtPassword = new JPasswordField(15);
    private final JTextField txtNombre = new JTextField(15);
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnVolver = new JButton("Volver");

    public VentanaRegistro(VentanaLogin login, SessionController sc) {
        super("Registro - Casino Black Cat");
        this.ventanaLogin = login;
        this.sessionController = sc;
        configurarComponentes();
        configurarVentana();
        agregarEventos();
    }

    private void configurarComponentes() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Clave:"));
        panel.add(txtPassword);
        panel.add(new JLabel("Nombre Completo:"));
        panel.add(txtNombre);
        panel.add(btnVolver);
        panel.add(btnRegistrar);

        this.add(panel);
    }

    private void configurarVentana() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> volverLogin());
    }

    private void registrarUsuario() {
        String u = txtUsername.getText();
        String p = new String(txtPassword.getPassword());
        String n = txtNombre.getText();

        try {
            sessionController.registrarUsuario(u, p, n);
            JOptionPane.showMessageDialog(this, "¡Usuario " + u + " registrado con éxito!",
                    "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            volverLogin();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverLogin() {
        this.dispose();
        ventanaLogin.mostrarVentana();
    }

    public void mostrarVentana() {
        this.setVisible(true);
    }
}
