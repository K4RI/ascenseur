public class NainJardin implements Victime {
    int solid;

    public NainJardin (int solid) {
        this.solid = solid;
    }

    public String toString() {
        return "Je suis un nain solide de " + this.solid + "\n";
    }

    public String getNom() {
        if (this.solid == 0){
            return "un nain brisé";
        } else {
            return "un nain";
        }
    }

    public int subitFrappe(int coup){
        if (coup>this.solid) {
            int s = this.solid;
            this.solid = 0;
            return -s;
        } else {
            return -coup;
        }
    }

    public int subitCharme(int coup) {
        return -1;
    }
}
