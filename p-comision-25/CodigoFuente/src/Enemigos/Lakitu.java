package Enemigos;

import java.util.Random;


import Fabricas.FabricaEntidades;

import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Juego.Nivel;
import Jugador.Jugador;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class Lakitu extends Enemigo {
		
		private int velocidad;
		protected  Jugador jugador;
		protected Observer observer;
		protected Hitbox hitbox;
		protected Nivel nivel;
		private static final long INTERVALO_LANZAMIENTO = 12000; // Intervalo en milisegundos (12 segundos)
		private long tiempoAcumulado = 0;


		public Lakitu(Sprite sprite, int x, int y ) {
	        super(sprite, x, y);
	        velocidad = 3;
	        this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
	    }

	    public void mover() {
	    	x += velocidad * direccionDeEntidad ;
	        hitbox.setX(x);
	        hitbox.setY(y);
	        observer.actualizar();
	        
	    }


	    public void registrarObserver(Observer observer) {
	        this.observer = observer;
	    }

	   
	    
	    public Hitbox getHitbox() {
	        return hitbox;
	    }


		@Override
		public void recibirGolpe() {
			desaparecer();
			direccionDeEntidad =0;
			y += 32*50;
			sesion.incrementarPuntaje(60);
		}

		@Override
		public void recibirGolpePorBolaDefuego() {
			
			recibirGolpe();
		}
		public Spiny generarSpiny() {
			
			Spiny spiny = null; // Inicializamos la variable
			Observer obv = null;
			
			spiny = nivel.agregarSpiny(x, y-32);
			obv = nivel.getJuego().getControlador().registrarEntidad(spiny);
			spiny.registrarObserver(obv);
			return spiny;
		}
	
		  public void actualizar(long deltaTiempo) {
		        // Acumula el tiempo transcurrido
		        tiempoAcumulado += deltaTiempo;

		        // Verifica si ha pasado el intervalo para lanzar un Spiny
		        if (tiempoAcumulado >= INTERVALO_LANZAMIENTO) {
		            generarSpiny(); // Lanza un nuevo Spiny
		            tiempoAcumulado = 0; // Reinicia el acumulador
		        }
		    }

		public void accept(Visitor v) {
			    v.visitar(this);
			}
		
		public void setNivel(Nivel nivelActual) {
			nivel = nivelActual;
		}
}