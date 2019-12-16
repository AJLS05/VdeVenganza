package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;


public class Mapa extends PapaDeLosPollitos {
	
//	private int velocidad;
	

	public Mapa(int x, int y, String indiceImagen) {
		super(x, y, indiceImagen);
//		this.velocidad = velocidad;
		this.indiceImagen = indiceImagen;
	}

	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(indiceImagen), x, y);
//		graficos.drawImage(Juego.imagenes.get(indiceImagen), x, y);
	}
	
//	public void mover() {
//		if (Juego.espacio) {
//			this.velocidad = 20;
//		}else {
//			this.velocidad = 10; 	
//		}
//		if (Juego.derecha)
//			x-=velocidad;
//		
//		if (Juego.izquierda)
//			x+=velocidad;
//	}
//
//	public int getVelocidad() {
//		return velocidad;
//	}
//
//	public void setVelocidad(int velocidad) {
//		this.velocidad = velocidad;
//	}
//	

	
}
