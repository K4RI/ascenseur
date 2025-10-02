public class Main {
    public static void main(String[] arg){

        Victime[] tab = new Victime[12];
        tab[0] = new Monstre("Monstre0", 1+(int)(100*Math.random()));
        tab[1] = new Monstre("Monstre1", 1+(int)(100*Math.random()));
        tab[2] = new Monstre("Monstre2", 1+(int)(100*Math.random()));
        tab[3] = new Sorcier("Sorcier0", 1+(int)(100*Math.random()));
        tab[4] = new Sorcier("Sorcier1", 1+(int)(100*Math.random()));
        tab[5] = new Sorcier("Sorcier2", 1+(int)(100*Math.random()));
        tab[6] = new Magicien("Magicien", 1+(int)(100*Math.random()));
        tab[7] = new NainJardin(1+(int)(100*Math.random()));
        tab[8] = new NainJardin(1+(int)(100*Math.random()));
        tab[9] = new GnomeJardin(1+(int)(100*Math.random()));

        for (int j=0; j<10; j++) {
            System.out.println(tab[j]);
        }
        
        int monstres = 3, sorciers = 3, magiciens = 1;
        int t=0, atk, def;
        while ((monstres > 0 || magiciens > 0) && (monstres + sorciers + magiciens > 1)) {
            t++;
            // attaquant : personnage non-mort
            do {
                atk = (int) (10*Math.random());
            } while (!(tab[atk] instanceof Personnage) || ((Personnage) tab[atk]).mort());
            
            // défenseur : non-personnage ou personnage non-mort
            do {
                def = (int) (10*Math.random());
            } while (atk == def || (tab[def] instanceof Personnage && ((Personnage) tab[def]).mort()));

            if (tab[def] instanceof Personnage){
                System.out.println(t + ". " + tab[atk].getNom() + " (" + ((Personnage) tab[atk]).getVie() + ") attaque " + tab[def].getNom() + " (" + ((Personnage) tab[def]).getVie() + ")");
                if (((Personnage) tab[def]).mort()){
                    System.out.println(tab[def]);
                }
            } else {
                System.out.println(t + ". " + tab[atk].getNom() + " (" + ((Personnage) tab[atk]).getVie() + ") attaque " + tab[def].getNom());
            }

            ((Personnage) tab[atk]).attaque(tab[def]);

            if (tab[atk] instanceof SuperPouvoir && tab[def] instanceof SuperPouvoir) {
                System.out.println("Excès de magie tous les non-magiques perdent 1 pv");
                for (int j=0; j<10; j++) {
                    if (!(tab[j]instanceof SuperPouvoir) && (tab[j] instanceof Personnage)){
                        ((Personnage) tab[j]).addVie(-1);
                        if (((Personnage) tab[j]).getVie() == 0){
                            System.out.println("---" + tab[j]);
                            if (tab[j] instanceof Monstre) { monstres--; }
                            else if (tab[j] instanceof Sorcier) { sorciers--; }
                            else { magiciens--; }
                        }
                    }
                }
            }
            if (((Personnage) tab[atk]).mort()){
                System.out.println("---" + tab[atk]);
                if (tab[atk] instanceof Monstre) { monstres--; }
                else if (tab[atk] instanceof Magicien) { magiciens--; }
                else { sorciers--; }
            }
            if ((tab[def] instanceof Personnage) && (((Personnage) tab[def]).mort())){
                System.out.println("---" + tab[def]);
                if (tab[def] instanceof Monstre) { monstres--; }
                else if (tab[def] instanceof Magicien) { magiciens--; }
                else { sorciers--; }
            }
        }

        if (monstres == 0 && magiciens == 0) {
            System.out.println("les sorciers ont gagné");
        } else {
            for (int j=0; j<10; j++) {
                if (tab[j] instanceof Personnage && !(((Personnage)tab[j]).mort())){
                    System.out.println(tab[j].getNom() + " a gagné !!");                    
                }
            }
        }
        
    }
}
