package net.erickpineda.generadorequipos.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import net.erickpineda.generadorequipos.models.Profesor;

public class CrearXML {
  /**
   * Create Factory.
   */
  private XMLOutputFactory xof;
  /**
   * Escribe en el fichero XML.
   */
  private XMLStreamWriter escritorXML;
  private Set<Profesor> list;
  private String ruta;

  public CrearXML(final String rutaFichero, final Set<Profesor> lista) {
    setRuta(rutaFichero);
    setSet(lista);
  }

  public CrearXML() {
  }

  public void crearInstancia() {
    try {
      xof = XMLOutputFactory.newInstance();
      escritorXML = xof.createXMLStreamWriter(new FileWriter(ruta));
    } catch (XMLStreamException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void crearFicheroXML() {
    try {
      // "<?xml version='1.0' encoding='UTF-8'?>"
      escritorXML.writeStartDocument("UTF-8", "1.0");

      // Crea la etiqueta professors
      escritorXML.writeStartElement("professors");

      for (Profesor p : list) {
        // Crea la etiqueta professor
        escritorXML.writeStartElement("professor");

        // Crea la etiqueta nom
        escritorXML.writeStartElement("nom");
        escritorXML.writeCharacters(String.valueOf(p.getNombre()));
        escritorXML.writeEndElement();

        // Crea la etiqueta cognom
        escritorXML.writeStartElement("cognom");
        escritorXML.writeCharacters(p.getApellido());
        escritorXML.writeEndElement();

        // Crea la etiqueta sexe
        escritorXML.writeStartElement("sexe");
        escritorXML.writeCharacters(p.getSexo());
        escritorXML.writeEndElement();

        // Crea la etiqueta edat
        escritorXML.writeStartElement("edat");
        escritorXML.writeCharacters(String.valueOf(p.getEdad()));
        escritorXML.writeEndElement();

        // Cierra la etiqueta professor
        escritorXML.writeEndElement();
      }

      // Cierra la etiqueta professors
      escritorXML.writeEndElement();

      escritorXML.writeEndDocument();
      escritorXML.flush();
      escritorXML.close();

    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
  }

  public void setRuta(final String ruta) {
    this.ruta = ruta;
  }

  public void setSet(final Set<Profesor> set) {
    this.list = set;
  }
}
