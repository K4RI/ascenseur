public class GnomeJardin extends NainJardin implements SuperPouvoir {
    

    public GnomeJardin (int solid) {
        super(solid);
    }

    public String toString() {
        return "Je suis un gnome solide de " + this.solid + "\n";
    }

    public String getNom() {
        if (this.solid == 0){
            return "un gnome brisé";
        } else {
            return "un gnome";
        }
    }

    @Override
    public double sort() {
        return solid*Math.random();
    }

    @Override
    public int subitCharme(int coup) {
        if (this.sort() > coup) {
            return -coup;
        }
        return 0;
    }
}