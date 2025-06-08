package Juego;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import Enemigos.Enemigo;
import Fabricas.*;
import Parser.GeneradorNivel;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Sonido.SonidoManager;
import Grafica.Observer;
import Jugador.BolaDeFuego;
import Jugador.Jugador;

import Grafica.ControladorVistas;

public class Juego {
	protected ControladorVistas controladorVistas;
	protected GeneradorNivel generadorNivel;
	protected FabricaSprites fabricaSprites;
	protected FabricaEntidades fabricaEntidades;
	protected Nivel nivelActual;
	protected MovimientoJugador moverMario;
	protected MovimientoEntidad moverEntidades;
	protected MotorColision colisionador;
	protected int numeroNivel;
	protected int modoJuego;
	protected SonidoManager sonido;
	protected SesionDeJuego sesion = new SesionDeJuego();

	public Juego() {
		this.sonido= SonidoManager.getInstancia();
    	
		numeroNivel = 1;

		fabricaSprites = new FabricaSpritesOriginales();

		fabricaEntidades = new FabricaEntidades(fabricaSprites);

		generadorNivel = new GeneradorNivel(fabricaEntidades);
		
		this.colisionador = new MotorColision(this);
	}



	public void setControladorVistas(ControladorVistas controladorVistas) {
		this.controladorVistas = controladorVistas;
		this.modoJuego = controladorVistas.getModoJuego();

	}

	public ControladorVistas getControlador() {
		return controladorVistas;
	}

	public Nivel getNivelActual() {
		return nivelActual;
	}

	public void iniciar() {
		modoJuego = controladorVistas.getModoJuego();
		checkModoJuego();
		sesion.ReanudarTiempo();
		iniciarNivel();
		iniciarSonidoJuego();
		iniciarComponentesNivel();
	}
	private void checkModoJuego() {
		if (modoJuego == 2) {
			fabricaSprites = new FabricaSpritesReemplazo();
			fabricaEntidades = new FabricaEntidades(fabricaSprites);
			generadorNivel = new GeneradorNivel(fabricaEntidades);
		}
	}
	private void iniciarSonidoJuego() {
		sonido.iniciarMusicaFondo();
		
	}
	private void iniciarNivel() {
		nivelActual = generadorNivel.generarNivel(numeroNivel);
		nivelActual.registrarSesionParaEnemigos(sesion);
		nivelActual.getJugador().setSesionDeJuego(sesion);
		nivelActual.getJugador().setSonidoManager();
	}

	public void gameOver() {
		sesion.PausarTiempo();
		detenerHilos();
		controladorVistas.mostrarPantallaGameOver();
	}
	private void detenerHilos() {
		moverMario.detener();
		moverEntidades.detener();
	}
	private void pausarHilos() {
		moverMario.pausar();
		moverEntidades.pausar();
	}
	private void reanudarHilos() {
		moverMario.reanudar();
		moverEntidades.reanudar();
	}
	public void muerteNivel() {
		sesion.PausarTiempo();
		pausarHilos();
	    controladorVistas.crearNuevaPantallaMuerte(sesion.getVidasRestantes(), numeroNivel);
	    controladorVistas.mostrarPantallaMuerteNivel();
	    
	    int delay = 2000; // 2000 milisegundos = 2 segundos
	    Timer timer = new Timer(delay, e -> { 
	    	sesion.ReanudarTiempo();
	    	reanudarHilos();
	    	controladorVistas.mostrarPantallaJuego();
	    	
	    });

	    timer.setRepeats(false); // Para que el temporizador se ejecute solo una vez
	    timer.start(); // Inicia el temporizador para la pausa
	    
	}

	//public void finDeJuego() {}
	public void cambiarNivel() {
		sesion.PausarTiempo();
		numeroNivel += 1;

		if (numeroNivel > 4) {
			System.out.println("Ganaste");
			numeroNivel=1;
			controladorVistas.crearNuevaPantallaJuego();
			gameOver(); // Deberia ser PantallaFinalJuego
		} else {
			detenerHilos();
			controladorVistas.crearNuevaPantallaEntreNivel(numeroNivel);
			controladorVistas.mostrarPantallaEntreNivel();
			
			
			controladorVistas.crearNuevaPantallaJuego();
			iniciarNivel();
			nivelActual.getJugador().reponerUltimoEstado();
			nivelActual.setJuego(this);
			
			System.out.println("Se genero el nivel " + numeroNivel);
			iniciarComponentesNivel();
			sesion.ReanudarTiempo();
		}

	}

	private void iniciarComponentesNivel() {
		nivelActual.agregarJuego(this);
		registrarObserversYOyente();
		iniciarHilos();
		controladorVistas.mostrarPantallaJuego();
	}

	private void iniciarHilos() {
		moverMario = new MovimientoJugador(this);
		moverEntidades = new MovimientoEntidad(this);
		moverMario.start();
		moverEntidades.start();
	}

	protected void registrarObserversYOyente() {
		registrarObserverJugador(nivelActual.getJugador());
		registrarOyenteJugador(nivelActual.getJugador());
		registrarObserverSilueta(nivelActual.getSilueta());
		registrarObserversParaEntidades(nivelActual.getListaEnemigos());
		registrarObserversParaEntidades(nivelActual.getListaPlataformas());
		registrarObserversParaEntidades(nivelActual.getListaPowerUps());
	}

	protected void registrarObserverJugador(Jugador jugador) {
		Observer observerJugador = controladorVistas.registrarEntidad(jugador);
		jugador.registrarObserver(observerJugador);
	}

	protected void registrarOyenteJugador(EntidadJugador jugador) {
		KeyListenerJugador oyente = new KeyListenerJugador(jugador);
		controladorVistas.registrarKeyListener(oyente);
	}

	protected void registrarObserverSilueta(Silueta silueta) {
		Observer observer = controladorVistas.registrarSilueta(silueta);
		silueta.registrarObserver(observer);
	}

	protected void registrarObserversParaEntidades(List<? extends Entidad> entidades) {
		for (Entidad entidad : entidades) {
			Observer observer = controladorVistas.registrarEntidad(entidad);
			entidad.registrarObserver(observer);
		}
	}

	protected void registrarObserversParaEntidades(Entidad entidad) {
		Observer observer = controladorVistas.registrarEntidad(entidad);
		entidad.registrarObserver(observer);

	}

	public void detectarColisiones() {
		Jugador jugador = nivelActual.getJugador();

		// Iterar sobre enemigos
		List<Enemigo> enemigos = new ArrayList<>(nivelActual.getListaEnemigos());
		for (Enemigo enemigo : enemigos) {
		    colisionador.colisionJugadorEnemigo(jugador, enemigo);
		}

		// Iterar sobre plataformas
		List<Plataforma> plataformas = new ArrayList<>(nivelActual.getListaPlataformas());
		for (Plataforma plataforma : plataformas) {
		    colisionador.colisionJugadorPlataforma(jugador, plataforma);
		}

		// Iterar sobre power-ups
		List<PowerUp> powerUps = new ArrayList<>(nivelActual.getListaPowerUps());
		for (PowerUp powerUp : powerUps) {
		    colisionador.colisionJugadorPowerUp(jugador, powerUp);
		}

		// Colisiones entre enemigos y plataformas
		for (Enemigo enemigo : enemigos) {
		    for (Plataforma plataforma : plataformas) {
		        colisionador.colisionEnemigoPlataforma(enemigo, plataforma);
		    }
		}

		// Colisiones entre power-ups y plataformas
		for (PowerUp pw : powerUps) {
		    for (Plataforma plataforma : plataformas) {
		        colisionador.colisionPwPlataforma(pw, plataforma);
		    }
		}

		// Colisiones entre bolas de fuego y plataformas
		List<BolaDeFuego> bolasDeFuego = new ArrayList<>(nivelActual.getListaBolaDefuego());
		for (BolaDeFuego bola : bolasDeFuego) {
		    for (Plataforma plataforma : plataformas) {
		        colisionador.colisionBolaPlataforma(bola, plataforma);
		    }
		}

		// Colisiones entre bolas de fuego y enemigos
		for (BolaDeFuego bola : bolasDeFuego) {
		    for (Enemigo enemigo : enemigos) {
		        colisionador.colisionBolaEnemigo(bola, enemigo);
		    }
		}

	}

	public SesionDeJuego getSesion() {
		return sesion;
	}

	

}
