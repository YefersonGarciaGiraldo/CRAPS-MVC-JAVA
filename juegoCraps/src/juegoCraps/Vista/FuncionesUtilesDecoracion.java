package juegoCraps.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FuncionesUtilesDecoracion {
	
	public static class EstilosUI {
		//Colores Base
	    public static final Color AZUL_PROFUNDO = new Color(11, 45, 79);
	    public static final Color AZUL_MEDIO = new Color(23, 67, 110);
	    public static final Color AZUL_GRD = new Color(11, 45, 79, 80);
	    public static final Color DORADO = new Color(198, 166, 100);
	    public static final Color BLANCO_SECUNDARIO = new Color(224, 224, 224);
	    public static final Color BLANCO_PRIMARIO = new Color(255, 255, 255);
	
	    //Colores Fichas
	    public static final Color FICHA_GRIS = new Color(160, 165, 175);
	    public static final Color FICHA_ROJO = new Color(165, 42, 42);
	    public static final Color FICHA_VERDE = new Color(34, 100, 70);
	    public static final Color FICHA_AZUL = new Color(40, 80, 130);
	    public static final Color FICHA_MORADO = new Color(90, 50, 110);
	    public static final Color FICHA_NARANJA = new Color(180, 90, 40);
	    public static final Color FICHA_CIAN = new Color(50, 130, 140);
	    public static final Color FICHA_AMARILLO = new Color(160, 140, 40);
	    public static final Color FICHA_VINO = new Color(100, 30, 30);
	    public static final Color FICHA_LILA = new Color(130, 100, 160);
	
	    //Tipografía
	    public static final Font FUENTE_TER = new Font("Serif", Font.BOLD, 10);
	    public static final Font FUENTE_SEC = new Font("Serif", Font.BOLD, 20);
	    public static final Font FUENTE_PRI = new Font("Serif", Font.BOLD, 40);
	}

    public static void configurarVentana(JFrame ventana) {
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setMinimumSize(new Dimension(1024, 720));
        ventana.setLocationRelativeTo(null);
        ventana.getContentPane().setBackground(EstilosUI.AZUL_PROFUNDO);
        ventana.getRootPane()
               .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
               .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrar");

        ventana.getRootPane().getActionMap().put("cerrar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
            }
        });
    }

    public static GridBagConstraints obtenerRestricciones(
            int gridy, int gridx, int gridWidth, int gridHeight,
            String fill, double weightx, double weighty, int anchor) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx      = gridx;
        gbc.gridy      = gridy;
        gbc.gridwidth  = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.anchor     = anchor;
        gbc.weightx    = weightx;
        gbc.weighty    = weighty;

        switch (fill) {
            case "HORIZONTAL": gbc.fill = GridBagConstraints.HORIZONTAL; break;
            case "BOTH":       gbc.fill = GridBagConstraints.BOTH;       break;
            case "VERTICAL":   gbc.fill = GridBagConstraints.VERTICAL;   break;
            default:           gbc.fill = GridBagConstraints.NONE;       break;
        }

        return gbc;
    }

    public static void configurarBoton(JButton boton) {
        boton.setBackground(EstilosUI.AZUL_PROFUNDO);
        boton.setForeground(EstilosUI.DORADO);
        boton.setFont(EstilosUI.FUENTE_SEC);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public static void configurarBotonPequeño(JButton boton) {
        boton.setBackground(EstilosUI.AZUL_PROFUNDO);
        boton.setForeground(EstilosUI.DORADO);
        boton.setFont(EstilosUI.FUENTE_TER);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void campoConPlaceholder(JTextField campo, String texto) {
        campo.setText(texto);
        campo.setForeground(Color.GRAY);
        campo.setFont(EstilosUI.FUENTE_SEC);
        campo.setBackground(EstilosUI.BLANCO_SECUNDARIO);
        campo.setBorder(null);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(texto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(texto);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }
}