package Visitor;

import Enemigos.BuzzyBeetle;
import Enemigos.Enemigo;
import Enemigos.Goomba;
import Enemigos.Lakitu;
import Enemigos.PiranhaPlant;
import Enemigos.Spiny;
import Enemigos.Tortuga;
import Juego.Entidad;
import Juego.Hitbox;
import Jugador.Jugador;
import Plataformas.BloqueDePregunta;
import Plataformas.BloqueFinal;
import Plataformas.BloqueTransparente;
import Plataformas.Plataforma;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.PowerUp;
import PowerUps.SuperChampiñon;

// quien es el que visita 
public class VisitorJugador implements Visitor {

	private Jugador jugador;

	public VisitorJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	// mario consume los diferentes powerups
	public void visitar(SuperChampiñon powerup) {
		jugador.consumirPowerUp(powerup);

	}

	public void visitar(Moneda powerup) {
		jugador.consumirPowerUp(powerup);

	}

	public void visitar(FlorDeFuego powerup) {
		jugador.consumirPowerUp(powerup);

	}

	public void visitar(ChampiñonVerde powerup) {
		jugador.consumirPowerUp(powerup);

	}

	public void visitar(Estrella powerup) {
		jugador.consumirPowerUp(powerup);

	}

	public void visitar(Entidad entidad) {
		entidad.recibirGolpe();
	}

	public void visitar(Enemigo enemigo) {
		enemigo.recibirGolpe();
	}
	
	public void visitar(PowerUp powerup) {
		powerup.recibirGolpe();

	}

	// mario se topa con los diferentes enemigos

	public void visitar(BuzzyBeetle buzzyBeetle) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxEnemigo = buzzyBeetle.getHitbox();

		String direccionColision = determinarDireccionColision(hitboxJugador, hitboxEnemigo);

		if (direccionColision.equals("izquierda") || direccionColision.equals("derecha")) {
			if (!jugador.indestructible()) {
				jugador.recibirGolpe();
				jugador.danioRecibido(15);
			}
			else
				buzzyBeetle.recibirGolpe();
		} else if (direccionColision.equals("arriba")) {
			if (!jugador.indestructible())
			{
				jugador.recibirGolpe();
				jugador.danioRecibido(15);
			}
			else
				buzzyBeetle.recibirGolpe();
		} else if (direccionColision.equals("abajo")) {
			if (!jugador.indestructible())
			{
				jugador.recibirGolpe();
				jugador.danioRecibido(15);
			}
			else
				buzzyBeetle.recibirGolpe();
		}
	}

	public void visitar(Tortuga tortuga) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxEnemigo = tortuga.getHitbox();

		String direccionColision = determinarDireccionColision(hitboxJugador, hitboxEnemigo);

		if (direccionColision.equals("izquierda") || direccionColision.equals("derecha")) {
			if (!jugador.indestructible())
			{
				jugador.recibirGolpe();
				jugador.danioRecibido(45);
			}
			else
				tortuga.recibirGolpe();
		} else if (direccionColision.equals("arriba")) {
			if (!jugador.indestructible()) {
				jugador.setY(jugador.getY()+4);
				jugador.saltar();
				tortuga.recibirGolpe();
				
			}
			else {
				tortuga.recibirGolpe();
				tortuga.recibirGolpe();
			}
		} else if (direccionColision.equals("abajo")) {
			if (!jugador.indestructible())
			{
				jugador.recibirGolpe();
				jugador.danioRecibido(45);
			}
			else
				tortuga.recibirGolpe();
		}
	}

	public void visitar(Goomba goomba) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxEnemigo = goomba.getHitbox();

		String direccionColision = determinarDireccionColision(hitboxJugador, hitboxEnemigo);

		if (direccionColision.equals("izquierda") || direccionColision.equals("derecha")) {
			if (!jugador.indestructible())
			{
				jugador.recibirGolpe();
				jugador.danioRecibido(30);
			}
			else
				goomba.recibirGolpe();
		} else if (direccionColision.equals("arriba")) {
				goomba.recibirGolpe();
		} else if (direccionColision.equals("abajo")) {
			if (!jugador.indestructible())
			{
				jugador.recibirGolpe();
				jugador.danioRecibido(30);
			}
			else
				goomba.recibirGolpe();
		}
	}

	public void visitar(Lakitu lakitu) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxEnemigo = lakitu.getHitbox();

		String direccionColision = determinarDireccionColision(hitboxJugador, hitboxEnemigo);

		if (direccionColision.equals("izquierda") || direccionColision.equals("derecha")) {
			// Choque lateral, Mario recibe un golpe
			jugador.recibirGolpe();
		} else if (direccionColision.equals("arriba")) {
				lakitu.recibirGolpe();
		} else if (direccionColision.equals("abajo")) {
			if (!jugador.indestructible())
				jugador.recibirGolpe();
			else
				lakitu.recibirGolpe();
		}
	}

	public void visitar(PiranhaPlant piranhaPlant) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxEnemigo = piranhaPlant.getHitbox();

		String direccionColision = determinarDireccionColision(hitboxJugador, hitboxEnemigo);

		if (direccionColision.equals("izquierda") || direccionColision.equals("derecha")) {
			if (!jugador.indestructible()) {
				jugador.recibirGolpe();
				jugador.danioRecibido(30);
			}
			else
				piranhaPlant.recibirGolpe();
		} else if (direccionColision.equals("arriba")) {
			if (!jugador.indestructible()) {
				jugador.recibirGolpe();
				jugador.danioRecibido(30);
			}
			else
				piranhaPlant.recibirGolpe();
		} else if (direccionColision.equals("abajo")) {
			if (!jugador.indestructible()) {
				jugador.recibirGolpe();
				jugador.danioRecibido(30);
			}
			else
				piranhaPlant.recibirGolpe();
		}
	}

	public void visitar(Spiny spiny) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxEnemigo = spiny.getHitbox();

		String direccionColision = determinarDireccionColision(hitboxJugador, hitboxEnemigo);

		if (direccionColision.equals("izquierda") || direccionColision.equals("derecha")) {
			// Choque lateral, Mario recibe un golpe
			jugador.recibirGolpe();
			jugador.danioRecibido(30);
		} else if (direccionColision.equals("arriba")) {
			// Mario salta y el enemigo recibe el golpe
			jugador.saltar();
			spiny.recibirGolpe();
		} else if (direccionColision.equals("abajo")) {
			// Mario está bajo el enemigo, recibe un golpe
			jugador.recibirGolpe();
			jugador.danioRecibido(30);
		}
	}

	
	public void visitar(Plataforma plataforma) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxPlataforma = plataforma.getHitbox();

		String direccionColision = determinarDireccionColision(hitboxJugador, hitboxPlataforma);

		if (!direccionColision.equals("noColision")) { // hubo colision
			float superposicionEnX = (float) ((hitboxJugador.getAncho() / 2) + (hitboxPlataforma.getAncho() / 2)
					- Math.abs((hitboxJugador.getX() + (hitboxJugador.getAncho() / 2))
							- (hitboxPlataforma.getX() + (hitboxPlataforma.getAncho() / 2))));

			if (direccionColision.equals("izquierda")) {
				// Mover al jugador a la izquierda
				jugador.setX((int) (hitboxJugador.getX() + superposicionEnX));
			} else if (direccionColision.equals("derecha")) {
				// Mover al jugador a la derecha
				jugador.setX((int) (hitboxJugador.getX() - superposicionEnX));
			} else if (direccionColision.equals("arriba")) {
				int ySuperiorPlataforma = (int) hitboxPlataforma.getY() + 33;
				jugador.setPosicionPlataforma(ySuperiorPlataforma);
				jugador.setSobrePlataforma(true);
			} else if (direccionColision.equals("abajo")) {
				if (jugador.puedeRomperPlataformas()) { // Mario está bajo la plataforma
					plataforma.recibirGolpe();
					jugador.setcayendo();
				} else {
					jugador.setcayendo();
				}
			}
		}
	}

	private String determinarDireccionColision(Hitbox hitboxJugador, Hitbox hitboxPlataforma) {
		float diferenciaEntreCentrosX = (float) ((hitboxJugador.getX() + (hitboxJugador.getAncho() / 2))
				- (hitboxPlataforma.getX() + (hitboxPlataforma.getAncho() / 2)));
		float diferenciaEntreCentrosY = (float) ((hitboxJugador.getY() + (hitboxJugador.getAlto() / 2))
				- (hitboxPlataforma.getY() + (hitboxPlataforma.getAlto() / 2)));

		float mitadDeAnchosCombinados = (float) ((hitboxJugador.getAncho() / 2) + (hitboxPlataforma.getAncho() / 2));
		float mitadDeAltosCombinados = (float) ((hitboxJugador.getAlto() / 2) + (hitboxPlataforma.getAlto() / 2));

		if (Math.abs(diferenciaEntreCentrosX) < mitadDeAnchosCombinados
				&& Math.abs(diferenciaEntreCentrosY) < mitadDeAltosCombinados) { // hubo colision
			float superposicionEnX = (mitadDeAnchosCombinados - Math.abs(diferenciaEntreCentrosX));
			float superposicionEnY = (mitadDeAltosCombinados - Math.abs(diferenciaEntreCentrosY));

			if (superposicionEnX < superposicionEnY) { // colision horizontal
				return (diferenciaEntreCentrosX > 0) ? "izquierda" : "derecha";
			} else { // colision vertical
				return (diferenciaEntreCentrosY > 0) ? "arriba" : "abajo";
			}
		}
		return "noColision"; // No hubo colisión
	}

	public void visitar(BloqueTransparente bloque) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxPlataforma = bloque.getHitbox();

		float diferenciaEntreCentrosX = (float) (hitboxJugador.getX() + (hitboxJugador.getAncho() / 2)
				- (hitboxPlataforma.getX() + (hitboxPlataforma.getAncho() / 2)));
		float diferenciaEntreCentrosY = (float) (hitboxJugador.getY() + (hitboxJugador.getAlto() / 2)
				- (hitboxPlataforma.getY() + (hitboxPlataforma.getAlto() / 2)));

		float mitadDeAnchosCombinados = (float) ((hitboxJugador.getAncho() / 2) + (hitboxPlataforma.getAncho() / 2));
		float mitadDeAltosCombinados = (float) ((hitboxJugador.getAlto() / 2) + (hitboxPlataforma.getAlto() / 2));

		if ((Math.abs(diferenciaEntreCentrosX) < mitadDeAnchosCombinados)
				&& (Math.abs(diferenciaEntreCentrosY) < mitadDeAltosCombinados)) { // hubo colision
			float superposicionEnX = (mitadDeAnchosCombinados - Math.abs(diferenciaEntreCentrosX));
			float superposicionEnY = (mitadDeAltosCombinados - Math.abs(diferenciaEntreCentrosY));

			if (superposicionEnX < superposicionEnY) { // colision horizontal
				// Ajustar posición del jugador
				if (diferenciaEntreCentrosX > 0 && !jugador.getSobrePlataforma()) {
					if (!jugador.getSubiendo())
						jugador.setcayendo();
				} else {
					if (!jugador.getSubiendo())// Colisión desde la derecha
						jugador.setcayendo();
				}

			} else { // colision vertical

			}
		}
	}

	public void visitar(BloqueDePregunta bloquePregunta) {
		Hitbox hitboxJugador = jugador.getHitbox();
		Hitbox hitboxBloque = bloquePregunta.getHitbox();

		float diferenciaEntreCentrosX = (float) (hitboxJugador.getX() + (hitboxJugador.getAncho() / 2)
				- (hitboxBloque.getX() + (hitboxBloque.getAncho() / 2)));
		float diferenciaEntreCentrosY = (float) (hitboxJugador.getY() + (hitboxJugador.getAlto() / 2)
				- (hitboxBloque.getY() + (hitboxBloque.getAlto() / 2)));

		float mitadDeAnchosCombinados = (float) ((hitboxJugador.getAncho() / 2) + (hitboxBloque.getAncho() / 2));
		float mitadDeAltosCombinados = (float) ((hitboxJugador.getAlto() / 2) + (hitboxBloque.getAlto() / 2));

		if (Math.abs(diferenciaEntreCentrosX) < mitadDeAnchosCombinados
				&& Math.abs(diferenciaEntreCentrosY) < mitadDeAltosCombinados) {
			// Hay colisión
			float superposicionEnX = mitadDeAnchosCombinados - Math.abs(diferenciaEntreCentrosX);
			float superposicionEnY = mitadDeAltosCombinados - Math.abs(diferenciaEntreCentrosY);

			if (superposicionEnX < superposicionEnY) { // Colisión horizontal
				if (diferenciaEntreCentrosX > 0) { // Desde la izquierda
					jugador.setX((int) (jugador.getX() + superposicionEnX));
				} else { // Desde la derecha
					jugador.setX((int) (jugador.getX() - superposicionEnX));
				}
				jugador.setcayendo(); // Evita que el jugador quede "pegado" si es una colisión horizontal

			} else { // Colisión vertical
				if (diferenciaEntreCentrosY > 0) { // Desde arriba del bloque
					int ySuperiorBloque = (int) (hitboxBloque.getY() + hitboxBloque.getAlto());
					jugador.setPosicionPlataforma(ySuperiorBloque);
					jugador.setSobrePlataforma(true);
				} else { // Desde abajo del bloque
					System.out.println("Colision desde abajo");
					if (jugador.getSubiendo()) { // Si Mario está saltando hacia el bloque de pregunta

						bloquePregunta.recibirGolpe(); // Genera el Power-Up o el efecto deseado del bloque de pregunta
						jugador.setcayendo(); // Cambia el estado a cayendo después de golpear el bloque
					}
				}
			}
		}
	}

	public void visitar(BloqueFinal bloque) {
		jugador.cambiarNivel();
	}

	public void visitar(Jugador jugador) {
		// TODO Auto-generated method stub

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