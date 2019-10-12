package scenari;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import classi.Manutenzione;
import classi.ParcoVeicoli;
import classi.Veicolo;
import exception.VeicoloNotFoundException;
import utility.Constant;

public class ScenarioCostoTotaleByVeicolo {

	public static void main(String[] args) throws VeicoloNotFoundException {

		Scanner in = new Scanner(System.in);
		Scanner inVeicolo = null;
		Scanner inManutenzione = null;

		try {
			File fp = new File(Constant.FILE_VEICOLI);
			inVeicolo = new Scanner(fp);
		} catch(FileNotFoundException e) {
			System.err.println("Eccezione FileNotFound cattuarata.");
			System.err.println("Il file " + Constant.FILE_VEICOLI + "non e' stato trovato.");
		}

		try {
			File fp = new File(Constant.FILE_MANUTENZIONI);
			inManutenzione = new Scanner(fp);
		} catch(FileNotFoundException e) {
			System.err.println("Eccezione FileNotFound cattuarata.");
			System.err.println("Il file " + Constant.FILE_MANUTENZIONI + "non e' stato trovato.");
		}

		ParcoVeicoli parcoVeicoli = new ParcoVeicoli(inVeicolo, inManutenzione);

		System.out.println("Inserire la targa di un veicolo del quale si vuole conoscere il totale costi.");
		String inTarga = in.nextLine();
		
		Veicolo veicoloTemp = parcoVeicoli.getHashVeicoli().get(inTarga);
		ArrayList<Manutenzione> arrayManutenzioni = veicoloTemp.getArrayManutenzioni();
		
		double costoTotale = parcoVeicoli.returnTotaleCostiByVeicolo(inTarga);

		if(costoTotale != 0) {
			System.out.println("\t--- Totale costi sostenuti per il veicolo inserito ---");
			System.out.println("");
			System.out.println("Costo veicolo: " + veicoloTemp.getCostoAcquisto());
			System.out.println("");
			for(Manutenzione manutenzioneTemp : arrayManutenzioni) {
				System.out.println("--- Manutenzione ---");
				manutenzioneTemp.print(System.out);
			}
			System.out.println("Totale dei costi sostenuti per il veicolo inserito " + costoTotale);
		}
		else
			throw new VeicoloNotFoundException("Non e' stato possibile trovare il veicolo inserito.");
		
		in.close();

	}

}
