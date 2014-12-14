package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;

import entities.Compte;
import entities.CompteEpargne;
import entities.ComptePlatine;
import entities.CompteStandard;
import entities.Mouvement;

/**
 * Session Bean implementation class GestionCompte
 */
/**
 * @author leocorone
 *
 */
/**
 * @author leocorone
 *
 */
/**
 * @author leocorone
 *
 */
@Stateless
public class GestionCompte implements GestionCompteRemote, GestionCompteLocal {

	@EJB(name = "GestionHistoriqueRemote")
	// @EJB(lookup="java:global/BanqueEar/BanqueBeans/GestionHistorique!beans.GestionHistoriqueRemote")
	GestionHistorique gestionHistorique;

	private static final Logger log = Logger.getLogger(GestionCompte.class.getName());

	/**
	 * Default constructor.
	 */
	public GestionCompte() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager em;

	@Override
	public Compte ajouterCompte(Compte compte) {
		em.persist(compte);
		return compte;
	}

	@Override
	public void supprimerCompte(Compte compte) {
		compte = em.find(Compte.class, compte.getId());
		em.remove(compte);
	}

	@Override
	public void supprimerCompte(int idCompte) {
		Compte compte = em.find(Compte.class, idCompte);
		em.remove(compte);
	}

	@Override
	public ArrayList<Compte> recupererCompteClient(int id) {
		// TODO Auto-generated method stub
		String request = "Select c from Compte c Where client_id = '" + id + "'";
		@SuppressWarnings("unchecked")
		ArrayList<Compte> listCompte = (ArrayList<Compte>) em.createQuery(request).getResultList();
		return listCompte;
	}

	@Override
	public Compte getCompteById(int idCompte) {
		Compte compte;
		compte = em.find(Compte.class, idCompte);
		compte.getHistoriqueMouvements().size();
		return compte;
	}

	// Méthode prenant en charge une opération sur un compte
	@Override
	public boolean modifierSolde(int idCompte, double montant) {
		Compte compte = getCompteById(idCompte);
		// List<Mouvement> histo = compte.getHistoriqueMouvements();
		Double nouveauSolde = (double) compte.getSolde() + montant;

		if (nouveauSolde >= 0 || montant >= 0) {
			if (compte instanceof CompteEpargne) {
				calculInteretEpargne(montant, (CompteEpargne) compte);
			}
			compte.setSolde(nouveauSolde);
			// compte.setHistoriqueMouvements(histo);
			gestionHistorique.ajouterMouvement(montant, compte, "Crédit");
		} else {

			if (compte instanceof CompteStandard) {
				Double nouveauSoldePenalise = (double) nouveauSolde - ((CompteStandard) compte).getPenalite();
				compte.setSolde(nouveauSoldePenalise);
				// compte.setHistoriqueMouvements(histo);
				gestionHistorique.ajouterMouvement(-((CompteStandard) compte).getPenalite(), compte, "Pénalité");
			} else if (compte instanceof ComptePlatine) {

				if (nouveauSolde >= -((ComptePlatine) compte).getDecouvertAutorise()) {
					compte.setSolde(nouveauSolde);
					// compte.setHistoriqueMouvements(histo);
				} else {
					Double nouveauSoldePenalise = nouveauSolde - ((ComptePlatine) compte).getPenalite();
					compte.setSolde(nouveauSoldePenalise);
					// compte.setHistoriqueMouvements(histo);
					gestionHistorique.ajouterMouvement(-((ComptePlatine) compte).getPenalite(), compte, "Pénalité");
				}

			} else {
				return false;
			}
			gestionHistorique.ajouterMouvement(montant, compte, "Débit");

		}
		em.merge(compte);
		return true;
	}

	// Méthode permettant de calculer les interets sur un compte épargne. Cette
	// méthode est appellée à chaque
	// fois qu'une opération est effectuée sur un compte épargne.
	public CompteEpargne calculInteretEpargne(double mouvement, CompteEpargne compte) {
		double interet;

		Instant now = Instant.now();
		DateTime dt = now.toDateTime();
		int anneeActuelle = Integer.parseInt(dt.year().getAsText());
		Instant finAnnee = new DateTime(anneeActuelle, 12, 31, 0, 0, 0, 0).toInstant();

		org.joda.time.Duration duree = new Interval(now, finAnnee).toDuration();
		int intervalEnJour = duree.toStandardDays().getDays();

		double tauxDinteret = (double) compte.getTauxInteret() / 100;
		interet = (double) mouvement * tauxDinteret * intervalEnJour / 365;
		interet = (double) Math.round(interet * 100) / 100;

		compte.ajouterInteret(interet);
		return compte;
	}

	// Methode permettant de créditer les intêrets sur tous les comptes épargnes
	public void crediterLesInterets() {

		@SuppressWarnings("unchecked")
		List<Compte> listCompte = em.createQuery("Select c from Compte c").getResultList();
		double interet;
		for (Compte compte : listCompte) {
			if (compte instanceof CompteEpargne) {
				interet = ((CompteEpargne) compte).getCompteInteret();
				if (interet > 0) {
					compte.effecteurOperation(interet);
					((CompteEpargne) compte).setCompteInteret(0);
					em.merge(compte);
					gestionHistorique.ajouterMouvement(interet, compte, "Interêts créditeurs");
				}
			}
		}
		log.info("Crédit des intérets");
	}

	public boolean verificationAppartenanceCompte(int idClient, int idCompte) {
		Compte compte = em.find(Compte.class, idCompte);
		if (compte.getClient().getId() == idClient) {
			return true;
		} else {
			return false;
		}
	}

	// Methode permettant d'effectuer un virement d'un compte à un autre
	@Override
	public boolean effectuerVirement(int idCompte1, int idCompte2, Double iMontant) {
		Compte compte1 = getCompteById(idCompte1);
		Compte compte2 = getCompteById(idCompte2);

		if (compte1.getSolde() >= iMontant) {
			if(compte1 instanceof CompteEpargne){
				calculInteretEpargne(-iMontant, (CompteEpargne) compte1);
			}
			compte1.effecteurOperation(-iMontant);
			em.merge(compte1);
			gestionHistorique.ajouterMouvement(-iMontant, compte1, "Virement");

			if(compte2 instanceof CompteEpargne){
				calculInteretEpargne(iMontant, (CompteEpargne) compte2);
			}
			compte2.effecteurOperation(iMontant);
			em.merge(compte2);
			gestionHistorique.ajouterMouvement(iMontant, compte2, "Virement");
			return true;
		}
		return false;

	}
}
