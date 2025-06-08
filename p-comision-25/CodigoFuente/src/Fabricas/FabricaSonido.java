package Fabricas;

import Sonido.SonidoManager;

public class FabricaSonido {
	
	private SonidoManager soundManager;
	
	public FabricaSonido(SonidoManager sm) {
		soundManager = sm;
	}

	public void InicializarSonidos() {
		setChampiSuper();
		setChampiVerde();
		setEnemigoMuerto();
		setGameOver();
		setMarioEstrella();
		setMarioJuego();
		setMarioMiedo();
		setMarioPerdioUnaVida();
		setMoneda();
		setSaltando();
		setSaltandoMarioGrande();
		setSeAgotaElTiempo();
	}
	
	public void setChampiSuper() {
		soundManager.cargarEfecto("champiSuper" , "/Sonidos/champiSuper.wav");
	}
	
	public void setChampiVerde() {
		soundManager.cargarEfecto("champiVerde", "/Sonidos/champiVerde.wav");
	}
	
	public void setEnemigoMuerto() {
		soundManager.cargarEfecto("enemigoMuerto", "/Sonidos/enemigoMuerto.wav");
	}
	
	public void setGameOver() {
		soundManager.cargarEfecto("gameOver", "/Sonidos/gameOver.wav");
	}
	
	public void setMarioEstrella() {
		soundManager.cargarEfecto("marioEstrella", "/Sonidos/MarioEstrella.wav");
	}
	
	public void setMarioJuego() {
		soundManager.cargarEfecto("marioJuego","/Sonidos/MarioJuego.wav");
	}
	
	public void setMarioMiedo() {
		soundManager.cargarEfecto("marioMiedo", "/Sonidos/MarioMiedo.wav");
	}
	
	public void setMarioPerdioUnaVida() {
		soundManager.cargarEfecto("marioPerdioUnaVida", "/Sonidos/marioPerdioUnaVida.wav");
	}
	
	public void setMoneda() {
		soundManager.cargarEfecto("moneda", "/Sonidos/moneda.wav");
	}
	
	public void setSaltando() {
		soundManager.cargarEfecto("saltando", "/Sonidos/saltando.wav");
	}	
	
	public void setSaltandoMarioGrande() {
		soundManager.cargarEfecto("saltandoMarioGrande", "/Sonidos/saltandoMarioGrande.wav");
	}
	
	public void setSeAgotaElTiempo() {
		soundManager.cargarEfecto("seAgotaElTiempo", "/Sonidos/seAgotaElTiempo.wav");
	}
	
}
