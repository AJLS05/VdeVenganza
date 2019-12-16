package clases;

import javafx.scene.shape.Rectangle;

public class Animacion {
	private String nombreAnimacion;
	private int  frameActual=0;
	private double duracion;
	private int cantidadFrames;
	private Rectangle[] coordenadasImagenes;
	public Animacion(String nombreAnimacion, double duracion, Rectangle[] coordenadasImagenes) {
		this.nombreAnimacion = nombreAnimacion;
		this.duracion = duracion;
		this.coordenadasImagenes = coordenadasImagenes;
		cantidadFrames = this.coordenadasImagenes.length;
	}
	public Rectangle calcularFrameActual(double time) {
		this.frameActual = (int)((time % (cantidadFrames * duracion)) / duracion);
        System.out.println(this.frameActual);
        return coordenadasImagenes[this.frameActual];
	}
	
	public String getNombreAnimacion() {
		return nombreAnimacion;
	}
	public void setNombreAnimacion(String nombreAnimacion) {
		this.nombreAnimacion = nombreAnimacion;
	}
	public int getFrameActual() {
		return frameActual;
	}
	public void setFrameActual(int frameActual) {
		this.frameActual = frameActual;
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	public int getCantidadFrames() {
		return cantidadFrames;
	}
	public void setCantidadFrames(int cantidadFrames) {
		this.cantidadFrames = cantidadFrames;
	}
	public Rectangle[] getCoordenadasImagenes() {
		return coordenadasImagenes;
	}
	public void setCoordenadasImagenes(Rectangle[] coordenadasImagenes) {
		this.coordenadasImagenes = coordenadasImagenes;
	}
	
}
