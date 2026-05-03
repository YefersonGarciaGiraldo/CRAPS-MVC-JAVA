package juegoCraps.Vista;
 
import javax.swing.*;
import java.awt.*;

import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class VistaInicio extends PanelFondo {

	private static final long serialVersionUID = 1L;
	private ListenerNavegador listener;
	
	private final JPanel  panelPrincipal  = new JPanel();
    private final JLabel  nombreProyecto  = new JLabel("C R A P S");
    private final JLabel  nombreAutor     = new JLabel("YEFERSON ALEXANDER GARCIA GIRALDO");
    private final JButton botonSeguir     = new JButton("ENTRAR");

    public VistaInicio() {
        this.setLayout(new GridBagLayout());
        cargarImagen("FondoRegistro.png");
 
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setPreferredSize(new Dimension(800, 600));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 3, true));
        panelPrincipal.setBackground(EstilosUI.AZUL_GRD);
 
        nombreProyecto.setFont(EstilosUI.FUENTE_PRI);
        nombreProyecto.setForeground(EstilosUI.BLANCO_PRIMARIO);
        nombreProyecto.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        nombreAutor.setFont(EstilosUI.FUENTE_SEC);
        nombreAutor.setForeground(EstilosUI.BLANCO_PRIMARIO);
        nombreAutor.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonSeguir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSeguir.setPreferredSize(new Dimension(200, 50));
        FuncionesUtilesDecoracion.configurarBoton(botonSeguir);
        botonSeguir.addActionListener(e -> {
        	if(listener != null) {
        		listener.alPresionarEntrarINICIO();
        	}
        });
 
        panelPrincipal.add(Box.createVerticalStrut(80));
        panelPrincipal.add(nombreProyecto);
        panelPrincipal.add(Box.createVerticalStrut(160));
        panelPrincipal.add(nombreAutor);
        panelPrincipal.add(Box.createVerticalGlue());
        panelPrincipal.add(botonSeguir);
        panelPrincipal.add(Box.createVerticalStrut(80));
 
        GridBagConstraints gbc = FuncionesUtilesDecoracion.obtenerRestricciones(0, 0, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
        this.add(panelPrincipal, gbc);
    }
    
//setters
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
    }
}