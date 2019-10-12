package classi;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import utility.Constant;

public class Veicolo {

	public Veicolo(String targa, Date annoImmatricolazione, int chilometri, double costoAcquisto) {
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
		this.chilometri = chilometri;
		this.costoAcquisto = costoAcquisto;
		this.arrayManutenzioni = new ArrayList<Manutenzione>();
	}
	
	public ArrayList<Manutenzione> getArrayManutenzioni() {
		return arrayManutenzioni;
	}
	public void setArrayManutenzioni(ArrayList<Manutenzione> arrayManutenzioni) {
		this.arrayManutenzioni = arrayManutenzioni;
	}
	public String getTarga() {
		return targa;
	}
	public Date getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}
	public int getChilometri() {
		return chilometri;
	}
	public double getCostoAcquisto() {
		return costoAcquisto;
	}

	public void addManutenzione(Manutenzione m) {
		this.arrayManutenzioni.add(m);
	}
	
	public static Veicolo read(Scanner in) {
		
		if(!in.hasNextLine()) return null;
		String targa = in.nextLine();
		
		if(!in.hasNextLine()) return null;
		String inString = in.nextLine();
		Date annoImmatricolazione = null;
		try {
			annoImmatricolazione = Constant.DATE.parse(inString);
		} catch(ParseException e) {
			System.err.println("Eccezione Parse catturata.");
			System.err.println("Sara' inserita la data odierna.");
			annoImmatricolazione = new Date();
		}
		
		if(!in.hasNextLine()) return null;
		String chilometriString = in.nextLine();
		int chilometri = 0;
		try {
			chilometri = Integer.parseInt(chilometriString);
		} catch(NumberFormatException e) {
			System.err.println("Eccezione NumberFormat catturata.");
			System.err.println("Sara' inserito il valore 0.");
		}
		
		if(!in.hasNextLine()) return null;
		String costoString = in.nextLine();
		double costoAcquisto = 0;
		try {
			costoAcquisto = Double.parseDouble(costoString);
		} catch(NumberFormatException e) {
			System.err.println("Eccezione NumberFormat catturata.");
			System.err.println("Sara' inserito il valore 0.");
		}
		
		return new Veicolo(targa, annoImmatricolazione, chilometri, costoAcquisto);
		
	}
	
	public void print(PrintStream ps) {
		ps.println(targa);
		ps.println(Constant.DATE.format(annoImmatricolazione));
		ps.println(chilometri);
		ps.println(costoAcquisto);
		System.out.println("");
	}

	private String targa;
	private Date annoImmatricolazione;
	private int chilometri;
	private double costoAcquisto;
	private ArrayList<Manutenzione> arrayManutenzioni;
	
}
