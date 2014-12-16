package beans;

import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;

/**
 * Session Bean implementation class TimerInteretBean
 */
@Singleton
@DependsOn({"GestionCompte"})
@LocalBean
public class TimerInteretBean {
	@Resource
	TimerService timerService;
	@EJB
	GestionCompteRemote gestionComptes;


	/**
	 * Default constructor.
	 */
	public TimerInteretBean() {
		// TODO Auto-generated constructor stub
	}
	
	// Le versement des intêrets créditeur est effectué à interval régulié (normalement 1 fois par an) toutes les 10 minutes pour l'exemple
	@Schedule(minute = "*/10", hour = "*", persistent=false)
	public void automaticTimeout() {
		gestionComptes.crediterLesInterets();
	}
}
