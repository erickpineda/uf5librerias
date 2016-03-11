package net.erickpineda.thecolorsleague;

public class Club {
  private String nombre;
  private String presidente;
  private int ganados;
  private int perdidos;
  private int empatados;
  private int golesMarcados;
  private int golesRecibidos;
  private int posicion;
  private int gmTemp;

  public Club() {

  }

  public Club(final String nombre) {
    this.setNombre(nombre);
  }

  /**
   * Método que suma la cantidad de victorias de un Club.
   */
  public void sumarVictoria() {
    this.ganados += 1;
  }

  /**
   * Método que suma la cantidad de derrotas de un Club.
   */
  public void sumarDerrota() {
    this.perdidos += 1;
  }

  /**
   * Método que suma la cantidad de empates de un Club.
   */
  public void sumarEmpate() {
    this.empatados += 1;
  }

  /**
   * Método que suma una cantidad de goles a favor.
   * 
   * @param cant cantidad de goles a sumar.
   */
  public void sumarGolesAFavor(int cant) {
    this.golesMarcados += cant;
  }

  /**
   * Método que suma una cantidad de goles en contra.
   * 
   * @param cant cantidad de goles en contra a sumar.
   */
  public void sumarGolesEnContra(int cant) {
    this.golesRecibidos += cant;
  }

  /**
   * 
   * @return retorna la puntuación total del club.
   */
  public int getPuntuacion() {
    return (ganados * 3) + empatados;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPresidente() {
    return presidente;
  }

  public void setPresidente(String presidente) {
    this.presidente = presidente;
  }

  public int getVictorias() {
    return ganados;
  }

  public void setVictorias(int ganados) {
    this.ganados = ganados;
  }

  public int getDerrotas() {
    return perdidos;
  }

  public void setDerrotas(int perdidos) {
    this.perdidos = perdidos;
  }

  public int getEmpates() {
    return empatados;
  }

  public void setEmpates(int empatados) {
    this.empatados = empatados;
  }

  public int getGolesMarcados() {
    return golesMarcados;
  }

  public void setGolesMarcados(int golesMarcados) {
    this.golesMarcados = golesMarcados;
  }

  public int getGolesRecibidos() {
    return golesRecibidos;
  }

  public void setGolesRecibidos(int golesRecibidos) {
    this.golesRecibidos = golesRecibidos;
  }

  public int getPosicion() {
    return posicion;
  }

  public void setPosicion(int posicion) {
    this.posicion = posicion;
  }

  /**
   * 
   * @return retorna los <b>Goles Marcados</b> de un Club en un partido jugado.
   */
  public int getGMTemp() {
    return gmTemp;
  }

  /**
   * 
   * @param gmTemp cantidad de <b>Goles Marcados</b> por el Club en un partido jugado.
   */
  public void setGMTemp(int gmTemp) {
    this.gmTemp = gmTemp;
  }

  @Override
  public String toString() {
    return this.getPresidente() + "\t   " + this.getNombre() + "\t" + this.getPosicion() + "\t"
        + this.getVictorias() + "\t" + this.getEmpates() + "\t" + this.getDerrotas() + "\t"
        + this.golesMarcados + "\t" + this.golesRecibidos + "\t" + this.getPuntuacion();
  }
}
