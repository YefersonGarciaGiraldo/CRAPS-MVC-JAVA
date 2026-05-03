package juegoCraps.Vista;

import javax.swing.*;
import java.awt.*;

import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class PanelAcciones extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ListenerNavegador listener;
	private final Dado dadoResultado1 = new Dado(1, EstilosUI.AZUL_PROFUNDO, 50, false);
    private final Dado dadoResultado2 = new Dado(1, EstilosUI.AZUL_PROFUNDO, 50, false);
    
    private final JLabel labelSuma = new JLabel("—", SwingConstants.CENTER);
    private final JButton botonTirar         = new JButton("TIRAR DADOS");
    private final JButton botonQuitarApuesta = new JButton("QUITAR APUESTA");
    private final JButton botonConfirmarApuesta = new JButton("Confirmar Apuesta");
    
    public PanelAcciones() {
        this.setLayout(new GridBagLayout());
        this.setBackground(EstilosUI.AZUL_MEDIO);
        this.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 1));
        this.setPreferredSize(new Dimension(170, 0));

        GridBagConstraints gbc;

        // Título del panel
        JLabel labelTitulo = new JLabel("ÚLTIMO TIRO", SwingConstants.CENTER);
        labelTitulo.setFont(EstilosUI.FUENTE_TER);
        labelTitulo.setForeground(EstilosUI.DORADO);
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(0, 0, 2, 1, "HORIZONTAL", 1.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(12, 6, 8, 6);
        this.add(labelTitulo, gbc);

        // Dado izquierdo
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(1, 0, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(0, 6, 0, 3);
        this.add(dadoResultado1, gbc);

        // Dado derecho
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(1, 1, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(0, 3, 0, 6);
        this.add(dadoResultado2, gbc);

        // Etiqueta de suma
        labelSuma.setFont(EstilosUI.FUENTE_SEC);
        labelSuma.setForeground(EstilosUI.DORADO);
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(2, 0, 2, 1, "HORIZONTAL", 1.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(6, 6, 16, 6);
        this.add(labelSuma, gbc);

        // Separador visual
        JSeparator separador = new JSeparator();
        separador.setForeground(EstilosUI.DORADO);
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(3, 0, 2, 1, "HORIZONTAL", 1.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(0, 10, 10, 10);
        this.add(separador, gbc);

        // Botón tirar dados
        FuncionesUtilesDecoracion.configurarBotonPequeño(botonTirar);
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(4, 0, 2, 1, "HORIZONTAL", 1.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(0, 10, 8, 10);
        botonTirar.setEnabled(false);
        this.add(botonTirar, gbc);
        botonTirar.addActionListener(e->{
        	if(listener!=null) {
        		listener.alLanzarDados();
        	}
        });

        // Botón quitar apuesta
        FuncionesUtilesDecoracion.configurarBotonPequeño(botonQuitarApuesta);
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(5, 0, 2, 1, "HORIZONTAL", 1.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(0, 10, 8, 10);
        this.add(botonQuitarApuesta, gbc);
        botonQuitarApuesta.addActionListener(e->{
        	if(listener!=null) {
        		listener.alQuitarApuesta();
        	}
        });
        
        //confirmar apuesta
        FuncionesUtilesDecoracion.configurarBotonPequeño(botonConfirmarApuesta);
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(6, 0, 2, 1, "HORIZONTAL", 1.0, 0.0, GridBagConstraints.CENTER);
        gbc.insets = new Insets(0, 10, 8, 10);
        this.add(botonConfirmarApuesta, gbc);
        botonConfirmarApuesta.addActionListener(e->{
        	if(listener!=null) {
        		listener.alConfirmarApuesta();
        	}
        });

        // Espaciador inferior para empujar contenido hacia arriba
        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(6, 0, 2, 1, "BOTH", 1.0, 1.0, GridBagConstraints.CENTER);
        this.add(new JLabel(), gbc);
    }

//metodos    
    public void mostrarResultado(int valor1, int valor2) {
        labelSuma.setText("Salió: " + String.valueOf(valor1 + valor2));
        dadoResultado1.setValor(valor1);
        dadoResultado2.setValor(valor2);
        dadoResultado1.repaint();
        dadoResultado2.repaint();
    }
//getters
    public JButton getBotonTirar() {
        return botonTirar;
    }

    public JButton getBotonQuitarApuesta() {
        return botonQuitarApuesta;
    }
//setters
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
    }
}