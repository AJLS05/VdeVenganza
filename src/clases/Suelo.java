package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Suelo extends PapaDeLosPollitos {

	public Suelo(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen, String indiceImagen,
			int tipo) {
		super(x, y, xImagen, yImagen, anchoImagen, altoImagen, indiceImagen, tipo);
		
	}
	
	public Suelo(int x, int y, String indiceImagen, int tipo) {
		super(x, y, indiceImagen);
		this.tipo = tipo;	
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
	
		switch (this.tipo) {
		case 1: subImagen(0, 240, 80, 80); break;

		
		}
	}

	public void subImagen(int x, int y, int ancho, int alto) {
		this.xImagen = x;
		this.yImagen = y;
		this.anchoImagen = ancho;
		this.altoImagen = alto;
	}
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImagen, altoImagen);
	}
	
}
