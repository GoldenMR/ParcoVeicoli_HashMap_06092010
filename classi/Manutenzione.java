package classi;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import utility.Constant;

public class Manutenzione {
	
	public Manutenzione(Date dataManutenzione, double costoManutenzione, String descrizione, String targaVeicolo) {
		this.dataManutenzione = dataManutenzione;
		this.costoManutenzione = costoManutenzione;
		this.descrizione = descrizione;
		this.targaVeicolo = targaVeicolo;
		this.veicolo = null;
	}
	
	public String getTargaVeicolo() {
		return targaVeicolo;
	}
	public Veicolo getVeicolo() {
		return veicolo;
	}
	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}
	public Date getDataManutenzione() {
		return dataManutenzione;
	}
	public double getCostoManutenzione() {
		return costoManutenzione;
	}
	public String getDescrizione() {
		return descrizione;
	}

	public static Manutenzione read(Scanner in) {
		
		if(!in.hasNextLine()) return null;
		String dataString = in.nextLine();
		Date dataManutenzione = null;
		try {
			dataManutenzione = Constant.DATE.parse(dataString);
		} catch(ParseException e) {
			System.err.println("Eccezione Parse catturata.");
			System.err.println("Sara' inserita la data odierna.");
			dataManutenzione = new Date();
		}
		
		if(!in.hasNextLine()) return null;
		String costoString = in.nextLine();
		double costoManutenzione = 0;
		try {
			costoManutenzione = Double.parseDouble(costoString);
		} catch(NumberFormatException e) {
			System.err.println("Eccezione NumberFormat catturata.");
			System.err.println("Sara' inserito il valore 0.");
		}
		
		if(!in.hasNextLine()) return null;
		String descrizione = in.nextLine();
		
		if(!in.hasNextLine()) return null;
		String targaVeicolo = in.nextLine();
		
		return new Manutenzione(dataManutenzione, costoManutenzione, descrizione, targaVeicolo);
		
	}

	public void print(PrintStream ps) {
		ps.println(Constant.DATE.format(dataManutenzione));
		ps.println(costoManutenzione);
		ps.println(descrizione);
		System.out.println("");
	}

	private Date dataManutenzione;
	private double costoManutenzione;
	private String descrizione;
	private String targaVeicolo;
	private Veicolo veicolo;
	
}
