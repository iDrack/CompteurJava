public class Mot {
    private String mot;
    private int cpt;

    public Mot(String m){
        cpt = 1;
        mot = m;
    }

    public void nouvelleOccurence(){
        cpt ++;
    }

    public int getOccurence(){
        return cpt;
    }

    public String getMot(){
        return this.mot;
    }
}
