package net.erickpineda.subvencionesdenavidad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Fichero {
  private BufferedReader br = null;
  private String nombreFichero;
  private String msj = "***NO SE HA PODIDO LEER EL FICHERO***";
  private String lineaFichero = "";
  private static final Logger logger = Logger.getLogger(Fichero.class.getName());
  private Map<String, Personaje> mapa;
  private String regexpNombreCrio = "(^[A-z:])\\S+.";

  public Fichero(final String nom) {
    this.nombreFichero = nom;
    this.mapa = new HashMap<String, Personaje>();
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
      pintar();
    } catch (IOException e) {
      logger.error("Error de lectura del fichero", e);
    }
  }

  public void pintar() throws IOException {
    while (lineaFichero != null) {
      if (!lineaFichero.matches(regexpNombreCrio)) {
        String[] array = lineaFichero.split(regexpNombreCrio);
        String crio = array[0];
        System.out.println(crio);
        System.out.println(lineaFichero.replaceAll(regexpNombreCrio, "Crio "));
      }
      lineaFichero = br.readLine();
    }
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
