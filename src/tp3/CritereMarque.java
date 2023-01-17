package tp3;

public class CritereMarque implements Critere {

	private final String marque;
	public CritereMarque(String mrk)
	{
		marque=mrk;
	}
	@Override
	public boolean estSatisfaitPar(Voiture v) {	
		return marque.equals(v.getMarque());
	}

}
