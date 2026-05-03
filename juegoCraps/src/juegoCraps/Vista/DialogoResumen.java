package juegoCraps.Vista;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DialogoResumen extends JDialog {
	private static final long serialVersionUID = 1L;

	public DialogoResumen(JFrame padre, String texto) {

        super(padre, "Resumen de la ronda", true);

        setLayout(new BorderLayout());

        JTextArea areaTexto = new JTextArea(texto);

        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaTexto);
        JButton btnContinuar = new JButton("CONTINUAR");
        btnContinuar.addActionListener(e -> dispose());

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnContinuar);
        
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        setSize(700, 500);
        setLocationRelativeTo(padre);
    }
}