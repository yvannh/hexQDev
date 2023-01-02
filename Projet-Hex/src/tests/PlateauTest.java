package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hex.Plateau;

class PlateauTest {

	@Test
	void test() {
		final int taille = 4;
		Plateau p = new Plateau(taille);
		assertEquals(taille, p.taille());
		//System.out.println(p.toString());
		assertEquals(
				" A B C D\n" + 
				"1 . . . .\n" + 
				"2  . . . .\n" + 
				"3   . . . .\n" + 
				"4    . . . .\n", p.toString());
		String pos="....XOXXOO.OX...";
		Plateau p1 = new Plateau(taille,pos);
		System.out.println(p1.toString());
		assertEquals(
				" A B C D\n" + 
				"1 . . . .\n" + 
				"2  X O X X\n" + 
				"3   O O . O\n" + 
				"4    X . . .\n", p1.toString());
		String[] lignes;
		lignes=Plateau.decouper(taille,pos);
		
		String[] lignes_rep= {"....","XOXX","OO.O","X..."};
		for(int i=0;i<taille;i++) {
			assertEquals(lignes_rep[i],lignes[i]);
		}
	}

}
