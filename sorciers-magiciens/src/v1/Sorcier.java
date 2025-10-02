public class Sorcier extends Personnage {
    private double pouvoir;

    public Sorcier(String nom, int pv) {
        super(nom, pv);
        this.pouvoir = Math.random();
    }

    public String toString() {
        if (!this.mort()) {
            return "Je m'appelle " + this.getNom() + " et j'ai " + this.getVie() + " points de vie et un pouvoir x" + Math.round(this.pouvoir * 100.0) / 100.0 + ".\n";
        } else {
            return this.getNom() + " est mort.\n";
        }
    }

    public void attaque(Personnage p){
        if (!this.mort()) {
            int riposte = p.subitCharme((int)(this.getVie()*this.pouvoir));
            this.addVie(riposte);
        }
    }

    public int subitFrappe(int coup){
        this.addVie(-coup);
        if (!this.mort()) {
            return (int)(-this.getVie()*this.pouvoir);
        } else {
            return 0;
        }
    }

    public int subitCharme(int coup){
        return 0;
    }
}