package sources.hex;

public class Plateau {
	private final static int TAILLE_MAX = 26;
	private final static int JOUEURS_MAX=2;
	
	private Pion[][] t;
	private int manche;
	private Pion jActuel;

	public Plateau(int taille) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];
		for(int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				this.t[i][j]=Pion.Vide;
			}
		}
		this.jActuel = Pion.Rond;
		this.manche = 0;
	}
	
	public Plateau(int taille, String pos) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];
		String[] lignes=decouper(taille,pos);
		for (int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				char c=lignes[i].charAt(j);
				this.t[i][j]=Pion.get(c);
			}
		}
	}

	public int taille() {
		return t.length;
	}
	
	public boolean estValide(int x, int y, Pion p) {
		return x>=0 && x < taille() &&
				y >= 0 && y < taille() &&
				t[x][y].equals(p);
	}
	
	public void jouer(int x, int y) {
		if(estValide(x, y, Pion.Vide)) {
			t[x][y] = jActuel;
		}
		
		this.manche++;
		if(manche%2 == 0) {
			jActuel = Pion.Rond;
		} else {
			jActuel = Pion.Croix;
		}
	}
	
	public boolean bordPion(int x, int y) {
		if(jActuel.equals(Pion.Rond)) {
			return x == 0 || x == taille() - 1;
		} else {
			return y == 0 || y == taille() - 1;
		}
	}
	
	public boolean direction() {
		
	}
	
	public Pion[][] tableauPion() {
		Pion[][] tabP = new Pion[taille()][taille()];
		
		for(int i = 0; i < taille(); i++) {
			for(int j = 0; j < taille(); j++) {
				if(t[i][j].equals(jActuel)) {
					tabP[i][j] = jActuel;
				} else  {
					tabP[i][j] = Pion.Vide;
				}
			}
		}
		
		return tabP;
	}
	
	public int debut(Pion[][] pionJ) {
		for(int i = 0; i < taille(); i++) {
			if(jActuel.equals(Pion.Rond)) {
				if(pionJ[i][0].equals(jActuel)) return i;
			} else {
				if(pionJ[0][i].equals(jActuel)) return i;
			}
		}
		return -1;
	}
	
	public boolean verifGagnant() {
		Pion[][] pionJ = tableauPion();
		
		
		
		return false;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i=0;i<this.taille();i++) {
			s+= (" "+(char)('A'+i));
		}
		s+="\n";
		for(int i=0;i<this.taille();i++) {
			s+=i+1;
			for(int h=0;h<i;h++) {
				s+=" ";
			}
			for(int j=0;j<this.taille();j++) {
				s+=" "+this.t[i][j];
			}
			s+="\n";
		}
		return s;
	}

	public static String[] decouper(int taille, String pos) {
        String[] s= new String[taille];
        for(int i=0;i<taille;i++) {
            for(int j=0;j<taille;j++) {
                if(j==taille-1) {
                    s[i]=pos.substring(i*taille, (i*taille)+taille);
                }
            }

        }
        return s;
    }




}
