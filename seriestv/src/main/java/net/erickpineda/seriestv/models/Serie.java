package net.erickpineda.seriestv.models;

import java.util.List;

import javafx.scene.image.Image;

public class Serie {
  private String nombre;
  private List<Temporada> temporadas;
  private int totalCapitulos;
  private Image imagen;

  public Serie() {

  }

  public Serie(final String nom) {
    setNombre(nom);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<Temporada> getTemporadas() {
    return temporadas;
  }

  public void setTemporadas(List<Temporada> temporadas) {
    this.temporadas = temporadas;
  }

  public Image getImagen() {
    return imagen;
  }

  public void setImagen(Image imagen) {
    this.imagen = imagen;
  }

  public int getTotalCapitulos() {
    return totalCapitulos;
  }

  public void setTotalCapitulos(int totalCapitulos) {
    this.totalCapitulos = totalCapitulos;
  }

  @Override
  public String toString() {
    return "Serie [getNombre()=" + getNombre() + ", getTemporadas()=" + getTemporadas()
        + ", getImagen()=" + getImagen() + ", getTotalCapitulos()=" + getTotalCapitulos() + "]";
  }
}
