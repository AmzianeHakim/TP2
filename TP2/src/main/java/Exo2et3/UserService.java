package Exo2et3;

public class UserService {


    private final UtilisateurApi utilisateurApi;
    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }
    public int creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        return utilisateurApi.creerUtilisateur(utilisateur);

    }
}
