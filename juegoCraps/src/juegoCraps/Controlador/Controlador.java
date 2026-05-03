package juegoCraps.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import juegoCraps.Interfaces.ListenerNavegador;
import juegoCraps.Modelo.ModeloJuego;
import juegoCraps.Modelo.ModeloJuego.estadoJugador;
import juegoCraps.Modelo.ModeloJuego.fase;
import juegoCraps.Modelo.ModeloJuego.rolJugador;
import juegoCraps.Modelo.ModeloJugador;
import juegoCraps.Vista.DialogoResumen;
import juegoCraps.Vista.VistaJFContenedor;


public class Controlador implements ListenerNavegador{
	
	private VistaJFContenedor JFContenedor;
	
    private ModeloJugador modelo;
    private ModeloJuego modeloFlujo;
    
    private List<ModeloJugador> listaJugadores;
    private int totalJugadores;
    private int contadorRegistrados;
    private int indiceTiradorActual = 0;
    private int indiceApostadorActual = 0;
    private double fichaActiva = 0.0;
//metodo principal
    public Controlador(ModeloJugador modelo, ModeloJuego modeloJuego) {
        this.modelo = modelo;
        this.modeloFlujo = modeloJuego;
        this.listaJugadores = new ArrayList<>();
        this.modeloFlujo.setControlador(this);
    }
    
    public void setVentana(VistaJFContenedor JFContenedor ) { 
    	this.JFContenedor = JFContenedor; 
    }
//metodos   
    public void prepararRegistro(int numJugadores) {
        this.totalJugadores = numJugadores;
        this.contadorRegistrados = 0;
        JFContenedor.actualizarRegistroNumJugadore(1);
    }

    public void procesarRegistro(String nombre) {
        if (contadorRegistrados >= totalJugadores) {
            return;
        }
        String nombreIngresado = nombre;
        if (nombreIngresado == null || nombreIngresado.isEmpty()) {
            System.out.println("Nombre vacío.");
            return;
        }
        boolean existe = listaJugadores.stream()
                .anyMatch(j -> j.getNombre().equalsIgnoreCase(nombreIngresado));

        if (existe) {
            System.out.println("El jugador ya existe: " + nombreIngresado);
            return;
        }
        rolJugador rol;
        if(contadorRegistrados == 0) { rol = rolJugador.TIRADOR;}
        else {rol = rolJugador.APOSTADOR;}
        listaJugadores.add(new ModeloJugador(nombreIngresado, 2500.00, rol));
        contadorRegistrados++;
        JFContenedor.limpiarCampoNombre();
        System.out.println(nombreIngresado + ":" + rol);//borrar

        if (contadorRegistrados < totalJugadores) {
            JFContenedor.prepararSiguienteJugador(contadorRegistrados);
        } else {
            listaJugadores.forEach(j -> System.out.println("Registrado: " + j.getNombre()));
            JFContenedor.finalizarRegistroVisual();
        }
    }
    
    public void ingresarJugadoresListaVisual() {
	    	boolean registroCompletado = this.registroCompleto();
	    	if(registroCompletado == true){
	    		for(ModeloJugador jugador : listaJugadores) {
	    			JFContenedor.agregarJugador(jugador);
	    		}
	    		actualizarInterfazTurno();
	    	}
    }

    public boolean registroCompleto() {
        return contadorRegistrados >= totalJugadores;
    }
    
    public boolean puedeRegistrarNombre() {
        return totalJugadores > 0;
    }
    
    public void verificarCambioTirador(estadoJugador resultado) {
    	if(resultado == ModeloJuego.estadoJugador.PIERDE) {
    		indiceTiradorActual ++;
    		if(indiceTiradorActual >= listaJugadores.size()) {
    			indiceTiradorActual =0;
    		}
    		for(int i = 0; i < listaJugadores.size(); i++) {
    			if(i == indiceTiradorActual) {
    				listaJugadores.get(i).setCambiarRol(rolJugador.TIRADOR);
    			}else {
    				listaJugadores.get(i).setCambiarRol(rolJugador.APOSTADOR);
    			}
    		}
    		actualizarInterfazTurno();
    	}
    }

    private void actualizarInterfazTurno() {

        if(listaJugadores.isEmpty()) {
            return;
        }

        if(indiceTiradorActual >= listaJugadores.size()) {
            indiceTiradorActual = 0;
        }

        ModeloJugador t = listaJugadores.get(indiceTiradorActual);

        JFContenedor.getVistaPrincipalJuego()
                .actualizarTurno(
                        t.getNombre(),
                        t.getRol().toString()
                );
    }
    
	private void reiniciarJuego() {
	    listaJugadores.clear();
	    totalJugadores = 0;
	    contadorRegistrados = 0;
	    indiceTiradorActual = 0;
	    indiceApostadorActual = 0;
	    fichaActiva = 0;
	    
	    modeloFlujo = new ModeloJuego();
	    modeloFlujo.setControlador(this);

	    JFContenedor.getVistaRegistro().restablecerFormulario();
	    JFContenedor.getVistaPrincipalJuego()
        .getPanelListaJugadores()
        .limpiarJugadores();
	    JFContenedor.cambiarVista("REGISTRO");
	    JFContenedor.limpiarCampoNombre();
	}
	
	private void actualizarTarjetas() {
	    for (int i = 0; i < listaJugadores.size(); i++) {
	        ModeloJugador j = listaJugadores.get(i);
	        
	        JFContenedor.getVistaPrincipalJuego()
	                    .getPanelListaJugadores()
	                    .actualizarSaldoTarjeta(i, j.getSaldo());
	    }
	}
	
	private void prepararSiguienteRonda() {

	    if(listaJugadores.isEmpty()) {
	        return;
	    }

	    for(ModeloJugador j : listaJugadores) {
	        j.reiniciarRonda();
	    }

	    indiceApostadorActual = 0;

	    fichaActiva = 0;

	    JFContenedor.getVistaPrincipalJuego()
	                .getVistaTablero()
	                .setEnabled(true);

	    JFContenedor.getVistaPrincipalJuego()
	                .getPanelAcciones()
	                .getBotonTirar()
	                .setEnabled(false);

	    if(indiceTiradorActual >= listaJugadores.size()) {
	        indiceTiradorActual = 0;
	    }

	    ModeloJugador tirador =
	            listaJugadores.get(indiceTiradorActual);

	    JFContenedor.getVistaPrincipalJuego()
	                .actualizarTurno(
	                        tirador.getNombre(),
	                        tirador.getRol().toString()
	                );
	}
	
//Getters
    public List<ModeloJugador> getListaJugadores() {
        return listaJugadores;
    }
    
	public ModeloJugador getModelo() {
		return this.modelo;
	}
	
//Listeners 
    //vista carga
	@Override
	public void alFinalizarCargaCARGA() {
		JFContenedor.cambiarVista("INICIO");
	}
	//vista inicio
	@Override
	public void alPresionarEntrarINICIO() {
		JFContenedor.cambiarVista("REGISTRO");
		
	}
	//vista registro
	@Override
	public void alHacerFocusNombreREGISTRO() {
		  if (!this.puedeRegistrarNombre()) {
          JOptionPane.showMessageDialog(null,"¡Primero ingresa la cantidad de jugadores y acepta!");
          JFContenedor.enfocarBotonCantidad();
      }	
	}
	@Override
	public void alBotonIniciarREGISTRO() {
		JFContenedor.cambiarVista("JUEGO");
		this.ingresarJugadoresListaVisual();
	}
	@Override
	public void alRegistrarJugadorREGISTRO(String nombre) {
		this.procesarRegistro(nombre);	
	}
	@Override
	public void alAceptarCantidadREGISTRO(int numJugadores) {
		this.prepararRegistro(numJugadores);
	}
	
//vista apuestas panio y tablero tiro
	@Override 
	public void alApostarEnZona(ModeloJuego.UbicacionApuesta zona) {
		if (indiceApostadorActual >= listaJugadores.size()) {
	        return; 
	    }
		 if(this.fichaActiva <= 0) {
			 JOptionPane.showMessageDialog(null, "Selecciona una ficha primero");
			 return;
		 }
		 if (indiceApostadorActual < listaJugadores.size()) {
			 ModeloJugador t = listaJugadores.get(indiceApostadorActual);
			 if(this.modeloFlujo.apuestEsValida(fichaActiva, t.getSaldo())){
				 t.setSaldo(t.getSaldo() - fichaActiva);
				 t.getModeloApuesta().ingresarApuesta(zona, fichaActiva);
				 JFContenedor.getVistaPrincipalJuego().getPanelListaJugadores()
				 .actualizarSaldoTarjeta(indiceApostadorActual, t.getSaldo());
				 System.out.println(t.getNombre() + " apostó $ " + fichaActiva + " en " + zona);
				 t.setTotalApostadoEnRonda(fichaActiva);
				 System.out.println(t.getNombre() + " total apuestas actuales: " + t.getTotalApostadoEnRonda());
			 }else {
				 JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
			 }
		 } else {
			 System.err.println("Error: Se intentó apostar fuera de turno.");
		 }
	}
	@Override
	public void alLanzarDados() {
	    ModeloJugador tirador = listaJugadores.get(indiceTiradorActual);
	    if (modeloFlujo.getFase() == fase.TIRADA_SALIDA && !modeloFlujo.validarTiroSalida(tirador)) {
	        JOptionPane.showMessageDialog(null, tirador.getNombre() + " debe apostar en Pass Line o Don't Pass para iniciar.");
	        return;
	    }
	    int d1 = (int) (Math.random() * 6) + 1;
	    int d2 = (int) (Math.random() * 6) + 1;
	    int suma = d1 + d2;
	    
	    JFContenedor.getVistaPrincipalJuego().getPanelAcciones().mostrarResultado(d1, d2);
	    estadoJugador resultado = modeloFlujo.procesarTirada(suma);
	    if(modeloFlujo.getFase() == fase.TIRADA_PUNTO) {
	        JFContenedor.getVistaPrincipalJuego().getVistaTablero().marcarPunto(modeloFlujo.getPunto());
	    } else {
	        JFContenedor.getVistaPrincipalJuego().getVistaTablero().marcarPunto(0);
	    }
	    modeloFlujo.recorrerApuestasMesa(suma, resultado);

	    StringBuilder reporte = new StringBuilder();

	    reporte.append("=====================================\n");
	    reporte.append("       RESULTADO DE LA RONDA\n");
	    reporte.append("=====================================\n\n");

	    reporte.append("TOTAL DEL TIRO: ")
	           .append(suma)
	           .append("\n\n");

	    for (ModeloJugador j : listaJugadores) {

	        double apostado = j.getTotalApostadoEnRonda();
	        double ganado = j.getGananciaUltimaRonda();
	        double perdido = j.getPerdidaUltimaRonda();
	        double neto = ganado - perdido;

	        reporte.append("╔══════════════════════════════════════╗\n");
	        reporte.append("   JUGADOR: ").append(j.getNombre().toUpperCase()).append("\n");
	        reporte.append("╚══════════════════════════════════════╝\n");

	        reporte.append("Rol actual: ").append(j.getRol()).append("\n");
	        reporte.append("Saldo actual: $").append(j.getSaldo()).append("\n\n");

	        reporte.append("TOTAL APOSTADO ESTA RONDA: $")
	               .append(apostado).append("\n");

	        reporte.append("PERDIDO ESTA RONDA: $")
	               .append(perdido).append("\n");

	        reporte.append("GANADO ESTA RONDA: $")
	               .append(ganado).append("\n");

	        reporte.append("BALANCE NETO: ")
	               .append(neto >= 0 ? "+" : "")
	               .append(neto)
	               .append("\n\n");
	        reporte.append("APUESTAS ACTIVAS EN MESA:\n");
	        if(j.getModeloApuesta().getApuestasActivas().isEmpty()) {
	            reporte.append("   - Ninguna\n");
	        } else {

	            for (Map.Entry<ModeloJuego.UbicacionApuesta, Double> entry :
	                    j.getModeloApuesta().getApuestasActivas().entrySet()) {
	                reporte.append("   • ")
	                       .append(entry.getKey())
	                       .append(" -> $")
	                       .append(entry.getValue())
	                       .append("\n");
	            }
	        }
	        reporte.append("\nDETALLE DE PREMIOS:\n");

	        if(j.getHistorialGanadas().isEmpty()) {
	            reporte.append("   - No ganó apuestas\n");

	        } else {

	            for(String premio : j.getHistorialGanadas()) {

	                reporte.append("   ✓ ")
	                       .append(premio)
	                       .append("\n");
	            }
	        }
	        reporte.append("\n==========================================\n\n");
	    }
	    actualizarTarjetas();
	    verificarCambioTirador(resultado);
	    tirador = listaJugadores.get(indiceTiradorActual);
	    DialogoResumen ventanaResumen =
	            new DialogoResumen(JFContenedor, reporte.toString());

	    ventanaResumen.setVisible(true);
	    listaJugadores.removeIf(j ->
	    j.getSaldo() <= 0 &&
	    j.getModeloApuesta().getApuestasActivas().isEmpty()
	);
	    if(listaJugadores.isEmpty()) {

	        JOptionPane.showMessageDialog(
	                null,
	                "TODOS LOS JUGADORES QUEBRARON"
	        );
	        reiniciarJuego();
	        return;
	    }
	    if(totalJugadores > 1 && listaJugadores.size() == 1) {

	        ModeloJugador ganador = listaJugadores.get(0);

	        JOptionPane.showMessageDialog(
	                null,
	                "GANADOR FINAL: " + ganador.getNombre()
	        );
	        reiniciarJuego();
	        return;
	    }
	    if(indiceTiradorActual >= listaJugadores.size()) {
	        indiceTiradorActual = 0;
	    }
	    JFContenedor.getVistaPrincipalJuego().revalidate();
	    JFContenedor.getVistaPrincipalJuego().repaint();
	    prepararSiguienteRonda();
	}

//listeners del panio central apuestas
	@Override
	public void alApostarSeven() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.SEVEN);
	}

	@Override
	public void alApostarBajo7() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.BAJO_7);
	}

	@Override
	public void alApostarSobre7() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.SOBRE_7);
	}

	@Override
	public void alApostarCuernos11() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.CUERNOS_11);
	}

	@Override
	public void alApostarCuernos3() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.CUERNOS_3);
	}

	@Override
	public void alApostarCuernos2() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.CUERNOS_2);
	}

	@Override
	public void alApostarCuernos12() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.CUERNOS_12);
	}

	@Override
	public void alApostarDuros4() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.DUROS_4);
	}

	@Override
	public void alApostarDuros6() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.DUROS_6);
	}

	@Override
	public void alApostarDuros10() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.DUROS_10);
	}

	@Override
	public void alApostarDuros8() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.DUROS_8);
	}

	@Override
	public void alApostarAceDeuce3() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.ACE_DEUCE_3);
	}

	@Override
	public void alApostarSnakeEyes2() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.SNAKE_EYES_2);
	}

	@Override
	public void alApostarBoxCars12() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.BOX_CARS_12);
	}

	@Override
	public void alApostarYo11() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.YO_11);
	}

	@Override
	public void alApostarAnyCraps() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.ANY_CRAPS);
	}
	
//listeners de apuestas tablero tiro
	@Override
	public void alApostarPassLine() {
		fase faseActual = modeloFlujo.getFase();
		if(faseActual != fase.TIRADA_SALIDA) {
			JOptionPane.showMessageDialog(null, "Solo se puede apostar en esta casilla en la tirada de salida");
		}else {
			this.alApostarEnZona(ModeloJuego.ubicacionApuestaLineBet.PASS_LINE);
		}
	}

	@Override
	public void alApostarDontPassLine() {
		fase faseActual = modeloFlujo.getFase();
		if(faseActual != fase.TIRADA_SALIDA) {
			JOptionPane.showMessageDialog(null, "Solo se puede apostar en esta casilla en la tirada de salida");
		}else {
			this.alApostarEnZona(ModeloJuego.ubicacionApuestaLineBet.DONT_PASS_LINE);
		}
		
	}

	@Override
	public void alApostarDontComeBar() {
		fase faseActual = modeloFlujo.getFase();
		if(faseActual != fase.TIRADA_PUNTO) {
			JOptionPane.showMessageDialog(null, "Solo se puede apostar en esta casilla despues de la tirada de salida");
		}else {
			this.alApostarEnZona(ModeloJuego.ubicacionApuestaLineBet.DONT_COME_BAR);
		}
	}

	@Override
	public void alApostarCome() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaLineBet.COME);
	}

	@Override
	public void alApostarBig6() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaLineBet.BIG_6);
	}

	@Override
	public void alApostarBig8() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaLineBet.BIG_8);
	}

	@Override
	public void alApostarField() {
		this.alApostarEnZona(ModeloJuego.ubicacionApuestaOneShot.FIELD);
	}

//Listeners del panel fichas
	@Override
	public void alElegirFicha_1() {
		this.fichaActiva = 1.0;
	}

	@Override
	public void alElegirFicha_50() {
		this.fichaActiva = 50.0;	
	}

	@Override
	public void alElegirFicha_100() {
		this.fichaActiva = 100.0;
	}

	@Override
	public void alElegirFicha_250() {
		this.fichaActiva = 250.0;
	}

	@Override
	public void alElegirFicha_500() {
		this.fichaActiva = 500.0;
	}

	@Override
	public void alElegirFicha_750() {
		this.fichaActiva = 750.0;
	}

	@Override
	public void alElegirFicha_1500() {
		this.fichaActiva = 1500.0;
	}

	@Override
	public void alElegirFicha_2500() {
		this.fichaActiva = 2500.0;
	}

//Listener del panel Acciones
	@Override
	public void alQuitarApuesta() {
	
	    if(listaJugadores.isEmpty()) {
	        return;
	    }
	
	    if(indiceApostadorActual >= listaJugadores.size()) {
	        indiceApostadorActual = 0;
	    }
	
	    ModeloJugador actual = listaJugadores.get(indiceApostadorActual);
	
	    double totalRecuperado = 0;
	
	    for (Double monto : actual.getModeloApuesta().getApuestasActivas().values()) {
	        totalRecuperado += monto;
	    }
	
	    actual.setSaldo(actual.getSaldo() + totalRecuperado);
	
	    actual.getModeloApuesta().getApuestasActivas().clear();
	
	    JFContenedor.getVistaPrincipalJuego()
	        .getPanelListaJugadores()
	        .actualizarSaldoTarjeta(indiceApostadorActual, actual.getSaldo());
	
	    JOptionPane.showMessageDialog(null,
	            "Apuestas eliminadas. Recuperaste $" + totalRecuperado);
	}

	@Override
	public void alConfirmarApuesta() {
	    indiceApostadorActual++;
	    javax.swing.SwingUtilities.invokeLater(() -> {
	        if (indiceApostadorActual < listaJugadores.size()) {
	            ModeloJugador proximo = listaJugadores.get(indiceApostadorActual);
	            JFContenedor.getVistaPrincipalJuego().actualizarTurno(proximo.getNombre(), "APOSTADOR");
	            JOptionPane.showMessageDialog(JFContenedor, "Turno de apuesta: " + proximo.getNombre());
	        } else {
	            ModeloJugador tirador = listaJugadores.get(indiceTiradorActual);
	            JFContenedor.getVistaPrincipalJuego().actualizarTurno(tirador.getNombre(), "¡A TIRAR!");
	            JFContenedor.getVistaPrincipalJuego().getPanelAcciones().getBotonTirar().setEnabled(true);
	            JFContenedor.getVistaPrincipalJuego().getVistaTablero().setEnabled(false);
	            
	            JOptionPane.showMessageDialog(JFContenedor, "Apuestas cerradas. ¡Tirador, lance los dados!");
	        }
	    });
	}
}