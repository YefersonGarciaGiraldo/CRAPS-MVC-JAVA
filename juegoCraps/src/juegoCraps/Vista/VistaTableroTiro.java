package juegoCraps.Vista;
 
import javax.swing.*;
import java.awt.*;

import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class VistaTableroTiro extends JPanel {
	private static final long serialVersionUID = 1L;
	private ListenerNavegador listener;
	
	private CeldaTiro passLineV;
	private CeldaTiro dontPassLineV;
	private CeldaTiro dontComeBar;
	private CeldaTiro come;
	private CeldaTiro big6;
	private CeldaTiro big8;
	private CeldaTiro field;
	private CeldaTiro passLineH;
	private CeldaTiro dontPassLineH;
	
	private java.util.Map<Integer, CeldaTiro> celdasNumericas = new java.util.HashMap<>();
	private Integer puntoActual = null;

	public VistaTableroTiro() {
        this.setLayout(new GridBagLayout());
        this.setBackground(EstilosUI.AZUL_PROFUNDO);
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        passLineV = new CeldaTiro("PASS LINE", true);
        agregarCelda(passLineV,0, 0, 1, 4);
        passLineV.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarPassLine();
        	}
        });
        
        dontPassLineV = new CeldaTiro("DON'T PASS LINE", true);
        agregarCelda(dontPassLineV ,0, 1, 1, 2);
        dontPassLineV.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarDontPassLine();
        	}
        });

        dontComeBar = new CeldaTiro("DONT COME BAR", false);
        agregarCelda(dontComeBar,0, 2, 1, 1);
        dontComeBar.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarDontComeBar();
        	}
        });
        
        //puntos
        int[] numeros = {4, 5, 6, 8, 9, 10};
        String[] labels = {"4", "5", "SIX", "8", "NINE", "10"};
        for(int i=0; i < numeros.length; i++) {
            CeldaTiro celda = new CeldaTiro(labels[i], (numeros[i]==6 || numeros[i]==9));
            celdasNumericas.put(numeros[i], celda);
            agregarCelda(celda, 0, 3 + i, 1, 1);
        }

        come = new CeldaTiro("COME", false);
        agregarCelda(come,1, 2, 6, 1);
        come.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarCome();
        	}
        });

        big6 = new CeldaTiro("BIG 6", false);
        agregarCelda(big6,2, 1, 1, 1);
        big6.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarBig6();
        	}
        });
        
        big8 = new CeldaTiro("BIG 8", false);
        agregarCelda(big8,3, 1, 1, 1);
        big8.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarBig8();
        	}
        });

        field = new CeldaTiro("2 · 3 · 4 · 9 ·   FIELD   · 10 · 11 · 12", false);
        agregarCelda(field, 2, 2, 6, 1);
        field.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarField();
        	}
        });

        dontPassLineH = new CeldaTiro("DON'T PASS LINE", false);
        agregarCelda(dontPassLineH, 3, 2, 6, 1); 
        dontPassLineH.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarDontPassLine();
        	}
        });
        
        passLineH = new CeldaTiro("PASS LINE", false);
        agregarCelda(passLineH,4, 0, 8, 1);
        passLineH.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarPassLine();
        	}
        });
    }
	
//metodos
    public void agregarCelda(Component c, int y, int x, int width, int height) {
        GridBagConstraints gbc = FuncionesUtilesDecoracion.obtenerRestricciones(y, x, width, height, "BOTH", 1.0, 1.0, GridBagConstraints.CENTER);
        this.add(c, gbc);
    }
    
    public void marcarPunto(int punto) {
        if (puntoActual != null && celdasNumericas.containsKey(puntoActual)) {
            CeldaTiro anterior = celdasNumericas.get(puntoActual);
            switch (puntoActual) {
                case 6:
                    anterior.setText("SIX");
                    break;

                case 9:
                    anterior.setText("NINE");
                    break;

                default:
                    anterior.setText(String.valueOf(puntoActual));
                    break;
            }

            anterior.setBackground(EstilosUI.AZUL_MEDIO);
        }
        if (punto > 0 && celdasNumericas.containsKey(punto)) {

            puntoActual = punto;

            CeldaTiro celda = celdasNumericas.get(punto);

            String textoBase = celda.getTextoBase();

            switch (punto) {

                case 6:
                    textoBase = "SIX";
                    break;

                case 9:
                    textoBase = "NINE";
                    break;

                default:
                    textoBase = String.valueOf(punto);
                    break;
            }

            celda.setText(
        	    "<html><center>"
        	    + textoBase
        	    + "<br><font color='yellow'><b>● ON</b></font>"
        	    + "</center></html>"
            );

            celda.setBackground(Color.WHITE);

        } else {

            puntoActual = null;
        }

        revalidate();
        repaint();
    }
    
//listeners
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
    }
        
}