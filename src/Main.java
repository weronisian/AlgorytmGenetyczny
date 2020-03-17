import java.util.ArrayList;

public class Main {

	public static int rozmiarGenotypu;
	public static int rozmiarPopulacji;
	public static int liczbaPowtorzen;
	public static double prawdMutacji;
//	public static int liczbaPowtorzen;
	public static ArrayList<Miejsce> listaMiejsc;
	public static String [] pliki = {"berlin11_modified.tsp", "berlin52.tsp", "kroA100.tsp", "kroA150.tsp", "kroA200.tsp", "fl417.tsp", "pr2392.tsp"};
		
	public static void main(String[] args) {
		
		listaMiejsc = new ArrayList<Miejsce>();	

		Plik plik1 = new Plik(pliki[0]);
		plik1.odczyt();	
				
		rozmiarPopulacji = 10;
		liczbaPowtorzen = 10;
		prawdMutacji = 0.9;
		Algorytm algorytm = new Algorytm(rozmiarPopulacji, rozmiarGenotypu, liczbaPowtorzen, prawdMutacji);
		algorytm.start();
		
//		AlgorytmZachlanny algZachlanny = new AlgorytmZachlanny(rozmiarGenotypu);

	}

}
