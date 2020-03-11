import java.util.ArrayList;

public class Algorytm {
	
//	public int rozmiarGenotypu;
	public static int rozmiarPopulacji;
	public static ArrayList<Osobnik> listaOsobnikow;
	private Osobnik najlepszeRozw;
//	private int indexNajlepszeRozw;

	public Algorytm(int rozmiarPopulacji, int rozmiarGenotypu) {
		Algorytm.rozmiarPopulacji = rozmiarPopulacji;
//		this.rozmiarGenotypu = rozmiarGenotypu;
		Algorytm.listaOsobnikow = wygenerujPopulacje(rozmiarPopulacji, rozmiarGenotypu);
		
	}
	
	public ArrayList<Osobnik> wygenerujPopulacje(int rozmiarPopulacji, int rozmiarGenotypu) {
		listaOsobnikow = new ArrayList<Osobnik>();
		double minDlugosc = Double.MAX_VALUE;
		int minIndex = 0;
		
		for(int i=0; i<rozmiarPopulacji; i++) {
			Osobnik osobnik = new Osobnik(rozmiarGenotypu);
			if(minDlugosc > osobnik.dlugoscTrasy) {
				minDlugosc = osobnik.dlugoscTrasy;
				minIndex = i;
			}
			listaOsobnikow.add(osobnik);
		}
		//wyœwietlanie osobnikow
		for(int j=0; j<listaOsobnikow.size(); j++) {
			System.out.println(listaOsobnikow.get(j).toString());
		}
		najlepszeRozw = listaOsobnikow.get(minIndex);
		System.out.println("Najlepsze rozw: " + najlepszeRozw);
		return listaOsobnikow;
	}
	
	public static double odlegloscEuklidesowa(Miejsce m1, Miejsce m2) {
		float x1 = m1.getX();
		float x2 = m2.getX();
		float y1 = m1.getY();
		float y2 = m2.getY();
		double odleglosc = Math.sqrt(Math.pow((x2-x1),2)+ Math.pow((y2-y1), 2));	
		return odleglosc;
	}
	
	
}
