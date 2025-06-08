package Grafica;

import Juego.EntidadJugador;
import Juego.EntidadLogica;
import Juego.KeyListenerJugador;

public interface ControladorEntreJuegoVista {
	public Observer registrarEntidad(EntidadLogica entidadLogica);
	public Observer registrarEntidad(EntidadJugador entidadJugador);
	public Observer registrarSilueta(EntidadLogica silueta);
	public void mostrarPantallaJuego();
	public void mostrarPantallaGameOver();
	public void mostrarPantallaEntreNivel();
	public void crearNuevaPantallaJuego(); 
	public void crearNuevaPantallaMuerte(int vidas,int nivel);
	public void registrarKeyListener(KeyListenerJugador oyenteTeclado);
}
