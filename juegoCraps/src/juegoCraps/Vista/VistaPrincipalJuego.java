package juegoCraps.Vista;

import javax.swing.*;
import java.awt.*;
import juegoCraps.Controlador.Controlador;
import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class VistaPrincipalJuego extends PanelFondo {
	private static final long serialVersionUID = 1L;
	
	private ListenerNavegador listener;
    private Controlador controlador;

	VistaPanioCentral vistaPanio = new VistaPanioCentral();
	VistaTableroTiro vistaTableroTiro = new VistaTableroTiro();
    PanelListaJugadores panelListaJugadores = new PanelListaJugadores();
    PanelAcciones panelAcciones = new PanelAcciones();
    PanelFichas panelFichas = new PanelFichas();
    JPanel panelCentral = new JPanel(new GridLayout(1, 2, 6, 0)); 
    JPanel panelTurno = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 6));
    JLabel labelTurnoTitulo = new JLabel("TURNO ACTUAL");
    JLabel labelNombre = new JLabel("");
    JLabel labelRol = new JLabel("");
    
    public VistaPrincipalJuego() {
        this.setLayout(new BorderLayout(6, 6));
        this.setBackground(EstilosUI.AZUL_PROFUNDO);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        construirPanelTurno();
        construirPanelCentral();

        this.add(panelTurno,          BorderLayout.NORTH);
        this.add(panelListaJugadores, BorderLayout.WEST);
        this.add(panelCentral,        BorderLayout.CENTER);
        this.add(panelAcciones,       BorderLayout.EAST);
        this.add(panelFichas,         BorderLayout.SOUTH);
    }
    
//metodos
    private void construirPanelTurno() {
        panelTurno.setBackground(EstilosUI.AZUL_MEDIO);
        panelTurno.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 1));

        labelTurnoTitulo.setFont(EstilosUI.FUENTE_SEC);
        labelTurnoTitulo.setForeground(EstilosUI.DORADO);

        labelNombre.setFont(EstilosUI.FUENTE_SEC);
        labelNombre.setForeground(EstilosUI.BLANCO_PRIMARIO);

        labelRol.setFont(EstilosUI.FUENTE_SEC);
        labelRol.setForeground(EstilosUI.BLANCO_PRIMARIO);

        JLabel sep1 = new JLabel("|");
        sep1.setForeground(EstilosUI.DORADO);
        JLabel sep2 = new JLabel("|");
        sep2.setForeground(EstilosUI.DORADO);

        panelTurno.add(labelTurnoTitulo);
        panelTurno.add(sep1);
        panelTurno.add(labelNombre);
        panelTurno.add(sep2);
        panelTurno.add(labelRol);
    }
    
    private void construirPanelCentral() {
        panelCentral.setOpaque(false);
        panelCentral.add(vistaTableroTiro);
        panelCentral.add(vistaPanio);
    }
    
    public void actualizarTurno(String nombreJugador, String rol) {
        labelNombre.setText(nombreJugador.toUpperCase());
        labelRol.setText("ROL: " + rol);
        if(rol.equalsIgnoreCase("TIRADOR")) {
            labelRol.setForeground(EstilosUI.DORADO);
        } else {
            labelRol.setForeground(EstilosUI.BLANCO_PRIMARIO);
        }
    }

//geters
    public PanelAcciones getPanelAcciones() {
        return panelAcciones;
    }
    public PanelListaJugadores getPanelListaJugadores() {
        return panelListaJugadores;
    }
    
    public VistaTableroTiro getVistaTablero() {
    	return vistaTableroTiro;
    }
    public Controlador getControlador() {
    	return controlador;
    }
    
    public ListenerNavegador getListener() {
    	return listener;
    }
    
//setters
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
        if (this.vistaPanio != null) {
            this.vistaPanio.setListener(listener);
        } 
        if(this.vistaTableroTiro != null) {
        	this.vistaTableroTiro.setListener(listener);
        }
        if(this.panelFichas != null) {
        	this.panelFichas.setListener(listener);
        }
        if(this.panelAcciones!=null) {
        	this.panelAcciones.setListener(listener);
        }
    }
}