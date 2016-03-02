package net.erickpineda.generadorequipos.models;

public class Profesor {
  private String nombre;
  private String apellido;
  private String sexo;
  private int edad;

  public Profesor() {

  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getNombreCompleto() {
    return String.format("%s %s", getNombre(), getApellido());
  }

  @Override
  public String toString() {
    return "Profesor [getNombre()=" + getNombre() + ", getApellido()=" + getApellido()
        + ", getSexo()=" + getSexo() + ", getEdad()=" + getEdad() + "]";
  }
}
