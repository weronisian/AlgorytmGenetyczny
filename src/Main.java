import java.util.ArrayList;

public class Main {

	public static int rozmiarGenotypu;
	public static int rozmiarPopulacji;
	public static ArrayList<Miejsce> listaMiejsc;
	public static String [] pliki = {"berlin11_modified.tsp", "berlin52.tsp", "kroA100.tsp", "kroA150.tsp", "kroA200.tsp"};
	
	public static void main(String[] args) {
		
		listaMiejsc = new ArrayList<Miejsce>();
//		

		Plik plik1 = new Plik("C:\\Users\\werci\\Desktop\\Semestr 6\\Sztuczna inteligencja i in�ynieria wiedzy\\laboratorium\\"+
								"TSP dane\\" + pliki[0]);
		plik1.odczyt();
			
		//wyswietlenie miejsc
//		for(int j=0; j<listaMiejsc.size(); j++) {
//			System.out.println(listaMiejsc.get(j).toString());
//		}
		
		rozmiarPopulacji = 100;
		Algorytm algorytm = new Algorytm(rozmiarPopulacji, rozmiarGenotypu);
		

	}

}
