import java.util.Arrays;

public class Osobnik {

	public int genotyp[];
	private int rozmiar;
	public double dlugoscTrasy;
	
	//losowy genotyp
	public Osobnik(int size) {
		this.rozmiar = size;
		genotyp = new int[rozmiar];		
		wygeneruj();
		dlugoscTrasy = obliczDlugoscTrasy();
	}
	
	public Osobnik(Osobnik os) {
		this.rozmiar = os.rozmiar;
		this.genotyp = new int[rozmiar];
		for(int i=0; i<rozmiar; i++) 
			genotyp[i] = os.genotyp[i];
		dlugoscTrasy = obliczDlugoscTrasy();
	}
	
	public Osobnik(int size, boolean algZachlanny) {
		this.rozmiar = size;
		genotyp = new int[rozmiar];
	}
	
	public void wygeneruj() {
		for(int i=1; i<rozmiar+1; i++) {
			int rand = (int)(Math.random()*rozmiar);
			while(genotyp[rand]!=0) {
				rand = (int)(Math.random()*rozmiar);
			}
			genotyp[rand] = i;
		}
	}
	
	public double obliczDlugoscTrasy() {		
		double suma = 0;
		for(int i=0; i<rozmiar-1; i++) {
			suma += odlegloscEuklidesowa(Main.listaMiejsc.get(genotyp[i]-1), Main.listaMiejsc.get(genotyp[i+1]-1));
		}	
		suma += odlegloscEuklidesowa(Main.listaMiejsc.get(genotyp[rozmiar-1]-1), Main.listaMiejsc.get(genotyp[0]-1));
//		suma += odlegloscEuklidesowa(Main.listaMiejsc.get(rozmiar-1), Main.listaMiejsc.get(0));
		return suma;
	}
	
	public double odlegloscEuklidesowa(Miejsce m1, Miejsce m2) {
		float x1 = m1.getX();
		float x2 = m2.getX();
		float y1 = m1.getY();
		float y2 = m2.getY();
		double odleglosc = Math.sqrt(Math.pow((x2-x1),2)+ Math.pow((y2-y1), 2));	
		return odleglosc;
	}

	@Override
	public String toString() {
		return "Osobnik [genotyp=" + Arrays.toString(genotyp) + ", rozmiar=" + rozmiar + ", dlugoscTrasy="
				+ dlugoscTrasy + "]";
	}

		
}
