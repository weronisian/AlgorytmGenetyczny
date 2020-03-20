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
		
		Plik plik1 = new Plik(pliki[2]);
		plik1.odczyt();	
		
		long start = System.currentTimeMillis();
		
		int liczbaPowtorzen = 10;
		for(int n=0; n<liczbaPowtorzen; n++) {
			
			rozmiarPopulacji = 200;
			liczbaPokolen = 2000;
			prawdKrzyzowania = 0.9;
			prawdMutacji = 0.3;
			rozmiarSelekcji = 3;
			czyRuletka = false;
			czyZapis = false; // (true - wartoœci po ka¿dej generacji, false - wartoœci po okreœlonej liczbie powtorzeñ)
//			algorytm = new Algorytm(true); // algorytm losowy
			algorytm = new Algorytm(rozmiarPopulacji, rozmiarGenotypu, liczbaPokolen, prawdKrzyzowania, prawdMutacji);
			algorytm.start();
			
			listaNajlepszych.add(new Osobnik(algorytm.getNajlepszeRozw())); 
		}
		
		long end = System.currentTimeMillis();
		long czas = end - start;
		double seconds = czas / 1000.0;
		System.out.println("Czas:"+seconds);
				
		
		if(!Main.czyZapis) {
			Plik plik = new Plik();
			algorytm.obliczOceny(listaNajlepszych);
			// odchylenie standardowe
			double std, sum = 0;
			for(int i=0; i<listaNajlepszych.size(); i++) {
				sum += Math.pow((listaNajlepszych.get(i).dlugoscTrasy - algorytm.œrednia_ocen), 2); 
			}
			std = Math.sqrt(sum / (double)liczbaPowtorzen);
			
			plik.zapis(std, algorytm.najlepsza_ocena, algorytm.œrednia_ocen, algorytm.najgorsza_ocena);
		}
		
		//algorytm zach³anny
		AlgorytmZachlanny algZachlanny = new AlgorytmZachlanny(rozmiarGenotypu);

	}

}
