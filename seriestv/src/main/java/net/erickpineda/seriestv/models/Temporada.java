package net.erickpineda.seriestv.models;

import java.util.List;

public class Temporada {
  private String nombre;
  private int numero;
  private List<Capitulo> capitulos;
  private int cantidadCapitulos;
  private int cantidadEsp;
  private int cantidadIng;
  private int cantidadSub;

  public Temporada(final String nom, final int num) {
    this.setNombre(nom);
    this.setNumero(num);
  }

  public Temporada(final String nom, final int num, final List<Capitulo> caps) {
    this.setNombre(nom);
    this.setNumero(num);
    this.setCapitulos(caps);
    this.setCantidadCapitulos(caps.size());
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nom) {
    this.nombre = nom;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public List<Capitulo> getCapitulos() {
    return capitulos;
  }

  public void setCapitulos(List<Capitulo> capitulos) {
    this.capitulos = capitulos;
  }

  public int getCantidadCapitulos() {
    return cantidadCapitulos;
  }

  public void setCantidadCapitulos(int cantidadCapitulos) {
    this.cantidadCapitulos = cantidadCapitulos;
  }

  public void sumarEsp(final int n) {
    this.cantidadEsp += n;
  }

  public int getCantidadEsp() {
    return cantidadEsp;
  }

  public void setCantidadEsp(int cantidadEsp) {
    this.cantidadEsp = cantidadEsp;
  }

  public void sumarIng(final int n) {
    this.cantidadIng += n;
  }

  public int getCantidadIng() {
    return cantidadIng;
  }

  public void setCantidadIng(int cantidadIng) {
    this.cantidadIng = cantidadIng;
  }

  public void sumarSub(final int n) {
    this.cantidadSub += n;
  }

  public int getCantidadSub() {
    return cantidadSub;
  }

  public void setCantidadSub(int cantidadSub) {
    this.cantidadSub = cantidadSub;
  }

  @Override
  public String toString() {
    return "Temporada [getNombre()=" + getNombre() + ", getNumero()=" + getNumero()
        + ", getCapitulos()=" + getCapitulos() + ", getCantidadCapitulos()="
        + getCantidadCapitulos() + ", getCantidadEsp()=" + getCantidadEsp() + ", getCantidadIng()="
        + getCantidadIng() + ", getCantidadSub()=" + getCantidadSub() + "]";
  }

}
