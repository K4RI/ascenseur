public class Monstre extends Personnage {


    public Monstre(String nom, int pv) {
        super(nom, pv);
    }

    public void attaque(Victime p){
        if (!this.mort()) {
            int riposte = p.subitFrappe(this.getVie()/2);
            this.addVie(riposte);
        }
    }

    public int subitFrappe(int coup){
        this.addVie(-coup);
        if (!this.mort()) {
            return -this.getVie()/2;
        } else {
            return 0;
        }
    }

    public int subitCharme(int coup){
        this.addVie(-coup);
        if (!this.mort()) {
            return this.getVie()/2;
        } else {
            return 0;
        }
    }
}
