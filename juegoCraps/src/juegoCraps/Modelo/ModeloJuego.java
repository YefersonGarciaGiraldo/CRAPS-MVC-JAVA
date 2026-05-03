package juegoCraps.Modelo;
import java.util.Map;
import juegoCraps.Controlador.Controlador;
import juegoCraps.Interfaces.ListenerNavegador;

public class ModeloJuego {
	private Controlador controlador;
	private ModeloJugador modeloJugador;
	
	private ListenerNavegador listener;
	public interface UbicacionApuesta{}
	
//usamos enums como controladores de flujo del juego, para normalizar informacion y 
//parametros posibles, obteniendo una fuente de verdad centralizada
	
	public enum fase{TIRADA_SALIDA,TIRADA_PUNTO}
	public enum estadoJugador{GANA,PIERDE,CONTINUA}
	public enum rolJugador{TIRADOR,APOSTADOR}

	public enum ubicacionApuestaLineBet implements UbicacionApuesta{ 
		
		PASS_LINE, DONT_PASS_LINE,COME, DONT_COME_BAR, BIG_6, BIG_8
	}
	
	public enum ubicacionApuestaOneShot implements UbicacionApuesta{
		
		FIELD,
		SEVEN, BAJO_7, SOBRE_7,
		CUERNOS_11, CUERNOS_3, CUERNOS_2, CUERNOS_12, 
		DUROS_4, DUROS_6, DUROS_10, DUROS_8,
		ACE_DEUCE_3, SNAKE_EYES_2, BOX_CARS_12,
		YO_11,
		ANY_CRAPS;
		
	}
	
	private fase faseActual = fase.TIRADA_SALIDA;
	private int punto = 0;
	
//metodos
	public estadoJugador procesarTirada(int sumaDados) {
		if(faseActual == fase.TIRADA_SALIDA) {
			return procesarTiradaSalida(sumaDados);
		}else {
			return procesarTiradaPunto(sumaDados);
		}
	}
	
	private estadoJugador procesarTiradaSalida(int suma) {
		if(suma == 7 || suma == 11) {
			return estadoJugador.GANA;
		}else if(suma == 2 || suma == 3 || suma == 12) {
			return estadoJugador.PIERDE;
		}else {
			this.punto = suma;
			this.faseActual = fase.TIRADA_PUNTO;
			return estadoJugador.CONTINUA;
		}
	}
	
	private estadoJugador procesarTiradaPunto(int suma) {
		if(suma == punto ) {
			faseActual = fase.TIRADA_SALIDA;
			return estadoJugador.GANA;
		}else if(suma == 7) {
			faseActual = fase.TIRADA_SALIDA;
			return estadoJugador.PIERDE;
		}else {
			return estadoJugador.CONTINUA;
		}
	}
	
	public boolean validarTiroSalida(ModeloJugador jugador) {
		Map<UbicacionApuesta, Double> mapa = jugador.getModeloApuesta().getApuestasActivas();
		
	    return mapa.containsKey(ubicacionApuestaLineBet.PASS_LINE) || 
	           mapa.containsKey(ubicacionApuestaLineBet.DONT_PASS_LINE);
	}
	
	public boolean apuestEsValida(Double monto, Double saldoJugador) {
		if(saldoJugador < monto) {return false;
		}else {return true;}
	}
	
	public void siPerdioApuesta(Double montoApostado) {
		Double nuevoSaldo = this.modeloJugador.getSaldo() - montoApostado;
		this.modeloJugador.setSaldo(nuevoSaldo);
	}
	
	public void pagarApuesta( UbicacionApuesta ubicacion, Double montoApostado, int sumaDados) {
		int multiplicador = getMultiplicador(ubicacion, sumaDados);
		Double cantidadAPagar = montoApostado * multiplicador;
		modeloJugador.setSaldo(modeloJugador.getSaldo() + cantidadAPagar);
	}
	
	public void apostar(double monto) {
		this.modeloJugador.setSaldo(modeloJugador.getSaldo()-monto);
	}
	
	public void recorrerApuestasMesa(int sumaDados, estadoJugador resultadoTirador) {

	    for (ModeloJugador jugador : this.controlador.getListaJugadores()) {

	        this.modeloJugador = jugador;

	        Map<UbicacionApuesta, Double> apuestas =
	                jugador.getModeloApuesta().getApuestasActivas();

	        java.util.Iterator<Map.Entry<UbicacionApuesta, Double>> it =
	                apuestas.entrySet().iterator();

	        while (it.hasNext()) {

	            Map.Entry<UbicacionApuesta, Double> entrada = it.next();

	            UbicacionApuesta ubicacion = entrada.getKey();

	            Double monto = entrada.getValue();

	            boolean remover = false;

	            double pago = 0;

	            if (ubicacion instanceof ubicacionApuestaLineBet) {

	                if (ubicacion == ubicacionApuestaLineBet.PASS_LINE) {

	                    if (resultadoTirador == estadoJugador.GANA) {

	                        int multiplicador =
	                                getMultiplicador(ubicacion, sumaDados);

	                        pago = monto * multiplicador;

	                        pagarApuesta(ubicacion, monto, sumaDados);

	                        jugador.setGananciaUltimaRonda(pago);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Ganó $" + pago
	                                + " en PASS LINE con "
	                                + sumaDados
	                        );

	                        remover = true;

	                    } else if (resultadoTirador == estadoJugador.PIERDE) {

	                        jugador.setPerdidaUltimaRonda(monto);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Perdió $" + monto
	                                + " en PASS LINE"
	                        );

	                        remover = true;
	                    }
	                }

	                else if (ubicacion == ubicacionApuestaLineBet.DONT_PASS_LINE) {

	                    if (resultadoTirador == estadoJugador.PIERDE) {

	                        int multiplicador =
	                                getMultiplicador(ubicacion, sumaDados);

	                        pago = monto * multiplicador;

	                        pagarApuesta(ubicacion, monto, sumaDados);

	                        jugador.setGananciaUltimaRonda(pago);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Ganó $" + pago
	                                + " en DON'T PASS LINE con "
	                                + sumaDados
	                        );

	                        remover = true;

	                    } else if (resultadoTirador == estadoJugador.GANA) {

	                        jugador.setPerdidaUltimaRonda(monto);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Perdió $" + monto
	                                + " en DON'T PASS LINE"
	                        );

	                        remover = true;
	                    }
	                }

	                else if (ubicacion == ubicacionApuestaLineBet.COME) {

	                    if (resultadoTirador == estadoJugador.GANA) {

	                        int multiplicador =
	                                getMultiplicador(ubicacion, sumaDados);

	                        pago = monto * multiplicador;

	                        pagarApuesta(ubicacion, monto, sumaDados);

	                        jugador.setGananciaUltimaRonda(pago);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Ganó $" + pago
	                                + " en COME con "
	                                + sumaDados
	                        );

	                        remover = true;

	                    } else if (resultadoTirador == estadoJugador.PIERDE) {

	                        jugador.setPerdidaUltimaRonda(monto);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Perdió $" + monto
	                                + " en COME"
	                        );

	                        remover = true;
	                    }
	                }

	                else if (ubicacion == ubicacionApuestaLineBet.DONT_COME_BAR) {

	                    if (resultadoTirador == estadoJugador.PIERDE) {

	                        int multiplicador =
	                                getMultiplicador(ubicacion, sumaDados);

	                        pago = monto * multiplicador;

	                        pagarApuesta(ubicacion, monto, sumaDados);

	                        jugador.setGananciaUltimaRonda(pago);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Ganó $" + pago
	                                + " en DON'T COME BAR"
	                        );

	                        remover = true;

	                    } else if (resultadoTirador == estadoJugador.GANA) {

	                        jugador.setPerdidaUltimaRonda(monto);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Perdió $" + monto
	                                + " en DON'T COME BAR"
	                        );

	                        remover = true;
	                    }
	                }

	                else if (ubicacion == ubicacionApuestaLineBet.BIG_6) {

	                    if (sumaDados == 6) {

	                        pago = monto * 2;

	                        pagarApuesta(ubicacion, monto, sumaDados);

	                        jugador.setGananciaUltimaRonda(pago);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Ganó $" + pago
	                                + " en BIG 6"
	                        );

	                        remover = true;

	                    } else if (sumaDados == 7) {

	                        jugador.setPerdidaUltimaRonda(monto);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Perdió $" + monto
	                                + " en BIG 6"
	                        );

	                        remover = true;
	                    }
	                }

	                else if (ubicacion == ubicacionApuestaLineBet.BIG_8) {

	                    if (sumaDados == 8) {

	                        pago = monto * 2;

	                        pagarApuesta(ubicacion, monto, sumaDados);

	                        jugador.setGananciaUltimaRonda(pago);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Ganó $" + pago
	                                + " en BIG 8"
	                        );

	                        remover = true;

	                    } else if (sumaDados == 7) {

	                        jugador.setPerdidaUltimaRonda(monto);

	                        jugador.agregarDetalleUltimaRonda(
	                                "Perdió $" + monto
	                                + " en BIG 8"
	                        );

	                        remover = true;
	                    }
	                }
	            }

	            else {

	                if (verificarSiGanaOneShot(ubicacion, sumaDados)) {

	                    int multiplicador =
	                            getMultiplicador(ubicacion, sumaDados);

	                    pago = monto * multiplicador;

	                    pagarApuesta(ubicacion, monto, sumaDados);

	                    jugador.setGananciaUltimaRonda(pago);

	                    jugador.agregarDetalleUltimaRonda(
	                            "Ganó $" + pago
	                            + " con "
	                            + sumaDados
	                            + " en "
	                            + ubicacion
	                    );

	                } else {

	                    jugador.setPerdidaUltimaRonda(monto);

	                    jugador.agregarDetalleUltimaRonda(
	                            "Perdió $" + monto
	                            + " en "
	                            + ubicacion
	                    );
	                }

	                remover = true;
	            }

	            if (remover) {

	                it.remove();
	            }
	        }
	    }
	}
	
	private boolean verificarSiGanaOneShot(UbicacionApuesta ubicacion, int suma) {

	    if (ubicacion == ubicacionApuestaOneShot.FIELD) {
	        return (suma == 2 ||suma == 3 ||suma == 4 ||suma == 9 ||suma == 10 ||suma == 11 ||suma == 12);
	    }
	    if (ubicacion == ubicacionApuestaOneShot.SEVEN) {return (suma == 7);}
	    if (ubicacion == ubicacionApuestaOneShot.BAJO_7) {return (suma < 7);}
	    if (ubicacion == ubicacionApuestaOneShot.SOBRE_7) {return (suma > 7);}
	    if (ubicacion == ubicacionApuestaOneShot.CUERNOS_2) {return (suma == 2);}
	    if (ubicacion == ubicacionApuestaOneShot.CUERNOS_3) {return (suma == 3);}
	    if (ubicacion == ubicacionApuestaOneShot.CUERNOS_11) {return (suma == 11);}
	    if (ubicacion == ubicacionApuestaOneShot.CUERNOS_12) {return (suma == 12);}
	    if (ubicacion == ubicacionApuestaOneShot.DUROS_4) {return (suma == 4);}
	    if (ubicacion == ubicacionApuestaOneShot.DUROS_6) {return (suma == 6);}
	    if (ubicacion == ubicacionApuestaOneShot.DUROS_8) {return (suma == 8);}
	    if (ubicacion == ubicacionApuestaOneShot.DUROS_10) {return (suma == 10);}
	    if (ubicacion == ubicacionApuestaOneShot.SNAKE_EYES_2) {return (suma == 2);}
	    if (ubicacion == ubicacionApuestaOneShot.ACE_DEUCE_3) {return (suma == 3);}
	    if (ubicacion == ubicacionApuestaOneShot.YO_11) {return (suma == 11);}
	    if (ubicacion == ubicacionApuestaOneShot.BOX_CARS_12) {return (suma == 12);}
	    if (ubicacion == ubicacionApuestaOneShot.ANY_CRAPS) {return (suma == 2 ||suma == 3 ||suma == 12);}
	    return false;
	}
	
//getters
	public int getMultiplicador(UbicacionApuesta ubicacion, int sumaDados) {
		//esta funcion obtiene el multiplicador de paga para la apuesta segun la casilla a la que se apostó
		int multiplicador = 0;
		if(ubicacion == ubicacionApuestaLineBet.PASS_LINE || ubicacion == ubicacionApuestaLineBet.DONT_PASS_LINE ||
				ubicacion == ubicacionApuestaLineBet.COME || ubicacion == ubicacionApuestaLineBet.DONT_COME_BAR ||
				ubicacion == ubicacionApuestaLineBet.BIG_6 || ubicacion == ubicacionApuestaLineBet.BIG_8 ||
				ubicacion == ubicacionApuestaOneShot.BAJO_7 || ubicacion == ubicacionApuestaOneShot.SOBRE_7 || 
				ubicacion == ubicacionApuestaOneShot.FIELD
				) {
			if((ubicacion == ubicacionApuestaOneShot.FIELD && sumaDados == 2) || 
					(ubicacion == ubicacionApuestaOneShot.FIELD && sumaDados == 12) ) {
				multiplicador = 3;
			}else {
				multiplicador = 2;
			}
				
		}else if(ubicacion == ubicacionApuestaOneShot.CUERNOS_11 || ubicacion == ubicacionApuestaOneShot.CUERNOS_3 || 
				ubicacion == ubicacionApuestaOneShot.CUERNOS_2 || ubicacion == ubicacionApuestaOneShot.CUERNOS_12) {
			multiplicador = 4;
		}else if(ubicacion == ubicacionApuestaOneShot.DUROS_10 || ubicacion == ubicacionApuestaOneShot.DUROS_4 ||
				ubicacion == ubicacionApuestaOneShot.DUROS_6 || ubicacion == ubicacionApuestaOneShot.DUROS_8) {
			multiplicador = 8;
		}else if(ubicacion == ubicacionApuestaOneShot.ACE_DEUCE_3 || ubicacion == ubicacionApuestaOneShot.SNAKE_EYES_2||
				ubicacion == ubicacionApuestaOneShot.BOX_CARS_12) {
			multiplicador = 30;
		}else if(ubicacion == ubicacionApuestaOneShot.SEVEN) {
			multiplicador = 15;
		}else if(ubicacion == ubicacionApuestaOneShot.ANY_CRAPS) {
			multiplicador = 7;
		}
		return multiplicador;
	}
	
	public fase getFase() {
		return faseActual;
	}
	
	public void setControlador(Controlador controlador) {
	    this.controlador = controlador;
	}
	
	public int getPunto() {
		return punto;
	}
	public ListenerNavegador getListener() {
		return listener;
	}
	
}
