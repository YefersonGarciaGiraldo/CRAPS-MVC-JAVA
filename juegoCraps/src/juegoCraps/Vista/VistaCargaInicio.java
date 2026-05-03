package juegoCraps.Vista;
 
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class VistaCargaInicio extends PanelFondo {
	private static final long serialVersionUID = 1L;
	private ListenerNavegador listener;
	
	private final JLabel labelBienvenida = new JLabel("BIENVENIDO");
    private final Dado dadoCarga = new Dado(5, EstilosUI.AZUL_PROFUNDO, 100, true);

    public VistaCargaInicio() {
        this.setLayout(new GridBagLayout());
        cargarImagen("FondoPantallaCarga.png");
       
        
        labelBienvenida.setForeground(EstilosUI.BLANCO_PRIMARIO);
        labelBienvenida.setFont(EstilosUI.FUENTE_PRI);
        GridBagConstraints gbc = FuncionesUtilesDecoracion.obtenerRestricciones(0, 0, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.NORTH);
        gbc.insets = new Insets(50, 0, 0, 0);
        this.add(labelBienvenida, gbc);

        gbc = FuncionesUtilesDecoracion.obtenerRestricciones(1, 0, 1, 1, "NONE", 0.0, 0.8, GridBagConstraints.CENTER);
        this.add(dadoCarga, gbc);
        iniciarAnimacion(); 
    }  
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
    }

//metodo para temporizador y notificacion a controlador
    private void iniciarAnimacion() {
        Timer temporizador = new Timer(3000, e -> {
            dadoCarga.detenerRotacion();
            if(listener != null) {
            	listener.alFinalizarCargaCARGA();
            }
        });
        temporizador.setRepeats(false);
        temporizador.start();
    }
}