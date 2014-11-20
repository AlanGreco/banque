package beans;

import javax.ejb.Remote;

import entities.Compte;

@Remote
public interface GestionCompteRemote {
public Compte ajouterCompte (Compte compte);
public void supprimerCompte (Compte compte);
}
