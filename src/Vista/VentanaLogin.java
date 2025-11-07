package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private final SessionController sessionController;
    private final RuletaController ruletaController;


    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnIngresar = new JButton("Iniciar Sesión");
    private final JButton btnRegistro = new JButton("Registrar");

    public VentanaLogin(SessionController sc, RuletaController rc) {
        super("Login Casino Black Cat");
        this.sessionController = sc;
        this.ruletaController = rc;
        configurarComponentes();
        configurarVentana();
        agregarEventos();
    }

    private void configurarComponentes() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Clave:"));
        panel.add(txtClave);
        panel.add(btnIngresar);
        panel.add(btnRegistro);

        this.add(panel);
    }

    private void configurarVentana() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        btnIngresar.addActionListener(e -> login());
        btnRegistro.addActionListener(e -> abrirRegistro());
    }

    private void login() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());

        if (sessionController.iniciarSesion(u, p)) {
            JOptionPane.showMessageDialog(this,
                    "¡Bienvenido, " + sessionController.getNombreUsuario() + "!",
                    "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
            abrirMenu();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Credenciales incorrectas. Inténtalo de nuevo.",
                    "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentana() {
        this.setVisible(true);
    }

    private void abrirMenu() {
        VentanaMenu menu = new VentanaMenu(this, this.sessionController, this.ruletaController);
        menu.mostrarVentana();
        this.setVisible(false);
    }

    private void abrirRegistro() {
        VentanaRegistro registro = new VentanaRegistro(this, sessionController);
        registro.mostrarVentana();
        this.setVisible(false);
    }
}
