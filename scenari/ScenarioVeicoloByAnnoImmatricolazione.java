package scenari;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

import classi.ParcoVeicoli;
import classi.Veicolo;
import exception.VeicoloNotFoundException;
import utility.Constant;

public class ScenarioVeicoloByAnnoImmatricolazione {

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

		System.out.println("Inserire una anno di immatricolazione.");
		String inDataString = in.nextLine();
		Date inData = null;
		try {
			inData = Constant.DATE.parse(inDataString);
		} catch(ParseException e) {
			System.err.println("Eccezione Parse catturata.");
			System.err.println("Sara' inserita la data odierna.");
			inData = new Date();
		}
		
		LinkedHashMap<String, Veicolo> hashVeicoli = parcoVeicoli.searchVeicoloByAnnoImmatricolazione(inData);
		
		if(!hashVeicoli.isEmpty()) {
			System.out.println("\t--- Veicoli immatricolati dopo l'eta' inserita ---");
			for(Veicolo veicoloTemp : hashVeicoli.values())
				veicoloTemp.print(System.out);
		}
		else
			throw new VeicoloNotFoundException("Non sono stati trovati veicoli immatricolati dopo la data inserita.");
		
		in.close();
	}

}
