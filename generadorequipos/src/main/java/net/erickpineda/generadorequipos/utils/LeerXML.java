package net.erickpineda.generadorequipos.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import net.erickpineda.generadorequipos.models.Profesor;

public class LeerXML {
  /**
   * Parser que procesará el fichero XML.
   */
  private XMLStreamReader parser;
  private Set<Profesor> profesores;
  private File ficheroXML;
  private Profesor profe;

  public LeerXML(final File f) {
    this.ficheroXML = f;
    this.profesores = new LinkedHashSet<Profesor>();
  }

  public LeerXML crearInstancia() {
    try {
      InputStream entrada = ficheroXML.toURI().toURL().openStream();
      parser = XMLInputFactory.newInstance().createXMLStreamReader(entrada);

    } catch (XMLStreamException | FactoryConfigurationError | IOException e) {
      e.printStackTrace();
    }
    return this;
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
