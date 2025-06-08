package Fabricas;

import Enemigos.*;

import Plataformas.*;
import PowerUps.*;
import Juego.*;
import Jugador.BolaDeFuego;
import Jugador.Jugador;


public class FabricaEntidades {
	
	protected FabricaSprites fabricaSprites;
	
	public FabricaEntidades(FabricaSprites fabricaSprites) {
		this.fabricaSprites = fabricaSprites;
	}

	
	public Silueta getSilueta() {
		Sprite siluetaSprite = fabricaSprites.getSilueta();
		Silueta silueta = new Silueta(siluetaSprite);
		return silueta;
	}
	
	public Jugador getJugador(int x, int y) {
		Sprite jugadorSprite = fabricaSprites.getMarioNormalInmovilDer();
		Jugador jugador = new Jugador(jugadorSprite,x,y);
		return jugador;
	}
	
	public BuzzyBeetle getBuzzyBeetle(int x, int y) {
		Sprite buzzyBeetleSprite = fabricaSprites.getBuzzyBeetle();
		BuzzyBeetle buzzyBeetle = new BuzzyBeetle(buzzyBeetleSprite, x, y);
		return buzzyBeetle;
	}
	
	public Goomba getGoomba(int x, int y) {
		Sprite goombaSprite = fabricaSprites.getGoomba();
		Goomba goomba = new Goomba(goombaSprite,x , y);
		return goomba;
	}
	
	public Tortuga getTortuga(int x, int y) {
		Sprite TortugaSprite = fabricaSprites.getKoopaTroopa();
		Tortuga Tortuga = new Tortuga(TortugaSprite, x, y);
		return Tortuga;
	}
	
	public Lakitu getLakitu(int x, int y) {
		Sprite lakituSprite = fabricaSprites.getLakitu();
		Lakitu lakitu = new Lakitu(lakituSprite, x, y);
		return lakitu;
	}
	
	public PiranhaPlant getPiranhaPlant(int x, int y) {
		Sprite piranhaPlantSprite = fabricaSprites.getPiranhaPlant();
		PiranhaPlant piranhaPlant = new PiranhaPlant(piranhaPlantSprite, x, y);
		return piranhaPlant;
	}
	
	public Spiny getSpiny(int x, int y) {
		Sprite spinySprite = fabricaSprites.getSpiny();
		Spiny spiny = new Spiny(spinySprite, x, y);
		return spiny;
	}
	
	public BloqueSolido getBloqueSolido(int x, int y) {
		Sprite bloqueSprite = fabricaSprites.getBloque();
		BloqueSolido bloque = new BloqueSolido(bloqueSprite, x, y);
		return bloque;
	}
	
	public BloqueDePregunta getBloqueDePregunta(int x, int y) {
		Sprite bloqueDePregSprite = fabricaSprites.getBloqueDePregunta();
		BloqueDePregunta bloqueDePreg = new BloqueDePregunta(bloqueDePregSprite, x, y);
		return bloqueDePreg;
	}
	
	public LadrilloSolido getLadrilloSolido(int x, int y) {
		Sprite ladrilloSprite = fabricaSprites.getLadrilloSolido();
		LadrilloSolido ladrillo = new LadrilloSolido(ladrilloSprite, x, y);
		return ladrillo;
	}
	
	public Tuberia getTuberia(int x, int y) {
		Sprite tuberiaSprite = fabricaSprites.getTuberia();
		Tuberia tuberia = new Tuberia(tuberiaSprite, x, y);
		return tuberia;
	}
	public BloqueTransparente getBloqueTransparente(int x, int y) {
		Sprite BloqueTransparente = fabricaSprites.getBloqueTransparente();
		BloqueTransparente transparente = new BloqueTransparente(BloqueTransparente, x, y);
		return transparente;
	}
	public Vacio getVacio(int x, int y) {
		Sprite bloqueVacio = fabricaSprites.getVacio();
		Vacio vacio = new Vacio(bloqueVacio, x, y);
		return vacio;
	}
	
	public ChampiñonVerde getChampiñonVerde(int x, int y) {
		Sprite champiVerdeSprite = fabricaSprites.getChampiñonVerde();
		ChampiñonVerde champiVerde = new ChampiñonVerde(champiVerdeSprite, x, y);
		return champiVerde;
	}
	
	public Estrella getEstrella(int x, int y) {
		Sprite estrellaSprite = fabricaSprites.getEstrella();
		Estrella estrella = new Estrella(estrellaSprite, x, y);
		return estrella;
	}
	
	public FlorDeFuego getFlorDeFuego(int x, int y) {
		Sprite florDeFuegoSprite = fabricaSprites.getFlorDeFuego();
		FlorDeFuego florDeFuego = new FlorDeFuego(florDeFuegoSprite, x, y);
		return florDeFuego;
	}
	
	public Moneda getMoneda(int x, int y) {
		Sprite monedaSprite = fabricaSprites.getMoneda();
		Moneda moneda = new Moneda(monedaSprite, x, y);
		return moneda;
	}
	
	public SuperChampiñon getSuperChampiñon(int x, int y) {
		Sprite superChampiSprite = fabricaSprites.getSuperChampiñon();
		SuperChampiñon superChampi = new SuperChampiñon(superChampiSprite, x, y);
		return superChampi;
	}
	
	public BloqueSuelo getBloqueSuelo(int x, int y) {
		Sprite BloqueSueloSprite = fabricaSprites.getBloqueSuelo();
		BloqueSuelo BloqueSuelo = new BloqueSuelo(BloqueSueloSprite, x, y);
		return BloqueSuelo;
	}
	
	public BloqueFinal getBloqueFinal(int x, int y) {
		Sprite BloqueFinalSprite = fabricaSprites.getBloqueFinal();
		BloqueFinal BloqueFinal = new BloqueFinal(BloqueFinalSprite, x, y);
		return BloqueFinal;
	}
	
	public BolaDeFuego getBolaDeFuego(int x,int y) {
		Sprite bolaSprite= fabricaSprites.getBolaDeFuego();
		BolaDeFuego bola =new BolaDeFuego(bolaSprite,x,y);
		return bola;
	}
}
