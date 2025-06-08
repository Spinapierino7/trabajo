package Juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;

import Jugador.Estado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SesionDeJuego {
    private int puntaje;
    private int tiempoRestante;
    private Timer timer;
    private int vidas;
    private Estado estadoJugador;

    public SesionDeJuego() {
        this.puntaje = 0;
        vidas=3;
        this.tiempoRestante = 121; // Inicializa el tiempo restante en 120 segundos (2 minutos)
        iniciarContador();
        PausarTiempo();
        
    }
    public void PausarTiempo() {
    	timer.stop();
    }
    public void ReanudarTiempo() {
    	timer.start();
    }
    public boolean hayTiempo() {
    	return tiempoRestante!=0;
    }
    private void iniciarContador() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrementarTiempo(1); // Decrementa el tiempo en 1 segundo
            }
        });
        timer.start(); // Inicia el temporizador
    }

    public void incrementarPuntaje(int puntos) {
        this.puntaje += puntos;
    }
    public void decrementarPuntaje(int puntos) {
    	this.puntaje -= puntos;
    }

    public void decrementarTiempo(int segundos) {
        tiempoRestante -= segundos;
        if (tiempoRestante < 0) {
            tiempoRestante = 0; // Asegura que el tiempo no sea negativo
        }
        // Imprimir tiempo restante para depuración
        System.out.println("Tiempo restante: " + tiempoRestante + " segundos");
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getVidas() {
        return vidas;
    }

    public void sumarVida() {
    	vidas++;
    }
    public void restarVida() {
        vidas--;
    }

    public boolean isGameOver() {
        return vidas == 0;
    }
   
    public int getPuntaje() {
        return puntaje;
    }

    public void reiniciar() {
       tiempoRestante = 120; // Reinicia el tiempo a 120 segundos
       System.out.println("Tiempo reiniciado a: " + tiempoRestante + " segundos");
       puntaje = 0;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public List<String> obtenerMejoresPuntajes() {
        List<String> puntajes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("puntajes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(" - Puntaje: ")) {
                    puntajes.add(linea);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Collections.sort(puntajes, (a, b) -> {
            try {
                int puntajeA = Integer.parseInt(a.split(": ")[1]);
                int puntajeB = Integer.parseInt(b.split(": ")[1]);
                return Integer.compare(puntajeB, puntajeA);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        });

        List<String> mejoresPuntajes = new ArrayList<>();
        for (int i = 0; i < Math.min(puntajes.size(), 5); i++) {
            mejoresPuntajes.add((i + 1) + ". " + puntajes.get(i));
        }

        return mejoresPuntajes;
    }

    public void guardarPuntaje(String nombreJugador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("puntajes.txt", true))) {
            writer.write(nombreJugador + " - Puntaje: " + puntaje);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	public int  getVidasRestantes() {
		return vidas;
	}
	public void guardarEstado(Estado estado) {
		estadoJugador = estado;
		System.out.println("se guardo");
	}
	public Estado getEstadoGuardado() {
		return estadoJugador;
	}
}
