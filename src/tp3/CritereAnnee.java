package tp3;

public class CritereAnnee  implements Critere {

	private final int annee;
	public CritereAnnee(int an) {
		annee=an;
	}
	@Override
	public boolean estSatisfaitPar(Voiture v) {	
		return v.getAnneeProd()==annee;
	}

}
