package net.erickpineda.papanoelrepartidor;

import java.util.HashSet;
import java.util.Set;

public class Persona {
  private String nombre;
  private Set<Regalo> regalos = new HashSet<Regalo>();

  public Persona(final String nom) {
    this.nombre = nom;
  }

  public Persona(final String nom, final Set<Regalo> regalos) {
    this.setNombre(nom);
    this.setRegalos(regalos);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Set<Regalo> getRegalos() {
    return regalos;
  }

  public void setRegalos(Set<Regalo> regalos) {
    this.regalos = regalos;
  }

  @Override
  public String toString() {
    return "Persona [getNombre()=" + getNombre() + ", getRegalos()=" + getRegalos() + "]";
  }
}
