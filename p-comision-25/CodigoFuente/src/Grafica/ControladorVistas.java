package Grafica;

import java.awt.Container;

import javax.swing.JFrame;


import Juego.EntidadJugador;
import Juego.EntidadLogica;
import Juego.Juego;
import Juego.KeyListenerJugador;

public class ControladorVistas implements ControladorDeVistas,ControladorEntreJuegoVista{
	
	protected JFrame ventana;
	protected PantallaInicial pantallaInicial;
	protected PantallaJuego pantallaJuego;
	protected PantallaFinal PantallaFinal;
	protected PantallaGameOver PantallaGameOver;
	protected PantallaEntreNivel pantallaEntreNivel;
	protected PantallaMuerteNivel pantallaMuerte;
	protected Juego juego;
	protected int modoJuego;
	
	public ControladorVistas(Juego juego) {
		this.juego = juego;
		modoJuego=1;
		configurarVentana();
		this.pantallaInicial = new PantallaInicial(this, this.juego.getSesion());
		this.pantallaJuego = new PantallaJuego(this.juego.getSesion());
		this.PantallaFinal = new PantallaFinal();
		this.PantallaGameOver = new PantallaGameOver(this.juego.getSesion(), this);
	}
	
	public void nuevaPantallaInicial() {
		this.pantallaInicial = new PantallaInicial(this, juego.getSesion());
	}

	protected void configurarVentana() {
		    System.out.println("Configurando ventana...");
			ventana = new JFrame("TDP COMISION 25 SuperMarioBros");
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.setResizable(false);
			ventana.setSize(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
			ventana.setLocationRelativeTo(null);
			ventana.setVisible(true);
		
	}
	
	public void mostrarPantallaInicial() {
		ventana.setContentPane(pantallaInicial);
		refrescar();
	}

	public void mostrarPantallaJuego() {
	    // Asegúrate de que sólo se crea una vez la instancia de pantallaJuego
	    if (pantallaJuego == null) {
	        pantallaJuego = new PantallaJuego(juego.getSesion());
	    }
	    ventana.setContentPane(pantallaJuego);
	    ventana.setSize(ConstantesVistas.PANEL_JUEGO_ANCHO, ConstantesVistas.PANEL_JUEGO_ALTO);
	    refrescar();
	}

	
	public void mostrarPantallaGameOver() {
		ventana.setContentPane(PantallaGameOver);
		refrescar();
		
	}
	public void mostrarPantallaEntreNivel() {
		ventana.setContentPane(pantallaEntreNivel);
		refrescar();
	}
	public void mostrarPantallaMuerteNivel() {
		ventana.setContentPane(pantallaMuerte);
		refrescar();
	}

	public void crearNuevaPantallaEntreNivel(int nivel) {
		pantallaEntreNivel= new PantallaEntreNivel(nivel);
	}
	public void crearNuevaPantallaJuego() {
		pantallaJuego= new PantallaJuego(juego.getSesion());
	}
	public void crearNuevaPantallaMuerte(int vidas, int nivel) {
		pantallaMuerte= new PantallaMuerteNivel(vidas, nivel);
	}
	
	protected void refrescar() {
		ventana.revalidate();
		ventana.repaint();
	}

	public void accionarInicioJuego() {
		juego.iniciar();
	}
	public void accionarGameOver() {
		juego.gameOver();
	}

	public void accionarPantallaRanking() {
		// to do
	}

	public void cambiarModoJuego(int modo) {
		modoJuego=modo;
		System.out.println("Modo de juego cambiado a: "+modo);
	}
	public int getModoJuego() {
		return modoJuego;
	}
	
	public Observer registrarEntidad(EntidadLogica entidadLogica) {
		Observer observerEntidad = pantallaJuego.incorporarEntidad(entidadLogica);
		refrescar();
		return observerEntidad;
	}
	
	public Observer registrarEntidad(EntidadJugador entidadJugador) {
		Observer observerJugador = pantallaJuego.incorporarEntidadJugador(entidadJugador);
		refrescar();
		return observerJugador;
	}
	
	public Observer registrarSilueta(EntidadLogica silueta) {
		Observer observerSilueta = pantallaJuego.incorporarSilueta(silueta);
		refrescar();
		return observerSilueta;
	}
	
	public void registrarKeyListener(KeyListenerJugador oyenteTeclado) {
		ventana.addKeyListener(oyenteTeclado);
		ventana.setFocusable(true);
		ventana.requestFocusInWindow();
	}
	
}