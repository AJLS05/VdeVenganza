package clases;

public class PapaDeLosPollitos {
	protected int x;
	protected int y;
	protected int xImagen;
	protected int yImagen;
	protected int anchoImagen;
	protected int altoImagen;
	protected String indiceImagen;
	protected int tipo;
	
	public PapaDeLosPollitos(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen,
			String indiceImagen, int tipo) {
		this.x = x;
		this.y = y;
		this.xImagen = xImagen;
		this.yImagen = yImagen;
		this.anchoImagen = anchoImagen;
		this.altoImagen = altoImagen;
		this.indiceImagen = indiceImagen;
		this.tipo = tipo;
	}

	public PapaDeLosPollitos(int x, int y, String indiceImagen) {
		super();
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxImagen() {
		return xImagen;
	}

	public void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}

	public int getyImagen() {
		return yImagen;
	}

	public void setyImagen(int yImagen) {
		this.yImagen = yImagen;
	}

	public int getAnchoImagen() {
		return anchoImagen;
	}

	public void setAnchoImagen(int anchoImagen) {
		this.anchoImagen = anchoImagen;
	}

	public int getAltoImagen() {
		return altoImagen;
	}

	public void setAltoImagen(int altoImagen) {
		this.altoImagen = altoImagen;
	}

	public String getIndiceImagen() {
		return indiceImagen;
	}

	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
}
