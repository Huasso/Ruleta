package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.Resultado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial extends JFrame {
    private final VentanaMenu ventanaMenu;
    private final SessionController sessionController;
    private final RuletaController ruletaController; // Controlador para obtener el historial

    private final JTextArea txtHistorialCompleto = new JTextArea(20, 50);
    private final JButton btnVolver = new JButton("Volver al Menú");

    public VentanaHistorial(VentanaMenu menu, SessionController sc, RuletaController rc) {
        super("Historial de Rondas - " + sc.getNombreUsuario());
        this.ventanaMenu = menu;
        this.sessionController = sc;
        // Obtenemos el ResultadoController a través de RuletaController
        this.ruletaController = rc;

        configurarComponentes();
        configurarVentana();
        agregarEventos();
        cargarHistorial();
    }

    private void configurarComponentes() {
        this.setLayout(new BorderLayout(10, 10));

        txtHistorialCompleto.setEditable(false);
        txtHistorialCompleto.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollHistorial = new JScrollPane(txtHistorialCompleto);

        this.add(scrollHistorial, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.add(btnVolver);
        this.add(panelInferior, BorderLayout.SOUTH);

        // Agrega un título simple
        JLabel lblTitulo = new JLabel("Historial Completo de Juego", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(lblTitulo, BorderLayout.NORTH);
    }

    private void configurarVentana() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        btnVolver.addActionListener(e -> volverMenu());
    }

    /**
     * Carga el historial completo desde el controlador y lo muestra en el JTextArea.
     */
    private void cargarHistorial() {
        // En este caso, solicitamos todos los resultados almacenados
        List<Resultado> historial = ruletaController.getHistorialCompleto();

        txtHistorialCompleto.setText(""); // Limpiar el área de texto

        if (historial.isEmpty()) {
            txtHistorialCompleto.append("Aún no se ha jugado ninguna ronda.");
        } else {
            // Recorremos la lista y formateamos cada resultado
            for (Resultado item : historial) {
                txtHistorialCompleto.append(item.toString() + "\n");
            }
        }
    }

    // NOTA: Para que esto funcione, ResultadoController necesita un getter para el tamaño total.

    private void volverMenu() {
        this.dispose();
        ventanaMenu.mostrarVentana();
    }

    public void mostrarVentana() {
        this.setVisible(true);
    }
}
