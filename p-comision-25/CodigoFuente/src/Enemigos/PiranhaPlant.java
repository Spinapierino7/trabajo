package Enemigos;

import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Visitor.Visitor;

public class PiranhaPlant extends Enemigo {
    
    private int velocidad;
    protected Observer observer;
    protected Hitbox hitbox;

    private long tiempoInicio;
    private EstadoMovimiento estadoMovimiento;
    private final int tiempoEspera = 2500; 
    private final int distanciaMaxima = 38; 
    private final int posicionInicial; // Para registrar la posición inicial de la planta

    private enum EstadoMovimiento {
        SUBIENDO, ESPERA_ARRIBA, BAJANDO, ESPERA_ABAJO
    }

    public PiranhaPlant(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        velocidad = 5;
        this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
        estadoMovimiento = EstadoMovimiento.SUBIENDO;
        tiempoInicio = System.currentTimeMillis();
        direccionDeEntidad = -1;
        posicionInicial = y;
    }

    public void mover() {
        long tiempoActual = System.currentTimeMillis();

        switch (estadoMovimiento) {
            case SUBIENDO:
                if (y > posicionInicial - distanciaMaxima) { // Subir hasta 32 unidades
                    y -= velocidad;
                    hitbox.setY(y);
                } else {
                    estadoMovimiento = EstadoMovimiento.ESPERA_ARRIBA;
                    tiempoInicio = tiempoActual;
                }
                break;

            case ESPERA_ARRIBA:
                if (tiempoActual - tiempoInicio >= tiempoEspera) {
                    estadoMovimiento = EstadoMovimiento.BAJANDO;
                    tiempoInicio = tiempoActual;
                }
                break;

            case BAJANDO:
                if (y < posicionInicial) { // Bajar hasta la posición inicial
                    y += velocidad;
                    hitbox.setY(y);
                } else {
                    estadoMovimiento = EstadoMovimiento.ESPERA_ABAJO;
                    tiempoInicio = tiempoActual;
                }
                break;

            case ESPERA_ABAJO:
                if (tiempoActual - tiempoInicio >= tiempoEspera) {
                    estadoMovimiento = EstadoMovimiento.SUBIENDO;
                    tiempoInicio = tiempoActual;
                }
                break;
        }

        observer.actualizar();
    }

    @Override
	public void recibirGolpe() {
		desaparecer();
		direccionDeEntidad =0;
		y += 32*50;
		sesion.incrementarPuntaje(30);
	}

	@Override
	public void recibirGolpePorBolaDefuego() {
		
		recibirGolpe();
	}

    public void registrarObserver(Observer observer) {
        this.observer = observer;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void detener() {
        velocidad = 0;
    }
    //patron Visitor redefinido en clases especificas
    @Override
	public void accept(Visitor v) {
	    v.visitar(this);
	}
    
	
}
