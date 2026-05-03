 package juegoCraps.Vista;
 
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class PanelFondo extends JPanel {
	private static final long serialVersionUID = 1L;
	
    protected Image imagen;
    
    public void cargarImagen(String nombreImagen) {
        try {
            this.imagen = ImageIO.read(getClass().getResource("/" + nombreImagen));
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
 
            GradientPaint gradiente = new GradientPaint(
                    0, 0, new Color(0, 0, 0, 150),
                    0, getHeight(), new Color(0, 0, 0, 0));
            g2d.setPaint(gradiente);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
