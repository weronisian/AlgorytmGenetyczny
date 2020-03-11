import java.util.ArrayList;

public class Algorytm {
	
//	public static int rozmiarGenotypu;
	public static int rozmiarPopulacji;
//	public static ArrayList<Osobnik> listaOsobnikow;
//	public static ArrayList<Miejsce> listaMiejsc;

	public Algorytm(int rozmiarPopulacji) {
		Algorytm.rozmiarPopulacji = rozmiarPopulacji;
		
	}
	
	public void wygenerujPopulacje(int rozmiarPopulacji) {
		
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
