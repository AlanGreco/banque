package beans;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;

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
	@PostConstruct
	@Schedule(minute = "*/10", hour = "*", persistent=false)
	public void automaticTimeout() {
//		System.out.println("Automatic timeout occured");
		gestionComptes.crediterLesInterets();
	}
}
