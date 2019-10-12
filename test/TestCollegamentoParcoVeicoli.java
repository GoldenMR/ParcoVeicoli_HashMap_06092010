package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import classi.ParcoVeicoli;
import utility.Constant;

public class TestCollegamentoParcoVeicoli {

	public static void main(String[] args) {

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
		
		System.out.println("\tContenuto del file " + Constant.FILE_MANUTENZIONI);
		parcoVeicoli.printManutenzioni();
		
		System.out.println("\tContenuto del file " + Constant.FILE_VEICOLI);
		parcoVeicoli.printVeicoli();
		
		
		in.close();
		
	}
	
}