package net.erickpineda.subvencionesdenavidad;

public class Personaje {
    private String nombre;
    private int cantidadRegalos;
    private double porcentaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void sumarRegalo() {
        this.cantidadRegalos++;
    }

    public int getCantidadRegalos() {
        return cantidadRegalos;
    }

    public void setCantidadRegalos(int cantidadRegalos) {
        this.cantidadRegalos = cantidadRegalos;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
