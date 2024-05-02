package Exo2et3;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

@RunWith(MockitoJUnitRunner.class)
public class UserSetviceTest3 {
    @Test
    public void testCreerUtilisateur() throws ServiceException {
        UtilisateurApi utilisateurApiMock = mock(UtilisateurApi.class);
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        // Lever une exception lors de la création de l'utilisateur avec l’exception
        //ServiceException("Echec de la création de l'utilisateur").
        doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(utilisateur);

        //  Tester le comportement en cas d'erreur de validation
        UserService userService = new UserService(utilisateurApiMock);
        Assertions.assertThrows(ServiceException.class, ()->userService.creerUtilisateur(utilisateur));
        //reset le comportement de l'objet mocké
        reset(utilisateurApiMock);
    }

    @Test
    public void testCreerUtilisateurNoValidation() throws ServiceException{
        UtilisateurApi utilisateurApiMock = mock(UtilisateurApi.class);
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        // mocker l'objet
        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(-1);
        // création d'un user service
        UserService us = new UserService(utilisateurApiMock);
        // vérifier que l'utilisateur n'a pas été crée
        Assertions.assertEquals(-1, us.creerUtilisateur(utilisateur));

    }

    @Test
    public void testId() throws ServiceException{
        UtilisateurApi utilisateurApiMock = mock(UtilisateurApi.class);
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        UserService userService = new UserService(utilisateurApiMock);
        // Définition d'un ID fictif

        int id = 2001;

        //  Configuration du mock pour renvoyer l'ID
        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(id);
        // Appel de la méthode à tester
        int Id = userService.creerUtilisateur(utilisateur);
        //  Vérification de l'ID de l'utilisateur
        Assertions.assertEquals(id,Id);


    }

    @Test
    public void testArgumentCaptor() throws ServiceException{
        UtilisateurApi utilisateurApiMock = mock(UtilisateurApi.class);

        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        UserService userService = new UserService(utilisateurApiMock);

        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(1);

        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        userService.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());

        Utilisateur user = argumentCaptor.getValue();
        Assertions.assertEquals(utilisateur,user);

    }
}
