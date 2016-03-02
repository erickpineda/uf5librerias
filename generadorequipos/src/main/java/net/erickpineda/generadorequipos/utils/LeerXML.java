package net.erickpineda.generadorequipos.utils;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import net.erickpineda.generadorequipos.models.Profesor;

public class LeerXML {
  /**
   * Parser que procesará el fichero XML.
   */
  private XMLStreamReader parser;
  private Set<Profesor> profesores;
  private Profesor profe;

  public LeerXML(final XMLStreamReader parser) {
    this.parser = parser;
    this.profesores = new LinkedHashSet<Profesor>();
  }

  public void procesarXML() {
    try {
      while (parser.hasNext()) {
        parser.next();
        startElement();
        endElement();
      }
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
  }

  public void startElement() throws XMLStreamException {
    if (parser.getEventType() == XMLStreamReader.START_ELEMENT) {
      abrirEtiquetas(true);
    }
  }

  public void endElement() throws XMLStreamException {
    if (parser.getEventType() == XMLStreamReader.END_ELEMENT) {
      abrirEtiquetas(false);
    }
  }

  private void abrirEtiquetas(final boolean abrir) throws XMLStreamException {
    if (abrir) {
      switch (parser.getLocalName()) {
      case "professor":
        profe = new Profesor();
        break;
      case "nom":
        profe.setNombre(parser.getElementText());
        break;
      case "cognom":
        profe.setApellido(parser.getElementText());
        break;
      case "sexe":
        profe.setSexo(parser.getElementText());
        break;
      case "edat":
        profe.setEdad(new Integer(parser.getElementText()).intValue());
        break;
      default:
        break;
      }
    }
    if (!abrir && parser.getLocalName().equalsIgnoreCase("professor")) {
      agregarALista(profe);
    }
  }

  private void agregarALista(final Profesor profesor) {
    if (profesor != null) {
      profesores.add(profesor);
    }
  }

  public Set<Profesor> getProfesores() {
    return profesores;
  }
}
