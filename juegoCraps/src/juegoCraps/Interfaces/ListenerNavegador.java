package juegoCraps.Interfaces;

import juegoCraps.Modelo.ModeloJuego.UbicacionApuesta;

public interface ListenerNavegador {
	//listeners vista carga inicio
    void alFinalizarCargaCARGA();
    //listeners Inicio
	void alPresionarEntrarINICIO();
	//listeners registro
	void alHacerFocusNombreREGISTRO();
	void alBotonIniciarREGISTRO();
	void alRegistrarJugadorREGISTRO(String nombre);
	void alAceptarCantidadREGISTRO(int numJugadores);
	
	//Listeners modelo juego
	void alApostarEnZona(UbicacionApuesta zona);
	void alLanzarDados();
	void alQuitarApuesta();
	void alConfirmarApuesta();
	
	//Listeners apuestas
	//panio central
	void alApostarSeven();
	void alApostarBajo7();
	void alApostarSobre7();
	void alApostarCuernos11();
	void alApostarCuernos3();
	void alApostarCuernos2();
	void alApostarCuernos12();
	void alApostarDuros4();
	void alApostarDuros6();
	void alApostarDuros10();
	void alApostarDuros8();
	void alApostarAceDeuce3();
	void alApostarSnakeEyes2();
	void alApostarBoxCars12();
	void alApostarYo11();
	void alApostarAnyCraps();
	
	//tablero tiro
	void alApostarPassLine();
	void alApostarDontPassLine();
	void alApostarDontComeBar();
	void alApostarCome();
	void alApostarBig6();
	void alApostarBig8();
	void alApostarField();
	
	//listeners fichas panel
	void alElegirFicha_1();
	void alElegirFicha_50();
	void alElegirFicha_100();
	void alElegirFicha_250();
	void alElegirFicha_500();
	void alElegirFicha_750();
	void alElegirFicha_1500();
	void alElegirFicha_2500();
	
}