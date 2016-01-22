package net.erickpineda.staxgoleadores;

import java.util.HashSet;
import java.util.Set;

public class Club {
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
        this.jugadores.add(j);
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

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Club [getNombre()=" + getNombre() + ", getGoles()=" + getGoles()
                + ", getJugadores()=" + getJugadores() + "]";
    }
}
