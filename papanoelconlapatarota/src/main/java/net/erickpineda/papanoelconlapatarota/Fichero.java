package net.erickpineda.papanoelconlapatarota;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class Fichero {
    private BufferedReader br = null;
    private String nombreFichero;
    private String msj = "***NO SE HA PODIDO LEER EL FICHERO***";
    private String lineaFichero = "";
    private static final Logger logger = Logger.getLogger(Fichero.class.getName());
    private Set<String> casas;

    public Fichero(final String nom) {
        this.nombreFichero = nom;
        casas = new HashSet<String>();
    }

    /**
     * Abrir el fichero de lectura.
     */
    public void abrirFichero() {
        InputStream entrada = Fichero.class.getResourceAsStream(nombreFichero);
        logger.info("Abriendo fichero " + nombreFichero);

        if (entrada != null) {
            Reader leer = new InputStreamReader(entrada);
            br = new BufferedReader(leer);

        } else {
            logger.error(msj);
        }
    }

    /**
     * Leer el fichero que contiene las instrucciones.
     */
    public void leerFichero() {
        try {
            lineaFichero = br.readLine();
        } catch (IOException e) {
            logger.error("Error de lectura del fichero", e);
        }
    }

    /**
     * Crea las casas segun las rutas en posicion vertical y horizontal.
     */
    public void comprobarRutas() {
        int posHorizontal = 0, posVertical = 0;

        for (int i = 0; i < lineaFichero.length(); i++) {

            if (lineaFichero.charAt(i) == '>') {
                posHorizontal++;
            }
            if (lineaFichero.charAt(i) == '<') {
                posHorizontal--;
            }
            if (lineaFichero.charAt(i) == 'v') {
                posVertical++;
            }
            if (lineaFichero.charAt(i) == '^') {
                posVertical--;
            }

            String casa = "Casa ph:" + posHorizontal + " pv:" + posVertical;
            casas.add(casa);

        }
    }

    /**
     * Mostrar las casas segun sus posiciones verticales y horizontales.
     */
    public void pintarCasas() {
        casas.forEach(c -> System.out.println(c));
        System.out.println("-> Total casas: " + casas.size());
    }

    /**
     * Cierra el fichero.
     */
    public void cerrarFichero() {
        try {
            br.close();
            logger.info("Fichero" + nombreFichero + " cerrado correctamente");
        } catch (IOException e) {
            logger.error("No se pudo cerrar el fichero", e);
        }
    }

    /**
     * 
     * @return retorna la linea actual de lectura del fichero.
     */
    public String getLinea() {
        return lineaFichero;
    }
}
