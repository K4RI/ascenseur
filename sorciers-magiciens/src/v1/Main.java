public class Main {
    public static void main(String[] arg){
        
        int monstres = 3;
        int sorciers = 3;
        final int tot = monstres+sorciers;
        Personnage[] tab = new Personnage[tot];
        for (int i=0; i<monstres; i++) {
            tab[i] = new Monstre("Monstre" + i, 1+(int) (100*Math.random()));
        }
        for (int i=0; i<sorciers; i++) {
            tab[monstres+i] = new Sorcier("Sorcier" + i, 1+(int) (100*Math.random()));
        }
        for (int j=0; j<tot; j++) {
            System.out.println(tab[j]);
        }
      
        int atk, def, t=0;
        while (!(monstres==0 || (monstres==1 && sorciers==0))) {
            t++;
            do {
                atk = (int) (tot*Math.random());
            } while (tab[atk].mort());
            
            do {
                def = (int) (tot*Math.random());
            } while (atk == def || tab[def].mort());

            System.out.println(t + ". " + tab[atk].getNom() + " (" + tab[atk].getVie() + ") attaque " + tab[def].getNom() + " (" + tab[def].getVie() + ")");

            tab[atk].attaque(tab[def]);

            if (tab[def].mort()){
                if (tab[def] instanceof Sorcier) {
                    sorciers--;
                } else {
                    monstres--;
                }
                System.out.println("---" + tab[def]);
            }
            if (tab[atk].mort()){
                if (tab[atk] instanceof Sorcier) {
                    sorciers--;
                } else {
                    monstres--;
                }
                System.out.println("---" + tab[atk]);
            }
        }
        if (sorciers>1){
            System.out.println("Il n'y a plus que des sorciers");
                return;
        } else {
            for (int j=0; j<tot; j++) {
                if (!tab[j].mort()){
                    System.out.println(tab[j].getNom() + " a gagné !!");                    
                }
            }
            return;
        }
    }
}