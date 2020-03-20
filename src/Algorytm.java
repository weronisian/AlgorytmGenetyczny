import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Algorytm {
	
	public static ArrayList<Osobnik> populacja;
	public ArrayList<Osobnik> nowaPopulacja;
	private Osobnik najlepszeRozw;
	private double prawdKrzy;
	private double prawdMut;
	private int iloscPokolen;
	public int nr_pokolenia;
	public double najgorsza_ocena, najlepsza_ocena, œrednia_ocen;
	
	public Algorytm() {		
	}
	
	public Algorytm(boolean czyLosowy) {
		Algorytm.populacja = wygenerujPopulacje(Main.rozmiarPopulacji, Main.rozmiarGenotypu);
		wyswietlNajlepszeRozw();
	}
	
	public Algorytm(int rozmiarPopulacji, int rozmiarGenotypu, int iloscPokolen, double prawdKrzyzowania, double prawdMutacji) {
		this.prawdKrzy = prawdKrzyzowania;
		this.prawdMut = prawdMutacji;
		this.iloscPokolen = iloscPokolen;
		this.nr_pokolenia = 0;
		Algorytm.populacja = wygenerujPopulacje(rozmiarPopulacji, rozmiarGenotypu);
		wyswietlNajlepszeRozw();
		if(Main.czyZapis) {
			Plik plik = new Plik();
			obliczOceny(populacja);
			plik.zapis(nr_pokolenia, najlepsza_ocena, œrednia_ocen, najgorsza_ocena );
		}
	}
	
	public void start() {
		int n = 0;
		while(n < iloscPokolen) {
			n++;
			nr_pokolenia = n;
			nowaPopulacja = new ArrayList<Osobnik>();
			nowaPopulacja.add(new Osobnik(najlepszeRozw));
			
			if(!Main.czyRuletka)
				selekcjaTurniej(Main.rozmiarSelekcji, Main.rozmiarPopulacji, populacja);
			else
				selekcjaRuletka(Main.rozmiarPopulacji, populacja);
						
			//wyswietlanie wyniku po operacji
//			System.out.println("\nNowa populacja po selekcji");
			//wyœwietlanie osobnikow
//			for(int j=0; j<nowaPopulacja.size(); j++) {
//				System.out.println(nowaPopulacja.get(j).toString());
//			}
			najlepszeRozw(nowaPopulacja);
//			wyswietlNajlepszeRozw();
		
			nowaPopulacja = krzyzowanie(prawdKrzy, nowaPopulacja);
		
//			System.out.println("\nNowa populacja po krzyzowaniu");
			//wyœwietlanie
//			for(int j=0; j<nowaPopulacja.size(); j++) {
//				System.out.println(nowaPopulacja.get(j).toString());
//			}
			najlepszeRozw(nowaPopulacja);
//			wyswietlNajlepszeRozw();
		
			mutacja(prawdMut, nowaPopulacja);
		
//			System.out.println("\nNowa populacja po mutacji");
//			//wyœwietlanie
//			for(int j=0; j<nowaPopulacja.size(); j++) {
//				System.out.println(nowaPopulacja.get(j).toString());
//			}
			najlepszeRozw(nowaPopulacja);
			wyswietlNajlepszeRozw();
		
			//zast¹pienie starej populacji now¹
			populacja = nowaPopulacja;
			
			//zapis do pliku
			if(Main.czyZapis) {
				Plik plik = new Plik();
				obliczOceny(populacja);
				plik.zapis(nr_pokolenia, najlepsza_ocena, œrednia_ocen, najgorsza_ocena );
			}
		}
		wyswietlWynik();
		//do testowania krzyzowania i mutacji
//		Osobnik os = new Osobnik(11);
//		Osobnik os2 = new Osobnik(11);
//		ArrayList<Osobnik> lista = new ArrayList<>();
//		lista.add(os);
//		lista.add(os2);
//		System.out.println("\n\n"+os);
//		System.out.println(os2);
//		lista = krzyzowanie(lista);
//		for(int j=0; j<lista.size(); j++) {
//			System.out.println(lista.get(j).toString());
//		}
//		mutacjaOsobnika(os);
//		System.out.println(os);
	}
	
	public ArrayList<Osobnik> wygenerujPopulacje(int rozmiarPopulacji, int rozmiarGenotypu) {
		populacja = new ArrayList<Osobnik>();
		
		for(int i=0; i<rozmiarPopulacji; i++) {
			Osobnik osobnik = new Osobnik(rozmiarGenotypu);
			populacja.add(osobnik);
		}
		//wyœwietlanie osobnikow
//		for(int j=0; j<populacja.size(); j++) {
//			System.out.println(populacja.get(j).toString());
//		}
		
		najlepszeRozw = new Osobnik(najlepszeRozwWPop(populacja));		
		return populacja;
	}
	
	public void selekcjaTurniej(int N, int rozmiarPopulacji, ArrayList<Osobnik> staraPopulacja) {
		ArrayList<Osobnik> temp = new ArrayList<Osobnik>();
		for(int i=0; i<rozmiarPopulacji; i++) {
			temp.clear();
			// wybór losowy N osobników z populacji 
			for(int j=0; j < N; j++) {
				int rand = (int)(Math.random()*Main.rozmiarPopulacji);
				temp.add(new Osobnik(staraPopulacja.get(rand)));
			}
			
			//skopiowanie najlepszego z N osobników do nowej populacji
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
	
	public void selekcjaRuletka(int rozmiarPopulacji, ArrayList<Osobnik> staraPopulacja) {
		Osobnik maxOdl = najgorszeRozwWPop(staraPopulacja);
		double sumaWeight = 0;
		for(int i=0; i<staraPopulacja.size(); i++) {
			staraPopulacja.get(i).waga = maxOdl.dlugoscTrasy - staraPopulacja.get(i).dlugoscTrasy;
			sumaWeight += staraPopulacja.get(i).waga;
		}
		double ostatni = 0;
		for(int j=0; j<staraPopulacja.size(); j++) {
			double prawd = staraPopulacja.get(j).waga / sumaWeight;
			staraPopulacja.get(j).prawd = ostatni + prawd;
			ostatni += prawd;
		}
		int n=0;
		while(n < rozmiarPopulacji) {
			n++;
			double rand = (Math.random());
			for(int k=0; k<staraPopulacja.size(); k++) {
				if(rand <= staraPopulacja.get(k).prawd) {
					nowaPopulacja.add(new Osobnik(staraPopulacja.get(k)));
					break;
				}
			}
		}
	}
	
	public ArrayList<Osobnik> krzyzowanie(double prawdKrzyzowania, ArrayList<Osobnik> populacja) {
		ArrayList<Osobnik> temp = new ArrayList<Osobnik>();
		Osobnik rodzic1, rodzic2, dziecko1, dziecko2;
		for(int i=0; i<populacja.size()-1; i+=2) {
			double prawd = Math.random();
			rodzic1 = populacja.get(i);
			rodzic2 = populacja.get(i+1);
			if(prawd < prawdKrzyzowania) {
				dziecko1 = new Osobnik(Main.rozmiarGenotypu, true);
				dziecko2 = new Osobnik(Main.rozmiarGenotypu, true);
				int rand1 = (int)(Math.random()*Main.rozmiarGenotypu);
				int rand2 = (int)(Math.random()*Main.rozmiarGenotypu);
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
			} else {
				dziecko1 = new Osobnik(rodzic1);
				dziecko2 = new Osobnik(rodzic2);
			}
			
			temp.add(dziecko1);
			temp.add(dziecko2);			
		}
		return temp;
	}
	
	public void mutacja(double prawdMutacji, ArrayList<Osobnik> populacja) {
		for(int i=0; i< populacja.size(); i++) {
			double pm = (Math.random());
			if(pm <= prawdMutacji) 
				mutacjaOsobnika(populacja.get(i));				
		}					
	}
	
	public Osobnik mutacjaOsobnika(Osobnik osobnik) {
		int rand1 = (int)(Math.random()*Main.rozmiarGenotypu);
		int rand2 = (int)(Math.random()*Main.rozmiarGenotypu);
		int temp = osobnik.genotyp[rand1];
		osobnik.genotyp[rand1] = osobnik.genotyp[rand2];
		osobnik.genotyp[rand2] = temp;
		osobnik.nowaDlugoscTrasy();
		return osobnik;
	}
	
	public void najlepszeRozw(ArrayList<Osobnik> pop) {
		Osobnik temp = najlepszeRozwWPop(pop);
		if(temp.dlugoscTrasy <= najlepszeRozw.dlugoscTrasy) {
			najlepszeRozw = new Osobnik(temp);
		}			
	}
	
	public Osobnik najlepszeRozwWPop(ArrayList<Osobnik> pop) {
		int minInd=0;
		double minOdl = Double.MAX_VALUE;
		for(int i=0; i<pop.size(); i++) {
			if(minOdl > pop.get(i).dlugoscTrasy) {
				minOdl = pop.get(i).dlugoscTrasy;
				minInd = i;
			}
		}
		return pop.get(minInd);
	}
	
	public Osobnik najgorszeRozwWPop(ArrayList<Osobnik> pop) {
		int maxInd=0;
		double maxOdl = 0;
		double œrednia = 0;
		for(int i=0; i<pop.size(); i++) {
			if(maxOdl < pop.get(i).dlugoscTrasy) {
				maxOdl = pop.get(i).dlugoscTrasy;
				maxInd = i;
			}
			œrednia += pop.get(i).dlugoscTrasy;
		}
		œrednia_ocen = œrednia / pop.size();
		return pop.get(maxInd);
	}
	
	public void obliczOceny(ArrayList<Osobnik> pop) {
		Osobnik os = najgorszeRozwWPop(pop);
		najgorsza_ocena = os.dlugoscTrasy;
		os = najlepszeRozwWPop(pop);
		najlepsza_ocena = os.dlugoscTrasy;		
	}
	
	public void wyswitetlRozmiarPop() {
		System.out.println("Rozmiar populacji: "+populacja.size());
	}
	
	public Osobnik getNajlepszeRozw() {
		return najlepszeRozw;
	}
	
	private void wyswietlNajlepszeRozw() {
		System.out.println("Najlepsze rozwi¹zanie: " + String.format("%.2f", najlepszeRozw.dlugoscTrasy));	
	}
	
	private void wyswietlWynik() {
		System.out.println("\n---------------\nAlgorytm ewolucyjny \nNajlepsze rozwi¹zanie: " + String.format("%.2f", najlepszeRozw.dlugoscTrasy)
		+ "\n" + najlepszeRozw.genotypToString());
	}
	
	
	
}
