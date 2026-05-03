package juegoCraps.Vista;

import javax.swing.*;
import java.awt.*;

import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class PanelFichas extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ListenerNavegador listener;
	
    public PanelFichas() {
    	
        this.setLayout(new BorderLayout());
        this.setBackground(EstilosUI.AZUL_MEDIO);
        this.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 1));
        this.setPreferredSize(new Dimension(0, 90));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 10));
        panelBotones.setBackground(EstilosUI.AZUL_MEDIO);

        JButton ficha_1 = crearFicha(1, EstilosUI.FICHA_AMARILLO);
        panelBotones.add(ficha_1);
        ficha_1.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_1();
        	}
        });

        JButton ficha_50 = crearFicha(50, EstilosUI.FICHA_AZUL);
        panelBotones.add(ficha_50);
        ficha_50.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_50();
        	}
        });
        
        JButton ficha_100 = crearFicha(100, EstilosUI.FICHA_CIAN);
        panelBotones.add(ficha_100);
        ficha_100.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_100();
        	}
        });
        
        JButton ficha_250 = crearFicha(250, EstilosUI.FICHA_GRIS);
        panelBotones.add(ficha_250);
        ficha_250.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_250();
        	}
        });
        
        JButton ficha_500 = crearFicha(500, EstilosUI.FICHA_LILA);
        panelBotones.add(ficha_500);
        ficha_500.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_500();
        	}
        });
        
        JButton ficha_750 = crearFicha(750, EstilosUI.FICHA_MORADO);
        panelBotones.add(ficha_750);
        ficha_750.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_750();
        	}
        });
        
        JButton ficha_1500 = crearFicha(1500, EstilosUI.FICHA_NARANJA);
        panelBotones.add(ficha_1500);
        ficha_1500.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_1500();
        	}
        });
        
        JButton ficha_2500 = crearFicha(2500, EstilosUI.FICHA_VERDE);
        panelBotones.add(ficha_2500);
        ficha_2500.addActionListener(e->{
        	if(listener != null) {
        		listener.alElegirFicha_2500();
        	}
        });
        
        JLabel labelTitulo = new JLabel("  FICHAS  ");
        labelTitulo.setFont(EstilosUI.FUENTE_SEC);
        labelTitulo.setForeground(EstilosUI.DORADO);
        this.add(labelTitulo, BorderLayout.WEST);

        JScrollPane scroll = new JScrollPane(panelBotones);
        scroll.setBorder(null);
        scroll.setBackground(EstilosUI.AZUL_MEDIO);
        scroll.getViewport().setBackground(EstilosUI.AZUL_MEDIO);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        this.add(scroll, BorderLayout.CENTER);
    }

    private JButton crearFicha(int valor, Color colorFicha) {
    	double vt = (int) valor;
        String etiqueta = vt >= 1000
                ? (vt / 1000) + "K"
                : String.valueOf(vt);

        JButton ficha = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                // Cuerpo circular de la ficha
                g2.setColor(colorFicha);
                g2.fillOval(2, 2, getWidth() - 4, getHeight() - 4);

                // Borde exterior dorado
                g2.setColor(EstilosUI.DORADO);
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(2, 2, getWidth() - 4, getHeight() - 4);

                // Borde interior blanco punteado
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1, new float[]{4, 3}, 0));
                g2.drawOval(7, 7, getWidth() - 14, getHeight() - 14);

                // Texto del valor centrado
                g2.setStroke(new BasicStroke(1));
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Serif", Font.BOLD, 13));
                FontMetrics fm = g2.getFontMetrics();
                int tx = (getWidth()  - fm.stringWidth(etiqueta)) / 2;
                int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(etiqueta, tx, ty);
                g2.dispose();
            }
        };

        ficha.setPreferredSize(new Dimension(60, 60));
        ficha.setContentAreaFilled(false);
        ficha.setBorderPainted(false);
        ficha.setFocusPainted(false);
        ficha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ficha.setToolTipText("$ " + String.format("%,d", valor));
        ficha.setActionCommand(String.valueOf(valor));
        return ficha;
    }
    
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
    }
    
}