package juegoCraps.Vista;
 
import javax.swing.*;
import java.awt.*;

import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Vista.FuncionesUtilesDecoracion.EstilosUI;

public class VistaPanioCentral extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ListenerNavegador listener;
	
	public CeldaApuesta seven;
	public CeldaApuesta sobre7;
	public CeldaApuesta bajo7;
	public CeldaApuesta cuernos11;
	public CeldaApuesta cuernos3;
	public CeldaApuesta cuernos2;
	public CeldaApuesta cuernos12;
	public CeldaApuesta duros4;
	public CeldaApuesta duros6;
	public CeldaApuesta duros10;
	public CeldaApuesta duros8;
	public CeldaApuesta aceDeuce3;
	public CeldaApuesta snakeEyes2;
	public CeldaApuesta boxCars12;
	public CeldaApuesta yo11;
	public JButton anyCraps;
	
    public VistaPanioCentral() {
        this.setLayout(new GridBagLayout());
        this.setBackground(EstilosUI.AZUL_PROFUNDO);
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
  
        // Seven
        seven = agregarCelda(new CeldaApuesta(false, "5", 7), 0, 0, 4, 1);
        seven.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarSeven();
    		}
    	});
        // Bajo y Sobre 7
        bajo7 = agregarCelda(new CeldaApuesta(false, "B A J O / 1", 7), 1, 0, 2, 1);
        bajo7.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarBajo7();
    		}
    	});
        sobre7 = agregarCelda(new CeldaApuesta(false, "S O B R E / 1", 7), 1, 2, 2, 1);
        sobre7.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarSobre7();
    		}
    	});
 
        // Cuernos
        agregarEtiquetaDecorada("C U E R N O S", 2, 0, 4, 1);
        cuernos11 = agregarCelda(new CeldaApuesta(true, "4", 11), 3, 0, 1, 1);
        cuernos11.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarCuernos11();
    		}
    	});
        cuernos3 = agregarCelda(new CeldaApuesta(true, "4", 3),  3, 1, 1, 1);
        cuernos3.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarCuernos3();
    		}
    	});
        cuernos2 = agregarCelda(new CeldaApuesta(true, "4", 2),  3, 2, 1, 1);
        cuernos2.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarCuernos2();
    		}
    	});
        cuernos12 = agregarCelda(new CeldaApuesta(true, "4", 12), 3, 3, 1, 1);
        cuernos12.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarCuernos12();
    		}
    	});
 
        // Duros
        agregarEtiquetaDecorada("D U R O S", 4, 0, 4, 1);
        duros4 = agregarCelda(new CeldaApuesta(true, "8", 4),  5, 0, 2, 1);
        duros4.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarDuros4();
    		}
    	});
        duros6 = agregarCelda(new CeldaApuesta(true, "8", 6),  5, 2, 2, 1);
        duros6.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarDuros6();
    		}
    	});
        duros10 = agregarCelda(new CeldaApuesta(true, "8", 10), 6, 0, 2, 1);
        duros10.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarDuros10();
    		}
    	});
        duros8 = agregarCelda(new CeldaApuesta(true, "8", 8),  6, 2, 2, 1);
        duros8.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarDuros8();
    		}
    	});
 
        // Apuestas individuales de número con alta paga
        aceDeuce3 = agregarCelda(new CeldaApuesta(true, "30", 3),  7, 0, 1, 1);
        aceDeuce3.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarAceDeuce3();
    		}
    	});
        snakeEyes2 = agregarCelda(new CeldaApuesta(true, "30", 2),  7, 1, 2, 1);
        snakeEyes2.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarSnakeEyes2();
    		}
    	});
        boxCars12 = agregarCelda(new CeldaApuesta(true, "30", 12), 7, 3, 1, 1);
        boxCars12.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarBoxCars12();
    		}
    	});
        yo11 = agregarCelda(new CeldaApuesta(true, "15", 11), 9, 0, 4, 1);
        yo11.addActionListener(e->{
    		if(listener!=null) {
    			listener.alApostarYo11();
    		}
    	});
 
        // Craps (cualquier craps)
        anyCraps = crearBotonCraps();
        agregarCText(anyCraps, 10, 0, 4, 1);
        anyCraps.addActionListener(e->{
        	if(listener != null) {
        		listener.alApostarAnyCraps();
        	}
        });
    }

//metodos
    private void agregarEtiquetaDecorada(String texto, int y, int x, int width, int height) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(EstilosUI.FUENTE_TER);
        label.setForeground(EstilosUI.BLANCO_PRIMARIO);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(EstilosUI.DORADO, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        agregarCText(label, y, x, width, height);
    }

    private JButton crearBotonCraps() {
        JButton btn = new JButton("C R A P S  7 a 1");
        btn.setFont(EstilosUI.FUENTE_TER);
        btn.setBackground(EstilosUI.AZUL_MEDIO);
        btn.setForeground(EstilosUI.DORADO);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(EstilosUI.DORADO, 2));
        return btn;
    }

    public void agregarCText(Component c, int y, int x, int width, int height) {
        GridBagConstraints gbc = FuncionesUtilesDecoracion.obtenerRestricciones(
                y, x, width, height, "BOTH", 1.0, 1.0, GridBagConstraints.CENTER);
        this.add(c, gbc);
    }
    
    public CeldaApuesta agregarCelda(CeldaApuesta c, int y, int x, int width, int height) {
        GridBagConstraints gbc = FuncionesUtilesDecoracion.obtenerRestricciones(
                y, x, width, height, "BOTH", 1.0, 1.0, GridBagConstraints.CENTER);
        this.add(c, gbc);
			return c;
    }

//setters
    public void setListener(ListenerNavegador listener) {
        this.listener = listener;
    }
}