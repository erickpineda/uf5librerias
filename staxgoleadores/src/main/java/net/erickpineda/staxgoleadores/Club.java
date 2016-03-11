package net.erickpineda.staxgoleadores;

import java.util.HashSet;
import java.util.Set;

public class Club implements Comparable<Club> {
  private String nombre;
  private int goles;
  private Set<Jugador> jugadores;

  public Club() {
    this.jugadores = new HashSet<Jugador>();
  }

  public Club(final String nombre) {
    this.jugadores = new HashSet<Jugador>();
    this.setNombre(nombre);
  }

  public Club(final String nombre, final int goles) {
    this.jugadores = new HashSet<Jugador>();
    this.setNombre(nombre);
    this.setGoles(goles);
  }

  public void addPlayer(final Jugador j) {
    for (Jugador j2 : jugadores) {
      if (!j2.getNombre().equals(j.getNombre())) {
        jugadores.add(j);
      }
    }
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void sumarTodosLosGoles() {
    getJugadores().forEach(j -> goles += j.getGoles());
  }

  public int getGoles() {
    return goles;
  }

  public void setGoles(int goles) {
    this.goles = goles;
  }

  public Set<Jugador> getJugadores() {
    return jugadores;
  }

  public void setJugadores(Set<Jugador> jugadores) {
    this.jugadores = jugadores;
  }

  public void pintarJugadores() {
    for (Jugador j : jugadores) {
      System.out.println(j.getNombre());
    }
  }

  @Override
  public String toString() {
    return "Club: " + getNombre();
  }

  @Override
  public int compareTo(Club c) {
    if (getGoles() > c.getGoles()) {
      return -1;
    } else if (getGoles() < c.getGoles()) {
      return 1;
    }
    return 0;
  }
}
