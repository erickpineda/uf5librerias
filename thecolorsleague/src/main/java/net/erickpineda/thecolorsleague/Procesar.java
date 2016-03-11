package net.erickpineda.thecolorsleague;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Procesar extends DefaultHandler {
  /**
   * Etiqueta <i>partit</i>.
   */
  private String TAG_PARTIT = "partit";
  /**
   * Etiqueta <i>nom</i>.
   */
  private String TAG_NOM = "nom";
  /**
   * Etiqueta <i>club</i>.
   */
  private String TAG_CLUB = "club";
  /**
   * Etiqueta <i>president</i>.
   */
  private String TAG_PRESIDENT = "president";
  /**
   * Etiqueta <i>resultat</i>.
   */
  private String TAG_RESULTAT = "resultat";
  /**
   * Comprueba si la etiqueta <i>nom</i> esta abierta o cerrada.
   */
  private boolean TAG_NOM_OK = false;
  /**
   * Comprueba si la etiqueta <i>club</i> esta abierta o cerrada.
   */
  private boolean TAG_CLUB_OK = false;
  /**
   * Comprueba si la etiqueta <i>partit</i> esta abierta o cerrada.
   */
  private boolean TAG_PARTIT_OK = false;
  /**
   * Comprueba si la etiqueta <i>president</i> esta abierta o cerrada.
   */
  private boolean TAG_PRESIDENT_OK = false;
  /**
   * Comprueba si la etiqueta <i>resultat</i> esta abierta o cerrada.
   */
  private boolean TAG_RESULTAT_OK = false;
  /**
   * Conjunto de clubes que conforman la liga.
   */
  private Set<Club> clubs;
  /**
   * Lista que se usa para almacenar cada partido, una vez finalizado se vacía la lista para
   * reutilizar la variable.
   */
  private List<Club> partido = new ArrayList<Club>();
  /**
   * Club temporal que se usa para crear los equipos.
   */
  private Club clubTemp = null;

  public void endDocument() throws SAXException {
    System.out.println("Presi\t       \tEquipo\t\tPos\tV \tE \tD \tGF \tGC \tPts");
    ordenarPorPuntos().forEach(c -> System.out.println(c));
    System.out.println();
  }

  public void startDocument() throws SAXException {
    this.clubs = new HashSet<Club>();
  }

  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    marcarTagsComo(qName, true);
  }

  public void characters(char[] ch, int start, int length) throws SAXException {
    recolectarDatosDelClub(new String(ch, start, length).trim());
  }

  /**
   * Método que se encarga de clasificar la información del club, según los datos que le llegan.
   * 
   * @param data String de información que se recoge del fichero XML.
   */
  private void recolectarDatosDelClub(String data) {
    if (TAG_NOM_OK && TAG_CLUB_OK) {
      this.clubTemp.setNombre(data);
    }
    if (TAG_NOM_OK && TAG_PARTIT_OK) {
      clubTemp = findByNombre(data);
    }
    if (TAG_PRESIDENT_OK) {
      this.clubTemp.setPresidente(data);
    }
    if (TAG_RESULTAT_OK) {
      clubTemp.setGMTemp(Integer.parseInt(data));
      partido.add(clubTemp);
    }
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    marcarTagsComo(qName, false);
  }

  /**
   * Método que busca el nombre de una etiqueta, si la encuentra, pondrá el valor a true, cuando
   * cierra la etiqueta será false (por defecto es false).
   * 
   * @param qName String que recibe el nombre de la etiqueta XML.
   * @param valor parámetro que se usa para cambiar las variables coincidentes a true o false según
   *          la etiqueta este abierta o cerrada.
   */
  private void marcarTagsComo(String qName, boolean valor) {
    if (qName.equals(TAG_CLUB)) {
      TAG_CLUB_OK = valor;
      if (TAG_CLUB_OK) {
        this.clubTemp = new Club();
      } else {
        this.clubs.add(clubTemp);
      }
    }
    if (qName.equals(TAG_PARTIT)) {
      TAG_PARTIT_OK = valor;
      if (!TAG_PARTIT_OK) {
        comprobarResultadosDelPartido();
      }
    }
    if ((qName.equals(TAG_NOM) && TAG_CLUB_OK) || (qName.equals(TAG_NOM) && TAG_PARTIT_OK)) {
      TAG_NOM_OK = valor;
    }
    if (qName.equals(TAG_PRESIDENT)) {
      TAG_PRESIDENT_OK = valor;
    }
    if (qName.equals(TAG_RESULTAT)) {
      TAG_RESULTAT_OK = valor;
    }
  }

  /**
   * Método que compara la lista de {@code List<Club> partido}, para saber quien gana, pierde o si
   * empatan.
   */
  private void comprobarResultadosDelPartido() {

    if (partido.get(0).getGMTemp() > partido.get(1).getGMTemp()) {
      ganadorLocal(partido.get(0), partido.get(1));
    } else if (partido.get(0).getGMTemp() == partido.get(1).getGMTemp()) {
      hanEmpatado(partido.get(0), partido.get(1));
    } else {
      ganadorVisitante(partido.get(0), partido.get(1));
    }
    completarDatosClub(partido.get(0), partido.get(1));
    partido.clear();
  }

  /**
   * Da una victoria al club local y una derrota al equipo visitante.
   * 
   * @param c1 club local.
   * @param c2 club visitante.
   */
  private void ganadorLocal(Club c1, Club c2) {
    c1.sumarVictoria();
    c1.sumarGolesAFavor(c1.getGMTemp());
    c1.sumarGolesEnContra(c2.getGMTemp());

    c2.sumarDerrota();
    c2.sumarGolesAFavor(c2.getGMTemp());
    c2.sumarGolesEnContra(c1.getGMTemp());
  }

  /**
   * Si el resultado del partido es un empate, se suma 1 punto.
   * 
   * @param c1 club local.
   * @param c2 club visitante.
   */
  private void hanEmpatado(Club c1, Club c2) {
    c1.sumarEmpate();
    c1.sumarGolesAFavor(c1.getGMTemp());
    c1.sumarGolesEnContra(c2.getGMTemp());

    c2.sumarEmpate();
    c2.sumarGolesAFavor(c2.getGMTemp());
    c2.sumarGolesEnContra(c1.getGMTemp());
  }

  /**
   * Da una victoria al club visitante y una derrota al club local.
   * 
   * @param c1 club local.
   * @param c2 club visitante.
   */
  private void ganadorVisitante(Club c1, Club c2) {
    c2.sumarVictoria();
    c2.sumarGolesAFavor(c2.getGMTemp());
    c2.sumarGolesEnContra(c1.getGMTemp());

    c1.sumarDerrota();
    c1.sumarGolesAFavor(c1.getGMTemp());
    c1.sumarGolesEnContra(c2.getGMTemp());
  }

  /**
   * Método que guarda la información nueva de la liga, según avanza cada jornada.
   * 
   * @param c1 club local.
   * @param c2 club visitante.
   */
  private void completarDatosClub(Club c1, Club c2) {
    for (Club c : clubs) {
      if (c.getNombre().equals(c1.getNombre())) {
        c = c1; // Reemplazo los valores nuevos
      }
      if (c.getNombre().equals(c2.getNombre())) {
        c = c2; // Reemplazo los valores nuevos
      }
    }
  }

  /**
   * Contador para las posiciones de los equipos.
   */
  private int pos = 1;

  /**
   * 
   * @return retorna una lista de clubes ordenada por puntuación.
   */
  private List<Club> ordenarPorPuntos() {
    List<Club> list = new ArrayList<Club>(clubs);
    Collections.sort(list, new Comparator<Club>() {
      @Override
      public int compare(Club o1, Club o2) {
        return o2.getPuntuacion() - o1.getPuntuacion();
      }
    });
    list.forEach(c -> c.setPosicion(pos++));
    return list;
  }

  /**
   * 
   * @param nom nombre del club a buscar.
   * @return retorna un Club a partir de un nombre.
   */
  private Club findByNombre(String nom) {
    for (Club c : clubs) {
      // clubTemp = c.getNombre().equals(nom) ? clubTemp = c : (clubTemp = null);
      if (c.getNombre().equals(nom)) {
        clubTemp = c;
      }
    }
    return clubTemp;
  }
}