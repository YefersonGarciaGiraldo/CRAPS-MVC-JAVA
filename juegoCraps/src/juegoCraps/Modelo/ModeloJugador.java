package juegoCraps.Modelo;

import juegoCraps.Modelo.ModeloJuego.rolJugador;
import java.util.ArrayList;
import java.util.List;

public class ModeloJugador {
	private ModeloApuesta misApuestas;

	private List<String> historialGanadas = new ArrayList<>();
	private rolJugador rol;
	
    private String nombre;
    private String detalleUltimaRonda;
    
    private Double saldo;
    private Double totalApostadoEnRonda;
    private Double gananciaUltimaRonda;
    private Double perdidaUltimaRonda;
    private Double totalGanadoHistorico;
    private Double totalApostadoHistorico;
    private boolean esTirador = false;

    public ModeloJugador(String nombre, Double saldo, rolJugador rol) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.misApuestas = new ModeloApuesta();
        this.rol = rol;
        this.totalApostadoEnRonda = 0.0;
        this.gananciaUltimaRonda = 0.0;
        this.perdidaUltimaRonda = 0.0;
        this.totalGanadoHistorico = 0.0;
        this.totalApostadoHistorico = 0.0;
        this.detalleUltimaRonda = "";
    }
    
//metodos
    public void reiniciarRonda() {
        totalApostadoEnRonda = 0.0;
        gananciaUltimaRonda = 0.0;
    }
    
    public void agregarDetalleUltimaRonda(String detalle) {

        detalleUltimaRonda += detalle + "\n";
    }
    
    public void limpiarDatosRonda() {
        totalApostadoEnRonda = 0.0;
        gananciaUltimaRonda = 0.0;
        perdidaUltimaRonda = 0.0;
        detalleUltimaRonda = "";
    }
    
    public void agregarHistorialGanada(String texto) {
        historialGanadas.add(texto);
    }

    public void limpiarResumenRonda() {

        totalApostadoEnRonda = 0.0;
        gananciaUltimaRonda = 0.0;
        perdidaUltimaRonda = 0.0;

        historialGanadas.clear();
    }

//getters
    public ModeloApuesta getModeloApuesta() {
        return misApuestas;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getSaldo() {
    	return saldo;
    }
    
    public boolean getEsTirador() {
        return esTirador;
    }
    
    public Double getTotalApostadoEnRonda() {
        return totalApostadoEnRonda;
    }
    
    public Double getTotalApostadoHistorico() {
        return totalApostadoHistorico;
    }

    public Double getGananciaUltimaRonda() {
        return gananciaUltimaRonda;
    }
    
    public rolJugador getRol() {
        return rol;
    }
    
    public Double getTotalGanadoHistorico() {
        return totalGanadoHistorico;
    }

    public Double getPerdidaUltimaRonda() {
        return perdidaUltimaRonda;
    }
    
    public String getDetalleUltimaRonda() {
        return detalleUltimaRonda;
    }
    
    public List<String> getHistorialGanadas() {
        return historialGanadas;
    }
    
//setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setEsTirador(boolean estadoRol) {
        this.esTirador = estadoRol;
    }

    public void setCambiarRol(rolJugador rol) {
        this.rol = rol;
    }

    public void setTotalApostadoEnRonda(double apuesta) {
        totalApostadoEnRonda += apuesta;
        totalApostadoHistorico += apuesta;
    }

    public void setGananciaUltimaRonda(double ganancia) {
        gananciaUltimaRonda += ganancia;
        totalGanadoHistorico += ganancia;
    }

    public void setPerdidaUltimaRonda(double perdida) {

        perdidaUltimaRonda += perdida;
    }

    public void setPerdidaUltimaRonda(Double perdida) {
        this.perdidaUltimaRonda += perdida;
    }
}
