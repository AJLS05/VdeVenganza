package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Tile extends PapaDeLosPollitos {
	
	private boolean  capturado;

	public Tile(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen, String indiceImagen, int tipo) {
	super(x, y, xImagen, yImagen, anchoImagen, altoImagen, indiceImagen, tipo);
}

	public Tile(int x, int y, String indiceImagen, int tipo) {
		super(x, y, indiceImagen);
		this.tipo = tipo;	
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		
		switch(this.tipo) {
		case 1: subImagen(0,0,80,70); break;
		case 2: subImagen(80,0,80,70); break;
		case 3: subImagen(160,0,80,70); break;
		case 4: subImagen(0,80,80,70); break;
		case 5: subImagen(80,240,80,-160); break;
		case 6: subImagen(160,240,80,-160); break;
		case 7: subImagen(0,160,80,80); break;
		case 8: subImagen(0,400,80,-160); break;
		case 9: subImagen(80,400,80,-160); break;
		case 10: subImagen(160,400,80,-160); break;
		case 11: subImagen(0,560,160,-160); break;
		case 12: subImagen(160,560,80,-160); break;
		case 13: subImagen(0,560,80,80); break;
		case 14: subImagen(320,0,80,80); break;
		
		}
	
	}
	
	public void subImagen(int x, int y, int ancho, int alto) {
		this.xImagen = x;
		this.yImagen = y;
		this.anchoImagen = ancho;
		this.altoImagen = alto;
	}
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
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
