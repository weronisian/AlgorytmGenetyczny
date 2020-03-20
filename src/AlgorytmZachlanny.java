
public class AlgorytmZachlanny {
	
	private boolean[] visited;
	private Osobnik optymalneRozw, najlRozw;
	
	public AlgorytmZachlanny (int rozmiarGenotypu) {
		optymalneRozw = new Osobnik(rozmiarGenotypu, true);
		najlRozw = new Osobnik(rozmiarGenotypu, true);
		visited = new boolean[rozmiarGenotypu];
		
		najlRozw();
		wyswietlNajlRozw();
	}
	
	public void najlRozw() {
		double najlDlug = Double.MAX_VALUE;
		for(int i=1; i<Main.rozmiarGenotypu+1; i++) {
			optymalneRozw = new Osobnik(Main.rozmiarGenotypu, true);
			for(int j=0; j<Main.rozmiarGenotypu; j++)
				visited[j] = false;
			generujRozwiazanie(i);
//			wyswietlRozw(i);
			
			if(optymalneRozw.dlugoscTrasy < najlDlug) {
				najlDlug = optymalneRozw.dlugoscTrasy;
				najlRozw = optymalneRozw;
			}
		}
	}
	
	public void generujRozwiazanie(int rand) {
		optymalneRozw.genotyp[0] = rand;
		visited[rand-1] = true;
				
		int lastNode = rand;
		int bestNode = -1;
		double bestOdleglosc;
		for(int i=1; i<Main.rozmiarGenotypu; i++) {
			bestOdleglosc = Double.MAX_VALUE;
			for(int j=0; j<Main.rozmiarGenotypu; j++) {
				if(visited[j] == false) {
					double odleglosc = optymalneRozw.odlegloscEuklidesowa(Main.listaMiejsc.get(lastNode-1), Main.listaMiejsc.get(j));
					if(odleglosc < bestOdleglosc) {
						bestNode = Main.listaMiejsc.get(j).getNr();
						bestOdleglosc = odleglosc;
					}					
				}				
			}			
			lastNode = bestNode;
			visited[bestNode-1] = true;
			optymalneRozw.genotyp[i] = bestNode;
//			System.out.println(optymalneRozw);			
		}
		optymalneRozw.dlugoscTrasy = optymalneRozw.obliczDlugoscTrasy();
	}
	
	public void wyswietlRozw(int i) {
		System.out.println("Dla i="+i+" najlepsze rozwi¹zanie: "+ String.format("%.2f", optymalneRozw.dlugoscTrasy));
		System.out.println(optymalneRozw);
	}
	
	public void wyswietlNajlRozw() {
		System.out.println("---------------\nAlgorytm zach³anny \nNajlepsze rozwi¹zanie: " + String.format("%.2f", najlRozw.dlugoscTrasy));
		System.out.println(najlRozw);
	}
	
}
