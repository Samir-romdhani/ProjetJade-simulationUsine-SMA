package Objects;

import java.io.Serializable;

public class planches implements Serializable {
	
	private int nb ;
	
	public planches(int n) {
		this.nb = n ;
	}
	
	public int getNb() {
		return nb;
	}
	
	public void setNb(int nb) {
		this.nb = nb;
	}
	
	public planches() {
		// TODO Auto-generated constructor stub
	}

}