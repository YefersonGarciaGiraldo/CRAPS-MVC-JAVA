package juegoCraps.Vista;
 
import javax.swing.*;
import java.awt.*;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class CeldaApuesta extends JButton {
	private static final long serialVersionUID = 1L;
	
    private final int num;
    private final String multiplicador;
    
    public CeldaApuesta(boolean dado, String multiplicador, int num) {
        this.multiplicador = multiplicador;
        this.num = num;
        
        //configuraciones del panel
        this.setLayout(new GridBagLayout());
        this.setBackground(EstilosUI.AZUL_MEDIO);
        this.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 2));
        this.setFocusPainted(false);
        
        GridBagConstraints gbc = FuncionesUtilesDecoracion.obtenerRestricciones(0, 0, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
 
        if (dado) {
            int cara1 = num / 2;
            int cara2 = num - cara1;
            Dado d1 = new Dado(cara1, EstilosUI.AZUL_PROFUNDO, 25, false);
            Dado d2 = new Dado(cara2, EstilosUI.AZUL_PROFUNDO, 25, false);
            this.add(d1, gbc);
            d1.setFocusable(false);
            d1.setInheritsPopupMenu(true);
            gbc = FuncionesUtilesDecoracion.obtenerRestricciones(
                    0, 1, 1, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
            this.add(d2, gbc);
            d2.setFocusable(false);
            d2.setInheritsPopupMenu(true);
        } else {
            JLabel labelNum = new JLabel(obtenerNombreNumero(num));
            labelNum.setFont(EstilosUI.FUENTE_TER);
            labelNum.setForeground(EstilosUI.BLANCO_PRIMARIO);
            this.add(labelNum, gbc);
            labelNum.setFocusable(false);
            labelNum.setInheritsPopupMenu(true);
        }
        
        JLabel labelMultiplicador = new JLabel(multiplicador + " a 1");
        labelMultiplicador.setFont(EstilosUI.FUENTE_TER);
        labelMultiplicador.setForeground(EstilosUI.DORADO);
 
        GridBagConstraints gbcMult = FuncionesUtilesDecoracion.obtenerRestricciones(
                1, 0, 2, 1, "NONE", 0.0, 0.0, GridBagConstraints.CENTER);
        gbcMult.insets = new Insets(2, 0, 0, 0);
        this.add(labelMultiplicador, gbcMult);
        labelMultiplicador.setFocusable(false);
        labelMultiplicador.setInheritsPopupMenu(true);
    }

    private String obtenerNombreNumero(int num) {
        switch (num) {
            case 2:  return "DOS";
            case 3:  return "TRES";
            case 4:  return "CUATRO";
            case 5:  return "CINCO";
            case 6:  return "SEIS";
            case 7:  return "S I E T E";
            case 8:  return "OCHO";
            case 10: return "DIEZ";
            case 11: return "ONCE";
            case 12: return "DOCE";
            default: return Integer.toString(num);
        }
    }
    public String getMultiplicador() {
        return this.multiplicador;
    }
    public int getNum() {
    	return this.num;
    }
}