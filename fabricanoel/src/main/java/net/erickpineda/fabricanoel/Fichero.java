package net.erickpineda.fabricanoel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.Logger;

public class Fichero {
  private BufferedReader br = null;
  private String nombreFichero;
  private String msj = "***NO SE HA PODIDO LEER EL FICHERO***";
  private String lineaFichero = "";
  private static final Logger logger = Logger.getLogger(Fichero.class.getName());

  public Fichero(final String nom) {
    this.nombreFichero = "/" + nom;
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
      while (lineaFichero != null) {
        System.out.println(lineaFichero);
        lineaFichero = br.readLine();
      }
    } catch (IOException e) {
      logger.error("Error de lectura del fichero", e);
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
