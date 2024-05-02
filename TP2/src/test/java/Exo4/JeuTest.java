package Exo4;

import Exo2et3.UtilisateurApi;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class JeuTest {

    @Test
    public void jeuSeFermeSiLaBanqueEstInsolvable(){
        Banque banqueMock = mock(Banque.class);

        when(banqueMock.est_solvable()).thenReturn(false);
        Jeu jeu = new Jeu(banqueMock);
        Assertions.assertFalse(jeu.estOuvert());

    }
    @Test
    public void jeuThrowsJeuFermeExceptionIfJoueurEstNonSolvable() throws DebitImpossibleException,JeuFermeException{
        Banque banqueMock = mock(Banque.class);
        Joueur joueurMock = mock(Joueur.class);
        De de1Mock = mock(De.class);
        De de2Mock = mock(De.class);


        int somme = 100;
        when(banqueMock.est_solvable()).thenReturn(true);
        doThrow(new DebitImpossibleException("le joueur n'a pas assez de crédit")).when(joueurMock).debiter(somme);
        when(joueurMock.mise()).thenReturn(somme);
        Jeu jeu = new Jeu(banqueMock);
        Assertions.assertThrows(JeuFermeException.class, ()->jeu.jouer(joueurMock,de1Mock,de2Mock));
        verify(de1Mock, never()).lancer();
        verify(de2Mock, never()).lancer();

    }

    @Test
    public void jeuArreteApresQueLeJoueurPerd() throws DebitImpossibleException,JeuFermeException{
        Banque banqueMock = mock(Banque.class);
        Joueur joueurMock = mock(Joueur.class);
        De de1Mock = mock(De.class);
        De de2Mock = mock(De.class);

        int somme = 100;
        when(banqueMock.est_solvable()).thenReturn(true);
        when(de1Mock.lancer()).thenReturn(1);
        when(de2Mock.lancer()).thenReturn(2);
        doNothing().when(banqueMock).crediter(somme);
        when(joueurMock.mise()).thenReturn(somme);
        doNothing().when(joueurMock).debiter(somme);

        Jeu jeu = new Jeu(banqueMock);
        jeu.jouer(joueurMock,de1Mock,de1Mock);
        Assertions.assertTrue(jeu.estArrete());

    }

    @Test
    public void jeuContinueApresQueLeJoueurGagneEtLaBanqueEstSolvable() throws DebitImpossibleException,JeuFermeException{
        Banque banqueMock = mock(Banque.class);
        Joueur joueurMock = mock(Joueur.class);
        De de1Mock = mock(De.class);
        De de2Mock = mock(De.class);

        int somme = 100;
        when(banqueMock.est_solvable()).thenReturn(true);
        when(de1Mock.lancer()).thenReturn(5);
        when(de2Mock.lancer()).thenReturn(2);
        doNothing().when(banqueMock).crediter(somme);
        doNothing().when(banqueMock).debiter(2 * somme);
        when(joueurMock.mise()).thenReturn(somme);
        doNothing().when(joueurMock).debiter(somme);
        doNothing().when(joueurMock).crediter(2 * somme);

        Jeu jeu = new Jeu(banqueMock);
        jeu.jouer(joueurMock,de1Mock,de1Mock);
        Assertions.assertTrue(jeu.estOuvert());

    }
}