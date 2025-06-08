package Juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyListenerJugador implements KeyListener {
    
    protected EntidadJugador jugador;
    private Set<Integer> teclasPresionadas; // Conjunto para almacenar las teclas presionadas

    public KeyListenerJugador(EntidadJugador jugador) {
        this.jugador = jugador;
        this.teclasPresionadas = new HashSet<>();
    }

    public void keyTyped(KeyEvent e) {        
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        teclasPresionadas.add(code); // Agregar la tecla presionada al conjunto

        // Manejo de las teclas en función del estado actual
        if (teclasPresionadas.contains(KeyEvent.VK_W) && (!jugador.getSubiendo() || jugador.getSobrePlataforma())) {
        	jugador.setDireccion(3); //Direccion de salto
        	jugador.saltar();
        }

        if (teclasPresionadas.contains(KeyEvent.VK_A)) {
            jugador.setDireccion(2); // Dirección a la izquierda en movimiento
        }

        if (teclasPresionadas.contains(KeyEvent.VK_D)) {
            jugador.setDireccion(1); // Dirección a la derecha en movimiento
        }

        if (teclasPresionadas.contains(KeyEvent.VK_SPACE)) {
            jugador.lanzarBolasDeFuego(); // Lanzar bola de fuego
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        teclasPresionadas.remove(code); // Eliminar la tecla del conjunto al soltar

        // Cambiar la dirección según la tecla soltada
        if (code == KeyEvent.VK_W) {
            // No hacer nada al soltar W, porque el salto se maneja en keyPressed
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_A) {
            // Si se suelta D o A, ajustar la dirección según la última tecla
            if (teclasPresionadas.contains(KeyEvent.VK_D)) {
                jugador.setDireccion(1); // Mirando a la derecha en movimiento
            } else if (teclasPresionadas.contains(KeyEvent.VK_A)) {
                jugador.setDireccion(2); // Mirando a la izquierda en movimiento
            } else {
                // Sin movimiento: -1 si estaba mirando a la izquierda, 0 si estaba mirando a la derecha
                jugador.setDireccion(jugador.getDireccion() == 2 ? -1 : 0);
            }
        }
    }
}