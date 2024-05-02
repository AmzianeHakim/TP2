package Exo1;

public class Calculatrice {
    public static final int Etat1=1;
    public static final int Etat0=0;
    private int result ;
    public int additionner(int a, int b) {
        result = a + b ;
        return result;
    }


    public int getEtat() {
        return this.Etat1;
    }
}
