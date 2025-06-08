package Sonido;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

import Fabricas.FabricaSonido;

public class SonidoManager {

    private static SonidoManager instancia; // Instancia única del singleton
    private FabricaSonido fabricaSonido;
    private Map<String, Clip> sonidos;

    // Constructor privado
    private SonidoManager() {
        sonidos = new HashMap<>();
        fabricaSonido = new FabricaSonido(this);
        try {
            fabricaSonido.InicializarSonidos();
        } catch (Exception e) {
            System.err.println("Error al cargar uno de los sonidos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método estático para obtener la instancia del singleton
    public static SonidoManager getInstancia() {
        if (instancia == null) {
            instancia = new SonidoManager();
        }
        return instancia;
    }

    public void cargarEfecto(String nombre, String rutaArchivo) {
        try {
            AudioInputStream audioStream = cargarYConvertirAudio(rutaArchivo);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            sonidos.put(nombre, clip);
        } catch (Exception e) {
            System.err.println("Error al cargar el sonido " + nombre + ": " + e.getMessage());
        }
    }

    private AudioInputStream cargarYConvertirAudio(String ruta) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(ruta);
            if (inputStream == null) {
                throw new IOException("No se encontró el archivo: " + ruta);
            }

            BufferedInputStream bufferedIn = new BufferedInputStream(inputStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            
            AudioFormat formatoBase = audioStream.getFormat();
            if (formatoBase.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                AudioFormat formatoDecodificado = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    formatoBase.getSampleRate(),
                    16,
                    formatoBase.getChannels(),
                    formatoBase.getChannels() * 2,
                    formatoBase.getSampleRate(),
                    false);
                return AudioSystem.getAudioInputStream(formatoDecodificado, audioStream);
            }
            return audioStream;
        } catch (Exception e) {
            System.out.println("Error al cargar archivo de audio: " + e.getMessage());
            return null;
        }
    }

    public void reproducirEfecto(String clave) {
        Clip clip = sonidos.get(clave);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void iniciarMusicaFondo() {
        Clip clip = sonidos.get("marioJuego");
        clip.setFramePosition(0);
        clip.loop(clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public void pausarMusicaFondo() {
        this.detenerSonido("marioJuego");
    }

    public void detenerSonido(String clave) {
        Clip clip = sonidos.get(clave);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
