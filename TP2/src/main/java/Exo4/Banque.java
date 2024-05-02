package Exo4;

public interface Banque {
    public void crediter(int somme);
    public boolean est_solvable();
    public void debiter(int somme);
}
