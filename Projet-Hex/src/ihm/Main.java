package ihm;

import java.util.Scanner;

import hex.Pion;
import hex.Plateau;

public class Main {

	public static void main(String[] args) {
		int mode = bienvenue();
		int taille = tailleGrille();
		Plateau p = new Plateau(taille);
		
		gagner(jeu(p, mode));
		
	}
	
	public static void gagner(Pion pion) {
		if(pion.equals(Pion.Rond)) {
			System.out.println("Les Ronds remportent la partie !");
		} else {
			System.out.println("Les Croix remportent la partie !");
		}
	}
	
	public static int bienvenue() {
		System.out.println("Bienvenue, veuillez choisir un mode de jeu en entrant le numéro situé devant");
		System.out.println("1. Joueur contre Joueur");
		System.out.println("2. Joueur contre Ordinateur");
		System.out.println("3. Ordinateur contre Ordinateur");
		Scanner sc = new Scanner(System.in);
		int entree = sc.nextInt();
		return entree;
	}
	
	public static int tailleGrille() {
		System.out.println("Veuillez entrez une taille de grille (comprise entre 1 et 26)");
		Scanner sc = new Scanner(System.in);
		int entree = sc.nextInt();
		return entree;
	}
	
	public static Pion jeu(Plateau p, int mode) {
		Pion gagnant = Pion.Vide;
		while(gagnant.equals(Pion.Vide)) {
			if(mode == 1) {
				if(tourJcJ(p)) gagnant = p.joueurActuel();
			} else if (mode == 2) {
				if(tourJcO(p)) gagnant = p.joueurActuel();
			} else {
				if(tourOcO(p)) gagnant = p.joueurActuel();
			}
			
		}
		
		return gagnant;
	}
	
	public static boolean tourJcJ(Plateau p) {
		int x = 0;
		int y = 0;
		int c = 0;
		
		if(p.joueurActuel().equals(Pion.Rond)) {
			System.out.println("C'est aux Ronds de jouer \n");
		} else {
			System.out.println("C'est aux Croix de jouer \n");
		}
		
		System.out.println(p.toString());
		
		Scanner sc = new Scanner(System.in);
		String entree = sc.next();
		if(entree.length() > 2);
		if(!(entree.charAt(0) >= 'A' && entree.charAt(0) <= 'Z'+1)) return false;
		
		
		if(entree.length() > 2) {
			if(!(entree.charAt(0) >= 'A' && entree.charAt(0) <= 'Z'+1)) return false;
			x = (char) (entree.charAt(0)-('A'));
			y = (char) (entree.charAt(1)-('1'));
		} else if (entree.length() == 3) {
			if(!(entree.charAt(0) >= 'A' && entree.charAt(0) <= 'Z'+1)) return false;
			x = (char) (entree.charAt(0)-('A'));
			c = (char) (entree.charAt(1));
			y = (char) (entree.charAt(2)-('1'));
		}

		System.out.println(c);
		System.out.println(y);
		
		p.jouer(x, y);
		if(p.verifGagnant()) return true;
		else p.changementJoueur();
		return false;
	}
	
	public static boolean tourJcO(Plateau p) {
		
		int x = (int) (Math.random() * ( p.taille() - 0 ));;
		int y = (int) (Math.random() * ( p.taille() - 0 ));;
		
		if(p.joueurActuel().equals(Pion.Rond)) {
			System.out.println("C'est aux Ronds de jouer \n");
			System.out.println(p.toString());
		} else {
			System.out.println("C'est aux Croix de jouer \n");
			Scanner sc = new Scanner(System.in);
			String entree = sc.next();
			if(entree.length() > 2) {
				if(!(entree.charAt(0) >= 'A' && entree.charAt(0) <= 'Z'+1)) return false;
				x = (char) (entree.charAt(0)-('A'));
				y = (char) (entree.charAt(1)-('1'));
			} else if (entree.length() == 3) {
				if(!(entree.charAt(0) >= 'A' && entree.charAt(0) <= 'Z'+1)) return false;
				x = (char) (entree.charAt(0)-('A'));
				y = (char) ((entree.charAt(1)-('1'))+(entree.charAt(2)-('1')));
			}
			
		}
		
		p.jouer(x, y);
		if(p.verifGagnant()) return true;
		else p.changementJoueur();
		return false;
	}
	
	public static boolean tourOcO(Plateau p) {
		int x = (int) (Math.random() * ( p.taille() - 0 ));;
		int y = (int) (Math.random() * ( p.taille() - 0 ));;
		
		System.out.println(x + " " + y);
		
		if(p.joueurActuel().equals(Pion.Rond)) {
			System.out.println("C'est aux Ronds de jouer \n");
		} else {
			System.out.println("C'est aux Croix de jouer \n");
		}

		System.out.println(p.toString());
		
		p.jouer(x, y);
		if(p.verifGagnant()) return true;
		else p.changementJoueur();
		return false;
	}
}
