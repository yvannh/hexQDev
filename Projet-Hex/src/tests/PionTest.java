package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hex.Pion;

class PionTest {

	@Test
	void test() {
		assertEquals("X",Pion.Croix.toString());
		assertEquals("O",Pion.Rond.toString());
		assertEquals(".",Pion.Vide.toString());
		assertTrue(Pion.Croix==Pion.get('X'));
	}

}
