package hex;

public enum Pion {
	Vide('.'),Croix('X'),Rond('O');
	private char symbole;
	
	Pion(char symbole) {
		this.symbole=symbole;
	}
	@Override
	public String toString() {
		return ""+this.symbole;
	}
	public static Pion get(char string) {
		for (Pion p : Pion.values()) {
			if(p.symbole==string) {
				return p;
			}
		}
		throw new IllegalArgumentException("symbole inconnu "+string);
	}
}
