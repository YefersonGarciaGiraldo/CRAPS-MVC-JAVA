package juegoCraps.Vista;

import javax.swing.*;
import java.awt.*;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class PanelListaJugadores extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final JPanel panelContenedorScroll = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(panelContenedorScroll);

    public PanelListaJugadores() {

        this.setLayout(new BorderLayout());
        this.setBackground(EstilosUI.AZUL_PROFUNDO);
        this.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 2));
        this.setPreferredSize(new Dimension(220, 0));

        panelContenedorScroll.setLayout(new BoxLayout(panelContenedorScroll, BoxLayout.Y_AXIS));
        panelContenedorScroll.setBackground(EstilosUI.AZUL_MEDIO);

        JLabel labelTitulo = new JLabel("JUGADORES", SwingConstants.CENTER);
        labelTitulo.setForeground(EstilosUI.DORADO);
        labelTitulo.setFont(EstilosUI.FUENTE_SEC);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelContenedorScroll.add(labelTitulo);

        scrollPane.setBorder(null);
        scrollPane.setBackground(EstilosUI.AZUL_MEDIO);
        scrollPane.getViewport().setBackground(EstilosUI.AZUL_MEDIO);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(scrollPane, BorderLayout.CENTER);
    }

//metodos
    public void limpiarJugadores() {
        panelContenedorScroll.removeAll();
        panelContenedorScroll.revalidate();
        panelContenedorScroll.repaint();
    }

    public void actualizarSaldoTarjeta(int indice, double nuevoSaldo) {
        // Obtenemos todos los componentes (las TarjetaJugador) del panel
        Component[] componentes = panelContenedorScroll.getComponents();
        int indiceD = indice +1;
        // Verificamos que el índice sea válido dentro del array de componentes
        if (indiceD >= 0 && indiceD < componentes.length) {
            if (componentes[indiceD] instanceof TarjetaJugador) {
                TarjetaJugador tarjeta = (TarjetaJugador) componentes[indiceD];
                // Llamamos al método de la tarjeta para refrescar el texto
                tarjeta.actualizarSaldo(nuevoSaldo);
                tarjeta.revalidate();
                tarjeta.repaint();
            }
        }
    }
    
//seters
    public void seterPanelContenedorScroll(Component c) {
    	panelContenedorScroll.add(c);
    }
}