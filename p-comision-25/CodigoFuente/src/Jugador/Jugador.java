package Jugador;

import java.util.Timer;
import java.util.TimerTask;

import Enemigos.Enemigo;
import Fabricas.FabricaSonido;
import Fabricas.FabricaSprites;

import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import Grafica.Observer;
import Juego.EntidadJugador;
import Juego.Hitbox;
import Juego.Nivel;
import Juego.SesionDeJuego;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.PowerUp;
import PowerUps.SuperChampiñon;
import Sonido.SonidoManager;
import Visitor.Visitor;

public class Jugador implements EntidadJugador {

	protected Sprite sprite;
	protected int x;
	protected int y;
	protected Observer observer;
	protected int direccion;
	protected int velX;
	protected int velY;
	protected Hitbox hitbox;
	protected boolean cayendo = false;
	protected int posicionPlataforma;
	protected int posicionInicio;
	protected int alturaInicio;
	protected int salto = 270;
	protected boolean subiendo = false;
	protected boolean sobrePlataforma = true;
	protected Personaje personaje;
	protected Nivel nivel;
	protected int vidas;
	protected int danio;
	protected boolean vivo;
	protected SesionDeJuego sesion;
	protected SonidoManager sonido;
	private boolean invulnerable = false;
	private long tiempoUltimoGolpe = 0;
	private static final int DURACION_INVULNERABILIDAD = 10000; // 10 segundos// 1 segundo en milisegundos

	public Jugador(Sprite sprite, int x, int y) {
		this.personaje = new Personaje(this, new Normal());
		personaje.setEstado(new Normal());
		
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		direccion = 0;
		velX = 8;
		velY = 4;
		vidas = 3;
		this.posicionInicio = x;
		this.alturaInicio = y;
		this.posicionPlataforma = y;
		this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());

	}

	public void recibirGolpe() {
		long tiempoActual = System.currentTimeMillis();

		
		if (invulnerable) {
			return; 
		}

		// Registrar el golpe y activar la invulnerabilidad
		personaje.getEstado().recibirGolpe(this.personaje);
		activarInvulnerabilidad();
	}

	private void activarInvulnerabilidad() {
		invulnerable = true;
		tiempoUltimoGolpe = System.currentTimeMillis();

		// Programar un temporizador para desactivar la invulnerabilidad después de 1
		// segundo
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				invulnerable = false;
			}
		}, DURACION_INVULNERABILIDAD);
	}

	public Sprite getSprite() {
		Sprite spriteARetornar = null;
		if (direccion == 0) {
			spriteARetornar = personaje.getEstado().getMarioInmovilDer();
		} else if (direccion == 1) {
			spriteARetornar = personaje.getEstado().getMarioMoviendoDer();
		} else if (direccion == 2) {
			spriteARetornar = personaje.getEstado().getMarioMoviendoIzq();
		} else if (direccion == -1) {
			spriteARetornar = personaje.getEstado().getMarioInmovilIzq();
		} else if (direccion == 3 | direccion == 4) {
			spriteARetornar = personaje.getEstado().getMarioSaltandoDer();
		}
		return spriteARetornar;
	}

	public void mover() {
		if (direccion == 1) {
			if (subiendo || cayendo)
				x = (int) (x + velX * 0.7);
			else
				x = x + velX;
		} else if (direccion == 2) {
			if (subiendo || cayendo)
				x = (int) (x - velX * 0.7);
			else
				x = x - velX;
		}

		if (subiendo) {
			if (y < posicionPlataforma + salto) {
				y += velY * 1.4;
			} else {
				setcayendo();
			}
		}
		if (cayendo) {
			y -= velY *1.4;
		}
		if (y < alturaInicio - 32) {
			danio=15;
			perderVida();
			
		}
		hitbox.setX(x);
		hitbox.setY(y);
		observer.actualizar();
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void saltar() {
		if (sobrePlataforma) {
			subiendo = true;
			cayendo = false;
			sobrePlataforma = false;
			sonido.reproducirEfecto("saltando");
		}
	}

	public void setSobrePlataforma(boolean sobrePlataforma) {
		subiendo = false;
		cayendo = false;
		this.sobrePlataforma = sobrePlataforma;
		posicionPlataforma = y;
	}

	public boolean cayendo() {
		return cayendo;
	}

	public void setPosicionPlataforma(int valor) {
		posicionPlataforma = valor;
	}

	public boolean getSobrePlataforma() {
		return sobrePlataforma;
	}

	public void setcayendo() {
		subiendo = false;
		cayendo = true;
	}
	private void perderPuntosCaida() {
		sesion.decrementarPuntaje(15);
	}
	public void accept(Visitor v) {
		v.visitar(this);
	}

	public void lanzarBolasDeFuego() {
		personaje.getEstado().lanzarBolasDeFuego();

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVelX() {
		return velX;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setCayendo(boolean C) {
		cayendo = C;
	}

	public void setX(int f) {
		this.x = f;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void registrarObserver(Observer observer) {
		this.observer = observer;
	}

	public void consumirPowerUp(SuperChampiñon pw) {
		sonido.reproducirEfecto("champiSuper");
		sesion.incrementarPuntaje(personaje.getEstado().obtenerPuntajeSuperChampiñon());
		personaje.setEstado(new Super());
	}

	public void consumirPowerUp(Moneda pw) {
		sonido.reproducirEfecto("moneda");
		sesion.incrementarPuntaje(5);
	}

	public void consumirPowerUp(FlorDeFuego pw) {
		sesion.incrementarPuntaje(personaje.getEstado().obtenerPuntajeFlordeFuego());
		personaje.setEstado(new SuperFuego(this.personaje));
		
	}

	public void consumirPowerUp(ChampiñonVerde pw) {
		sonido.reproducirEfecto("champiVerde");
		sesion.sumarVida();
		sesion.incrementarPuntaje(100);
	}

	public void consumirPowerUp(Estrella pw) {
		// sonido estrella
		sesion.incrementarPuntaje(personaje.getEstado().obtenerPuntajeEstrella());
		sonido.reproducirEfecto("marioEstrella");
		personaje.consumirEstrella();

	}

	public boolean puedeRomperPlataformas() {
		// TODO Auto-generated method stub
		return personaje.getEstado().puedeRomperLadrillo();
	}

	public boolean getSubiendo() {
		return subiendo;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public void perderVida() {
		sesion.restarVida();
		personaje.setEstado(new Normal());
		sesion.decrementarPuntaje(danio);
		
		if (sesion.isGameOver()) {
			
			this.morir();

		} else {
			sonido.reproducirEfecto("marioPerdioUnaVida");
			reiniciarPosicion();
			nivel.muerteNivel();

		}
	}

	private void reiniciarPosicion() {
		this.x = posicionInicio;
		this.y = alturaInicio;
		cayendo = false;
		subiendo = false;
		sobrePlataforma = true;
		hitbox.setX(x);
		hitbox.setY(y);
		observer.actualizar();
	}

	public void morir() {
		sonido.pausarMusicaFondo();
		sonido.reproducirEfecto("gameOver");
		vivo = false;
		nivel.gameover();
	}

	public void cambiarNivel() {
		sesion.guardarEstado(personaje.getEstado());
		nivel.cambiarNivel();
	}

	public void setSesionDeJuego(SesionDeJuego sesion) {
		this.sesion = sesion;
	}
	public void reponerUltimoEstado() {
		personaje.setEstado(sesion.getEstadoGuardado());
	}

	public boolean indestructible() {
		return personaje.getEstado().indestructuble();
	}

	@Override
	public void cambiarDireccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosicionSuelo(int nuevaPosicion) {
		// TODO Auto-generated method stub

	}

	public void setSonidoManager() {
		sonido = SonidoManager.getInstancia();
		
	}
	public void danioRecibido(int danio) {
		this.danio = danio;
	}
	

}
