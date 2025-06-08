package Fabricas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	protected String rutaAImagen;
	protected int ancho;
    protected int alto;
    protected Image imagen;
	
	
	public Sprite (String rutaAImagen) {
		this.rutaAImagen = rutaAImagen;
		
		cargarDimensiones();
	}

	public void cargarDimensiones() {
		
		try {
			BufferedImage imagen = ImageIO.read(getClass().getClassLoader().getResource(rutaAImagen));
			
			alto = imagen.getHeight();
			ancho = imagen.getWidth();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getRutaImagen() {
		return rutaAImagen;
	}	


	public int getAncho() {
		return ancho;
	}


	public void setAncho(int ancho) {
		this.ancho = ancho;
	}


	public int getAlto() {
		return alto;
	}


	public void setAlto(int alto) {
		this.alto = alto;
	}
	
}
