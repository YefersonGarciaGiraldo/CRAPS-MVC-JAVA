 package juegoCraps.Vista;
 
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class TarjetaJugador extends PanelFondo {
	private static final long serialVersionUID = 1L;

	private final String nombreJugador;
    private final Color colorJugador;
    private Double saldoJugador;
    JLabel labelSaldo;
    
    public TarjetaJugador(String nombreJugador, Double saldoJugador) {
    		this.saldoJugador = saldoJugador;
        this.nombreJugador = nombreJugador;
        this.colorJugador  = generarColorAleatorio();
        this.cargarImagen("SiluetaUser.png");
 
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(colorJugador);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        if (imagen != null) {
            JLabel icono = new JLabel(
                    new ImageIcon(imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            this.add(icono, BorderLayout.CENTER);
            System.out.println("Imagen cargada");
        }
        
        labelSaldo = new JLabel("$ " + String.format("%.2f", saldoJugador));
        labelSaldo.setForeground(Color.YELLOW);
        labelSaldo.setFont(EstilosUI.FUENTE_SEC.deriveFont(Font.BOLD, 14f));
        labelSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(labelSaldo, BorderLayout.NORTH);
        
        JLabel labelNombre = new JLabel(nombreJugador);
        labelNombre.setForeground(EstilosUI.BLANCO_PRIMARIO);
        labelNombre.setFont(EstilosUI.FUENTE_SEC);
        labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(labelNombre, BorderLayout.SOUTH);

        
    }
//metodos
    private Color generarColorAleatorio() {
        Random random = new Random();
        return new Color(
                random.nextInt(155) + 100,
                random.nextInt(155) + 100,
                random.nextInt(155) + 100);
    }
    
    public void actualizarSaldo(Double nuevoSaldo) {
        this.saldoJugador = nuevoSaldo;
        labelSaldo.setText("$ " + String.format("%.2f", nuevoSaldo));
        this.repaint();
    }

//getters
    public String getNombreJugador() {
    		return nombreJugador;
    }
    public Double getSaldoJugador() {
    		return saldoJugador;
    }    
}