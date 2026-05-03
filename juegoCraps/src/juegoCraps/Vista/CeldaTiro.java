package juegoCraps.Vista;

import javax.swing.*;
import java.awt.*;

import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class CeldaTiro extends JButton {

    private static final long serialVersionUID = 1L;

    private String textoBase;

    public CeldaTiro(String text, boolean vertical) {

        this.textoBase = text;

        setBackground(EstilosUI.AZUL_MEDIO);

        setForeground(EstilosUI.BLANCO_PRIMARIO);

        setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 2));

        setFocusPainted(false);

        setFont(EstilosUI.FUENTE_TER);

        setContentAreaFilled(true);

        setOpaque(true);

        setHorizontalAlignment(SwingConstants.CENTER);

        setVerticalAlignment(SwingConstants.CENTER);

        setPreferredSize(new Dimension(120, 80));

        setMinimumSize(new Dimension(120, 80));

        setMaximumSize(new Dimension(120, 80));

        if(vertical) {
            setText(convertirTextoVertical(text));
        } else {
            setText(text);
        }
    }

    public String getTextoBase() {
        return textoBase;
    }

    private String convertirTextoVertical(String text) {

        StringBuilder sb = new StringBuilder("<html><center>");

        for(char c : text.toCharArray()) {

            if(c == ' ') {
                sb.append("<br><br>");
            } else {
                sb.append(c).append("<br>");
            }
        }

        sb.append("</center></html>");

        return sb.toString();
    }
}