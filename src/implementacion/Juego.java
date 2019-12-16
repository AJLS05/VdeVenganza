package implementacion;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Item;
import clases.Jugador;
import clases.Suelo;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Juego extends Application{
	private Scene escena;
	private Group root;
	private Canvas lienzo;
	private GraphicsContext graficos; 

	public static boolean derecha;
	public static boolean izquierda;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean espacio;
	public boolean Verificador = true;
	//	public static boolean defensa;
	public int nivel = 1;
	long inicioPartida = System.nanoTime();


	public static HashMap<String,Image> imagenes;
	private ArrayList<Tile> tiles;
	private ArrayList<Suelo> suelos;
	private ArrayList<Item> items;
	private Jugador jugador;
	private Tile tile;
	public int itemsColectados = 0;
	PerspectiveCamera camara = new PerspectiveCamera();

	public static void main(String[] args) {
		launch(args);
	}

	private int[][] itemsMap = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,2,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,0,0,0,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	private int[][] tilemap = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{4,0,4,0,4,0,4,0,0,0,0,0,0,4},
			{4,0,4,0,0,0,4,0,0,4,4,4,4,4},
			{4,0,4,0,0,0,0,0,4,4,4,0,4,4},
			{4,0,0,0,0,0,0,0,0,4,4,0,4,4},
			{4,0,0,0,4,0,0,0,0,0,0,0,0,2},
			{4,0,0,0,4,0,4,0,4,0,0,0,0,2},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tilemapCompletado = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{4,0,4,0,4,0,4,0,0,0,0,0,0,4},
			{4,0,4,0,0,0,4,0,0,4,4,4,4,4},
			{4,0,4,0,0,0,0,0,4,4,4,0,4,4},
			{4,0,0,0,0,0,0,0,0,4,4,0,4,4},
			{4,0,0,0,4,0,0,0,0,0,0,0,0,0},
			{4,0,0,0,4,0,4,0,4,0,0,0,0,0},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] items2Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,2,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,0,0,0,0,2,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile2map = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,0,4,0,0,4,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,4,0,0,0,0,2},
			{4,4,4,4,4,4,0,0,0,0,0,0,0,4},
			{4,0,0,4,0,0,0,0,0,0,4,4,0,4},
			{4,4,0,0,0,4,0,0,4,0,4,4,0,4},
			{4,0,0,0,0,4,0,0,0,0,0,4,0,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tile2mapCompletado = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,0,4,0,0,4,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,4,0,0,0,0,0},
			{4,4,4,4,4,4,0,0,0,0,0,0,0,4},
			{4,0,0,4,0,0,0,0,0,0,4,4,0,4},
			{4,4,0,0,0,4,0,0,4,0,4,4,0,4},
			{4,0,0,0,0,4,0,0,0,0,0,4,0,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] items3Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,2,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,0,0,0,0,2,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile3map = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,0,0,4,0,0,0,4,0,0,0,2},
			{2,0,0,4,0,0,0,0,4,0,0,0,0,2},
			{4,4,4,4,4,4,0,0,0,0,0,4,0,4},
			{4,0,0,0,0,0,0,0,0,0,4,4,0,4},
			{4,4,4,0,0,4,4,4,4,0,4,4,0,4},
			{4,0,0,0,0,4,0,0,0,0,0,4,0,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tile3mapCompletado = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,0,0,4,0,0,0,4,0,0,0,0},
			{2,0,0,4,0,0,0,0,4,0,0,0,0,0},
			{4,4,4,4,4,4,0,0,0,0,0,4,0,4},
			{4,0,0,0,0,0,0,0,0,0,4,4,0,4},
			{4,4,4,0,0,4,4,4,4,0,4,4,0,4},
			{4,0,0,0,0,4,0,0,0,0,0,4,0,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] items4Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,2,0,2,0,2,0,0,0,0,2,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile4map = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,4,0,0,0,2},
			{4,4,0,4,0,0,0,0,0,4,0,0,4,4},
			{4,0,0,0,0,4,4,0,0,4,0,0,4,4},
			{4,4,0,4,0,4,4,4,0,4,0,4,4,4},
			{4,0,0,0,0,4,0,0,0,4,0,0,4,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tile4mapCompletado = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,0,4,0,0,0,0},
			{4,4,0,4,0,0,0,0,0,4,0,0,4,4},
			{4,0,0,0,0,4,4,0,0,4,0,0,4,4},
			{4,4,0,4,0,4,4,4,0,4,0,4,4,4},
			{4,0,0,0,0,4,0,0,0,4,0,0,4,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] items5Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,2,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile5mapA = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,4,4,4,0,4,0,4,4,0,0,2},
			{2,0,0,0,0,0,0,4,0,0,0,0,0,2},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,4,4,4,4,4,0,4,4,4,4,4,4,4},
			{4,4,4,4,4,4,0,4,4,4,4,4,4,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	//	mapa para cambiar in.game
	//	private int[][] tile5mapB = {
	//
	//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
	//			{2,0,0,4,4,4,0,4,0,4,4,0,0,2},
	//			{2,0,0,0,0,0,0,4,0,0,0,0,0,2},
	//			{4,0,0,0,0,0,0,0,0,0,0,0,0,4},
	//			{4,4,4,4,4,4,2,4,4,4,4,4,4,4},
	//			{4,4,4,4,4,4,0,4,4,4,4,4,4,4},
	//			{4,0,0,0,2,0,0,0,2,0,0,0,0,4},
	//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
	//
	//	};
	private int[][] tile5mapCompletado = {

			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{2,0,0,4,4,4,0,4,0,4,4,0,0,0},
			{2,0,0,0,0,0,0,4,0,0,0,0,0,0},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,4,4,4,4,4,0,4,4,4,4,4,4,4},
			{4,4,4,4,4,4,0,4,4,4,4,4,4,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tilemapFinal = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,4,4,4,4,4,0,0,4,0,3,0,0},
			{0,0,4,0,0,4,4,4,0,4,3,0,3,0},
			{0,0,4,4,0,4,4,0,4,4,0,3,0,0},
			{0,0,4,0,0,4,4,0,0,4,0,3,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},

	};
	private int[][] suelo = {

			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},

	};


	public void start(Stage ventana) {
		cargarImagen();
		crearObjetosJuego();
		ventana.setTitle("Animation Timer");
		root = new Group();
		escena = new Scene(root,1120,620);
		registrarEventos();
		lienzo = new Canvas(1120,620);
		graficos = lienzo.getGraphicsContext2D();
		ventana.setScene(escena);
		root.getChildren().add(lienzo);
		cicloPrincipal();
		ventana.show();
	}

	public void cicloPrincipal() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {

			@Override
			public void handle(long tiempoActualNanoSegundos) {
				double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
				pintar();
				actualizar(t);
			}
		};
		animationTimer.start();
	}

	public void pintar () {
		graficos.fillRect(0, 0, 1120, 460);

		for (int i=0; i<suelos.size(); i++) 
			suelos.get(i).pintar(graficos);

		for (int i=0; i<tiles.size(); i++)
			tiles.get(i).pintar(graficos);

		for (int i=0; i<items.size(); i++)
			items.get(i).pintar(graficos);

		graficos.setFont(new Font(60));
		puntuacion();
		jugador.pintar(graficos);

	}
	public void puntuacion() {
		graficos.fillText(String.valueOf("Mapas: " + jugador.getPuntuacion()+"/4"), 10d, 60d);
	}


	public void actualizar(double time) {

		jugador.actualizarAnimacion(time);
		jugador.mover();
		System.out.println(nivel);
		itemsColectados = jugador.getPuntuacion();
		System.out.println("Items colectados"+itemsColectados);
		if (itemsColectados==4) {
			actualizarTiles();
		}
		if (jugador.getX()>=1020) {
			nivel=nivel+1;
			crearObjetosJuego();
		}
		for (int i=0; i<items.size();i++) {
			if (jugador.verificarColision(items.get(i)))
				items.remove(items.get(i));
		}
		for (int i=0; i<tiles.size();i++) {
			if (jugador.verificarColision(tiles.get(i))) {
				if(jugador.getX() == tile.getAnchoImagen())
					tiles.remove(tiles.get(i));
			}
		}
	}

	public void registrarEventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "RIGHT":
					derecha = true;
					break;
				case "LEFT":
					izquierda = true;
					break;
				case "UP":
					arriba = true;
					break;
				case "DOWN":
					abajo = true;
					break;
				case "SPACE":
					espacio = true;
					break;
				}
			}
		});

		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "RIGHT":
					derecha = false;
					break;
				case "LEFT":
					izquierda = false;
					break;
				case "UP":
					arriba = false;
					break;
				case "DOWN":
					abajo = false;
					break;
				case "SPACE":
					espacio = false;
					break;
				}
			}
		});
	}

	public void cargarImagen() {
		imagenes = new HashMap<String,Image>();
		imagenes.put("ob1", new Image("obtaculosEsenario.png"));		
		imagenes.put("esq",  new Image("Esqueleto.png"));
		imagenes.put("items", new Image("items.png"));
		imagenes.put("suelos", new Image("suelos.png"));
	}
	public void crearObjetosJuego() {

		jugador = new Jugador(82,82,"esq",2,"descanso");
		tiles = new ArrayList<Tile>();
		suelos = new ArrayList<Suelo>();
		items = new ArrayList<Item>();

		for (int i=0; i<suelo.length; i++) {
			for (int j=0; j<suelo[i].length; j++) {
				if (suelo[i][j] != 0) {
					suelos.add(new Suelo(j*80,i*80,"suelos",suelo[i][j]));
				}		
			}
		}
		if (nivel==1) {
			System.out.println("pintar nivel 1");
			for (int i=0; i<itemsMap.length; i++) {
				for (int j=0; j<itemsMap[i].length; j++) {
					if (itemsMap[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",itemsMap[i][j]));
					}		
				}
			}

			for (int i=0; i<tilemap.length; i++) {
				for (int j=0; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tilemap[i][j]));
					}		
				}
			}
		}
		if (nivel==2) {
			System.out.println("pintar nivel 2");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items2Map.length; i++) {
				for (int j=0; j<items2Map[i].length; j++) {
					if (items2Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items2Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile2map.length; i++) {
				for (int j=0; j<tile2map[i].length; j++) {
					if (tile2map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile2map[i][j]));
					}		
				}

			}
		}
		if (nivel==3) {
			System.out.println("pintar nivel 3");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items3Map.length; i++) {
				for (int j=0; j<items3Map[i].length; j++) {
					if (items3Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items3Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile3map.length; i++) {
				for (int j=0; j<tile4map[i].length; j++) {
					if (tile3map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile3map[i][j]));
					}		
				}

			}
		}
		if (nivel==4) {
			System.out.println("pintar nivel 4");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items4Map.length; i++) {
				for (int j=0; j<items4Map[i].length; j++) {
					if (items4Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items4Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile4map.length; i++) {
				for (int j=0; j<tile4map[i].length; j++) {
					if (tile4map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile4map[i][j]));
					}		
				}

			}
		}
		if (nivel==5) {
			System.out.println("pintar nivel 5");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items5Map.length; i++) {
				for (int j=0; j<items5Map[i].length; j++) {
					if (items5Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items5Map[i][j]));
					}		
				}
			}
			for (int i=0; i<tile5mapA.length; i++) {
				for (int j=0; j<tile5mapA[i].length; j++) {
					if (tile5mapA[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile5mapA[i][j]));
					}		
				}

			}
		}
		if (nivel==6) {
			System.out.println("pintar nivel 5");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}
			for (int i=0; i<tilemapFinal.length; i++) {
				for (int j=0; j<tilemapFinal[i].length; j++) {
					if (tilemapFinal[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tilemapFinal[i][j]));
					}		
				}

			}
			mostrarTiempo();
		}
	}

	private void mostrarTiempo() {
		graficos.fillText(String.valueOf("Tiempo: " + ((System.nanoTime()) - inicioPartida) / 1000000000.0), 11d, 61d);
	}

	public void actualizarTiles() {
		if (nivel==1) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 1 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tilemapCompletado.length; i++) {
					for (int j=0; j<tilemapCompletado[i].length; j++) {
						if (tilemapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tilemapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==2) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 2 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile2mapCompletado.length; i++) {
					for (int j=0; j<tile2mapCompletado[i].length; j++) {
						if (tile2mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile2mapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==3) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 3 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile3mapCompletado.length; i++) {
					for (int j=0; j<tile3mapCompletado[i].length; j++) {
						if (tile3mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile3mapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==4) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 4 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile4mapCompletado.length; i++) {
					for (int j=0; j<tile4mapCompletado[i].length; j++) {
						if (tile4mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile4mapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==5) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 5 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile5mapCompletado.length; i++) {
					for (int j=0; j<tile5mapCompletado[i].length; j++) {
						if (tile5mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile5mapCompletado[i][j]));
						}		
					}
				}
			}
		}
	}
}