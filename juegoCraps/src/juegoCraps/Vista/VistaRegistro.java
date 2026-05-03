package juegoCraps.Vista;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class VistaRegistro extends PanelFondo {
	private static final long serialVersionUID = 1L;
	
	private ListenerNavegador listener;
	
	JPanel panelContenedor  = new JPanel();
    JLabel labelTitulo = new JLabel();
    JTextField nombreJugador = new JTextField("EJ.Nombre 1");
    JSpinner spinnerJugadores = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
    JButton botonRegistrar = new JButton("Registrar jugador");
    JButton botonCantidad = new JButton("Aceptar cantidad");
    JButton botonIniciar = new JButton("Iniciar");

    public VistaRegistro() {
        this.setLayout(new GridBagLayout());
        cargarImagen("FondoRegistro.png");

        panelContenedor.setLayout(new GridBagLayout());
        panelContenedor.setPreferredSize(new Dimension(800, 600));
        panelContenedor.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 3, true));
        panelContenedor.setBackground(EstilosUI.AZUL_GRD);

        labelTitulo.setText("Ingrese el nombre del jugador: 1");
        labelTitulo.setForeground(EstilosUI.BLANCO_PRIMARIO);
        labelTitulo.setFont(EstilosUI.FUENTE_PRI);

        nombreJugador.setPreferredSize(new Dimension(400, 50));
        nombreJugador.setEditable(false);
        FuncionesUtilesDecoracion.campoConPlaceholder(nombreJugador, "EJ.Nombre 1");
        nombreJugador.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
            	if(listener!=null) {
            		listener.alHacerFocusNombreREGISTRO();
            	}
            }
        });

        spinnerJugadores.setPreferredSize(new Dimension(80, 50));

        botonIniciar.setVisible(false);
        FuncionesUtilesDecoracion.configurarBoton(botonIniciar);
        botonIniciar.addActionListener(e ->{
        	if(listener!=null) {
        		listener.alBotonIniciarREGISTRO();
        	}
        });

        botonRegistrar.setEnabled(false);
        FuncionesUtilesDecoracion.configurarBoton(botonRegistrar);
        botonRegistrar.addActionListener(e -> {
            String nombre = nombreJugador.getText();
            if (nombre.isEmpty() || nombre.equals("EJ.Nombre 1")) {
            	JOptionPane.showMessageDialog(this, "Debe ingresar un nombre.");
            	return;
            }
            if(listener!=null) {
            	listener.alRegistrarJugadorREGISTRO(nombre);
            }
            repaint();
        });

        FuncionesUtilesDecoracion.configurarBoton(botonCantidad);

        botonCantidad.addActionListener(e -> {
            int numJugadores = (int) spinnerJugadores.getValue();

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro? No podrás cambiar la cantidad después.",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                spinnerJugadores.setEnabled(false);
                botonCantidad.setEnabled(false);
                nombreJugador.setEditable(true);
                botonRegistrar.setEnabled(true);
                if(listener!=null) {
                	listener.alAceptarCantidadREGISTRO(numJugadores);
                }
            } else {
                System.out.println("El usuario no confirmó la cantidad.");
            }
        });
        
        
        //GRID BAG CONSTRAINTS Y ADD DE LOS COMPONENTES
        
        //panel Contenedor
        GridBagConstraints gbc = FuncionesUtilesDecoracion.obtenerRestricciones(0, 0, 1, 1, "BOTH", 0.0, 0.0, GridBagConstraints.CENTER);
        this.add(panelContenedor, gbc);
        
        //label titulo
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(0, 0, 2, 1, "NONE", 0.0, 0.0, GridBagConstraints.NORTH);
        panelContenedor.add(labelTitulo, gbc);
        
        //JText nombre jugador
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(1, 0, 1, 1, "BOTH", 0.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(20, 0, 0, 20);
        panelContenedor.add(nombreJugador, gbc);
        
        //Spinner cantidad
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(1, 1, 1, 1, "BOTH", 0.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(20, 0, 0, 0);
        panelContenedor.add(spinnerJugadores, gbc);
        
        //boton iniciar
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(3, 0, 2, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(20, 0, 0, 0);
        panelContenedor.add(botonIniciar, gbc);
        
        //
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(2, 0, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(20, 0, 0, 0);
        panelContenedor.add(botonRegistrar, gbc);
        
        //boton aceptar cantidad
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(2, 1, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(20, 0, 0, 0);
        panelContenedor.add(botonCantidad, gbc);

    }

//setters
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
    }
    
    public void setTextNumJugador(int numero) {
        labelTitulo.setText("Ingrese el nombre del jugador: " + numero);
    }

//metodos
    public void limpiarCampoNombre() {
        nombreJugador.setText("");
    }

    public void mostrarBotonIniciarJuego() {
        botonIniciar.setVisible(true);
        botonRegistrar.setEnabled(false);
        nombreJugador.setEnabled(false);
    }

    public void bloquearCampoNombre() {
        nombreJugador.setEditable(false);
    }
    
    public void enfocarBotonCantidad() {
        this.botonCantidad.requestFocus();
    }
    public void restablecerFormulario() {

        // Spinner
        spinnerJugadores.setValue(1);
        spinnerJugadores.setEnabled(true);

        // Campo nombre
        nombreJugador.setText("EJ.Nombre 1");
        nombreJugador.setEditable(false);
        nombreJugador.setEnabled(true);

        // Botones
        botonCantidad.setEnabled(true);

        botonRegistrar.setEnabled(false);

        botonIniciar.setVisible(false);

        // Label
        labelTitulo.setText("Ingrese el nombre del jugador: 1");

        repaint();
        revalidate();
    }
    
}