package Juego;

import java.util.LinkedList;
import java.util.List;

import Enemigos.*;
import PowerUps.*;
import Plataformas.*;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Grafica.Observer;
import Jugador.BolaDeFuego;
import Jugador.Jugador;

public class Nivel {

	protected Silueta silueta;
	protected List<Enemigo> listaEnemigos;
	protected List<PowerUp> listaPowerUps;
	protected List<Plataforma> listaPlataformas;
	protected List<BolaDeFuego> listabolaDeFuegos;
	protected Jugador jugador;
	protected Juego juego;

	protected FabricaSprites fabricaSprites;
	protected FabricaEntidades fabricaEntidades;

	public Nivel(Silueta silueta) {
		this.listabolaDeFuegos = new LinkedList<BolaDeFuego>();
		this.listaEnemigos = new LinkedList<Enemigo>();
		this.listaPowerUps = new LinkedList<PowerUp>();
		this.listaPlataformas = new LinkedList<Plataforma>();
		this.silueta = silueta;
		this.fabricaSprites = new FabricaSpritesOriginales();
		this.fabricaEntidades = new FabricaEntidades(fabricaSprites);
	}

	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Jugador getJugador() {
		return jugador;
	}



	public Silueta getSilueta() {
		return silueta;
	}

	public List<Enemigo> getListaEnemigos() {
		return listaEnemigos;
	}

	public List<BolaDeFuego> getListaBolaDefuego() {
		return listabolaDeFuegos;
	}

	public List<PowerUp> getListaPowerUps() {
		return listaPowerUps;
	}

	public List<Plataforma> getListaPlataformas() {
		return listaPlataformas;
	}

	public void gameover() {
		juego.gameOver();
	}

	public void agregarJugador(Jugador jugador) {
		this.jugador = jugador;
		jugador.setNivel(this);
	}

	public Enemigo agregarEnemigo(Enemigo enemigo) {
		listaEnemigos.add(enemigo);
		return enemigo;
	}

	public PowerUp agregarPowerUp(PowerUp powerUp) {
		listaPowerUps.add(powerUp);
		return powerUp;
	}
	public Spiny agregarSpiny(Spiny spiny) {
		listaEnemigos.add(spiny);
		return spiny;
	}

	public BolaDeFuego agregarBolaDeFuego(int x, int y) {
		BolaDeFuego bola = fabricaEntidades.getBolaDeFuego(x, y);
		listabolaDeFuegos.add(bola);
		return bola;
	}

	public Plataforma agregarPlataforma(Plataforma plataforma) {
		listaPlataformas.add(plataforma);
		return plataforma;
	}

	public PowerUp agregarEntidad(int tipoEntidad, int posX, int posY) {
		PowerUp retorno = null;
		switch (tipoEntidad) {
		case 13:
			retorno = agregarPowerUp(fabricaEntidades.getChampiñonVerde(posX, posY));
			break;
		case 14:
			retorno = agregarPowerUp(fabricaEntidades.getEstrella(posX, posY));
			break;
		case 15:
			retorno = agregarPowerUp(fabricaEntidades.getFlorDeFuego(posX, posY));
			break;
		case 16:
			retorno = agregarPowerUp(fabricaEntidades.getMoneda(posX, posY));
			break;
		case 17:
			retorno = agregarPowerUp(fabricaEntidades.getSuperChampiñon(posX, posY));
			break;

		}
		return retorno;
	}
	public 	Spiny agregarSpiny( int posX, int posY) {
		Spiny retorno = null;
		retorno = agregarSpiny(fabricaEntidades.getSpiny(posX, posY));
		return retorno;
	}

	public void agregarJuego(Juego juego) {
		this.juego = juego;
	}

	public void cambiarNivel() {
		juego.cambiarNivel();
	}
	public void muerteNivel() {
		juego.muerteNivel();
	}

	public void registrarSesionParaEnemigos(SesionDeJuego sesion) {
		for(Enemigo enemigo : listaEnemigos) {
			enemigo.setSesion(sesion);
			//enemigo.actualizar();
		}
	
		
	}

	public void intercambiarPorTransparente(LadrilloSolido ladrilloSolido) {
		Plataforma Transparente = agregarPlataforma(fabricaEntidades.getBloqueTransparente(ladrilloSolido.getX(), ladrilloSolido.getY()+32));		
		Observer obv = this.getJuego().getControlador().registrarEntidad(Transparente);
		Transparente.registrarObserver(obv);
		ladrilloSolido.desaparecer();
	
	}
}