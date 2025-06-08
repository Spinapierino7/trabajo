package Juego;

import Enemigos.Enemigo;
import Jugador.BolaDeFuego;
import Jugador.Jugador;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Visitor.VisitorJugador;

public class MotorColision {

	protected Juego juego;

	public MotorColision(Juego juego) {
		this.juego = juego;
	}

	public void colisionJugadorEnemigo(Jugador jugador, Enemigo enemigo) {

		Hitbox hitboxJugaor = jugador.getHitbox();
		Hitbox hitboxEntidad = enemigo.getHitbox();

		if (hayColision(hitboxJugaor, hitboxEntidad)) {
			VisitorJugador visitorJugador = new VisitorJugador(jugador);
			enemigo.accept(visitorJugador);
		}

	}

	public void colisionJugadorPlataforma(Jugador jugador, Plataforma plataforma) {

		Hitbox hitboxJugaor = jugador.getHitbox();
		Hitbox hitboxEntidad = plataforma.getHitbox();
		if (hayColision(hitboxJugaor, hitboxEntidad)) {
			VisitorJugador visitorJugador = new VisitorJugador(jugador);
			plataforma.accept(visitorJugador);
		}

	}

	public void colisionJugadorPowerUp(Jugador jugador, PowerUp powerUp) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxPowerUp = powerUp.getHitbox();

		// Calcula las diferencias entre los centros de las hitboxes
		float diferenciaX = (float) ((float) (hitboxJugador.getX() + hitboxJugador.getAncho() / 2)
				- (hitboxPowerUp.getX() + hitboxPowerUp.getAncho() / 2));
		float diferenciaY = (float) ((float) (hitboxJugador.getY() + hitboxJugador.getAlto() / 2)
				- (hitboxPowerUp.getY() + hitboxPowerUp.getAlto() / 2));

		// Calcula los límites combinados de las hitboxes
		float limiteX = (float) (hitboxJugador.getAncho() / 2 + hitboxPowerUp.getAncho() / 2);
		float limiteY = (float) (hitboxJugador.getAlto() / 2 + hitboxPowerUp.getAlto() / 2);

		// Verifica si hay colisión
		if (Math.abs(diferenciaX) < limiteX && Math.abs(diferenciaY) < limiteY) {
			VisitorJugador visitorJugador = new VisitorJugador(jugador);
			powerUp.accept(visitorJugador);
			powerUp.recibirGolpe();
		}
	}

	public void colisionEnemigoPlataforma(Enemigo enemigo, Plataforma plataforma) {
		// Ajustar el tamaño del hitbox del enemigo al 70%
		Hitbox hitboxEnemigo = reducirHitbox(enemigo.getHitbox(), 0.7);
		Hitbox hitboxPlataforma = plataforma.getHitbox();

		if (hayColision(hitboxEnemigo, hitboxPlataforma)) {
			// Verificar colisión por la derecha
			if (colisionPorDerecha(hitboxEnemigo, hitboxPlataforma)) {
				enemigo.setX((int) (hitboxPlataforma.getX() - hitboxEnemigo.getAncho() - 4)); // Reubicar 
				enemigo.cambiarDireccion(); // Cambiar dirección al chocar por la derecha
			}
			// Verificar colisión por la izquierda
			else 
				if (colisionPorIzquierda(hitboxEnemigo, hitboxPlataforma)) {
					enemigo.setX((int) (hitboxPlataforma.getX() + hitboxPlataforma.getAncho() + 4)); // Reubicar
					enemigo.cambiarDireccion(); // Cambiar dirección al chocar por la izquierda
				}
				else {
				    enemigo.colisionPlataforma(plataforma);	
					}
		}
	}

	public void colisionPwPlataforma(PowerUp pw, Plataforma plataforma) {
		// Ajustar el tamaño del hitbox del PowerUp al 70%
		Hitbox hitboxPowerUp = reducirHitbox(pw.getHitbox(), 0.7);
		Hitbox hitboxPlataforma = plataforma.getHitbox();

		if (hayColision(hitboxPowerUp, hitboxPlataforma)) {
			// Verificar colisión por la derecha
			if (colisionPorDerecha(hitboxPowerUp, hitboxPlataforma)) {
				pw.setX((int) (hitboxPlataforma.getX() - hitboxPowerUp.getAncho() - 4)); // Reubicar a la izquierda de
				pw.cambiarDireccion(); // Cambiar dirección al chocar por la derecha

			}
			// Verificar colisión por la izquierda
			else if (colisionPorIzquierda(hitboxPowerUp, hitboxPlataforma)) {
				pw.setX((int) (hitboxPlataforma.getX() + hitboxPlataforma.getAncho() + 4)); // Reubicar a la derecha de
				pw.cambiarDireccion(); // Cambiar dirección al chocar por la izquierda
			}
		}
	}

	// Método auxiliar para reducir el tamaño del hitbox
	private Hitbox reducirHitbox(Hitbox original, double factor) {
		int nuevoAncho = (int) (original.getAncho() * factor);
		int nuevoAlto = (int) (original.getAlto() * factor);
		int nuevaX = (int) (original.getX() + (original.getAncho() - nuevoAncho) / 2);
		int nuevaY = (int) (original.getY() + (original.getAlto() - nuevoAlto) / 2);
		return new Hitbox(nuevaX, nuevaY, nuevoAncho, nuevoAlto);
	}

	public void colisionBolaPlataforma(BolaDeFuego bola, Plataforma plataforma) {

		Hitbox hitboxBola = bola.getHitbox();
		Hitbox hitboxEntidad = plataforma.getHitbox();

		if (hayColision(hitboxBola, hitboxEntidad) && !plataforma.isTransparente()) {

			bola.recibirGolpe();
		}

	}

	public void colisionBolaEnemigo(BolaDeFuego bola, Enemigo enemigo) {
		Hitbox hitboxBola = bola.getHitbox();
		Hitbox hitboxEntidad = enemigo.getHitbox();
		// Utilizar el método hayColision para verificar si hay colisión entre la bola y
		// el enemigo
		if (hayColision(hitboxBola, hitboxEntidad)) {
			// Acciones a realizar en caso de colisión
			bola.recibirGolpe();
			enemigo.recibirGolpePorBolaDefuego();
		}
	}

	// Método para verificar colisión por la derecha
	private boolean colisionPorDerecha(Hitbox hitboxA, Hitbox hitboxB) {
		return hitboxA.getX() + hitboxA.getAncho() >= hitboxB.getX() && hitboxA.getX() < hitboxB.getX()
				&& Math.abs(hitboxA.getY() - hitboxB.getY()) < (hitboxA.getAlto() + hitboxB.getAlto()) / 2;
	}

	// Método para verificar colisión por la izquierda
	private boolean colisionPorIzquierda(Hitbox hitboxA, Hitbox hitboxB) {
		return hitboxA.getX() <= hitboxB.getX() + hitboxB.getAncho()
				&& hitboxA.getX() + hitboxA.getAncho() > hitboxB.getX() + hitboxB.getAncho()
				&& Math.abs(hitboxA.getY() - hitboxB.getY()) < (hitboxA.getAlto() + hitboxB.getAlto()) / 2;
	}

	// Método para verificar colisión por arriba
	private boolean colisionPorArriba(Hitbox hitboxA, Hitbox hitboxB) {
		return hitboxA.getY() + hitboxA.getAlto() <= hitboxB.getY() && hitboxA.getY() < hitboxB.getY()
				&& Math.abs(hitboxA.getX() - hitboxB.getX()) < (hitboxA.getAncho() + hitboxB.getAncho()) / 2;
	}

	// Método para verificar colisión por abajo
	private boolean colisionPorAbajo(Hitbox hitboxA, Hitbox hitboxB) {
		return hitboxA.getY() >= hitboxB.getY() + hitboxB.getAlto()
				&& hitboxA.getY() < hitboxB.getY() + hitboxB.getAlto()
				&& Math.abs(hitboxA.getX() - hitboxB.getX()) < (hitboxA.getAncho() + hitboxB.getAncho()) / 2;
	}

	// Método genérico para verificar si hay colisión entre dos hitboxes
	private boolean hayColision(Hitbox hitboxA, Hitbox hitboxB) {
		float diferenciaX = (float) ((hitboxA.getX() + hitboxA.getAncho() / 2)
				- (hitboxB.getX() + hitboxB.getAncho() / 2));
		float diferenciaY = (float) ((hitboxA.getY() + hitboxA.getAlto() / 2)
				- (hitboxB.getY() + hitboxB.getAlto() / 2));

		float limiteX = (float) (hitboxA.getAncho() / 2 + hitboxB.getAncho() / 2);
		float limiteY = (float) (hitboxA.getAlto() / 2 + hitboxB.getAlto() / 2);

		return Math.abs(diferenciaX) < limiteX && Math.abs(diferenciaY) < limiteY;
	}

}