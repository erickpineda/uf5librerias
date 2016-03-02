package net.erickpineda.generadorequipos.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.erickpineda.generadorequipos.models.Profesor;

public class Util {
  public static int rand(int desde, int hasta) {
    return (int) (Math.random() * (hasta - desde + 1) + desde);
  }

  public static boolean existeFichero(String ruta) {
    boolean ok = false;

    Pattern patron = Pattern.compile("([a-zA-Z]{1,10})\\w([ 0-9a-zA-Z]{0,10})");
    Matcher mat = patron.matcher(ruta);

    if (!ruta.endsWith(".xml")) {
      ruta += ".xml";
    }
    if (mat.matches()) {
      ok = true;
    }
    return ok;
  }

  public static Profesor extraerDirector(Set<Profesor> profesores) {
    List<Profesor> list = new ArrayList<Profesor>(profesores);
    Profesor profe = list.get(rand(0, list.size() - 1));
    profesores.remove(profe);
    return profe;
  }

  public static Profesor extraerSecretario(Set<Profesor> profesores) {
    List<Profesor> list = new ArrayList<Profesor>(profesores);
    Profesor profe = list.get(rand(0, list.size() - 1));
    profesores.remove(profe);
    return profe;
  }

  public static Profesor extraerCoordinador(Set<Profesor> profesores) {
    List<Profesor> list = new ArrayList<Profesor>(profesores);
    Profesor profe = list.get(rand(0, list.size() - 1));
    profesores.remove(profe);
    return profe;
  }
}
