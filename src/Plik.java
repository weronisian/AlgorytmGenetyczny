import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Plik {

	private File plik;
	public static String NAME = "NAME:";
	public static String SIZE = "DIMENSION:";
	public static String TYP = "EDGE_WEIGHT_TYPE:";
	public static String COORD_SECTION = "NODE_COORD_SECTION";
	public static String EOF = "EOF";
	public String name;
	
	public Plik() {
		
	}
	
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
					odczytMiejsc = false;
				}
				odczytWiersza(linia, odczytMiejsc);
			}		
			odczyt.close();
			wyswietlInfo();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void odczytWiersza(String linia, boolean odczytMiejsc) {
		String wiersz[] = linia.split(" ");
		if(wiersz[0].equals(NAME))
			name = wiersz[1];
		if(wiersz[0].equals(SIZE))
			Main.rozmiarGenotypu = Integer.parseInt(wiersz[1]);
		//dodanie miejsc do listy
		if(odczytMiejsc == true) {
			Miejsce miejsce = new Miejsce(Integer.parseInt(wiersz[0]),Float.parseFloat(wiersz[1]), Float.parseFloat(wiersz[2]));
			Main.listaMiejsc.add(miejsce);
		}
		//wyswietlanie pliku
//		System.out.println(linia);
	}
	
	public void wyswietlInfo() {
		System.out.println("Plik\nNAME: " + name + "\nDIMENSION: "+Main.rozmiarGenotypu);
	}

	public void zapis(int nr_pokolenia, double najlepsza_ocena, double œrednia_ocen, double najgorsza_ocena) {
		File plik2 = new File("Wyniki.csv");
		try {
			BufferedWriter zapis = new BufferedWriter(new FileWriter(plik2));
			
			zapis.write(Integer.toString(nr_pokolenia));
			zapis.write(";"+String.format("%.2f",najlepsza_ocena));
			zapis.write(";"+String.format("%.2f",œrednia_ocen));
			zapis.write(";"+String.format("%.2f",najgorsza_ocena)+";");
			zapis.newLine();
			
			zapis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
