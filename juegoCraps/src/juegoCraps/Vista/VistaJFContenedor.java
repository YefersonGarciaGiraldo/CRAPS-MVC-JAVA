package juegoCraps.Vista;

import javax.swing.*;
import java.awt.*;
import juegoCraps.Controlador.Controlador;
import juegoCraps.Modelo.ModeloJugador;

public class VistaJFContenedor extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private Controlador controlador;
    
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelContenedor = new JPanel(cardLayout);
    
    private VistaCargaInicio vistaCarga = new VistaCargaInicio();
    private VistaInicio vistaInicio = new VistaInicio();
    private VistaRegistro vistaRegistro = new VistaRegistro();
    private VistaPrincipalJuego vistaPrincipal = new VistaPrincipalJuego();
   

    public VistaJFContenedor(Controlador controlador) {
    
    	this.controlador = controlador;
    	this.vistaCarga.setListener(controlador);
    	this.vistaInicio.setListener(controlador);
    	this.vistaRegistro.setListener(controlador);
    	this.vistaPrincipal.setListener(controlador);
    	
        FuncionesUtilesDecoracion.configurarVentana(this);
        
        setTitle("C R A P S");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panelContenedor.setOpaque(false);
        panelContenedor.add(vistaCarga,    "CARGA");
        panelContenedor.add(vistaInicio,   "INICIO");
        panelContenedor.add(vistaRegistro, "REGISTRO");
        panelContenedor.add(vistaPrincipal,"JUEGO");
        getContentPane().add(panelContenedor);
        
    	this.controlador.setVentana(this);
        cambiarVista("CARGA");
    }

//metodos
    public void cambiarVista(String nombreVista) {
        cardLayout.show(panelContenedor, nombreVista);
    }
    
    public void actualizarRegistroNumJugadore(int numero) {
    	this.vistaRegistro.setTextNumJugador(numero);
    }
    
    public void limpiarCampoNombre() {
    	vistaRegistro.limpiarCampoNombre();
    }
    
    public void prepararSiguienteJugador(int numero) {
    	vistaRegistro.setTextNumJugador(numero + 1);
    }
    
    public void finalizarRegistroVisual() {
        vistaRegistro.mostrarBotonIniciarJuego();
        vistaRegistro.bloquearCampoNombre();
    }
    
    public void enfocarBotonCantidad() {
    	vistaRegistro.enfocarBotonCantidad();
    }
    
    public void agregarJugador(ModeloJugador jugador) {
    	PanelListaJugadores panel = vistaPrincipal.getPanelListaJugadores();
        TarjetaJugador tarjeta = new TarjetaJugador(jugador.getNombre(), jugador.getSaldo());
        tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.seterPanelContenedorScroll(tarjeta);
        panel.add(Box.createVerticalStrut(5));
        panel.revalidate();
        panel.repaint();
    } 
    public void restablecerVistaRegistro() {

        vistaRegistro.restablecerFormulario();
    }

//getters
    public VistaCargaInicio getVistaCargaInicio() {
    	return this.vistaCarga;
    }
    
    public VistaInicio getVistaInicio() {
    	return this.vistaInicio;
    }
    
    public VistaRegistro getVistaRegistro() {
    	return this.vistaRegistro;
    }
    
    public VistaPrincipalJuego getVistaPrincipalJuego() {
    	return this.vistaPrincipal;
    }   
}
