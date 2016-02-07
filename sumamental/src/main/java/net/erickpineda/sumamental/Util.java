package net.erickpineda.sumamental;

public class Util {
    private Util() {

    }

    public static int rand(int desde, int hasta) {
        return (int) (Math.random() * (hasta - desde + 1) + desde);
    }
}
