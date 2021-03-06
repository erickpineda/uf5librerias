package net.erickpineda.staxgoleadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Procesar {
  /**
   * Parser que procesará el fichero XML.
   */
  private XMLStreamReader parser;
  private String ATTR_POSICIO = "posicio";
  private String TAG_JUGADOR = "jugador";
  private String TAG_NOM = "nom";
  private String TAG_GOLS = "gols";
  private String TAG_PARTITS = "partits";
  private String TAG_MITJANA = "mitjana";
  private String TAG_EQUIPS = "equips";
  private String TAG_EQUIP = "equip";

  private boolean ATTR_POSICIO_OK = false;
  private boolean TAG_JUGADOR_OK = false;
  private boolean TAG_NOM_OK = false;
  private boolean TAG_GOLS_OK = false;
  private boolean TAG_PARTITS_OK = false;
  private boolean TAG_MITJANA_OK = false;
  private boolean TAG_EQUIPS_OK = false;
  private boolean TAG_EQUIP_OK = false;

  private Map<String, Jugador> mapa;
  private Set<Jugador> jugadores;
  private Set<Club> clubes;
  private Club club;
  private Jugador jugador;

  public Procesar(final XMLStreamReader parser) {
    this.parser = parser;
    this.jugadores = new LinkedHashSet<Jugador>();
    this.clubes = new HashSet<Club>();
    this.mapa = new HashMap<>();
  }

  public Procesar procesarXML() {
    try {
      while (parser.hasNext()) {
        parser.next();
        startElement();
        endElement();
        endDocument();
      }
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    return this;
  }

  private void startElement() throws XMLStreamException {
    if (parser.getEventType() == XMLStreamReader.START_ELEMENT) {
      abrirEtiquetas(true);
    }
  }

  private void endElement() throws XMLStreamException {
    if (parser.getEventType() == XMLStreamReader.END_ELEMENT) {
      abrirEtiquetas(false);
    }
  }

  private void abrirEtiquetas(final boolean valor) throws XMLStreamException {
    if (parser.getLocalName().equalsIgnoreCase(TAG_JUGADOR)) {
      TAG_JUGADOR_OK = valor;
      // if (!TAG_PARTITS_OK) {
      //
      // }
      if (TAG_JUGADOR_OK) {
        this.jugador = new Jugador();
        String pos = parser.getAttributeValue(0);
        this.jugador.setPosicion(Integer.parseInt(pos));
      }
    }
    if (parser.getLocalName().equalsIgnoreCase(TAG_NOM)) {
      TAG_NOM_OK = valor;
      if (TAG_NOM_OK) {
        recolectarInformacion(parser.getElementText());
      }
    }
    if (parser.getLocalName().equalsIgnoreCase(TAG_GOLS)) {
      TAG_GOLS_OK = valor;
      if (TAG_GOLS_OK) {
        recolectarInformacion(parser.getElementText());
      }
    }
    if (parser.getLocalName().equalsIgnoreCase(TAG_PARTITS)) {
      TAG_PARTITS_OK = valor;
      if (TAG_PARTITS_OK) {
        recolectarInformacion(parser.getElementText());
      }
    }
    if (parser.getLocalName().equalsIgnoreCase(TAG_MITJANA)) {
      TAG_MITJANA_OK = valor;
      if (TAG_MITJANA_OK) {
        recolectarInformacion(parser.getElementText());
      }
    }
    if (parser.getLocalName().equalsIgnoreCase(TAG_EQUIP)) {
      TAG_EQUIP_OK = valor;
      if (TAG_EQUIP_OK) {
        this.club = new Club();
      }
      if (!TAG_EQUIP_OK) {
        this.jugador.addClub(club);
        this.club.addPlayer(jugador);
        this.clubes.add(club);
      }
    }
    if (parser.getLocalName().equalsIgnoreCase(TAG_EQUIPS)) {
      TAG_EQUIPS_OK = valor;
      if (!TAG_EQUIPS_OK && TAG_JUGADOR_OK) {
        this.jugadores.add(jugador);
      }
    }
  }

  private void recolectarInformacion(final String data) {
    if (TAG_NOM_OK && TAG_JUGADOR_OK && (!TAG_EQUIP_OK)) {
      this.jugador.setNombre(data);
    }
    if (TAG_JUGADOR_OK && TAG_GOLS_OK) {
      this.jugador.setGoles(new Integer(data));
    }
    if (TAG_PARTITS_OK) {
      this.jugador.setPartidos(Integer.parseInt(data));
    }
    if (TAG_MITJANA_OK) {
      this.jugador.setMediana(new Double(data));
    }
    if (TAG_EQUIP_OK && TAG_NOM_OK) {
      this.club.setNombre(data);
      if (!mapa.containsKey(data)) {
        mapa.put(data, jugador);
      }
    }
    if (TAG_EQUIP_OK && TAG_GOLS_OK) {
      this.club.setGoles(new Integer(data));
    }
  }

  private void endDocument() {
    if (parser.getEventType() == XMLStreamReader.END_DOCUMENT) {
      pintarJugadores(ordenado(jugadores));
    }
  }

  public Set<Jugador> ordenado(Set<Jugador> jugadores) {
    List<Jugador> lista = new ArrayList<Jugador>(jugadores);
    Collections.sort(lista, new Comparator<Jugador>() {
      @Override
      public int compare(Jugador j1, Jugador j2) {
        return j2.getGoles() - j1.getGoles();
      }
    });
    return new LinkedHashSet<Jugador>(lista);
  }
  
  public Set<Jugador> ordenado2(Set<Jugador> jugadores) {
    List<Jugador> lista = new ArrayList<Jugador>();
    Collections.sort(lista, new Comparator<Jugador>() {
      @Override
      public int compare(Jugador j1, Jugador j2) {
        return j2.getGoles() - j1.getGoles();
      }
    });
    return new LinkedHashSet<Jugador>(lista);
  }
  
  public void pintarJugadores(Set<Jugador> list) {
  //mapa.values().forEach(v->System.out.println(v));
    
    //for (Club c: clubes){
        //System.out.println(c.getNombre());
    //}
    //clubes.forEach(c -> System.out.println(c.getNombre() +" "+c.getJugadores()));
    //jugadores.forEach(j -> System.out.println(j + " " + j.getEquipos()));
    list.forEach(j -> System.out.println(j.getEquipos() + "\n" + j.nombreYGoles()+"\n"));
    //clubes.forEach(c-> System.out.println(c.getNombre()+ " <-"));
  }
}