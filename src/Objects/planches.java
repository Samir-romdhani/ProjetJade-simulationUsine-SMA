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

}