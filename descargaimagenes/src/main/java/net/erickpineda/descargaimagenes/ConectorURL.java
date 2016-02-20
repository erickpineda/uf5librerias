package net.erickpineda.descargaimagenes;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class ConectorURL {
  /**
   * URL para proceder la descarga.
   */
  private String URL_IMG;
  /**
   * Carpeta donde se leeran y descargaran todas la imágenes.
   */
  private static final String CARPETA = "img";
  /**
   * Semaforo que comprobara si la imágen o imágenes se han descargado correctamente.
   */
  private boolean imgOK = true;

  public ConectorURL() {

  }

  /**
   * Método para descargar una sola imágen.
   */
  public void descargarImagen() {
    try {
      Response res = Jsoup.connect(URL_IMG).ignoreContentType(true).execute();
      descargar(res);
      setImgOK(true);
    } catch (IOException ex) {
      setImgOK(false);
      System.err.println("Error");
    }
  }

  /**
   * Método para descargar varias imágenes.
   */
  public void descargarImagenes() {
    try {

      Document doc = Jsoup.connect(URL_IMG).get();
      Elements img = doc.select("img");

      for (Element el : img) {
        String src = el.absUrl("src");
        String name = obtenerNombreImagen(src);
        URL url = new URL(src);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(CARPETA + name));

        for (int b; (b = in.read()) != -1;) {
          out.write(b);
        }
        out.close();
        in.close();
      }
      setImgOK(true);
    } catch (IOException ex) {
      setImgOK(false);
      System.err.println("There was an error");
    }
  }

  /**
   * 
   * @param res response a partir de la url designada.
   * @throws IOException
   */
  private void descargar(final Response res) throws IOException {
    String img = obtenerNombreImagen(URL_IMG);
    OutputStream out = new BufferedOutputStream(new FileOutputStream(CARPETA + img));
    out.write(res.bodyAsBytes());
    out.close();
  }

  /**
   * Define el nombre de la imágen a partir de su url.
   * 
   * @param imgPath ruta de la imagen.
   * @return retorna un String con el nombre de la imagen.
   */
  private String obtenerNombreImagen(String imgPath) {
    int index = imgPath.lastIndexOf("/");

    if (index == imgPath.length()) {
      imgPath = imgPath.substring(1, index);
    }

    index = imgPath.lastIndexOf("/");
    return imgPath.substring(index, imgPath.length());
  }

  /**
   * 
   * @param url URL a cambiar.
   */
  public void setURL_IMG(String url) {
    this.URL_IMG = url;
  }

  /**
   * 
   * @return true si la imágen se ha descargado correctamente.
   */
  public boolean isImgOK() {
    return imgOK;
  }

  /**
   * 
   * @param imgOK cambia true o false según la imagen se haya descagado correctamente.
   */
  public void setImgOK(boolean imgOK) {
    this.imgOK = imgOK;
  }
}
