package Grafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Juego.SesionDeJuego;

public class PantallaInicial extends JPanel {
    private static final long serialVersionUID = 1L;
    private ControladorVistas controladorVistas;
    private JLabel imagenFondo;
    private JButton botonIniciarModo1;
    private JButton botonIniciarModo2;
    private SesionDeJuego sesionDeJuego;

    private static final String RUTA_FONDO_PANTALLA_INICIAL = "/Imagenes/imagenPantallaInicial.png";

    public PantallaInicial(ControladorVistas controladorVistas, SesionDeJuego sesionDeJuego) {
        this.controladorVistas = controladorVistas;
        this.sesionDeJuego = sesionDeJuego;
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarPanelPuntajes();
        agregarImagenFondo();
        agregarBotonIniciarModo1();
        agregarBotonIniciarModo2();
    }
	
	protected void agregarImagenFondo() {
		imagenFondo = new JLabel();
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource(RUTA_FONDO_PANTALLA_INICIAL));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
		imagenFondo.setIcon(iconoImagenEscalado);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		add(imagenFondo);
	}
	
	protected void agregarBotonIniciarModo1() {
		botonIniciarModo1 = new JButton();
		decorarBotonIniciarModo1();
		registrarOyenteBotonIniciarModo1();
		add(botonIniciarModo1);
	}
	
	protected void agregarBotonIniciarModo2() {
		botonIniciarModo2 = new JButton();
		decorarBotonIniciarModo2();
		registrarOyenteBotonIniciarModo2();
		add(botonIniciarModo2);
	}
	
	
	protected void decorarBotonIniciarModo1() {
	    transparentarBoton(botonIniciarModo1);
	    botonIniciarModo1.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 350, ConstantesVistas.PANEL_ALTO - 270, 330, 40); // Mover a la izquierda y ajustar espacio
	}

	protected void decorarBotonIniciarModo2() {
	    transparentarBoton(botonIniciarModo2);
	    botonIniciarModo2.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 350, ConstantesVistas.PANEL_ALTO - 180, 330, 40); // Mover a la izquierda y ajustar espacio
	}

	
	protected void registrarOyenteBotonIniciarModo1() {
		botonIniciarModo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.cambiarModoJuego(1);
				controladorVistas.accionarInicioJuego();
			}
		});
	}
	
	protected void registrarOyenteBotonIniciarModo2() {
		botonIniciarModo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controladorVistas.cambiarModoJuego(2);
					controladorVistas.accionarInicioJuego();
			}
		});
	}
	
	
	protected void transparentarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}
	


	private void agregarPanelPuntajes() {
	    List<String> mejoresPuntajes = sesionDeJuego.obtenerMejoresPuntajes();

	    JPanel panelPuntajes = new JPanel();
	    panelPuntajes.setLayout(new BoxLayout(panelPuntajes, BoxLayout.Y_AXIS));
	    panelPuntajes.setBounds(470, 350, 300, 200);
	    panelPuntajes.setOpaque(false);

	    for (String puntaje : mejoresPuntajes) {
	        JLabel puntajeLabel = new JLabel(puntaje);
	        puntajeLabel.setFont(new Font("Arial", Font.BOLD, 20));
	        puntajeLabel.setForeground(Color.WHITE);  
	        puntajeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        panelPuntajes.add(puntajeLabel);
	    }
	    

	    add(panelPuntajes);
	    revalidate();
	    repaint();
	}
}