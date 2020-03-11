import java.util.ArrayList;

public class Main {

	public static int rozmiarGenotypu;
	public static int rozmiarPopulacji;
	public static ArrayList<Osobnik> listaOsobnikow;
	public static ArrayList<Miejsce> listaMiejsc;
	
	public static void main(String[] args) {
		
		listaOsobnikow = new ArrayList<Osobnik>();
		listaMiejsc = new ArrayList<Miejsce>();
		rozmiarPopulacji = 20;

		Plik plik1 = new Plik("C:\\Users\\werci\\Desktop\\Semestr 6\\Sztuczna inteligencja i in¿ynieria wiedzy\\laboratorium\\TSP dane\\berlin11_modified.tsp");
//		Plik plik2 = new Plik("C:\\Users\\werci\\Desktop\\Semestr 6\\Sztuczna inteligencja i in¿ynieria wiedzy\\laboratorium\\TSP dane\\kroA100.tsp");

		plik1.odczyt();
//		plik2.odczyt();
		
		System.out.println("Rozmiar: " + rozmiarGenotypu);
		
		for(int i=0; i<rozmiarPopulacji; i++) {
			Osobnik osobnik = new Osobnik(rozmiarGenotypu);
			listaOsobnikow.add(osobnik);
		}
		
		for(int j=0; j<listaOsobnikow.size(); j++) {
			System.out.println(listaOsobnikow.get(j).toString());
		}
		
		for(int j=0; j<listaMiejsc.size(); j++) {
			System.out.println(listaMiejsc.get(j).toString());
		}
		
		Algorytm algorytm = new Algorytm(20);
		System.out.println(algorytm.odlegloscEuklidesowa(listaMiejsc.get(0), listaMiejsc.get(1)));
		

	}

}
