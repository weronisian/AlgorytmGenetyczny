import java.util.ArrayList;

public class Algorytm {
	
	public static ArrayList<Osobnik> listaOsobnikow;
	private Osobnik najlepszeRozw;

	public Algorytm(int rozmiarPopulacji, int rozmiarGenotypu) {
		Algorytm.listaOsobnikow = wygenerujPopulacje(rozmiarPopulacji, rozmiarGenotypu);
		wyswietlNajlepszeRozw();		
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
//		for(int j=0; j<listaOsobnikow.size(); j++) {
//			System.out.println(listaOsobnikow.get(j).toString());
//		}
		
		najlepszeRozw = listaOsobnikow.get(minIndex);		
		return listaOsobnikow;
	}
	
	private void wyswietlNajlepszeRozw() {
		System.out.println("---------------\nAlgorytm losowy \nNajlepsze rozwi¹zanie: " + String.format("%.2f", najlepszeRozw.dlugoscTrasy));
	}
	
	
	
	
}
