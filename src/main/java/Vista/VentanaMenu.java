package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {
    private final VentanaLogin ventanaLogin;
    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private final JLabel lblBienvenida = new JLabel();
    private final JButton btnJugar = new JButton("Jugar Ruleta");
    private final JButton btnVerPerfil = new JButton("Ver Perfil");
    private final JButton btnSalir = new JButton("Cerrar Sesión");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnEstadisticas = new JButton("Estadísticas");

    public VentanaMenu(VentanaLogin login, SessionController sc, RuletaController rc ) {
        super("Menú Principal - Casino Black Cat");
        this.ventanaLogin = login;
        this.sessionController = sc;
        this.ruletaController = rc;
        configurarComponentes();
        configurarVentana();
        agregarEventos();
    }

    private void configurarComponentes() {
        this.setLayout(new BorderLayout(10, 10));

        lblBienvenida.setText("<html><h1>Bienvenido/a, " + sessionController.getNombreUsuario() + "</h1>" +
                "<p>Saldo actual: $" + ruletaController.getSaldo() + "</p></html>");
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelMenu = new JPanel(new GridLayout(4, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelMenu.add(btnJugar);
        panelMenu.add(btnVerPerfil);
        panelMenu.add(btnHistorial);
        panelMenu.add(btnEstadisticas);
        panelMenu.add(btnSalir);

        this.add(panelMenu, BorderLayout.WEST);
        this.add(lblBienvenida, BorderLayout.CENTER);
    }

    private void configurarVentana() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        btnJugar.addActionListener(e -> abrirRuleta());
        btnVerPerfil.addActionListener(e -> abrirPerfil());
        btnSalir.addActionListener(e -> cerrarSesion());
        btnHistorial.addActionListener(event-> abrirHistorial());
        btnEstadisticas.addActionListener(e -> abrirEstadisticas());
    }

    private void abrirRuleta() {
        VentanaRuleta ruleta = new VentanaRuleta(this, ruletaController, sessionController);
        ruleta.mostrarVentana();
        this.setVisible(false);
    }

    private void abrirPerfil() {
        VentanaPerfil perfil = new VentanaPerfil(this, sessionController, ruletaController);
        perfil.mostrarVentana();
        this.setVisible(false);
    }

    private void abrirHistorial() {
        VentanaHistorial historial = new VentanaHistorial(this, sessionController, ruletaController);
        historial.mostrarVentana();
        this.setVisible(false);
    }

    private void cerrarSesion() {
        sessionController.cerrarSesion();
        this.dispose();
        ventanaLogin.mostrarVentana();
    }

    public void mostrarVentana() {
        this.setVisible(true);
    }

    public void actualizarBienvenida() {
        lblBienvenida.setText("<html><h1>Bienvenido/a, " + sessionController.getNombreUsuario() + "</h1>" +
                "<p>Saldo actual: $" + ruletaController.getSaldo() + "</p></html>");
    }

    private void abrirEstadisticas() {
        // Le pasamos los controladores necesarios
        VentanaEstadisticas stats = new VentanaEstadisticas(this, ruletaController);
        stats.mostrarVentana();
        this.setVisible(false);
    }
}
