package tests;

import org.junit.jupiter.api.Test;

import hex.Plateau;

class JeuTest {

	@Test
	void test() {
		final int taille = 4;
		String pos=".XO."
				 + "OXXO"
				 + "XOXX"
				 + "OOO.";
		Plateau p = new Plateau(taille,pos);
		
		p.jouer(0, 0);
		System.out.println(p.toString());
		System.out.println(p.verifGagnant());
		p.changementJoueur();
		
		p.jouer(3, 3);
		System.out.println(p.toString());
		System.out.println(p.verifGagnant());
		p.changementJoueur();
	}

}
