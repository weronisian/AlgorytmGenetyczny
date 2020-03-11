import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Plik {

	private File plik;
	public static String NAME = "NAME:";
	public static String SIZE = "DIMENSION:";
	public static String TYP = "EDGE_WEIGHT_TYPE:";
	public static String COORD_SECTION = "NODE_COORD_SECTION";
	public static String EOF = "EOF";
	
	public Plik(String sciezka) {
		this.plik = new File(sciezka);
	}
	
	public void odczyt() {
		String linia;
		boolean odczytMiejsc = false;
		try {
			BufferedReader odczyt = new BufferedReader(new FileReader(plik));

			while((linia = odczyt.readLine()) != null) {
				if(linia.equals(COORD_SECTION)) {
					linia = odczyt.readLine();
					odczytMiejsc = true;
				}
				if(linia.equals(EOF)) {
//					linia = odczyt.readLine();
					odczytMiejsc = false;
				}
				odczytWiersza(linia, odczytMiejsc);
			}		
			odczyt.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void odczytWiersza(String linia, boolean odczytMiejsc) {
		String wiersz[] = linia.split(" ");
		if(wiersz[0].equals(SIZE))
			Main.rozmiarGenotypu = Integer.parseInt(wiersz[1]);
		if(odczytMiejsc == true) {
			Miejsce miejsce = new Miejsce(Integer.parseInt(wiersz[0]),Float.parseFloat(wiersz[1]), Float.parseFloat(wiersz[2]));
			Main.listaMiejsc.add(miejsce);
		}
		//wyswietlanie pliku
		System.out.println(linia);
	}

	public void zapis() {
		
	}
	
}
