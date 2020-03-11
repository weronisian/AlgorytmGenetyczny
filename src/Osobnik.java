import java.util.Arrays;

public class Osobnik {

	public int genotyp[];
	private int rozmiar;
	public double dlugoscTrasy;
	
	public Osobnik(int size) {
		this.rozmiar = size;
		genotyp = new int[rozmiar];		
		wygeneruj();
		dlugoscTrasy = obliczDlugoscTrasy();
	}
	
	private double obliczDlugoscTrasy() {		
		double suma = 0;
		for(int i=0; i<rozmiar-1; i++) {
			suma += Algorytm.odlegloscEuklidesowa(Main.listaMiejsc.get(genotyp[i]-1), Main.listaMiejsc.get(genotyp[i+1]-1));
		}	
		suma += Algorytm.odlegloscEuklidesowa(Main.listaMiejsc.get(rozmiar-1), Main.listaMiejsc.get(0));
		return suma;
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

	@Override
	public String toString() {
		return "Osobnik [genotyp=" + Arrays.toString(genotyp) + ", rozmiar=" + rozmiar + ", dlugoscTrasy="
				+ dlugoscTrasy + "]";
	}

	
	
	
}
