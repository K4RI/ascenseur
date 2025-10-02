public class Sorcier extends Personnage {
    private double pouvoir;

    public Sorcier(String nom, int pv) {
        super(nom, pv);
        this.pouvoir = Math.random();
    }

    double getPouvoir(){
        return this.pouvoir;
    }

    public void attaque(Victime p){
        if (!this.mort()) {
            int riposte = p.subitCharme((int)(this.getVie()*getPouvoir()));
            this.addVie(riposte);
        }
    }

    public int subitFrappe(int coup){
        this.addVie(-coup);
        if (!this.mort()) {
            return (int)(-this.getVie()*getPouvoir());
        } else {
            return 0;
        }
    }

    public int subitCharme(int coup){
        return 0;
    }
}
