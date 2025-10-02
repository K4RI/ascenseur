public abstract class Personnage implements Victime {
    private String nom;
    private int pv;

    public Personnage(String nom, int pv) {
        this.nom = nom;
        this.pv = pv;
    }

    public String toString() {
        if (!this.mort()) {
            return "Je m'appelle " + this.nom + " et j'ai " + this.pv + " points de vie.\n";
        } else {
            return this.nom + " est mort.\n";
        }
    }

    public String getNom() {
        return this.nom;
    }

    public boolean mort() {
        return (this.pv <= 0);
    }

    public int getVie() {
        return this.pv;
    }

    public void addVie(int n) {
        this.pv += n;
    }

    abstract public void attaque(Victime p);

    abstract public int subitFrappe(int coup);

    abstract public int subitCharme(int coup);
}
