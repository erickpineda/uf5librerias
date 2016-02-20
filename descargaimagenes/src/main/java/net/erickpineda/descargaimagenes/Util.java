package net.erickpineda.descargaimagenes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
  private static Pattern pattern;
  private static Matcher matcher;

  private static final String IMG_PATTERN = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";
  private static final String URL_PATTERN_IMG = "^https?://(?:[a-z0-9\\-]+\\.)+[a-z]{2,6}(?:/[^/#?]+)+\\.(?:jpe?g|gif|bmp|png)$";
  private static final String URL_PATTERN = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";

  public Util() {

  }

  /**
   * Valida una URL con una expresión regular.
   * 
   * @param url url para validar
   * @return true para url válida, false para url no válida
   */
  public static boolean validarURLImagen(final String url) {
    pattern = Pattern.compile(URL_PATTERN_IMG);
    matcher = pattern.matcher(url);
    return matcher.matches();
  }

  /**
   * Valida una URL con una expresión regular.
   * 
   * @param url url para validar
   * @return true para url válida, false para url no válida
   */
  public static boolean validarURL(final String url) {
    pattern = Pattern.compile(URL_PATTERN);
    matcher = pattern.matcher(url);
    return matcher.matches();
  }

  /**
   * Valida una Imágen con una expresión regular.
   * 
   * @param img imágen para velidar
   * @return true para imágen válida, false para imágen no válida
   */
  public static boolean validarImg(final String img) {
    pattern = Pattern.compile(IMG_PATTERN);
    matcher = pattern.matcher(img);
    return matcher.matches();
  }
}
