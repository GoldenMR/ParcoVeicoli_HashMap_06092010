package classi;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

import exception.ManutenzioneNotFoundException;
import exception.VeicoloNotFoundException;

public class ParcoVeicoli {
	
	public ParcoVeicoli(Scanner inVeicolo, Scanner inManutenzione) {
		
		hashVeicoli = new LinkedHashMap<String, Veicolo>();
		Veicolo veicoloTemp = Veicolo.read(inVeicolo);
		while(veicoloTemp != null) {
			hashVeicoli.put(veicoloTemp.getTarga(), veicoloTemp);
			veicoloTemp = Veicolo.read(inVeicolo);
		}
		
		arrayManutenzioni = new ArrayList<Manutenzione>();
		Manutenzione manutenzioneTemp = Manutenzione.read(inManutenzione);
		while(manutenzioneTemp != null) {
			try {
				arrayManutenzioni.add(manutenzioneTemp);
				veicoloTemp = this.searchVeicoloByTarga(manutenzioneTemp.getTargaVeicolo());
				veicoloTemp.addManutenzione(manutenzioneTemp);
				manutenzioneTemp.setVeicolo(veicoloTemp);
			} catch(VeicoloNotFoundException e) {
				System.err.println("Eccezione VeicoloNotFound catturata.");
				System.err.println("Il collegamento con il veicolo " + veicoloTemp + "non e' stato effettuato.");
			}
			manutenzioneTemp = Manutenzione.read(inManutenzione);
		}
		
	}
	
	private ParcoVeicoli(LinkedHashMap<String, Veicolo> hashVeicoli, ArrayList<Manutenzione> arrayManutenzioni) {
		this.hashVeicoli = hashVeicoli;
		this.arrayManutenzioni = arrayManutenzioni;
	}

	public LinkedHashMap<String, Veicolo> getHashVeicoli() {
		return hashVeicoli;
	}
	
	public ArrayList<Manutenzione> getArrayManutenzioni() {
		return arrayManutenzioni;
	}

	public Veicolo searchVeicoloByTarga(String inTarga) throws VeicoloNotFoundException {
		Veicolo veicoloTemp = hashVeicoli.get(inTarga);
		if(veicoloTemp != null)
			return veicoloTemp;
		else
			throw new VeicoloNotFoundException("Il veicolo con targa " + inTarga + "non e' stato trovato.");
	}
	
	public Manutenzione searchManutenzioneByTarga(String inTarga) throws ManutenzioneNotFoundException {

		int i = 0;
		boolean flag = false;
		Manutenzione manutenzioneTemp = null;
		
		while(!flag && i < arrayManutenzioni.size()) {
			if(arrayManutenzioni.get(i).getTargaVeicolo().equalsIgnoreCase(inTarga)) {
				manutenzioneTemp = arrayManutenzioni.get(i);
				flag = true;
			}
			else
				i++;
		}
		if(manutenzioneTemp != null)
			return manutenzioneTemp;
		else
			throw new ManutenzioneNotFoundException("La manutenzione relativa alla targa " + inTarga + "non e' stata trovata.");
			
	}
	
//	a) determinare il totale dei costi sostenuti per l’acquisto e la manutenzione di un dato veicolo
	public double returnTotaleCostiByVeicolo(String inTargaVeicolo) {
		double costoVeicolo = 0;
		double costoManutenzioni = 0;
		
		for(Veicolo veicoloTemp : hashVeicoli.values()) {
			if(veicoloTemp.getTarga().equalsIgnoreCase(inTargaVeicolo)) {
				costoVeicolo = veicoloTemp.getCostoAcquisto();
				for(Manutenzione manutenzioneTemp : veicoloTemp.getArrayManutenzioni()) {
					costoManutenzioni = costoManutenzioni + manutenzioneTemp.getCostoManutenzione();
				}
			}
		}
		
		double costoTotale = costoManutenzioni + costoVeicolo;
		return costoTotale;
	}
	
//	b) individuare tutti i veicoli che abbiamo un’età maggiore di una soglia assegnata
	public LinkedHashMap<String, Veicolo> searchVeicoloByAnnoImmatricolazione(Date inAnno) throws VeicoloNotFoundException {
		LinkedHashMap<String, Veicolo> filterHashVeicoli = new LinkedHashMap<String, Veicolo>();
		
		for(Veicolo veicoloTemp : hashVeicoli.values()) {
			if(veicoloTemp.getAnnoImmatricolazione().after(inAnno))
				filterHashVeicoli.put(veicoloTemp.getTarga(), veicoloTemp);
		}
		
		if(!filterHashVeicoli.isEmpty())
			return filterHashVeicoli;
		else
			throw new VeicoloNotFoundException("Eccezione VeicoloNotFound catturata.");
	}
	
//	c) individuare tutti i veicoli che abbiamo una percorrenza chilomentrica maggiore di una soglia
//	assegnata
	public LinkedHashMap<String, Veicolo> searchVeicoloByChilometri(double inChilometri) throws VeicoloNotFoundException {
		LinkedHashMap<String, Veicolo> filterHashVeicoli = new LinkedHashMap<String, Veicolo>();
		
		for(Veicolo veicoloTemp : hashVeicoli.values()) {
			if(veicoloTemp.getChilometri() > inChilometri)
				filterHashVeicoli.put(veicoloTemp.getTarga(), veicoloTemp);
		}
		
		if(!filterHashVeicoli.isEmpty())
			return filterHashVeicoli;
		else
			throw new VeicoloNotFoundException("Eccezione VeicoloNotFound catturata.");
	}

	public void printManutenzioni() {
		for(Manutenzione m : arrayManutenzioni)
			m.print(System.out);
		System.out.println("");
	}
	
	public void printVeicoli() {
		for(Veicolo v : hashVeicoli.values())
			v.print(System.out);
	}

	LinkedHashMap<String, Veicolo> hashVeicoli;
	ArrayList<Manutenzione> arrayManutenzioni;
}
