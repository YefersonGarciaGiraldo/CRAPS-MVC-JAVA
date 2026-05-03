package juegoCraps.Vista;
 
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class Dado extends JPanel {
	private static final long serialVersionUID = 1L;
	private int valor;
    private final int tamanio;
    private final Color colorFondo;
    private final boolean rotarDado;
    private double angulo = 0;
    private Timer timerRotacion;
    
    public Dado(int valor, Color colorFondo, int tamanio, boolean rotarDado) {
        this.valor      = valor;
        this.colorFondo = colorFondo;
        this.tamanio    = tamanio;
        this.rotarDado  = rotarDado;
        int tamanioPanelSeguro = (int) (tamanio * Math.sqrt(2));
        this.setPreferredSize(new Dimension(tamanioPanelSeguro, tamanioPanelSeguro));
        this.setOpaque(false);
 
        if (this.rotarDado) {
            iniciarTimerRotacion();
        }
    }
    private void iniciarTimerRotacion() {
        timerRotacion = new Timer(16, e -> {
            angulo = (angulo + 5.0) % 360;
            repaint();
        });
        timerRotacion.start();
    }
    public void detenerRotacion() {
        if (timerRotacion != null) {
            timerRotacion.stop();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
        if (rotarDado) {
            g2.rotate(Math.toRadians(angulo), getWidth() / 2.0, getHeight() / 2.0);
        }
 
        // Cuerpo del dado
        int offsetX = (getWidth()  / 4) + 2;
        int offsetY = (getHeight() / 4) + 2;
        int lado    = tamanio - (tamanio / 3);
 
        g2.setColor(colorFondo);
        g2.fillRoundRect(offsetX, offsetY, lado, lado, 5, 5);
 
        g2.setColor(EstilosUI.DORADO);
        g2.drawRoundRect(offsetX, offsetY, lado - 1, lado - 1, 5, 5);
 
        // Puntos del dado
        g2.setColor(EstilosUI.DORADO);
        int tamPunto = tamanio / 10;
        int margen   = tamanio / 6;
        int cx = (getWidth()  / 2) - (tamPunto / 2);
        int cy = (getHeight() / 2) - (tamPunto / 2);
 
        if (valor == 1 || valor == 3 || valor == 5) {
            g2.fillOval(cx, cy, tamPunto, tamPunto);
        }
        if (valor > 1) {
            g2.fillOval(cx - margen, cy - margen, tamPunto, tamPunto);
            g2.fillOval(cx + margen, cy + margen, tamPunto, tamPunto);
        }
        if (valor > 3) {
            g2.fillOval(cx + margen, cy - margen, tamPunto, tamPunto);
            g2.fillOval(cx - margen, cy + margen, tamPunto, tamPunto);
        }
        if (valor == 6) {
            g2.fillOval(cx - margen, cy, tamPunto, tamPunto);
            g2.fillOval(cx + margen, cy, tamPunto, tamPunto);
        }
    }
    public int getTamanioDado() {
        return tamanio;
    }
    public void setValor(int valor) {
    	this.valor = valor;
    }
}