import java.util.ArrayList;

public class Algorytm {
	
	public static ArrayList<Osobnik> populacja;
	public ArrayList<Osobnik> nowaPopulacja;
	private Osobnik najlepszeRozw;
	private int iloscPokolen;

	public Algorytm(int rozmiarPopulacji, int rozmiarGenotypu) {
		Algorytm.populacja = wygenerujPopulacje(rozmiarPopulacji, rozmiarGenotypu);
		wyswietlNajlepszeRozw();		
	}
	
	public void start() {
		nowaPopulacja = new ArrayList<Osobnik>();
		selekcjaTurniej(3, Main.rozmiarPopulacji);
		
		for(int j=0; j<nowaPopulacja.size(); j++) {
			System.out.println(nowaPopulacja.get(j).toString());
		}
			
//		Osobnik os = new Osobnik(11);
//		System.out.println(os);
//		mutacja(os);
//		System.out.println(os);
	}
	
	public ArrayList<Osobnik> wygenerujPopulacje(int rozmiarPopulacji, int rozmiarGenotypu) {
		populacja = new ArrayList<Osobnik>();
		double minDlugosc = Double.MAX_VALUE;
		int minIndex = 0;
		
		for(int i=0; i<rozmiarPopulacji; i++) {
			Osobnik osobnik = new Osobnik(rozmiarGenotypu);
			if(minDlugosc > osobnik.dlugoscTrasy) {
				minDlugosc = osobnik.dlugoscTrasy;
				minIndex = i;
			}
			populacja.add(osobnik);
		}
		//wyœwietlanie osobnikow
		for(int j=0; j<populacja.size(); j++) {
			System.out.println(populacja.get(j).toString());
		}
		
		najlepszeRozw = populacja.get(minIndex);		
		return populacja;
	}
	
	public void selekcjaTurniej(int N, int rozmiarPopulacji) {
		ArrayList<Osobnik> temp = new ArrayList<Osobnik>();
		for(int i=0; i<rozmiarPopulacji; i++) {
			temp.clear();
			// wybór losowy N osobników z populacji 
			for(int j=0; j < N; j++) {
				int rand = (int)(Math.random()*Main.rozmiarPopulacji);
				temp.add(new Osobnik(populacja.get(rand)));
			}
//			System.out.println();
//			for(int j=0; j<temp.size(); j++) {
//				System.out.println(temp.get(j).toString());
//			}
			
			//skopiowanie najlepszego do nowej populacji
			int bestIndex = 0;
			double bestOdl = Double.MAX_VALUE;
			for(int k=0; k < temp.size(); k++) {
				if(bestOdl > temp.get(k).dlugoscTrasy) {
					bestOdl = temp.get(k).dlugoscTrasy;
					bestIndex = k;
				}
			}
			nowaPopulacja.add(new Osobnik(temp.get(bestIndex)));
		}
	}
	
	public void selekcjaRuletka() {
		
	}
	
	public void krzyzowanie() {
		
	}
	
	public Osobnik mutacja(Osobnik osobnik) {
		int rand1 = (int)(Math.random()*Main.rozmiarGenotypu);
		int rand2 = (int)(Math.random()*Main.rozmiarGenotypu);
		int temp = osobnik.genotyp[rand1];
		osobnik.genotyp[rand1] = osobnik.genotyp[rand2];
		osobnik.genotyp[rand2] = temp;
		return osobnik;
	}
	
	private void wyswietlNajlepszeRozw() {
		System.out.println("\n---------------\nAlgorytm losowy \nNajlepsze rozwi¹zanie: " + String.format("%.2f", najlepszeRozw.dlugoscTrasy));
	}
	
	
	
	
}
