package net.erickpineda.seriestv.models;

public class Capitulo {
  private String nombre;
  private int numero;
  private int esp;
  private int sub;
  private int ing;
  private int cantidadIdiomas;

  public Capitulo() {

  }

  public Capitulo(final String nom, final int num) {
    setNombre(nom);
    setNumero(num);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public void sumarEsp() {
    this.esp++;
  }

  public int getEsp() {
    return esp;
  }

  public void setEsp(int esp) {
    this.esp = esp;
  }

  public void sumarSub() {
    this.sub++;
  }

  public int getSub() {
    return sub;
  }

  public void setSub(int sub) {
    this.sub = sub;
  }

  public void sumarIng() {
    this.ing++;
  }

  public int getIng() {
    return ing;
  }

  public void setIng(int ing) {
    this.ing = ing;
  }

  public int getCantidadIdiomas() {
    return cantidadIdiomas;
  }

  public void setCantidadIdiomas(int cantidadIdiomas) {
    this.cantidadIdiomas = cantidadIdiomas;
  }

  @Override
  public String toString() {
    return "Capitulo [getNombre()=" + getNombre() + ", getNumero()=" + getNumero() + ", getEsp()="
        + getEsp() + ", getSub()=" + getSub() + ", getIng()=" + getIng() + ", getCantidadIdiomas()="
        + getCantidadIdiomas() + "]";
  }
}
