package net.erickpineda.webscrapping;

public class Color {
    private String nom;
    private String colorhex;
    private int red;
    private int green;
    private int blue;
    public Color(){
        
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getColorhex() {
        return colorhex;
    }
    public void setColorhex(String colorhex) {
        this.colorhex = colorhex;
    }
    public int getRed() {
        return red;
    }
    public void setRed(int red) {
        this.red = red;
    }
    public int getGreen() {
        return green;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public int getBlue() {
        return blue;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
    @Override
    public String toString() {
        return "Color [getNom()=" + getNom() + ", getColorhex()=" + getColorhex() + ", getRed()="
                + getRed() + ", getGreen()=" + getGreen() + ", getBlue()=" + getBlue() + "]";
    }
}
