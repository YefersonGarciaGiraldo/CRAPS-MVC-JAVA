package juegoCraps.Modelo;
import juegoCraps.Modelo.ModeloJuego.UbicacionApuesta;
import java.util.HashMap;
import java.util.Map;

public class ModeloApuesta {
	private Map<UbicacionApuesta, Double> apuestasActivas;
	
	public ModeloApuesta() {
		this.apuestasActivas = new HashMap<>();
	}
	
	public void ingresarApuesta(UbicacionApuesta lugar, Double cantidad) {
		apuestasActivas.put(lugar, cantidad);
	}
	
	public void eliminarApuesta(UbicacionApuesta lugar) {
		apuestasActivas.remove(lugar);
	}
	
	public Map<UbicacionApuesta, Double> getApuestasActivas() {
		return apuestasActivas;
	}
}
