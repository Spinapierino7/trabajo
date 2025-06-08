package Juego;

public class MovimientoJugador extends Thread {
    
    protected Juego juego;
    private volatile boolean enEjecucion = true; // Indica si el hilo está en ejecución
    private volatile boolean enPausa = false; // Indica si el hilo está en pausa

    public MovimientoJugador(Juego juego) {
        this.juego = juego;
    }
    
    @Override
    public void run() {
        while (enEjecucion) {
            try {
                // Verifica si el hilo está en pausa
                synchronized (this) {
                    while (enPausa) {
                        wait(); // Espera hasta que no esté en pausa
                    }
                }

                sleep(20); 
                juego.getNivelActual().getJugador().mover();
                juego.detectarColisiones();
                if (!juego.getSesion().hayTiempo()) {
                    juego.getNivelActual().getJugador().morir();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hilo Mario detenido");
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
