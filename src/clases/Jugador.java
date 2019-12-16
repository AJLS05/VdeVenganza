package clases;

import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Jugador extends PapaDeLosPollitos{
	private int velocidad;
	private double velociadadAnimacion  ;
	private double num;
	private HashMap<String, Animacion> animaciones;
	private String animacionActual ;
	private int orientacion = 1;
	private int arp =0;
	private int puntuacion = 0;
	private String ultimaAnimacion = "descanso";
	public Juego juego;

	public Jugador(int x, int y, String indiceImagen, int velocidad, String animacionActual ) {
		super(x, y,indiceImagen);
		this.velocidad = velocidad;
		this.velociadadAnimacion = num;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}

	public void inicializarAnimaciones() {
		
		Rectangle[] CoorrdenadasDescanso = {
				new Rectangle(126,1024,57,81),
		};
		animaciones.put("descanso",new Animacion("descanso",0.20,CoorrdenadasDescanso));
		Rectangle[] CoorrdenadasCaminarAbajo = {
				new Rectangle(25,1024,57,78),
				new Rectangle(126,1024,57,78),
				new Rectangle(227,1024,57,79),
				new Rectangle(328,1026,57,77),
				new Rectangle(429,1024,58,81),
				new Rectangle(531,1024,57,81),
				new Rectangle(632,1024,57,79),
				new Rectangle(733,1026,56,77),
				new Rectangle(835,1024,57,81)
		};
		
		animaciones.put("caminarAbajo",new Animacion("caminarAbajo",0.08,CoorrdenadasCaminarAbajo));
		Rectangle[] CoordenadasCaminarArriba = {
				new Rectangle(12,821,61,77),
				new Rectangle(115,821,61,77),
				new Rectangle(216,822,61,77),
				new Rectangle(317,823,59,78),
				new Rectangle(420,822,59,77),
				new Rectangle(521,822,56,76),
				new Rectangle(623,821,58,78),
				new Rectangle(725,823,58,78),
				new Rectangle(825,821,59,78),
				
		};
		animaciones.put("caminarArriba", new Animacion("caminarArriba",0.08, CoordenadasCaminarArriba));
		Rectangle[] CoorrdenadasCaminarHorizontal = {
				new Rectangle(20,1126,58,76),
				new Rectangle(121,1127,57,76),
				new Rectangle(222,1126,57,76),
				new Rectangle(322,1126,59,76),
				new Rectangle(422,1126,60,77),
				new Rectangle(521,1127,63,77),
				new Rectangle(623,1126,62,77),
				new Rectangle(726,1126,60,77),
				new Rectangle(828,1126,59,77),
				
		};
		animaciones.put("horizontal",new Animacion("horizontal",0.10,CoorrdenadasCaminarHorizontal));
		
	}
	
	public void mover() {
		this.animacionActual = "descanso";
		
		if (Juego.espacio) {
			this.velociadadAnimacion = 0.01;
			this.velocidad = 5;
			this.indiceImagen = "esq";
		}else {
			this.velociadadAnimacion = 0.15;
			this.velocidad = 2;
			this.indiceImagen = "esq";
		}
			
		if (Juego.derecha) {
				
			x+= velocidad;
			this.animacionActual = "horizontal";
			this.orientacion = 1;
			arp = 0;				
		}
		
		if (Juego.izquierda) {
			if(true)
			x-= velocidad;
			this.animacionActual = "horizontal";
			this.orientacion = -1;
			arp = 60;
			
		}
		
		if (Juego.arriba) {
			y-= velocidad;
			this.animacionActual = "caminarArriba";
		}
		
		if (Juego.abajo) {
			y+= velocidad;
			this.animacionActual = "caminarAbajo";
		}
		if (x>1120) {
			x= 20;
		}
	}

	
	public void actualizarAnimacion(double time) {
		Rectangle rectanguloActual = animaciones.get(animacionActual).calcularFrameActual(time);
		this.xImagen = (int)rectanguloActual.getX();
		this.yImagen = (int)rectanguloActual.getY();
		this.anchoImagen = (int)rectanguloActual.getWidth();
		this.altoImagen = (int)rectanguloActual.getHeight();
	}
	
	
	public void pintar (GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x+arp, y, (orientacion)*anchoImagen, altoImagen);
	}

	public void agregarPuntuacion(int puntuacion) {
		this.puntuacion+= puntuacion;
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	public boolean verificarColision (Tile tiles) {
		if (obtenerRectangulo().intersects(tiles.obtenerRectangulo().getBoundsInLocal()) ) {
			//no mover parametro de X e Y
			System.out.println("colision con una pared");
			if (Juego.derecha) {
				
				x-=velocidad;
				this.animacionActual = "descanso";
				arp = 0;
							
			}
			
			if (Juego.izquierda) {
				if(true)
				x+= velocidad;
				this.animacionActual = "descanso";;
				this.orientacion = -1;
				arp = 60;
				
			}
			
			if (Juego.arriba) {
				y+= velocidad;
				this.animacionActual = "descanso";;
			}
			
			if (Juego.abajo) {
				y-= velocidad;
				this.animacionActual = "descanso";;
			}
			return true;
		}
		return false;
	}
	public boolean verificarColision(Item item) {
		if (obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal()) && !item.isCapturado()) {
			item.setCapturado(true);
			agregarPuntuacion(1);
			System.out.println("Colisionaron");
			return true;
		}
		return false;
	}

	
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public HashMap<String, Animacion> getAnimaciones() {
		return animaciones;
	}

	public void setAnimaciones(HashMap<String, Animacion> animaciones) {
		this.animaciones = animaciones;
	}

	public String getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}

	public double getVelociadadAnimacion() {
		return velociadadAnimacion;
	}

	public void setVelociadadAnimacion(double velociadadAnimacion) {
		this.velociadadAnimacion = velociadadAnimacion;
	}

	public int getArp() {
		return arp;
	}

	public void setArp(int arp) {
		this.arp = arp;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getUltimaAnimacion() {
		return ultimaAnimacion;
	}

	public void setUltimaAnimacion(String ultimaAnimacion) {
		this.ultimaAnimacion = ultimaAnimacion;
	}
	
}
