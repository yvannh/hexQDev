package hex;

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
		this.jActuel = Pion.Rond;
		this.manche = 0;
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
	}
	
	public void changementJoueur() {
		if(manche%JOUEURS_MAX == 0) {
			jActuel = Pion.Rond;
		} else {
			jActuel = Pion.Croix;
		}
	}
	
	public boolean bordRond(int x, int y) {
		return (x >= 0 || x < taille()) 
				&& y == taille() - 1
				&& jActuel.equals(Pion.Rond);
	}

	public boolean bordCroix(int x, int y) {
		return (y >= 0 || y < taille()) 
				&& x == taille() - 1
				&& jActuel.equals(Pion.Croix);
	}
	
	public boolean direction(boolean[][] visite, int x, int y) {
		visite[x][y] = true;
		
		if(jActuel.equals(Pion.Rond)) {
			if(bordRond(x,y)) {
				return true;
			}
		} else {
			if(bordCroix(x,y)) {
				return true;
			}
		}
		
		if(estValide(x-1,y,jActuel) && !visite[x-1][y]) return direction(visite,x-1,y);
		if(estValide(x-1,y+1,jActuel) && !visite[x-1][y+1]) return direction(visite,x-1,y+1);
		if(estValide(x,y-1,jActuel) && !visite[x][y-1]) return direction(visite,x,y-1);
		if(estValide(x,y+1,jActuel) && !visite[x][y+1]) return direction(visite,x,y+1);
		if(estValide(x+1,y-1,jActuel) && !visite[x+1][y-1]) return direction(visite,x+1,y-1);
		if(estValide(x+1,y,jActuel) && !visite[x+1][y]) return direction(visite,x+1,y);
		
		
		return false;
	}
	
	public boolean[][] tableauVide() {
		boolean[][] tabP = new boolean[taille()][taille()];
		
		for(int i = 0; i < taille(); i++) {
			for(int j = 0; j < taille(); j++) {
				tabP[i][j] = false;
			}
		}
		
		return tabP;
	}
	
	public boolean[][] debut() {
		boolean[][] tab = tableauVide();
		for(int i = 0; i < taille(); i++) {
			if(jActuel.equals(Pion.Rond)) {
				if(t[i][0].equals(jActuel)) {
					tab[i][0] = true;
				}
			} else {
				if(t[0][i].equals(jActuel)) {
					tab[0][i] = true;
				}
			}
		}
		
		return tab;
	}
	
	public boolean verifGagnant() {
		boolean[][] visite = debut();
		
		for(int i = 0; i < taille(); i++) {
			if(jActuel.equals(Pion.Rond)) {
				if(visite[i][0] == true)
					if(direction(visite,i, 0)) {
						return true;
					}
			} else {
				if(visite[0][i] == true)
					if(direction(visite,0, i)){
						return true;
					}
			}
		}

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

	public Pion joueurActuel() {
		return this.jActuel;
	}


}
