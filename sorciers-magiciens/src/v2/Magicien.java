public class Magicien extends Sorcier implements SuperPouvoir {
    
    public Magicien(String nom, int pv) {
        super(nom, pv);
    }

    @Override
    double getPouvoir(){
        return extra*super.getPouvoir();
    }

    public double sort() {
        return Math.random()*getPouvoir();
    }

    @Override
    public int subitCharme(int coup){
        return (int) (-coup*this.sort());
    }
}
