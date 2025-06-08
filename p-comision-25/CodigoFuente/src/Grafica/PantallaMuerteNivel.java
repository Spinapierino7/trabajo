package Grafica;

import javax.swing.*;
import java.awt.*;

public class PantallaMuerteNivel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel imagenFondo;
    private static final String RUTA_NIVEL_1 = "/Imagenes/VIDASNIVEL1";
    private static final String RUTA_NIVEL_2 = "/Imagenes/VIDASNIVEL2";
    private static final String RUTA_NIVEL_3 = "/Imagenes/VIDASNIVEL3";
    private static final String RUTA_NIVEL_4 = "/Imagenes/VIDASNIVEL4";
    private static final String RUTA_DEFAULT = "/Imagenes/VIDASNIVEL1";
    
    private static final String RUTA_VIDAS_1 ="/vidas1.png";
    private static final String RUTA_VIDAS_2 = "/vidas2.png";
    private static final String RUTA_VIDAS_3 ="/vidas3.png";
    
    public PantallaMuerteNivel(int vidas,int nivel) {

        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        String rutaImagen= crearRutaImagen(vidas, nivel);
        
        System.out.println("Ruta de imagen final: " + rutaImagen);
        
        configurarImagenFondo(rutaImagen);
        
        add(imagenFondo);
    }
    
    private void configurarImagenFondo(String ruta) {
    	imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource(ruta));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalado);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
    }
    private String crearRutaImagen(int vidas, int nivel) {
    	String ruta;
        
        switch (nivel) {
            case 1:
                ruta = RUTA_NIVEL_1;
                break;
            case 2:
                ruta = RUTA_NIVEL_2;
                break;
            case 3:
                ruta = RUTA_NIVEL_3;
                break;
            case 4:
            	ruta = RUTA_NIVEL_4;
            	break;
            default:
                ruta= RUTA_DEFAULT;
                break;
        }
        switch(vidas) {
	        case 1:
	            ruta = ruta + RUTA_VIDAS_1;
	            break;
	        case 2:
	            ruta = ruta + RUTA_VIDAS_2;
	            break;
	        case 3:
	            ruta = ruta + RUTA_VIDAS_3;
	            break;
	        default:
	            ruta = ruta + RUTA_VIDAS_3;
	            break;
        }
        
        return ruta;
    }
}

