package Juego;

import java.util.ArrayList;
import java.util.List;

import Enemigos.Enemigo;
import Jugador.BolaDeFuego;
import PowerUps.PowerUp;

public class MovimientoEntidad extends Thread {
    
    protected Juego juego;
    private volatile boolean enEjecucion = true; // Indica si el hilo está en ejecución
    private volatile boolean enPausa = false; // Indica si el hilo está en pausa

    public MovimientoEntidad(Juego juego) {
        this.juego = juego;
    }
    
    @Override
    public void run() {
    	long ultimoTiempo = System.currentTimeMillis();
        while (enEjecucion) {
            try {
                // Verifica si el hilo está en pausa
                synchronized (this) {
                    while (enPausa) {
                        wait(); // Espera hasta que no esté en pausa
                    }
                }

                sleep(17);
                long tiempoActual = System.currentTimeMillis();
                long deltaTiempo = tiempoActual - ultimoTiempo; // Tiempo transcurrido desde la última actualización
                ultimoTiempo = tiempoActual;
                synchronized (juego.getNivelActual()) {
                    List<Enemigo> listaEnemigosEnNivel = juego.getNivelActual().getListaEnemigos();
                    List<PowerUp> listaPowerUps = juego.getNivelActual().getListaPowerUps();
                    List<BolaDeFuego> listaBolaDeFuego = juego.getNivelActual().getListaBolaDefuego();

                    for (Enemigo enemigo : new ArrayList<>(listaEnemigosEnNivel)) {
                        enemigo.mover();
                        enemigo.actualizar(deltaTiempo);
                    }
                    for (PowerUp pw : new ArrayList<>(listaPowerUps)) {
                        pw.mover();
                    }
                    for (BolaDeFuego boladefuego : new ArrayList<>(listaBolaDeFuego)) {
                        boladefuego.mover();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hilo Entidades detenido.");
    }

    public void detener() {
        enEjecucion = false;
    }

    public void pausar() {
        enPausa = true; // Establece la bandera de pausa
    }

    public void reanudar() {
        synchronized (this) {
            enPausa = false; // Cambia la bandera a false para reanudar
            notify(); // Notifica al hilo que está esperando
        }
    }
}
