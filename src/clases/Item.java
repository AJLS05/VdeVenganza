package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item extends PapaDeLosPollitos {
	
	private boolean capturado;
	

	 public Item(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen, String indiceImagen,
			 int tipo, boolean capturado) {
		super(x, y, xImagen, yImagen, anchoImagen, altoImagen, indiceImagen, tipo);
		
		
		this.capturado = capturado;
		this.anchoImagen = (int)Juego.imagenes.get(this.indiceImagen).getWidth();
		this.altoImagen = (int)Juego.imagenes.get(this.indiceImagen).getHeight();
	 }
	 
	 public Item(int x, int y,String indiceImagen, int tipo) {
		super(x, y,indiceImagen);
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.anchoImagen = (int)Juego.imagenes.get(this.indiceImagen).getWidth();
		this.altoImagen = (int)Juego.imagenes.get(this.indiceImagen).getHeight();
		
		switch(this.tipo) {
		case 1: subImagen(19,15,45,36); break;
		case 2: subImagen(83,7,48,47); break;
		}
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	public void mover() {
		
	}
	
	public void subImagen(int x, int y, int ancho, int alto) {
		this.xImagen = x;
		this.yImagen = y;
		this.anchoImagen = ancho;
		this.altoImagen = alto;
	}
	
	public void pintar(GraphicsContext graficos) {
		if (this.capturado)
			return;
		
		graficos.strokeRect(this.x, this.y, this.anchoImagen, this.altoImagen);
		graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImagen, altoImagen);
	}
	

	public boolean isCapturado() {
		return capturado;
	}
	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
}
