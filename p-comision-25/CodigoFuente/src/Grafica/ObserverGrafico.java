package Grafica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Juego.EntidadLogica;


public abstract class ObserverGrafico extends JLabel implements Observer{
	
	private static final long serialVersionUID = 1L;
	private EntidadLogica entidadObservada;
	
	protected ObserverGrafico(EntidadLogica entidadObservada) {
		super();
		this.entidadObservada = entidadObservada;
	}
	
	public void actualizar() {
		actualizarImagen();
		actualizarPosicionTamano();
	}
	
	protected void actualizarImagen() {
		String rutaImagen = entidadObservada.getSprite().getRutaImagen();
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
	
	protected void actualizarPosicionTamano() {
		int x = AdaptadorPosicionPixel.transformarX(entidadObservada.getX());
		int y = AdaptadorPosicionPixel.transformarY(entidadObservada.getY());
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y, ancho, alto);
	}
}
