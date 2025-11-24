package Vista;

import Controlador.RuletaController;
import Modelo.Estadisticas;
import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas extends JFrame {

    private final VentanaMenu ventanaMenu;
    private final RuletaController ruletaController;

    private final JLabel lblTotalJugadas = new JLabel("Total Jugadas: 0");
    private final JLabel lblVictorias = new JLabel("Victorias: 0");
    private final JLabel lblPorcentajeVictorias = new JLabel("Porcentaje Victoria: 0.0%");
    private final JLabel lblRachaMaxima = new JLabel("Racha Máxima: 0");
    private final JLabel lblTipoMasJugado = new JLabel("Tipo Más Jugado: N/A");
    private final JButton btnVolver = new JButton("Volver al Menú");

    public VentanaEstadisticas(VentanaMenu menu, RuletaController rc) {
        super("Estadísticas del Jugador");
        this.ventanaMenu = menu;
        this.ruletaController = rc;

        configurarComponentes();
        configurarVentana();
        agregarEventos();
        cargarDatos();
    }

    private void configurarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelMetricas = new JPanel(new GridLayout(5, 1, 10, 10));
        panelMetricas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelMetricas.add(lblTotalJugadas);
        panelMetricas.add(lblVictorias);
        panelMetricas.add(lblPorcentajeVictorias);
        panelMetricas.add(lblRachaMaxima);
        panelMetricas.add(lblTipoMasJugado);

        JLabel lblTitulo = new JLabel("Estadísticas de Juego", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        this.add(lblTitulo, BorderLayout.NORTH);
        this.add(panelMetricas, BorderLayout.CENTER);
        this.add(btnVolver, BorderLayout.SOUTH);
    }

    private void configurarVentana() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setSize(400, 350);
        this.setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        btnVolver.addActionListener(e -> volverMenu());
    }

    private void cargarDatos() {
        Estadisticas stats = ruletaController.calcularEstadisticasUsuario();

        lblTotalJugadas.setText("Total Jugadas: " + stats.getTotalJugadas());
        lblVictorias.setText("Victorias: " + stats.getVictorias());

        lblPorcentajeVictorias.setText(String.format("Porcentaje Victoria: %.2f%%", stats.getPorcentajeVictorias()));

        lblRachaMaxima.setText("Racha Máxima: " + stats.getRachaMaxima());

        String tipo = stats.getTipoMasJugado();
        if (tipo == null || tipo.isBlank() || tipo.equals("N/A")) {
            tipo = "N/A";
        }
        lblTipoMasJugado.setText("Tipo Más Jugado: " + tipo);
    }

    private void volverMenu() {
        this.dispose();
        ventanaMenu.mostrarVentana();
    }

    public void mostrarVentana() {
        this.setVisible(true);
    }
}
