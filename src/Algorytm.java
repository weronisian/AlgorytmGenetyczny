import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
		
		System.out.println("\nNowa populacja po selekcji");
//		for(int j=0; j<nowaPopulacja.size(); j++) {
//			System.out.println(nowaPopulacja.get(j).toString());
//		}
		najlepszeRozw(nowaPopulacja);
		wyswietlNajlepszeRozw();
		
		nowaPopulacja = krzyzowanie(nowaPopulacja);
		
		System.out.println("\nNowa populacja po krzyzowaniu");
//		for(int j=0; j<nowaPopulacja.size(); j++) {
//			System.out.println(nowaPopulacja.get(j).toString());
//		}
		najlepszeRozw(nowaPopulacja);
		wyswietlNajlepszeRozw();
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
	
	public ArrayList<Osobnik> krzyzowanie(ArrayList<Osobnik> populacja) {
		ArrayList<Osobnik> temp = new ArrayList<Osobnik>();
		Osobnik rodzic1, rodzic2, dziecko1, dziecko2;
		for(int i=0; i<populacja.size(); i+=2) {
			dziecko1 = new Osobnik(Main.rozmiarGenotypu, true);
			dziecko2 = new Osobnik(Main.rozmiarGenotypu, true);
			rodzic1 = populacja.get(i);
			rodzic2 = populacja.get(i+1);
			int rand1 = (int)(Math.random()*populacja.size());
			int rand2 = (int)(Math.random()*populacja.size());
			if(rand1 > rand2) {
				int t = rand1;
				rand1 = rand2;
				rand2 = t;
			}// rand1 zawsze <= od rand2
			Set <Integer> set1 = new HashSet<Integer>();
			Set <Integer> set2 = new HashSet<Integer>();
			for(int j=0; j<Main.rozmiarGenotypu; j++) {
				if(rand1<=j && j<=rand2) {
					dziecko1.genotyp[j] = rodzic1.genotyp[j];
					dziecko2.genotyp[j] = rodzic2.genotyp[j];
					set1.add(dziecko1.genotyp[j]);
					set2.add(dziecko2.genotyp[j]);
				} 						
			}
			for(int k=0; k<Main.rozmiarGenotypu; k++) {
				if(!set1.contains(rodzic2.genotyp[k])) {
					int x=0;
					while(dziecko1.genotyp[x]!=0 && x<Main.rozmiarGenotypu-1)
						x++;
					dziecko1.genotyp[x] = rodzic2.genotyp[k];							
				}	
				if(!set2.contains(rodzic1.genotyp[k])) {
					int x=0;
					while(dziecko2.genotyp[x]!=0 && x<Main.rozmiarGenotypu-1)
						x++;
					dziecko2.genotyp[x] = rodzic1.genotyp[k];							
				}			
			}
			dziecko1.dlugoscTrasy = dziecko1.obliczDlugoscTrasy();
			dziecko2.dlugoscTrasy = dziecko2.obliczDlugoscTrasy();
			temp.add(dziecko1);
			temp.add(dziecko2);			
		}
		return temp;
	}
	
	public Osobnik mutacja(Osobnik osobnik) {
		int rand1 = (int)(Math.random()*Main.rozmiarGenotypu);
		int rand2 = (int)(Math.random()*Main.rozmiarGenotypu);
		int temp = osobnik.genotyp[rand1];
		osobnik.genotyp[rand1] = osobnik.genotyp[rand2];
		osobnik.genotyp[rand2] = temp;
		return osobnik;
	}
	
	public void najlepszeRozw(ArrayList<Osobnik> pop) {
		int minInd=0;
		double minOdl = Double.MAX_VALUE;
		for(int i=0; i<pop.size(); i++) {
			if(minOdl > pop.get(i).dlugoscTrasy) {
				minOdl = pop.get(i).dlugoscTrasy;
				minInd = i;
			}
		}
		najlepszeRozw = pop.get(minInd);
	}
	
	private void wyswietlNajlepszeRozw() {
//		System.out.println("\n---------------\nAlgorytm losowy \nNajlepsze rozwi¹zanie: " + String.format("%.2f", najlepszeRozw.dlugoscTrasy));
		System.out.println("Najlepsze rozwi¹zanie: " + String.format("%.2f", najlepszeRozw.dlugoscTrasy));	
	}
	
	
	
	
}
