package net.erickpineda.seriestv.utils;

public class Util {
  private static String OS = System.getProperty("os.name").toLowerCase();

  public static boolean isWindows() {
    return (OS.indexOf("win") >= 0);
  }

  public static boolean isMac() {
    return (OS.indexOf("mac") >= 0);
  }

  public static boolean isUnix() {
    return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
  }

  public static boolean isSolaris() {
    return (OS.indexOf("sunos") >= 0);
  }

  /**
   * Define el nombre de la imágen a partir de su url.
   * 
   * @param imgPath ruta de la imagen.
   * @return retorna un String con el nombre de la imagen.
   */
  public static String obtenerNombreImagen(String imgPath) {
    int index = imgPath.lastIndexOf("/");

    if (index == imgPath.length()) {
      imgPath = imgPath.substring(1, index);
    }

    index = imgPath.lastIndexOf("/");
    return imgPath.substring(index, imgPath.length());
  }
}
