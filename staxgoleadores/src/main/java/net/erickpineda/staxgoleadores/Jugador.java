package net.erickpineda.staxgoleadores;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
  private String nombre;
  private int goles;
  private int posicion;
  private int partidos;
  private double mediana;
  private List<Club> equipos;

  public Jugador() {
    this.equipos = new ArrayList<Club>();
  }

  public Jugador(final String nombre) {
    this.equipos = new ArrayList<Club>();
    this.nombre = nombre;
  }

  public void addClub(final Club c) {
    this.equipos.add(c);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getGoles() {
    return goles;
  }

  public void setGoles(int goles) {
    this.goles = goles;
  }

  public int getPosicion() {
    return posicion;
  }

  public void setPosicion(int posicion) {
    this.posicion = posicion;
  }

  public int getPartidos() {
    return partidos;
  }

  public void setPartidos(int partidos) {
    this.partidos = partidos;
  }

  public double getMediana() {
    return mediana;
  }

  public void setMediana(double mediana) {
    this.mediana = mediana;
  }

  public List<Club> getEquipos() {
    return equipos;
  }

  public boolean haEstadoEnClub(Club club) {
    getEquipos().forEach(c -> {
      if (c.equals(club))
        return;
    });
    return false;
  }

  public void setEquipos(List<Club> equipos) {
    this.equipos = equipos;
  }

  public String nombreYGoles() {
    return "" + getNombre() + " " + getGoles();
  }

  public void pintarResultado() {
    for (Club c : equipos) {
      System.out.println(c.getNombre() + ": " + c.getGoles() + " goles");
      for (Jugador j : c.getJugadores()) {
        System.out.println(j.getNombre() + ": " + j.getGoles() + " goles");
      }
      System.out.println();
    }
  }

  @Override
  public String toString() {
    return "Jugador [getNombre()=" + getNombre() + ", getGoles()=" + getGoles() + ", getPosicion()="
        + getPosicion() + ", getPartidos()=" + getPartidos() + ", getMediana()=" + getMediana()
        + "]";
  }
}
