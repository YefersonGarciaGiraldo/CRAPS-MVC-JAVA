package juegoCraps;
import juegoCraps.Controlador.Controlador;
import juegoCraps.Modelo.ModeloJuego;
import juegoCraps.Modelo.ModeloJuego.rolJugador;
import juegoCraps.Modelo.ModeloJugador;
import juegoCraps.Vista.VistaJFContenedor;

public class Main {
    public static void main(String[] args) {
        ModeloJugador modelo = new ModeloJugador("Por defecto", 0.0, rolJugador.APOSTADOR);
        ModeloJuego modeloFlujo = new ModeloJuego();
        Controlador controlador = new Controlador(modelo, modeloFlujo);
        VistaJFContenedor ventana = new VistaJFContenedor(controlador);
        controlador.setVentana(ventana);
        ventana.setVisible(true);
    }
}