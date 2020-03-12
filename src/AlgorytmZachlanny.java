
public class AlgorytmZachlanny {
	
	public boolean[] visited;
	public Osobnik optymalneRozw;
	
	public AlgorytmZachlanny (int rozmiarGenotypu) {
		optymalneRozw = new Osobnik(rozmiarGenotypu, true);
		visited = new boolean[rozmiarGenotypu];
		for(int i=0; i<rozmiarGenotypu; i++)
			visited[i] = false;
		generujRozwiazanie();
		wyswietlRozw();
	}
	
	public void generujRozwiazanie() {
		int rand = (int)(Math.random()*Main.rozmiarGenotypu);
		optymalneRozw.genotyp[0] = rand;
		visited[rand-1] = true;
		
		int bestNode = 0;
		double bestOdleglosc = Double.MAX_VALUE;
		for(int i=1; i<Main.rozmiarGenotypu; i++) {
			for(int j=0; j<Main.rozmiarGenotypu; j++) {
				if(visited[i] == false) {
					double odleglosc = optymalneRozw.odlegloscEuklidesowa(Main.listaMiejsc.get(i-1), Main.listaMiejsc.get(j));
					if(odleglosc < bestOdleglosc) {
						bestNode = Main.listaMiejsc.get(j).getNr();
					}
					visited[bestNode-1] = true;
					optymalneRozw.genotyp[i] = bestNode;
				}			
			}
		}
//		optymalneRozw.obliczDlugoscTrasy();
	}
	
	public void wyswietlRozw() {
		System.out.println(optymalneRozw);
		System.out.println("Algorytm zach³anny \nNajlepsze rozwi¹zanie: " + String.format("%.2f", optymalneRozw.dlugoscTrasy));
	}

}
