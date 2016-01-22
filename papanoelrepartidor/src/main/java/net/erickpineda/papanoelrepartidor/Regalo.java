package net.erickpineda.papanoelrepartidor;

public class Regalo {
    private String nombre;
    private int cantidad;

    public Regalo(final String nombre) {
        this.nombre = nombre;
    }

    public Regalo(final String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void sumarRegalo() {
        this.cantidad += 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Regalo))
            return false;
        if (obj == this)
            return true;
        return this.nombre.equals(((Regalo) obj).nombre);
    }

    @Override
    public int hashCode() {
        return nombre.length();
    }

    @Override
    public String toString() {
        return "Regalo [getNombre()=" + getNombre() + ", getCantidad()=" + getCantidad() + "]";
    }
}
