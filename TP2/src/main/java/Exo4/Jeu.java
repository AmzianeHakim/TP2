package Exo4;

public class Jeu {
    private final Banque banque;
    private boolean ferme;
    private boolean arrete;
    public Jeu(Banque labanque){
        this.banque = labanque;
        ferme = !banque.est_solvable();
        arrete = false;
    }
    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!ferme) {
            // récupérer la mise de joueur
            int mis = joueur.mise();
            // débiter de compte de joueur et créditer la banque avec la somme mise
            try {
                joueur.debiter(mis);
            } catch (DebitImpossibleException e) {
                throw new JeuFermeException("Le joueur n'a pas assez de crédit");
            }
            banque.crediter(mis);
            // lancer les dés
            int pari1 = de1.lancer();
            int pari2 = de2.lancer();
            //vérifier si égale à 7
            if (pari1 + pari2 == 7) {
                //débiter la banque avec deux fois la somme mise
                banque.debiter(mis * 2);
                // rajouter le montant au compte de joueur
                joueur.crediter(mis * 2);
                // vérifier si la banque est toujours solvable
                if (!banque.est_solvable()) {
                    // fermer  le jeu si la banque n'est pas solvable
                    this.fermer();
                }
             /*
             le jeu contiue sinon
              */
            } else {
                /*
                le jeu s'arrête s'il a perdu
               */
                arrete = true;
            }
        }
    }

    public void fermer () {
        this.ferme = true;
    }
    ;
    public boolean estOuvert () {
        return !ferme;
    }
    public boolean estArrete (){
        return arrete;
    }
}
