package tp3;
import java.util.ArrayList;
import java.util.List;
public class InterCritere  implements Critere{
	
	private List<Critere> lesCriteres;
	
	public InterCritere() {
		lesCriteres=new ArrayList<>();
	}
	public void addCritere(Critere c)
	{
		lesCriteres.add(c);
	}
	public void viderCritere()
	{
		lesCriteres.clear();
	}
	@Override
	public boolean estSatisfaitPar(Voiture v) {
		for(Critere crit : lesCriteres)
		{
			if(!crit.estSatisfaitPar(v)) return false;
		}
		return true;
	}

}
