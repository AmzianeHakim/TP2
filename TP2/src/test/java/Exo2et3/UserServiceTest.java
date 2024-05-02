package Exo2et3;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Test
    public void testCreerUtilisateur() throws ServiceException {
        UtilisateurApi utilisateurApiMock = mock(UtilisateurApi.class);
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        // Configuration du comportement du mock, utiliser la
        //directive « when » avec sa méthode « thenReturn »

        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(1);
        //  Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        //  Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        //  Vérification de l'appel à l'API
        verify(utilisateurApiMock, times(1)).creerUtilisateur(utilisateur);
    }
}