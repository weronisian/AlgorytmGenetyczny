import java.util.ArrayList;

public class Main {

	public static int rozmiarGenotypu;
	public static int rozmiarPopulacji;
	public static int liczbaPokolen;
	public static double prawdKrzyzowania;
	public static double prawdMutacji;
	public static int rozmiarSelekcji;
	public static boolean czyRuletka;
	public static boolean czyZapis;
	public static ArrayList<Miejsce> listaMiejsc;
	public static String [] pliki = {"berlin11_modified.tsp", "berlin52.tsp", "kroA100.tsp", "kroA150.tsp", "kroA200.tsp", "fl417.tsp", "pr2392.tsp"};
		
	public static void main(String[] args) {
		
		listaMiejsc = new ArrayList<Miejsce>();	
		ArrayList<Osobnik> listaNajlepszych = new ArrayList<>();
		Algorytm algorytm = new Algorytm();

		Plik plik1 = new Plik(pliki[3]);
		plik1.odczyt();	
		
		int liczbaPowtorzen = 10;
		for(int n=0; n<liczbaPowtorzen; n++) {
			
			rozmiarPopulacji = 20;
			liczbaPokolen = 2000;
			prawdKrzyzowania = 0.7;
			prawdMutacji = 0.1;
			rozmiarSelekcji = 5;
			czyRuletka = false;
			czyZapis = false;
			algorytm = new Algorytm(rozmiarPopulacji, rozmiarGenotypu, liczbaPokolen, prawdKrzyzowania, prawdMutacji);
			algorytm.start();
			
			listaNajlepszych.add(new Osobnik(algorytm.getNajlepszeRozw())); 
		}
		
		if(!Main.czyZapis) {
			Plik plik = new Plik();
			algorytm.obliczOceny(listaNajlepszych);
			plik.zapis(rozmiarPopulacji, algorytm.najlepsza_ocena, algorytm.œrednia_ocen, algorytm.najgorsza_ocena);
		}
		
//		algorytm.wyswitetlRozmiarPop();
		
		AlgorytmZachlanny algZachlanny = new AlgorytmZachlanny(rozmiarGenotypu);

	}

}
