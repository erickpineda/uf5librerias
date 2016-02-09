package net.erickpineda.sumamental;

public class Operacion {
    private int n1;
    private int n2;
    private String operador;

    public Operacion(final int n1, final int n2) {
        this.setN1(n1);
        this.setN2(n2);
    }

    private int multiplicar() {
        return n1 * n2;
    }

    private int sumar() {
        return n1 + n2;
    }

    private int restar() {
        return n1 - n2;
    }

    public String getOperacion() {
        int rand = Util.rand(0, 2);

        if (rand == 0) {
            operador = "+";
        }
        if (rand == 1) {
            operador = "-";
        }
        if (rand == 2) {
            operador = "*";
        }
        return new String(getN1() + " " + operador + " " + getN2());
    }

    public boolean esCorrecto(final String s) {
        int r = Integer.parseInt(s);

        if (operador.equals("+")) {
            return (r == sumar());
        }
        if (operador.equals("-")) {
            return (r == restar());
        } else {
            return (r == multiplicar());
        }
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public String getOperador() {
        return operador;
    }
}
